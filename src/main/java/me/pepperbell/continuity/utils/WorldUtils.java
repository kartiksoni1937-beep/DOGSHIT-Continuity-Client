/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import java.util.Objects;
import java.util.stream.Stream;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.module.modules.client.Fr13nd5;
import me.pepperbell.continuity.utils.RenderUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1675;
import net.minecraft.class_1799;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2818;
import net.minecraft.class_2960;
import net.minecraft.class_3489;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_7923;
import net.minecraft.class_9304;
import net.minecraft.class_9334;

public final class WorldUtils {
    public static boolean isDeadBodyNearby() {
        return Sweto.mc.field_1687.method_18456().parallelStream().filter(e -> e != Sweto.mc.field_1724).filter(e -> e.method_5858((class_1297)Sweto.mc.field_1724) <= 36.0).anyMatch(class_1309::method_29504);
    }

    public static class_1297 findNearestEntity(class_1657 toPlayer, float radius, boolean seeOnly) {
        float mr = Float.MAX_VALUE;
        class_1297 entity = null;
        assert (Sweto.mc.field_1687 != null);
        for (class_1297 e : Sweto.mc.field_1687.method_18112()) {
            float d = e.method_5739((class_1297)toPlayer);
            if (e == toPlayer || !(d <= radius) || Sweto.mc.field_1724.method_6057(e) != seeOnly || !(d < mr)) continue;
            mr = d;
            entity = e;
        }
        return entity;
    }

    public static double distance(class_243 fromVec, class_243 toVec) {
        return Math.sqrt(Math.pow(toVec.field_1352 - fromVec.field_1352, 2.0) + Math.pow(toVec.field_1351 - fromVec.field_1351, 2.0) + Math.pow(toVec.field_1350 - fromVec.field_1350, 2.0));
    }

    public static class_1657 findNearestPlayer(class_1657 toPlayer, float range, boolean seeOnly, boolean excludeFriends) {
        float minRange = Float.MAX_VALUE;
        class_1657 minPlayer = null;
        for (class_1657 player : Sweto.mc.field_1687.method_18456()) {
            float distance = (float)WorldUtils.distance(toPlayer.method_73189(), player.method_73189());
            if (excludeFriends && Sweto.INSTANCE.getModuleManager().getModule(Fr13nd5.class).disableAimAssist.getValue() && Sweto.INSTANCE.getFriendManager().isFriend(player) || player == toPlayer || !(distance <= range) || player.method_6057((class_1297)toPlayer) != seeOnly || !(distance < minRange)) continue;
            minRange = distance;
            minPlayer = player;
        }
        return minPlayer;
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
        return WorldUtils.getPlayerLookVec(player.method_36454(), player.method_36455());
    }

    public static class_239 getHitResult(double radius) {
        return WorldUtils.getHitResult((class_1657)Sweto.mc.field_1724, false, Sweto.mc.field_1724.method_36454(), Sweto.mc.field_1724.method_36455(), radius);
    }

    public static class_239 getHitResult(class_1657 entity, boolean ignoreInvisibles, float yaw, float pitch, double distance) {
        class_238 box;
        class_243 vec3d3;
        class_3966 entityHitResult;
        if (entity == null || Sweto.mc.field_1687 == null) {
            return null;
        }
        double d = distance;
        class_243 cameraPosVec = entity.method_5836(RenderUtils.tickProgress());
        class_243 rotationVec = WorldUtils.getPlayerLookVec(yaw, pitch);
        class_243 range = cameraPosVec.method_1031(rotationVec.field_1352 * d, rotationVec.field_1351 * d, rotationVec.field_1350 * d);
        class_239 result = Sweto.mc.field_1687.method_17742(new class_3959(cameraPosVec, range, class_3959.class_3960.field_17559, class_3959.class_242.field_1348, (class_1297)entity));
        double e = d * d;
        d = distance;
        if (result != null) {
            e = result.method_17784().method_1025(cameraPosVec);
        }
        if ((entityHitResult = class_1675.method_18075((class_1297)entity, (class_243)cameraPosVec, (class_243)(vec3d3 = cameraPosVec.method_1031(rotationVec.field_1352 * d, rotationVec.field_1351 * d, rotationVec.field_1350 * d)), (class_238)(box = entity.method_5829().method_18804(rotationVec.method_1021(d)).method_1009(1.0, 1.0, 1.0)), entityx -> !entityx.method_7325() && entityx.method_5863() && (!entityx.method_5767() || !ignoreInvisibles), (double)e)) != null) {
            class_243 vec3d4 = entityHitResult.method_17784();
            double g = cameraPosVec.method_1025(vec3d4);
            if (distance > distance && g > Math.pow(distance, 2.0) || g < e || result == null) {
                result = g > Math.pow(distance, 2.0) ? class_3965.method_17778((class_243)vec3d4, (class_2350)class_2350.method_10142((double)rotationVec.field_1352, (double)rotationVec.field_1351, (double)rotationVec.field_1350), (class_2338)class_2338.method_49638((class_2374)vec3d4)) : entityHitResult;
            }
        }
        return result;
    }

