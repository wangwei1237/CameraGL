#version 300 es
precision mediump float;
in vec4 ourColor;
in vec2 ourTextureCoods;
uniform sampler2D uTexture1;
uniform sampler2D uTexture2;
uniform float progress;
out vec4 gl_FragColor;

void main() {
    float squareSizeFactor  = 50.0;
    float imageWidthFactor  = 1.0 / 720.0;
    float imageHeightFactor = 1.0 / 1280.0;

    if (progress > 0.5f) {
        squareSizeFactor = squareSizeFactor * (1.0 - progress);
    } else {
        squareSizeFactor = squareSizeFactor * progress;
    }

    float dx = squareSizeFactor * imageWidthFactor;
    float dy = squareSizeFactor * imageHeightFactor;

    if (progress <= 0.01f) {
        gl_FragColor = texture(uTexture1, ourTextureCoods);
    } else if (progress < 0.5f){
        gl_FragColor = texture(uTexture1,
        vec2(dx * floor(ourTextureCoods.x / dx), dy * floor(ourTextureCoods.y / dy)));
    } else if (progress < 1.0f) {
        gl_FragColor = texture(uTexture2,
        vec2(dx * floor(ourTextureCoods.x / dx), dy * floor(ourTextureCoods.y / dy)));
    } else {
        gl_FragColor = texture(uTexture2, ourTextureCoods);
    }
}
