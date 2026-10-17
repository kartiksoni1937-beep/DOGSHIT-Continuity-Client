/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils.rotation;

import java.lang.reflect.Field;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.MovementPacketListener;
import me.pepperbell.continuity.event.events.MovementPacketPostListener;
import me.pepperbell.continuity.event.events.PacketReceiveListener;
import me.pepperbell.continuity.event.events.PacketSendListener;
import me.pepperbell.continuity.event.events.VelocityStrafeEvent;
import me.pepperbell.continuity.utils.rotation.Rotation;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2828;
import net.minecraft.class_3532;

public final class R0T1AT0RM4NG3R
implements PacketSendListener,
MovementPacketListener,
MovementPacketPostListener,
PacketReceiveListener,
VelocityStrafeEvent {
    private float currentYaw;
    private float currentPitch;
    private float targetYaw;
    private float targetPitch;
    private float serverYaw;
    private float serverPitch;
    private float visualYaw;
    private float visualPitch;
    private float prevVisualYaw;
    private float prevVisualPitch;
    private boolean active;
    private long lastUpdateTime;

    public R0T1AT0RM4NG3R() {
        EventManager em = Sweto.INSTANCE.eventManager;
        em.add(PacketSendListener.class, this);
        em.add(MovementPacketListener.class, this);
        em.add(MovementPacketPostListener.class, this);
        em.add(PacketReceiveListener.class, this);
        em.add(VelocityStrafeEvent.class, this);
        this.syncFromPlayer();
    }

    public void setRotation(Rotation rotation) {
        if (Float.isNaN(rotation.yaw()) || Float.isNaN(rotation.pitch())) {
            return;
        }
        this.targetYaw = rotation.yaw();
        this.targetPitch = rotation.pitch();
        this.active = true;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public void reset() {
        this.active = false;
        this.targetYaw = 0.0f;
        this.targetPitch = 0.0f;
        this.syncFromPlayer();
    }

    @Override
    public void onSendMovementPackets() {
        float pitchDelta;
        if (Sweto.mc.field_1724 == null || Sweto.mc.field_1724.method_29504()) {
            return;
        }
        this.prevVisualYaw = this.visualYaw;
        this.prevVisualPitch = this.visualPitch;
        if (!this.active) {
            this.syncFromPlayer();
            return;
        }
        if (System.currentTimeMillis() - this.lastUpdateTime > 250L) {
            this.reset();
            return;
        }
        float yawDelta = class_3532.method_15393((float)(this.targetYaw - this.currentYaw));
        float diff = (float)Math.hypot(yawDelta, pitchDelta = this.targetPitch - this.currentPitch);
        if (diff > 0.01f) {
            float proximityFactor = Math.min(1.0f, diff / 12.0f);
            proximityFactor = Math.max(0.15f, 0.25f + 0.75f * proximityFactor);
            float perTickRandom = 0.8f + (float)(Math.random() * 0.4);
            float scale = 0.55f + (float)(Math.random() * 0.25);
            float stepYaw = yawDelta * scale * proximityFactor * perTickRandom;
            float stepPitch = pitchDelta * scale * proximityFactor * perTickRandom;
            float maxYaw = 22.0f * proximityFactor * perTickRandom;
            float maxPitch = 16.0f * proximityFactor * perTickRandom;
            stepYaw = class_3532.method_15363((float)stepYaw, (float)(-maxYaw), (float)maxYaw);
            stepPitch = class_3532.method_15363((float)stepPitch, (float)(-maxPitch), (float)maxPitch);
            if (Math.abs(yawDelta) > 1.0f) {
                stepYaw += (float)((Math.random() - 0.5) * 0.2);
            }
            if (Math.abs(pitchDelta) > 1.0f) {
                stepPitch += (float)((Math.random() - 0.5) * 0.12);
            }
            this.currentYaw += stepYaw;
            this.currentPitch = class_3532.method_15363((float)(this.currentPitch + stepPitch), (float)-90.0f, (float)90.0f);
        }
        double gcd = R0T1AT0RM4NG3R.computeGcd();
        float prevServerYaw = this.serverYaw;
        float prevServerPitch = this.serverPitch;
        double ditherYaw = (Math.random() - 0.5) * gcd * 0.04;
        double ditherPitch = (Math.random() - 0.5) * gcd * 0.04;
        float deltaYaw = this.currentYaw - prevServerYaw;
        float deltaPitch = this.currentPitch - prevServerPitch;
        this.serverYaw = prevServerYaw + (float)Math.round(((double)deltaYaw + ditherYaw) / gcd) * (float)gcd;
        this.serverPitch = class_3532.method_15363((float)(prevServerPitch + (float)Math.round(((double)deltaPitch + ditherPitch) / gcd) * (float)gcd), (float)-90.0f, (float)90.0f);
        this.visualYaw = R0T1AT0RM4NG3R.lerpAngle(0.5f, this.visualYaw, this.serverYaw);
        this.visualPitch = class_3532.method_16439((float)0.5f, (float)this.visualPitch, (float)this.serverPitch);
    }

    @Override
    public void onSendMovementPacketsPost() {
        if (Sweto.mc.field_1724 == null || !this.active) {
            return;
        }
        float diff = class_3532.method_15393((float)(this.visualYaw - Sweto.mc.field_1724.field_6283));
        if (Math.abs(diff) > 45.0f) {
            Sweto.mc.field_1724.field_6283 = class_3532.method_15393((float)(this.visualYaw - (diff > 0.0f ? 45.0f : -45.0f)));
        }
    }

    @Override
    public void onPacketSend(PacketSendListener.PacketSendEvent event) {
        class_2596 class_25962 = event.packet;
        if (class_25962 instanceof class_2828) {
            class_2828 packet = (class_2828)class_25962;
            if (this.active) {
                R0T1AT0RM4NG3R.modifyPacketYawPitch(packet, this.serverYaw, this.serverPitch);
            }
        }
    }

    private static void modifyPacketYawPitch(class_2828 packet, float yaw, float pitch) {
        Class<?> cls = packet.getClass();
        int floatIdx = 0;
        for (Field f : cls.getDeclaredFields()) {
            if (f.getType() != Float.TYPE) continue;
            f.setAccessible(true);
            try {
                f.setFloat(packet, floatIdx == 0 ? yaw : pitch);
            }
            catch (IllegalAccessException illegalAccessException) {
                // empty catch block
            }
            if (++floatIdx < 2) continue;
            return;
        }
    }

    @Override
    public void onPacketReceive(PacketReceiveListener.PacketReceiveEvent event) {
        class_2596 class_25962 = event.packet;
        if (class_25962 instanceof class_2708) {
            class_2708 packet = (class_2708)class_25962;
            float y = packet.comp_3228().comp_3150();
            float p = packet.comp_3228().comp_3151();
            this.currentYaw = y;
            this.currentPitch = p;
            this.serverYaw = y;
            this.serverPitch = p;
            this.visualYaw = y;
            this.visualPitch = p;
            this.prevVisualYaw = y;
            this.prevVisualPitch = p;
            this.reset();
        }
    }

    @Override
    public void onVelocityStrafe(VelocityStrafeEvent.VelocityStrafeData event) {
        if (!this.active) {
            return;
        }
        float yaw = (float)Math.toRadians(this.serverYaw);
        class_243 movementInput = event.getMovementInput();
        double d = movementInput.method_1027();
        if (d < 1.0E-7) {
            event.setVelocity(class_243.field_1353);
            return;
        }
        double speed = event.getSpeed();
        class_243 input = (d > 1.0 ? movementInput.method_1029() : movementInput).method_1021(speed);
        float sin = class_3532.method_15374((double)yaw);
        float cos = class_3532.method_15362((double)yaw);
        event.setVelocity(new class_243(input.method_10216() * (double)cos - input.method_10215() * (double)sin, input.method_10214(), input.method_10215() * (double)cos + input.method_10216() * (double)sin));
    }

    public float getServerYaw() {
        return this.serverYaw;
    }

    public float getServerPitch() {
        return this.serverPitch;
    }

    public Rotation getServerRotation() {
        return new Rotation(this.serverYaw, this.serverPitch);
    }

    public boolean isActive() {
        return this.active;
    }

    public float getVisualYaw() {
        return this.visualYaw;
    }

    public float getVisualPitch() {
        return this.visualPitch;
    }

    public float getPrevVisualYaw() {
        return this.prevVisualYaw;
    }

    public float getPrevVisualPitch() {
        return this.prevVisualPitch;
    }

    public void setMovementFix(boolean fix) {
    }

    public void sync() {
    }

    private void syncFromPlayer() {
        if (Sweto.mc.field_1724 == null) {
            return;
        }
        float y = Sweto.mc.field_1724.method_36454();
        float p = Sweto.mc.field_1724.method_36455();
        this.currentYaw = y;
        this.currentPitch = p;
        this.serverYaw = y;
        this.serverPitch = p;
        this.visualYaw = y;
        this.visualPitch = p;
        this.prevVisualYaw = y;
        this.prevVisualPitch = p;
    }

    private static double computeGcd() {
        if (Sweto.mc.field_1690 == null) {
            return 0.15f;
        }
        double sens = (Double)Sweto.mc.field_1690.method_42495().method_41753();
        double f = sens * 0.6 + 0.2;
        double g = f * f * f * 8.0;
        return g * (double)0.15f;
    }

    private static float lerpAngle(float delta, float from, float to) {
        return class_3532.method_17821((float)delta, (float)from, (float)to);
    }
}

