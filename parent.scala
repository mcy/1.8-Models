import java.io._

for(arg <- args) {
    val src = new File(arg)
    val in = new BufferedReader(new FileReader(src))
    val str = Iterator.continually(in.readLine()).takeWhile(_ ne null).mkString("\n")
    in.close()
    val cnv = {
        if(!str.contains("\"parent\":")){
            if(str(str.indexOf('{') + 1) == '"')
                str.replaceFirst("\\{", "{\"parent\":\"itemModels/base\",")
            else str.replaceFirst("\\{", "{\n    \"parent\":\"itemModels/base\",")
        } else str
    }
    val out = new PrintWriter(new BufferedWriter(new FileWriter(src)))
    out.write(cnv)
    out.flush()
    out.close()
}