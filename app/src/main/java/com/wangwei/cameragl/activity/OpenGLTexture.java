package com.wangwei.cameragl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wangwei.cameragl.R;
import com.wangwei.cameragl.view.TextureGLSurfaceView;

public class OpenGLTexture extends AppCompatActivity {

    private GLSurfaceView mGLSurfaceView;
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gltexture);

        mContainer = findViewById(R.id.opengl_texture_view);
        mGLSurfaceView = new TextureGLSurfaceView(this);
        mContainer.addView(mGLSurfaceView, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
    }
}
