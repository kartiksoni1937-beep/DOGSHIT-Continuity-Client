/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components.settings;

import java.awt.Color;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;

public final class ModeBox
extends RenderableSetting {
    public final ModeSetting<?> setting;

    public ModeBox(ModuleButton parent, Setting<?> setting, int offset) {
        super(parent, setting, offset);
        this.setting = (ModeSetting)setting;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        Matrix3x2fStack matrices = context.method_51448();
        int textX = this.parentX() + 8;
        int centerY = this.parentY() + this.parentOffset() + this.offset + 15;
        String modeText = ((Enum)this.setting.getMode()).toString();
        int modeWidth = TextRenderer.getWidth(modeText);
        float pillPadding = 4.0f;
        float pillWidth = Math.max(70.0f, (float)modeWidth * 0.7f + pillPadding * 2.0f);
        float pillHeight = 12.0f;
        float pillX = (float)(this.parentX() + this.parentWidth()) - pillWidth - 8.0f;
        float pillY = (float)centerY - pillHeight / 2.0f;
        matrices.pushMatrix();
        float labelScale = 0.7f;
        matrices.scale(labelScale, labelScale);
        int scaledTextX = (int)((float)textX / labelScale);
        int scaledTextY = (int)(((float)centerY - (float)TextRenderer.getFontHeight() * labelScale / 2.0f) / labelScale);
        Object name = this.setting.getName().toString();
        int availableWidth = (int)((pillX - (float)textX - 10.0f) / labelScale);
        if (TextRenderer.getWidth((CharSequence)name) > availableWidth) {
            name = TextRenderer.trim((String)name, availableWidth) + "...";
        }
        TextRenderer.drawString((CharSequence)name, context, scaledTextX, scaledTextY, -4473925, false);
        matrices.popMatrix();
        RenderUtils.renderRoundedQuad(context, new Color(45, 45, 45, 200), pillX, pillY, pillX + pillWidth, pillY + pillHeight, 4.0, 10.0);
        matrices.pushMatrix();
        float modeScale = 0.7f;
        matrices.scale(modeScale, modeScale);
        float scaledTextWidth = (float)modeWidth * modeScale;
        int scaledPillTextX = (int)((pillX + (pillWidth - scaledTextWidth) / 2.0f) / modeScale);
        int scaledPillTextY = (int)(((float)centerY - (float)TextRenderer.getFontHeight() * modeScale / 2.0f) / modeScale);
        TextRenderer.drawString(modeText, context, scaledPillTextX, scaledPillTextY, this.isHovered(mouseX, mouseY) ? new Color(180, 180, 180).getRGB() : -1, false);
        matrices.popMatrix();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isHovered(mouseX, mouseY)) {
            this.setting.cycle();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}

