/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.setting;

import java.util.function.BooleanSupplier;

public abstract class Setting<T extends Setting<T>> {
    private CharSequence name;
    public CharSequence description;
    private boolean visible = true;
    private BooleanSupplier visibility = () -> true;

    public Setting(CharSequence name) {
        this.name = name;
    }

    public boolean isVisible() {
        return this.visible && (this.visibility == null || this.visibility.getAsBoolean());
    }

    public T setVisible(boolean visible) {
        this.visible = visible;
        return (T)this;
    }

    public T visible(BooleanSupplier visibility) {
        this.visibility = visibility;
        return (T)this;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public CharSequence getName() {
        return this.name;
    }

    public CharSequence getDescription() {
        return this.description;
    }

    public T setDescription(CharSequence desc) {
        this.description = desc;
        return (T)this;
    }
}

