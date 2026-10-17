/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.setting;

import me.pepperbell.continuity.module.setting.Setting;

public final class SlotSetting
extends Setting<SlotSetting> {
    private final boolean[] slots = new boolean[9];

    public SlotSetting(CharSequence name) {
        super(name);
        for (int i = 0; i < 9; ++i) {
            this.slots[i] = false;
        }
    }

    public boolean getSlot(int index) {
        if (index < 0 || index > 8) {
            return false;
        }
        return this.slots[index];
    }

    public void setSlot(int index, boolean value) {
        if (index >= 0 && index <= 8) {
            this.slots[index] = value;
        }
    }

    public void toggleSlot(int index) {
        if (index >= 0 && index <= 8) {
            this.slots[index] = !this.slots[index];
        }
    }

    public boolean[] getSlots() {
        return this.slots;
    }
}

