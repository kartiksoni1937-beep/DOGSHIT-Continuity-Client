/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components.settings;

import java.awt.Color;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.module.setting.StringSetting;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_156;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;

public final class TextBox
extends RenderableSetting {
    private final StringSetting setting;
    private boolean focused;
    private long lastCursorBlink;
    private boolean cursorVisible;

    public TextBox(ModuleButton parent, Setting<?> setting, int offset) {
        super(parent, setting, offset);
        this.setting = (StringSetting)setting;
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
        int scaledLabelX = (int)((float)textX / labelScale);
        int scaledLabelY = (int)((float)(this.parentY() + this.parentOffset() + this.offset + 4) / labelScale);
        TextRenderer.drawString(this.setting.getName().toString(), context, scaledLabelX, scaledLabelY, -4473925, false);
        matrices.popMatrix();
        float boxWidth = this.parentWidth() - 16;
        float boxHeight = 14.0f;
        float boxX = this.parentX() + 8;
        float boxY = centerY + 1;
        RenderUtils.renderRoundedQuad(context, new Color(30, 30, 30, 200), boxX, boxY, boxX + boxWidth, boxY + boxHeight, 2.0, 10.0);
        Color borderColor = this.focused ? new Color(180, 180, 180) : new Color(80, 80, 80);
        RenderUtils.renderRoundedOutline(context, borderColor, boxX, boxY, boxX + boxWidth, boxY + boxHeight, 2.0, 2.0, 2.0, 2.0, 0.5, 10.0);
        String valueText = this.setting.getValue();
        if (this.focused) {
            if (class_156.method_658() - this.lastCursorBlink > 500L) {
                this.cursorVisible = !this.cursorVisible;
                this.lastCursorBlink = class_156.method_658();
            }
        } else {
            this.cursorVisible = false;
        }
        matrices.pushMatrix();
        float textScale = 0.8f;
        matrices.scale(textScale, textScale);
        int scaledTextX = (int)((boxX + 3.0f) / textScale);
        int scaledTextY = (int)((boxY + (boxHeight - (float)TextRenderer.getFontHeight() * textScale) / 2.0f) / textScale);
        TextRenderer.drawString(valueText, context, scaledTextX, scaledTextY, -1, false);
        if (this.cursorVisible) {
            int textWidthSoFar = TextRenderer.getWidth(valueText);
            float cursorX = boxX + 3.0f + (float)textWidthSoFar * textScale + 1.0f;
            float cursorY = boxY + 2.0f;
            RenderUtils.drawQuad(context, cursorX, cursorY, cursorX + 0.5f, cursorY + boxHeight - 4.0f, -1);
        }
        matrices.popMatrix();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isHovered(mouseX, mouseY) && button == 0) {
            this.focused = true;
            this.lastCursorBlink = class_156.method_658();
            this.cursorVisible = true;
            return true;
        }
        if (button == 0) {
            this.focused = false;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean charTyped(char chr) {
        if (!this.focused) {
            return false;
        }
        this.setting.setValue(this.setting.getValue() + chr);
        return true;
    }

    @Override
    public boolean keyPressed(int key) {
        if (!this.focused) {
            return false;
        }
        if (key == 259) {
            String current = this.setting.getValue();
            if (!current.isEmpty()) {
                this.setting.setValue(current.substring(0, current.length() - 1));
            }
            return true;
        }
        if (key == 257 || key == 256) {
            this.focused = false;
            return true;
        }
        return false;
    }

    public boolean isFocused() {
        return this.focused;
    }
}

