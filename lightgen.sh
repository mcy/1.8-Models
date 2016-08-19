for i in 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 
do 
    echo "{
    \"parent\": \"block/redstone/day_sensor/base\",
    \"textures\": {
        \"lights\": \"blocks/redstone/day_sensor/lights$i\"
    }
}" > light$i.json
done