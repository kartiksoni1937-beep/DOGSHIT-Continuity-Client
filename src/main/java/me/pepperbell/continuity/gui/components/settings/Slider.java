/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components.settings;

import java.awt.Color;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.MathUtils;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;

public final class Slider
extends RenderableSetting {
    public boolean dragging;
    protected SliderSetting settingRef;
    private double min;
    private double max;
    private double step;
    private float animatedProgress;

    public SliderSetting getSettingRef() {
        return this.settingRef;
    }

    public Slider(ModuleButton parent, Setting<?> setting, int offset) {
        super(parent, setting, offset);
        if (!(setting instanceof SliderSetting)) {
            throw new IllegalArgumentException("Unsupported setting type for Slider: " + setting.getClass().getName());
        }
        this.settingRef = (SliderSetting)setting;
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
        double currentValue = this.settingRef.getValue();
        if (this.dragging) {
            float trackX = 10.0f;
            float trackWidth = this.parentWidth() - 20;
            this.animatedProgress = Math.max(0.0f, Math.min(1.0f, ((float)mouseX - trackX) / trackWidth));
        } else {
            float targetProgress = (float)((currentValue - this.min) / (this.max - this.min));
            targetProgress = Math.max(0.0f, Math.min(1.0f, targetProgress));
            this.animatedProgress += (targetProgress - this.animatedProgress) * 0.15f;
        }
        float renderProgress = this.animatedProgress;
        Color accentColor = new Color(180, 180, 180);
        Color labelColor = new Color(200, 200, 200);
        Color valueColor = Color.WHITE;
        Color trackBgColor = new Color(15, 15, 15, 200);
        matrices.pushMatrix();
        float labelScale = 0.7f;
        matrices.scale(labelScale, labelScale);
        String valueStr = String.format(this.step >= 1.0 ? "%.0f" : "%.1f", currentValue);
        int valueWidth = TextRenderer.getWidth(valueStr);
        int valueX = (int)((float)(this.parentX() + this.parentWidth() - 8) / labelScale) - valueWidth;
        int labelX = (int)((float)textX / labelScale);
        int maxLabelWidth = valueX - labelX - (int)(5.0f / labelScale);
        Object labelText = this.setting.getName().toString();
        if (TextRenderer.getWidth((CharSequence)labelText) > maxLabelWidth) {
            labelText = this.mc.field_1772.method_27524((String)labelText, (int)((float)maxLabelWidth * labelScale), false) + "...";
        }
        int labelY = (int)((float)(topY + 4) / labelScale);
        TextRenderer.drawString((CharSequence)labelText, context, labelX, labelY, -4473925, false);
        TextRenderer.drawString(valueStr, context, valueX, labelY, -1, false);
        matrices.popMatrix();
        float trackHeight = 4.0f;
        float trackY = (float)(topY + 22) - trackHeight / 2.0f;
        float trackX = this.parentX() + 10;
        float trackWidth = this.parentWidth() - 20;
        RenderUtils.renderRoundedQuad(context, trackBgColor, trackX, trackY, trackX + trackWidth, trackY + trackHeight, 2.0, 16.0);
        if (renderProgress > 0.0f) {
            RenderUtils.renderRoundedQuad(context, accentColor, trackX, trackY, trackX + trackWidth * renderProgress, trackY + trackHeight, 2.0, 16.0);
        }
        float knobX = trackX + trackWidth * renderProgress;
        float knobWidth = 2.0f;
        float knobExtraHeight = 2.0f;
        RenderUtils.renderRoundedQuad(context, Color.WHITE, knobX - knobWidth / 2.0f, trackY - knobExtraHeight, knobX + knobWidth / 2.0f, trackY + trackHeight + knobExtraHeight, 2.0, 16.0);
        if (this.dragging) {
            RenderUtils.renderRoundedOutline(context, accentColor, knobX - knobWidth / 2.0f - 1.0f, trackY - knobExtraHeight - 1.0f, knobX + knobWidth / 2.0f + 1.0f, trackY + trackHeight + knobExtraHeight + 1.0f, 1.0, 1.0, 1.0, 1.0, 0.5, 8.0);
        }
    }

    private void slide(double mouseX) {
        float trackX = this.parentX() + 10;
        float trackWidth = this.parentWidth() - 20;
        double progress = (mouseX - (double)trackX) / (double)trackWidth;
        double rawValue = progress * (this.max - this.min) + this.min;
        double snappedValue = MathUtils.roundToStep(rawValue, this.step);
        snappedValue = Math.max(this.min, Math.min(this.max, snappedValue));
        this.settingRef.setValue(snappedValue);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isHovered(mouseX, mouseY) && button == 0) {
            this.dragging = true;
            this.slide(mouseX);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (this.dragging && button == 0) {
            this.dragging = false;
        }
        super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.dragging) {
            this.slide(mouseX);
        }
        super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}

