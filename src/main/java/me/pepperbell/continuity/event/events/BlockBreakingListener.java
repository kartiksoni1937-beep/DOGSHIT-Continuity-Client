/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.CancellableEvent;
import me.pepperbell.continuity.event.Listener;

public interface BlockBreakingListener
extends Listener {
    public void onBlockBreaking(BlockBreakingEvent var1);

    public static class BlockBreakingEvent
    extends CancellableEvent<BlockBreakingListener> {
        @Override
        public void fire(List<BlockBreakingListener> listeners) {
            listeners.forEach(e -> e.onBlockBreaking(this));
        }

        @Override
        public Class<BlockBreakingListener> getListenerType() {
            return BlockBreakingListener.class;
        }
    }
}

