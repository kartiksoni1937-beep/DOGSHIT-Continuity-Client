/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import net.minecraft.class_10055;
import net.minecraft.class_1007;
import net.minecraft.class_11890;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_1007.class})
public abstract class PlayerEntityRendererMixin {
    @Inject(method={"method_62604"}, at={@At(value="TAIL")})
    private void onUpdateRenderState(class_11890 entity, class_10055 state, float tickProgress, CallbackInfo ci) {
    }
}

