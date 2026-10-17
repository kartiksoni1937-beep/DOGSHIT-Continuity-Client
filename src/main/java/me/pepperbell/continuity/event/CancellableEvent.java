/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event;

import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;

public abstract class CancellableEvent<T extends Listener>
implements Event<T> {
    private boolean isCancelled = false;

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void cancel() {
        this.isCancelled = true;
    }
}

