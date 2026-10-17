/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.gui.ClickGui;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.modules.client.C11ck9U1;
import me.pepperbell.continuity.utils.MathUtils;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_310;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;

public final class Window {
    public List<ModuleButton> moduleButtons = new ArrayList<ModuleButton>();
    protected class_310 mc = Sweto.mc;
    private double x;
    private double y;
    private double renderX;
    private double renderY;
    private double renderTilt;
    private final int width;
    private final Category category;
    public boolean dragging;
    double dragX;
    double dragY;
    private int currentHeight;
    private List<String> subCategories = new ArrayList<String>();
    private int subCategoryIndex = 0;
    private final int subHeaderHeight = 20;
    private final Color normalBackgroundColor = new Color(40, 0, 80, 70);
    private final Color draggingBackgroundColor = new Color(40, 0, 80, 120);
    private final int headerHeight;
    private double renderHeight;

    public Window(int x, int y, int width, int height, Category category) {
        this.x = x;
        this.y = y;
        this.renderX = x;
        this.renderY = y;
        this.renderTilt = 0.0;
        this.width = width;
        this.headerHeight = height;
        this.renderHeight = height;
        this.dragging = false;
        this.category = category;
        this.currentHeight = height;
        List<Module> modules = Sweto.INSTANCE.getModuleManager().getModulesInCategory(category);
        for (Module module : modules) {
            String sub = module.getSubCategory();
            if (this.subCategories.contains(sub)) continue;
            this.subCategories.add(sub);
        }
        if (this.subCategories.isEmpty()) {
            this.subCategories.add("Main");
        }
        for (Module module : modules) {
            this.moduleButtons.add(new ModuleButton(this, module, 0));
        }
        this.updateHeight();
    }

