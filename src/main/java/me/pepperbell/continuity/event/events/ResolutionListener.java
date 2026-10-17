/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;
import net.minecraft.class_1041;

public interface ResolutionListener
extends Listener {
    public void onResolution(ResolutionEvent var1);

    public static class ResolutionEvent
    implements Event<ResolutionListener> {
        public class_1041 window;

        public ResolutionEvent(class_1041 window) {
            this.window = window;
        }

        @Override
        public void fire(List<ResolutionListener> listeners) {
            listeners.forEach(l -> l.onResolution(this));
        }

        @Override
        public Class<ResolutionListener> getListenerType() {
            return ResolutionListener.class;
        }
    }
}

