//package io.boole.convertpack

import scala.collection.mutable
import scala.math.Numeric.DoubleIsFractional
import scala.util.control.Breaks._
import scala.util.parsing.json.JSON
import java.io._

if(args.length == 0) {
  println("scala convert.scala [files]")
}
else for(arg <- args) {
  println("Converting: " + arg)
  convertpack.convert(new File(arg))
}

object convertpack {

  val tag = "\"display\":"

  def convert(src: File): Unit = {
    val in = new BufferedReader(new FileReader(src))
    val str = Iterator.continually(in.readLine()).takeWhile(_ ne null).mkString("\n")
    in.close()
    val cnv = convert(str)
    if(cnv.isEmpty) {
      println("No display to convert!")
      return
    }
    val out = new PrintWriter(new BufferedWriter(new FileWriter(src)))
    out.write(cnv.get)
    out.flush()
    out.close()
  }

  def convert(src: String): Option[String] = {
    extractDisplay(src).map(_.converted.insert(src))
  }

  def extractDisplay(src: String): Option[Display] = {
    val start = src.indexOf(tag)
    if(start == -1) return None
    var end = start + tag.length
    var counter = 0
    breakable {
      for (c <- src.substring(start + tag.length)) {
        c match {
          case '{' => counter += 1
          case '}' => counter -= 1
            if (counter == 0) {
              end += 1
              break
            }
          case _ =>
        }
        end += 1
      }
    }
    val display = src.substring(start + tag.length, end)
    val tree = JSON.parseFull(display).get.asInstanceOf[Map[String, Any]]
    val map = mutable.HashMap.empty[DisplayType, Offset]
    for((name, obj) <- tree) {
      val tpe = DisplayType.byName(name)
      if(tpe != null) {
        val off = obj.asInstanceOf[Map[String, Any]]
        val rotation = off.getOrElse("rotation", List(0,0,0)).asInstanceOf[List[Any]]
        val translation = off.getOrElse("translation", List(0,0,0)).asInstanceOf[List[Any]]
        val scale = off.getOrElse("scale", List(1,1,1)).asInstanceOf[List[Any]]
        map(tpe) = Offset(
          (rotation(0).asInstanceOf[Number].doubleValue(), rotation(1).asInstanceOf[Number].doubleValue(), rotation(2).asInstanceOf[Number].doubleValue()),
          (translation(0).asInstanceOf[Number].doubleValue(), translation(1).asInstanceOf[Number].doubleValue(), translation(2).asInstanceOf[Number].doubleValue()),
          (scale(0).asInstanceOf[Number].doubleValue(), scale(1).asInstanceOf[Number].doubleValue(), scale(2).asInstanceOf[Number].doubleValue()),
          tpe
        )
      }
    }
    map(DisplayType.FirstLeft) = map.get(DisplayType.FirstRight).map{ o =>
      o.copy(scale = o.scale * (-1,1,1))
      //o.copy(rotation = o.rotation + (0,180,-45))
    }.orNull
    map(DisplayType.ThirdLeft) = map.get(DisplayType.ThirdRight).map { o =>
      o.copy(scale = o.scale * (-1,1,1))
    }.orNull
    Some(Display(map.filter{_._2 ne null}.toMap, (start, end), 4))
  }


  type Vec = (Double, Double, Double)

  case class Offset(
                     rotation: Vec,
                     translation: Vec,
                     scale: Vec,
                     tpe: DisplayType = null,
                     isConverted: Boolean = false
                   ) {
    def converted: Offset = {
      if(isConverted || tpe == null) return this
      if(tpe == DisplayType.ThirdRight) {
        val newRotation = ((-rotation._3, rotation._1, -rotation._2) + (90, -90, -90)) % 360
        val newTranslation = (-translation._1, -translation._3, translation._2) * scale.sqrt
        val newScale = scale
        this.copy(rotation = newRotation, translation = newTranslation, scale = newScale, isConverted = true)
      } else {
        val newRotation = (rotation + tpe.default.rotation) % 360
        val newTranslation = translation  + tpe.default.translation
        val newScale = scale * tpe.default.scale 
        this.copy(rotation = newRotation, translation = newTranslation, scale = newScale, isConverted = true)
      }
    }
  }

  object Offset {
    def zero(tpe: DisplayType) = Offset((0,0,0),(0,0,0),(1,1,1),tpe)
  }

