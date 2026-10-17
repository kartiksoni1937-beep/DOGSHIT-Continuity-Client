/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.module.modules.render.Fu118r19h7;
import net.minecraft.class_2874;
import net.minecraft.class_7172;
import net.minecraft.class_765;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_765.class})
public abstract class LightmapTextureManagerMixin {
    @Redirect(method={"method_3313"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_7172;method_41753()Ljava/lang/Object;"))
    private Object onGetGamma(class_7172<Double> instance) {
        if (Sweto.INSTANCE.getModuleManager().getModule(Fu118r19h7.class).isEnabled()) {
            return 10.0;
        }
        return instance.method_41753();
    }

    @Inject(method={"method_23284"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onGetBrightness(class_2874 type, int lightLevel, CallbackInfoReturnable<Float> cir) {
        if (Sweto.INSTANCE.getModuleManager().getModule(Fu118r19h7.class).isEnabled()) {
            cir.setReturnValue(Float.valueOf(1.0f));
        }
    }
}

