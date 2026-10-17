/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.BlockBreakingListener;
import me.pepperbell.continuity.event.events.ClickSlotListener;
import me.pepperbell.continuity.module.modules.misc.N08r34kD314y;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_636.class})
public class ClientPlayerInteractionManagerMixin {
    @Shadow
    private int field_3716;

    @Redirect(method={"method_2902"}, at=@At(value="FIELD", target="Lnet/minecraft/class_636;field_3716:I", opcode=180, ordinal=0))
    public int updateBlockBreakingProgress(class_636 clientPlayerInteractionManager) {
        int cooldown = this.field_3716;
        return Sweto.INSTANCE.getModuleManager().getModule(N08r34kD314y.class).isEnabled() ? 0 : cooldown;
    }

    @Inject(method={"method_2906"}, at={@At(value="HEAD")}, cancellable=true)
    private void onClickSlot(int syncId, int slotId, int button, class_1713 actionType, class_1657 player, CallbackInfo ci) {
        ClickSlotListener.ClickSlotEvent event = new ClickSlotListener.ClickSlotEvent(syncId, slotId, button, actionType);
        EventManager.fire(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"method_2925"}, at={@At(value="HEAD")}, cancellable=true)
    private void onCancelBlockBreaking(CallbackInfo ci) {
        BlockBreakingListener.BlockBreakingEvent event = new BlockBreakingListener.BlockBreakingEvent();
        EventManager.fire(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
}

