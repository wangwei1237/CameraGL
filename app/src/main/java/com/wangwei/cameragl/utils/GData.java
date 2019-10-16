package com.wangwei.cameragl.utils;

public class GData {
    private static boolean isCamera = true;
    private static boolean isMediaCodec = false;

    public static boolean getIsCamera() {
        return isCamera;
    }

    public static void setIsCamera(boolean isCamera) {
        GData.isCamera     = isCamera;
        if (GData.isCamera) {
            GData.isMediaCodec = false;
        }
    }

    public static boolean getIsMediaCodec() {
        return isMediaCodec;
    }

    public static void setIsMediaCodec(boolean isMediaCodec) {
        GData.isMediaCodec = isMediaCodec;
    }
}
