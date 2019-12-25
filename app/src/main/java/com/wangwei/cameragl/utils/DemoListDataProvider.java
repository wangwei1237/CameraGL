package com.wangwei.cameragl.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import com.wangwei.cameragl.activity.CameraPreviewActivity;
import com.wangwei.cameragl.activity.CameraVideoPreviewActivity;
import com.wangwei.cameragl.activity.OpenGLSample01Activity;
import com.wangwei.cameragl.activity.OpenGLTexture;
import com.wangwei.cameragl.activity.PreviewAndRecorderActivity;
import com.wangwei.cameragl.activity.VideoPreviewActivity;
import com.wangwei.cameragl.activity.VideoPreviewMediaCodecActivity;
import com.wangwei.cameragl.model.DemoItem;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.WIFI_SERVICE;

public class DemoListDataProvider {
    public static ArrayList<DemoItem> getDemoList() {
        ArrayList<DemoItem> arr = new ArrayList<>();

        arr.add(new DemoItem("OpenGL显示摄像头预览", "SurfaceTexture", CameraPreviewActivity.class));
        arr.add(new DemoItem("OpenGL播放视频", "MediaPlayer", VideoPreviewActivity.class));
        arr.add(new DemoItem("OpenGL切换摄像头和视频", "MediaPlayer", CameraVideoPreviewActivity.class));
        arr.add(new DemoItem("OpenGL预览并录制", "包含录制功能", PreviewAndRecorderActivity.class));
        arr.add(new DemoItem("预览视频", "mediacodec", VideoPreviewMediaCodecActivity.class));
        arr.add(new DemoItem("OpenGL01", "opengl demo", OpenGLSample01Activity.class));
        arr.add(new DemoItem("texture", "texture", OpenGLTexture.class));

        return arr;
    }

    public static String getWIFISSID(Activity activity) {
        String ssid="unknown id";

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O||Build.VERSION.SDK_INT==Build.VERSION_CODES.P) {

            WifiManager mWifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            assert mWifiManager != null;
            WifiInfo info = mWifiManager.getConnectionInfo();

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return info.getSSID();
            } else {
                return info.getSSID().replace("\"", "");
            }
        } else if (Build.VERSION.SDK_INT== Build.VERSION_CODES.O_MR1){

            ConnectivityManager connManager = (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connManager != null;
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo.isConnected()) {
                if (networkInfo.getExtraInfo()!=null){
                    return networkInfo.getExtraInfo().replace("\"","");
                }
            }
        }
        return ssid;
    }

    public static String getConnectedWifiMacAddress(Context context) {
        String connectedWifiMacAddress = null;
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> wifiList;

        if (wifiManager != null) {
            wifiList = wifiManager.getScanResults();
            WifiInfo info = wifiManager.getConnectionInfo();
            if (wifiList != null && info != null) {
                for (int i = 0; i < wifiList.size(); i++) {
                    ScanResult result = wifiList.get(i);
                    if (info.getBSSID().equals(result.BSSID)) {
                        connectedWifiMacAddress = result.BSSID;
                    }
                }
            }
        }
        return connectedWifiMacAddress;
    }

}
