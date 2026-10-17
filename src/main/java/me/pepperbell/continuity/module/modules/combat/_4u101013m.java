/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.FakeInvScreen;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.TimerUtils;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1713;
import net.minecraft.class_1723;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_437;
import net.minecraft.class_490;

public final class _4u101013m
extends Module
implements TickListener {
    public static boolean bypassNoHotbarMessup = false;
    private final ModeSetting<Mode> mode = (ModeSetting)new ModeSetting<Mode>(EncryptedString.of("M~.\u00ad|o|^^d^_~|e"), Mode.Blatant, Mode.class).setDescription(EncryptedString.of("W|-h\u00ad\u00ade.`_t*^_.h|.*^e^$-|r`^\u00b7 \u00b7_|_t\u00b7\u00b7$~o\u00ad\u00b7$ .-^r*_\u00ad$a\u00b7.n_$.\u00add^\u00b7_.o.^\u00ad*m_\u00b7~i-_z||e^|^ _\u00b7``t^*-h.\u00ad~e_\u00ad` ^\u00ad^t\u00ad$-\u00ado-`_t^`-e|-$m.`_i\u00b7||n_\u00b7\u00ad\u00b7g-. $.\u00b7p\u00b7^._a-*-.t-$t.-~e.\u00ad$$r-`~n\u00ad_ |\u00b7_o~*_r-^ -$n$\u00b7o"));
    private final SliderSetting delay = new SliderSetting(EncryptedString.of("D-*\u00b7e`_``l^|a*_$y"), 0.0, 20.0, 0.0, 1.0);
    private final BooleanSetting hotbar = (BooleanSetting)new BooleanSetting(EncryptedString.of("H_|o_|^$t\u00b7*b.|.a|_r"), true).setDescription(EncryptedString.of("P_^u**~t$-s_- |-a^$ `.|*t.__o._t\u00b7*|e-$_m|\u00b7_ *^i`|n.~` *~_y^`$-o.\u00b7^u.~\u00b7\u00adr*~ .-h--o`^.|t~|`b```a_``r_-- \u00b7^a|\u00b7*s^. ^-w`\u00ad*e^^^^l$\u00ad\u00b7^l~|`,~*\u00b7 .\u00b7|.i$__f_$ .\u00ad$~e~._n`\u00b7a-~b^`-\u00adl\u00b7.e~^`.d$^|_ ~`(|.S$\u00ade`^t`\u00b7t\u00ad\u00b7*_i|*n\u00b7$.g.`` |^b_*.|e*`..l-.`o\u00b7~w~-`| __\u00b7w^$\u00adi***~l~*.\u00b7l\u00ad_ `|_w\u00ad`o-|_r~_k_-\u00b7$ |--i\u00ad\u00adf$\u00b7~~ \u00b7_~_t*\u00b7h^\u00b7i_^s.$\u00b7* .\u00b7$i_|^`s._*\u00ad ^_*e.\u00b7~n~$a|\u00ad.b~$l_|\u00ade.-*d_.\u00b7*)"));
    private final SliderSetting totemSlot = (SliderSetting)new SliderSetting(EncryptedString.of("T*$|`o*.t_\u00b7e*|m\u00b7` -^*.S-|l\u00ad.o^-^t"), 1.0, 9.0, 1.0, 1.0).setDescription(EncryptedString.of("Y^`$o\u00b7\u00adu$\u00ad.$r$|-- -`p*\u00ad.^r$\u00b7\u00b7^e.$f**e\u00b7-r|..|r|_|e$--d** ~*~^t**o*`~t_|$e$-\u00ad$m$^_* ~..s-~l_.$`o`~t"));
    private final BooleanSetting autoSwitch = (BooleanSetting)new BooleanSetting(EncryptedString.of("A-~_*u~`.`t^|o`|. *^S-.`.w^`_i_\u00b7t_-*_c|.h"), false).setDescription(EncryptedString.of("S_.w-`.\u00b7i*`t-^c~$_.h|~e\u00ad~*s$`_ \u00ad~*\u00b7t^|\u00b7`o\u00ad` *.|.t~^o^*t^^\u00ad$e``_m.\u00b7$ ~..s$_l-\u00ad-o_^\u00b7t^.. -$^w.~|h`\u00b7e\u00ad_n~^. $^.g\u00ad~o^.^i$~n~*`$g^. ~\u00b7^i$.n`_*\u00ads-__i$$d^~_*e.\u00ad`| `_^t_\u00b7.h-|-e^* `$~`i_$|^n`|v\u00b7*e\u00ad|*n\u00b7-t~-o$~^r|_y"));
    private final BooleanSetting forceTotem = (BooleanSetting)new BooleanSetting(EncryptedString.of("F`|\u00b7~o`_|r\u00b7..`c$|~\u00ade~` *$.\u00adT|`o-^_t\u00b7~_.e^.^`m"), false).setDescription(EncryptedString.of("P_*^`u*~~t\u00ad`\u00b7^s*`~ $^.\u00b7t\u00b7\u00b7\u00b7h|`e``|\u00ad -^*t~~`\u00b7o^\u00b7t||*~e~.*m-$^. ^~.i^.n_|*~ \u00b7\u00b7t._`h-_\u00b7e|\u00b7 `-s*-l^~o$-t--$`,^|\u00b7\u00ad ^^r|*.*e*\u00b7_g|*a-\u00ad\u00ad.r^||d*\u00ad-$l|\u00b7-e-`s\u00ad\u00b7`s*_ ~\u00b7i\u00b7-`\u00adf-\u00b7* `^i*.t*\u00b7^.s*_*. *-\u00b7s^.p_\u00b7a~.`c\u00b7`e~_\u00ad^ |_\u00b7^i`-`s^-|^ $\u00adt^_.$a$.k-~\u00ad`e--\u00ad-n*_* |$$u.-^p_| \u00ad\u00b7b\u00b7.*`y\u00ad.\u00ad ^.$s|.-^o.~$m$`*e-_*t~$\u00b7$h*_-^i$\u00ad^_n\u00ad$\u00ad^g-_ *$`^e_|-l`-\u00ad\u00ads$-e"));
    private final BooleanSetting autoOpen = (BooleanSetting)new BooleanSetting(EncryptedString.of("A\u00ad-*~u.\u00adt^\u00b7~o$_$ ~__O~-_p.`_e~~.|n"), false).setDescription(EncryptedString.of("A$~|u_$t\u00b7_*\u00ado\u00b7``m_*\u00ada-^.t..~i*\u00adc`~|_a$\u00ad`|l`$*l~*_y\u00ad| *.\u00ado~\u00ad$-p\u00ad~-\u00b7e\u00b7\u00adn~_s** \u00ad^|a^~n`^d\u00b7-^ `$\u00ad|c$|l\u00b7_o^`-\u00ads__*e.-s\u00ad\u00ad- \u00ad.*.t.\u00b7h~-e^~ $.i\u00ad$-n|.*`v~$`e_|*`n~\u00b7t\u00ad\u00ad^o--^r\u00ad$^y^\u00ad\u00b7- \u00b7**|f|*-o^.\u00b7.r`` `.y`-`o\u00b7``$u"));
    private final SliderSetting stayOpenFor = new SliderSetting(EncryptedString.of("S`\u00b7$t$~^a_`y|_- ^*-O~-$\u00adp$\u00ad_e`$|n-_^\u00ad \u00ad|\u00b7F|~^`o|$r"), 0.0, 20.0, 0.0, 1.0);
    int clock = -1;
    int closeClock = -1;
    TimerUtils openTimer = new TimerUtils();
    TimerUtils closeTimer = new TimerUtils();

    public _4u101013m() {
        super(EncryptedString.of("A\u00ad-`-u\u00ad^`t-.-o.`T_$|o~^t._|^e\u00ad.m"), EncryptedString.of("A``u-$`\u00adt`|\u00ad|o^\u00b7-m-*a|`t$\u00b7$i~-c__\u00b7*a^\u00ad^\u00b7l-^$l~|~|y`^~~ `|-\u00b7e.`|^q\u00ad\u00b7u*.\u00ad$i|`p**~s|`\u00b7 $|*a*-.$ -|\u00b7t*\u00b7__o|.t\u00b7^e\u00b7*\u00ad*m\u00ad.$ ~-|i`\u00ad^\u00b7n.~` |_\u00b7^y\u00ad`\u00b7`o^.-^u_*--r_$ _-\u00b7o$~f_\u00b7\u00adf|**$h*``-a$$^.n**\u00add$~$^ -^-|a`.n`^*$d$\u00b7 $\u00b7*m$\u00ad_a*~\u00b7i$^\u00b7\u00b7n*$ .\u00b7h|~.-a*.-n$|\u00ad|d\u00ad\u00b7$. ^^i^\u00adf$^ \u00b7\u00b7e$~$^m.^-$p|*^t\u00b7-.\u00b7y"), -1, Category.COMBAT);
        this.addSettings(this.mode, this.delay, this.hotbar, this.totemSlot, this.autoSwitch, this.forceTotem, this.autoOpen, this.stayOpenFor);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.clock = -1;
        this.closeClock = -1;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        super.onDisable();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onTick() {
        if (this.shouldOpenScreen() && this.autoOpen.getValue()) {
            this.mc.method_1507((class_437)new FakeInvScreen((class_1657)this.mc.field_1724));
        }
        if (!(this.mc.field_1755 instanceof class_490) && !(this.mc.field_1755 instanceof FakeInvScreen)) {
            this.clock = -1;
            this.closeClock = -1;
            return;
        }
        if (this.clock == -1) {
            this.clock = this.delay.getValueInt();
        }
        if (this.closeClock == -1) {
            this.closeClock = this.stayOpenFor.getValueInt();
        }
        if (this.clock > 0) {
            --this.clock;
        }
        class_1661 inventory = this.mc.field_1724.method_31548();
        if (this.autoSwitch.getValue()) {
            inventory.method_61496(this.totemSlot.getValueInt() - 1);
        }
        if (this.clock <= 0) {
            class_1799 mainHand;
            if (this.mc.field_1724.method_6079().method_7909() != class_1802.field_8288) {
                int slot;
                int n = slot = this.mode.isMode(Mode.Blatant) ? InventoryUtils.findTotemSlot() : InventoryUtils.findRandomTotemSlot();
                if (slot != -1) {
                    bypassNoHotbarMessup = true;
                    try {
                        this.mc.field_1761.method_2906(((class_1723)((class_490)this.mc.field_1755).method_17577()).field_7763, slot, 40, class_1713.field_7791, (class_1657)this.mc.field_1724);
                    }
                    finally {
                        bypassNoHotbarMessup = false;
                    }
                    return;
                }
            }
            if (this.hotbar.getValue() && ((mainHand = this.mc.field_1724.method_6047()).method_7960() || this.forceTotem.getValue() && mainHand.method_7909() != class_1802.field_8288)) {
                int slot;
                int n = slot = this.mode.isMode(Mode.Blatant) ? InventoryUtils.findTotemSlot() : InventoryUtils.findRandomTotemSlot();
                if (slot != -1) {
                    bypassNoHotbarMessup = true;
                    try {
                        this.mc.field_1761.method_2906(((class_1723)((class_490)this.mc.field_1755).method_17577()).field_7763, slot, inventory.method_67532(), class_1713.field_7791, (class_1657)this.mc.field_1724);
                    }
                    finally {
                        bypassNoHotbarMessup = false;
                    }
                    return;
                }
            }
            if (this.shouldCloseScreen() && this.autoOpen.getValue()) {
                if (this.closeClock != 0) {
                    --this.closeClock;
                    return;
                }
                this.mc.field_1755.method_25419();
                this.closeClock = this.stayOpenFor.getValueInt();
            }
        }
    }

    public boolean shouldCloseScreen() {
        if (this.hotbar.getValue()) {
            return this.mc.field_1724.method_31548().method_5438(this.totemSlot.getValueInt() - 1).method_7909() == class_1802.field_8288 && this.mc.field_1724.method_6079().method_7909() == class_1802.field_8288 && this.mc.field_1755 instanceof FakeInvScreen;
        }
        return this.mc.field_1724.method_6079().method_7909() == class_1802.field_8288 && this.mc.field_1755 instanceof FakeInvScreen;
    }

    public boolean shouldOpenScreen() {
        if (this.hotbar.getValue()) {
            return (this.mc.field_1724.method_6079().method_7909() != class_1802.field_8288 || this.mc.field_1724.method_31548().method_5438(this.totemSlot.getValueInt() - 1).method_7909() != class_1802.field_8288) && !(this.mc.field_1755 instanceof FakeInvScreen) && InventoryUtils.countItemExceptHotbar(item -> item == class_1802.field_8288) != 0;
        }
        return this.mc.field_1724.method_6079().method_7909() != class_1802.field_8288 && !(this.mc.field_1755 instanceof FakeInvScreen) && InventoryUtils.countItemExceptHotbar(item -> item == class_1802.field_8288) != 0;
    }

    public static enum Mode {
        Blatant,
        Random;

    }
}

