#!/bin/bash

FILES=`find src -name \*_.json -print`
FILES=$FILES' '`find src -name \*copy\*.json -print`

md cruft 2> /dev/null

for f in $FILES
do
  dir=`dirname $f`
  mkdir -p "cruft/$dir" 2> /dev/null 
  mv $f "cruft/$dir"
done
