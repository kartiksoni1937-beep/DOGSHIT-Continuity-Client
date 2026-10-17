/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import me.pepperbell.continuity.Sweto;
import net.minecraft.class_243;
import net.minecraft.class_4184;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public final class ProjectionUtils {
    private ProjectionUtils() {
    }

    @Nullable
    public static ProjectedPoint project(class_243 worldPos) {
        if (Sweto.mc.field_1687 == null || Sweto.mc.field_1773 == null) {
            return null;
        }
        class_4184 camera = Sweto.mc.field_1773.method_19418();
        if (camera == null) {
            return null;
        }
        int width = Sweto.mc.method_22683().method_4486();
        int height = Sweto.mc.method_22683().method_4502();
        if (width <= 0 || height <= 0) {
            return null;
        }
        class_243 cameraPos = camera.method_71156();
        Vector4f clip = new Vector4f((float)(worldPos.field_1352 - cameraPos.field_1352), (float)(worldPos.field_1351 - cameraPos.field_1351), (float)(worldPos.field_1350 - cameraPos.field_1350), 1.0f);
        Matrix4f view = new Matrix4f().rotateX((float)Math.toRadians(camera.method_19329())).rotateY((float)Math.toRadians(camera.method_19330() + 180.0f));
        Matrix4f projection = new Matrix4f().setPerspective((float)Math.toRadians(((Integer)Sweto.mc.field_1690.method_41808().method_41753()).intValue()), (float)width / (float)height, 0.05f, (float)((Integer)Sweto.mc.field_1690.method_42503().method_41753()).intValue() * 16.0f);
        view.transform(clip);
        projection.transform(clip);
        if (clip.w <= 0.0f) {
            return null;
        }
        float ndcX = clip.x / clip.w;
        float ndcY = clip.y / clip.w;
        float ndcZ = clip.z / clip.w;
        if (ndcZ < -1.0f || ndcZ > 1.0f) {
            return null;
        }
        float screenX = (ndcX * 0.5f + 0.5f) * (float)width;
        float screenY = (1.0f - (ndcY * 0.5f + 0.5f)) * (float)height;
        return new ProjectedPoint(screenX, screenY, ndcZ);
    }

    public record ProjectedPoint(float x, float y, float depth) {
    }
}

