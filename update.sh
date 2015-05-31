#!/bin/bash

LOCAL=$(git rev-parse @)
REMOTE=$(git rev-parse @{u})
BASE=$(git merge-base @ @{u})

if [ $LOCAL = $REMOTE ]; then
    :
elif [ $LOCAL = $BASE ]; then
    # out of date, need to update!
    touch ./update.lock
    git pull > /dev/null
    ./build
    rm ./update.lock
fi