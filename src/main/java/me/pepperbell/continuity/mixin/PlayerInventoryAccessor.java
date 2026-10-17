/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import net.minecraft.class_1661;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_1661.class})
public interface PlayerInventoryAccessor {
    @Accessor(value="field_7545")
    public int getSelectedSlot();

    @Accessor(value="field_7545")
    public void setSelectedSlot(int var1);
}

