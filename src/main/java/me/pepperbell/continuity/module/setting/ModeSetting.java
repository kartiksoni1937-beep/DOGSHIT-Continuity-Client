/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.setting;

import java.util.Arrays;
import java.util.List;
import me.pepperbell.continuity.module.setting.Setting;

public final class ModeSetting<T extends Enum<T>>
extends Setting<ModeSetting<T>> {
    public int index;
    private final List<T> possibleValues;
    private final int originalValue;

    public ModeSetting(CharSequence name, T defaultValue, Class<T> type) {
        super(name);
        T[] values = type.getEnumConstants();
        this.possibleValues = Arrays.asList(values);
        this.originalValue = this.index = this.possibleValues.indexOf(defaultValue);
    }

    public T getMode() {
        if (this.index < 0 || this.index >= this.possibleValues.size()) {
            this.index = 0;
        }
        return (T)((Enum)this.possibleValues.get(this.index));
    }

    public void setMode(T mode) {
        int i = this.possibleValues.indexOf(mode);
        if (i != -1) {
            this.index = i;
        }
    }

    public void setModeIndex(int mode) {
        this.index = mode < 0 || mode >= this.possibleValues.size() ? 0 : mode;
    }

    public int getModeIndex() {
        return this.index;
    }

    public int getOriginalValue() {
        return this.originalValue;
    }

    public void cycle() {
        this.index = this.index < this.possibleValues.size() - 1 ? ++this.index : 0;
    }

    public boolean isMode(T mode) {
        return this.index == this.possibleValues.indexOf(mode);
    }
}

