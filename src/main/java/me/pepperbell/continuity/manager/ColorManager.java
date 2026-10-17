/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.manager;

import java.awt.Color;
import me.pepperbell.continuity.module.modules.client.C11ck9U1;

public class ColorManager {
    private static ColorManager INSTANCE;

    public ColorManager() {
        INSTANCE = this;
    }

    public static ColorManager getInstance() {
        return INSTANCE;
    }

    public Color getThemeColor() {
        return C11ck9U1.gradientStart.getColor();
    }

    public Color getThemeColor(int alpha) {
        Color c = this.getThemeColor();
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
    }

    public int getColorWithAlpha(int alpha) {
        Color c = this.getThemeColor();
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha).getRGB();
    }

    public int getHoverColor() {
        return this.getColorWithAlpha(C11ck9U1.hoverAlpha != null ? C11ck9U1.hoverAlpha.getValueInt() : 180);
    }

    public int getBackgroundAlpha() {
        return C11ck9U1.backgroundAlpha != null ? C11ck9U1.backgroundAlpha.getValueInt() : 230;
    }

    public Color getGradientStart() {
        return C11ck9U1.gradientStart.getColor();
    }

    public Color getGradientEnd() {
        return C11ck9U1.gradientEnd.getColor();
    }

    public int interpolateColors(Color c1, Color c2, float ratio) {
        ratio = Math.max(0.0f, Math.min(1.0f, ratio));
        int r = (int)((float)c1.getRed() + (float)(c2.getRed() - c1.getRed()) * ratio);
        int g = (int)((float)c1.getGreen() + (float)(c2.getGreen() - c1.getGreen()) * ratio);
        int b = (int)((float)c1.getBlue() + (float)(c2.getBlue() - c1.getBlue()) * ratio);
        int a = (int)((float)c1.getAlpha() + (float)(c2.getAlpha() - c1.getAlpha()) * ratio);
        return new Color(r, g, b, a).getRGB();
    }
}

