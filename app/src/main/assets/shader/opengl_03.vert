#version 300 es
layout (location=0) in vec3 vPosition;
layout (location=1) in vec4 vColor;
layout (location=2) in vec2 vTextureCoods;

out vec4 ourColor;
out vec2 ourTextureCoods;

void main() {
    gl_Position = vec4(vPosition, 1.0f);
    ourColor = vColor;
    ourTextureCoods = vTextureCoods;
}