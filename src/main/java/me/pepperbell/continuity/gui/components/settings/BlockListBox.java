/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components.settings;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.module.setting.BlockListSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_156;
import net.minecraft.class_1747;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_7923;
import org.joml.Matrix3x2fStack;

public final class BlockListBox
extends RenderableSetting {
    private final BlockListSetting setting;
    private String searchQuery = "";
    private boolean focused;
    private int scrollOffset = 0;
    private List<class_2248> filteredBlocks = new ArrayList<class_2248>();
    private final List<class_2248> allBlocks = new ArrayList<class_2248>();
    private long lastCursorBlink;
    private boolean cursorVisible;

    public BlockListBox(ModuleButton parent, Setting<?> setting, int offset) {
        super(parent, setting, offset);
        this.setting = (BlockListSetting)setting;
        for (class_2248 block : class_7923.field_41175) {
            if (!(block.method_8389() instanceof class_1747)) continue;
            this.allBlocks.add(block);
        }
        this.updateFilter();
    }

    @Override
    public int height() {
        return 160;
    }

    private void updateFilter() {
        if (this.searchQuery.isEmpty()) {
            this.filteredBlocks = new ArrayList<class_2248>(this.allBlocks);
        } else {
            String query = this.searchQuery.toLowerCase();
            this.filteredBlocks = this.allBlocks.stream().filter(b -> {
                String id = class_7923.field_41175.method_10221(b).method_12832().toLowerCase();
                String name = b.method_9518().getString().toLowerCase();
                return id.contains(query) || name.contains(query);
            }).collect(Collectors.toList());
        }
        this.scrollOffset = 0;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        int index;
        super.render(context, mouseX, mouseY, delta);
        Matrix3x2fStack matrices = context.method_51448();
        int x = this.parentX() + 8;
        int y = this.parentY() + this.parentOffset() + this.offset;
        int width = this.parentWidth() - 16;
        matrices.pushMatrix();
        float labelScale = 0.7f;
        matrices.scale(labelScale, labelScale);
        TextRenderer.drawString(this.setting.getName(), context, (int)((float)x / labelScale), (int)((float)(y + 4) / labelScale), -4473925, false);
        matrices.popMatrix();
        int searchY = y + 15;
        int searchHeight = 14;
        RenderUtils.renderRoundedQuad(context, new Color(30, 30, 30, 200), x, searchY, x + width, searchY + searchHeight, 2.0, 10.0);
        Color borderColor = this.focused ? new Color(180, 180, 180) : new Color(60, 60, 60);
        RenderUtils.renderRoundedOutline(context, borderColor, x, searchY, x + width, searchY + searchHeight, 2.0, 2.0, 2.0, 2.0, 0.5, 10.0);
        if (this.focused) {
            if (class_156.method_658() - this.lastCursorBlink > 500L) {
                this.cursorVisible = !this.cursorVisible;
                this.lastCursorBlink = class_156.method_658();
            }
        } else {
            this.cursorVisible = false;
        }
        String displayText = this.searchQuery.isEmpty() && !this.focused ? "Search blocks..." : this.searchQuery;
        int textColor = this.searchQuery.isEmpty() && !this.focused ? -7829368 : -1;
        matrices.pushMatrix();
        float textScale = 0.75f;
        matrices.scale(textScale, textScale);
        TextRenderer.drawString(displayText, context, (int)((float)(x + 4) / textScale), (int)(((double)searchY + 3.5) / (double)textScale), textColor, false);
        if (this.cursorVisible) {
            float cursorX = (float)(x + 4) + (float)TextRenderer.getWidth(this.searchQuery) * textScale + 1.0f;
            RenderUtils.drawQuad(context, cursorX, searchY + 3, cursorX + 0.5f, searchY + searchHeight - 3, -1);
        }
        matrices.popMatrix();
        int listY = searchY + searchHeight + 5;
        int listHeight = this.height() - (listY - y) - 5;
        RenderUtils.drawQuad(context, x, listY, x + width, listY + listHeight, new Color(20, 20, 20, 150).getRGB());
        int itemHeight = 16;
        int visibleCount = listHeight / itemHeight;
        for (int i = 0; i < visibleCount && (index = i + this.scrollOffset) < this.filteredBlocks.size(); ++i) {
            class_2248 block = this.filteredBlocks.get(index);
            class_2960 id = class_7923.field_41175.method_10221(block);
            String name = block.method_9518().getString();
            boolean selected = this.setting.contains(id.toString()) || this.setting.contains(id.method_12832());
            int itemY = listY + i * itemHeight;
            if (mouseX > x && mouseX < x + width && mouseY > itemY && mouseY < itemY + itemHeight) {
                RenderUtils.drawQuad(context, x, itemY, x + width, itemY + itemHeight, new Color(255, 255, 255, 20).getRGB());
            }
            context.method_51427(block.method_8389().method_7854(), x + 2, itemY);
            matrices.pushMatrix();
            float nameScale = 0.7f;
            matrices.scale(nameScale, nameScale);
            int maxNameW = (int)((float)(width - 35) / nameScale);
            Object displayName = TextRenderer.trim(name, maxNameW);
            if (TextRenderer.getWidth(name) > maxNameW) {
                displayName = (String)displayName + "...";
            }
            TextRenderer.drawString((CharSequence)displayName, context, (int)((float)(x + 22) / nameScale), (int)(((double)itemY + 4.5) / (double)nameScale), selected ? Color.GREEN.getRGB() : -1, false);
            matrices.popMatrix();
            if (!selected) continue;
            TextRenderer.drawString("v", context, x + width - 10, itemY + 3, Color.GREEN.getRGB(), false);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int clickedRow;
        int index;
        int x = this.parentX() + 8;
        int y = this.parentY() + this.parentOffset() + this.offset;
        int width = this.parentWidth() - 16;
        int searchY = y + 15;
        int searchHeight = 14;
        if (mouseX > (double)x && mouseX < (double)(x + width) && mouseY > (double)searchY && mouseY < (double)(searchY + searchHeight)) {
            this.focused = true;
            this.lastCursorBlink = class_156.method_658();
            this.cursorVisible = true;
            return true;
        }
        this.focused = false;
        int listY = searchY + searchHeight + 5;
        int listHeight = this.height() - (listY - y) - 5;
        int itemHeight = 16;
        if (mouseX > (double)x && mouseX < (double)(x + width) && mouseY > (double)listY && mouseY < (double)(listY + listHeight) && (index = (clickedRow = (int)((mouseY - (double)listY) / (double)itemHeight)) + this.scrollOffset) < this.filteredBlocks.size()) {
            class_2248 block = this.filteredBlocks.get(index);
            this.setting.toggle(class_7923.field_41175.method_10221(block).toString());
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        int x = this.parentX() + 8;
        int y = this.parentY() + this.parentOffset() + this.offset;
        int width = this.parentWidth() - 16;
        int listY = y + 15 + 14 + 5;
        int listHeight = this.height() - (listY - y) - 5;
        if (mouseX > (double)x && mouseX < (double)(x + width) && mouseY > (double)listY && mouseY < (double)(listY + listHeight)) {
            this.scrollOffset = Math.max(0, Math.min(this.filteredBlocks.size() - 1, this.scrollOffset - (int)amount));
            return true;
        }
        return false;
    }

    @Override
    public boolean charTyped(char chr) {
        if (!this.focused) {
            return false;
        }
        this.searchQuery = this.searchQuery + chr;
        this.updateFilter();
        return true;
    }

    @Override
    public boolean keyPressed(int key) {
        if (!this.focused) {
            return false;
        }
        if (key == 259) {
            if (!this.searchQuery.isEmpty()) {
                this.searchQuery = this.searchQuery.substring(0, this.searchQuery.length() - 1);
                this.updateFilter();
            }
            return true;
        }
        if (key == 257 || key == 256) {
            this.focused = false;
            return true;
        }
        return false;
    }
}

