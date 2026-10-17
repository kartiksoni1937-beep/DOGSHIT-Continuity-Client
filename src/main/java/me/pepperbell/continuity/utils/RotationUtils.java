/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.rotation.Rotation;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public final class RotationUtils {
    public static class_243 getEyesPos(class_1657 player) {
        return player.method_5836(RenderUtils.tickProgress());
    }

    public static Rotation getRotationToVec(class_243 origin, class_243 target) {
        double dx = target.field_1352 - origin.field_1352;
        double dy = target.field_1351 - origin.field_1351;
        double dz = target.field_1350 - origin.field_1350;
        double dist = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0));
        float pitch = (float)(-class_3532.method_15338((double)Math.toDegrees(Math.atan2(dy, dist))));
        return new Rotation(yaw, pitch);
    }

    public static double getSensitivityStep() {
        if (Sweto.mc.field_1690 == null) {
            return 0.007042253408581021;
        }
        double sens = (Double)Sweto.mc.field_1690.method_42495().method_41753();
        double f = sens * 0.6 + 0.2;
        double g = f * f * f * 8.0;
        return g * (double)0.15f;
    }

    public static Rotation applySensitivity(Rotation target, Rotation start) {
        float yawDiff = class_3532.method_15393((float)(target.yaw() - start.yaw()));
        float pitchDiff = target.pitch() - start.pitch();
        double step = RotationUtils.getSensitivityStep();
        if (step == 0.0) {
            return target;
        }
        int mouseDeltaX = (int)Math.round((double)yawDiff / step);
        int mouseDeltaY = (int)Math.round((double)pitchDiff / step);
        float fixedYaw = (float)((double)start.yaw() + (double)mouseDeltaX * step);
        float fixedPitch = (float)((double)start.pitch() + (double)mouseDeltaY * step);
        fixedPitch = class_3532.method_15363((float)fixedPitch, (float)-90.0f, (float)90.0f);
        return new Rotation(fixedYaw, fixedPitch);
    }

    public static class_241 rotateInput(float forward, float sideways, float targetYaw, float currentYaw) {
        double angle = Math.toRadians(class_3532.method_15393((float)(currentYaw - targetYaw)));
        float cos = (float)Math.cos(angle);
        float sin = (float)Math.sin(angle);
        float correctedForward = forward * cos + sideways * sin;
        float correctedSideways = sideways * cos - forward * sin;
        return new class_241(correctedSideways, correctedForward);
    }

    public static class_243 getPlayerLookVec(float yaw, float pitch) {
        float f = pitch * ((float)Math.PI / 180);
        float g = -yaw * ((float)Math.PI / 180);
        float h = class_3532.method_15362((double)g);
        float i = class_3532.method_15374((double)g);
        float j = class_3532.method_15362((double)f);
        float k = class_3532.method_15374((double)f);
        return new class_243((double)(i * j), (double)(-k), (double)(h * j));
    }

    public static class_243 getPlayerLookVec(class_1657 player) {
        return RotationUtils.getPlayerLookVec(player.method_36454(), player.method_36455());
    }

    public static Rotation getDirection(class_1297 entity, class_243 vec) {
        double dx = vec.field_1352 - entity.method_23317();
        double dy = vec.field_1351 - entity.method_23318();
        double dz = vec.field_1350 - entity.method_23321();
        double dist = class_3532.method_15355((float)((float)(dx * dx + dz * dz)));
        return new Rotation(class_3532.method_15338((double)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0)), -class_3532.method_15338((double)Math.toDegrees(Math.atan2(dy, dist))));
    }

    public static double getAngleToRotation(Rotation rotation) {
        float currentYaw = class_3532.method_15393((float)Sweto.mc.field_1724.method_36454());
        float currentPitch = class_3532.method_15393((float)Sweto.mc.field_1724.method_36455());
        float diffYaw = class_3532.method_15393((float)(currentYaw - rotation.yaw()));
        float diffPitch = currentPitch - rotation.pitch();
        return Math.sqrt(diffYaw * diffYaw + diffPitch * diffPitch);
    }

    public static Rotation getSmoothRotation(Rotation from, Rotation to, double speed) {
        return new Rotation(class_3532.method_17821((float)((float)speed), (float)from.yaw(), (float)to.yaw()), class_3532.method_16439((float)((float)speed), (float)from.pitch(), (float)to.pitch()));
    }

    public static class_243 getClosestPointOnBox(class_243 origin, class_243 lookVec, class_238 box) {
        double x = class_3532.method_15350((double)origin.field_1352, (double)box.field_1323, (double)box.field_1320);
        double y = class_3532.method_15350((double)origin.field_1351, (double)box.field_1322, (double)box.field_1325);
        double z = class_3532.method_15350((double)origin.field_1350, (double)box.field_1321, (double)box.field_1324);
        return new class_243(x, y, z);
    }

    public static double getTotalDiff(Rotation from, Rotation to) {
        return Math.abs(class_3532.method_15393((float)(from.yaw() - to.yaw()))) + Math.abs(from.pitch() - to.pitch());
    }
}

