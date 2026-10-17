/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.CancellableEvent;
import me.pepperbell.continuity.event.Listener;

public interface MouseMoveListener
extends Listener {
    public void onMouseMove(MouseMoveEvent var1);

    public static class MouseMoveEvent
    extends CancellableEvent<MouseMoveListener> {
        public long windowHandle;
        public double x;
        public double y;

        public MouseMoveEvent(long windowHandle, double x, double y) {
            this.windowHandle = windowHandle;
            this.x = x;
            this.y = y;
        }

        @Override
        public void fire(List<MouseMoveListener> listeners) {
            listeners.forEach(e -> e.onMouseMove(this));
        }

        @Override
        public Class<MouseMoveListener> getListenerType() {
            return MouseMoveListener.class;
        }
    }
}

