for i in 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 
do 
    echo "{
    \"parent\": \"block/day_sensor/base\",
    \"textures\": {
        \"lights\": \"blocks/day_sensor/darks$i\"
    }
}" > dark$i.json
done