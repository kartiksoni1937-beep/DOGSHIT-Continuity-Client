/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import java.awt.Color;
import java.util.function.Consumer;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.module.modules.client.C11ck9U1;
import net.minecraft.class_12249;
import net.minecraft.class_1657;
import net.minecraft.class_1921;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_9799;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import org.lwjgl.opengl.GL11;

public final class RenderUtils {
    public static boolean rendering3D = true;
    private static int projectionDepth;

    public static class_243 getCameraPos() {
        return Sweto.mc.field_1773.method_19418().method_71156();
    }

    public static float tickProgress() {
        return Sweto.mc.method_61966().method_60637(true);
    }

    public static float frameDelta() {
        return Sweto.mc.method_61966().method_60636();
    }

    public static double deltaTime() {
        return Sweto.mc.method_47599() > 0 ? 1.0 / (double)Sweto.mc.method_47599() : 1.0;
    }

    public static float fast(float end, float start, float multiple) {
        return (1.0f - class_3532.method_15363((float)((float)(RenderUtils.deltaTime() * (double)multiple)), (float)0.0f, (float)1.0f)) * end + class_3532.method_15363((float)((float)(RenderUtils.deltaTime() * (double)multiple)), (float)0.0f, (float)1.0f) * start;
    }

    public static class_243 getPlayerLookVec(class_1657 player) {
        float f = (float)Math.PI / 180;
        float pi = (float)Math.PI;
        float f1 = class_3532.method_15362((double)(-player.method_36454() * f - pi));
        float f2 = class_3532.method_15374((double)(-player.method_36454() * f - pi));
        float f3 = -class_3532.method_15362((double)(-player.method_36455() * f));
        float f4 = class_3532.method_15374((double)(-player.method_36455() * f));
        return new class_243((double)(f2 * f3), (double)f4, (double)(f1 * f3)).method_1029();
    }

    public static void unscaledProjection(class_332 context, float scale) {
        if (projectionDepth++ == 0) {
            float scaleFactor = class_310.method_1551().method_22683().method_4495();
            context.method_51448().pushMatrix();
            context.method_51448().scale(scale / scaleFactor, scale / scaleFactor);
        }
        rendering3D = false;
    }

    public static void scaledProjection(class_332 context) {
        if (projectionDepth == 0) {
            rendering3D = true;
            return;
        }
        if (--projectionDepth == 0) {
            context.method_51448().popMatrix();
        }
        rendering3D = true;
    }

    public static void drawLayer(class_1921 layer, Consumer<class_4588> runner) {
        int bufferSize = Math.max(256, Math.min(layer.method_22722(), 8192));
        try (class_9799 allocator = new class_9799(bufferSize);){
            class_4597.class_4598 immediate = class_4597.method_22991((class_9799)allocator);
            runner.accept(immediate.method_73477(layer));
            immediate.method_22994(layer);
        }
    }

