package com.wangwei.cameragl.view;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import com.wangwei.cameragl.model.IDrawable;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class TextureGLSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer {
    private IDrawable mDrawable;
    private Context mContext;

    public TextureGLSurfaceView(Context context, IDrawable drawable) {
        super(context);
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(RENDERMODE_CONTINUOUSLY);
        mContext = context;
        mDrawable = drawable;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        mDrawable.preDraw();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        mDrawable.draw();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }
}
