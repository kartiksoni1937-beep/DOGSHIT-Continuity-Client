/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.renderer.sdf;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.minecraft.class_10789;
import net.minecraft.class_10799;
import net.minecraft.class_12137;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;
import org.joml.Vector4fc;
import org.lwjgl.system.MemoryUtil;

public class KawasePipeline {
    private static final int BLUR_ITERATIONS = 5;
    private static final int DOWNSAMPLE_SCALE = 2;
    private static final int BUFFER_SIZE = 256;
    private static final RenderPipeline PIPELINE_BLUR = class_10799.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{class_10799.field_60125}).withLocation(class_2960.method_60655((String)"immediatelyfast", (String)"pipeline/blur_pass")).withVertexShader(class_2960.method_60655((String)"immediatelyfast", (String)"blur_pass_vertex")).withFragmentShader(class_2960.method_60655((String)"immediatelyfast", (String)"blur_pass_fragment")).withVertexFormat(class_290.field_60033, VertexFormat.class_5596.field_27379).withUniform("BlurData", class_10789.field_60031).withSampler("Sampler0").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
    private static final RenderPipeline PIPELINE_FINAL = class_10799.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{class_10799.field_60125}).withLocation(class_2960.method_60655((String)"immediatelyfast", (String)"pipeline/blur_final")).withVertexShader(class_2960.method_60655((String)"immediatelyfast", (String)"blur_final_vertex")).withFragmentShader(class_2960.method_60655((String)"immediatelyfast", (String)"blur_final_fragment")).withVertexFormat(class_290.field_60033, VertexFormat.class_5596.field_27379).withUniform("BlurData", class_10789.field_60031).withSampler("Sampler0").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
    private static final Vector4f COLOR_MODULATOR = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
    private static final Vector3f MODEL_OFFSET = new Vector3f(0.0f, 0.0f, 0.0f);
    private static final Matrix4f TEXTURE_MATRIX = new Matrix4f();
    private static GpuBuffer uniformBuffer;
    private static GpuBuffer dummyVertexBuffer;
    private static ByteBuffer dataBuffer;
    private static GpuTexture copyTexture;
    private static GpuTextureView copyTextureView;
    private static GpuTexture[] pingPongTextures;
    private static GpuTextureView[] pingPongViews;
    private static int lastWidth;
    private static int lastHeight;
    private static boolean initialized;
    private static long lastFrameTime;
    private static int cachedBlurSrc;
    private static float cachedStrength;
    private static long frameCounter;
    private static long lastRenderedFrame;

    public static void beginFrame() {
        ++frameCounter;
    }

    public static void init() {
        if (initialized) {
            return;
        }
        dataBuffer = MemoryUtil.memAlloc((int)256);
        ByteBuffer dummyData = MemoryUtil.memAlloc((int)4);
        dummyData.putInt(0);
        dummyData.flip();
        dummyVertexBuffer = RenderSystem.getDevice().createBuffer(() -> "immediatelyfast:blur_dummy_vertex", 32, dummyData);
        MemoryUtil.memFree((Buffer)dummyData);
        initialized = true;
    }

    private static void ensureTextures(int fbWidth, int fbHeight) {
        int blurWidth = fbWidth / 2;
        int blurHeight = fbHeight / 2;
        if (copyTexture != null && fbWidth == lastWidth && fbHeight == lastHeight) {
            return;
        }
        if (copyTextureView != null) {
            copyTextureView.close();
            copyTextureView = null;
        }
        if (copyTexture != null) {
            copyTexture.close();
            copyTexture = null;
        }
        copyTexture = RenderSystem.getDevice().createTexture(() -> "immediatelyfast:blur_copy", 5, TextureFormat.RGBA8, fbWidth, fbHeight, 1, 1);
        copyTextureView = RenderSystem.getDevice().createTextureView(copyTexture);
        for (int i = 0; i < 2; ++i) {
            if (pingPongViews[i] != null) {
                pingPongViews[i].close();
                KawasePipeline.pingPongViews[i] = null;
            }
            if (pingPongTextures[i] != null) {
                pingPongTextures[i].close();
                KawasePipeline.pingPongTextures[i] = null;
            }
            int idx = i;
            KawasePipeline.pingPongTextures[i] = RenderSystem.getDevice().createTexture(() -> "immediatelyfast:blur_pp_" + idx, 13, TextureFormat.RGBA8, blurWidth, blurHeight, 1, 1);
            KawasePipeline.pingPongViews[i] = RenderSystem.getDevice().createTextureView(pingPongTextures[i]);
        }
        lastWidth = fbWidth;
        lastHeight = fbHeight;
        lastFrameTime = -1L;
    }

    public static void prepareBlur(float strength) {
        class_310 client = class_310.method_1551();
        if (client.method_1522() == null || client.method_1522().method_30277() == null) {
            return;
        }
        KawasePipeline.init();
        int fbWidth = client.method_1522().field_1482;
        int fbHeight = client.method_1522().field_1481;
        int blurWidth = fbWidth / 2;
        int blurHeight = fbHeight / 2;
        KawasePipeline.ensureTextures(fbWidth, fbHeight);
        if (frameCounter == lastRenderedFrame && strength == cachedStrength) {
            return;
        }
        class_12137 sampler = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().method_71106((Matrix4fc)RenderSystem.getModelViewMatrix(), (Vector4fc)COLOR_MODULATOR, (Vector3fc)MODEL_OFFSET, (Matrix4fc)TEXTURE_MATRIX);
        CommandEncoder encoder = RenderSystem.getDevice().createCommandEncoder();
        encoder.copyTextureToTexture(client.method_1522().method_30277(), copyTexture, 0, 0, 0, 0, 0, fbWidth, fbHeight);
        KawasePipeline.prepareBlurData(fbWidth, fbHeight, blurWidth, blurHeight, 1.0f, strength);
        encoder.writeToBuffer(uniformBuffer.slice(), dataBuffer);
        try (RenderPass downsample = encoder.createRenderPass(() -> "immediatelyfast:blur_downsample", pingPongViews[0], OptionalInt.empty(), null, OptionalDouble.empty());){
            downsample.setPipeline(PIPELINE_BLUR);
            downsample.setVertexBuffer(0, dummyVertexBuffer);
            downsample.bindTexture("Sampler0", copyTextureView, sampler);
            RenderSystem.bindDefaultUniforms((RenderPass)downsample);
            downsample.setUniform("DynamicTransforms", dynamicTransforms);
            downsample.setUniform("BlurData", uniformBuffer);
            downsample.draw(0, 6);
        }
        int iterations = Math.max(2, (int)(5.0f * strength));
        float[] offsets = new float[]{1.0f, 2.0f, 2.0f, 3.0f};
        int i = 0;
        while (i < iterations) {
            int src = i % 2;
            int dst = (i + 1) % 2;
            float offset = i < offsets.length ? offsets[i] : 3.0f;
            int passIdx = i++;
            KawasePipeline.prepareBlurData(blurWidth, blurHeight, blurWidth, blurHeight, offset, 1.0f);
            encoder.writeToBuffer(uniformBuffer.slice(), dataBuffer);
            try (RenderPass pass = encoder.createRenderPass(() -> "immediatelyfast:blur_" + passIdx, pingPongViews[dst], OptionalInt.empty(), null, OptionalDouble.empty());){
                pass.setPipeline(PIPELINE_BLUR);
                pass.setVertexBuffer(0, dummyVertexBuffer);
                pass.bindTexture("Sampler0", pingPongViews[src], sampler);
                RenderSystem.bindDefaultUniforms((RenderPass)pass);
                pass.setUniform("DynamicTransforms", dynamicTransforms);
                pass.setUniform("BlurData", uniformBuffer);
                pass.draw(0, 6);
            }
        }
        cachedBlurSrc = iterations % 2;
        lastRenderedFrame = frameCounter;
        cachedStrength = strength;
    }

    public static void draw(Matrix4f matrix, float x, float y, float width, float height, float radius, float strength, boolean liquid, float z) {
        class_310 client = class_310.method_1551();
        if (client.method_1522() == null) {
            return;
        }
        KawasePipeline.prepareBlur(strength);
        int blurWidth = client.method_1522().field_1482 / 2;
        int blurHeight = client.method_1522().field_1481 / 2;
        class_12137 sampler = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().method_71106((Matrix4fc)RenderSystem.getModelViewMatrix(), (Vector4fc)COLOR_MODULATOR, (Vector3fc)MODEL_OFFSET, (Matrix4fc)TEXTURE_MATRIX);
        CommandEncoder encoder = RenderSystem.getDevice().createCommandEncoder();
        float[] radii = new float[]{radius, radius, radius, radius};
        int scaledW = client.method_22683().method_4486();
        int scaledH = client.method_22683().method_4502();
        KawasePipeline.prepareFinalData(matrix, x, y, width, height, scaledW, scaledH, radii, liquid, z);
        encoder.writeToBuffer(uniformBuffer.slice(), dataBuffer);
        try (RenderPass finalPass = encoder.createRenderPass(() -> "immediatelyfast:blur_final", client.method_1522().method_71639(), OptionalInt.empty(), client.method_1522().method_71640(), OptionalDouble.of(1.0));){
            finalPass.setPipeline(PIPELINE_FINAL);
            finalPass.setVertexBuffer(0, dummyVertexBuffer);
            finalPass.bindTexture("Sampler0", pingPongViews[cachedBlurSrc], sampler);
            RenderSystem.bindDefaultUniforms((RenderPass)finalPass);
            finalPass.setUniform("DynamicTransforms", dynamicTransforms);
            finalPass.setUniform("BlurData", uniformBuffer);
            finalPass.draw(0, 6);
        }
    }

    private static void prepareBlurData(int screenW, int screenH, int texW, int texH, float offset, float strength) {
        dataBuffer.clear();
        for (int i = 0; i < 16; ++i) {
            dataBuffer.putFloat(0.0f);
        }
        dataBuffer.putFloat(0.0f).putFloat(0.0f).putFloat(screenW).putFloat(screenH);
        dataBuffer.putFloat(screenW).putFloat(screenH).putFloat(1.0f).putFloat(offset);
        dataBuffer.putFloat(texW).putFloat(texH).putFloat(1.0f).putFloat(strength);
        dataBuffer.putFloat(0.0f).putFloat(0.0f).putFloat(0.0f).putFloat(0.0f);
        dataBuffer.putFloat(0.0f).putFloat(0.0f).putFloat(0.0f).putFloat(0.0f);
        dataBuffer.flip();
        KawasePipeline.ensureBuffer();
    }

    private static void prepareFinalData(Matrix4f matrix, float x, float y, float w, float h, int fbW, int fbH, float[] r, boolean liquid, float z) {
        dataBuffer.clear();
        dataBuffer.putFloat(matrix.m00()).putFloat(matrix.m01()).putFloat(matrix.m02()).putFloat(matrix.m03());
        dataBuffer.putFloat(matrix.m10()).putFloat(matrix.m11()).putFloat(matrix.m12()).putFloat(matrix.m13());
        dataBuffer.putFloat(matrix.m20()).putFloat(matrix.m21()).putFloat(matrix.m22()).putFloat(matrix.m23());
        dataBuffer.putFloat(matrix.m30()).putFloat(matrix.m31()).putFloat(matrix.m32()).putFloat(matrix.m33());
        dataBuffer.putFloat(x).putFloat(y).putFloat(w).putFloat(h);
        dataBuffer.putFloat(fbW).putFloat(fbH).putFloat(0.0f).putFloat(0.0f);
        dataBuffer.putFloat(fbW).putFloat(fbH).putFloat(1.0f).putFloat(0.0f);
        dataBuffer.putFloat(r[0]).putFloat(r[1]).putFloat(r[2]).putFloat(r[3]);
        dataBuffer.putFloat(z).putFloat(liquid ? 1.0f : 0.0f).putFloat(0.0f).putFloat(0.0f);
        dataBuffer.flip();
        KawasePipeline.ensureBuffer();
    }

    private static void ensureBuffer() {
        int size = dataBuffer.remaining();
        if (uniformBuffer == null || uniformBuffer.size() < (long)size) {
            if (uniformBuffer != null) {
                uniformBuffer.close();
            }
            uniformBuffer = RenderSystem.getDevice().createBuffer(() -> "immediatelyfast:blur_uniform", 136, (long)size);
        }
    }

    public static GpuTextureView getBlurTextureView() {
        if (!initialized || pingPongViews[cachedBlurSrc] == null) {
            return null;
        }
        return pingPongViews[cachedBlurSrc];
    }

    public static void shutdown() {
        if (uniformBuffer != null) {
            uniformBuffer.close();
            uniformBuffer = null;
        }
        if (dummyVertexBuffer != null) {
            dummyVertexBuffer.close();
            dummyVertexBuffer = null;
        }
        if (dataBuffer != null) {
            MemoryUtil.memFree((Buffer)dataBuffer);
            dataBuffer = null;
        }
        if (copyTextureView != null) {
            copyTextureView.close();
            copyTextureView = null;
        }
        if (copyTexture != null) {
            copyTexture.close();
            copyTexture = null;
        }
        for (int i = 0; i < 2; ++i) {
            if (pingPongViews[i] != null) {
                pingPongViews[i].close();
                KawasePipeline.pingPongViews[i] = null;
            }
            if (pingPongTextures[i] == null) continue;
            pingPongTextures[i].close();
            KawasePipeline.pingPongTextures[i] = null;
        }
        lastWidth = 0;
        lastHeight = 0;
        initialized = false;
        lastFrameTime = -1L;
    }

    static {
        pingPongTextures = new GpuTexture[2];
        pingPongViews = new GpuTextureView[2];
        lastWidth = 0;
        lastHeight = 0;
        initialized = false;
        lastFrameTime = -1L;
        cachedBlurSrc = 0;
        cachedStrength = 0.0f;
        frameCounter = 0L;
        lastRenderedFrame = -1L;
    }
}

