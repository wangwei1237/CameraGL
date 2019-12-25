precision mediump float;
varying vec4 ourColor;
varying vec2 ourTextureCoods;
uniform sampler2D uTexture1;
uniform sampler2D uTexture2;
uniform float uMixEdge;

void main() {
    gl_FragColor = mix(texture2D(uTexture1, ourTextureCoods),
                       texture2D(uTexture2, ourTextureCoods),
                       1.0 - step(uMixEdge, ourTextureCoods.x));
}
