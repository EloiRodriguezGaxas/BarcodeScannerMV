var exec = require('cordova/exec');

function plugin() {};

plugin.prototype.coolMethod = function (success, error) {
    params = {};
    exec(success, error, 'BarcodeScannerMV', 'coolMethod', []);
};

module.exports = new plugin();