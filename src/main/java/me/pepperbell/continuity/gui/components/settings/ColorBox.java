/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components.settings;

import java.awt.Color;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.module.setting.ColorSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;

public final class ColorBox
extends RenderableSetting {
    private final ColorSetting setting;
    private boolean pickerOpen;
    private boolean draggingHue;
    private boolean draggingSquare;
    private float[] currentHSB;

    public ColorBox(ModuleButton parent, Setting<?> setting, int offset) {
        super(parent, setting, offset);
        this.setting = (ColorSetting)setting;
        Color c = this.setting.getColor();
        this.currentHSB = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
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
        float boxW = 32.0f;
        float boxH = 12.0f;
        float boxX = (float)(this.parentX() + this.parentWidth()) - boxW - 8.0f;
        float boxY = (float)centerY - boxH / 2.0f;
        Color displayColor = this.setting.getColor();
        RenderUtils.renderRoundedQuad(context, new Color(0, 0, 0, 100), boxX + 1.0f, boxY + 1.0f, boxX + boxW + 1.0f, boxY + boxH + 1.0f, 3.0, 16.0);
        RenderUtils.renderRoundedQuad(context, displayColor, boxX, boxY, boxX + boxW, boxY + boxH, 3.0, 16.0);
        RenderUtils.renderRoundedOutline(context, new Color(255, 255, 255, 100), boxX, boxY, boxX + boxW, boxY + boxH, 3.0, 3.0, 3.0, 3.0, 0.5, 16.0);
        if (this.setting.isRainbow()) {
            matrices.pushMatrix();
            float rScale = 0.5f;
            matrices.scale(rScale, rScale);
            String rText = "RAINBOW";
            int rX = (int)((boxX + (boxW - (float)TextRenderer.getWidth(rText) * rScale) / 2.0f) / rScale);
            int rY = (int)(((float)centerY - (float)TextRenderer.getFontHeight() * rScale / 2.0f) / rScale);
            TextRenderer.drawString(rText, context, rX, rY, -1, true);
            matrices.popMatrix();
        }
        if (this.pickerOpen) {
            this.renderPicker(context, mouseX, mouseY);
        }
    }

    private void renderPicker(class_332 context, int mouseX, int mouseY) {
        Matrix3x2fStack matrices = context.method_51448();
        int pickerW = 140;
        int pickerH = 170;
        int pickerX = this.parentX() + this.parentWidth() + 4;
        int pickerY = this.parentY() + this.parentOffset() + this.offset;
        if (pickerX + pickerW > this.parentWidth() + 50) {
            pickerX = this.parentX() - pickerW - 4;
        }
        RenderUtils.renderRoundedQuad(context, new Color(18, 18, 22, 240), pickerX, pickerY, pickerX + pickerW, pickerY + pickerH, 8.0, 32.0);
        RenderUtils.renderRoundedOutline(context, new Color(255, 255, 255, 30), pickerX, pickerY, pickerX + pickerW, pickerY + pickerH, 8.0, 8.0, 8.0, 8.0, 0.5, 32.0);
        int pad = 8;
        int squareSize = 100;
        int sqX = pickerX + pad;
        int sqY = pickerY + pad + 14;
        int hueBarX = sqX + squareSize + 6;
        int hueBarW = 12;
        int hueBarH = squareSize;
        float hue = this.currentHSB[0];
        float sat = this.currentHSB[1];
        float bri = this.currentHSB[2];
        this.drawHSBSquare(context, sqX, sqY, squareSize, hue);
        this.drawHueBar(context, hueBarX, sqY, hueBarW, hueBarH, hue);
        float markerX = (float)sqX + sat * (float)squareSize;
        float markerY = (float)sqY + (1.0f - bri) * (float)squareSize;
        RenderUtils.renderRoundedOutline(context, Color.WHITE, markerX - 3.0f, markerY - 3.0f, markerX + 3.0f, markerY + 3.0f, 3.0, 3.0, 3.0, 3.0, 1.5, 16.0);
        float hueMarkerY = (float)sqY + (1.0f - hue) * (float)hueBarH;
        RenderUtils.renderRoundedQuad(context, Color.WHITE, hueBarX - 2, hueMarkerY - 2.0f, hueBarX + hueBarW + 2, hueMarkerY + 2.0f, 2.0, 16.0);
        Color currentColor = Color.getHSBColor(hue, sat, bri);
        int prevY = pickerY + pad + 14 + squareSize + 6;
        int prevH = 14;
        RenderUtils.renderRoundedQuad(context, currentColor, pickerX + pad, prevY, pickerX + pickerW - pad, prevY + prevH, 4.0, 16.0);
        RenderUtils.renderRoundedOutline(context, new Color(255, 255, 255, 40), pickerX + pad, prevY, pickerX + pickerW - pad, prevY + prevH, 4.0, 4.0, 4.0, 4.0, 0.5, 16.0);
        int infoY = prevY + prevH + 6;
        matrices.pushMatrix();
        float infoScale = 0.55f;
        matrices.scale(infoScale, infoScale);
        int infoX = (int)((float)(pickerX + pad) / infoScale);
        int infoY_scaled = (int)((float)infoY / infoScale);
        String rgbStr = String.format("RGB: %d %d %d", currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());
        String hexStr = String.format("#%02X%02X%02X", currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());
        TextRenderer.drawString(rgbStr, context, infoX, infoY_scaled, -5592406, false);
        TextRenderer.drawString(hexStr, context, infoX, infoY_scaled + 10, -2236963, false);
        matrices.popMatrix();
        int btnsY = pickerY + pickerH - 20;
        int btnW = (pickerW - pad * 2 - 6) / 2;
        Color btnColor = this.setting.isRainbow() ? new Color(100, 180, 255, 200) : new Color(60, 60, 70, 200);
        RenderUtils.renderRoundedQuad(context, btnColor, pickerX + pad, btnsY, pickerX + pad + btnW, btnsY + 14, 4.0, 16.0);
        matrices.pushMatrix();
        float btnScale = 0.55f;
        matrices.scale(btnScale, btnScale);
        String rainbowText = this.setting.isRainbow() ? "Rainbow: ON" : "Rainbow";
        int rwX = (int)(((float)(pickerX + pad) + ((float)btnW - (float)TextRenderer.getWidth(rainbowText) * btnScale) / 2.0f) / btnScale);
        int rwY = (int)(((float)(btnsY + 7) - (float)TextRenderer.getFontHeight() * btnScale / 2.0f) / btnScale);
        TextRenderer.drawString(rainbowText, context, rwX, rwY, -1, false);
        matrices.popMatrix();
        int closeX = pickerX + pad + btnW + 6;
        Color closeColor = new Color(60, 60, 70, 200);
        RenderUtils.renderRoundedQuad(context, closeColor, closeX, btnsY, closeX + btnW, btnsY + 14, 4.0, 16.0);
        matrices.pushMatrix();
        matrices.scale(btnScale, btnScale);
        String closeText = "Close";
        int clX = (int)(((float)closeX + ((float)btnW - (float)TextRenderer.getWidth(closeText) * btnScale) / 2.0f) / btnScale);
        int clY = (int)(((float)(btnsY + 7) - (float)TextRenderer.getFontHeight() * btnScale / 2.0f) / btnScale);
        TextRenderer.drawString(closeText, context, clX, clY, -1, false);
        matrices.popMatrix();
        int titleY = pickerY + 4;
        matrices.pushMatrix();
        float titleScale = 0.65f;
        matrices.scale(titleScale, titleScale);
        String title = "Color Picker";
        int tX = (int)(((float)pickerX + ((float)pickerW - (float)TextRenderer.getWidth(title) * titleScale) / 2.0f) / titleScale);
        int tY = (int)(((float)(titleY + 6) - (float)TextRenderer.getFontHeight() * titleScale / 2.0f) / titleScale);
        TextRenderer.drawString(title, context, tX, tY, -3355444, false);
        matrices.popMatrix();
    }

    private void drawHSBSquare(class_332 context, int x, int y, int size, float hue) {
        int strips = 40;
        float stripW = (float)size / (float)strips;
        for (int i = 0; i < strips; ++i) {
            float sat = ((float)i + 0.5f) / (float)strips;
            float sx = (float)x + (float)i * stripW;
            Color topColor = Color.getHSBColor(hue, sat, 1.0f);
            Color bottomColor = Color.getHSBColor(hue, sat, 0.0f);
            RenderUtils.renderRoundedGradientQuad(context, topColor, bottomColor, sx, y, sx + stripW + 1.0f, y + size, 0.0, 0.0, 0.0, 0.0, 8.0);
        }
    }

    private void drawHueBar(class_332 context, int x, int y, int w, int h, float currentHue) {
        int strips = 60;
        float stripH = (float)h / (float)strips;
        for (int i = 0; i < strips; ++i) {
            float hueVal = (float)i / (float)strips;
            float sy = (float)y + (float)i * stripH;
            Color c = Color.getHSBColor(hueVal, 1.0f, 1.0f);
            RenderUtils.renderRoundedQuad(context, c, x, sy, x + w, sy + stripH + 1.0f, 0.0, 0.0, 0.0, 0.0, 4.0);
        }
    }

    public boolean isPickerOpen() {
        return this.pickerOpen;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.pickerOpen) {
            if (this.handlePickerClick(mouseX, mouseY, button)) {
                return true;
            }
            if (!this.isInsidePicker(mouseX, mouseY)) {
                this.pickerOpen = false;
                return true;
            }
            return true;
        }
        if (this.isHovered(mouseX, mouseY)) {
            if (button == 0) {
                this.currentHSB = Color.RGBtoHSB(this.setting.getColor().getRed(), this.setting.getColor().getGreen(), this.setting.getColor().getBlue(), null);
                this.pickerOpen = true;
                return true;
            }
            if (button == 1) {
                this.setting.setRainbow(!this.setting.isRainbow());
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean handlePickerClick(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return false;
        }
        int pickerW = 140;
        int pickerH = 170;
        int pickerX = this.parentX() + this.parentWidth() + 4;
        int pickerY = this.parentY() + this.parentOffset() + this.offset;
        if (pickerX + pickerW > this.parentWidth() + 50) {
            pickerX = this.parentX() - pickerW - 4;
        }
        int pad = 8;
        int squareSize = 100;
        int sqX = pickerX + pad;
        int sqY = pickerY + pad + 14;
        int hueBarX = sqX + squareSize + 6;
        int hueBarW = 12;
        int hueBarH = squareSize;
        if (mouseX >= (double)sqX && mouseX <= (double)(sqX + squareSize) && mouseY >= (double)sqY && mouseY <= (double)(sqY + squareSize)) {
            this.draggingSquare = true;
            this.updateSquare(mouseX, mouseY, sqX, sqY, squareSize);
            return true;
        }
        if (mouseX >= (double)hueBarX && mouseX <= (double)(hueBarX + hueBarW) && mouseY >= (double)sqY && mouseY <= (double)(sqY + hueBarH)) {
            this.draggingHue = true;
            this.updateHue(mouseY, sqY, hueBarH);
            return true;
        }
        int prevY = sqY + squareSize + 6;
        int btnsY = pickerY + pickerH - 20;
        int btnW = (pickerW - pad * 2 - 6) / 2;
        if (mouseY >= (double)btnsY && mouseY <= (double)(btnsY + 14)) {
            if (mouseX >= (double)(pickerX + pad) && mouseX <= (double)(pickerX + pad + btnW)) {
                this.setting.setRainbow(!this.setting.isRainbow());
                return true;
            }
            if (mouseX >= (double)(pickerX + pad + btnW + 6) && mouseX <= (double)(pickerX + pad + btnW + 6 + btnW)) {
                this.pickerOpen = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (button != 0) {
            super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
            return;
        }
        if (!this.pickerOpen) {
            super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
            return;
        }
        int pickerW = 140;
        int pickerH = 170;
        int pickerX = this.parentX() + this.parentWidth() + 4;
        int pickerY = this.parentY() + this.parentOffset() + this.offset;
        if (pickerX + pickerW > this.parentWidth() + 50) {
            pickerX = this.parentX() - pickerW - 4;
        }
        int pad = 8;
        int squareSize = 100;
        int sqX = pickerX + pad;
        int sqY = pickerY + pad + 14;
        int hueBarX = sqX + squareSize + 6;
        int hueBarH = squareSize;
        if (this.draggingSquare) {
            this.updateSquare(mouseX, mouseY, sqX, sqY, squareSize);
        } else if (this.draggingHue) {
            this.updateHue(mouseY, sqY, hueBarH);
        } else {
            super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        this.draggingSquare = false;
        this.draggingHue = false;
        super.mouseReleased(mouseX, mouseY, button);
    }

    private void updateSquare(double mouseX, double mouseY, int sqX, int sqY, int size) {
        float sat = (float)Math.max(0.0, Math.min(1.0, (mouseX - (double)sqX) / (double)size));
        float bri = (float)Math.max(0.0, Math.min(1.0, 1.0 - (mouseY - (double)sqY) / (double)size));
        this.currentHSB[1] = sat;
        this.currentHSB[2] = bri;
        this.setting.setColor(Color.getHSBColor(this.currentHSB[0], sat, bri));
    }

    private void updateHue(double mouseY, int sqY, int hueBarH) {
        float hue;
        this.currentHSB[0] = hue = (float)Math.max(0.0, Math.min(1.0, 1.0 - (mouseY - (double)sqY) / (double)hueBarH));
        this.setting.setColor(Color.getHSBColor(hue, this.currentHSB[1], this.currentHSB[2]));
    }

    private boolean isInsidePicker(double mouseX, double mouseY) {
        int pickerW = 140;
        int pickerH = 170;
        int pickerX = this.parentX() + this.parentWidth() + 4;
        int pickerY = this.parentY() + this.parentOffset() + this.offset;
        if (pickerX + pickerW > this.parentWidth() + 50) {
            pickerX = this.parentX() - pickerW - 4;
        }
        return mouseX >= (double)pickerX && mouseX <= (double)(pickerX + pickerW) && mouseY >= (double)pickerY && mouseY <= (double)(pickerY + pickerH);
    }
}

