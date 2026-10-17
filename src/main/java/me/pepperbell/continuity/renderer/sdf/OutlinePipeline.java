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
import com.mojang.blaze3d.vertex.VertexFormat;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.minecraft.class_10789;
import net.minecraft.class_10799;
import net.minecraft.class_276;
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

public class OutlinePipeline {
    private static final int UNIFORM_SIZE = 256;
    private static final RenderPipeline PIPELINE = class_10799.method_67887((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[]{class_10799.field_60125}).withLocation(class_2960.method_60655((String)"immediatelyfast", (String)"pipeline/outline")).withVertexShader(class_2960.method_60655((String)"immediatelyfast", (String)"outline_vertex")).withFragmentShader(class_2960.method_60655((String)"immediatelyfast", (String)"outline_fragment")).withVertexFormat(class_290.field_60033, VertexFormat.class_5596.field_27379).withUniform("Uniforms", class_10789.field_60031).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
    private static final Vector4f COLOR_MODULATOR = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
    private static final Vector3f MODEL_OFFSET = new Vector3f(0.0f, 0.0f, 0.0f);
    private static final Matrix4f TEXTURE_MATRIX = new Matrix4f();
    private static GpuBuffer uniformBuffer;
    private static GpuBuffer dummyVertexBuffer;
    private static boolean initialized;

    public static void init() {
        if (initialized) {
            return;
        }
        uniformBuffer = RenderSystem.getDevice().createBuffer(() -> "Outline2D Uniforms", 136, 256L);
        ByteBuffer dummyData = MemoryUtil.memAlloc((int)4);
        dummyData.putInt(0);
        dummyData.flip();
        dummyVertexBuffer = RenderSystem.getDevice().createBuffer(() -> "immediatelyfast:outline_dummy_vertex", 32, dummyData);
        MemoryUtil.memFree((Buffer)dummyData);
        initialized = true;
    }

    public static void draw(Matrix4f matrix, float x, float y, float width, float height, float tl, float tr, float br, float bl, float thickness, float z, int ... colors) {
        if (!initialized) {
            OutlinePipeline.init();
        }
        if (uniformBuffer == null) {
            return;
        }
        int[] finalColors = OutlinePipeline.normalizeColors(colors);
        ByteBuffer buffer = MemoryUtil.memAlloc((int)256);
        buffer.putFloat(matrix.m00()).putFloat(matrix.m01()).putFloat(matrix.m02()).putFloat(matrix.m03());
        buffer.putFloat(matrix.m10()).putFloat(matrix.m11()).putFloat(matrix.m12()).putFloat(matrix.m13());
        buffer.putFloat(matrix.m20()).putFloat(matrix.m21()).putFloat(matrix.m22()).putFloat(matrix.m23());
        buffer.putFloat(matrix.m30()).putFloat(matrix.m31()).putFloat(matrix.m32()).putFloat(matrix.m33());
        buffer.position(64);
        buffer.putFloat(x).putFloat(y).putFloat(width).putFloat(height);
        buffer.putFloat(br).putFloat(tr).putFloat(bl).putFloat(tl);
        buffer.putFloat(thickness);
        buffer.putFloat(z);
        buffer.putFloat(0.0f).putFloat(0.0f);
        buffer.position(112);
        for (int i = 0; i < 9; ++i) {
            int color = finalColors[i];
            buffer.putFloat((float)(color >> 16 & 0xFF) / 255.0f);
            buffer.putFloat((float)(color >> 8 & 0xFF) / 255.0f);
            buffer.putFloat((float)(color & 0xFF) / 255.0f);
            buffer.putFloat((float)(color >> 24 & 0xFF) / 255.0f);
        }
        buffer.flip();
        CommandEncoder encoder = RenderSystem.getDevice().createCommandEncoder();
        encoder.writeToBuffer(uniformBuffer.slice(), buffer);
        MemoryUtil.memFree((Buffer)buffer);
        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().method_71106((Matrix4fc)RenderSystem.getModelViewMatrix(), (Vector4fc)COLOR_MODULATOR, (Vector3fc)MODEL_OFFSET, (Matrix4fc)TEXTURE_MATRIX);
        class_276 framebuffer = class_310.method_1551().method_1522();
        try (RenderPass pass = encoder.createRenderPass(() -> "Outline2D", framebuffer.method_71639(), OptionalInt.empty(), framebuffer.method_71640(), OptionalDouble.of(1.0));){
            pass.setPipeline(PIPELINE);
            pass.setVertexBuffer(0, dummyVertexBuffer);
            RenderSystem.bindDefaultUniforms((RenderPass)pass);
            pass.setUniform("DynamicTransforms", dynamicTransforms);
            pass.setUniform("Uniforms", uniformBuffer);
            pass.draw(0, 6);
        }
    }

    private static int[] normalizeColors(int[] colors) {
        if (colors.length == 1) {
            int c = colors[0];
            return new int[]{c, c, c, c, c, c, c, c, c};
        }
        if (colors.length >= 9) {
            return colors;
        }
        int[] result = new int[9];
        for (int i = 0; i < 9; ++i) {
            result[i] = i < colors.length ? colors[i] : colors[colors.length - 1];
        }
        return result;
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

