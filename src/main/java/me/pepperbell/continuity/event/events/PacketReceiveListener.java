/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.CancellableEvent;
import me.pepperbell.continuity.event.Listener;
import net.minecraft.class_2596;

public interface PacketReceiveListener
extends Listener {
    public void onPacketReceive(PacketReceiveEvent var1);

    public static class PacketReceiveEvent
    extends CancellableEvent<PacketReceiveListener> {
        public class_2596 packet;

        public PacketReceiveEvent(class_2596 packet) {
            this.packet = packet;
        }

        @Override
        public void fire(List<PacketReceiveListener> listeners) {
            listeners.forEach(e -> e.onPacketReceive(this));
        }

        @Override
        public Class<PacketReceiveListener> getListenerType() {
            return PacketReceiveListener.class;
        }
    }
}

