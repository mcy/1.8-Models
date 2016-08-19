for(a <- 0 to 15) {
    println(s"""        {"predicate":{"angle":${a/16D}},"model":"itemModels/misc/compass/d${15-a}"},""")
}
