/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.gui.Window;
import me.pepperbell.continuity.gui.components.settings.BlockListBox;
import me.pepperbell.continuity.gui.components.settings.CheckBox;
import me.pepperbell.continuity.gui.components.settings.ColorBox;
import me.pepperbell.continuity.gui.components.settings.ConfigBox;
import me.pepperbell.continuity.gui.components.settings.ItemBox;
import me.pepperbell.continuity.gui.components.settings.KeybindBox;
import me.pepperbell.continuity.gui.components.settings.MinMaxSlider;
import me.pepperbell.continuity.gui.components.settings.ModeBox;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.gui.components.settings.Slider;
import me.pepperbell.continuity.gui.components.settings.SlotBox;
import me.pepperbell.continuity.gui.components.settings.TextBox;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.modules.client.C11ck9U1;
import me.pepperbell.continuity.module.setting.BlockListSetting;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.ColorSetting;
import me.pepperbell.continuity.module.setting.ConfigSetting;
import me.pepperbell.continuity.module.setting.ItemSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.module.setting.SlotSetting;
import me.pepperbell.continuity.module.setting.StringSetting;
import me.pepperbell.continuity.utils.ColorUtils;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;

public final class ModuleButton {
    public List<RenderableSetting> settings = new ArrayList<RenderableSetting>();
    public Window parent;
    public Module module;
    public int offset;
    public float renderY;
    public boolean extended;
    public int settingOffset;
    private float arrowRotation = 0.0f;
    private float targetArrowRotation = 0.0f;
    private float renderExpandedHeight = 0.0f;

    public ModuleButton(Window parent, Module module, int offset) {
        this.parent = parent;
        this.module = module;
        this.offset = offset;
        this.renderY = offset;
        this.extended = false;
        int currentSettingY = 0;
        for (Setting<?> setting : module.getSettings()) {
            RenderableSetting rs = null;
            if (setting instanceof BooleanSetting) {
                BooleanSetting booleanSetting = (BooleanSetting)setting;
                rs = new CheckBox(this, booleanSetting, currentSettingY);
            } else if (setting instanceof SliderSetting) {
                SliderSetting sliderSetting = (SliderSetting)setting;
                rs = new Slider(this, sliderSetting, currentSettingY);
            } else if (setting instanceof ModeSetting) {
                ModeSetting modeSetting = (ModeSetting)setting;
                rs = new ModeBox(this, modeSetting, currentSettingY);
            } else if (setting instanceof KeybindSetting) {
                KeybindSetting keybindSetting = (KeybindSetting)setting;
                rs = new KeybindBox(this, keybindSetting, currentSettingY);
            } else if (setting instanceof MinMaxSetting) {
                MinMaxSetting minMaxSetting = (MinMaxSetting)setting;
                rs = new MinMaxSlider(this, minMaxSetting, currentSettingY);
            } else if (setting instanceof StringSetting) {
                StringSetting stringSetting = (StringSetting)setting;
                rs = new TextBox(this, stringSetting, currentSettingY);
            } else if (setting instanceof SlotSetting) {
                SlotSetting slotSetting = (SlotSetting)setting;
                rs = new SlotBox(this, slotSetting, currentSettingY);
            } else if (setting instanceof ColorSetting) {
                ColorSetting colorSetting = (ColorSetting)setting;
                rs = new ColorBox(this, colorSetting, currentSettingY);
            } else if (setting instanceof BlockListSetting) {
                BlockListSetting blockListSetting = (BlockListSetting)setting;
                rs = new BlockListBox(this, blockListSetting, currentSettingY);
            } else if (setting instanceof ConfigSetting) {
                ConfigSetting configSetting = (ConfigSetting)setting;
                rs = new ConfigBox(this, configSetting, currentSettingY);
            } else if (setting instanceof ItemSetting) {
                ItemSetting itemSetting = (ItemSetting)setting;
                rs = new ItemBox(this, itemSetting, currentSettingY);
            }
            if (rs == null) continue;
            this.settings.add(rs);
            currentSettingY += rs.height();
        }
        this.settingOffset = currentSettingY;
    }