    private List<ModuleButton> getVisibleButtons() {
        String activeSub = this.subCategories.get(this.subCategoryIndex);
        return this.moduleButtons.stream().filter(b -> b.module.getSubCategory().equals(activeSub)).toList();
    }

    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        double smoothness = 0.25;
        this.renderX = MathUtils.goodLerp(smoothness, this.renderX, this.x);
        this.renderY = MathUtils.goodLerp(smoothness, this.renderY, this.y);
        double velocityX = this.x - this.renderX;
        double targetTilt = MathUtils.clamp(velocityX * 1.5, -12.0, 12.0);
        this.renderTilt = MathUtils.goodLerp(0.15, this.renderTilt, targetTilt);
        if (Math.abs(this.renderX - this.x) < 0.05) {
            this.renderX = this.x;
        }
        if (Math.abs(this.renderY - this.y) < 0.05) {
            this.renderY = this.y;
        }
        if (Math.abs(this.renderTilt) < 0.05) {
            this.renderTilt = 0.0;
        }
        this.renderHeight += ((double)this.currentHeight - this.renderHeight) * 0.2;
        int alpha = (int)Sweto.INSTANCE.getModuleManager().getModule(C11ck9U1.class).getAnimatedAlpha();
        Matrix3x2fStack matrices = context.method_51448();
        matrices.pushMatrix();
        matrices.translate((float)this.renderX, (float)this.renderY);
        if (this.renderTilt != 0.0) {
            float pivotX = (float)this.width / 2.0f;
            float pivotY = 0.0f;
            matrices.translate(pivotX, pivotY);
            matrices.rotate((float)Math.toRadians(this.renderTilt));
            matrices.translate(-pivotX, -pivotY);
        }
        int localMouseX = (int)((double)mouseX - this.renderX);
        int localMouseY = (int)((double)mouseY - this.renderY);
        float cornerRadius = 12.0f;
        boolean isLiquid = C11ck9U1.shaderMode.getMode() == C11ck9U1.ShaderMode.LIQUID;
        Color panelBg = isLiquid ? new Color(18, 18, 22, 80) : new Color(12, 12, 15, 200);
        RenderUtils.renderRoundedQuad(context, panelBg, 0.0, 0.0, this.width, (float)this.renderHeight, cornerRadius, 32.0);
        RenderUtils.renderRoundedQuad(context, isLiquid ? new Color(30, 30, 35, 100) : new Color(28, 28, 32, 220), 0.0, 0.0, this.width, this.headerHeight, cornerRadius, cornerRadius, 0.0, 0.0, 32.0);
        RenderUtils.renderRoundedQuad(context, isLiquid ? new Color(18, 18, 22, 60) : new Color(14, 14, 17, 200), 0.0, this.headerHeight, this.width, (float)this.renderHeight, 0.0, 0.0, cornerRadius, cornerRadius, 32.0);
        RenderUtils.renderRoundedQuad(context, isLiquid ? new Color(255, 255, 255, 60) : new Color(255, 255, 255, 30), 4.0, this.headerHeight, (double)this.width - 4.0, (double)this.headerHeight + 1.0, 0.5, 8.0);
        matrices.pushMatrix();
        float scaleFactor = 1.1f;
        matrices.scale(scaleFactor, scaleFactor);
        String headerText = this.getFormattedCategoryName();
        int headerWidth = TextRenderer.getWidth(headerText);
        float scaledCenterX = (float)((double)this.width / 2.0 / (double)scaleFactor);
        String nameText = this.getFormattedCategoryName();
        float nameX = (float)((double)this.width / 2.0 / (double)scaleFactor) - (float)TextRenderer.getWidth(nameText) / 2.0f;
        float scaledCenterY = (float)((double)this.headerHeight / 2.0 / (double)scaleFactor);
        float scaledTextY = scaledCenterY - (float)TextRenderer.getFontHeight() / 2.0f;
        Color gs = C11ck9U1.gradientStart.getColor();
        Color ge = C11ck9U1.gradientEnd.getColor();
        Color headerStart = new Color(gs.getRed(), gs.getGreen(), gs.getBlue(), alpha);
        Color headerEnd = new Color(ge.getRed(), ge.getGreen(), ge.getBlue(), alpha);
        TextRenderer.drawGradientGlowString(nameText, context, nameX, scaledTextY, headerStart, headerEnd);
        matrices.popMatrix();
        if (this.subCategories.size() > 1) {
            float subBarY = this.headerHeight;
            float subBarCenterY = subBarY + 10.0f;
            RenderUtils.renderRoundedQuad(context, new Color(25, 25, 28, 150), 4.0, (double)subBarY + 2.0, (double)this.width - 4.0, (double)subBarY + 20.0 - 2.0, 4.0, 16.0);
            String currentSubName = this.subCategories.get(this.subCategoryIndex);
            float subScale = 0.8f;
            int subWidth = (int)((float)TextRenderer.getWidth(currentSubName) * subScale);
            TextRenderer.drawString("<", context, 12.0f, subBarCenterY - (float)TextRenderer.getFontHeight() / 2.0f, -5592406, false);
            TextRenderer.drawString(">", context, this.width - 12 - TextRenderer.getWidth(">"), subBarCenterY - (float)TextRenderer.getFontHeight() / 2.0f, -5592406, false);
            matrices.pushMatrix();
            matrices.scale(subScale, subScale);
            float scaledTitleX = ((float)this.width / 2.0f - (float)subWidth / 2.0f) / subScale;
            float scaledTitleY = (subBarCenterY - (float)TextRenderer.getFontHeight() * subScale / 2.0f) / subScale;
            TextRenderer.drawString(currentSubName, context, scaledTitleX, scaledTitleY, -4473925, false);
            matrices.popMatrix();
            RenderUtils.renderRoundedQuad(context, new Color(255, 255, 255, 20), 8.0, (double)subBarY + 20.0, (double)this.width - 8.0, (double)subBarY + 20.0 + 1.0, 0.5, 8.0);
        }
        List<ModuleButton> visibleButtons = this.getVisibleButtons();
        for (int i = 0; i < visibleButtons.size(); ++i) {
            visibleButtons.get(i).render(context, localMouseX, localMouseY, delta, i == visibleButtons.size() - 1);
        }
        matrices.popMatrix();
    }

    private String getFormattedCategoryName() {
        String name = this.category.name();
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int effectiveSubHeight;
        if (mouseX > this.renderX && mouseX < this.renderX + (double)this.width && mouseY > this.renderY && mouseY < this.renderY + (double)this.headerHeight && button == 0) {
            this.dragging = true;
            this.dragX = mouseX - this.x;
            this.dragY = mouseY - this.y;
            return true;
        }
        int n = effectiveSubHeight = this.subCategories.size() > 1 ? 20 : 0;
        if (effectiveSubHeight > 0 && mouseX > this.renderX && mouseX < this.renderX + (double)this.width && mouseY > this.renderY + (double)this.headerHeight && mouseY < this.renderY + (double)this.headerHeight + (double)effectiveSubHeight && button == 0) {
            float localX = (float)(mouseX - this.renderX);
            if (localX < (float)this.width / 3.0f) {
                --this.subCategoryIndex;
                if (this.subCategoryIndex < 0) {
                    this.subCategoryIndex = this.subCategories.size() - 1;
                }
                ClickGui.saveSubCategoryIndex(this.category, this.subCategoryIndex);
                this.updateHeight();
            } else if (localX > (float)this.width * 2.0f / 3.0f) {
                ++this.subCategoryIndex;
                if (this.subCategoryIndex >= this.subCategories.size()) {
                    this.subCategoryIndex = 0;
                }
                ClickGui.saveSubCategoryIndex(this.category, this.subCategoryIndex);
                this.updateHeight();
            }
            return true;
        }
        for (ModuleButton moduleButton : this.getVisibleButtons()) {
            if (!moduleButton.mouseClicked(mouseX, mouseY, button)) continue;
            return true;
        }
        return false;
    }

    public void mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        for (ModuleButton moduleButton : this.moduleButtons) {
            moduleButton.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && this.dragging) {
            this.dragging = false;
        }
        for (ModuleButton moduleButton : this.moduleButtons) {
            moduleButton.mouseReleased(mouseX, mouseY, button);
        }
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        for (ModuleButton button1 : this.getVisibleButtons()) {
            if (!button1.mouseScrolled(mouseX, mouseY, amount)) continue;
            return true;
        }
        return false;
    }

    public boolean charTyped(char chr) {
        for (ModuleButton button1 : this.getVisibleButtons()) {
            if (!button1.charTyped(chr)) continue;
            return true;
        }
        return false;
    }

    public boolean keyPressed(int key) {
        for (ModuleButton button1 : this.getVisibleButtons()) {
            if (!button1.keyPressed(key)) continue;
            return true;
        }
        return false;
    }

    public void updateHeight() {
        int effectiveSubHeight = this.subCategories.size() > 1 ? 20 : 0;
        int calculatedHeight = this.headerHeight + effectiveSubHeight;
        List<ModuleButton> visible = this.getVisibleButtons();
        for (ModuleButton moduleButton : visible) {
            calculatedHeight += 26;
            if (!moduleButton.extended) continue;
            calculatedHeight += moduleButton.settingOffset;
        }
        this.currentHeight = calculatedHeight;
        this.updateModuleButtonOffsets();
    }

    public void updateModuleButtonOffsets() {
        int effectiveSubHeight = this.subCategories.size() > 1 ? 20 : 0;
        int currentOffset = this.headerHeight + effectiveSubHeight;
        List<ModuleButton> visible = this.getVisibleButtons();
        for (ModuleButton button : this.moduleButtons) {
            button.setOffset(-1000);
        }
        for (ModuleButton moduleButton : visible) {
            moduleButton.setOffset(currentOffset);
            moduleButton.updateRenderableSettingOffsets();
            currentOffset += 26;
            if (!moduleButton.extended) continue;
            currentOffset += moduleButton.settingOffset;
        }
    }

    public void setSubCategoryIndex(int index) {
        if (index >= 0 && index < this.subCategories.size()) {
            this.subCategoryIndex = index;
            this.updateHeight();
        }
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getX() {
        return (int)this.renderX;
    }

    public int getY() {
        return (int)this.renderY;
    }

    public int getTargetX() {
        return (int)this.x;
    }

    public int getTargetY() {
        return (int)this.y;
    }

    public double getRenderX() {
        return this.renderX;
    }

    public double getRenderY() {
        return this.renderY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.currentHeight;
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > this.renderX && mouseX < this.renderX + (double)this.width && mouseY > this.renderY && mouseY < this.renderY + (double)this.currentHeight;
    }

    public void updatePosition(double mouseX, double mouseY) {
        if (this.dragging) {
            this.x = mouseX - this.dragX;
            this.y = mouseY - this.dragY;
        }
    }
}

