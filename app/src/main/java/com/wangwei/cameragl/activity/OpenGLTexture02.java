package com.wangwei.cameragl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wangwei.cameragl.R;
import com.wangwei.cameragl.model.Square;
import com.wangwei.cameragl.model.Square2;
import com.wangwei.cameragl.view.TextureGLSurfaceView;

public class OpenGLTexture02 extends AppCompatActivity {

    private TextureGLSurfaceView mGLSurfaceView;
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gltexture02);

        mContainer = findViewById(R.id.opengl_texture_view_02);

        mGLSurfaceView = new TextureGLSurfaceView(this, new Square2(this));
        mContainer.addView(mGLSurfaceView, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
    }
}
