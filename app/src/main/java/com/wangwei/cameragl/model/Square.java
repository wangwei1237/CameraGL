package com.wangwei.cameragl.model;

import android.content.Context;
import android.opengl.GLES30;
import com.wangwei.cameragl.R;
import com.wangwei.cameragl.utils.Shader;
import com.wangwei.cameragl.utils.TextureUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Square implements IDrawable {
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
    private boolean isReRender = false; // 是否是重新渲染，重新渲染时需要做特殊控制.保证两张图片静止指定时间.

    public Square(Context context) {
        mContext = context;
        beginTime = System.currentTimeMillis();
    }

    @Override
    public void preDraw() {
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
        mShader = new Shader(mContext.getResources(),
                "shader/opengl_02.vert",
                "shader/opengl_02.frag");

        // 加载texture.
        texture1 = TextureUtils.loadTexture(mContext, R.drawable.texture_1);
        texture2 = TextureUtils.loadTexture(mContext, R.drawable.texture_2);
    }

    @Override
    public void draw() {
        mShader.use();
        mVertexBuffer.position(0);
        mPositionHandle = GLES30.glGetAttribLocation(mShader.getID(), "vPosition");
        GLES30.glVertexAttribPointer(mPositionHandle, 3,
                GLES30.GL_FLOAT, false,
                vertexStride, mVertexBuffer);
        GLES30.glEnableVertexAttribArray(mPositionHandle);

        mVertexBuffer.position(3);
        mColorHandle = GLES30.glGetAttribLocation(mShader.getID(), "vColor");
        GLES30.glVertexAttribPointer(mColorHandle, 4,
                GLES30.GL_FLOAT, false,
                vertexStride, mVertexBuffer);
        GLES30.glEnableVertexAttribArray(mColorHandle);

        mVertexBuffer.position(7);
        mTextureCoods = GLES30.glGetAttribLocation(mShader.getID(), "vTextureCoods");
        GLES30.glVertexAttribPointer(mTextureCoods, 2,
                GLES30.GL_FLOAT, false,
                vertexStride, mVertexBuffer);
        GLES30.glEnableVertexAttribArray(mTextureCoods);

        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture1);

        GLES30.glActiveTexture(GLES30.GL_TEXTURE1);
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture2);

        mShader.setInt("uTexture1", 0);
        mShader.setInt("uTexture2", 1);

        long currentTime = System.currentTimeMillis();

        if (currentTime - beginTime > (isReRender ? 2 * durationTime : durationTime)) {
            i += 0.008;
            if (i > 1.1f) {
                beginTime = currentTime;
                i = 0.0f;
                isReRender = true;
            } else {
                isReRender = false;
            }
        }

        if (currentTime - beginTime > durationTime) {
            mShader.setFloat("uMixEdge", i);
        }

        GLES30.glDrawElements(GLES30.GL_TRIANGLES, VERTEX_INDEX.length, GLES30.GL_UNSIGNED_SHORT, mVertexIndexBuffer);
        GLES30.glDisableVertexAttribArray(mPositionHandle);
        GLES30.glDisableVertexAttribArray(mColorHandle);
        GLES30.glDisableVertexAttribArray(mTextureCoods);
    }
}
