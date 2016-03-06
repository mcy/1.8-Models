#!/bin/bash

for d in {0..1}; do
for i in {0..15}; do 
for j in {0..15}; do
    DAY="items/misc/clock/day"
    if [[ $d == 1 ]]; then
        DAY="items/misc/clock/night"
    fi
    echo "{\"parent\":\"itemModels/misc/clock\",\"textures\":{\"h${i}\":\"items/misc/clock/hand\",\"m${j}\":\"items/misc/clock/hand\",\"day\":\"$DAY\"}}" > "d${d}h${i}m${j}.json"
done
done
done