def json(n: Int, x: Int, y: Int, h: Double) = 
s"""

        {
            "comment": "light $n",
            "from": [${1+x}, 5, ${2+y}],
            "to": [${5+x}, $h, ${4+y}],
            "shade": false,
            "faces": {
                "up":   {"uv": [${1+x}, ${2+y}, ${5+x}, ${4+y}], "texture": "#lights" },
                "west": {"uv": [${1+x}, ${2+y}, ${2+x}, ${4+y}], "texture": "#lights", "rotation": 90 },
                "east": {"uv": [${4+x}, ${2+y}, ${5+x}, ${4+y}], "texture": "#lights", "rotation": 90 }
            }
        },

        {
            "comment": "light $n",
            "from": [${2+x}, 5, ${1+y}],
            "to": [${4+x}, $h, ${2+y}],
            "shade": false,
            "faces": {
                "up":    {"uv": [${2+x}, ${1+y}, ${4+x}, ${2+y}], "texture": "#lights"},
                "north": {"uv": [${2+x}, ${1+y}, ${4+x}, ${2+y}], "texture": "#lights"}
            }
        },

        {
            "comment": "light $n",
            "from": [${2+x}, 5, ${4+y}],
            "to": [${4+x}, $h, ${5+y}],
            "faces": {
                "up":    {"uv": [${2+x}, ${4+y}, ${4+x}, ${5+y}], "texture": "#lights"},
                "south": {"uv": [${2+x}, ${4+y}, ${4+x}, ${5+y}], "texture": "#lights"}
            }
        },

        {
            "comment": "light $n",
            "from": [${0.58+x}, 5, ${1+y}],
            "to": [${2+x}, ${h-0.01}, ${2+y}],
            "shade": false,
            "rotation": {"origin": [${2+x}, 8, ${1+y}], "axis": "y", "angle": 45 },
            "faces": {
                "up":    {"uv": [${1+x}, ${1+y}, ${2+x}, ${2+y}], "texture": "#lights"},
                "north": {"uv": [${1+x}, ${1+y}, ${2+x}, ${2+y}], "texture": "#lights"}
            }
        },

        {
            "comment": "light $n",
            "from": [${4+x}, 5, ${1+y}],
            "to": [${5.42+x}, ${h-0.01}, ${2+y}],
            "shade": false,
            "rotation": {"origin": [${4+x}, 8, ${1+y}], "axis": "y", "angle": -45 },
            "faces": {
                "up":    {"uv": [${4+x}, ${1+y}, ${5+x}, ${2+y}], "texture": "#lights"},
                "north": {"uv": [${4+x}, ${1+y}, ${5+x}, ${2+y}], "texture": "#lights"}
            }
        },

        {
            "comment": "light $n",
            "from": [${0.58+x}, 5, ${4+y}],
            "to": [${2+x}, ${h-0.01}, ${5+y}],
            "shade": false,
            "rotation": {"origin": [${2+x}, 8, ${5+y}], "axis": "y", "angle": -45 },
            "faces": {
                "up":    {"uv": [${1+x}, ${4+y}, ${2+x}, ${5+y}], "texture": "#lights"},
                "south": {"uv": [${1+x}, ${4+y}, ${2+x}, ${5+y}], "texture": "#lights"}
            }
        },

        {
            "comment": "light $n",
            "from": [${4+x}, 5, ${4+y}],
            "to": [${5.42+x}, ${h-0.01}, ${5+y}],
            "shade": false,
            "rotation": {"origin": [${4+x}, 8, ${5+y}], "axis": "y", "angle": 45 },
            "faces": {
                "up":    {"uv": [${4+x}, ${4+y}, ${5+x}, ${5+y}], "texture": "#lights"},
                "south": {"uv": [${4+x}, ${4+y}, ${5+x}, ${5+y}], "texture": "#lights"}
            }
        },"""

var n = 1
for(x <- List(0, 5, 10); y <- List(0, 5, 10)) {
    println(json(n, x, y, 6.5))
    println()
    println()
    n += 1
}