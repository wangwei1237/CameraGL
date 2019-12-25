package com.wangwei.cameragl.model;

import android.content.Context;
import android.opengl.GLES20;

import com.wangwei.cameragl.utils.SharderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {
    private FloatBuffer mVertexBuffer;
    static final int COORD_PER_VERTEX = 7;
    static float triangleCoords[] = {
             0.0f,  0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
            -1.0f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
             1.0f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f
    };

    private int mProgram;
    private int mPositionHandle;
    private int mColorHandle;

    private final int vertexCount = triangleCoords.length / COORD_PER_VERTEX;
    private final int vertexStride = COORD_PER_VERTEX * 4;

    private Context mContext;

    public Triangle(Context context) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        mVertexBuffer = byteBuffer.asFloatBuffer();
        mVertexBuffer.put(triangleCoords);
        mVertexBuffer.position(0);
        mContext = context;

        int vertexSharder   = SharderUtils.loadShader(GLES20.GL_VERTEX_SHADER,
                SharderUtils.loadGLSLFromRes(mContext.getResources(), "shader/opengl_01_vertex.glsl"));
        int fragmentSharder = SharderUtils.loadShader(GLES20.GL_FRAGMENT_SHADER,
                SharderUtils.loadGLSLFromRes(mContext.getResources(), "shader/opengl_01_fragment.glsl"));

        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexSharder);
        GLES20.glAttachShader(mProgram, fragmentSharder);
        GLES20.glLinkProgram(mProgram);
    }

    public void Draw() {
        GLES20.glUseProgram(mProgram);

        mVertexBuffer.position(0);
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glVertexAttribPointer(mPositionHandle, 3,
                GLES20.GL_FLOAT, false,
                vertexStride, mVertexBuffer);
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        mVertexBuffer.position(3);
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "vColor");
        GLES20.glVertexAttribPointer(mColorHandle, 4,
                GLES20.GL_FLOAT, false,
                vertexStride, mVertexBuffer);
        GLES20.glEnableVertexAttribArray(mColorHandle);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mColorHandle);
    }
}
