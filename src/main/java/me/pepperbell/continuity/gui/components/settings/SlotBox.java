/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components.settings;

import java.awt.Color;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.module.setting.SlotSetting;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;

public final class SlotBox
extends RenderableSetting {
    private final SlotSetting setting;

    public SlotBox(ModuleButton parent, Setting<?> setting, int offset) {
        super(parent, setting, offset);
        this.setting = (SlotSetting)setting;
    }

    @Override
    public int height() {
        return 45;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        Matrix3x2fStack matrices = context.method_51448();
        int textX = this.parentX() + 8;
        int centerY = this.parentY() + this.parentOffset() + this.offset + 12;
        matrices.pushMatrix();
        float labelScale = 0.7f;
        matrices.scale(labelScale, labelScale);
        int scaledTextX = (int)((float)textX / labelScale);
        int scaledTextY = (int)(((float)centerY - (float)TextRenderer.getFontHeight() * labelScale / 2.0f) / labelScale);
        TextRenderer.drawString(this.setting.getName().toString(), context, scaledTextX, scaledTextY, -4473925, false);
        matrices.popMatrix();
        float boxSize = 10.0f;
        float spacing = 2.0f;
        int totalWidth = (int)(9.0f * boxSize + 8.0f * spacing);
        float startX = this.parentX() + 8;
        float startY = centerY + 12;
        Color accentColor = new Color(180, 180, 180);
        Color bgColor = new Color(45, 45, 45, 200);
        for (int i = 0; i < 9; ++i) {
            float boxX = startX + (float)i * (boxSize + spacing);
            float boxY = startY;
            boolean value = this.setting.getSlot(i);
            RenderUtils.renderRoundedQuad(context, value ? accentColor : bgColor, boxX, boxY, boxX + boxSize, boxY + boxSize, 3.0, 10.0);
            RenderUtils.renderRoundedOutline(context, value ? accentColor : new Color(100, 100, 100), boxX, boxY, boxX + boxSize, boxY + boxSize, 3.0, 3.0, 3.0, 3.0, 0.5, 10.0);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int textX = this.parentX() + 8;
        int centerY = this.parentY() + this.parentOffset() + this.offset + 12;
        float boxSize = 10.0f;
        float spacing = 2.0f;
        float startX = this.parentX() + 8;
        float startY = centerY + 12;
        if (mouseY >= (double)startY && mouseY <= (double)(startY + boxSize)) {
            for (int i = 0; i < 9; ++i) {
                float boxX = startX + (float)i * (boxSize + spacing);
                if (!(mouseX >= (double)boxX) || !(mouseX <= (double)(boxX + boxSize))) continue;
                this.setting.toggleSlot(i);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}

