/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import net.minecraft.class_1306;
import net.minecraft.class_4587;
import net.minecraft.class_759;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_759.class})
public abstract class HeldItemRendererMixin {
    @Inject(method={"method_3224"}, at={@At(value="TAIL")})
    private void onApplyEquipOffset(class_4587 matrices, class_1306 arm, float equipProgress, CallbackInfo ci) {
    }
}

