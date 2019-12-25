layout (location=0) attribute vec3 vPosition;
layout (location=1) attribute vec4 vColor;
varying vec4 ourColor;

void main() {
    gl_Position = vec4(vPosition, 1.0f);
    ourColor = vColor;
}