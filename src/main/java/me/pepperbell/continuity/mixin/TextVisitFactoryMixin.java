/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.module.modules.render.N4m3Pr073c7;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_5223;
import net.minecraft.class_5224;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_5223.class})
public abstract class TextVisitFactoryMixin {
    private static final ThreadLocal<Boolean> IGNORE = ThreadLocal.withInitial(() -> false);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Inject(method={"method_27472"}, at={@At(value="HEAD")}, cancellable=true)
    private static void onVisitFormatted(String text, int offset, class_2583 style, class_5224 visitor, CallbackInfoReturnable<Boolean> cir) {
        String ownName;
        if (IGNORE.get().booleanValue()) {
            return;
        }
        N4m3Pr073c7 N4m3Pr073c72 = Sweto.INSTANCE.getModuleManager().getModule(N4m3Pr073c7.class);
        if (N4m3Pr073c72 != null && N4m3Pr073c72.isEnabled() && text.contains(ownName = class_310.method_1551().method_1548().method_1676())) {
            String replaced = text.replace(ownName, N4m3Pr073c72.getFakeName());
            IGNORE.set(true);
            try {
                cir.setReturnValue(class_5223.method_27472((String)replaced, (int)offset, (class_2583)style, (class_5224)visitor));
            }
            finally {
                IGNORE.set(false);
            }
        }
    }
}

