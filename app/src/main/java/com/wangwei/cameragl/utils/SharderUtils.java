package com.wangwei.cameragl.utils;

import android.content.res.Resources;
import android.opengl.GLES20;

import com.wangwei.cameragl.activity.MainActivity;

import java.io.InputStream;

public class SharderUtils {
    public static int loadShader(int type, String sharderCode) {
        int sharder = GLES20.glCreateShader(type);
        GLES20.glShaderSource(sharder, sharderCode);
        GLES20.glCompileShader(sharder);

        return sharder;
    }

    //通过路径加载Assets中的文本内容
    public static String loadGLSLFromRes(Resources res, String path) {
        StringBuilder result = new StringBuilder();
        try {
            InputStream is = res.getAssets().open(path);
            int ch;
            byte[] buffer = new byte[1024];
            while (-1 != (ch = is.read(buffer))) {
                result.append(new String(buffer, 0, ch));
            }
        } catch (Exception e) {
            return null;
        }
        return result.toString().replaceAll("\\r\\n", "\n");
    }
}
