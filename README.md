# BarcodeScannerMV
Cordova plugin for Android APP to Scan Barcodes. Uses the mobile vision technology from Google to be accurate and fast. 

You can set to tap the barcode you want or get the closest one to the square paint it in the screen. 

A toast is shown at the begining so you can explain wahtever you need. 

The flash can be turned on and turned off with the volumen button. 

## Installation
```
cordova plugin add https://github.com/EloiRodriguezGaxas/BarcodeScannerMV
```

## Usage
Just call the method with the params you want.
```javascript
var params = {
    "text_instructions": popup_translations.cameraFlash,
    "draw_barcodes": true,
    "tap_barcode": false
}

var respCallback = function (result) {
    console.log("The barcode is: ", result);
}

var errCallback = function (error) {
    console.log("An error occured: ", error);
}

BarcodeScannerMV.coolMethod(
    params, respCallback, errCallback
);
```
## Params
Param Option | Functionality
------------ | -------------
text_instructions | Text to be shown in a Toast for two seconds at the beginin
draw_barcodes | true/false --  Draw sqaures indicating the barcodes in the screen. If set to false you cannot tap it.
tap_barcode | true/false -- If set to true the user must tap the barcode to obtain a result, else it will detect the barcodes and return the closest one to the scaning area (printed in the screen)
