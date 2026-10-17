/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.setting;

import java.util.List;
import me.pepperbell.continuity.module.setting.Setting;

public final class BlockListSetting
extends Setting<BlockListSetting> {
    private List<String> value;

    public BlockListSetting(String name, List<String> defaultValue) {
        super(name);
        this.value = defaultValue;
    }

    public List<String> getValue() {
        return this.value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public void add(String blockId) {
        if (!this.value.contains(blockId)) {
            this.value.add(blockId);
        }
    }

    public void remove(String blockId) {
        this.value.remove(blockId);
    }

    public void toggle(String blockId) {
        if (this.contains(blockId)) {
            this.remove(blockId);
        } else {
            this.add(blockId);
        }
    }

    public boolean contains(String blockId) {
        return this.value.contains(blockId);
    }

    public void clear() {
        this.value.clear();
    }
}

