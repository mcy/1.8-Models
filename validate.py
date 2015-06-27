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
            hasComments = jsonObj != commentless

            try:
                json.loads(commentless)
                if hasComments:
                    print(path + " (Has comments)")
            except ValueError as e:
                if hasComments:
                    print(path + " -> " + e.args[0] + " (Has comments)")
                else: 
                    print(path + " -> " + e.args[0])
            
