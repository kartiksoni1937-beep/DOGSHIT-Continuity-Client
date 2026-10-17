/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import com.mojang.authlib.GameProfile;
import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.MovementPacketListener;
import me.pepperbell.continuity.event.events.MovementPacketPostListener;
import me.pepperbell.continuity.event.events.PlayerTickListener;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_746.class})
public class ClientPlayerEntityMixin
extends class_742 {
    @Shadow
    @Final
    protected class_310 field_3937;

    public ClientPlayerEntityMixin(class_638 world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method={"method_3136"}, at={@At(value="HEAD")})
    private void onSendMovementPackets(CallbackInfo ci) {
        EventManager.fire(new MovementPacketListener.MovementPacketEvent());
    }

    @Inject(method={"method_3136"}, at={@At(value="TAIL")})
    private void onSendMovementPacketsPost(CallbackInfo ci) {
        EventManager.fire(new MovementPacketPostListener.MovementPacketPostEvent());
    }

    @Inject(method={"method_5773"}, at={@At(value="HEAD")})
    private void onPlayerTick(CallbackInfo ci) {
        EventManager.fire(new PlayerTickListener.PlayerTickEvent());
    }
}

