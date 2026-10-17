/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import java.awt.Color;
import me.pepperbell.continuity.module.modules.client.C11ck9U1;

public final class ColorUtils {
    public static Color interpolate(Color c1, Color c2, float ratio) {
        ratio = Math.max(0.0f, Math.min(1.0f, ratio));
        int r = (int)((float)c1.getRed() + (float)(c2.getRed() - c1.getRed()) * ratio);
        int g = (int)((float)c1.getGreen() + (float)(c2.getGreen() - c1.getGreen()) * ratio);
        int b = (int)((float)c1.getBlue() + (float)(c2.getBlue() - c1.getBlue()) * ratio);
        int a = (int)((float)c1.getAlpha() + (float)(c2.getAlpha() - c1.getAlpha()) * ratio);
        return new Color(r, g, b, a);
    }

    public static int interpolateInt(Color c1, Color c2, float ratio) {
        return ColorUtils.interpolate(c1, c2, ratio).getRGB();
    }

    public static Color getMainColor() {
        return C11ck9U1.gradientStart.getColor();
    }

    public static Color getSecondaryColor() {
        return C11ck9U1.gradientEnd.getColor();
    }
}

