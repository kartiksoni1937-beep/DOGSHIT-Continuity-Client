/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.events.ModuleToggleListener;
import me.pepperbell.continuity.module.Module;

public record ModuleToggleEvent(Module module, boolean state) implements Event<ModuleToggleListener>
{
    @Override
    public Class<ModuleToggleListener> getListenerType() {
        return ModuleToggleListener.class;
    }

    @Override
    public void fire(List<ModuleToggleListener> listeners) {
        listeners.forEach(l -> l.onModuleToggle(this));
    }
}

