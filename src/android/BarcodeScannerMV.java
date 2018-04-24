package com.example.sample.barcodeScannerMV;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.common.api.CommonStatusCodes;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class BarcodeScannerMV extends CordovaPlugin {

    private static int SCAN_CODE = 9001;

    private static final String TAG = "BarcodeScannerMV";

    private CallbackContext scanCallbackContext;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Context context = cordova.getActivity().getApplicationContext();
        this.scanCallbackContext = callbackContext;
        if (action.equals("coolMethod")) {
            Intent intent = new Intent(context, BarcodeCaptureActivity.class);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
            intent.putExtra(BarcodeCaptureActivity.UseFlash, false);
            cordova.startActivityForResult(this, intent, SCAN_CODE);
            return true;
        }
        return false;
    }

    private void coolMethod(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        cordova.startActivityForResult(this, intent, SCAN_CODE);
        // scanCallbackContext.success("barcodeValue");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SCAN_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    String barcode = data.getStringExtra("Barcode");
                    Log.d(TAG, "Barcode read: " + barcode);
                    scanCallbackContext.success(barcode);
                } else {
                    Log.d(TAG, "No barcode captured, intent data is null");
                    scanCallbackContext.error("No barcode captured");
                }
            } else {
                scanCallbackContext.error("Unknown error");
            }
        }

    }

}
