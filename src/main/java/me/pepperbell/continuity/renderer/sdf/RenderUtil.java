/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.renderer.sdf;

import java.util.ArrayList;
import java.util.List;
import me.pepperbell.continuity.renderer.sdf.KawasePipeline;
import me.pepperbell.continuity.renderer.sdf.OutlinePipeline;
import me.pepperbell.continuity.renderer.sdf.RectPipeline;
import net.minecraft.class_1041;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_408;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class RenderUtil {
    private static final List<Runnable> OVERRIDE_TASKS = new ArrayList<Runnable>();
    private static final float Z_OVERRIDE = 0.0f;
    private static final int FIXED_GUI_SCALE = 2;

    public static int getFixedScaledWidth() {
        class_1041 window = class_310.method_1551().method_22683();
        return (int)Math.ceil((double)window.method_4480() / 2.0);
    }

    public static int getFixedScaledHeight() {
        class_1041 window = class_310.method_1551().method_22683();
        return (int)Math.ceil((double)window.method_4507() / 2.0);
    }

    public static float getScaleFactor() {
        class_1041 window = class_310.method_1551().method_22683();
        int currentScale = window.method_4495();
        return (float)currentScale / 2.0f;
    }

    public static float convertX(float x) {
        return x * RenderUtil.getScaleFactor();
    }

    public static float convertY(float y) {
        return y * RenderUtil.getScaleFactor();
    }

    public static float convertSize(float size) {
        return size * RenderUtil.getScaleFactor();
    }

    public static Matrix4f createProjection() {
        return new Matrix4f().ortho(0.0f, (float)RenderUtil.getFixedScaledWidth(), (float)RenderUtil.getFixedScaledHeight(), 0.0f, -1000.0f, 1000.0f);
    }

    public static boolean hasScreenOpen() {
        class_310 client = class_310.method_1551();
        return client.field_1755 != null && !(client.field_1755 instanceof class_408);
    }

    public static void drawRoundedRect(class_332 context, float x, float y, float width, float height, float radius, int color, boolean overrideContext) {
        RenderUtil.drawRoundedRect(RenderUtil.createProjection(), x, y, width, height, radius, color, overrideContext);
    }

    public static void drawRoundedRect(class_332 context, float x, float y, float width, float height, float radius, boolean overrideContext, int ... colors) {
        RenderUtil.drawRoundedRect(RenderUtil.createProjection(), x, y, width, height, radius, radius, radius, radius, overrideContext, colors);
    }

    public static void drawRoundedRect(class_332 context, float x, float y, float width, float height, float tl, float tr, float br, float bl, boolean overrideContext, int ... colors) {
        RenderUtil.drawRoundedRect(RenderUtil.createProjection(), x, y, width, height, tl, tr, br, bl, overrideContext, colors);
    }

    public static void drawRoundedRect(Matrix4f matrix, float x, float y, float width, float height, float radius, int color, boolean overrideContext) {
        RenderUtil.drawRoundedRect(matrix, x, y, width, height, radius, radius, radius, radius, overrideContext, color);
    }

    public static void drawRoundedRect(Matrix4f matrix, float x, float y, float width, float height, float tl, float tr, float br, float bl, boolean overrideContext, int ... colors) {
        if (overrideContext) {
            OVERRIDE_TASKS.add(() -> RectPipeline.draw(matrix, x, y, width, height, tl, tr, br, bl, 0.0f, colors));
            return;
        }
        RectPipeline.draw(matrix, x, y, width, height, tl, tr, br, bl, 0.0f, colors);
    }

    public static void drawRoundedRect(Matrix4f matrix, float x, float y, float width, float height, float radius, boolean overrideContext, int ... colors) {
        RenderUtil.drawRoundedRect(matrix, x, y, width, height, radius, radius, radius, radius, overrideContext, colors);
    }

    public static void drawOutline(class_332 context, float x, float y, float width, float height, float radius, float thickness, int color, boolean overrideContext) {
        RenderUtil.drawOutline(RenderUtil.createProjection(), x, y, width, height, radius, radius, radius, radius, thickness, color, overrideContext);
    }

    public static void drawOutline(class_332 context, float x, float y, float width, float height, float tl, float tr, float br, float bl, float thickness, int color, boolean overrideContext) {
        RenderUtil.drawOutline(RenderUtil.createProjection(), x, y, width, height, tl, tr, br, bl, thickness, color, overrideContext);
    }

    public static void drawOutline(Matrix4f matrix, float x, float y, float width, float height, float radius, float thickness, int color, boolean overrideContext) {
        RenderUtil.drawOutline(matrix, x, y, width, height, radius, radius, radius, radius, thickness, color, overrideContext);
    }

    public static void drawOutline(Matrix4f matrix, float x, float y, float width, float height, float tl, float tr, float br, float bl, float thickness, int color, boolean overrideContext) {
        if (overrideContext) {
            OVERRIDE_TASKS.add(() -> OutlinePipeline.draw(matrix, x, y, width, height, tl, tr, br, bl, thickness, 0.0f, color));
            return;
        }
        OutlinePipeline.draw(matrix, x, y, width, height, tl, tr, br, bl, thickness, 0.0f, color);
    }

    public static void drawOutline(Matrix4f matrix, float x, float y, float width, float height, float radius, float thickness, boolean overrideContext, int ... colors) {
        RenderUtil.drawOutline(matrix, x, y, width, height, radius, radius, radius, radius, thickness, overrideContext, colors);
    }

    public static void drawOutline(Matrix4f matrix, float x, float y, float width, float height, float tl, float tr, float br, float bl, float thickness, boolean overrideContext, int ... colors) {
        if (overrideContext) {
            OVERRIDE_TASKS.add(() -> OutlinePipeline.draw(matrix, x, y, width, height, tl, tr, br, bl, thickness, 0.0f, colors));
            return;
        }
        OutlinePipeline.draw(matrix, x, y, width, height, tl, tr, br, bl, thickness, 0.0f, colors);
    }

    public static void drawBlur(class_332 context, float x, float y, float width, float height, float radius, float strength, boolean overrideContext) {
        Matrix4f projection = RenderUtil.createProjection();
        if (overrideContext) {
            OVERRIDE_TASKS.add(() -> KawasePipeline.draw(projection, x, y, width, height, radius, strength, false, 0.0f));
            return;
        }
        KawasePipeline.draw(projection, x, y, width, height, radius, strength, false, 0.0f);
    }

    public static void drawTexture(class_332 context, float x, float y, float size, class_2960 texture, int color, float radius, boolean overrideContext) {
    }

    public static void addOverrideTask(Runnable task) {
        OVERRIDE_TASKS.add(task);
    }

    public static void renderOverrides(class_332 context) {
        if (OVERRIDE_TASKS.isEmpty()) {
            return;
        }
        GL11.glDisable((int)2929);
        OVERRIDE_TASKS.forEach(Runnable::run);
        OVERRIDE_TASKS.clear();
        GL11.glEnable((int)2929);
    }

    public static void clearOverrideTasks() {
        OVERRIDE_TASKS.clear();
    }

    public static boolean hasOverrideTasks() {
        return !OVERRIDE_TASKS.isEmpty();
    }

    public static boolean isOverrideActive() {
        class_310 client = class_310.method_1551();
        return client.field_1755 == null || client.field_1755 instanceof class_408;
    }
}

