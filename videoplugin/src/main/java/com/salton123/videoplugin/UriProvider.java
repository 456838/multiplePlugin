package com.salton123.videoplugin;

import android.net.Uri;
import android.os.Environment;

import com.salton123.base.ApplicationBase;
import com.salton123.util.log.MLog;

import java.io.File;

/**
 * Created by salton on 2017/12/3.
 */

public class UriProvider {

    private static final String TAG = "UriProvider";

    //资源放在/sdcard/leland/res
    public UriProvider() {

    }

    public static String defaultPath() {
        return Environment.getExternalStorageDirectory() + File.separator + "leland" + File.separator + "res";
//        return Environment.getExternalStorageDirectory() +"";
    }

    public static String getVideoPath(String resNameWithSuffix) {
        return defaultPath() + File.separator + resNameWithSuffix;
    }

    private static Uri getVideoPathUriFromLocal(String resNameWithSuffix) {
        return Uri.parse(getVideoPath(resNameWithSuffix));
    }

    private static Uri getVideoPathUriFromRaw(String resNameWithSuffix) {
        return Uri.parse(getVideoPathWithoutSuffix(resNameWithSuffix));
    }

    public static String getVideoPathWithoutSuffix(String resNameWithSuffix) {
        String packageName = ApplicationBase.getInstance().getPackageName();
        String resName = resNameWithSuffix.substring(0, resNameWithSuffix.lastIndexOf("."));
        int resId = ApplicationBase.getInstance().getResources().getIdentifier(resName, "raw", packageName);
        MLog.info(TAG, "[getVideoPathWithoutSuffix] resName=" + resName + ",id=" + resId);
//        return "file:///android_asset" + "/res/" + resNameWithSuffix;//*R.raw.handle_02*//*;
        return "android.resource://" + packageName + "/" + resId;//*R.raw.handle_02*//*;
    }

    public static Uri getVideoPathUri(String resNameWithSuffix) {
        return getVideoPathUriFromRaw(resNameWithSuffix);
    }
}
