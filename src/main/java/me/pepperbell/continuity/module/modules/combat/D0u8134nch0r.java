/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.concurrent.ThreadLocalRandom;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.BlockUtils;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_239;
import net.minecraft.class_3965;
import org.lwjgl.glfw.GLFW;

public final class D0u8134nch0r
extends Module
implements TickListener {
    private final SliderSetting activateKey = (SliderSetting)new SliderSetting(EncryptedString.of("A\u00b7\u00b7_\u00b7c$*$t\u00ad-i--\u00ad.v`**|a-`__t|-^e|.` ^|K\u00b7^*$e_-y"), 0.0, 350.0, 71.0, 1.0).setDescription(EncryptedString.of("K`^^e`*_\u00ady*^\u00b7| ~--t*`h|$\u00b7$a**t\u00b7- -~s~^|t\u00b7\u00ada`$*r..t-$\u00ad~s\u00ad\u00b7 -*^d*~o-\u00ad\u00ad^u~.|~b*|l\u00b7\u00b7e._* *_\u00b7\u00b7a\u00ad._~n~-~_c--_h-$.-o_.r$`*i~.$*n`\u00b7^g_\u00b7. \u00b7\u00b7($*-d^$e~_~f*|\u00b7a\u00ad*u^\u00ad_~l~$_~t~\u00b7 $|_~G_||\u00b7=`^7$^|\u00ad1*\u00ad$^)"));
    private final SliderSetting switchDelay = (SliderSetting)new SliderSetting(EncryptedString.of("S$_w*^i$`*t\u00b7*c$.$.h|. |\u00adD$\u00ade.|l`-*a\u00b7-$y"), 0.0, 20.0, 0.0, 1.0).setDescription(EncryptedString.of("D~*\u00ad\u00b7e.*`l-\u00ada\u00b7-y||`. *\u00b7|b_`e.**t^*w_$.e~`|*e_\u00b7n-\u00b7\u00b7~ _-s-|-t`._-e|`.p_|-~s`~ \u00ad\u00ad_i\u00b7.^n\u00b7~~ _\u00b7t^$`\u00b7i$-c$^k-^s"));
    private final SliderSetting totemSlot = (SliderSetting)new SliderSetting(EncryptedString.of("T_|o-~t\u00ad*|e_~$-m`*\u00b7 \u00ad-_.S`\u00adl-._o-.$_t"), 1.0, 9.0, 1.0, 1.0).setDescription(EncryptedString.of("S$`\u00adl-|o~~~t\u00b7\u00b7_$ .`_t`|\u00ado*- |~s-.$w^__.a_\u00ad~p`^^^ \u00ad`*-t^.\u00ado~_\u00b7$ `_*~a_$f-^t~\u00ad\u00ade~|`r\u00ad.| \u00ad*_\u00b7p^\u00ad-l.`a\u00b7~*c\u00b7\u00b7*~e-*~.m*~\u00b7e^\u00b7n`_t"));
    private final MinMaxSetting rightCps = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("R*$i`.g`\u00ad_|h*-\u00b7t_*`` \u00ad~C$*P\u00ad~S"), 1.0, 30.0, 8.0, 14.0, 1.0).setDescription(EncryptedString.of("C\u00b7|P-``S\u00ad\u00b7.| .~-r*~a|_$_n|`*^g*^\u00b7e\u00b7\u00ad `.f~_-o__$\u00b7r~^._ -.r``\u00b7^i\u00b7`_g~_h\u00ad$\u00ad^t*^*\u00ad *`c|^\u00adl~_i-*||c`^|-k-._* `|~.s**~\u00adi\u00ad\u00adm*_u$_\u00ad.l\u00b7*.|a-|`*t\u00ad.i\u00ad|-*o^_n"));
    private int delayCounter = 0;
    private int step = 0;
    private boolean isAnchoring = false;
    private long lastClickTime = 0L;

    public D0u8134nch0r() {
        super(EncryptedString.of("D\u00b7.`o^.^u.*$b~^$l|\u00ad`$e$^- .^*A_-n*~c^.`h\u00ad$`o.^$r"), EncryptedString.of("A.*\u00b7_u|^-t__-o~~*\u00b7m`__a-_\u00b7_t^|i*~c.*$_a_-$|l^.l\u00b7$*y~^-_ $*--p_\u00b7l-*$a\u00b7_\u00adc^\u00ade~.s$- _$|2$` .~.a-\u00b7n$^\u00ad|c_`$\u00adh^|$o\u00ad`.~r.\u00ads-- ~.`$o*$$n$- -.|$c`$~h~$a\u00b7~r-.~^g`\u00b7-$e*\u00b7\u00add`.~ \u00b7|$_r~`^.e\u00b7\u00ad*s*$p$\u00b7a.^\u00adw~.~n`~ ^*\u00b7a^|$n\u00b7\u00ad\u00adc\u00b7^.`h*`~`o\u00b7|$r_~s"), -1, Category.COMBAT);
        this.addSettings(this.activateKey, this.switchDelay, this.totemSlot, this.rightCps);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.isAnchoring = false;
        this.step = 0;
        this.delayCounter = 0;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.isAnchoring = false;
        this.step = 0;
        this.delayCounter = 0;
        super.onDisable();
    }

    @Override
    public void onTick() {
        class_3965 hit;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null || this.mc.field_1755 != null) {
            return;
        }
        if (!this.hasRequiredItems()) {
            return;
        }
        if (!this.isAnchoring && !this.checkActivationKey()) {
            return;
        }
        class_239 crosshairTarget = this.mc.field_1765;
        if (!(crosshairTarget instanceof class_3965) || BlockUtils.isBlock((hit = (class_3965)crosshairTarget).method_17777(), class_2246.field_10124)) {
            this.resetState();
            return;
        }
        this.simulateRightCps();
        if (this.delayCounter < this.switchDelay.getValueInt()) {
            ++this.delayCounter;
            return;
        }
        this.delayCounter = 0;
        switch (this.step) {
            case 0: {
                this.selectItem(class_1802.field_23141);
                break;
            }
            case 1: {
                WorldUtils.placeBlock(hit, true);
                break;
            }
            case 2: {
                this.selectItem(class_1802.field_8801);
                break;
            }
            case 3: {
                WorldUtils.placeBlock(hit, true);
                break;
            }
            case 4: {
                this.selectItem(class_1802.field_23141);
                break;
            }
            case 5: {
                WorldUtils.placeBlock(hit, true);
                WorldUtils.placeBlock(hit, true);
                break;
            }
            case 6: {
                this.selectItem(class_1802.field_8801);
                break;
            }
            case 7: {
                WorldUtils.placeBlock(hit, true);
                break;
            }
            case 8: {
                InventoryUtils.setInvSlot(this.totemSlot.getValueInt() - 1);
                break;
            }
            case 9: {
                WorldUtils.placeBlock(hit, true);
                break;
            }
            case 10: {
                this.isAnchoring = false;
                this.step = 0;
                this.delayCounter = 0;
                return;
            }
        }
        ++this.step;
    }

    private void selectItem(class_1792 item) {
        int slot = InventoryUtils.getSlot(item);
        if (slot != -1) {
            InventoryUtils.setInvSlot(slot);
        }
    }

    private boolean hasRequiredItems() {
        boolean hasAnchor = false;
        boolean hasGlowstone = false;
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = this.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_31574(class_1802.field_23141)) {
                hasAnchor = true;
            }
            if (!stack.method_31574(class_1802.field_8801)) continue;
            hasGlowstone = true;
        }
        return hasAnchor && hasGlowstone;
    }

    private boolean checkActivationKey() {
        int key = (int)this.activateKey.getValue();
        if (key == -1 || GLFW.glfwGetKey((long)this.mc.method_22683().method_4490(), (int)key) != 1) {
            this.resetState();
            return false;
        }
        this.step = 0;
        this.isAnchoring = true;
        return true;
    }

    private void simulateRightCps() {
        double cps;
        long interval;
        if (!this.isAnchoring) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastClickTime >= (interval = (long)(1000.0 / (cps = ThreadLocalRandom.current().nextDouble(this.rightCps.getMinValue(), this.rightCps.getMaxValue()))))) {
            this.lastClickTime = now;
        }
    }

    private void resetState() {
        this.delayCounter = 0;
        this.step = 0;
        this.isAnchoring = false;
    }
}

