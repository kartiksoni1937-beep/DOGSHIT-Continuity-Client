/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import net.minecraft.class_2338;
import net.minecraft.class_636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_636.class})
public interface ClientPlayerInteractionManagerAccessor {
    @Invoker(value="method_2911")
    public void syncSlot();

    @Accessor(value="field_3715")
    public float getBreakingProgress();

    @Accessor(value="field_3714")
    public class_2338 getBreakingPos();
}

