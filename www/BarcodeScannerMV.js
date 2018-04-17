var exec = require('cordova/exec');

function plugin() {};

plugin.prototype.coolMethod = function (success, error) {
    exec(success, error, 'BarcodeScannerMV', 'coolMethod', []);
};

module.exports = new plugin();