    public static void placeBlock(class_3965 blockHit, boolean swingHand) {
        if (!WorldUtils.withinBlockRange(blockHit.method_17777())) {
            return;
        }
        Sweto.INSTANCE.rotatorManager.sync();
        class_1269 result = Sweto.mc.field_1761.method_2896(Sweto.mc.field_1724, class_1268.field_5808, blockHit);
        if (result.method_23665() && swingHand) {
            Sweto.mc.field_1724.method_6104(class_1268.field_5808);
        }
    }

    public static void attackBlock(class_2338 pos, class_2350 side) {
        if (!WorldUtils.withinBlockRange(pos)) {
            return;
        }
        Sweto.mc.field_1761.method_2910(pos, side);
    }

    private static boolean withinBlockRange(class_2338 pos) {
        if (Sweto.mc.field_1724 == null || Sweto.mc.field_1687 == null) {
            return false;
        }
        class_243 eyes = Sweto.mc.field_1724.method_33571();
        class_243 target = pos.method_46558();
        double reach = Sweto.mc.field_1724.method_55754();
        return eyes.method_1022(target) <= reach;
    }

    public static Stream<class_2818> getLoadedChunks() {
        int radius = Math.max(2, Sweto.mc.field_1690.method_38521()) + 3;
        int diameter = radius * 2 + 1;
        class_1923 center = Sweto.mc.field_1724.method_31476();
        class_1923 min = new class_1923(center.field_9181 - radius, center.field_9180 - radius);
        class_1923 max = new class_1923(center.field_9181 + radius, center.field_9180 + radius);
        return Stream.iterate(min, pos -> {
            int x = pos.field_9181;
            int z = pos.field_9180;
            if (++x > max.field_9181) {
                x = min.field_9181;
                ++z;
            }
            if (z > max.field_9180) {
                throw new IllegalStateException("Stream limit didn't work.");
            }
            return new class_1923(x, z);
        }).limit((long)diameter * (long)diameter).filter(c -> Sweto.mc.field_1687.method_8393(c.field_9181, c.field_9180)).map(c -> Sweto.mc.field_1687.method_8497(c.field_9181, c.field_9180)).filter(Objects::nonNull);
    }

    public static boolean isShieldFacingAway(class_1657 player) {
        if (Sweto.mc.field_1724 != null && player != null) {
            class_243 playerPos = Sweto.mc.field_1724.method_73189();
            class_243 targetPos = player.method_73189();
            class_243 directionToPlayer = playerPos.method_1020(targetPos).method_1029();
            float yaw = player.method_36454();
            float pitch = player.method_36455();
            class_243 facingDirection = new class_243(-Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)), -Math.sin(Math.toRadians(pitch)), Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch))).method_1029();
            double dotProduct = facingDirection.method_1026(directionToPlayer);
            return dotProduct < 0.0;
        }
        return false;
    }

    public static boolean isSword(class_1799 itemStack) {
        return itemStack.method_31573(class_3489.field_42611);
    }

    public static boolean isAxe(class_1799 itemStack) {
        return itemStack.method_31573(class_3489.field_42612);
    }

    public static boolean isWeapon(class_1799 itemStack) {
        return WorldUtils.isSword(itemStack) || WorldUtils.isAxe(itemStack);
    }

    public static boolean isTool(class_1799 itemStack) {
        return WorldUtils.isWeapon(itemStack) || itemStack.method_31573(class_3489.field_42614) || itemStack.method_31573(class_3489.field_42615) || itemStack.method_31573(class_3489.field_42613);
    }

    public static boolean isCrit(class_1657 player, class_1297 target) {
        return player.method_7261(0.5f) > 0.9f && player.field_6017 > 0.0 && !player.method_24828() && !player.method_6101() && !player.method_5869() && !player.method_6059(class_1294.field_5919) && target instanceof class_1309;
    }

    public static void hitEntity(class_1297 entity, boolean swingHand) {
        Sweto.INSTANCE.rotatorManager.sync();
        Sweto.mc.field_1761.method_2918((class_1657)Sweto.mc.field_1724, entity);
        if (swingHand) {
            Sweto.mc.field_1724.method_6104(class_1268.field_5808);
        }
    }

    public static boolean isSpear(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return false;
        }
        class_2960 id = class_7923.field_41178.method_10221(stack.method_7909());
        if (id == null) {
            return false;
        }
        String path = id.method_12832();
        return path.equals("spear") || path.endsWith("_spear") || path.contains("spear");
    }

    public static boolean hasLungeEnchantment(class_1799 stack) {
        if (!stack.method_57826(class_9334.field_49633)) {
            return false;
        }
        class_9304 enchantments = (class_9304)stack.method_58694(class_9334.field_49633);
        if (enchantments == null) {
            return false;
        }
        return enchantments.method_57534().stream().anyMatch(entry -> {
            String id = entry.method_55840().toLowerCase();
            return id.contains("lunge") || id.contains("lung");
        });
    }
}

