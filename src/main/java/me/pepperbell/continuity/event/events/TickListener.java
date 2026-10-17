/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;

public interface TickListener
extends Listener {
    public void onTick();

    public static class TickEvent
    implements Event<TickListener> {
        @Override
        public void fire(List<TickListener> listeners) {
            listeners.forEach(TickListener::onTick);
        }

        @Override
        public Class<TickListener> getListenerType() {
            return TickListener.class;
        }
    }
}

