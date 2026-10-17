/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.utils.rotation.R0T1AT0RM4NG3R;
import net.minecraft.class_10042;
import net.minecraft.class_1309;
import net.minecraft.class_3532;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_922.class})
public abstract class LivingEntityRendererMixin<T extends class_1309, S extends class_10042> {
    @Inject(method={"method_62355"}, at={@At(value="TAIL")})
    private void onUpdateRenderState(T entity, S state, float tickProgress, CallbackInfo ci) {
        R0T1AT0RM4NG3R rotator;
        if (entity == Sweto.mc.field_1724 && (rotator = Sweto.INSTANCE.rotatorManager).isActive()) {
            float visualYaw = class_3532.method_17821((float)tickProgress, (float)rotator.getPrevVisualYaw(), (float)rotator.getVisualYaw());
            float visualPitch = class_3532.method_16439((float)tickProgress, (float)rotator.getPrevVisualPitch(), (float)rotator.getVisualPitch());
            ((class_10042)state).field_53446 = visualYaw;
            ((class_10042)state).field_53447 = 0.0f;
            ((class_10042)state).field_53448 = visualPitch;
        }
    }
}

