/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.mixin.HandledScreenMixin;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1723;
import net.minecraft.class_1735;
import net.minecraft.class_1802;
import net.minecraft.class_437;
import net.minecraft.class_490;

public final class H0V3R70173M
extends Module
implements TickListener {
    private final SliderSetting delay = new SliderSetting(EncryptedString.of("D-.*$e$$l_\u00b7a*^$$y"), 0.0, 20.0, 0.0, 1.0);
    private final BooleanSetting hotbar = (BooleanSetting)new BooleanSetting(EncryptedString.of("H^.o`*.`t.-b$.$a|\u00b7.|r"), true).setDescription(EncryptedString.of("P``u$\u00b7-t.\u00ads^- ~~*|a\u00ad^ ~\u00adt`|\u00ad\u00ado.`\u00ad.t\u00b7_-|e|-m..\u00ad *~`i|`._n*^$ -^$~y`\u00b7*`o^-u|`|r^^~ .\u00b7h^$\u00b7.o\u00b7-$t^`\u00b7b|-`a.\u00b7$-r$`_\u00ad -\u00b7a~|s|_ `*w*\u00b7\u00ade^._$l~*l\u00ad\u00ad.,\u00b7._ *.i.**.f\u00ad~`$ ||e`-n*\u00ad$`a\u00ad-^^b.|^l*~e.$d\u00ad`. ~-$_(_*~S**e^\u00adt~^t~*.i\u00ad`n^*`$g*\u00b7. ^$b**-_e\u00b7\u00b7-*l~-o--~w^\u00b7_$ .~w-\u00ad*i\u00b7^*l***l\u00ad*| .\u00ad~_w^\u00ad*_o`^`^r$\u00adk|^\u00b7 `\u00adi-\u00b7~`f|`$^ *-t\u00ad.`^h*-*i-~.|s\u00b7\u00b7~* ~|.i_\u00b7^|s\u00b7\u00ad|- ^`.e\u00b7\u00b7`.n$|||a|._*b.|\u00ad$l_.e~~^*d_^)"));
    private final SliderSetting slot = (SliderSetting)new SliderSetting(EncryptedString.of("T^~*|o__.*t*|e^\u00ad~m_\u00ad *__`S\u00b7__~l\u00ad\u00ad*o^*t"), 1.0, 9.0, 1.0, 1.0).setDescription(EncryptedString.of("Y**\u00ado-$_\u00adu`||-r.\u00b7~| $\u00b7p`\u00b7r~$e~|.f~~|-e\u00b7\u00ad-`r\u00b7_r..\u00ad*e.**~d^_ |\u00b7~$t\u00b7_\u00b7$o_$\u00b7.t_`-*e~$m\u00b7~\u00ad ``s\u00ad~l`$o^`\u00ad^t"));
    private final BooleanSetting autoSwitch = (BooleanSetting)new BooleanSetting(EncryptedString.of("A`-~_u`~-t~\u00ado||~` ~\u00b7~S-~w-^*i\u00ad._|t^_\u00b7c\u00b7.*-h"), false).setDescription(EncryptedString.of("S`-*`w`-_i~`t.\u00b7c\u00b7^|\u00b7h$\u00ad-*e.`~s\u00ad$ $.t\u00b7-$o._ \u00b7\u00ad\u00adt.|o.*t*_e.~\u00b7m$.^ $~|_s|\u00b7\u00b7l\u00b7*o^$t-|_~ \u00ad$`w^\u00adh~*e._n-_^ ^$\u00b7-g|$~o~*|*i-$n$|g$$|~ .|~\u00adi\u00ad$\u00b7^n^`s.$`i~*.d\u00ad--e|-\u00ad |\u00ad\u00b7t.`.\u00adh\u00ad$$`e^^. .$`~i-_\u00b7\u00b7n^~v\u00b7\u00ad*e.*n_`\u00adt$$o||-*r..^y"));
    private int clock;

    public H0V3R70173M() {
        super(EncryptedString.of("H`--o\u00b7`v-|**e..r.$~`T*.*o_\u00ad*`t|~|$e.`_^m"), EncryptedString.of("E.~\u00b7q_.u\u00ad*i\u00b7*p\u00b7~`-s$\u00ad_^ |^a$__$ ```-t\u00b7|*~o\u00b7.t.^.e`\u00b7^m\u00b7\u00ad *\u00b7.i||n\u00b7^~ |-^y.\u00b7o_|*~u\u00b7-~r-$`$ ~^t`_o|`|t\u00ad^-e\u00b7^m_*- $*a^|n*$^\u00add|._ ^-*o^\u00adf*\u00b7|*f\u00b7`-\u00adh-|\u00ada\u00ad.*\u00b7n\u00b7$d.\u00b7 $-~|s.*l^-.\u00b7o$||t$-~*s.- \u00b7~~_i\u00b7\u00adf*` \u00b7$^a~- -_t^|\u00b7*o|$t\u00b7-e\u00b7`_$m.|. -``\u00adi\u00ad`-|s\u00b7^- `~h`~-o$-v\u00ad~`e\u00b7^$_r~$|e~-\u00b7d"), -1, Category.COMBAT);
        this.addSettings(this.delay, this.hotbar, this.slot, this.autoSwitch);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.clock = 0;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        super.onDisable();
    }

    @Override
    public void onTick() {
        class_437 class_4372 = this.mc.field_1755;
        if (class_4372 instanceof class_490) {
            class_490 inv = (class_490)class_4372;
            class_1735 hoveredSlot = ((HandledScreenMixin)inv).getFocusedSlot();
            if (this.autoSwitch.getValue()) {
                this.mc.field_1724.method_31548().method_61496(this.slot.getValueInt() - 1);
            }
            if (hoveredSlot != null) {
                int s = hoveredSlot.method_34266();
                if (s > 35) {
                    return;
                }
                int totem = this.slot.getValueInt() - 1;
                if (hoveredSlot.method_7677().method_7909() == class_1802.field_8288) {
                    if (this.hotbar.getValue() && this.mc.field_1724.method_31548().method_5438(totem).method_7909() != class_1802.field_8288) {
                        if (this.clock > 0) {
                            --this.clock;
                            return;
                        }
                        this.mc.field_1761.method_2906(((class_1723)inv.method_17577()).field_7763, s, totem, class_1713.field_7791, (class_1657)this.mc.field_1724);
                        this.clock = this.delay.getValueInt();
                    } else if (!this.mc.field_1724.method_6079().method_31574(class_1802.field_8288)) {
                        if (this.clock > 0) {
                            --this.clock;
                            return;
                        }
                        this.mc.field_1761.method_2906(((class_1723)inv.method_17577()).field_7763, s, 40, class_1713.field_7791, (class_1657)this.mc.field_1724);
                        this.clock = this.delay.getValueInt();
                    }
                }
            }
        } else {
            this.clock = this.delay.getValueInt();
        }
    }
}

