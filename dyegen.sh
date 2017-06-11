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

for color in $colors; do
    read -r -d '' terracotta <<EOF
{
    "parent": "item_models/block/chiseled",
    "textures": {
        "top": "blocks/terracotta/$color",
        "side": "blocks/terracotta/$color",
        "bottom": "blocks/terracotta/$color"
    }
}
EOF
    read -r -d '' wool <<EOF
{
    "parent": "item_models/block/wool_roll",
    "textures": {
        "wool": "blocks/wool/$color"
    }
}
EOF
    read -r -d '' glazed <<EOF
{
    "parent": "item_models/block/plates",
    "textures": {
        "plate": "blocks/terracotta/glazed/$color",
        "side": "blocks/terracotta/$color",
        "string": "items/bag/string/$color"
    }
}
EOF
    read -r -d '' concrete <<EOF
{
    "parent": "item_models/block/chiseled",
    "textures": {
        "top": "blocks/concrete/$color",
        "side": "blocks/concrete/$color",
        "bottom": "blocks/concrete/$color"
    }
}
EOF
    read -r -d '' powder <<EOF
{
    "parent": "item_models/block/sack",
    "textures": {
        "material": "blocks/concrete/powder/$color",
        "bag":      "items/bag/cloth/mono",
        "bag_rim":  "items/bag/cloth/rim/mono",
        "string":   "items/bag/string/$color"
    }
}
EOF
    read -r -d '' glass <<EOF
{
    "parent": "item_models/block/plates",
    "textures": {
        "plate": "blocks/glass/$color",
        "side": "blocks/glass/pane/$color",
        "string": "items/bag/string/$color"
    }
}
EOF

    echo $terracotta > "assets/minecraft/models/item/${color}_stained_hardened_clay.json"
    echo "Wrote assets/minecraft/models/item/${color}_stained_hardened_clay.json"

    echo $glazed > "assets/minecraft/models/item/${color}_glazed_terracotta.json"
    echo "Wrote assets/minecraft/models/item/${color}_glazed_terracotta.json"

    echo $wool > "assets/minecraft/models/item/${color}_wool.json"
    echo "Wrote assets/minecraft/models/item/${color}_wool.json"

    echo $concrete > "assets/minecraft/models/item/${color}_concrete.json"
    echo "Wrote assets/minecraft/models/item/${color}_concrete.json"

    echo $powder > "assets/minecraft/models/item/${color}_concrete_powder.json"
    echo "Wrote assets/minecraft/models/item/${color}_concrete_powder.json"

    echo $glass > "assets/minecraft/models/item/${color}_stained_glass.json"
    echo "Wrote assets/minecraft/models/item/${color}_stained_glass.json"
done