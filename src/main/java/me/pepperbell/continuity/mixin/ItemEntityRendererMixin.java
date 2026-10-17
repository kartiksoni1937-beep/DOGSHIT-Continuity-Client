/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import net.minecraft.class_10039;
import net.minecraft.class_11659;
import net.minecraft.class_12075;
import net.minecraft.class_4587;
import net.minecraft.class_916;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_916.class})
public abstract class ItemEntityRendererMixin {
    @Inject(method={"method_3996"}, at={@At(value="HEAD")})
    private void onRender(class_10039 state, class_4587 matrices, class_11659 queue, class_12075 cameraRenderState, CallbackInfo ci) {
    }
}

