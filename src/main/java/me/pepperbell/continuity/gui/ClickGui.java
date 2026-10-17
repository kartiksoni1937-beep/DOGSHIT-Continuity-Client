/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.HudListener;
import me.pepperbell.continuity.gui.Window;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.gui.components.settings.ColorBox;
import me.pepperbell.continuity.gui.components.settings.KeybindBox;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.modules.client.C11ck9U1;
import me.pepperbell.continuity.renderer.sdf.KawasePipeline;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_11905;
import net.minecraft.class_11908;
import net.minecraft.class_11909;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

public final class ClickGui
extends class_437 {
    List<Window> windows = new ArrayList<Window>();
    private float renderScale = 1.0f;
    private static final Map<Category, Integer> lastSubCategoryIndex = new HashMap<Category, Integer>();

    static void saveSubCategoryIndex(Category category, int index) {
        lastSubCategoryIndex.put(category, index);
    }

    public ClickGui() {
        super((class_2561)class_2561.method_43473());
    }

    protected void method_25426() {
        int startX;
        super.method_25426();
        this.windows.clear();
        List<Category> visibleCategories = Arrays.stream(Category.values()).filter(c -> c != Category.HIDDEN && c != Category.HUD).toList();
        int numCategories = visibleCategories.size();
        float scaleFactor = Sweto.mc.method_22683().method_4495();
        float targetScale = Math.max(1.0f, scaleFactor * 0.85f);
        this.renderScale = targetScale / scaleFactor;
        int windowWidth = 150;
        int spacing = 10;
        int totalWidth = numCategories * (windowWidth + spacing) - spacing;
        float logicalWidth = (float)Sweto.mc.method_22683().method_4486() / this.renderScale;
        int offsetX = startX = (int)((logicalWidth - (float)totalWidth) / 2.0f);
        for (Category category : visibleCategories) {
            Window w = new Window(offsetX, (int)(60.0f / this.renderScale), windowWidth, 30, category);
            Integer saved = lastSubCategoryIndex.get((Object)category);
            if (saved != null) {
                w.setSubCategoryIndex(saved);
            }
            this.windows.add(w);
            offsetX += windowWidth + spacing;
        }
    }

    public void open() {
        Sweto.mc.method_1507((class_437)this);
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        double unscaledMouseX = (double)mouseX / (double)this.renderScale;
        double unscaledMouseY = (double)mouseY / (double)this.renderScale;
        RenderUtils.unscaledProjection(context, 1.0f);
        float scaleFactor = Sweto.mc.method_22683().method_4495();
        context.method_51448().pushMatrix();
        context.method_51448().scale(this.renderScale * scaleFactor, this.renderScale * scaleFactor);
        if (C11ck9U1.background.getValue()) {
            float screenW = (float)Sweto.mc.method_22683().method_4486() / this.renderScale;
            float screenH = (float)Sweto.mc.method_22683().method_4502() / this.renderScale;
            RenderUtils.renderRoundedQuad(context, new Color(0, 0, 0, 160), 0.0, 0.0, screenW, screenH, 0.0, 0.0);
        }
        KawasePipeline.beginFrame();
        String currentTooltip = null;
        for (Window window : this.windows) {
            window.updatePosition(unscaledMouseX, unscaledMouseY);
            window.render(context, (int)unscaledMouseX, (int)unscaledMouseY, delta);
            for (ModuleButton button : window.moduleButtons) {
                boolean hoveringModule = button.isHovered(unscaledMouseX - (double)window.getX(), unscaledMouseY - (double)window.getY());
                boolean hoveringSettings = false;
                if (button.extended) {
                    for (RenderableSetting setting : button.settings) {
                        if (!setting.isHovered(unscaledMouseX - (double)window.getX(), unscaledMouseY - (double)window.getY())) continue;
                        hoveringSettings = true;
                        if (setting.setting.getDescription() == null) break;
                        currentTooltip = setting.setting.getDescription().toString();
                        break;
                    }
                }
                if (!hoveringModule && !hoveringSettings || currentTooltip != null || button.module.getDescription() == null) continue;
                currentTooltip = button.module.getDescription().toString();
            }
        }
        if (currentTooltip != null && !currentTooltip.isEmpty()) {
            this.drawTooltip(context, currentTooltip, (int)unscaledMouseX, (int)unscaledMouseY);
        }
        context.method_51448().popMatrix();
        RenderUtils.scaledProjection(context);
        EventManager.fire(new HudListener.HudEvent(context, delta));
        super.method_25394(context, mouseX, mouseY, delta);
    }

    private void drawTooltip(class_332 context, String text, int x, int y) {
        if (text.isEmpty()) {
            return;
        }
        float scale = 0.7f;
        int padding = 6;
        int textWidth = (int)((float)TextRenderer.getWidth(text) * scale);
        int textHeight = (int)((float)TextRenderer.getFontHeight() * scale);
        float tooltipW = textWidth + padding * 2;
        float tooltipH = textHeight + padding * 2;
        int rectX = x + 10;
        int rectY = y + 10;
        float screenW = (float)Sweto.mc.method_22683().method_4486() / this.renderScale;
        float screenH = (float)Sweto.mc.method_22683().method_4502() / this.renderScale;
        if ((float)rectX + tooltipW > screenW) {
            rectX = (int)((float)x - tooltipW - 10.0f);
        }
        if ((float)rectY + tooltipH > screenH) {
            rectY = (int)((float)y - tooltipH - 2.0f);
        }
        if (rectY < 0) {
            rectY = 2;
        }
        if (rectX < 0) {
            rectX = 2;
        }
        context.method_51448().pushMatrix();
        RenderUtils.renderRoundedQuad(context, new Color(15, 15, 20, 230), rectX, rectY, (float)rectX + tooltipW, (float)rectY + tooltipH, 4.0, 4.0, 4.0, 4.0, 32.0);
        RenderUtils.renderRoundedOutline(context, new Color(180, 180, 180, 120), rectX, rectY, (float)rectX + tooltipW, (float)rectY + tooltipH, 4.0, 4.0, 4.0, 4.0, 0.5, 32.0);
        context.method_51448().pushMatrix();
        context.method_51448().translate((float)(rectX + padding), (float)(rectY + padding));
        context.method_51448().scale(scale, scale);
        TextRenderer.drawString(text, context, 0.0f, 0.0f, -1, false);
        context.method_51448().popMatrix();
        context.method_51448().popMatrix();
    }

    public boolean method_25402(class_11909 click, boolean doubleClick) {
        double unscaledX = click.comp_4798() / (double)this.renderScale;
        double unscaledY = click.comp_4799() / (double)this.renderScale;
        int button = click.method_74245();
        if (button >= 2) {
            for (Window window : this.windows) {
                for (ModuleButton moduleButton : window.moduleButtons) {
                    if (!moduleButton.extended) continue;
                    for (RenderableSetting setting : moduleButton.settings) {
                        if (!(setting instanceof KeybindBox)) continue;
                        KeybindBox keybindBox = (KeybindBox)setting;
                        if (!keybindBox.setting.isListening()) continue;
                        keybindBox.onMousePress(button);
                        return true;
                    }
                }
            }
        }
        for (Window window : this.windows) {
            for (ModuleButton mb : window.moduleButtons) {
                if (!mb.extended) continue;
                for (RenderableSetting rs : mb.settings) {
                    double localY;
                    double localX;
                    ColorBox cb;
                    if (!(rs instanceof ColorBox) || !(cb = (ColorBox)rs).isPickerOpen() || !cb.mouseClicked(localX = unscaledX - (double)window.getX(), localY = unscaledY - (double)window.getY(), button)) continue;
                    return true;
                }
            }
        }
        for (Window window : this.windows) {
            if (!window.mouseClicked(unscaledX, unscaledY, button)) continue;
            return true;
        }
        return super.method_25402(click, doubleClick);
    }

    public boolean method_25403(class_11909 click, double deltaX, double deltaY) {
        double mouseX = click.comp_4798() / (double)this.renderScale;
        double mouseY = click.comp_4799() / (double)this.renderScale;
        double unscaledDeltaX = deltaX / (double)this.renderScale;
        double unscaledDeltaY = deltaY / (double)this.renderScale;
        for (Window window : this.windows) {
            window.mouseDragged(mouseX, mouseY, 0, unscaledDeltaX, unscaledDeltaY);
        }
        return super.method_25403(click, deltaX, deltaY);
    }

    public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        double unscaledMouseX = mouseX / (double)this.renderScale;
        double unscaledMouseY = mouseY / (double)this.renderScale;
        for (Window window : this.windows) {
            if (!window.mouseScrolled(unscaledMouseX, unscaledMouseY, verticalAmount)) continue;
            return true;
        }
        int scroll = (int)(verticalAmount * 12.0);
        if (scroll != 0) {
            for (Window window : this.windows) {
                window.setY(window.getTargetY() + scroll);
            }
            return true;
        }
        return super.method_25401(unscaledMouseX, unscaledMouseY, horizontalAmount, verticalAmount);
    }

    public boolean method_25400(class_11905 input) {
        for (Window window : this.windows) {
            if (!window.charTyped((char)input.comp_4793())) continue;
            return true;
        }
        return super.method_25400(input);
    }

    public boolean method_25404(class_11908 input) {
        if (this.windows.stream().anyMatch(w -> w.keyPressed(input.comp_4795()))) {
            return true;
        }
        if (input.comp_4795() == 256) {
            this.method_25419();
            return true;
        }
        return super.method_25404(input);
    }

    public void method_25419() {
        Sweto.INSTANCE.getModuleManager().getModule(C11ck9U1.class).setEnabled(false);
        super.method_25419();
    }

    public boolean method_25406(class_11909 click) {
        double unscaledX = click.comp_4798() / (double)this.renderScale;
        double unscaledY = click.comp_4799() / (double)this.renderScale;
        for (Window window : this.windows) {
            window.mouseReleased(unscaledX, unscaledY, 0);
        }
        return super.method_25406(click);
    }
}

