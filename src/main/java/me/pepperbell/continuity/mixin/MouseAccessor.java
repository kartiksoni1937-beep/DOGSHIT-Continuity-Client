/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import net.minecraft.class_312;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_312.class})
public interface MouseAccessor {
    @Invoker(value="method_1600")
    public void invokeOnCursorPos(long var1, double var3, double var5);
}

