package com.wangwei.cameragl.utils;

import android.content.res.Resources;
import android.opengl.GLES20;
import java.io.InputStream;

public class Shader {
    private int ID;

    /**
     *
     * @param res 资源
     * @param vertexPath vertex shader path.
     * @param fragmentPath fragment shader path.
     */
    public Shader(Resources res, String vertexPath, String fragmentPath) {
        String vertexSharderString   = loadGLSLFromRes(res, vertexPath);
        String fragmentSharderString = loadGLSLFromRes(res, fragmentPath);

        int    vertexSharder   = loadShader(GLES20.GL_VERTEX_SHADER, vertexSharderString);
        int    fragmentSharder = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSharderString);

        ID = GLES20.glCreateProgram();
        GLES20.glAttachShader(ID, vertexSharder);
        GLES20.glAttachShader(ID, fragmentSharder);
        GLES20.glLinkProgram(ID);

        GLES20.glDeleteShader(vertexSharder);
        GLES20.glDeleteShader(fragmentSharder);
    }

    public int getID() {
        return ID;
    }

    public void use() {
        GLES20.glUseProgram(ID);
    }

    public void setFloat(String name, float value) {
        GLES20.glUniform1f(GLES20.glGetUniformLocation(ID, name), value);
    }

    public void setInt(String name, int value) {
        GLES20.glUniform1i(GLES20.glGetUniformLocation(ID, name), value);
    }

    private int loadShader(int type, String sharderCode) {
        int sharder = GLES20.glCreateShader(type);
        GLES20.glShaderSource(sharder, sharderCode);
        GLES20.glCompileShader(sharder);

        return sharder;
    }

    //通过路径加载Assets中的文本内容
    private String loadGLSLFromRes(Resources res, String path) {
        StringBuilder result = new StringBuilder();
        try {
            InputStream is = res.getAssets().open(path);
            int ch;
            byte[] buffer = new byte[1024];
            while (-1 != (ch = is.read(buffer))) {
                result.append(new String(buffer, 0, ch));
            }
        } catch (Exception e) {
            return null;
        }
        return result.toString().replaceAll("\\r\\n", "\n");
    }
}
