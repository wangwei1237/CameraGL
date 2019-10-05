package com.wangwei.cameragl.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class FileUtils {
    public static void scanFile(Context context, String filePath) {
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(Uri.fromFile(new File(filePath)));
        context.sendBroadcast(scanIntent);
    }
}
