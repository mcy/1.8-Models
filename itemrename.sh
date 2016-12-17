#!/bin/bash

FILES=`find src -name \*.json -print`

for f in $FILES
do
  copy="$f.copy"
  touch "$copy"
  sed "s/itemmodels/item_models/g" "$f" > "$copy"
  mv "$copy" "$f"
  echo "$f"
done