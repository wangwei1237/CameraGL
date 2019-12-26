package com.wangwei.cameragl.utils;

import android.content.res.Resources;
import android.opengl.GLES30;
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

        int    vertexSharder   = loadShader(GLES30.GL_VERTEX_SHADER, vertexSharderString);
        int    fragmentSharder = loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentSharderString);

        ID = GLES30.glCreateProgram();
        GLES30.glAttachShader(ID, vertexSharder);
        GLES30.glAttachShader(ID, fragmentSharder);
        GLES30.glLinkProgram(ID);

        GLES30.glDeleteShader(vertexSharder);
        GLES30.glDeleteShader(fragmentSharder);
    }

    public int getID() {
        return ID;
    }

    public void use() {
        GLES30.glUseProgram(ID);
    }

    public void setFloat(String name, float value) {
        GLES30.glUniform1f(GLES30.glGetUniformLocation(ID, name), value);
    }

    public void setInt(String name, int value) {
        GLES30.glUniform1i(GLES30.glGetUniformLocation(ID, name), value);
    }

    private int loadShader(int type, String sharderCode) {
        int sharder = GLES30.glCreateShader(type);
        GLES30.glShaderSource(sharder, sharderCode);
        GLES30.glCompileShader(sharder);

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
