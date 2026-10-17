/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;
import net.minecraft.class_4587;

public interface GameRenderListener
extends Listener {
    public void onGameRender(GameRenderEvent var1);

    public static class GameRenderEvent
    implements Event<GameRenderListener> {
        public class_4587 matrices;
        public float delta;

        public GameRenderEvent(class_4587 matrices, float delta) {
            this.matrices = matrices;
            this.delta = delta;
        }

        @Override
        public void fire(List<GameRenderListener> listeners) {
            listeners.forEach(e -> e.onGameRender(this));
        }

        @Override
        public Class<GameRenderListener> getListenerType() {
            return GameRenderListener.class;
        }
    }
}

