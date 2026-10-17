/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.ModuleToggleEvent;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.Setting;
import net.minecraft.class_310;

public abstract class Module
implements Serializable {
    private final List<Setting<?>> settings = new ArrayList();
    public final EventManager eventManager;
    protected class_310 mc;
    private CharSequence name;
    private CharSequence description;
    private boolean enabled;
    private int key;
    private Category category;
    private String subCategory;

    public Module(CharSequence name, CharSequence description, int key, Category category) {
        this.eventManager = Sweto.INSTANCE.eventManager;
        this.mc = class_310.method_1551();
        this.subCategory = "Main";
        this.name = name;
        this.description = description;
        this.enabled = false;
        this.key = key;
        this.category = category;
    }

    public Module setSubCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public String getSubCategory() {
        return this.subCategory;
    }

    public void toggle() {
        boolean bl = this.enabled = !this.enabled;
        if (this.enabled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
        EventManager.fire(new ModuleToggleEvent(this, this.enabled));
    }

    public CharSequence getName() {
        return this.name;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public CharSequence getDescription() {
        return this.description;
    }

    public int getKey() {
        for (Setting<?> setting : this.settings) {
            KeybindSetting ks;
            if (!(setting instanceof KeybindSetting) || !(ks = (KeybindSetting)setting).getName().toString().equalsIgnoreCase("Keybind")) continue;
            return ks.getKey();
        }
        return this.key;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public void setDescription(CharSequence description) {
        this.description = description;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public List<Setting<?>> getSettings() {
        return this.settings;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void addSetting(Setting<?> setting) {
        this.settings.add(setting);
    }

    public void addSettings(Setting<?> ... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
        EventManager.fire(new ModuleToggleEvent(this, enabled));
    }

    public void setEnabledStatus(boolean enabled) {
        this.enabled = enabled;
    }
}