  case class Display(
                      data: Map[DisplayType, Offset],
                      loc:  (Int, Int),
                      tabs:  Int
                    ) {
    def converted: Display = {
      this.copy(data = data.mapValues(_.converted))
    }

    def pretty: String = {
      val sb = new StringBuilder
      val tab = " " * tabs
      sb ++= tag + "{" + "\n"
      for((tpe, off) <- data) {
        sb ++= tab * 2 + "\"" + tpe.name + "\"" + ": {" + "\n"
        sb ++= tab * 3 + "\"rotation\": [ " + off.rotation._1 + ", " + off.rotation._2 + ", " + off.rotation._3 + " ]," + "\n"
        sb ++= tab * 3 + "\"translation\": [ " + off.translation._1 + ", " + off.translation._2 + ", " + off.translation._3 + " ]," + "\n"
        sb ++= tab * 3 + "\"scale\": [ " + off.scale._1 + ", " + off.scale._2 + ", " + off.scale._3 + " ]" + "\n"
        sb ++= tab * 2 + "}," + "\n"
      }
      sb.deleteCharAt(sb.length - 2)
      sb ++= "\n" + tab + "}"
      sb.toString
    }

    def insert(src: String) = {
      val head = src.substring(0, loc._1)
      val tail = src.substring(loc._2)
      head + pretty + tail
    }
  }

  sealed class DisplayType(val name: String, _default: Offset) {
    val default = _default.copy(tpe = this)
  }
  object DisplayType {
    object ThirdRight extends DisplayType("thirdperson_righthand", Offset((-90, 180, 0), (0, -3, 3), (1, 1, 1)))
    object ThirdLeft  extends DisplayType("thirdperson_lefthand",  Offset((75, 45, 0), (0, 2.5, 0), (0.375, 0.375, 0.375)))
    object FirstRight extends DisplayType("firstperson_righthand", Offset((0, 45, 0), (0, -1, 1), (0.40, 0.40, 0.40)))
    object FirstLeft  extends DisplayType("firstperson_lefthand",  Offset((0, 225, 0), (0, -1, 1), (0.40, 0.40, 0.40)))
    object Gui        extends DisplayType("gui",                   Offset((30, 225, 0), (0, 0, 0), (0.625, 0.625, 0.625)))
    object Head       extends DisplayType("head",                  Offset((0, 0, 0), (0, 0, 0), (1, 1, 1)))
    object Ground     extends DisplayType("ground",                Offset((0, 0, 0), (0, 3, 0), (0.25, 0.25, 0.25)))
    object Fixed      extends DisplayType("fixed",                 Offset((0, 0, 0), (0, 0, 0), (0.5, 0.5, 0.5)))

    val values = List(ThirdRight, ThirdLeft, FirstRight, FirstLeft, Gui, Head, Ground, Fixed)
    def byName(s: String): DisplayType = {
      if(s == "thirdperson") return ThirdRight
      else if (s == "firstperson") return FirstRight
      for (t <- values) {
        if (t.name == s)
          return t
      }
      null
    }
  }

  implicit class _vec(val self: Vec) {
    def +(that: Vec) = (self._1 + that._1, self._2 + that._2, self._3 + that._3)
    def -(that: Vec) = (self._1 - that._1, self._2 - that._2, self._3 - that._3)
    def *(that: Vec) = (self._1 * that._1, self._2 * that._2, self._3 * that._3)
    def x(that: Vec) = (
      self._2 * that._3 - that._2 * self._3,
      self._1 * that._3 - that._1 * self._3,
      self._1 * that._2 - that._1 * self._2
    )
    def ~(that: Vec) = (self._1 * that._1 + self._2 * that._2 + self._3 * that._3)

    def *(that: Double) = (self._1 * that, self._2 * that, self._3 * that)
    def /(that: Double) = (self._1 / that, self._2 / that, self._3 / that)
    def %(that: Double) = (self._1 % that, self._2 % that, self._3 % that)
    
    def rot(e: Vec, th: Double): Vec = rotate(self, e, th)
    def rot(th: Vec) = rotate(self, th)

    def sqrt = (magSqrt(self._1), magSqrt(self._2), magSqrt(self._3))
  }

  def magSqrt(d: Double) = Math.signum(d) * Math.sqrt(Math.abs(d))

  def rotate(v: Vec, e: Vec, th: Double): Vec = {
    val s = Math.sin(Math.toRadians(th))
    val c = Math.cos(Math.toRadians(th))
    v * c + (e x v) * s + e * (e ~ v) * (1 - c)
  }

  def rotate(v: Vec, th: Vec): Vec = {
    v.rot((1,0,0), th._1).rot((0,1,0), th._2).rot((0,0,1), th._3)
  }
}