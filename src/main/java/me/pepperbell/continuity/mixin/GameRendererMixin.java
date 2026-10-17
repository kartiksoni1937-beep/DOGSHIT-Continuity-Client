/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.GameRenderListener;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4603;
import net.minecraft.class_757;
import net.minecraft.class_759;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_757.class})
public abstract class GameRendererMixin {
    @Shadow
    @Final
    private class_310 field_4015;
    @Shadow
    @Final
    public class_759 field_4012;
    @Shadow
    @Final
    private class_4603 field_59967;
    @Shadow
    @Final
    private class_4184 field_18765;

    @Shadow
    public abstract boolean method_35765();

    @Shadow
    protected abstract float method_3196(class_4184 var1, float var2, boolean var3);

    @Shadow
    protected abstract void method_3198(class_4587 var1, float var2);

    @Shadow
    protected abstract void method_3186(class_4587 var1, float var2);

    @Inject(method={"method_3188"}, at={@At(value="INVOKE_STRING", target="Lnet/minecraft/class_3695;method_15405(Ljava/lang/String;)V", args={"ldc=hand"})})
    private void onWorldRender(class_9779 tickCounter, CallbackInfo ci) {
        class_4587 matrixStack = new class_4587();
        EventManager.fire(new GameRenderListener.GameRenderEvent(matrixStack, tickCounter.method_60637(true)));
    }

    @Inject(method={"method_3192"}, at={@At(value="HEAD")})
    private void onRender(class_9779 tickCounter, boolean tick, CallbackInfo ci) {
    }
}

