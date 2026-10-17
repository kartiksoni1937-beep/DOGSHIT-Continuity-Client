/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.setting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import me.pepperbell.continuity.module.setting.Setting;
import net.minecraft.class_1792;

public class ItemSetting
extends Setting<ItemSetting> {
    private List<class_1792> selected = new ArrayList<class_1792>();
    private String searchResult = "";
    private boolean extended;

    public ItemSetting(CharSequence name, CharSequence description) {
        super(name);
        this.setDescription(description);
    }

    public ItemSetting(CharSequence name, CharSequence description, class_1792 ... defaults) {
        super(name);
        this.setDescription(description);
        if (defaults != null) {
            for (class_1792 item : defaults) {
                if (this.selected.contains(item)) continue;
                this.selected.add(item);
            }
        }
    }

    public List<class_1792> getSelected() {
        return this.selected;
    }

    public boolean isSelected(class_1792 item) {
        return this.selected.contains(item);
    }

    public void toggle(class_1792 item) {
        if (this.selected.contains(item)) {
            this.selected.remove(item);
        } else {
            this.selected.add(item);
        }
    }

    public String getSearchResult() {
        return this.searchResult;
    }

    public void setSearchResult(String searchResult) {
        this.searchResult = searchResult;
    }

    public boolean isExtended() {
        return this.extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    @Override
    public ItemSetting visible(BooleanSupplier visibility) {
        super.visible(visibility);
        return this;
    }
}

