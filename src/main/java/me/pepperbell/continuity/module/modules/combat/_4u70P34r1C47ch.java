/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.KeyUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1802;

public final class _4u70P34r1C47ch
extends Module
implements TickListener {
    private final KeybindSetting activateKey = new KeybindSetting(EncryptedString.of("A~$-|c*`t~_i-\u00b7\u00b7~v.^\u00b7_a\u00ad\u00ad\u00adt\u00ad~e`\u00b7~ ^`_\u00b7K-`\u00b7.e\u00b7*y"), -1, false);
    private final SliderSetting pearlSwapDelay = (SliderSetting)new SliderSetting(EncryptedString.of("P-~|e\u00b7~~|a$~*r|$l~\u00b7\u00ad- `|S|\u00b7w$_a|-p\u00b7$ ^_D$-e.\u00b7l-|-a\u00ad$|y"), 0.0, 20.0, 0.0, 1.0).setDescription(EncryptedString.of("T-.i*\u00ad^c.`k`~_`s-_*~ `.\u00b7_b|\u00b7_*e|~._f.\u00b7.o.-r|-*|e$~ `-_.s\u00b7_w-_a$^.\u00adp^-$^p.|\u00b7~i\u00b7\u00b7n\u00b7\u00ad-g`$ *_t_~~o$.`~ .`|`p$$`~e^^*a\u00ad^~\u00b7r\u00b7\u00b7l"));
    private final SliderSetting pearlThrowDelay = (SliderSetting)new SliderSetting(EncryptedString.of("P`-_e\u00b7_a\u00b7~`-r^^l.~$^ $$T$^h.~-`r^~o\u00ad.^`w-^ `~D|.e\u00ad~~`l-$a^~\u00b7.y"), 0.0, 20.0, 1.0, 1.0).setDescription(EncryptedString.of("T$|*_i^.c_$|`k\u00ad~s\u00ad\u00b7^\u00ad _$`a---f$\u00ad\u00b7\u00b7t~*..e_\u00ad^|r~|\u00ad* _-|s\u00b7^*w*|$a~`$p_`_` \u00ad*~~b-\u00ad-e\u00b7*_|f_|`_o-|r$$*.e\u00b7\u00b7| ^`*t.`|*h__\u00b7*r```$o_--w$\u00ad`\u00b7i\u00b7|n|.|g-.-* \u00ad\u00b7p~\u00ad^*e\u00ad_a``~r`~l"));
    private final SliderSetting windChargeSwapDelay = (SliderSetting)new SliderSetting(EncryptedString.of("W`-~i`~n*\u00add~*$* |\u00b7$.C_\u00b7h\u00b7\u00b7-a_|r|~g*\u00b7*e\u00b7*^ |\u00ad~S~*w~~*a_.*\u00b7p|* |~\u00b7`D.~.\u00ade_$\u00b7l|.\u00ad-a*$.y"), 0.0, 20.0, 1.0, 1.0).setDescription(EncryptedString.of("T$\u00ad*i~$~c-\u00ad*\u00adk~`*s~-*\u00ad ~.~$b_|*-e|$\u00b7\u00adt-\u00b7*w`.e-|**e^~n|- $\u00adp~_.e___`a$\u00ad`r\u00ad-$l*- ***-t_*\u00adh_^r.-*o\u00b7\u00b7\u00adw._ |\u00ad^a~~*_n^_d\u00ad** -\u00ad__w_\u00b7\u00b7^i$^_\u00b7n^.`d-*\u00ad* $|*-c*^h*_a_|\u00b7r$*g*`e$\u00ad .~^*s_\u00adw-|\u00ada._~p"));
    private final SliderSetting windChargeThrowDelay = (SliderSetting)new SliderSetting(EncryptedString.of("W$```i$^\u00b7^n_$^$d_~* ~$C`-*-h~_.a\u00ad-\u00ad*r~~|-g\u00ad||e-\u00ad^\u00ad |$T\u00b7*h-_`-r`_\u00b7|o-*\u00b7w^| ~`-D\u00ad\u00ade\u00b7|l~~a-.y"), 0.0, 20.0, 1.0, 1.0).setDescription(EncryptedString.of("T*\u00adi|~c|`k*\u00b7\u00ads.$$ _~a^~f\u00ad\u00b7|t.|e_$r^. \u00ad.|s*`\u00ad\u00b7w\u00b7\u00ad*^a_`p\u00ad| \u00b7~b-**e.*f\u00b7-\u00ad-o^.^r*`e$*\u00b7\u00b7 ^..t~$h$_-r*\u00b7\u00ad.o$\u00adw_^i~^`n\u00ad`.g-*\u00b7. $.`-w^-i*~n_*d^~_ `\u00b7~c**h.~`a-\u00b7r$-_g-.-e"));
    private final BooleanSetting swapBack = (BooleanSetting)new BooleanSetting(EncryptedString.of("S^\u00b7$.w~||a~\u00b7$^p\u00b7~ .~B$_^a.~c-.\u00ad\u00b7k"), true).setDescription(EncryptedString.of("S^|^w`\u00ad-$a$$`p_|_ \u00b7-$b\u00ad`^a*|c^^\u00ad_k\u00ad** \u00ad^t|*\u00b7-o\u00ad| $.*o|\u00b7\u00b7|r..\u00adi\u00b7.*_g`-.~i*~n.`*a$$^*l.$^ \u00b7.$-s\u00ad||$l_`o^-t\u00b7` **a~_f$^t._*e^\u00ad$r.| __$\u00b7t|\u00ad\u00b7h.^.\u00adr^_..o-`|w_$i$\u00b7n|^`$g"));
    private final SliderSetting swapBackDelay = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("S.^\u00ad\u00b7w.\u00b7-_a`|\u00ad\u00adp$. .|`B^*|a|~_c`.k\u00ad-\u00ad* ~.D_-$e_\u00b7-_l~..|a\u00ad|y"), 0.0, 20.0, 2.0, 1.0).setDescription(EncryptedString.of("T\u00ad^__i\u00b7-c_`_k-*_-s_\u00b7-. ^_\u00b7^b|.`e\u00ad~**f--|\u00ado\u00b7_.r\u00ad_e\u00ad_ \u00b7*s^~~-w-.|a.\u00b7p\u00b7\u00ad\u00b7p*\u00adi$^^n-*~g`*\u00b7` \u00ad~_|b.**$a~~^\u00b7c_\u00b7k"))).visible(this.swapBack::getValue);
    private State state = State.IDLE;
    private int tickCounter = 0;
    private int previousSlot = -1;
    private boolean wasKeyDown = false;

    public _4u70P34r1C47ch() {
        super(EncryptedString.of("A*|u$|t|*-o*$|| |\u00b7|P|\u00b7|e-*|a-\u00adr.|\u00b7l^* ~||.C\u00ad.\u00ad`a^-t~\u00adc\u00ad^\u00b7h"), EncryptedString.of("T\u00b7`h^|~r^\u00b7_~o\u00ad$_*w\u00b7|_ .~p.$^e$$$.a*~-r_\u00adl*`\u00ad- \u00ad\u00ad.\u00adt-_.h\u00b7|`.e_$n^~ ^-_-w_\u00adi\u00ad~\u00b7n.\u00ad~~d.^.| \u00b7$_c\u00b7^-.h|\u00b7*a\u00ad~r.\u00adg\u00ad*\u00b7.e~\u00b7 *~_o|_^^n\u00b7-` _^.`k-\u00b7|e-|\u00b7y_*\u00ad^ ~\u00ad|p_|r\u00b7\u00b7e*|.s^$\u00b7s"), -1, Category.COMBAT);
        this.addSettings(this.activateKey, this.pearlSwapDelay, this.pearlThrowDelay, this.windChargeSwapDelay, this.windChargeThrowDelay, this.swapBack, this.swapBackDelay);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.resetState();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.resetState();
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            this.resetState();
            return;
        }
        if (this.mc.field_1755 != null) {
            this.resetState();
            return;
        }
        boolean keyDown = KeyUtils.isKeyPressed(this.activateKey.getKey());
        if (keyDown && !this.wasKeyDown && this.state == State.IDLE) {
            this.startSequence();
        }
        this.wasKeyDown = keyDown;
        if (this.state != State.IDLE) {
            this.processState();
        }
    }

    private void startSequence() {
        int pearlSlot = this.findPearlSlot();
        if (pearlSlot == -1) {
            return;
        }
        if (this.mc.field_1724.method_7357() != null && this.mc.field_1724.method_7357().method_7904(this.mc.field_1724.method_31548().method_5438(pearlSlot))) {
            return;
        }
        this.previousSlot = this.mc.field_1724.method_31548().method_67532();
        this.state = State.WAIT_PEARL_SWAP;
        this.tickCounter = 0;
    }

    private void processState() {
        ++this.tickCounter;
        switch (this.state.ordinal()) {
            case 1: {
                if (this.tickCounter < this.pearlSwapDelay.getValueInt()) break;
                int pearlSlot = this.findPearlSlot();
                if (pearlSlot == -1) {
                    this.resetState();
                    return;
                }
                InventoryUtils.setInvSlot(pearlSlot);
                this.state = State.WAIT_PEARL_THROW;
                this.tickCounter = 0;
                break;
            }
            case 2: {
                if (this.tickCounter < this.pearlThrowDelay.getValueInt()) break;
                if (this.mc.field_1761 != null) {
                    this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, class_1268.field_5808);
                }
                this.state = State.WAIT_WIND_SWAP;
                this.tickCounter = 0;
                break;
            }
            case 3: {
                if (this.tickCounter < this.windChargeSwapDelay.getValueInt()) break;
                int windSlot = this.findWindChargeSlot();
                if (windSlot == -1) {
                    this.resetState();
                    return;
                }
                InventoryUtils.setInvSlot(windSlot);
                this.state = State.WAIT_WIND_THROW;
                this.tickCounter = 0;
                break;
            }
            case 4: {
                if (this.tickCounter < this.windChargeThrowDelay.getValueInt()) break;
                if (this.mc.field_1761 != null) {
                    this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, class_1268.field_5808);
                }
                if (this.swapBack.getValue()) {
                    this.state = State.WAIT_SWAP_BACK;
                    this.tickCounter = 0;
                    break;
                }
                this.resetState();
                break;
            }
            case 5: {
                if (this.tickCounter < this.swapBackDelay.getValueInt()) break;
                if (this.previousSlot >= 0 && this.previousSlot < 9) {
                    InventoryUtils.setInvSlot(this.previousSlot);
                }
                this.resetState();
            }
        }
    }

    private int findPearlSlot() {
        if (this.mc.field_1724 == null) {
            return -1;
        }
        for (int i = 0; i < 9; ++i) {
            if (this.mc.field_1724.method_31548().method_5438(i).method_7909() != class_1802.field_8634) continue;
            return i;
        }
        return -1;
    }

    private int findWindChargeSlot() {
        if (this.mc.field_1724 == null) {
            return -1;
        }
        for (int i = 0; i < 9; ++i) {
            if (this.mc.field_1724.method_31548().method_5438(i).method_7909() != class_1802.field_49098) continue;
            return i;
        }
        return -1;
    }

    private void resetState() {
        this.state = State.IDLE;
        this.tickCounter = 0;
        this.previousSlot = -1;
        this.wasKeyDown = false;
    }

    private static enum State {
        IDLE,
        WAIT_PEARL_SWAP,
        WAIT_PEARL_THROW,
        WAIT_WIND_SWAP,
        WAIT_WIND_THROW,
        WAIT_SWAP_BACK;

    }
}

