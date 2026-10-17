/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils.rotation;

import net.minecraft.class_3532;

public record Rotation(float yaw, float pitch) {
    public Rotation(double yaw, double pitch) {
        this((float)yaw, (float)pitch);
    }

    public Rotation lerp(Rotation target, float delta) {
        return new Rotation(class_3532.method_17821((float)delta, (float)this.yaw, (float)target.yaw), class_3532.method_16439((float)delta, (float)this.pitch, (float)target.pitch));
    }

    public Rotation wrap() {
        return new Rotation(class_3532.method_15393((float)this.yaw), class_3532.method_15393((float)this.pitch));
    }

    public static float getDelta(float current, float previous) {
        return class_3532.method_15393((float)(current - previous));
    }

    public static double getTotalDiff(Rotation from, Rotation to) {
        return Math.abs(class_3532.method_15393((float)(from.yaw - to.yaw))) + Math.abs(from.pitch - to.pitch);
    }
}

