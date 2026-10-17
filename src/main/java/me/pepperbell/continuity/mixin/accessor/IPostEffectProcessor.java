/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin.accessor;

import java.util.List;
import net.minecraft.class_279;
import net.minecraft.class_283;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_279.class})
public interface IPostEffectProcessor {
    @Accessor(value="field_1497")
    public List<class_283> getPasses();
}

