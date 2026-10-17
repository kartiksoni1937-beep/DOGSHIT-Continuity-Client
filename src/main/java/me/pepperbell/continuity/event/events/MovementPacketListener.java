/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;

public interface MovementPacketListener
extends Listener {
    public void onSendMovementPackets();

    public static class MovementPacketEvent
    implements Event<MovementPacketListener> {
        @Override
        public void fire(List<MovementPacketListener> listeners) {
            listeners.forEach(MovementPacketListener::onSendMovementPackets);
        }

        @Override
        public Class<MovementPacketListener> getListenerType() {
            return MovementPacketListener.class;
        }
    }
}

