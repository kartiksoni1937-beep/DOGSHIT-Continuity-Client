/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.mixin.MinecraftClientAccessor;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.KeyUtils;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_3489;
import net.minecraft.class_9362;

public final class _5p34r5w4p
extends Module
implements TickListener {
    private final KeybindSetting dashKey = new KeybindSetting(EncryptedString.of("D-$a~_`\u00b7s|.h^\u00ad_| $$K~^e^`~.y"), -1, false);
    private final BooleanSetting silentSwap = (BooleanSetting)new BooleanSetting(EncryptedString.of("S_\u00b7*i.\u00adl.~`_e.|n_|~t.|- \u00ad.|S$-w.$a$_\u00ad^p"), true).setDescription(EncryptedString.of("S_|\u00b7w`-*_a\u00b7-p$*_ _\u00ad|-s|$.*l_.o.\u00ad`t^..\u00b7 ~\u00b7`.s^^*i$\u00ad*l|-_e~$n-\u00b7.t$~l\u00ad\u00ad`y*.~ -`_`w`~^\u00b7i\u00b7$~t._h|_~\u00ado$_\u00b7u\u00ad$`t\u00ad$^^ \u00b7.$*v$.i-|s~\u00ad\u00adu~|.^a$`~~l|_ \u00ad~^c*~~h`-`|a\u00b7\u00b7*n$|g-`^e"));
    private final BooleanSetting autoJump = (BooleanSetting)new BooleanSetting(EncryptedString.of("A-_.*u\u00b7.t|\u00b7o\u00b7.$- ^-||J\u00b7\u00adu|__m~\u00ad^\u00adp"), true).setDescription(EncryptedString.of("J^`u-|^.m`*\u00b7*p`~ ~~|b`|~\u00ade\u00ad\u00adf\u00b7^\u00b7o^_r^^$^e_-~_ ~_-*e^\u00ad$x\u00b7~^\u00b7e``$c$``_u\u00b7_^$t-.\u00b7i~\u00ad^n^~g.\u00ad\u00ad$ $.`-t|-\u00b7h~|e^^-_ _-d\u00b7.$a_|~\u00b7s_^~h"));
    private final MinMaxSetting swapDelay = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("S.^_w|\u00b7a-.^_p.^$ _$D~_e|$l~|a|`y"), 0.0, 20.0, 1.0, 0.0, 2.0).setDescription(EncryptedString.of("T\u00b7\u00ad|$i_\u00ad|c\u00b7|k`\u00ad\u00b7s.\u00b7- ^.t-`o|_^- `^|~w\u00b7^a`.i~~*t|.\u00ad~ *_^.b-\u00b7$^e\u00ad.$f_\u00ad\u00ad~o**r^*``e_*. *-e`*x\u00ad.e-\u00b7.c_\u00b7\u00adu~.t$$^i*\u00b7..n_~$g|\u00ad -..t_*\u00adh~|e*~^ ^*^d$^`a.$|$s*$_|h"));
    private final MinMaxSetting swapBackDelay = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("S^$w|~a\u00b7`.p^- *`B\u00ad~\u00b7a.|.c`$k|| \u00b7*$~D~\u00b7~`e*|*~l\u00b7|^a-`^y"), 0.0, 20.0, 1.0, 1.0, 3.0).setDescription(EncryptedString.of("T^~|\u00adi|~--c-*^k|\u00b7|s\u00b7~-. $~$t|`o$` -~~.w`-^a..\u00ad_i`^t_* *\u00b7-b*-~e-\u00b7~f^\u00ado\u00b7|\u00ad\u00b7r_-e^\u00ad -_s\u00ad_-w$_a\u00ad*p|.|`p|*i^`^`n|.g$\u00ad\u00ad $^b^_\u00ad$a*$^`c\u00b7\u00b7|k"));
    private Phase phase = Phase.IDLE;
    private int ticksWaited = 0;
    private int currentSwapDelay = 0;
    private int currentSwapBackDelay = 0;
    private int spearSlot = -1;
    private int originalSlot = -1;
    private boolean wasKeyDown = false;

    public _5p34r5w4p() {
        super(EncryptedString.of("S*^\u00b7\u00adp\u00b7-~-e.~a|-*r^^** -_D|\u00ad\u00b7`a*\u00ad-~s\u00ad\u00adh"), EncryptedString.of("D`*^`a-|-s$.`.h|.^\u00ad \u00b7|-_u_`s|\u00b7-i_*.n_*g\u00b7^* |^`a|. -..-L-.$u$\u00adn.\u00b7g._.$e.|\u00ad|e_|`n.\u00ad`\u00adc\u00ad$h^_\u00b7a`^\u00b7n^_|`t~`^e-`|d\u00ad.*. .\u00b7.-s$*p~`\u00b7e*`~.a|*r"), -1, Category.COMBAT);
        this.addSettings(this.dashKey, this.silentSwap, this.autoJump, this.swapDelay, this.swapBackDelay);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.reset();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.reset();
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            this.reset();
            return;
        }
        if (this.mc.field_1755 != null) {
            this.reset();
            return;
        }
        boolean keyDown = KeyUtils.isKeyPressed(this.dashKey.getKey());
        if (keyDown && !this.wasKeyDown && this.phase == Phase.IDLE) {
            this.startDash();
        }
        this.wasKeyDown = keyDown;
        if (this.phase == Phase.IDLE) {
            return;
        }
        ++this.ticksWaited;
        switch (this.phase.ordinal()) {
            case 1: {
                if (this.ticksWaited < this.currentSwapDelay) break;
                this.executeDash();
                this.ticksWaited = 0;
                this.phase = Phase.AWAIT_SWAP_BACK;
                break;
            }
            case 2: {
                if (this.ticksWaited < this.currentSwapBackDelay) break;
                int safeSlot = this.findSafeSlot();
                int target = safeSlot != -1 ? safeSlot : this.originalSlot;
                InventoryUtils.setInvSlot(target);
                this.reset();
            }
        }
    }

    private void startDash() {
        this.spearSlot = this.findLungeSpearSlot();
        if (this.spearSlot == -1 || this.mc.field_1724 == null) {
            return;
        }
        this.originalSlot = this.mc.field_1724.method_31548().method_67532();
        this.currentSwapDelay = this.randomTicks((float)this.swapDelay.getMinValue(), (float)this.swapDelay.getMaxValue());
        this.currentSwapBackDelay = this.randomTicks((float)this.swapBackDelay.getMinValue(), (float)this.swapBackDelay.getMaxValue());
        this.ticksWaited = 0;
        if (this.autoJump.getValue() && this.mc.field_1724.method_24828()) {
            this.mc.field_1724.method_6043();
            if (this.currentSwapDelay == 0) {
                this.currentSwapDelay = 1;
            }
        }
        if (this.currentSwapDelay == 0) {
            this.executeDash();
            this.phase = Phase.AWAIT_SWAP_BACK;
        } else {
            this.phase = Phase.AWAIT_SWAP;
        }
    }

    private void executeDash() {
        InventoryUtils.setInvSlot(this.spearSlot);
        ((MinecraftClientAccessor)this.mc).invokeDoAttack();
    }

    private void reset() {
        this.phase = Phase.IDLE;
        this.ticksWaited = 0;
        this.spearSlot = -1;
        this.originalSlot = -1;
    }

    private int randomTicks(float min, float max) {
        if (min >= max) {
            return (int)min;
        }
        return (int)((double)min + Math.random() * (double)(max - min + 1.0f));
    }

    private int findLungeSpearSlot() {
        if (this.mc.field_1724 == null) {
            return -1;
        }
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = this.mc.field_1724.method_31548().method_5438(i);
            if (!WorldUtils.isSpear(stack) || !WorldUtils.hasLungeEnchantment(stack)) continue;
            return i;
        }
        return -1;
    }

    private int findSafeSlot() {
        if (this.mc.field_1724 == null) {
            return -1;
        }
        for (int i = 0; i < 9; ++i) {
            class_1799 stack;
            if (i == this.spearSlot || this.isCombatItem(stack = this.mc.field_1724.method_31548().method_5438(i))) continue;
            return i;
        }
        return -1;
    }

    private boolean isCombatItem(class_1799 stack) {
        if (stack.method_7960()) {
            return false;
        }
        class_1792 item = stack.method_7909();
        return stack.method_31573(class_3489.field_42611) || WorldUtils.isSpear(stack) || item instanceof class_1743 || item instanceof class_9362;
    }

    private static enum Phase {
        IDLE,
        AWAIT_SWAP,
        AWAIT_SWAP_BACK;

    }
}

