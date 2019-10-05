package com.wangwei.cameragl.utils;

public class GData {
    private static boolean isCamera = true;

    public static boolean getIsCamera() {
        return isCamera;
    }

    public static void setIsCamera(boolean isCamera) {
        GData.isCamera = isCamera;
    }
}
