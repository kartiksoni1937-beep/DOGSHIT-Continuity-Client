/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1799;

public final class _5p34rM4c3D0u81374p
extends Module
implements TickListener {
    private final SliderSetting timeout = new SliderSetting(EncryptedString.of("T\u00ad*\u00ad|i`|_`m\u00b7^e^*.\u00ado*$~_u^`t`\u00ad~ `$(_\u00b7^_m\u00b7_$s*-$$)"), 500.0, 5000.0, 3000.0, 100.0);
    private State currentState = State.IDLE;
    private int originalSlot = -1;
    private long actionStartTime = 0L;
    private boolean attackPressedLastTick = false;

    public _5p34rM4c3D0u81374p() {
        super(EncryptedString.of("S$$^~p~|^e|`-a_^r._ \u00ad*M|~~-a|.$^c*.~^e\u00b7| .__D$-o~--*u`-^~b|$-\u00adl*$e|. *-T*\u00b7a-`^~p"), EncryptedString.of("D-`.o~\u00b7~u*\u00ad\u00ad-b`~*l^$e.-^\u00ad $$$^t`.\u00b7a$-.`p$*~s~$\u00ad _.|\u00adp^|e\u00b7||`o_.p._l|^e.\u00b7 ~_w~_i`*..t.-$`h^| |\u00ad*a|\u00b7`$ |$s~_`~p\u00ad$_*e\u00b7^*a\u00b7.~|r\u00ad` \u00ad^`t|.^_h``\u00b7e*^_.n*-~ ~\u00b7\u00ad*m_^.|a**c$$e-| `^\u00b7c^_*o\u00b7_m$\u00ad._b*\u00ad\u00ad_i^~_n\u00ad|a*.\u00adt__\u00b7i\u00ad|_.o`~--n"), -1, Category.COMBAT);
        this.addSettings(this.timeout);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.resetSwapState();
        this.attackPressedLastTick = false;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.resetSwapState();
        this.attackPressedLastTick = false;
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null) {
            this.resetSwapState();
            this.attackPressedLastTick = false;
            return;
        }
        boolean attackPressed = this.mc.field_1690.field_1886.method_1434();
        switch (this.currentState.ordinal()) {
            case 0: {
                if (this.mc.field_1755 != null || !attackPressed || this.attackPressedLastTick) break;
                int spearSlot = this.findLungeSpearSlot();
                int maceSlot = InventoryUtils.getMaceSlot();
                if (spearSlot == -1 || maceSlot == -1) break;
                this.originalSlot = this.mc.field_1724.method_31548().method_67532();
                if (this.originalSlot != spearSlot) {
                    InventoryUtils.setInvSlot(spearSlot);
                }
                this.currentState = State.LUNGING;
                this.actionStartTime = System.currentTimeMillis();
                break;
            }
            case 1: {
                if (System.currentTimeMillis() - this.actionStartTime > (long)this.timeout.getValueInt()) {
                    this.revertToOriginal();
                    break;
                }
                if (this.mc.field_1724.method_24828() || !(this.mc.field_1724.field_6017 > (double)0.05f)) break;
                int maceSlot = InventoryUtils.getMaceSlot();
                if (maceSlot != -1) {
                    InventoryUtils.setInvSlot(maceSlot);
                    this.currentState = State.MACE_READY;
                    break;
                }
                this.revertToOriginal();
                break;
            }
            case 2: {
                if (System.currentTimeMillis() - this.actionStartTime > (long)this.timeout.getValueInt()) {
                    this.revertToOriginal();
                    break;
                }
                if (!this.mc.field_1724.method_24828()) break;
                this.revertToOriginal();
            }
        }
        this.attackPressedLastTick = attackPressed;
    }

    private void revertToOriginal() {
        if (this.originalSlot >= 0 && this.originalSlot < 9) {
            InventoryUtils.setInvSlot(this.originalSlot);
        }
        this.resetSwapState();
    }

    private int findLungeSpearSlot() {
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = this.mc.field_1724.method_31548().method_5438(i);
            if (!WorldUtils.isSpear(stack) || !WorldUtils.hasLungeEnchantment(stack)) continue;
            return i;
        }
        return -1;
    }

    private void resetSwapState() {
        this.currentState = State.IDLE;
        this.originalSlot = -1;
        this.actionStartTime = 0L;
    }

    private static enum State {
        IDLE,
        LUNGING,
        MACE_READY;

    }
}

