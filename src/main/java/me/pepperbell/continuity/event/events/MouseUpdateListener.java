/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;

public interface MouseUpdateListener
extends Listener {
    public void onMouseUpdate();

    public static class MouseUpdateEvent
    implements Event<MouseUpdateListener> {
        @Override
        public void fire(List<MouseUpdateListener> listeners) {
            listeners.forEach(MouseUpdateListener::onMouseUpdate);
        }

        @Override
        public Class<MouseUpdateListener> getListenerType() {
            return MouseUpdateListener.class;
        }
    }
}

