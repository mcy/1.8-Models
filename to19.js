#!/usr/bin/env node

var fs = require('fs');

String.prototype.endsWith = function(suffix) {
    return this.indexOf(suffix, this.length - suffix.length) !== -1;
};

var root = process.argv[2] || 'src'
processFile(root)

function processFile(path) {
    var stats = fs.statSync(path);
    if(stats.isDirectory()) {
        for(f in fs.readDirSync(path)) {
            transformFile(f);
        }
    } else if (path.match(/^.+\.json$/)) {
        processModel(path);
    }
}

function processModel(model) {
    var data = fs.readFileSync(model, 'UTF-8');
    var display = JSON.parse(data).display;
    if(typeof display !== 'object') return;

    transformGui(display);
    transformFixed(display);

    display = JSON.stringify(display);
    data = data.replace('\"display\"', '\"display\": ' + display + ', \"disabled\"');
    fs.writeFileSync(model, data);
}

function transformGui(display) {
    var gui = display.gui || {};

    var rotation = gui.rotation || [0, 0, 0];
    rotation[1] += 225;
    rotation[0] += 30;
    gui.rotation = rotation;

    var scale = (gui.scale || [1, 1, 1]).map(function(x) { return x * 0.625 });
    gui.scale = scale;

    var translation = (gui.translation || [0, 0, 0]).map(function(x) { return x * -1 });
    gui.translation = translation
}

function transformFixed(display) {
    var fixed = display.fixed || {};

    var scale = (fixed.scale || [1, 1, 1]).map(function(x) { return x * 0.5 });
    fixed.scale = scale;

}