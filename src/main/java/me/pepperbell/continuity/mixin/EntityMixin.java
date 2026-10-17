/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.VelocityStrafeEvent;
import me.pepperbell.continuity.module.modules.combat.H1780x;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1297.class})
public abstract class EntityMixin {
    @Inject(method={"method_5871"}, at={@At(value="RETURN")}, cancellable=true)
    private void onGetTargetingMargin(CallbackInfoReturnable<Float> cir) {
        H1780x H1780x2 = Sweto.INSTANCE.getModuleManager().getModule(H1780x.class);
        if (H1780x2 != null && H1780x2.isEnabled()) {
            float expand = H1780x2.expand.getValueFloat();
            if (H1780x2.legitMode.getValue()) {
                expand = Math.min(expand, 0.1f);
            }
            cir.setReturnValue(Float.valueOf(expand));
        }
    }

    @Redirect(method={"method_5724"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_1297;method_18795(Lnet/minecraft/class_243;FF)Lnet/minecraft/class_243;"))
    private class_243 hookVelocity(class_243 movementInput, float speed, float yaw) {
        class_310 mc = class_310.method_1551();
        if (mc != null && (Object)this == mc.field_1724) {
            VelocityStrafeEvent.VelocityStrafeData event = new VelocityStrafeEvent.VelocityStrafeData(movementInput, speed, yaw, EntityMixin.defaultVelocity(movementInput, speed, yaw));
            EventManager.fire(event);
            return event.getVelocity();
        }
        return EntityMixin.defaultVelocity(movementInput, speed, yaw);
    }

    private static class_243 defaultVelocity(class_243 movementInput, float speed, float yaw) {
        double d = movementInput.method_1027();
        if (d < 1.0E-7) {
            return class_243.field_1353;
        }
        class_243 vec3d = (d > 1.0 ? movementInput.method_1029() : movementInput).method_1021((double)speed);
        float f = class_3532.method_15374((double)(yaw * ((float)Math.PI / 180)));
        float g = class_3532.method_15362((double)(yaw * ((float)Math.PI / 180)));
        return new class_243(vec3d.field_1352 * (double)g - vec3d.field_1350 * (double)f, vec3d.field_1351, vec3d.field_1350 * (double)g + vec3d.field_1352 * (double)f);
    }
}