    public static void emitLine(class_4588 buffer, Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2, float red, float green, float blue, float alpha, float lineWidth) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        float dz = z2 - z1;
        float length = Math.max(1.0E-4f, class_3532.method_15355((float)(dx * dx + dy * dy + dz * dz)));
        float nx = dx / length;
        float ny = dy / length;
        float nz = dz / length;
        buffer.method_22918((Matrix4fc)matrix, x1, y1, z1).method_22915(red, green, blue, alpha).method_22914(nx, ny, nz).method_75298(lineWidth);
        buffer.method_22918((Matrix4fc)matrix, x2, y2, z2).method_22915(red, green, blue, alpha).method_22914(nx, ny, nz).method_75298(lineWidth);
    }

    public static void renderHudBackground(class_332 context, float x, float y, float w, float h) {
        float radius = 13.0f;
        RenderUtils.renderBlurredRect(context, x, y, w, h, radius, 3.0f);
        RenderUtils.renderRoundedQuad(context, new Color(10, 10, 10, 140), x, y, x + w, y + h, radius, radius, radius, radius, 32.0);
        RenderUtils.renderRoundedOutline(context, new Color(255, 255, 255, 25), x, y, x + w, y + h, radius, radius, radius, radius, 0.5, 32.0);
    }

    public static void renderBlurredRect(class_332 context, float x, float y, float width, float height, float radius, float strength) {
    }

    public static void renderRoundedQuad(class_332 context, Color color, double x, double y, double x2, double y2, double tl, double tr, double bl, double br, double samples) {
        int ix = (int)Math.min(x, x2);
        int iy = (int)Math.min(y, y2);
        int ix2 = (int)Math.max(x, x2);
        int iy2 = (int)Math.max(y, y2);
        context.method_25294(ix, iy, ix2, iy2, color.getRGB());
    }

    public static void renderRoundedGradientQuad(class_332 context, Color c1, Color c2, double x, double y, double x2, double y2, double r1, double r2, double r3, double r4, double samples) {
        int ix = (int)Math.min(x, x2);
        int iy = (int)Math.min(y, y2);
        int ix2 = (int)Math.max(x, x2);
        int iy2 = (int)Math.max(y, y2);
        context.method_25296(ix, iy, ix2, iy2, c1.getRGB(), c2.getRGB());
    }

    public static void renderRoundedQuad(class_332 context, Color color, double x, double y, double x1, double y1, double rad, double samples) {
        RenderUtils.renderRoundedQuad(context, color, x, y, x1, y1, rad, rad, rad, rad, samples);
    }

    public static void setScissorRegion(int x, int y, int width, int height) {
        class_437 currentScreen = class_310.method_1551().field_1755;
        int screenHeight = currentScreen == null ? 0 : currentScreen.field_22790 - height;
        double scaleFactor = class_310.method_1551().method_22683().method_4495();
        GL11.glScissor((int)((int)((double)x * scaleFactor)), (int)((int)((double)screenHeight * scaleFactor)), (int)((int)((double)(width - x) * scaleFactor)), (int)((int)((double)(height - y) * scaleFactor)));
        GL11.glEnable((int)3089);
    }

    public static void renderCircle(class_332 context, Color color, double originX, double originY, double radius, int segments) {
        int top = (int)Math.floor(originY - radius);
        int bottom = (int)Math.ceil(originY + radius);
        int packedColor = color.getRGB();
        for (int y = top; y < bottom; ++y) {
            double radiusSquared = radius * radius;
            double distanceY = (double)y + 0.5 - originY;
            double inner = radiusSquared - distanceY * distanceY;
            if (inner <= 0.0) continue;
            double distanceX = Math.sqrt(inner);
            int left = (int)Math.floor(originX - distanceX);
            int right = (int)Math.ceil(originX + distanceX);
            RenderUtils.fillRow(context, left, right, y, packedColor);
        }
    }

    public static void renderShaderRect(class_332 context, Color color, Color color2, Color color3, Color color4, float x, float y, float width, float height, float f5, float f6) {
        RenderUtils.renderRoundedGradientQuad(context, color, color2, x, y, x + width, y + height, 0.0, 0.0, 0.0, 0.0, 32.0);
    }

    public static void renderRoundedOutline(class_332 context, Color c, double x, double y, double x2, double y2, double tl, double tr, double bl, double br, double thickness, double samples) {
        int ix = (int)Math.min(x, x2);
        int iy = (int)Math.min(y, y2);
        int ix2 = (int)Math.max(x, x2);
        int iy2 = (int)Math.max(y, y2);
        int rgb = c.getRGB();
        int t = Math.max(1, (int)thickness);
        context.method_25294(ix, iy, ix2, iy + t, rgb);
        context.method_25294(ix, iy2 - t, ix2, iy2, rgb);
        context.method_25294(ix, iy, ix + t, iy2, rgb);
        context.method_25294(ix2 - t, iy, ix2, iy2, rgb);
    }

    public static class_4587 matrixFrom(double x, double y, double z) {
        class_4587 matrices = new class_4587();
        class_4184 camera = class_310.method_1551().field_1773.method_19418();
        matrices.method_22907((Quaternionfc)class_7833.field_40714.rotationDegrees(camera.method_19329()));
        matrices.method_22907((Quaternionfc)class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
        class_243 cameraPos = camera.method_71156();
        matrices.method_22904(x - cameraPos.field_1352, y - cameraPos.field_1351, z - cameraPos.field_1350);
        return matrices;
    }

    public static void renderQuad(class_332 context, float x, float y, float width, float height, int color) {
        context.method_25294((int)x, (int)y, (int)(x + width), (int)(y + height), color);
    }

    private static int clampRadius(double radius, int width, int height) {
        return Math.max(0, Math.min((int)Math.round(radius), Math.min(width, height) / 2));
    }

    private static int getLeftInsetForRow(int row, int height, int radiusTop, int radiusBottom) {
        int inset = 0;
        if (radiusTop > 0 && row < radiusTop) {
            inset = Math.max(inset, RenderUtils.getCornerInset(radiusTop, row));
        }
        if (radiusBottom > 0 && row >= height - radiusBottom) {
            inset = Math.max(inset, RenderUtils.getCornerInset(radiusBottom, height - 1 - row));
        }
        return inset;
    }

    private static int getRightInsetForRow(int row, int height, int radiusTop, int radiusBottom) {
        int inset = 0;
        if (radiusTop > 0 && row < radiusTop) {
            inset = Math.max(inset, RenderUtils.getCornerInset(radiusTop, row));
        }
        if (radiusBottom > 0 && row >= height - radiusBottom) {
            inset = Math.max(inset, RenderUtils.getCornerInset(radiusBottom, height - 1 - row));
        }
        return inset;
    }

    private static int getCornerInset(int radius, int offsetFromEdge) {
        double distance = (double)radius - ((double)offsetFromEdge + 0.5);
        double inside = (double)(radius * radius) - distance * distance;
        if (inside <= 0.0) {
            return radius;
        }
        return Math.max(0, (int)Math.ceil((double)radius - Math.sqrt(inside)));
    }

    private static void fillRow(class_332 context, int left, int right, int y, int color) {
        if (right <= left) {
            return;
        }
        context.method_25294(left, y, right, y + 1, color);
    }

    private static void addQuad(class_4588 buffer, Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, float red, float green, float blue, float alpha) {
        buffer.method_22918((Matrix4fc)matrix, x1, y1, z1).method_22915(red, green, blue, alpha);
        buffer.method_22918((Matrix4fc)matrix, x2, y2, z2).method_22915(red, green, blue, alpha);
        buffer.method_22918((Matrix4fc)matrix, x3, y3, z3).method_22915(red, green, blue, alpha);
        buffer.method_22918((Matrix4fc)matrix, x4, y4, z4).method_22915(red, green, blue, alpha);
    }

    public static void renderFilledBox(class_4587 matrices, float f, float f2, float f3, float f4, float f5, float f6, Color color) {
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        float alpha = (float)color.getAlpha() / 255.0f;
        Matrix4f matrix = matrices.method_23760().method_23761();
        GL11.glDepthFunc((int)519);
        RenderUtils.drawLayer(class_12249.method_76019(), buffer -> {
            RenderUtils.addQuad(buffer, matrix, f, f2, f3, f4, f2, f3, f4, f2, f6, f, f2, f6, red, green, blue, alpha);
            RenderUtils.addQuad(buffer, matrix, f, f5, f3, f, f5, f6, f4, f5, f6, f4, f5, f3, red, green, blue, alpha);
            RenderUtils.addQuad(buffer, matrix, f, f2, f3, f, f5, f3, f4, f5, f3, f4, f2, f3, red, green, blue, alpha);
            RenderUtils.addQuad(buffer, matrix, f, f2, f6, f4, f2, f6, f4, f5, f6, f, f5, f6, red, green, blue, alpha);
            RenderUtils.addQuad(buffer, matrix, f, f2, f3, f, f2, f6, f, f5, f6, f, f5, f3, red, green, blue, alpha);
            RenderUtils.addQuad(buffer, matrix, f4, f2, f3, f4, f5, f3, f4, f5, f6, f4, f2, f6, red, green, blue, alpha);
        });
        GL11.glDepthFunc((int)515);
    }

    public static void renderBoxOutline(class_4587 matrices, class_238 box, Color color, float lineWidth) {
        Matrix4f matrix = matrices.method_23760().method_23761();
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        float alpha = (float)color.getAlpha() / 255.0f;
        if (C11ck9U1.antiAliasing.getValue()) {
            GL11.glEnable((int)32925);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
        }
        GL11.glDepthFunc((int)519);
        RenderUtils.drawLayer(class_12249.method_76015(), buffer -> {
            float minX = (float)box.field_1323;
            float minY = (float)box.field_1322;
            float minZ = (float)box.field_1321;
            float maxX = (float)box.field_1320;
            float maxY = (float)box.field_1325;
            float maxZ = (float)box.field_1324;
            RenderUtils.emitLine(buffer, matrix, minX, minY, minZ, maxX, minY, minZ, red, green, blue, alpha, lineWidth);
            RenderUtils.emitLine(buffer, matrix, maxX, minY, minZ, maxX, minY, maxZ, red, green, blue, alpha, lineWidth);
            RenderUtils.emitLine(buffer, matrix, maxX, minY, maxZ, minX, minY, maxZ, red, green, blue, alpha, lineWidth);
            RenderUtils.emitLine(buffer, matrix, minX, minY, maxZ, minX, minY, minZ, red, green, blue, alpha, lineWidth);
            RenderUtils.emitLine(buffer, matrix, minX, maxY, minZ, maxX, maxY, minZ, red, green, blue, alpha, lineWidth);
            RenderUtils.emitLine(buffer, matrix, maxX, maxY, minZ, maxX, maxY, maxZ, red, green, blue, alpha, lineWidth);
            RenderUtils.emitLine(buffer, matrix, maxX, maxY, maxZ, minX, maxY, maxZ, red, green, blue, alpha, lineWidth);
            RenderUtils.emitLine(buffer, matrix, minX, maxY, maxZ, minX, maxY, minZ, red, green, blue, alpha, lineWidth);
            RenderUtils.emitLine(buffer, matrix, minX, minY, minZ, minX, maxY, minZ, red, green, blue, alpha, lineWidth);
            RenderUtils.emitLine(buffer, matrix, maxX, minY, minZ, maxX, maxY, minZ, red, green, blue, alpha, lineWidth);
            RenderUtils.emitLine(buffer, matrix, maxX, minY, maxZ, maxX, maxY, maxZ, red, green, blue, alpha, lineWidth);
            RenderUtils.emitLine(buffer, matrix, minX, minY, maxZ, minX, maxY, maxZ, red, green, blue, alpha, lineWidth);
        });
        GL11.glDepthFunc((int)515);
        if (C11ck9U1.antiAliasing.getValue()) {
            GL11.glDisable((int)2848);
            GL11.glDisable((int)32925);
        }
    }

    public static void renderFilledFace(class_4587 matrices, class_238 box, class_2350 side, Color color) {
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        float alpha = (float)color.getAlpha() / 255.0f;
        Matrix4f matrix = matrices.method_23760().method_23761();
        float minX = (float)box.field_1323;
        float minY = (float)box.field_1322;
        float minZ = (float)box.field_1321;
        float maxX = (float)box.field_1320;
        float maxY = (float)box.field_1325;
        float maxZ = (float)box.field_1324;
        GL11.glDepthFunc((int)519);
        RenderUtils.drawLayer(class_12249.method_76019(), buffer -> {
            switch (side) {
                case field_11033: {
                    RenderUtils.addQuad(buffer, matrix, minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, minX, minY, maxZ, red, green, blue, alpha);
                    break;
                }
                case field_11036: {
                    RenderUtils.addQuad(buffer, matrix, minX, maxY, minZ, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, red, green, blue, alpha);
                    break;
                }
                case field_11043: {
                    RenderUtils.addQuad(buffer, matrix, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ, maxX, minY, minZ, red, green, blue, alpha);
                    break;
                }
                case field_11035: {
                    RenderUtils.addQuad(buffer, matrix, minX, minY, maxZ, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ, red, green, blue, alpha);
                    break;
                }
                case field_11039: {
                    RenderUtils.addQuad(buffer, matrix, minX, minY, minZ, minX, minY, maxZ, minX, maxY, maxZ, minX, maxY, minZ, red, green, blue, alpha);
                    break;
                }
                case field_11034: {
                    RenderUtils.addQuad(buffer, matrix, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, maxX, minY, maxZ, red, green, blue, alpha);
                }
            }
        });
        GL11.glDepthFunc((int)515);
    }

    public static void renderFaceOutline(class_4587 matrices, class_238 box, class_2350 side, Color color, float lineWidth) {
        Matrix4f matrix = matrices.method_23760().method_23761();
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        float alpha = (float)color.getAlpha() / 255.0f;
        float minX = (float)box.field_1323;
        float minY = (float)box.field_1322;
        float minZ = (float)box.field_1321;
        float maxX = (float)box.field_1320;
        float maxY = (float)box.field_1325;
        float maxZ = (float)box.field_1324;
        GL11.glDepthFunc((int)519);
        RenderUtils.drawLayer(class_12249.method_76015(), buffer -> {
            switch (side) {
                case field_11033: {
                    RenderUtils.emitLine(buffer, matrix, minX, minY, minZ, maxX, minY, minZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, maxX, minY, minZ, maxX, minY, maxZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, maxX, minY, maxZ, minX, minY, maxZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, minX, minY, maxZ, minX, minY, minZ, red, green, blue, alpha, lineWidth);
                    break;
                }
                case field_11036: {
                    RenderUtils.emitLine(buffer, matrix, minX, maxY, minZ, maxX, maxY, minZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, maxX, maxY, minZ, maxX, maxY, maxZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, maxX, maxY, maxZ, minX, maxY, maxZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, minX, maxY, maxZ, minX, maxY, minZ, red, green, blue, alpha, lineWidth);
                    break;
                }
                case field_11043: {
                    RenderUtils.emitLine(buffer, matrix, minX, minY, minZ, maxX, minY, minZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, maxX, minY, minZ, maxX, maxY, minZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, maxX, maxY, minZ, minX, maxY, minZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, minX, maxY, minZ, minX, minY, minZ, red, green, blue, alpha, lineWidth);
                    break;
                }
                case field_11035: {
                    RenderUtils.emitLine(buffer, matrix, minX, minY, maxZ, maxX, minY, maxZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, maxX, minY, maxZ, maxX, maxY, maxZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, maxX, maxY, maxZ, minX, maxY, maxZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, minX, maxY, maxZ, minX, minY, maxZ, red, green, blue, alpha, lineWidth);
                    break;
                }
                case field_11039: {
                    RenderUtils.emitLine(buffer, matrix, minX, minY, minZ, minX, minY, maxZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, minX, minY, maxZ, minX, maxY, maxZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, minX, maxY, maxZ, minX, maxY, minZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, minX, maxY, minZ, minX, minY, minZ, red, green, blue, alpha, lineWidth);
                    break;
                }
                case field_11034: {
                    RenderUtils.emitLine(buffer, matrix, maxX, minY, minZ, maxX, minY, maxZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, maxX, minY, maxZ, maxX, maxY, maxZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, maxX, maxY, maxZ, maxX, maxY, minZ, red, green, blue, alpha, lineWidth);
                    RenderUtils.emitLine(buffer, matrix, maxX, maxY, minZ, maxX, minY, minZ, red, green, blue, alpha, lineWidth);
                }
            }
        });
        GL11.glDepthFunc((int)515);
    }

    public static void renderLine(class_4587 matrices, Color color, class_243 start, class_243 end) {
        matrices.method_22903();
        Matrix4f matrix = matrices.method_23760().method_23761();
        if (C11ck9U1.antiAliasing.getValue()) {
            GL11.glEnable((int)32925);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
        }
        GL11.glDepthFunc((int)519);
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        float alpha = (float)color.getAlpha() / 255.0f;
        RenderUtils.drawLayer(class_12249.method_76015(), buffer -> RenderUtils.emitLine(buffer, matrix, (float)start.field_1352, (float)start.field_1351, (float)start.field_1350, (float)end.field_1352, (float)end.field_1351, (float)end.field_1350, red, green, blue, alpha, 1.0f));
        GL11.glDepthFunc((int)515);
        if (C11ck9U1.antiAliasing.getValue()) {
            GL11.glDisable((int)2848);
            GL11.glDisable((int)32925);
        }
        matrices.method_22909();
    }

    public static void drawQuad(class_332 context, float x1, float y1, float x2, float y2, int color) {
        context.method_25294((int)x1, (int)y1, (int)x2, (int)y2, color);
    }

    public static void drawGradientQuad(class_332 context, float x, float y, float x1, float y1, int color1, int color2, float yStart, float yEnd) {
        context.method_25296((int)x, (int)y, (int)x1, (int)y1, color1, color2);
    }

    public static void renderGradientRoundedOutline(class_332 context, int color1, int color2, double x, double y, double x2, double y2, double r1, double r2, double r3, double r4, double thickness, double samples) {
        int ix = (int)Math.min(x, x2);
        int iy = (int)Math.min(y, y2);
        int ix2 = (int)Math.max(x, x2);
        int iy2 = (int)Math.max(y, y2);
        int t = Math.max(1, (int)thickness);
        context.method_25294(ix, iy, ix2, iy + t, color1);
        context.method_25294(ix, iy2 - t, ix2, iy2, color2);
        context.method_25294(ix, iy, ix + t, iy2, color1);
        context.method_25294(ix2 - t, iy, ix2, iy2, color1);
    }

    public static void drawCheckmark(class_332 context, float x, float y, float size, float thickness, int color) {
        Matrix3x2fStack matrices = context.method_51448();
        Color c = new Color(color, true);
        float unit = size / 24.0f;
        matrices.pushMatrix();
        matrices.translate(x, y);
        matrices.pushMatrix();
        matrices.translate(4.0f * unit, 12.0f * unit);
        matrices.rotate((float)Math.toRadians(45.0));
        RenderUtils.renderRoundedQuad(context, c, 0.0, 0.0, 7.07f * unit, thickness, (double)thickness / 2.0, 32.0);
        matrices.popMatrix();
        matrices.pushMatrix();
        matrices.translate(9.0f * unit, 17.0f * unit);
        matrices.rotate((float)Math.toRadians(-45.0));
        RenderUtils.renderRoundedQuad(context, c, 0.0, -thickness, 15.55f * unit, 0.0, (double)thickness / 2.0, 32.0);
        matrices.popMatrix();
        matrices.popMatrix();
    }

    public static void drawCross(class_332 context, float x, float y, float size, float thickness, int color) {
        Matrix3x2fStack matrices = context.method_51448();
        Color c = new Color(color, true);
        float unit = size / 24.0f;
        matrices.pushMatrix();
        matrices.translate(x + size / 2.0f, y + size / 2.0f);
        matrices.pushMatrix();
        matrices.rotate((float)Math.toRadians(45.0));
        RenderUtils.renderRoundedQuad(context, c, -size / 2.8f, -thickness / 2.0f, size / 2.8f, thickness / 2.0f, (double)thickness / 2.0, 32.0);
        matrices.popMatrix();
        matrices.pushMatrix();
        matrices.rotate((float)Math.toRadians(-45.0));
        RenderUtils.renderRoundedQuad(context, c, -size / 2.8f, -thickness / 2.0f, size / 2.8f, thickness / 2.0f, (double)thickness / 2.0, 32.0);
        matrices.popMatrix();
        matrices.popMatrix();
    }
}

