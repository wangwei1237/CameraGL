layout (location=0) attribute vec3 vPosition;
layout (location=1) attribute vec4 vColor;
uniform float xStep;

varying vec4 ourColor;

void main() {
    gl_Position = vec4(vPosition.x + xStep, vPosition.y, vPosition.z, 1.0f);
    ourColor = vColor;
}