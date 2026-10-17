/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.CancellableEvent;
import me.pepperbell.continuity.event.Listener;

public interface AttackListener
extends Listener {
    public void onAttack(AttackEvent var1);

    public static class AttackEvent
    extends CancellableEvent<AttackListener> {
        @Override
        public void fire(List<AttackListener> listeners) {
            listeners.forEach(e -> e.onAttack(this));
        }

        @Override
        public Class<AttackListener> getListenerType() {
            return AttackListener.class;
        }
    }
}

