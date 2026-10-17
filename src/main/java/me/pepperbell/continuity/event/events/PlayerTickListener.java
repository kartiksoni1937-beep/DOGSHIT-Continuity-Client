/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;

public interface PlayerTickListener
extends Listener {
    public void onPlayerTick();

    public static class PlayerTickEvent
    implements Event<PlayerTickListener> {
        @Override
        public void fire(List<PlayerTickListener> listeners) {
            listeners.forEach(PlayerTickListener::onPlayerTick);
        }

        @Override
        public Class<PlayerTickListener> getListenerType() {
            return PlayerTickListener.class;
        }
    }
}

