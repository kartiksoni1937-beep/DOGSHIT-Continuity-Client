/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import java.awt.Color;
import java.util.Objects;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.font.Fonts;
import me.pepperbell.continuity.module.modules.client.C11ck9U1;
import me.pepperbell.continuity.utils.ColorUtils;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;

public final class TextRenderer {
    public static int getFontHeight() {
        if (C11ck9U1.customFont.getValue()) {
            return Fonts.POPPINS.getFontHeight();
        }
        Objects.requireNonNull(Sweto.mc.field_1772);
        return 9;
    }

    public static void drawString(CharSequence string, class_332 context, int x, int y, int color) {
        if (C11ck9U1.customFont.getValue()) {
            int shadowColor = color & 0xFFFFFF | 0x50000000;
            Fonts.POPPINS.drawString(context, string, (float)x + 0.5f, (float)y + 0.5f, shadowColor);
            Fonts.POPPINS.drawString(context, string, x, y, color);
        } else {
            context.method_25303(Sweto.mc.field_1772, string.toString(), x, y, color);
        }
    }

    public static void drawGlowString(CharSequence string, class_332 context, float x, float y, int color) {
        double intensity = C11ck9U1.glowIntensity != null ? C11ck9U1.glowIntensity.getValue() / 1.5 : 1.0;
        TextRenderer.drawGlowString(string, context, x, y, color, intensity);
    }

    public static void drawScaledGlowString(CharSequence string, class_332 context, float x, float y, int color, float scale) {
        Matrix3x2fStack matrices = context.method_51448();
        matrices.pushMatrix();
        matrices.scale(scale, scale);
        TextRenderer.drawGlowString(string, context, x / scale, y / scale, color);
        matrices.popMatrix();
    }

    public static void drawGlowString(CharSequence string, class_332 context, float x, float y, int color, double intensity) {
        float[] offsets;
        if (!C11ck9U1.textGlow.getValue()) {
            TextRenderer.drawString(string, context, x, y, color, false);
            return;
        }
        int alpha = (int)Math.min(255.0, 25.0 * intensity);
        int glowColor = color & 0xFFFFFF | alpha << 24;
        for (float ox : offsets = new float[]{-0.5f, 0.5f}) {
            for (float oy : offsets) {
                TextRenderer.drawString(string, context, x + ox, y + oy, glowColor, false);
            }
        }
        TextRenderer.drawString(string, context, x, y, color, false);
    }

    public static int getWidth(CharSequence string) {
        if (C11ck9U1.customFont.getValue()) {
            return Fonts.POPPINS.getStringWidth(string);
        }
        return Sweto.mc.field_1772.method_1727(string.toString());
    }

    public static String trim(String string, int width) {
        if (C11ck9U1.customFont.getValue()) {
            return Fonts.POPPINS.trimStringToWidth(string, width).toString();
        }
        return Sweto.mc.field_1772.method_27523(string, width);
    }

    public static void drawCenteredString(CharSequence string, class_332 context, int x, int y, int color) {
        if (C11ck9U1.customFont.getValue()) {
            int shadowColor = color & 0xFFFFFF | 0x50000000;
            int cx = x - Fonts.POPPINS.getStringWidth(string) / 2;
            Fonts.POPPINS.drawString(context, string, (float)cx + 0.5f, (float)y + 0.5f, shadowColor);
            Fonts.POPPINS.drawString(context, string, cx, y, color);
        } else {
            context.method_25303(Sweto.mc.field_1772, string.toString(), x - Sweto.mc.field_1772.method_1727(string.toString()) / 2, y, color);
        }
    }

    public static void drawString(CharSequence string, class_332 context, float x, float y, int color, boolean shadow) {
        if (C11ck9U1.customFont.getValue()) {
            int shadowColor = color & 0xFFFFFF | 0x50000000;
            Fonts.POPPINS.drawString(context, string, x + 0.5f, y + 0.5f, shadowColor);
            Fonts.POPPINS.drawString(context, string, x, y, color);
        } else {
            context.method_51433(Sweto.mc.field_1772, string.toString(), (int)x, (int)y, color, shadow);
        }
    }

