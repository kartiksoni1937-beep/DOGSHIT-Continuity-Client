/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.gui.components.settings;

import java.awt.Color;
import java.util.List;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.gui.components.ModuleButton;
import me.pepperbell.continuity.gui.components.settings.RenderableSetting;
import me.pepperbell.continuity.module.modules.client.C0nf195;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;

public final class ConfigBox
extends RenderableSetting {
    private int scrollOffset = 0;
    private final int itemHeight = 22;
    private String feedbackText = "";
    private long feedbackTime = 0L;

    public ConfigBox(ModuleButton parent, Setting<?> setting, int offset) {
        super(parent, setting, offset);
    }

    @Override
    public int height() {
        return 160;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        boolean hoveringRef;
        boolean hoveringSave;
        int index;
        super.render(context, mouseX, mouseY, delta);
        Matrix3x2fStack matrices = context.method_51448();
        int x = this.parentX() + 6;
        int y = this.parentY() + this.parentOffset() + this.offset + 4;
        int w = this.parentWidth() - 12;
        int headerH = 24;
        int listY = y + headerH + 6;
        int listH = this.height() - headerH - 44;
        RenderUtils.renderRoundedQuad(context, new Color(22, 22, 28, 220), x, y, x + w, y + headerH, 6.0, 16.0);
        String configName = Sweto.INSTANCE.getModuleManager().getModule(C0nf195.class).getConfigName();
        matrices.pushMatrix();
        float titleScale = 0.6f;
        matrices.scale(titleScale, titleScale);
        String title = "Config Manager";
        int titleX = (int)((float)(x + 8) / titleScale);
        int titleY2 = (int)(((float)(y + headerH / 2) - (float)TextRenderer.getFontHeight() * titleScale / 2.0f) / titleScale);
        TextRenderer.drawString(title, context, titleX, titleY2, -3355444, false);
        String currentLabel = "Current: " + configName;
        int cw = TextRenderer.getWidth(currentLabel);
        int nameX2 = (int)(((float)(x + w - 8) - (float)cw * titleScale) / titleScale);
        TextRenderer.drawString(currentLabel, context, nameX2, titleY2, -7829368, false);
        matrices.popMatrix();
        RenderUtils.renderRoundedQuad(context, new Color(14, 14, 17, 180), x, listY, x + w, listY + listH, 6.0, 16.0);
        RenderUtils.renderRoundedOutline(context, new Color(255, 255, 255, 12), x, listY, x + w, listY + listH, 6.0, 6.0, 6.0, 6.0, 0.5, 16.0);
        List<String> profiles = Sweto.INSTANCE.profileManager.listProfiles();
        int visibleCount = listH / 22;
        if (!this.feedbackText.isEmpty() && System.currentTimeMillis() - this.feedbackTime < 2000L) {
            matrices.pushMatrix();
            float fbScale = 0.6f;
            matrices.scale(fbScale, fbScale);
            int fbX = (int)(((float)(x + w / 2) - (float)TextRenderer.getWidth(this.feedbackText) * fbScale / 2.0f) / fbScale);
            int fbY2 = (int)((float)(listY + listH - 16) / fbScale);
            TextRenderer.drawString(this.feedbackText, context, fbX, fbY2, -7798904, false);
            matrices.popMatrix();
        }
        for (int i = 0; i < visibleCount && (index = i + this.scrollOffset) < profiles.size(); ++i) {
            boolean hoveringRow;
            String profileName = profiles.get(index);
            int itemY = listY + i * 22 + 3;
            int itemH = 18;
            boolean bl = hoveringRow = mouseX > x && mouseX < x + w && mouseY > itemY && mouseY < itemY + itemH;
            if (hoveringRow) {
                RenderUtils.renderRoundedQuad(context, new Color(255, 255, 255, 8), x + 3, itemY, x + w - 3, itemY + itemH, 4.0, 16.0);
            }
            RenderUtils.renderRoundedQuad(context, new Color(100, 140, 255, 140), x + 6, itemY + 6, x + 8, itemY + itemH - 6, 1.0, 8.0);
            matrices.pushMatrix();
            float nameScale = 0.65f;
            matrices.scale(nameScale, nameScale);
            int nX = (int)((float)(x + 16) / nameScale);
            int nY2 = (int)(((float)(itemY + itemH / 2) - (float)TextRenderer.getFontHeight() * nameScale / 2.0f) / nameScale);
            TextRenderer.drawString(profileName, context, nX, nY2, profileName.equals(configName) ? -7816193 : -2236963, false);
            matrices.popMatrix();
            int btnW = 26;
            int btnH = 16;
            int btnY = itemY + (itemH - btnH) / 2;
            int btnRightX = x + w - 8;
            int loadX = btnRightX - btnW * 2 - 6;
            boolean hoveringLoad = mouseX > loadX && mouseX < loadX + btnW && mouseY > btnY && mouseY < btnY + btnH;
            Color loadColor = hoveringLoad ? new Color(70, 130, 255, 220) : new Color(50, 90, 180, 160);
            RenderUtils.renderRoundedQuad(context, loadColor, loadX, btnY, loadX + btnW, btnY + btnH, 4.0, 16.0);
            matrices.pushMatrix();
            float btnScale = 0.55f;
            matrices.scale(btnScale, btnScale);
            int btnLX = (int)(((float)loadX + ((float)btnW - (float)TextRenderer.getWidth("L") * btnScale) / 2.0f) / btnScale);
            int btnLY = (int)(((float)btnY + ((float)btnH - (float)TextRenderer.getFontHeight() * btnScale) / 2.0f) / btnScale);
            TextRenderer.drawString("L", context, btnLX, btnLY, -1, false);
            matrices.popMatrix();
            int delX = btnRightX - btnW;
            boolean hoveringDel = mouseX > delX && mouseX < delX + btnW && mouseY > btnY && mouseY < btnY + btnH;
            Color delColor = hoveringDel ? new Color(255, 60, 60, 220) : new Color(180, 40, 40, 160);
            RenderUtils.renderRoundedQuad(context, delColor, delX, btnY, delX + btnW, btnY + btnH, 4.0, 16.0);
            matrices.pushMatrix();
            matrices.scale(btnScale, btnScale);
            int btnDX = (int)(((float)delX + ((float)btnW - (float)TextRenderer.getWidth("X") * btnScale) / 2.0f) / btnScale);
            int btnDY = (int)(((float)btnY + ((float)btnH - (float)TextRenderer.getFontHeight() * btnScale) / 2.0f) / btnScale);
            TextRenderer.drawString("X", context, btnDX, btnDY, -1, false);
            matrices.popMatrix();
            if (!profileName.equals(configName)) continue;
            RenderUtils.renderRoundedQuad(context, new Color(70, 200, 70, 220), x + 3, itemY + itemH - 2, x + 12, itemY + itemH, 1.5, 8.0);
        }
        if (profiles.size() > visibleCount) {
            int dotY = listY + listH - 8;
            int dotCount = Math.min(profiles.size(), 5);
            for (int i = 0; i < dotCount; ++i) {
                int dX = x + w / 2 - dotCount * 3 + i * 6;
                boolean active = i == (int)((float)this.scrollOffset / (float)Math.max(1, profiles.size() - visibleCount) * (float)(dotCount - 1));
                RenderUtils.renderRoundedQuad(context, active ? new Color(150, 150, 150, 150) : new Color(80, 80, 80, 80), dX, dotY, dX + 3, dotY + 3, 1.5, 8.0);
            }
        }
        int footY = listY + listH + 6;
        int footBtnH = 18;
        int footBtnW = (w - 8) / 2;
        Color saveColor = new Color(60, 140, 60, 190);
        boolean bl = hoveringSave = mouseX > x && mouseX < x + footBtnW && mouseY > footY && mouseY < footY + footBtnH;
        if (hoveringSave) {
            saveColor = new Color(80, 180, 80, 230);
        }
        RenderUtils.renderRoundedQuad(context, saveColor, x, footY, x + footBtnW, footY + footBtnH, 5.0, 16.0);
        matrices.pushMatrix();
        float footScale = 0.6f;
        matrices.scale(footScale, footScale);
        String saveText = "Save as \"" + configName + "\"";
        int sX = (int)(((float)x + ((float)footBtnW - (float)TextRenderer.getWidth(saveText) * footScale) / 2.0f) / footScale);
        int sY2 = (int)(((float)(footY + footBtnH / 2) - (float)TextRenderer.getFontHeight() * footScale / 2.0f) / footScale);
        TextRenderer.drawString(saveText, context, sX, sY2, -1, false);
        matrices.popMatrix();
        int refX = x + footBtnW + 8;
        Color refColor = new Color(50, 50, 60, 190);
        boolean bl2 = hoveringRef = mouseX > refX && mouseX < refX + footBtnW && mouseY > footY && mouseY < footY + footBtnH;
        if (hoveringRef) {
            refColor = new Color(70, 70, 80, 230);
        }
        RenderUtils.renderRoundedQuad(context, refColor, refX, footY, refX + footBtnW, footY + footBtnH, 5.0, 16.0);
        matrices.pushMatrix();
        matrices.scale(footScale, footScale);
        String refText = "Refresh";
        int rX = (int)(((float)refX + ((float)footBtnW - (float)TextRenderer.getWidth(refText) * footScale) / 2.0f) / footScale);
        int rY2 = (int)(((float)(footY + footBtnH / 2) - (float)TextRenderer.getFontHeight() * footScale / 2.0f) / footScale);
        TextRenderer.drawString(refText, context, rX, rY2, -1, false);
        matrices.popMatrix();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int index;
        if (button != 0) {
            return false;
        }
        int x = this.parentX() + 6;
        int y = this.parentY() + this.parentOffset() + this.offset + 4;
        int w = this.parentWidth() - 12;
        int headerH = 24;
        int listY = y + headerH + 6;
        int listH = this.height() - headerH - 44;
        int footY = listY + listH + 6;
        int footBtnH = 18;
        int footBtnW = (w - 8) / 2;
        if (mouseY > (double)footY && mouseY < (double)(footY + footBtnH)) {
            if (mouseX > (double)x && mouseX < (double)(x + footBtnW)) {
                String cn = Sweto.INSTANCE.getModuleManager().getModule(C0nf195.class).getConfigName();
                if (cn != null && !cn.isEmpty()) {
                    Sweto.INSTANCE.profileManager.saveProfile(cn);
                    this.feedbackText = "Saved \"" + cn + "\"";
                    this.feedbackTime = System.currentTimeMillis();
                }
                return true;
            }
            if (mouseX > (double)(x + footBtnW + 8) && mouseX < (double)(x + w)) {
                this.feedbackText = "Refreshed";
                this.feedbackTime = System.currentTimeMillis();
                return true;
            }
        }
        List<String> profiles = Sweto.INSTANCE.profileManager.listProfiles();
        int visibleCount = listH / 22;
        for (int i = 0; i < visibleCount && (index = i + this.scrollOffset) < profiles.size(); ++i) {
            String profileName = profiles.get(index);
            int itemY = listY + i * 22 + 3;
            int itemH = 18;
            if (!(mouseX > (double)x) || !(mouseX < (double)(x + w)) || !(mouseY > (double)itemY) || !(mouseY < (double)(itemY + itemH))) continue;
            int btnW = 26;
            int btnH = 16;
            int btnY = itemY + (itemH - btnH) / 2;
            int btnRightX = x + w - 8;
            int loadX = btnRightX - btnW * 2 - 6;
            int delX = btnRightX - btnW;
            if (mouseX > (double)loadX && mouseX < (double)(loadX + btnW)) {
                Sweto.INSTANCE.profileManager.loadProfile(profileName);
                this.feedbackText = "Loaded \"" + profileName + "\"";
                this.feedbackTime = System.currentTimeMillis();
                return true;
            }
            if (!(mouseX > (double)delX) || !(mouseX < (double)(delX + btnW))) continue;
            Sweto.INSTANCE.profileManager.deleteProfile(profileName);
            this.feedbackText = "Deleted \"" + profileName + "\"";
            this.feedbackTime = System.currentTimeMillis();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        int x = this.parentX() + 6;
        int y = this.parentY() + this.parentOffset() + this.offset + 4;
        int w = this.parentWidth() - 12;
        int headerH = 24;
        int listY = y + headerH + 6;
        int listH = this.height() - headerH - 44;
        if (mouseX > (double)x && mouseX < (double)(x + w) && mouseY > (double)listY && mouseY < (double)(listY + listH)) {
            List<String> profiles = Sweto.INSTANCE.profileManager.listProfiles();
            int max = Math.max(0, profiles.size() - listH / 22);
            this.scrollOffset = Math.max(0, Math.min(max, this.scrollOffset - (int)amount));
            return true;
        }
        return false;
    }
}

