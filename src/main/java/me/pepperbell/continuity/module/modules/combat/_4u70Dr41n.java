/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.TimerUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2885;
import net.minecraft.class_3965;

public final class _4u70Dr41n
extends Module
implements TickListener {
    private final SliderSetting radius = new SliderSetting(EncryptedString.of("R-\u00ad`a*|d`\u00ad$-i^|-_u.^s"), 4.0, 1.0, 6.0, 0.5);
    private final BooleanSetting water = new BooleanSetting(EncryptedString.of("W-_~a.|$\u00b7t\u00ad.\u00b7e`.`^r"), true);
    private final BooleanSetting lava = new BooleanSetting(EncryptedString.of("L.\u00b7a~\u00ad-v*^\u00b7a"), true);
    private final BooleanSetting useBuckets = new BooleanSetting(EncryptedString.of("U|\u00b7|*s\u00ad_e\u00b7~| |\u00b7B_$*u^._c*\u00b7k~`^e_\u00ad|\u00b7t-`~s"), true);
    private final BooleanSetting useWebs = new BooleanSetting(EncryptedString.of("U|\u00b7^s\u00ad-_e-*^ `^_W|_|\u00b7e\u00b7\u00adb--|\u00b7s"), false);
    private final MinMaxSetting delay = new MinMaxSetting(EncryptedString.of("D.*e^^`^l_*.a`~|y"), 0.0, 500.0, 50.0, 0.0, 100.0);
    private final TimerUtils timer = new TimerUtils();

    public _4u70Dr41n() {
        super(EncryptedString.of("A~`u_|t`|$\u00ado-$ \u00b7.^.D^*r.*^^a.*-|i*-._n"), EncryptedString.of("A_^.^u$|$t\u00b7__o$-m.|`-a-*t_`-~i`~__c`|$.a-|._l~-*`l_-y_\u00b7^ $-.\u00adr_*_*e.~$~m\u00b7.\u00b7o~\u00adv~_e*$s\u00ad-`$ ~*|l^\u00b7*a|\u00b7^~v*\u00ad^$a~\u00ad\u00ad _.~a*\u00b7$\u00b7n$\u00b7^d\u00ad\u00ad `$w^_^a.`|t\u00b7\u00ade$.$^r\u00ad|- \u00ad-`u~.s~$*i*^|_n$\u00ad|g|`~| \u00b7\u00adb_^$`u\u00b7^*|c$-\u00ad.k_~_`e~-_t_. _$^*o^~\u00ad_r-*_| ^`\u00b7w*-\u00ad`e.`b.*$|s"), -1, Category.COMBAT);
        this.addSettings(this.radius, this.water, this.lava, this.useBuckets, this.useWebs, this.delay);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.timer.reset();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (!this.timer.delay((long)this.delay.getRandomValue())) {
            return;
        }
        int r = (int)this.radius.getValue();
        class_2338 playerPos = this.mc.field_1724.method_24515();
        for (int x = -r; x <= r; ++x) {
            for (int y = -r; y <= r; ++y) {
                for (int z = -r; z <= r; ++z) {
                    int webSlot;
                    class_2338 targetPos = playerPos.method_10069(x, y, z);
                    class_2680 state = this.mc.field_1687.method_8320(targetPos);
                    if (state.method_26227().method_15769() || !state.method_26227().method_15771()) continue;
                    boolean isWater = state.method_27852(class_2246.field_10382);
                    boolean isLava = state.method_27852(class_2246.field_10164);
                    if (isWater && !this.water.getValue() || isLava && !this.lava.getValue()) continue;
                    int bucketSlot = this.useBuckets.getValue() ? this.findSlot(class_1802.field_8550) : -1;
                    int n = webSlot = this.useWebs.getValue() ? this.findSlot(class_1802.field_8786) : -1;
                    if (bucketSlot != -1) {
                        this.drainFluid(targetPos, bucketSlot);
                        this.timer.reset();
                        return;
                    }
                    if (webSlot == -1) continue;
                    this.placeBlock(targetPos, webSlot);
                    this.timer.reset();
                    return;
                }
            }
        }
    }

    private void drainFluid(class_2338 pos, int slot) {
        int oldSlot = this.mc.field_1724.method_31548().method_67532();
        this.mc.field_1724.method_31548().method_61496(slot);
        class_3965 hit = new class_3965(class_243.method_24953((class_2382)pos), class_2350.field_11036, pos, false);
        this.mc.method_1562().method_52787((class_2596)new class_2885(class_1268.field_5808, hit, 0));
        this.mc.field_1724.method_31548().method_61496(oldSlot);
    }

    private void placeBlock(class_2338 pos, int slot) {
        int oldSlot = this.mc.field_1724.method_31548().method_67532();
        this.mc.field_1724.method_31548().method_61496(slot);
        class_3965 hit = new class_3965(class_243.method_24953((class_2382)pos), class_2350.field_11036, pos, false);
        this.mc.method_1562().method_52787((class_2596)new class_2885(class_1268.field_5808, hit, 0));
        this.mc.field_1724.method_31548().method_61496(oldSlot);
    }

    private int findSlot(class_1792 item) {
        for (int i = 0; i < 9; ++i) {
            if (this.mc.field_1724.method_31548().method_5438(i).method_7909() != item) continue;
            return i;
        }
        return -1;
    }
}

