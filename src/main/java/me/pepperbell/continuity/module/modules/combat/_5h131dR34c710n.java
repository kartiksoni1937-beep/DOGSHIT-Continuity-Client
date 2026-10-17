/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.PlayerTickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1802;

public final class _5h131dR34c710n
extends Module
implements PlayerTickListener {
    private final SliderSetting range = new SliderSetting(EncryptedString.of("R\u00b7$.^a.*n$_`g^^e"), 1.0, 6.0, 4.0, 0.1);

    public _5h131dR34c710n() {
        super(EncryptedString.of("S-$.h\u00b7`i\u00b7_e-~_l\u00ad-d~$ ^$*^R`\u00ade|*~a|-*.c~`t-~*~i`^o$$*\u00adn"), EncryptedString.of("A-\u00b7-u-*$t*`_|o_\u00b7_\u00b7m\u00b7^*a..*|t~\u00ad\u00b7i~\u00adc\u00ad_a*\u00ad~l$^`*l_\u00ad\u00b7y$-* .^.\u00adb|_`_l\u00b7.|\u00b7o^.|^c.$*.k_$.$s-~* `_\u00adw*$|i_-\u00b7\u00b7t|`h`.\u00b7` ~|y~-_$o.*$$u.`r_| .`|-s~\u00b7\u00b7$h`^i|*e^_l$`\u00b7-d`\u00ad \u00b7.\u00b7`w^|`$h|-~\u00ade||n.. --\u00ada\u00ad|$$n`_^. \u00b7$o`|p\u00b7-$p\u00ad\u00b7`*o|\u00b7|n\u00b7_*e^$*_n-^t-** ^_||a\u00b7\u00b7_t|_*t*~~a$\u00b7|c-\u00b7k.\u00b7s"), -1, Category.COMBAT);
        this.addSettings(this.range);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(PlayerTickListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(PlayerTickListener.class, this);
        super.onDisable();
    }

    @Override
    public void onPlayerTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        for (class_1657 player : this.mc.field_1687.method_18456()) {
            if (player == this.mc.field_1724 || (double)player.method_5739((class_1297)this.mc.field_1724) > this.range.getValue() || !(player.field_6251 > 0.0f) || !((double)player.field_6251 < 0.5) || !this.isLookingAtMe(player)) continue;
            if (this.mc.field_1724.method_6079().method_31574(class_1802.field_8255)) {
                this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, class_1268.field_5810);
                continue;
            }
            if (!this.mc.field_1724.method_6047().method_31574(class_1802.field_8255)) continue;
            this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, class_1268.field_5808);
        }
    }

    private boolean isLookingAtMe(class_1657 enemy) {
        return !WorldUtils.isShieldFacingAway(enemy);
    }
}

