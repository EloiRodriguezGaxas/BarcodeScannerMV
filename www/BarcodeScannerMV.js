var exec = require('cordova/exec');

function plugin() {};

plugin.prototype.coolMethod = function (params, success, error) {
    params = params || {};
    if(params.text_instructions === undefined) params.text_instructions = "Start or stop the flash with volume down key";
    if(params.draw_barcodes === undefined) params.draw_barcodes = "true";
    if(params.tap_barcode === undefined) params.tap_barcode = "false";
    exec(success, error, 'BarcodeScannerMV', 'coolMethod', [params]);
};

module.exports = new plugin();