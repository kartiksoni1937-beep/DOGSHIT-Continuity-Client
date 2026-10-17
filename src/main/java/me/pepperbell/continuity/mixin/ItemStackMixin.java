/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.module.modules.render.N080unc3;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1799.class})
public class ItemStackMixin {
    @Inject(method={"method_7965"}, at={@At(value="HEAD")}, cancellable=true)
    private void removeBounceAnimation(CallbackInfoReturnable<Integer> cir) {
        class_1799 mainHandStack;
        if (Sweto.mc.field_1724 == null) {
            return;
        }
        N080unc3 N080unc32 = Sweto.INSTANCE.getModuleManager().getModule(N080unc3.class);
        if (Sweto.INSTANCE != null && Sweto.mc.field_1724 != null && N080unc32.isEnabled() && (mainHandStack = Sweto.mc.field_1724.method_6047()).method_31574(class_1802.field_8301)) {
            cir.setReturnValue(0);
        }
    }
}

