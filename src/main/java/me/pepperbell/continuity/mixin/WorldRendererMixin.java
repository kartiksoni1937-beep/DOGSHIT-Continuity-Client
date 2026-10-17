/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_10017;
import net.minecraft.class_1297;
import net.minecraft.class_279;
import net.minecraft.class_310;
import net.minecraft.class_4599;
import net.minecraft.class_761;
import net.minecraft.class_9909;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={class_761.class})
public abstract class WorldRendererMixin {
    @Shadow
    @Final
    private class_4599 field_20951;
    @Shadow
    @Final
    private class_310 field_4088;

    @Redirect(method={"method_22710"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_279;method_62234(Lnet/minecraft/class_9909;IILnet/minecraft/class_279$class_9961;)V", ordinal=0))
    public void onRenderOutlinePostProcessor(class_279 instance, class_9909 builder, int textureWidth, int textureHeight, class_279.class_9961 framebufferSet) {
        instance.method_62234(builder, textureWidth, textureHeight, framebufferSet);
    }

    @Redirect(method={"method_72917"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_10017;method_72997()Z"))
    public boolean onHasOutline(class_10017 instance, @Local class_1297 entity) {
        return instance.method_72997();
    }
}

