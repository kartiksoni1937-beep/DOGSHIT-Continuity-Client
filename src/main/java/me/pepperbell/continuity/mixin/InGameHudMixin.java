/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.HudListener;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_329.class})
public class InGameHudMixin {
    @Inject(method={"method_1753"}, at={@At(value="HEAD")})
    private void onRenderHud(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        HudListener.HudEvent event = new HudListener.HudEvent(context, tickCounter.method_60637(true));
        EventManager.fire(event);
    }
}

