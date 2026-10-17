/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin.accessor;

import net.minecraft.class_757;
import net.minecraft.class_9920;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_757.class})
public interface IGameRenderer {
    @Accessor(value="field_53066")
    public class_9920 getPool();
}

