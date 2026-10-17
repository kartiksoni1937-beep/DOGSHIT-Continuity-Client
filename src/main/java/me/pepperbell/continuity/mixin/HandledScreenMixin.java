/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import net.minecraft.class_1735;
import net.minecraft.class_465;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_465.class})
public interface HandledScreenMixin {
    @Accessor(value="field_2787")
    public class_1735 getFocusedSlot();

    @Accessor(value="field_2776")
    public int getX();

    @Accessor(value="field_2800")
    public int getY();
}

