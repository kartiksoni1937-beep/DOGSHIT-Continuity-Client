/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;

public interface ButtonListener
extends Listener {
    public void onButtonPress(ButtonEvent var1);

    public static class ButtonEvent
    implements Event<ButtonListener> {
        public int button;
        public int action;
        public int modifiers;
        public long window;

        public ButtonEvent(int button, long window, int action, int modifiers) {
            this.button = button;
            this.window = window;
            this.action = action;
            this.modifiers = modifiers;
        }

        @Override
        public void fire(List<ButtonListener> listeners) {
            listeners.forEach(e -> e.onButtonPress(this));
        }

        @Override
        public Class<ButtonListener> getListenerType() {
            return ButtonListener.class;
        }
    }
}

