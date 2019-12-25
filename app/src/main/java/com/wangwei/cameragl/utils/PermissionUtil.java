package com.wangwei.cameragl.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {
    private final static String TAG = "PermissionUtil";

    public static String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public static final int PERMISSIONS_REQUEST_CODE = 10000;

    public static void permissionRequest(Activity c) {
        List<String> needReqPermissionList = new ArrayList<>();
        needReqPermissionList.clear();

        for (String p : permissions) {
            if (ContextCompat.checkSelfPermission(c, p)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "has the permission: " + p + ".");
            }else {
                needReqPermissionList.add(p);
                Log.d(TAG, "do not has the permission: " + p + ".");
            }
        }

        if (needReqPermissionList.size() > 0) {
            ActivityCompat.requestPermissions(c, permissions, PERMISSIONS_REQUEST_CODE);
        }
    }
}

