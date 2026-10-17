/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.ButtonListener;
import net.minecraft.class_11908;
import net.minecraft.class_309;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_309.class})
public class KeyboardMixin {
    @Shadow
    @Final
    private class_310 field_1678;

    @Inject(method={"method_1466"}, at={@At(value="HEAD")})
    private void onPress(long window, int action, class_11908 input, CallbackInfo ci) {
        EventManager.fire(new ButtonListener.ButtonEvent(input.comp_4795(), window, action, input.comp_4797()));
    }
}

