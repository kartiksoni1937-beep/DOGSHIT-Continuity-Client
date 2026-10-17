/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;

public interface InputListener
extends Listener {
    public void onInputUpdate(InputEvent var1);

    public static class InputEvent
    implements Event<InputListener> {
        private float movementForward;
        private float movementSideways;
        private boolean jumping;
        private boolean sneaking;

        public InputEvent(float movementForward, float movementSideways, boolean jumping, boolean sneaking) {
            this.movementForward = movementForward;
            this.movementSideways = movementSideways;
            this.jumping = jumping;
            this.sneaking = sneaking;
        }

        public float getMovementForward() {
            return this.movementForward;
        }

        public void setMovementForward(float movementForward) {
            this.movementForward = movementForward;
        }

        public float getMovementSideways() {
            return this.movementSideways;
        }

        public void setMovementSideways(float movementSideways) {
            this.movementSideways = movementSideways;
        }

        public boolean isJumping() {
            return this.jumping;
        }

        public void setJumping(boolean jumping) {
            this.jumping = jumping;
        }

        public boolean isSneaking() {
            return this.sneaking;
        }

        public void setSneaking(boolean sneaking) {
            this.sneaking = sneaking;
        }

        @Override
        public void fire(List<InputListener> listeners) {
            listeners.forEach(l -> l.onInputUpdate(this));
        }

        @Override
        public Class<InputListener> getListenerType() {
            return InputListener.class;
        }
    }
}

