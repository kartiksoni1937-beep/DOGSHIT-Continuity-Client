/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.Event;
import me.pepperbell.continuity.event.Listener;
import net.minecraft.class_243;

public interface VelocityStrafeEvent
extends Listener {
    public void onVelocityStrafe(VelocityStrafeData var1);

    public static class VelocityStrafeData
    implements Event<VelocityStrafeEvent> {
        private class_243 V310c17y;
        private final class_243 movementInput;
        private final float speed;
        private final float yaw;

        public VelocityStrafeData(class_243 movementInput, float speed, float yaw, class_243 V310c17y2) {
            this.movementInput = movementInput;
            this.speed = speed;
            this.yaw = yaw;
            this.V310c17y = V310c17y2;
        }

        public class_243 getVelocity() {
            return this.V310c17y;
        }

        public void setVelocity(class_243 V310c17y2) {
            this.V310c17y = V310c17y2;
        }

        public class_243 getMovementInput() {
            return this.movementInput;
        }

        public float getSpeed() {
            return this.speed;
        }

        public float getYaw() {
            return this.yaw;
        }

        @Override
        public void fire(List<VelocityStrafeEvent> listeners) {
            listeners.forEach(l -> l.onVelocityStrafe(this));
        }

        @Override
        public Class<VelocityStrafeEvent> getListenerType() {
            return VelocityStrafeEvent.class;
        }
    }
}

