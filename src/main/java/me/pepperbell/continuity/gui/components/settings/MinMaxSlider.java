/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components.settings;

import java.awt.Color;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.utils.MathUtils;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;

public final class MinMaxSlider
extends RenderableSetting {
    private final MinMaxSetting settingRef;
    private final double min;
    private final double max;
    private final double step;
    private boolean draggingMin;
    private boolean draggingMax;

    public MinMaxSlider(ModuleButton parent, Setting<?> setting, int offset) {
        super(parent, setting, offset);
        if (!(setting instanceof MinMaxSetting)) {
            throw new IllegalArgumentException("Unsupported setting type for MinMaxSlider: " + setting.getClass().getName());
        }
        this.settingRef = (MinMaxSetting)setting;
        this.min = this.settingRef.getMin();
        this.max = this.settingRef.getMax();
        this.step = this.settingRef.getIncrement();
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        Matrix3x2fStack matrices = context.method_51448();
        int textX = this.parentX() + 8;
        int topY = this.parentY() + this.parentOffset() + this.offset;
        int width = this.parentWidth() - 16;
        double minVal = this.settingRef.getMinValue();
        double maxVal = this.settingRef.getMaxValue();
        float minProgress = (float)((minVal - this.min) / (this.max - this.min));
        float maxProgress = (float)((maxVal - this.min) / (this.max - this.min));
        Color accentColor = new Color(180, 180, 180);
        Color labelColor = new Color(200, 200, 200);
        Color valueColor = Color.WHITE;
        Color trackBgColor = new Color(15, 15, 15, 200);
        matrices.pushMatrix();
        float labelScale = 0.7f;
        matrices.scale(labelScale, labelScale);
        String rangeStr = String.format(this.step >= 1.0 ? "%.0f - %.0f" : "%.1f - %.1f", minVal, maxVal);
        int rangeWidth = TextRenderer.getWidth(rangeStr);
        int rangeX = (int)((float)(this.parentX() + this.parentWidth() - 8) / labelScale) - rangeWidth;
        int labelX = (int)((float)textX / labelScale);
        int maxLabelWidth = rangeX - labelX - (int)(5.0f / labelScale);
        Object labelText = this.setting.getName().toString();
        if (TextRenderer.getWidth((CharSequence)labelText) > maxLabelWidth) {
            labelText = this.mc.field_1772.method_27524((String)labelText, (int)((float)maxLabelWidth * labelScale), false) + "...";
        }
        int labelY = (int)((float)(topY + 4) / labelScale);
        TextRenderer.drawString((CharSequence)labelText, context, labelX, labelY, -4473925, false);
        TextRenderer.drawString(rangeStr, context, rangeX, labelY, -1, false);
        matrices.popMatrix();
        float trackHeight = 4.0f;
        float trackX = this.parentX() + 10;
        float trackY = topY + 20;
        float trackWidth = this.parentWidth() - 20;
        RenderUtils.renderRoundedQuad(context, trackBgColor, trackX, trackY - 1.0f, trackX + trackWidth, trackY + trackHeight + 1.0f, 2.0, 16.0);
        float fillStartX = trackX + trackWidth * minProgress;
        float fillEndX = trackX + trackWidth * maxProgress;
        if (fillEndX > fillStartX) {
            RenderUtils.renderRoundedQuad(context, accentColor, fillStartX, trackY, fillEndX, trackY + trackHeight, 2.0, 16.0);
        }
        float knobWidth = 2.0f;
        float knobExtraHeight = 2.0f;
        RenderUtils.renderRoundedQuad(context, Color.WHITE, fillStartX - knobWidth / 2.0f, trackY - knobExtraHeight, fillStartX + knobWidth / 2.0f, trackY + trackHeight + knobExtraHeight, 2.0, 16.0);
        RenderUtils.renderRoundedQuad(context, Color.WHITE, fillEndX - knobWidth / 2.0f, trackY - knobExtraHeight, fillEndX + knobWidth / 2.0f, trackY + trackHeight + knobExtraHeight, 2.0, 16.0);
    }

    private void updateValues(double mouseX) {
        float trackX = this.parentX() + 10;
        float trackWidth = this.parentWidth() - 20;
        double progress = (mouseX - (double)trackX) / (double)trackWidth;
        double rawValue = progress * (this.max - this.min) + this.min;
        double snappedValue = MathUtils.roundToStep(rawValue, this.step);
        snappedValue = Math.max(this.min, Math.min(this.max, snappedValue));
        if (this.draggingMin) {
            if (snappedValue > this.settingRef.getMaxValue()) {
                snappedValue = this.settingRef.getMaxValue();
            }
            this.settingRef.setMinValue(snappedValue);
        } else if (this.draggingMax) {
            if (snappedValue < this.settingRef.getMinValue()) {
                snappedValue = this.settingRef.getMinValue();
            }
            this.settingRef.setMaxValue(snappedValue);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isHovered(mouseX, mouseY) && button == 0) {
            float trackX = this.parentX() + 10;
            float trackWidth = this.parentWidth() - 20;
            double minX = (this.settingRef.getMinValue() - this.min) / (this.max - this.min) * (double)trackWidth + (double)trackX;
            double maxX = (this.settingRef.getMaxValue() - this.min) / (this.max - this.min) * (double)trackWidth + (double)trackX;
            double distMin = Math.abs(mouseX - minX);
            double distMax = Math.abs(mouseX - maxX);
            double hitRadius = 10.0;
            if (distMin < distMax && distMin < hitRadius) {
                this.draggingMin = true;
            } else if (distMax < hitRadius) {
                this.draggingMax = true;
            } else if (distMin < distMax) {
                this.draggingMin = true;
            } else {
                this.draggingMax = true;
            }
            this.updateValues(mouseX);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            this.draggingMin = false;
            this.draggingMax = false;
        }
        super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.draggingMin || this.draggingMax) {
            this.updateValues(mouseX);
        }
        super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}