    public void render(class_332 context, int mouseX, int mouseY, float delta, boolean isLast) {
        int textColor;
        if (Math.abs(this.renderY - (float)this.offset) > 20.0f) {
            this.renderY = this.offset;
        }
        this.renderY += ((float)this.offset - this.renderY) * 0.25f;
        int currentVisibleY = 20;
        for (RenderableSetting rs : this.settings) {
            if (!rs.setting.isVisible()) continue;
            rs.setOffset(currentVisibleY);
            currentVisibleY += rs.height();
        }
        this.settingOffset = currentVisibleY - 20;
        this.parent.updateHeight();
        this.renderExpandedHeight += ((float)(this.extended ? this.settingOffset : 0) - this.renderExpandedHeight) * 0.2f;
        float animatedAlphaFloat = Sweto.INSTANCE.getModuleManager().getModule(C11ck9U1.class).getAnimatedAlpha() / 255.0f;
        Matrix3x2fStack matrices = context.method_51448();
        float topY = this.renderY;
        float fullHeight = 22.0f + this.renderExpandedHeight;
        float leftX = 1.0f;
        float rightX = this.parent.getWidth() - 1;
        RenderUtils.renderRoundedQuad(context, new Color(255, 255, 255, 15), leftX + 4.0f, topY, rightX - 4.0f, (double)topY + 0.5, 0.25, 8.0);
        if (this.isHovered(mouseX, mouseY)) {
            RenderUtils.renderRoundedQuad(context, new Color(255, 255, 255, 25), 0.0, this.renderY, this.parent.getWidth(), 22.0f + this.renderY, 0.0, 0.0, isLast && !this.extended ? 6.0 : 0.0, isLast && !this.extended ? 6.0 : 0.0, 32.0);
        }
        if (this.module.isEnabled()) {
            int alpha = (int)(animatedAlphaFloat * 255.0f);
            textColor = alpha << 24 | 0xAAAAAA;
        } else {
            textColor = -1;
        }
        this.arrowRotation += (this.targetArrowRotation - this.arrowRotation) * 0.25f;
        matrices.pushMatrix();
        float baseScale = 0.75f;
        float targetScale = this.isHovered(mouseX, mouseY) ? 0.85f : 0.75f;
        float scaleFactor = baseScale + (targetScale - baseScale) * 0.5f;
        matrices.scale(scaleFactor, scaleFactor);
        Object nameText = this.module.getName().toString();
        String arrowText = ">";
        int rightEdge = (int)((float)(this.parent.getWidth() - 6) / scaleFactor);
        int indicatorX = (int)(6.0f / scaleFactor);
        float targetBaseX = this.module.isEnabled() ? 18.0f : 8.0f;
        int nameX = (int)(targetBaseX / scaleFactor);
        int maxNameWidth = (int)((float)(rightEdge - nameX) - (float)(TextRenderer.getWidth(arrowText) + 10) / scaleFactor);
        if (TextRenderer.getWidth((CharSequence)nameText) > maxNameWidth) {
            nameText = Sweto.mc.field_1772.method_27524((String)nameText, (int)((float)maxNameWidth * scaleFactor), false) + "...";
        }
        int buttonCenterY = (int)(this.renderY + 11.0f);
        int fontHeight = TextRenderer.getFontHeight();
        int scaledTextY = (int)(((double)buttonCenterY - (double)fontHeight / 2.0 + 2.0) / (double)scaleFactor);
        int arrowWidth = TextRenderer.getWidth(arrowText);
        int arrowX = (int)((float)(this.parent.getWidth() - 12) / scaleFactor);
        if (this.module.isEnabled()) {
            Color c1 = C11ck9U1.gradientStart.getColor();
            Color c2 = C11ck9U1.gradientEnd.getColor();
            int alpha = (int)(animatedAlphaFloat * 255.0f);
            Color start = new Color(c1.getRed(), c1.getGreen(), c1.getBlue(), alpha);
            Color end = new Color(c2.getRed(), c2.getGreen(), c2.getBlue(), alpha);
            float checkRatio = (float)(Math.sin((double)System.currentTimeMillis() / 1000.0) * 0.5 + 0.5);
            int checkColor = ColorUtils.interpolateInt(start, end, checkRatio);
            RenderUtils.drawCheckmark(context, indicatorX - 2, (float)((double)scaledTextY + 1.5), 14.0f, 2.0f, checkColor);
            TextRenderer.drawGradientGlowString((CharSequence)nameText, context, nameX, scaledTextY, start, end);
        } else {
            TextRenderer.drawString((CharSequence)nameText, context, nameX, scaledTextY, textColor, false);
        }
        int arrowCenterX = arrowX + arrowWidth / 2;
        int arrowCenterY = scaledTextY + TextRenderer.getFontHeight() / 2;
        matrices.pushMatrix();
        matrices.translate((float)arrowCenterX, (float)arrowCenterY);
        matrices.rotate((float)Math.toRadians(this.arrowRotation));
        matrices.translate((float)(-arrowCenterX), (float)(-arrowCenterY));
        TextRenderer.drawString(arrowText, context, arrowX, scaledTextY, -1, false);
        matrices.popMatrix();
        matrices.popMatrix();
        if (this.extended && this.renderExpandedHeight > 1.0f) {
            for (RenderableSetting renderableSetting : this.settings) {
                if (!renderableSetting.setting.isVisible()) continue;
                renderableSetting.render(context, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.extended) {
            for (RenderableSetting renderableSetting : this.settings) {
                if (!renderableSetting.setting.isVisible()) continue;
                renderableSetting.mouseDragged(mouseX - (double)this.parent.getX(), mouseY - (double)this.parent.getY(), button, deltaX, deltaY);
            }
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        ColorBox cb;
        double localMouseX = mouseX - (double)this.parent.getX();
        double localMouseY = mouseY - (double)this.parent.getY();
        if (this.extended) {
            for (RenderableSetting rs : this.settings) {
                if (!(rs instanceof ColorBox) || !(cb = (ColorBox)rs).isPickerOpen() || !cb.mouseClicked(localMouseX, localMouseY, button)) continue;
                return true;
            }
        }
        if (this.isHovered(localMouseX, localMouseY)) {
            if (button == 0) {
                this.module.toggle();
            }
            if (button == 1) {
                this.extended = !this.extended;
                this.targetArrowRotation = this.extended ? 90.0f : 0.0f;
                this.parent.updateHeight();
            }
            return true;
        }
        if (this.extended) {
            for (RenderableSetting renderableSetting : this.settings) {
                if (!renderableSetting.setting.isVisible() || renderableSetting instanceof ColorBox && (cb = (ColorBox)renderableSetting).isPickerOpen() || !renderableSetting.mouseClicked(localMouseX, localMouseY, button)) continue;
                return true;
            }
        }
        return false;
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (this.extended) {
            for (RenderableSetting renderableSetting : this.settings) {
                if (!renderableSetting.setting.isVisible()) continue;
                renderableSetting.mouseReleased(mouseX - (double)this.parent.getX(), mouseY - (double)this.parent.getY(), button);
            }
        }
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (this.extended) {
            for (RenderableSetting renderableSetting : this.settings) {
                if (!renderableSetting.setting.isVisible() || !renderableSetting.mouseScrolled(mouseX - (double)this.parent.getX(), mouseY - (double)this.parent.getY(), amount)) continue;
                return true;
            }
        }
        return false;
    }

    public boolean charTyped(char chr) {
        if (this.extended) {
            for (RenderableSetting renderableSetting : this.settings) {
                if (!renderableSetting.setting.isVisible() || !renderableSetting.charTyped(chr)) continue;
                return true;
            }
        }
        return false;
    }

    public boolean keyPressed(int key) {
        if (this.extended) {
            for (RenderableSetting renderableSetting : this.settings) {
                if (!renderableSetting.setting.isVisible() || !renderableSetting.keyPressed(key)) continue;
                return true;
            }
        }
        return false;
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > 0.0 && mouseX < (double)this.parent.getWidth() && mouseY > (double)this.renderY && mouseY < (double)(this.renderY + 22.0f);
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void updateRenderableSettingOffsets() {
        int currentSettingY = 20;
        for (RenderableSetting renderableSetting : this.settings) {
            if (!renderableSetting.setting.isVisible()) continue;
            renderableSetting.setOffset(currentSettingY);
            currentSettingY += renderableSetting.height();
        }
    }
}

