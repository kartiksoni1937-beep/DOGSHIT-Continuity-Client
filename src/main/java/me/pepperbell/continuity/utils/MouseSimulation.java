/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.ButtonListener;

public final class MouseSimulation {
    public static HashMap<Integer, Boolean> mouseButtons = new HashMap();
    public static ExecutorService clickExecutor = Executors.newFixedThreadPool(100);

    public static boolean isMouseButtonPressed(int keyCode) {
        Boolean key = mouseButtons.get(keyCode);
        return key != null ? key : false;
    }

    public static void mousePress(int keyCode) {
        mouseButtons.put(keyCode, true);
        EventManager.fire(new ButtonListener.ButtonEvent(keyCode, Sweto.mc.method_22683().method_4490(), 1, 0));
    }

    public static void mouseRelease(int keyCode) {
        mouseButtons.put(keyCode, false);
        EventManager.fire(new ButtonListener.ButtonEvent(keyCode, Sweto.mc.method_22683().method_4490(), 0, 0));
    }

    public static void mouseClick(int keyCode, int millis) {
        clickExecutor.submit(() -> {
            try {
                MouseSimulation.mousePress(keyCode);
                Thread.sleep(millis);
                MouseSimulation.mouseRelease(keyCode);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
        });
    }

    public static void mouseClick(int keyCode) {
        MouseSimulation.mouseClick(keyCode, 35);
    }
}

