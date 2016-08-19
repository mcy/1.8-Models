def skip(i: Int) = i match {
    case 0 => 0
    case 1 => 1
    case 2 => 3
    case 3 => 4
    case 4 => 5
    case 5 => 7
    case 6 => 8
    case 7 => 9
    case 8 => 11
    case 9 => 12
    case 10 => 13
    case 11 => 15
}
for(d <- 0 to 1)
for(h <- 0 to 11)
for(m <- 0 to 11) {
    val day = 
        if(d == 0 && h >= 6 || d == 1 && h < 6) {
            1
        } else 0
    println(s"""        {"predicate":{"time":${(d * 144 + h * 12 + m)/288D}},"model":"itemModels/misc/clock/d${day}h${skip(h)}m${skip(m)}"},""")
}
