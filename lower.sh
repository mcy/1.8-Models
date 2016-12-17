#!/bin/bash

FILES=`find src -name \*.json -print`

for f in $FILES
do
  copy="$f.copy"
  touch "$copy"
  tr '[:upper:]' '[:lower:]' < "$f" > "$copy"
  mv "$copy" "$f"
  echo "$f"
done