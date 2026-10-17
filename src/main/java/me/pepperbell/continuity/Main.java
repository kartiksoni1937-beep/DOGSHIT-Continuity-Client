/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity;

import java.io.IOException;
import me.pepperbell.continuity.Sweto;
import net.fabricmc.api.ClientModInitializer;

public final class Main
implements ClientModInitializer {
    public void onInitializeClient() {
        try {
            new Sweto();
        }
        catch (IOException | InterruptedException exception) {
            // empty catch block
        }
    }
}

