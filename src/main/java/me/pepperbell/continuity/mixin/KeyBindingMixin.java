/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.imixin.IKeyBinding;
import net.minecraft.class_1041;
import net.minecraft.class_304;
import net.minecraft.class_3675;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={class_304.class})
public abstract class KeyBindingMixin
implements IKeyBinding {
    @Shadow
    private class_3675.class_306 field_1655;

    @Override
    public boolean isActuallyPressed() {
        int code = this.field_1655.method_1444();
        return class_3675.method_15987((class_1041)Sweto.mc.method_22683(), (int)code);
    }

    @Override
    public void resetPressed() {
        this.method_23481(this.isActuallyPressed());
    }

    @Shadow
    public abstract void method_23481(boolean var1);
}

