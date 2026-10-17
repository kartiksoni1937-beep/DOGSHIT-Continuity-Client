/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import net.minecraft.class_243;
import net.minecraft.class_745;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_745.class})
public interface OtherClientPlayerEntityAccessor {
    @Accessor(value="field_42908")
    public int getVelocityLerpDivisor();

    @Accessor(value="field_42908")
    public void setVelocityLerpDivisor(int var1);

    @Accessor(value="field_42907")
    public class_243 getClientVelocity();

    @Accessor(value="field_42907")
    public void setClientVelocity(class_243 var1);
}

