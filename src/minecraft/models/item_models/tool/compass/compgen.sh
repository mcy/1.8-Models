#!/bin/bash

for i in {0..15}; do
    echo "{\"parent\":\"itemModels/misc/compass/compass\",\"textures\":{\"d${i}\":\"items/misc/compass/neddle\"}}" > "d${i}.json"
done