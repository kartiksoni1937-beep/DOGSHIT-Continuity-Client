/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.CancellableEvent;
import me.pepperbell.continuity.event.Listener;
import net.minecraft.class_2596;

public interface PacketSendListener
extends Listener {
    public void onPacketSend(PacketSendEvent var1);

    public static class PacketSendEvent
    extends CancellableEvent<PacketSendListener> {
        public class_2596 packet;

        public PacketSendEvent(class_2596 packet) {
            this.packet = packet;
        }

        @Override
        public void fire(List<PacketSendListener> listeners) {
            listeners.forEach(e -> e.onPacketSend(this));
        }

        @Override
        public Class<PacketSendListener> getListenerType() {
            return PacketSendListener.class;
        }
    }
}

