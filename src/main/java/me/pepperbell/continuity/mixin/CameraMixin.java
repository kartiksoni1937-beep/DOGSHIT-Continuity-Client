/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.CameraUpdateListener;
import net.minecraft.class_4184;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={class_4184.class})
public class CameraMixin {
    @ModifyArgs(method={"method_19321"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_4184;method_19327(DDD)V"))
    private void update(Args args) {
        CameraUpdateListener.CameraUpdateEvent event = new CameraUpdateListener.CameraUpdateEvent((Double)args.get(0), (Double)args.get(1), (Double)args.get(2));
        EventManager.fire(event);
        args.set(0, (Object)event.getX());
        args.set(1, (Object)event.getY());
        args.set(2, (Object)event.getZ());
    }
}

