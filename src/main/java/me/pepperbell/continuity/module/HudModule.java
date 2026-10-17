/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module;

import me.pepperbell.continuity.event.events.HudListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;

public abstract class HudModule
extends Module
implements HudListener {
    public final SliderSetting xCoord;
    public final SliderSetting yCoord;
    protected int width;
    protected int height;

    public HudModule(CharSequence name, CharSequence description, int defaultX, int defaultY, int width, int height) {
        super(name, description, -1, Category.HUD);
        this.xCoord = (SliderSetting)new SliderSetting(EncryptedString.of("X"), 0.0, 4000.0, defaultX, 1.0).setVisible(false);
        this.yCoord = (SliderSetting)new SliderSetting(EncryptedString.of("Y"), 0.0, 4000.0, defaultY, 1.0).setVisible(false);
        this.width = width;
        this.height = height;
        this.addSettings(this.xCoord, this.yCoord);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(HudListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(HudListener.class, this);
        super.onDisable();
    }

    public int getX() {
        return this.xCoord.getValueInt();
    }

    public int getY() {
        return this.yCoord.getValueInt();
    }

    public void setX(int x) {
        this.xCoord.setValue(x);
    }

    public void setY(int y) {
        this.yCoord.setValue(y);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

