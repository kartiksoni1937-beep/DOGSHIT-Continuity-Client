/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.renderer.sdf;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import me.pepperbell.continuity.renderer.sdf.KawasePipeline;
import net.minecraft.class_10789;
import net.minecraft.class_10799;
import net.minecraft.class_12137;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryUtil;

public class LiquidGlassPipeline {
    private static final int UNIFORM_SIZE = 256;
    private static final RenderPipeline PIPELINE = class_10799.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{class_10799.field_60125}).withLocation(class_2960.method_60655((String)"rockstar", (String)"pipeline/liquidglass")).withVertexShader(class_2960.method_60655((String)"rockstar", (String)"core/liquidglass/vertex")).withFragmentShader(class_2960.method_60655((String)"rockstar", (String)"core/liquidglass/fragment")).withVertexFormat(class_290.field_1575, VertexFormat.class_5596.field_27379).withSampler("Sampler0").withUniform("Uniforms", class_10789.field_60031).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
    private static GpuBuffer uniformBuffer;
    private static GpuBuffer dummyVertexBuffer;
    private static boolean initialized;

    public static void init() {
        if (initialized) {
            return;
        }
        uniformBuffer = RenderSystem.getDevice().createBuffer(() -> "LiquidGlass Uniforms", 136, 256L);
        ByteBuffer dummyData = MemoryUtil.memAlloc((int)4);
        dummyData.putInt(0);
        dummyData.flip();
        dummyVertexBuffer = RenderSystem.getDevice().createBuffer(() -> "rockstar:liquidglass_dummy_vertex", 32, dummyData);
        MemoryUtil.memFree((Buffer)dummyData);
        initialized = true;
    }

    public static void draw(Matrix4f matrix, float x, float y, float w, float h, float[] radius, float alpha) {
        if (!initialized) {
            LiquidGlassPipeline.init();
        }
        ByteBuffer buffer = MemoryUtil.memAlloc((int)256);
        buffer.putFloat(matrix.m00()).putFloat(matrix.m01()).putFloat(matrix.m02()).putFloat(matrix.m03());
        buffer.putFloat(matrix.m10()).putFloat(matrix.m11()).putFloat(matrix.m12()).putFloat(matrix.m13());
        buffer.putFloat(matrix.m20()).putFloat(matrix.m21()).putFloat(matrix.m22()).putFloat(matrix.m23());
        buffer.putFloat(matrix.m30()).putFloat(matrix.m31()).putFloat(matrix.m32()).putFloat(matrix.m33());
        buffer.position(64);
        buffer.putFloat(w).putFloat(h);
        buffer.position(80);
        buffer.putFloat(radius[0]).putFloat(radius[1]).putFloat(radius[2]).putFloat(radius[3]);
        buffer.putFloat(1.0f);
        buffer.putFloat(2.0f);
        buffer.putFloat(alpha);
        buffer.putFloat(4.0f);
        buffer.position(112);
        buffer.putFloat(1.0f).putFloat(1.0f).putFloat(1.0f);
        buffer.putFloat(1.0f);
        buffer.position(128);
        buffer.putFloat(0.55f);
        buffer.putInt(0);
        buffer.putFloat(1.0f);
        buffer.putFloat(0.05f);
        buffer.flip();
        CommandEncoder encoder = RenderSystem.getDevice().createCommandEncoder();
        encoder.writeToBuffer(uniformBuffer.slice(), buffer);
        MemoryUtil.memFree((Buffer)buffer);
        class_310 client = class_310.method_1551();
        class_12137 sampler = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
        try (RenderPass pass = encoder.createRenderPass(() -> "LiquidGlass", client.method_1522().method_71639(), OptionalInt.empty(), client.method_1522().method_71640(), OptionalDouble.of(1.0));){
            pass.setPipeline(PIPELINE);
            pass.setVertexBuffer(0, dummyVertexBuffer);
            RenderSystem.bindDefaultUniforms((RenderPass)pass);
            pass.setUniform("Uniforms", uniformBuffer);
            GpuTextureView blurTexture = KawasePipeline.getBlurTextureView();
            if (blurTexture != null) {
                pass.bindTexture("Sampler0", blurTexture, sampler);
            }
            pass.draw(0, 6);
        }
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
        initialized = false;
    }

    static {
        initialized = false;
    }
}

