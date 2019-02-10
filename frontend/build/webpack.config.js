'use strict';

var webpack = require('webpack');

var config = {
    "mode": "development",
    "context": "/Users/zacharysmith/Workspace/isomorphic_kotlin_js/frontend/build/kotlin-js-min/main",
    "entry": {
        "frontend": "./frontend"
    },
    "output": {
        "path": "/Users/zacharysmith/Workspace/isomorphic_kotlin_js/frontend/build/bundle",
        "filename": "[name].bundle.js",
        "chunkFilename": "[id].bundle.js",
        "publicPath": "/frontend/"
    },
    "module": {
        "rules": [
            
        ]
    },
    "resolve": {
        "modules": [
            "kotlin-js-min/main",
            "js/resources",
            "/Users/zacharysmith/Workspace/isomorphic_kotlin_js/frontend/build/node_modules",
            "node_modules"
        ]
    },
    "plugins": [
        
    ]
};

module.exports = config;