    public static void drawLargeString(CharSequence string, class_332 context, int x, int y, int color) {
        boolean custom = C11ck9U1.customFont.getValue();
        if (custom) {
            Matrix3x2fStack matrices = context.method_51448();
            matrices.pushMatrix();
            matrices.scale(1.4f, 1.4f);
            Fonts.POPPINS.drawString(context, string, x, y, color);
            matrices.popMatrix();
        } else {
            TextRenderer.drawLargerMinecraftText(string, context, x, y, color);
        }
    }

    public static void drawMinecraftText(CharSequence string, class_332 context, int x, int y, int color) {
        Matrix3x2fStack matrices = context.method_51448();
        matrices.pushMatrix();
        matrices.scale(2.0f, 2.0f);
        context.method_51433(Sweto.mc.field_1772, string.toString(), x / 2, y / 2, color, false);
        matrices.popMatrix();
    }

    public static void drawLargerMinecraftText(CharSequence string, class_332 context, int x, int y, int color) {
        Matrix3x2fStack matrices = context.method_51448();
        matrices.pushMatrix();
        matrices.scale(3.0f, 3.0f);
        context.method_51433(Sweto.mc.field_1772, string.toString(), x / 3, y / 3, color, false);
        matrices.popMatrix();
    }

    public static void drawCenteredMinecraftText(CharSequence string, class_332 context, int x, int y, int color) {
        Matrix3x2fStack matrices = context.method_51448();
        matrices.pushMatrix();
        matrices.scale(2.0f, 2.0f);
        context.method_51433(Sweto.mc.field_1772, string.toString(), x / 2 - Sweto.mc.field_1772.method_1727(string.toString()) / 2, y / 2, color, false);
        matrices.popMatrix();
    }

    public static void drawGradientString(CharSequence text, class_332 context, float x, float y, Color c1, Color c2, boolean shadow) {
        float currentX = x;
        int len = text.length();
        if (len == 0) {
            return;
        }
        long time = System.currentTimeMillis();
        for (int i = 0; i < len; ++i) {
            char c = text.charAt(i);
            float delta = (float)(Math.sin((double)time / 1000.0 + (double)i / 5.0) * 0.5 + 0.5);
            int color = ColorUtils.interpolateInt(c1, c2, delta);
            String charStr = String.valueOf(c);
            TextRenderer.drawString(charStr, context, currentX, y, color, shadow);
            currentX += (float)TextRenderer.getWidth(charStr);
        }
    }

    public static void drawGradientGlowString(CharSequence text, class_332 context, float x, float y, Color c1, Color c2) {
        float[] offsets;
        if (!C11ck9U1.textGlow.getValue()) {
            TextRenderer.drawGradientString(text, context, x, y, c1, c2, false);
            return;
        }
        double intensity = C11ck9U1.glowIntensity != null ? C11ck9U1.glowIntensity.getValue() / 1.5 : 1.0;
        int alpha = (int)Math.min(255.0, 25.0 * intensity);
        for (float ox : offsets = new float[]{-0.5f, 0.5f}) {
            for (float oy : offsets) {
                Color gc1 = new Color(c1.getRed(), c1.getGreen(), c1.getBlue(), alpha);
                Color gc2 = new Color(c2.getRed(), c2.getGreen(), c2.getBlue(), alpha);
                TextRenderer.drawGradientString(text, context, x + ox, y + oy, gc1, gc2, false);
            }
        }
        TextRenderer.drawGradientString(text, context, x, y, c1, c2, false);
    }

    public static void drawScaleString(CharSequence text, class_332 context, float x, float y, float scale, int color) {
        Matrix3x2fStack matrices = context.method_51448();
        matrices.pushMatrix();
        matrices.scale(scale, scale);
        TextRenderer.drawString(text, context, x / scale, y / scale, color, false);
        matrices.popMatrix();
    }
}

