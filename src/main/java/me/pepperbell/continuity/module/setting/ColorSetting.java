/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.setting;

import java.awt.Color;
import me.pepperbell.continuity.module.setting.Setting;

public final class ColorSetting
extends Setting<ColorSetting> {
    private Color color;
    private boolean rainbow;

    public ColorSetting(CharSequence name, Color color) {
        super(name);
        this.color = color;
        this.rainbow = false;
    }

    public Color getColor() {
        if (this.rainbow) {
            return Color.getHSBColor((float)(System.currentTimeMillis() % 5000L) / 5000.0f, 0.8f, 1.0f);
        }
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRGB() {
        return this.getColor().getRGB();
    }

    public boolean isRainbow() {
        return this.rainbow;
    }

    public void setRainbow(boolean rainbow) {
        this.rainbow = rainbow;
    }
}

