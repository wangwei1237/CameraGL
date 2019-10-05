package com.wangwei.cameragl.utils;

import com.wangwei.cameragl.activity.CameraPreviewActivity;
import com.wangwei.cameragl.activity.CameraVideoPreviewActivity;
import com.wangwei.cameragl.activity.PreviewAndRecorderActivity;
import com.wangwei.cameragl.activity.VideoPreviewActivity;
import com.wangwei.cameragl.model.DemoItem;

import java.util.ArrayList;

public class DemoListDataProvider {
    public static ArrayList<DemoItem> getDemoList() {
        ArrayList<DemoItem> arr = new ArrayList<>();

        arr.add(new DemoItem("OpenGL显示摄像头预览", "SurfaceTexture", CameraPreviewActivity.class));
        arr.add(new DemoItem("OpenGL播放视频", "MediaPlayer", VideoPreviewActivity.class));
        arr.add(new DemoItem("OpenGL切换摄像头和视频", "MediaPlayer", CameraVideoPreviewActivity.class));
        arr.add(new DemoItem("OpenGL预览并录制", "包含录制功能", PreviewAndRecorderActivity.class));

        return arr;
    }


}
