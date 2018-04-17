package com.example.sample.barcodeScannerMV;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class BarcodeScannerMV extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Context context = cordova.getActivity().getApplicationContext();
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(context);
            return true;
        }
        return false;
    }

    private void coolMethod(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        this.cordova.getActivity().startActivity(intent);
    }
}
