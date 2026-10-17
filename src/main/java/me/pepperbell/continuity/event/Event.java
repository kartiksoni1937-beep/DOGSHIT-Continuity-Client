/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event;

import java.util.List;
import me.pepperbell.continuity.event.Listener;

public interface Event<T extends Listener> {
    public void fire(List<T> var1);

    public Class<T> getListenerType();
}

