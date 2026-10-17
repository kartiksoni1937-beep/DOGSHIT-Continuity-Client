/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.module.modules.render.N00v3r14y;
import net.minecraft.class_1058;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4603;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_4603.class})
public abstract class InGameOverlayRendererMixin {
    @Inject(method={"method_23070"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onRenderFireOverlay(class_4587 matrices, class_4597 vertexConsumers, class_1058 sprite, CallbackInfo ci) {
        N00v3r14y N00v3r14y2 = Sweto.INSTANCE.getModuleManager().getModule(N00v3r14y.class);
        if (N00v3r14y2 != null && N00v3r14y2.isEnabled() && N00v3r14y2.fire.getValue()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_23069"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onRenderUnderwaterOverlay(class_310 client, class_4587 matrices, class_4597 vertexConsumers, CallbackInfo ci) {
        N00v3r14y N00v3r14y2 = Sweto.INSTANCE.getModuleManager().getModule(N00v3r14y.class);
        if (N00v3r14y2 != null && N00v3r14y2.isEnabled() && N00v3r14y2.water.getValue()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_23068"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onRenderInWallOverlay(class_1058 sprite, class_4587 matrices, class_4597 vertexConsumers, CallbackInfo ci) {
        N00v3r14y N00v3r14y2 = Sweto.INSTANCE.getModuleManager().getModule(N00v3r14y.class);
        if (N00v3r14y2 != null && N00v3r14y2.isEnabled() && N00v3r14y2.blocks.getValue()) {
            ci.cancel();
        }
    }
}

