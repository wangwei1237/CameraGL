layout (location=0) attribute vec3 vPosition;
layout (location=1) attribute vec4 vColor;
layout (location=2) attribute vec2 vTextureCoods;

varying vec4 ourColor;
varying vec2 ourTextureCoods;

void main() {
    gl_Position = vec4(vPosition, 1.0f);
    ourColor = vColor;
    ourTextureCoods = vTextureCoods;
}