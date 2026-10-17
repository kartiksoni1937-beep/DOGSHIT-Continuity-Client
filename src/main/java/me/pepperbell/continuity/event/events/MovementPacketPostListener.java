/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;

public interface MovementPacketPostListener
extends Listener {
    public void onSendMovementPacketsPost();

    public static class MovementPacketPostEvent
    implements Event<MovementPacketPostListener> {
        @Override
        public void fire(List<MovementPacketPostListener> listeners) {
            listeners.forEach(MovementPacketPostListener::onSendMovementPacketsPost);
        }

        @Override
        public Class<MovementPacketPostListener> getListenerType() {
            return MovementPacketPostListener.class;
        }
    }
}

