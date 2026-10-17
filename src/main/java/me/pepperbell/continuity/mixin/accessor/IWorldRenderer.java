/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin.accessor;

import net.minecraft.class_276;
import net.minecraft.class_4599;
import net.minecraft.class_761;
import net.minecraft.class_9960;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_761.class})
public interface IWorldRenderer {
    @Accessor(value="field_53081")
    public class_9960 getFrameBufferSet();

    @Accessor(value="field_53080")
    public class_276 getEntityOutlineFramebuffer();

    @Accessor(value="field_20951")
    public class_4599 getBufferBuilders();
}

