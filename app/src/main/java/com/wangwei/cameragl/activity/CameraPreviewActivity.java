package com.wangwei.cameragl.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.wangwei.cameragl.R;
import com.wangwei.cameragl.utils.GData;
import com.wangwei.cameragl.view.CameraView;

public class CameraPreviewActivity extends AppCompatActivity {
    private FrameLayout mContainer;
    public  CameraView  mCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去除状态栏和标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_camerapreview);
        mContainer = findViewById(R.id.container);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCameraView != null) {
            mCameraView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCameraView != null) {
            mCameraView.onResume();
        } else {
            startCamera();
        }
    }

    private void startCamera() {
        GData.setIsCamera(true);

        if (mCameraView == null) {
            mCameraView = new CameraView(this);
            mContainer.addView(mCameraView,
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

}
