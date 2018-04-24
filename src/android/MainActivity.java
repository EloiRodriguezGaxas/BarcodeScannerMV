package com.example.sample.barcodeScannerMV;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import android.content.res.Resources;

import android.app.Activity;
import android.os.Bundle;

import android.Manifest;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private String package_name;
    private Resources resources;

    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     setContentView(getResourceId("layout/activity_main"));

    //     // findViewById(getResourceId("id/read_barcode")).setOnClickListener(this);
    //     super.onCreate(savedInstanceState);
    // }

    private int getResourceId(String typeAndName) {
        if (package_name == null)
            package_name = getApplication().getPackageName();
        if (resources == null)
            resources = getApplication().getResources();
        return resources.getIdentifier(typeAndName, null, package_name);
    }

    // @Override
    // public void onClick(View v) {

    //     if (v.getId() == getResourceId("id/read_barcode")) {
    //         Intent result = new Intent ();
    //         result.putExtra("Value", "12346677");
    //         setResult(Activity.RESULT_OK, result);
    //         finish();
    //     }

    // }

    // private CordovaInterface cordova;

    // use a compound button so either checkbox or switch widgets work.
    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private TextView statusMessage;
    private TextView barcodeValue;

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getApplication().getResources().getIdentifier("activity_main", "layout",
                getApplication().getPackageName()));

        statusMessage = (TextView) findViewById(getApplication().getResources().getIdentifier("status_message", "id",
                getApplication().getPackageName()));
        barcodeValue = (TextView) findViewById(getApplication().getResources().getIdentifier("barcode_value", "id",
                getApplication().getPackageName()));

        autoFocus = (CompoundButton) findViewById(getApplication().getResources().getIdentifier("auto_focus", "id",
                getApplication().getPackageName()));
        useFlash = (CompoundButton) findViewById(getApplication().getResources().getIdentifier("use_flash", "id",
                getApplication().getPackageName()));

        findViewById(getApplication().getResources().getIdentifier("read_barcode", "id",
                getApplication().getPackageName())).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == getApplication().getResources().getIdentifier("read_barcode", "id",
                getApplication().getPackageName())) {
            // launch barcode activity.
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
            intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());

            startActivityForResult(intent, RC_BARCODE_CAPTURE);
        }

    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    String barcode = data.getParcelableExtra("Barcode");
                    statusMessage.setText(getApplication().getResources().getIdentifier("barcode_success", "string",
                            getApplication().getPackageName()));
                    barcodeValue.setText(barcode);
                    Log.d(TAG, "Barcode read: " + barcode);
                } else {
                    statusMessage.setText(getApplication().getResources().getIdentifier("barcode_failure", "string",
                            getApplication().getPackageName()));
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                statusMessage.setText(String.format(
                        getString(getApplication().getResources().getIdentifier("barcode_error", "string",
                                getApplication().getPackageName())),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}