package com.wangwei.cameragl.model;

public class DemoItem {
    private String     name;
    private String     sign;
    private Class<?>   cls;

    public DemoItem(String name, String sign, Class<?> cls) {
        this.name  = name;
        this.sign  = sign;
        this.cls   = cls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }
}
