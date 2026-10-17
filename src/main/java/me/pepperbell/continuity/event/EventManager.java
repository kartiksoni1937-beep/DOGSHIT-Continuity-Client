/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;

public final class EventManager {
    private final HashMap<Class<? extends Listener>, ArrayList<PrioritizedListener<? extends Listener>>> listenerMap = new HashMap();

    public static <L extends Listener, E extends Event<L>> void fire(E event) {
        EventManager eventManager = Sweto.INSTANCE.getEventManager();
        if (eventManager != null) {
            eventManager.fireImpl(event);
        }
    }

    private <L extends Listener, E extends Event<L>> void fireImpl(E event) {
        Class<L> listenerType = event.getListenerType();
        ArrayList<PrioritizedListener<? extends Listener>> listeners = this.listenerMap.get(listenerType);
        if (listeners == null || listeners.isEmpty()) {
            return;
        }
        ArrayList<PrioritizedListener<? extends Listener>> listeners2 = new ArrayList<PrioritizedListener<? extends Listener>>(listeners);
        listeners2.removeIf(Objects::isNull);
        listeners2.sort(Comparator.comparing(listener -> Integer.MAX_VALUE - listener.getPriority()));
        ArrayList<L> listeners3 = new ArrayList<L>();
        for (PrioritizedListener<? extends Listener> listener : listeners2) {
            listeners3.add((L)listener.getListener());
        }
        event.fire(listeners3);
    }

    public <L extends Listener> void add(Class<L> type, L listener) {
        this.add(type, listener, 0);
    }

    public <L extends Listener> void add(Class<L> type, L listener, int priority) {
        ArrayList<PrioritizedListener<? extends Listener>> listeners = this.listenerMap.get(type);
        if (listeners == null) {
            listeners = new ArrayList();
            this.listenerMap.put(type, listeners);
        }
        listeners.add(new PrioritizedListener<L>(listener, priority));
    }

    public <L extends Listener> void remove(Class<L> type, L listener) {
        ArrayList<PrioritizedListener<? extends Listener>> listeners = this.listenerMap.get(type);
        if (listeners != null) {
            listeners.removeIf(l -> l.getListener().equals(listener));
        }
    }

    public void clearAll() {
        this.listenerMap.clear();
    }

    private static class PrioritizedListener<L extends Listener> {
        private final L listener;
        private final int priority;

        public PrioritizedListener(L listener) {
            this(listener, 0);
        }

        public PrioritizedListener(L listener, int priority) {
            this.listener = listener;
            this.priority = priority;
        }

        public int getPriority() {
            return this.priority;
        }

        public L getListener() {
            return this.listener;
        }
    }
}

