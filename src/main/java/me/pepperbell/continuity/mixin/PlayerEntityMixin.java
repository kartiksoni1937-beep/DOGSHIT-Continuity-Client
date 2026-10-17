/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.module.modules.combat.R34ch;
import me.pepperbell.continuity.module.modules.combat._5p34rR34ch;
import me.pepperbell.continuity.module.modules.render.N4m3Pr073c7;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1657;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1657.class})
public abstract class PlayerEntityMixin {
    @Inject(method={"method_55755"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetEntityInteractionRange(CallbackInfoReturnable<Double> cir) {
        if (Sweto.INSTANCE.getModuleManager() == null) {
            return;
        }
        _5p34rR34ch _5p34rR34ch2 = Sweto.INSTANCE.getModuleManager().getModule(_5p34rR34ch.class);
        if (_5p34rR34ch2 != null && _5p34rR34ch2.isEnabled() && WorldUtils.isSpear(class_310.method_1551().field_1724.method_6047())) {
            cir.setReturnValue((double)_5p34rR34ch2.getReach());
            return;
        }
        R34ch R34ch2 = Sweto.INSTANCE.getModuleManager().getModule(R34ch.class);
        if (R34ch2 != null && R34ch2.isEnabled()) {
            cir.setReturnValue((double)R34ch2.getReach());
        }
    }

    @Inject(method={"method_55754"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetBlockInteractionRange(CallbackInfoReturnable<Double> cir) {
        if (Sweto.INSTANCE.getModuleManager() == null) {
            return;
        }
        _5p34rR34ch _5p34rR34ch2 = Sweto.INSTANCE.getModuleManager().getModule(_5p34rR34ch.class);
        if (_5p34rR34ch2 != null && _5p34rR34ch2.isEnabled() && WorldUtils.isSpear(class_310.method_1551().field_1724.method_6047())) {
            cir.setReturnValue((double)_5p34rR34ch2.getReach());
            return;
        }
        R34ch R34ch2 = Sweto.INSTANCE.getModuleManager().getModule(R34ch.class);
        if (R34ch2 != null && R34ch2.isEnabled()) {
            cir.setReturnValue((double)R34ch2.getReach());
        }
    }

    @Inject(method={"method_5477"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetName(CallbackInfoReturnable<class_2561> cir) {
        if (Sweto.INSTANCE.getModuleManager() == null) {
            return;
        }
        N4m3Pr073c7 N4m3Pr073c72 = Sweto.INSTANCE.getModuleManager().getModule(N4m3Pr073c7.class);
        if (N4m3Pr073c72 != null && N4m3Pr073c72.isEnabled() && (Object)this == class_310.method_1551().field_1724) {
            cir.setReturnValue(class_2561.method_43470((String)N4m3Pr073c72.getFakeName()));
        }
    }

    @Inject(method={"method_5476"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetDisplayName(CallbackInfoReturnable<class_2561> cir) {
        if (Sweto.INSTANCE.getModuleManager() == null) {
            return;
        }
        N4m3Pr073c7 N4m3Pr073c72 = Sweto.INSTANCE.getModuleManager().getModule(N4m3Pr073c7.class);
        if (N4m3Pr073c72 != null && N4m3Pr073c72.isEnabled() && (Object)this == class_310.method_1551().field_1724) {
            cir.setReturnValue(class_2561.method_43470((String)N4m3Pr073c72.getFakeName()));
        }
    }
}

