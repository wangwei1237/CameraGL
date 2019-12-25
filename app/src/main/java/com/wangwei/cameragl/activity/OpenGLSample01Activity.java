package com.wangwei.cameragl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wangwei.cameragl.R;
import com.wangwei.cameragl.view.MyGLSurfaceView;

public class OpenGLSample01Activity extends AppCompatActivity {

    private GLSurfaceView mGLSurfaceView;
    private FrameLayout   mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_glsample01);
        mContainer = findViewById(R.id.myopengl_view);
        mGLSurfaceView = new MyGLSurfaceView(this);
        mContainer.addView(mGLSurfaceView, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
    }
}
