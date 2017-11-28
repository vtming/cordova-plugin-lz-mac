/*
 *wyf
 *
*/

var exec = require('cordova/exec');

var MAC = {
    getWIFIMac:function(callback) {
        exec(callback, null, "MAC", "WIFI", []);
    },
    getBLEMac:function(callback) {
        exec(callback, null, "MAC", "BLE", []);
    }
};

module.exports = MAC;
