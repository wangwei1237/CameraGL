package com.wangwei.cameragl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wangwei.cameragl.R;
import com.wangwei.cameragl.model.Square3;
import com.wangwei.cameragl.view.TextureGLSurfaceView;

public class OpenGLTexture03 extends AppCompatActivity {
    private TextureGLSurfaceView mGLSurfaceView;
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gltexture03);

        mContainer = findViewById(R.id.opengl_texture_view_03);

        mGLSurfaceView = new TextureGLSurfaceView(this, new Square3(this));
        mContainer.addView(mGLSurfaceView, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
    }
}
