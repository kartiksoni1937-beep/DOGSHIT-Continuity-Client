/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components.settings;

import java.awt.Color;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;
import org.lwjgl.glfw.GLFW;

public final class KeybindBox
extends RenderableSetting {
    public final KeybindSetting setting;

    public KeybindBox(ModuleButton parent, Setting<?> setting, int offset) {
        super(parent, setting, offset);
        this.setting = (KeybindSetting)setting;
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
        int scaledTextX = (int)((float)textX / labelScale);
        int scaledTextY = (int)(((float)centerY - (float)TextRenderer.getFontHeight() * labelScale / 2.0f) / labelScale);
        TextRenderer.drawString(this.setting.getName().toString(), context, scaledTextX, scaledTextY, -4473925, false);
        matrices.popMatrix();
        String keyText = this.setting.isListening() ? "..." : this.getKeyName(this.setting.getKey());
        int keyWidth = TextRenderer.getWidth(keyText);
        float tagPadding = 3.0f;
        float tagWidth = Math.max(16.0f, (float)keyWidth * 0.7f + tagPadding * 2.0f);
        float tagHeight = 10.0f;
        float tagX = (float)(this.parentX() + this.parentWidth()) - tagWidth - 8.0f;
        float tagY = (float)centerY - tagHeight / 2.0f;
        int tagColor = this.setting.isListening() ? new Color(140, 140, 140, 200).getRGB() : new Color(35, 35, 35, 200).getRGB();
        RenderUtils.renderRoundedQuad(context, new Color(tagColor, true), tagX, tagY, tagX + tagWidth, tagY + tagHeight, 3.0, 10.0);
        RenderUtils.renderRoundedOutline(context, this.setting.isListening() ? Color.WHITE : new Color(80, 80, 80), tagX, tagY, tagX + tagWidth, tagY + tagHeight, 3.0, 3.0, 3.0, 3.0, 0.5, 10.0);
        matrices.pushMatrix();
        float keyScale = 0.7f;
        matrices.scale(keyScale, keyScale);
        int scaledTagTextX = (int)((tagX + (tagWidth - (float)keyWidth * keyScale) / 2.0f) / keyScale);
        int scaledTagTextY = (int)(((double)centerY - (double)((float)TextRenderer.getFontHeight() * keyScale) / 2.0) / (double)keyScale);
        TextRenderer.drawString(keyText, context, scaledTagTextX, scaledTagTextY, -1, false);
        matrices.popMatrix();
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return super.isHovered(mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isHovered(mouseX, mouseY) && button == 0) {
            this.setting.toggleListening();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private String getKeyName(int keyCode) {
        if (keyCode == -1 || keyCode == -1 || keyCode == 0) {
            return "NONE";
        }
        if (keyCode == 0) {
            return "LMB";
        }
        if (keyCode == 1) {
            return "RMB";
        }
        if (keyCode == 2) {
            return "MMB";
        }
        if (keyCode == 3) {
            return "M4";
        }
        if (keyCode == 4) {
            return "M5";
        }
        if (keyCode == 5) {
            return "M6";
        }
        if (keyCode == 6) {
            return "M7";
        }
        if (keyCode == 7) {
            return "M8";
        }
        String keyName = GLFW.glfwGetKeyName((int)keyCode, (int)0);
        if (keyName == null) {
            switch (keyCode) {
                case 344: {
                    return "RSHIFT";
                }
                case 340: {
                    return "LSHIFT";
                }
                case 345: {
                    return "RCTRL";
                }
                case 341: {
                    return "LCTRL";
                }
                case 346: {
                    return "RALT";
                }
                case 342: {
                    return "LALT";
                }
                case 258: {
                    return "TAB";
                }
                case 257: {
                    return "ENTER";
                }
                case 259: {
                    return "BACK";
                }
                case 261: {
                    return "DEL";
                }
                case 256: {
                    return "ESC";
                }
            }
            return "KEY " + keyCode;
        }
        return keyName.toUpperCase();
    }

    @Override
    public boolean keyPressed(int key) {
        if (this.setting.isListening()) {
            this.onKeyPress(key, 0, 0);
            return true;
        }
        return super.keyPressed(key);
    }

    public void onKeyPress(int key, int scanCode, int modifiers) {
        this.handleKeyInput(key);
    }

    public void onMousePress(int button) {
        this.handleKeyInput(button);
    }

    private void handleKeyInput(int keyCode) {
        if (this.setting.isListening()) {
            if (keyCode == 256 || keyCode == 259) {
                this.setting.setKey(-1);
                if (this.setting.isModuleKey()) {
                    this.parent.module.setKey(-1);
                }
            } else {
                this.setting.setKey(keyCode);
                if (this.setting.isModuleKey()) {
                    this.parent.module.setKey(keyCode);
                }
            }
            this.setting.setListening(false);
        }
    }
}

