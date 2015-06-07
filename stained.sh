#!/bin/bash
colors="white
orange
magenta
light_blue
yellow
lime
gray
silver
purple
pink
cyan
blue
brown
green
red
black"
for color in $colors
do
json="{
    \"parent\": \"item/meshes/block/chiseled\",
    \"textures\": {
        \"top\": \"blocks/polished/clay_$color\",
        \"side\": \"blocks/polished/clay_$color\",
        \"bottom\": \"blocks/polished/clay_$color\"
    }
}"
echo $json > "assets/minecraft/models/item/${color}_stained_hardened_clay.json"
done