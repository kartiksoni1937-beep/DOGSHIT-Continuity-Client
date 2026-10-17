/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components.settings;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.module.setting.Setting;
import net.minecraft.class_310;
import net.minecraft.class_332;

public abstract class RenderableSetting {
    public class_310 mc = Sweto.mc;
    public ModuleButton parent;
    public Setting setting;
    public int offset;

    public RenderableSetting(ModuleButton parent, Setting setting, int offset) {
        this.parent = parent;
        this.setting = setting;
        this.offset = offset;
    }

    public int parentX() {
        return 0;
    }

    public int parentY() {
        return 0;
    }

    public int parentWidth() {
        return this.parent.parent.getWidth();
    }

    public int parentHeight() {
        return this.height();
    }

    public int height() {
        return 30;
    }

    public int parentOffset() {
        return (int)this.parent.renderY;
    }

    public void render(class_332 context, int mouseX, int mouseY, float delta) {
    }

    public boolean isHovered(double mouseX, double mouseY) {
        int localTotalY = this.parentOffset() + this.offset;
        return mouseX > 0.0 && mouseX < (double)this.parentWidth() && mouseY > (double)localTotalY && mouseY < (double)(localTotalY + this.height());
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
    }

    public void mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return false;
    }

    public boolean charTyped(char chr) {
        return false;
    }

    public boolean keyPressed(int key) {
        return false;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

