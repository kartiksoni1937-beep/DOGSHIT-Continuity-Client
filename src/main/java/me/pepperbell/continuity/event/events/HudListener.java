/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;
import net.minecraft.class_332;

public interface HudListener
extends Listener {
    public void onRenderHud(HudEvent var1);

    public static class HudEvent
    implements Event<HudListener> {
        public class_332 context;
        public float delta;

        public HudEvent(class_332 context, float delta) {
            this.context = context;
            this.delta = delta;
        }

        @Override
        public void fire(List<HudListener> listeners) {
            listeners.forEach(e -> e.onRenderHud(this));
        }

        @Override
        public Class<HudListener> getListenerType() {
            return HudListener.class;
        }
    }
}

