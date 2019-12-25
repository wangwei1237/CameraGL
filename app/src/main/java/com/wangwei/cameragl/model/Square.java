package com.wangwei.cameragl.model;

import android.content.Context;
import android.opengl.GLES20;
import com.wangwei.cameragl.R;
import com.wangwei.cameragl.utils.Shader;
import com.wangwei.cameragl.utils.TextureUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Square {
    private FloatBuffer mVertexBuffer;
    private ShortBuffer mVertexIndexBuffer;

    private static final int COORD_PER_VERTEX = 9;
    private static float triangleCoords[] = {
             0.8f,  0.8f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
            -0.8f,  0.8f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
            -0.8f, -0.8f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
             0.8f, -0.8f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
    };

    private static final short[] VERTEX_INDEX = {
            0, 1, 2,
            2, 3, 0,
    };

    private int mPositionHandle;
    private int mColorHandle;
    private int mTextureCoods;

    private final int vertexCount = triangleCoords.length / COORD_PER_VERTEX;
    private final int vertexStride = COORD_PER_VERTEX * 4;

    private Context mContext;
    private Shader  mShader;
    private float   i = 0.0f;
    private int texture1;
    private int texture2;
    private long beginTime;
    private int durationTime = 1000; // 控制转场前后每张图片显示的时间.

    public Square(Context context) {
        mVertexBuffer = ByteBuffer.allocateDirect(triangleCoords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(triangleCoords);
        mVertexBuffer.position(0);

        mVertexIndexBuffer = ByteBuffer.allocateDirect(VERTEX_INDEX.length * 2)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer()
                .put(VERTEX_INDEX);
        mVertexIndexBuffer.position(0);

        mContext = context;
        mShader = new Shader(mContext.getResources(),
                "shader/opengl_02.vert",
                "shader/opengl_02.frag");

        // 加载texture.
        texture1 = TextureUtils.loadTexture(mContext, R.drawable.texture_1);
        texture2 = TextureUtils.loadTexture(mContext, R.drawable.texture_2);
        beginTime = System.currentTimeMillis();
    }

    public void Draw() {
        mShader.use();
        mVertexBuffer.position(0);
        mPositionHandle = GLES20.glGetAttribLocation(mShader.getID(), "vPosition");
        GLES20.glVertexAttribPointer(mPositionHandle, 3,
                GLES20.GL_FLOAT, false,
                vertexStride, mVertexBuffer);
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        mVertexBuffer.position(3);
        mColorHandle = GLES20.glGetAttribLocation(mShader.getID(), "vColor");
        GLES20.glVertexAttribPointer(mColorHandle, 4,
                GLES20.GL_FLOAT, false,
                vertexStride, mVertexBuffer);
        GLES20.glEnableVertexAttribArray(mColorHandle);

        mVertexBuffer.position(7);
        mTextureCoods = GLES20.glGetAttribLocation(mShader.getID(), "vTextureCoods");
        GLES20.glVertexAttribPointer(mTextureCoods, 2,
                GLES20.GL_FLOAT, false,
                vertexStride, mVertexBuffer);
        GLES20.glEnableVertexAttribArray(mTextureCoods);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture1);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture2);

        mShader.setInt("uTexture1", 0);
        mShader.setInt("uTexture2", 1);

        long currentTime = System.currentTimeMillis();
        if (currentTime - beginTime > durationTime) {
            i += 0.008;
            if (i > 1.0f) {
                beginTime = currentTime;
                i = 0.0f;

            }

            if (currentTime - beginTime > durationTime) {
                mShader.setFloat("uMixEdge", i);
            }
        }

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, VERTEX_INDEX.length, GLES20.GL_UNSIGNED_SHORT, mVertexIndexBuffer);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mColorHandle);
        GLES20.glDisableVertexAttribArray(mTextureCoods);
    }
}
