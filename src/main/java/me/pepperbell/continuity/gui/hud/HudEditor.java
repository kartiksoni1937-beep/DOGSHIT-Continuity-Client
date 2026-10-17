/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.hud;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.HudModule;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.utils.ColorUtils;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_11909;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

public class HudEditor
extends class_437 {
    private HudModule draggingModule = null;
    private int dragOffsetX = 0;
    private int dragOffsetY = 0;
    private boolean pickerOpen = false;
    private int pickerX = 0;
    private int pickerY = 0;
    private final int PICKER_WIDTH = 100;
    private final int ITEM_HEIGHT = 16;
    private static final int GRID_SIZE = 10;

    public HudEditor() {
        super((class_2561)class_2561.method_43473());
    }

    private List<HudModule> getActiveHudModules() {
        ArrayList<HudModule> active = new ArrayList<HudModule>();
        for (Module m : Sweto.INSTANCE.getModuleManager().getModules()) {
            if (!(m instanceof HudModule) || !m.isEnabled()) continue;
            active.add((HudModule)m);
        }
        return active;
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        int screenW = Sweto.mc.method_22683().method_4486();
        int screenH = Sweto.mc.method_22683().method_4502();
        context.method_25294(0, 0, screenW, screenH, new Color(0, 0, 0, 80).getRGB());
        int gridColor = new Color(255, 255, 255, 15).getRGB();
        for (int gx = 0; gx < screenW; gx += 10) {
            context.method_25294(gx, 0, gx + 1, screenH, gridColor);
        }
        for (int gy = 0; gy < screenH; gy += 10) {
            context.method_25294(0, gy, screenW, gy + 1, gridColor);
        }
        int crossColor = new Color(180, 180, 180, 40).getRGB();
        context.method_25294(screenW / 2, 0, screenW / 2 + 1, screenH, crossColor);
        context.method_25294(0, screenH / 2, screenW, screenH / 2 + 1, crossColor);
        List<HudModule> modules = this.getActiveHudModules();
        for (HudModule module : modules) {
            boolean hovered;
            int x = module.getX();
            int y = module.getY();
            int w = module.getWidth();
            int h = module.getHeight();
            boolean bl = hovered = mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
            if (hovered || module == this.draggingModule) {
                RenderUtils.renderRoundedOutline(context, new Color(180, 180, 180, 200), x - 2, y - 2, x + w + 2, y + h + 2, 4.0, 4.0, 4.0, 4.0, 1.0, 8.0);
                RenderUtils.renderRoundedQuad(context, new Color(180, 180, 180, 30), x - 2, y - 2, x + w + 2, y + h + 2, 4.0, 8.0);
                continue;
            }
            RenderUtils.renderRoundedOutline(context, new Color(255, 255, 255, 100), x - 2, y - 2, x + w + 2, y + h + 2, 4.0, 4.0, 4.0, 4.0, 1.0, 8.0);
        }
        super.method_25394(context, mouseX, mouseY, delta);
        super.method_25394(context, mouseX, mouseY, delta);
        if (this.draggingModule != null) {
            this.drawGuides(context, screenW, screenH, modules);
        }
        if (this.pickerOpen) {
            this.renderModulePicker(context, mouseX, mouseY);
        }
    }

    private void drawGuides(class_332 context, int screenW, int screenH, List<HudModule> modules) {
        int x = this.draggingModule.getX();
        int y = this.draggingModule.getY();
        int w = this.draggingModule.getWidth();
        int h = this.draggingModule.getHeight();
        int centerX = x + w / 2;
        int centerY = y + h / 2;
        int guideColor = ColorUtils.getMainColor().getRGB();
        if (Math.abs(centerX - screenW / 2) < 5) {
            context.method_25294(screenW / 2, 0, screenW / 2 + 1, screenH, guideColor);
        }
        if (Math.abs(centerY - screenH / 2) < 5) {
            context.method_25294(0, screenH / 2, screenW, screenH / 2 + 1, guideColor);
        }
        for (HudModule other : modules) {
            if (other == this.draggingModule) continue;
            if (Math.abs(x - other.getX()) < 5) {
                context.method_25294(other.getX(), 0, other.getX() + 1, screenH, guideColor);
            }
            if (Math.abs(x + w - (other.getX() + other.getWidth())) < 5) {
                context.method_25294(other.getX() + other.getWidth(), 0, other.getX() + other.getWidth() + 1, screenH, guideColor);
            }
            if (Math.abs(y - other.getY()) < 5) {
                context.method_25294(0, other.getY(), screenW, other.getY() + 1, guideColor);
            }
            if (Math.abs(y + h - (other.getY() + other.getHeight())) >= 5) continue;
            context.method_25294(0, other.getY() + other.getHeight(), screenW, other.getY() + other.getHeight() + 1, guideColor);
        }
    }

    private void renderModulePicker(class_332 context, int mouseX, int mouseY) {
        List<Module> hudModules = Sweto.INSTANCE.getModuleManager().getModulesInCategory(Category.HUD);
        int totalHeight = hudModules.size() * 16;
        RenderUtils.renderRoundedQuad(context, new Color(20, 20, 25, 240), this.pickerX, this.pickerY, this.pickerX + 100, this.pickerY + totalHeight, 4.0, 8.0);
        RenderUtils.renderRoundedOutline(context, new Color(255, 255, 255, 40), this.pickerX, this.pickerY, this.pickerX + 100, this.pickerY + totalHeight, 4.0, 4.0, 4.0, 4.0, 1.0, 8.0);
        int currentY = this.pickerY;
        for (Module module : hudModules) {
            boolean hovered;
            boolean bl = hovered = mouseX >= this.pickerX && mouseX <= this.pickerX + 100 && mouseY >= currentY && mouseY <= currentY + 16;
            if (hovered) {
                context.method_25294(this.pickerX, currentY, this.pickerX + 100, currentY + 16, new Color(255, 255, 255, 20).getRGB());
            }
            int color = module.isEnabled() ? new Color(180, 180, 180).getRGB() : -10066330;
            TextRenderer.drawString(module.getName().toString(), context, this.pickerX + 6, currentY + 4, color, false);
            currentY += 16;
        }
    }

    public boolean method_25402(class_11909 click, boolean doubleClick) {
        int mouseX = (int)click.comp_4798();
        int mouseY = (int)click.comp_4799();
        int button = click.method_74245();
        if (this.pickerOpen) {
            List<Module> hudModules = Sweto.INSTANCE.getModuleManager().getModulesInCategory(Category.HUD);
            int currentY = this.pickerY;
            for (Module module : hudModules) {
                if (mouseX >= this.pickerX && mouseX <= this.pickerX + 100 && mouseY >= currentY && mouseY <= currentY + 16) {
                    module.toggle();
                    return true;
                }
                currentY += 16;
            }
            this.pickerOpen = false;
            return true;
        }
        if (button == 1) {
            int totalHeight;
            this.pickerOpen = true;
            this.pickerX = mouseX;
            this.pickerY = mouseY;
            if (this.pickerX + 100 > Sweto.mc.method_22683().method_4486()) {
                this.pickerX -= 100;
            }
            if (this.pickerY + (totalHeight = Sweto.INSTANCE.getModuleManager().getModulesInCategory(Category.HUD).size() * 16) > Sweto.mc.method_22683().method_4502()) {
                this.pickerY -= totalHeight;
            }
            return true;
        }
        if (button == 0) {
            List<HudModule> modules = this.getActiveHudModules();
            for (HudModule module : modules) {
                int x = module.getX();
                int y = module.getY();
                int w = module.getWidth();
                int h = module.getHeight();
                if (mouseX < x || mouseX > x + w || mouseY < y || mouseY > y + h) continue;
                this.draggingModule = module;
                this.dragOffsetX = mouseX - x;
                this.dragOffsetY = mouseY - y;
                return true;
            }
        }
        return super.method_25402(click, doubleClick);
    }

    private int snapToGrid(int value) {
        return Math.round((float)value / 10.0f) * 10;
    }

    public boolean method_25403(class_11909 click, double deltaX, double deltaY) {
        if (this.draggingModule != null) {
            int screenW = Sweto.mc.method_22683().method_4486();
            int screenH = Sweto.mc.method_22683().method_4502();
            int rawX = (int)click.comp_4798() - this.dragOffsetX;
            int rawY = (int)click.comp_4799() - this.dragOffsetY;
            int snappedX = rawX;
            int snappedY = rawY;
            if (Math.abs(snappedX + this.draggingModule.getWidth() / 2 - screenW / 2) < 5) {
                snappedX = screenW / 2 - this.draggingModule.getWidth() / 2;
            }
            if (Math.abs(snappedY + this.draggingModule.getHeight() / 2 - screenH / 2) < 5) {
                snappedY = screenH / 2 - this.draggingModule.getHeight() / 2;
            }
            for (HudModule other : this.getActiveHudModules()) {
                if (other == this.draggingModule) continue;
                if (Math.abs(snappedX - other.getX()) < 5) {
                    snappedX = other.getX();
                }
                if (Math.abs(snappedX + this.draggingModule.getWidth() - (other.getX() + other.getWidth())) < 5) {
                    snappedX = other.getX() + other.getWidth() - this.draggingModule.getWidth();
                }
                if (Math.abs(snappedY - other.getY()) < 5) {
                    snappedY = other.getY();
                }
                if (Math.abs(snappedY + this.draggingModule.getHeight() - (other.getY() + other.getHeight())) >= 5) continue;
                snappedY = other.getY() + other.getHeight() - this.draggingModule.getHeight();
            }
            this.draggingModule.setX(snappedX);
            this.draggingModule.setY(snappedY);
            return true;
        }
        return super.method_25403(click, deltaX, deltaY);
    }

    public boolean method_25406(class_11909 click) {
        if (click.method_74245() == 0 && this.draggingModule != null) {
            this.draggingModule = null;
            return true;
        }
        return super.method_25406(click);
    }

    public void method_25419() {
        super.method_25419();
    }
}

