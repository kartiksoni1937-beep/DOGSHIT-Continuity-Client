/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components.settings;

import java.awt.Color;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;

public final class CheckBox
extends RenderableSetting {
    private final BooleanSetting setting;
    private float pillProgress;

    public CheckBox(ModuleButton parent, Setting<?> setting, int offset) {
        super(parent, setting, offset);
        this.setting = (BooleanSetting)setting;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        Matrix3x2fStack matrices = context.method_51448();
        int textX = this.parentX() + 8;
        int centerY = this.parentY() + this.parentOffset() + this.offset + 15;
        matrices.pushMatrix();
        float labelScale = 0.7f;
        matrices.scale(labelScale, labelScale);
        float bgWidth = 20.0f;
        float boxX = (float)(this.parentX() + this.parentWidth()) - bgWidth - 10.0f;
        int scaledTextX = (int)((float)textX / labelScale);
        int maxLabelWidth = (int)((boxX - 5.0f - (float)textX) / labelScale);
        Object labelText = this.setting.getName().toString();
        if (TextRenderer.getWidth((CharSequence)labelText) > maxLabelWidth) {
            labelText = this.mc.field_1772.method_27524((String)labelText, (int)((float)maxLabelWidth * labelScale), false) + "...";
        }
        int scaledTextY = (int)(((float)centerY - (float)TextRenderer.getFontHeight() * labelScale / 2.0f) / labelScale);
        int labelColor = this.setting.getValue() ? -1 : -4473925;
        TextRenderer.drawString((CharSequence)labelText, context, scaledTextX, scaledTextY, labelColor, false);
        matrices.popMatrix();
        float bgHeight = 10.0f;
        float boxY = (float)centerY - bgHeight / 2.0f;
        boolean hovered = this.isHovered(mouseX, mouseY);
        Color accentColor = new Color(180, 180, 180);
        Color bgColor = new Color(30, 30, 35, 200);
        RenderUtils.renderRoundedQuad(context, bgColor, boxX, boxY, boxX + bgWidth, boxY + bgHeight, 5.0, 16.0);
        if (hovered) {
            RenderUtils.renderRoundedOutline(context, new Color(180, 180, 180, 40), (double)boxX - 0.5, (double)boxY - 0.5, (double)(boxX + bgWidth) + 0.5, (double)(boxY + bgHeight) + 0.5, 5.0, 5.0, 5.0, 5.0, 1.0, 16.0);
        }
        float pillTarget = this.setting.getValue() ? 1.0f : 0.0f;
        this.pillProgress += (pillTarget - this.pillProgress) * 0.2f;
        float pillPadding = 2.0f;
        float pillSize = bgHeight - pillPadding * 2.0f;
        float minPillX = boxX + pillPadding;
        float maxPillX = boxX + bgWidth - pillSize - pillPadding;
        float pillX = minPillX + (maxPillX - minPillX) * this.pillProgress;
        float pillY = boxY + pillPadding;
        Color pillColor = this.setting.getValue() ? accentColor : new Color(80, 80, 80);
        RenderUtils.renderRoundedQuad(context, pillColor, pillX, pillY, pillX + pillSize, pillY + pillSize, 3.0, 16.0);
        if (this.pillProgress > 0.5f) {
            RenderUtils.renderRoundedQuad(context, new Color(255, 255, 255, (int)(40.0f * (this.pillProgress - 0.5f) * 2.0f)), pillX, pillY, pillX + pillSize, pillY + pillSize, 3.0, 16.0);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isHovered(mouseX, mouseY)) {
            this.setting.setValue(!this.setting.getValue());
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}

