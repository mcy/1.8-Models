#!/usr/bin/env python
import json
import os
import re

for root, dirs, files in os.walk('src'):
    for name in files:
        path = os.path.join(root, name)
        if path.endswith('.json') or path.endswith('.mcdata'):
            jsonObj = open(path, 'r').read()
            # elide comments
            commentless = re.sub('/\\*.+?\\*/', '', jsonObj, flags = re.DOTALL)
            commentless = re.sub('//.+', '', commentless)

            open(path, 'w').write(commentless)
            
