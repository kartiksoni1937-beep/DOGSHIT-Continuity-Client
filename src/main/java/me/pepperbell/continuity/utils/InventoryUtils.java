/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.mixin.ClientPlayerInteractionManagerAccessor;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1661;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1828;
import net.minecraft.class_1844;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2868;
import net.minecraft.class_7923;
import net.minecraft.class_9334;

public final class InventoryUtils {
    public static void setInvSlot(int slot) {
        Sweto.mc.field_1724.method_31548().method_61496(slot);
        ((ClientPlayerInteractionManagerAccessor)Sweto.mc.field_1761).syncSlot();
    }

    public static void sendSilentSlot(int slot) {
        if (Sweto.mc.field_1724 != null && Sweto.mc.method_1562() != null) {
            Sweto.mc.method_1562().method_52787((class_2596)new class_2868(slot));
        }
    }

    public static boolean selectItemFromHotbar(Predicate<class_1792> item) {
        class_1661 inv = Sweto.mc.field_1724.method_31548();
        for (int i = 0; i < 9; ++i) {
            class_1799 itemStack = inv.method_5438(i);
            if (!item.test(itemStack.method_7909())) continue;
            inv.method_61496(i);
            return true;
        }
        return false;
    }

    public static boolean selectItemFromHotbar(class_1792 item) {
        return InventoryUtils.selectItemFromHotbar((class_1792 i) -> i == item);
    }

    public static boolean hasItemInHotbar(Predicate<class_1792> item) {
        class_1661 inv = Sweto.mc.field_1724.method_31548();
        for (int i = 0; i < 9; ++i) {
            class_1799 itemStack = inv.method_5438(i);
            if (!item.test(itemStack.method_7909())) continue;
            return true;
        }
        return false;
    }

    public static int countItem(Predicate<class_1792> item) {
        class_1661 inv = Sweto.mc.field_1724.method_31548();
        int count = 0;
        for (int i = 0; i < 36; ++i) {
            class_1799 itemStack = inv.method_5438(i);
            if (!item.test(itemStack.method_7909())) continue;
            count += itemStack.method_7947();
        }
        return count;
    }

    public static int countItemExceptHotbar(Predicate<class_1792> item) {
        class_1661 inv = Sweto.mc.field_1724.method_31548();
        int count = 0;
        for (int i = 9; i < 36; ++i) {
            class_1799 itemStack = inv.method_5438(i);
            if (!item.test(itemStack.method_7909())) continue;
            count += itemStack.method_7947();
        }
        return count;
    }

    public static int getSwordSlot() {
        class_1661 playerInventory = Sweto.mc.field_1724.method_31548();
        for (int itemIndex = 0; itemIndex < 9; ++itemIndex) {
            if (!WorldUtils.isSword(playerInventory.method_5438(itemIndex))) continue;
            return itemIndex;
        }
        return -1;
    }

    public static boolean selectSword() {
        int itemIndex = InventoryUtils.getSwordSlot();
        if (itemIndex != -1) {
            InventoryUtils.setInvSlot(itemIndex);
            return true;
        }
        return false;
    }

    public static int findSplash(class_1291 type, int duration, int amplifier) {
        class_1661 inv = Sweto.mc.field_1724.method_31548();
        class_1293 potion = new class_1293(class_7923.field_41174.method_47983(type), duration, amplifier);
        for (int i = 0; i < 9; ++i) {
            String s;
            class_1799 itemStack = inv.method_5438(i);
            if (!(itemStack.method_7909() instanceof class_1828) || !(s = ((class_1844)itemStack.method_58694(class_9334.field_49651)).method_57397().toString()).contains(potion.toString())) continue;
            return i;
        }
        return -1;
    }

    public static boolean isThatSplash(class_1291 type, int duration, int amplifier, class_1799 itemStack) {
        class_1293 potion = new class_1293(class_7923.field_41174.method_47983(type), duration, amplifier);
        return itemStack.method_7909() instanceof class_1828 && ((class_1844)itemStack.method_58694(class_9334.field_49651)).method_57397().toString().contains(potion.toString());
    }

