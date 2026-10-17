/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.CancellableEvent;
import me.pepperbell.continuity.event.Listener;

public interface ItemUseListener
extends Listener {
    public void onItemUse(ItemUseEvent var1);

    public static class ItemUseEvent
    extends CancellableEvent<ItemUseListener> {
        @Override
        public void fire(List<ItemUseListener> listeners) {
            listeners.forEach(e -> e.onItemUse(this));
        }

        @Override
        public Class<ItemUseListener> getListenerType() {
            return ItemUseListener.class;
        }
    }
}

