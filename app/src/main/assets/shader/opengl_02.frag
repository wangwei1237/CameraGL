#version 300 es
precision mediump float;
in vec4 ourColor;
in vec2 ourTextureCoods;
uniform sampler2D uTexture1;
uniform sampler2D uTexture2;
uniform float uMixEdge;
out vec4 gl_FragColor;

void main() {
    gl_FragColor = mix(texture(uTexture1, ourTextureCoods),
                       texture(uTexture2, ourTextureCoods),
                       1.0 - step(uMixEdge, ourTextureCoods.x));
}