    public static int findTotemSlot() {
        assert (Sweto.mc.field_1724 != null);
        class_1661 inv = Sweto.mc.field_1724.method_31548();
        for (int index = 9; index < 36; ++index) {
            if (inv.method_5438(index).method_7909() != class_1802.field_8288) continue;
            return index;
        }
        return -1;
    }

    public static boolean selectAxe() {
        int itemIndex = InventoryUtils.getAxeSlot();
        if (itemIndex != -1) {
            Sweto.mc.field_1724.method_31548().method_61496(itemIndex);
            return true;
        }
        return false;
    }

    public static int findRandomTotemSlot() {
        class_1661 inventory = Sweto.mc.field_1724.method_31548();
        Random random = new Random();
        ArrayList<Integer> totemIndexes = new ArrayList<Integer>();
        for (int i = 9; i < 36; ++i) {
            if (inventory.method_5438(i).method_7909() != class_1802.field_8288) continue;
            totemIndexes.add(i);
        }
        if (!totemIndexes.isEmpty()) {
            return (Integer)totemIndexes.get(random.nextInt(totemIndexes.size()));
        }
        return -1;
    }

    public static int findRandomPot(String potion) {
        class_1661 inventory = Sweto.mc.field_1724.method_31548();
        Random random = new Random();
        int slotIndex = random.nextInt(27) + 9;
        for (int i = 0; i < 27; ++i) {
            int index = (slotIndex + i) % 36;
            class_1799 itemStack = inventory.method_5438(index);
            if (!(itemStack.method_7909() instanceof class_1828) || index == 36 && index == 37 && index == 38 && index == 39) continue;
            if (!((class_1844)itemStack.method_58694(class_9334.field_49651)).method_57397().toString().contains(potion.toString())) {
                return -1;
            }
            return index;
        }
        return -1;
    }

    public static int findPot(class_1291 effect, int duration, int amplifier) {
        assert (Sweto.mc.field_1724 != null);
        class_1661 inv = Sweto.mc.field_1724.method_31548();
        class_1293 instance = new class_1293(class_7923.field_41174.method_47983(effect), duration, amplifier);
        for (int index = 9; index < 34; ++index) {
            if (!(inv.method_5438(index).method_7909() instanceof class_1828) || !((class_1844)inv.method_5438(index).method_58694(class_9334.field_49651)).method_57397().toString().contains(instance.toString())) continue;
            return index;
        }
        return -1;
    }

    public static List<Integer> getEmptyHotbarSlots() {
        class_1661 inventory = Sweto.mc.field_1724.method_31548();
        ArrayList<Integer> slots = new ArrayList<Integer>();
        for (int i = 0; i < 9; ++i) {
            if (inventory.method_5438(i).method_7960()) {
                slots.add(i);
                continue;
            }
            if (!slots.contains(i) || inventory.method_5438(i).method_7960()) continue;
            slots.remove(i);
        }
        return slots;
    }

    public static int getAxeSlot() {
        class_1661 playerInventory = Sweto.mc.field_1724.method_31548();
        for (int itemIndex = 0; itemIndex < 9; ++itemIndex) {
            if (!WorldUtils.isAxe(playerInventory.method_5438(itemIndex))) continue;
            return itemIndex;
        }
        return -1;
    }

    public static int getMaceSlot() {
        class_1661 playerInventory = Sweto.mc.field_1724.method_31548();
        for (int itemIndex = 0; itemIndex < 9; ++itemIndex) {
            if (playerInventory.method_5438(itemIndex).method_7909() != class_1802.field_49814) continue;
            return itemIndex;
        }
        return -1;
    }

    public static int countItem(class_1792 item) {
        return InventoryUtils.countItem((class_1792 i) -> i == item);
    }

    public static int getBestTool(class_2680 state) {
        int bestSlot = -1;
        double maxSpeed = 1.0;
        for (int i = 0; i < 9; ++i) {
            float speed;
            class_1799 stack = Sweto.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960() || !((double)(speed = stack.method_7924(state)) > maxSpeed)) continue;
            maxSpeed = speed;
            bestSlot = i;
        }
        return bestSlot;
    }

    public static int getSlot(class_1792 item) {
        for (int i = 0; i < 36; ++i) {
            if (Sweto.mc.field_1724.method_31548().method_5438(i).method_7909() != item) continue;
            return i;
        }
        return -1;
    }
}

