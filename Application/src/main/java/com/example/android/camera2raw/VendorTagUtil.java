package com.example.android.camera2raw;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.util.Log;
import android.hardware.camera2.CameraMetadata;
public class VendorTagUtil {

    private static final String TAG = "VendorTagUtil";

    private static CaptureRequest.Key<Integer> TOFParamskey = new CaptureRequest.Key<>("org.codeaurora.qcamera3.tofsensormeta.wb", Integer.class);

    private static boolean isSupported(CaptureRequest.Builder builder,
                                       CaptureRequest.Key<?> key) {
        boolean supported = true;
        try {
            builder.get(key);
        }catch(IllegalArgumentException exception){
            supported = false;
            Log.d(TAG, "vendor tag " + key.getName() + " is not supported");
            exception.printStackTrace();
        }
        if ( supported ) {
            Log.d(TAG, "vendor tag " + key.getName() + " is supported");
        }
        return supported;
    }

    public static void setTOFMode(CaptureRequest.Builder builder, Integer value) {
        if ( isTOFModeSupported(builder) ) {
            builder.set(TOFParamskey, value);
        }
    }

    private static boolean isTOFModeSupported(CaptureRequest.Builder builder) {
        return isSupported(builder, TOFParamskey);
    }
}
