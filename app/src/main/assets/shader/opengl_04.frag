#version 300 es
precision mediump float;
in vec4 ourColor;
in vec2 ourTextureCoods;
uniform sampler2D uTexture1;
uniform sampler2D uTexture2;
uniform float progress;
out vec4 gl_FragColor;

vec4 getFromColor (vec2 uv) {
    return texture(uTexture1, uv);
}

vec4 getToColor (vec2 uv) {
    return texture(uTexture2, uv);
}

vec4 transition(vec2 p) {
    float amplitude = 30.0f;
    float speed = 30.0f;

    vec2 dir = p - vec2(.5);
    float dist = length(dir);

    if (dist > progress) {
        return mix(getFromColor(p), getToColor(p), progress);
    } else {
        vec2 offset = dir * cos(dist * amplitude - progress * speed);
        return mix(getFromColor(p + offset), getToColor(p), progress);
    }
}

void main() {
    gl_FragColor = transition(ourTextureCoods);
}