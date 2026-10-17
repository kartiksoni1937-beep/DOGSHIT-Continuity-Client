package me.pepperbell.continuity.module.modules.client;

import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;

/**
 * Compatibility placeholder for a class that could not be safely recovered
 * from the original bytecode. It retains the original binary class name so
 * existing module registrations and references continue to resolve.
 */
public final class _531fD357ruc7 extends Module {
    public static volatile boolean b;

    public _531fD357ruc7() {
        super("Compatibility", "Recovered compatibility placeholder", -1, Category.CLIENT);
    }

    @Override
    public void onEnable() {
        setEnabled(false);
    }
}
