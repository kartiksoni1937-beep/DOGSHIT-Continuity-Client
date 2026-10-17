/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.Random;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.modules.combat._4nch0rM4cr0;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.BlockUtils;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.RotationUtils;
import me.pepperbell.continuity.utils.WorldUtils;
import me.pepperbell.continuity.utils.rotation.Rotation;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3965;

public final class _54f34nch0r
extends Module
implements TickListener {
    private final ModeSetting<RotationMode> rotationMode = (ModeSetting)new ModeSetting<RotationMode>(EncryptedString.of("R\u00ad\u00b7_^o.$^t`~_a$.t_\u00ad|i\u00ad\u00ad.o-*-~n$-. ~\u00ad`*M__|-o_|d\u00ad`_e"), RotationMode.Silent, RotationMode.class).setDescription(EncryptedString.of("S.*$\u00adi\u00b7^*l`-e\u00b7\u00ad*n_~|t-*_ _|_`:$~ *|`-u.~`s||*|\u00c3`~e-^|<-\u00ad`s|\u00ad_ `-*r~|o-*t|^a`$\u00b7t$^o~~^\u00b7r$^ ..|m`\u00ad.\u00b4_\u00b7\u00ada**n-_a|`g^\u00ade-`$r.\u00b7`~ ~^^.N||_\u00b7o|\u00b7-*r\u00ad^._m~*.`a\u00b7`\u00b7l`|-~:~\u00b7` \u00b7~- _..s.-~\u00adn-_|\u00b7a*~~`p.|s\u00ad|.- ~|-^p$$`$l.``$a\u00ad`|$y*.e`\u00b7-r$*-'^`s.__ \u00b7^`a.\u00b7*`c~~t`$~u|*`*a^`l\u00ad-\u00b7\u00ad \u00ad\u00ad\u00b7~v\u00ad-_i-`\u00ad^e|$*w"));
    private final ModeSetting<ShieldType> shieldType = (ModeSetting)new ModeSetting<ShieldType>(EncryptedString.of("S\u00ad`h`$i-\u00ad_e`$\u00adl*-d--~| ~|T*^y^~p``e"), ShieldType.Glowstone, ShieldType.class).setDescription(EncryptedString.of("B~$$l\u00ad`o_`c^--*k|~. $*_t$_o\u00ad$^$ ~-`u.^`s`\u00ad_~e^$- ~$a\u00b7.^s~_ -~t\u00ad-h_|\u00ad_e-^^ ~*s-\u00b7`^h\u00b7~~.i|~~e|-l.\u00add$\u00ad.| \u00ad-b||\u00ad*e\u00ad*t$**w-$.e_^e*\u00adn.` $``y|\u00b7|o\u00b7\u00b7\u00b7u.`_$ $\u00ad^a|\u00b7n||d*-* *~t\u00ad`-~h~~e\u00ad^\u00ad\u00b7 ^.\u00ad_a$*.n^*\u00adc\u00ad.h`~\u00ado`_**r"));
    private final BooleanSetting smartMode = (BooleanSetting)new BooleanSetting(EncryptedString.of("S\u00ad~m`~^$a^^$|r|*|`t.` |^~M`\u00ado.`\u00b7\u00b7d|^`e"), true).setDescription(EncryptedString.of("S*\u00ad~-k-~\u00b7i~~\u00ad`p.~.\u00ads~*^_ .$w^|$\u00adh$^-.e`_n\u00ad~ |$|$p~`a**~-t`\u00ad\u00b7^h`\u00b7| ||_i\u00ad^\u00b7s-$ _|\u00ada`*.l$^r`\u00b7e*-^\u00b7a\u00ad|\u00b7d.^$y_`^` `~b\u00b7$~l\u00ad|o~\u00b7c~*k~.e$_d\u00b7~;``* \u00ad~.f`.o.^`r`\u00b7 $-a\u00ad$\u00ad.n\u00ad\u00b7c~\u00b7\u00b7~h||$.o`*^r_-__ .$~.b^|e.|l\u00b7_o^`w\u00b7- \u00b7_*\u00ady^~.\u00b7o|*`u^-.:_$- \u00ad\u00b7~p\u00ad\u00b7l\u00b7.\u00ada`-~c~_|~e$`^.s.^- ~$\u00b7\u00ado```n\u00b7` `^._h_*o*`-\u00adr||_i$`z^|^`o**\u00b7^n|*~t\u00ad*a^|l$~ ~$f*|a^*c-\u00ad_e"));
    private final SliderSetting placeDelay = (SliderSetting)new SliderSetting(EncryptedString.of("P~.-`l\u00b7~~`a~|$c_.\u00b7$e.^- ^`-D^$_e.*_l._*^a-*y"), 0.0, 1000.0, 0.0, 10.0).setDescription(EncryptedString.of("M`\u00b7.-i\u00b7$\u00b7|l\u00ad\u00adl$|^i*|s-*-e.$\u00b7|c$_$o-__~n$-$*d_$^*s.**^ ^^`\u00adt$|o*| ___w.$*$a.\u00b7`i$\u00b7-t-^ _$a-$\u00b7~f\u00ad\u00ad~t-\u00b7_-e_-r~.-* -~`a-~n~``\u00adc\u00ad`h|$_o-\u00ad^$r^-_| _^i\u00ad~`-s.$ -$c$`h|--$a|\u00ad\u00b7r^_-*g|-*\u00ade|$*\u00b7d$* _`~b\u00b7$.e-~f$\u00ad`|o$*r`\u00ade~_ _*\u00b7s*`t*.a-`r\u00b7$t~_i^`n*\u00ad\u00b7g_~ ._^t`.-~h\u00b7\u00ade\u00ad| -*r.$\u00b7o_|^*t*`a\u00ad^t^_.i$^|\u00b7o$.`-n~`_\u00ad \u00ad_s|^-|w`|~e$-*e\u00b7-.p"));
    private final SliderSetting rotateTicks = (SliderSetting)new SliderSetting(EncryptedString.of("R-*-\u00ado`_t.-.a.|t.-**e.^^ \u00ad$.T\u00b7\u00b7\u00b7i*~$c`^-*k`~\u00b7.s"), 1.0, 20.0, 1.0, 1.0).setDescription(EncryptedString.of("T.\u00ad.`i^`c^_\u00b7k`*~^s.~^ --*|t\u00b7\u00b7$o|$ \u00b7..-s\u00b7_^p_~e*_$_n*|-d.$ |~s.^w|$-|e^^|e*^p_^\u00b7-i|.~n\u00ad\u00b7g~-.` ~*`^t$*h^-*|e.- ~_h^\u00b7-e`$-a$^d\u00b7- $\u00b7t.$\u00b7-o-\u00b7\u00b7 \u00b7~$^t_^|h||__e_.-` `*_|t$$$a|*`r-~\u00b7g^^e~._t._| -.b\u00ad$e_|f_\u00b7.o\u00b7_^r-|\u00b7|e~|. ~\u00b7|_p*`l\u00ad.a$_|c*|i$^$n~$g~^ ^-`*\u2014.^\u00ad* *|h\u00b7.^i^`^g-\u00ad\u00b7h\u00ad`^e\u00ad\u00b7-r*|*| _~=\u00b7*-$ ^^`m.~o$|$^r^_$|e`-~. $.v\u00b7\u00ad$\u00adi`\u00b7*\u00ads^~~i^-b.`~l~-e|` ~*\u00ad\u00b7m\u00ad-o\u00b7__v`.\u00ade|`m^$e~\u00adn_$`t"));
    private final SliderSetting timeoutTicks = (SliderSetting)new SliderSetting(EncryptedString.of("T$-.i.^$\u00b7m.~e*$`o*|-u_\u00ad`t"), 10.0, 200.0, 60.0, 1.0).setDescription(EncryptedString.of("A_^\u00b7b$.a^*~\u00adn\u00b7.d$^o_*n\u00ad\u00ad~ ~-^t^-.^h~~\u00ad.e$* $$c\u00ad~_u**_r`.r\u00ad_^e`|n\u00b7$--t\u00ad| $\u00ad_~a~_|`n-^|^c_|`h`_|_o^.r-$^ ~|a|^f|\u00adt`-*~e^\u00adr~\u00ad \u00ad`~t*\u00b7$_h-*|_i^*s*.|~ ^-\u00b7`m*.-`a`$^*n-_-y`\u00b7_| ~$*t_$\u00ad\u00b7i*~`c$$^k-$^_s~` $*|i_^..f`` `^s\u00b7.``h-$^i.~-e.~\u00ad*l`-*d$|~ -^w*|a|`s~^\u00ad `\u00ad$$n\u00b7^o.*-t-_^ ._p^^\u00adl\u00b7`~a$\u00adc.~e-\u00ad-\u00b7d"));
    private final BooleanSetting swapToTotem = (BooleanSetting)new BooleanSetting(EncryptedString.of("S\u00b7\u00b7`w|$a$$*.p*_$~ \u00ad-~`T*--^o\u00b7| _\u00adT.\u00ado.^t*.|-e`*m"), false).setDescription(EncryptedString.of("S*^^w^$^a*`|p-_s-_ .~t\u00b7\u00ad\u00b7o~- _\u00ad*t_`-h^._~e^$ \u00ad^~c`$\u00ad_o$$_\u00adn`$|f`^$-i||g*\u00ad.\u00adu`_$r~_e~*|*d-^~^ |-t._o\u00b7\u00ad$~t*\u00b7$e^$-^m|*_ `\u00ads|\u00b7\u00b7_l`\u00ado-\u00adt\u00b7$` .-^a`$.^f\u00b7\u00adt|`-\u00ade_.*|r.$ ~|$|p^\u00ad|\u00adl`~-~a|*c`|\u00ad-i~`$-n|_|$g^\u00b7`. |*-t$.h`_$e$\u00b7_ $`-s|^^*h-\u00ad\u00b7i._\u00b7e~$\u00b7l-^d\u00b7_|` |\u00b7_`b\u00b7$l*$|o~$`*c_$*k"));
    private final SliderSetting swapDelay = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("S.`\u00adw\u00b7*a\u00ad*p-*$ --^|D$`e_\u00ad.l*$_a_`~y"), 0.0, 20.0, 0.0, 1.0).setDescription(EncryptedString.of("T~^i*^$\u00adc`*k_\u00b7s\u00b7$*\u00ad *_$-t~~.o|\u00b7 $_w-$~-a*^\u00b7i.\u00ad_t\u00ad\u00b7. .\u00ad`b||e|\u00adf|`o_^$_r.*|$e\u00ad.* |\u00b7\u00ads*`_\u00adw^~^a.`p_-p\u00b7\u00b7^i^~\u00ad^n-\u00b7.g\u00ad|- |\u00b7t\u00ad-~$o\u00ad` -~`\u00adt--`o.\u00adt\u00ad.||e_*m"))).visible(this.swapToTotem::getValue);
    private final SliderSetting totemSlot = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("T.*^o*~^_t`\u00b7e~*m\u00b7^ .~S~|.\u00b7l*..$o`\u00ad-\u00adt"), 1.0, 9.0, 1.0, 1.0).setDescription(EncryptedString.of("H-$|~o_\u00ad-\u00adt~_~b\u00b7~`*a__r|` *$*$s|\u00adl\u00b7~|`o\u00ad_t$\u00ad\u00b7 `.~~(.`1_-_9_\u00b7$)-\u00ad\u00ad \u00b7\u00b7`^t$``o_^\u00b7 \u00ad.s|__w`\u00adi-*t_\u00b7\u00b7-c*~.h~- $.\u00adt--o-^ \u00ad~a\u00ad~f*\u00adt~\u00b7*e-|r^$* |^p||-_l`|$$a_\u00b7*\u00adc$~*\u00adi$_*n|-~g_~. *-*-t*|`h\u00ad^*`e_..| \u00ad\u00ad^-s`\u00adh\u00b7`$.i-~\u00ad`e\u00ad$l..-d"))).visible(this.swapToTotem::getValue);
    private final BooleanSetting autoBlow = (BooleanSetting)new BooleanSetting(EncryptedString.of("A`*u|\u00ad^.t-*$o$| \u00ad~$B*\u00ad-l\u00ad|o`.\u00adw"), false).setDescription(EncryptedString.of("A-$-u\u00ad-^\u00b7t^.o^`m`\u00b7_*a^*t\u00ad.\u00ad|i*\u00ad^|c.~-a^`_^l|$~$l\u00b7.|y_. *-.d`*|e~*.\u00b7t.$o^.n\u00ad^a$.t_*\u00ad~e-\u00ad|s^\u00b7`_ .\u00b7|.t_.~|h\u00b7^e$... *\u00b7*$a~.~n_^\u00b7c^_.*h~_$$o-^-~r`` ~\u00ada$_f*$t^\u00b7e\u00b7$r`\u00b7. ~^-p-*^_l**_a-*~~c~_`$i^_n``g*\u00b7~ |$|t`~\u00b7\u00adh\u00b7-\u00ad~e`- |\u00b7s\u00ad|.h\u00b7*i\u00ad`*^e~^l`-d^\u00b7|\u00b7 .\u00b7\u00b7*b\u00ad~`*l|**o\u00b7__|c\u00b7~*$k"));
    private final SliderSetting blowDelay = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("B|`-$l.`o.*\u00adw`\u00b7 -^.D|-e$.-l.$$a|-y"), 0.0, 20.0, 0.0, 1.0).setDescription(EncryptedString.of("T^$i\u00b7.^^c`\u00b7_k*~s|-\u00b7 \u00b7~.t`^o~\u00ad^- \u00ad-\u00adw^$~~a|*~-i^~._t_\u00ad^ .`a.|f-~$$t*`-e\u00b7~r..\u00ad .$p.~l._^a^.c\u00b7.^i\u00b7|n_\u00ad\u00b7_g|.\u00b7^ ~_s-.~$h_\u00ad_i~`~.e\u00ad$l^|d|_\u00ad* \u00b7^\u00b7b_$~e~.`f*_o|\u00b7\u00adr`^e`\u00b7*` |-d^$e_.`^t^^.o*^\u00b7n.-.`a\u00ad~t$\u00ad*^i\u00b7^*_n__`.g"))).visible(this.autoBlow::getValue);
    private final SliderSetting blowSlot = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("B\u00b7^|_l`.^_o$`$*w^*~^ *_$*S``\u00adl`**o\u00b7`*~t"), 1.0, 9.0, 1.0, 1.0).setDescription(EncryptedString.of("H$*.o$*-t\u00b7~-|b^-^a\u00b7*-r^`_ .$^s`**$l*\u00b7-~o.*t\u00ad*`_ `~.(*..1\u00ad|-*9\u00b7-)~^ $-t-~o~$$* |~|$u\u00ad$s$^$e\u00ad` `^~`f.^o_|*r*-\u00ad ..-d|^e-\u00b7\u00adt-*\u00b7o``\u00b7|n\u00ad..a*|\u00ad\u00adt\u00ad~i*.\u00b7n\u00ad-g$-^ -\u00adt.$`$h*__\u00b7e|~ |_$|a-\u00ad.n~~\u00adc*|h_`o`~`r"))).visible(this.autoBlow::getValue);
    private class_2338 pendingAnchorPos = null;
    private Stage stage = Stage.IDLE;
    private int delayClock = 0;
    private int timeoutClock = 0;
    private boolean shieldPlaced = false;
    private class_2338 cooldownPos = null;
    private int cooldownTicks = 0;
    private int storedPrevSlot = -1;
    private long placeDelayStartMs = 0L;
    private class_2338 lastShieldedAnchor = null;
    private int busyHoldTicks = 0;
    private Rotation targetRot = null;
    private Rotation sweepStart = null;
    private int sweepTick = 0;
    private class_3965 pendingHit = null;
    private int pendingSlot = -1;
    private static final Random RNG = new Random();

    public _54f34nch0r() {
        super(EncryptedString.of("S$^-_a_`.f$\u00ad^-e\u00ad\u00b7^\u00b7 $\u00b7A`~.n\u00ad*\u00b7|c**\u00ad^h\u00ad.\u00b7\u00ado\u00b7-*\u00b7r"), EncryptedString.of("P-\u00b7l**__a$\u00ad_c*-e.__`s__ -\u00ada`| .$|\u00b7s|.*\u00b7h.~^i*^e_`l\u00ad~^d*\u00b7 |~b\u00b7\u00adl\u00b7\u00b7o*\u00b7-.c_-|k\u00ad-\u00ad $~~b`.e\u00ad*t_.^w|^*e|~e.\u00b7n^\u00b7^~ \u00b7$-y^_o*\u00adu$-_ ~`._a-$|\u00adn\u00b7*d`. \u00ad`a|$~ `--~c-\u00b7.^h\u00ad\u00ad*a|.r|.$.g-~`e_\u00b7d$*` ~$-_a~$*n||c*_.h.\u00b7^-o`|r~_^| ~|*^(.\u00ad*M^|a^\u00adr~|*~l*^o_^|w_-$* $`M.-.`e|\u00ad*t_`h*.\u00ado\u00ad_d_\u00ad)"), -1, Category.COMBAT);
        this.addSettings(this.rotationMode, this.shieldType, this.smartMode, this.placeDelay, this.rotateTicks, this.timeoutTicks, this.swapToTotem, this.swapDelay, this.totemSlot, this.autoBlow, this.blowDelay, this.blowSlot);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.cooldownPos = null;
        this.cooldownTicks = 0;
        this.resetState();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        Sweto.INSTANCE.rotatorManager.reset();
        this.cooldownPos = null;
        this.cooldownTicks = 0;
        this.resetState();
        super.onDisable();
    }

    private void resetState() {
        this.pendingAnchorPos = null;
        this.stage = Stage.IDLE;
        this.delayClock = 0;
        this.timeoutClock = 0;
        this.shieldPlaced = false;
        this.storedPrevSlot = -1;
        this.targetRot = null;
        this.sweepStart = null;
        this.sweepTick = 0;
        this.pendingHit = null;
        this.pendingSlot = -1;
        this.placeDelayStartMs = 0L;
    }

    private void finalizeShield(class_2338 pos) {
        this.cooldownPos = pos;
        this.cooldownTicks = 5;
        this.lastShieldedAnchor = pos;
        this.busyHoldTicks = 2;
        this.resetState();
    }

    public boolean isBusyWith(class_2338 anchorPos) {
        if (!this.isEnabled()) {
            return false;
        }
        if (this.pendingAnchorPos != null && this.pendingAnchorPos.equals((Object)anchorPos)) {
            return true;
        }
        return this.lastShieldedAnchor != null && this.lastShieldedAnchor.equals((Object)anchorPos) && this.busyHoldTicks > 0;
    }

    public void notifyAnchorCharged(class_2338 anchorPos) {
        if (!this.isEnabled()) {
            return;
        }
        if (this.pendingAnchorPos != null) {
            return;
        }
        if (!this.mc.field_1687.method_8320(anchorPos.method_10074()).method_26234((class_1922)this.mc.field_1687, anchorPos.method_10074())) {
            return;
        }
        this.pendingAnchorPos = anchorPos;
        this.shieldPlaced = false;
        this.delayClock = 0;
        this.timeoutClock = 0;
        this.busyHoldTicks = 0;
        this.cooldownPos = null;
        this.cooldownTicks = 0;
        if (this.placeDelay.getValueInt() <= 0) {
            this.beginPlaceSequence();
            return;
        }
        this.placeDelayStartMs = System.currentTimeMillis();
        this.stage = Stage.PLACE_DELAY;
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (this.mc.field_1755 != null) {
            return;
        }
        this.tickRotation();
        this.tickPipeline();
    }

    private void tickPipeline() {
        class_2338 looked;
        if (this.busyHoldTicks > 0) {
            --this.busyHoldTicks;
            if (this.busyHoldTicks == 0) {
                this.lastShieldedAnchor = null;
            }
        }
        if (this.cooldownTicks > 0) {
            --this.cooldownTicks;
            if (this.cooldownTicks == 0) {
                this.cooldownPos = null;
            }
        }
        if (!(this.stage == Stage.IDLE || this.pendingAnchorPos == null || this.shieldPlaced || (looked = this.getLookedAnchorPos()) != null && looked.equals((Object)this.pendingAnchorPos))) {
            this.finalizeShield(this.pendingAnchorPos);
            return;
        }
        if (this.stage == Stage.IDLE) {
            _4nch0rM4cr0 macro = Sweto.INSTANCE.moduleManager.getModule(_4nch0rM4cr0.class);
            if (macro == null || !macro.isEnabled()) {
                return;
            }
            class_2338 anchorPos = macro.getLastChargedAnchorPos();
            if (anchorPos == null) {
                return;
            }
            if (!BlockUtils.isAnchorCharged(anchorPos)) {
                return;
            }
            if (anchorPos.equals((Object)this.cooldownPos)) {
                return;
            }
            if (anchorPos.equals((Object)this.pendingAnchorPos)) {
                return;
            }
            this.pendingAnchorPos = anchorPos;
            this.shieldPlaced = false;
            this.delayClock = 0;
            this.timeoutClock = 0;
            if (this.placeDelay.getValueInt() <= 0) {
                this.beginPlaceSequence();
                return;
            }
            this.placeDelayStartMs = System.currentTimeMillis();
            this.stage = Stage.PLACE_DELAY;
        }
        if (this.pendingAnchorPos == null) {
            return;
        }
        if (!BlockUtils.isAnchorCharged(this.pendingAnchorPos)) {
            this.finalizeShield(this.pendingAnchorPos);
            return;
        }
        ++this.timeoutClock;
        if (!this.shieldPlaced && this.timeoutClock > this.timeoutTicks.getValueInt()) {
            this.resetState();
            return;
        }
        if (this.stage == Stage.PLACE_DELAY) {
            if (System.currentTimeMillis() - this.placeDelayStartMs < (long)this.placeDelay.getValueInt()) {
                return;
            }
            this.delayClock = 0;
            this.beginPlaceSequence();
            return;
        }
        if (this.stage == Stage.ROTATE) {
            this.tickRotateSweep();
            return;
        }
        if (this.stage == Stage.SWAP_DELAY) {
            if (this.delayClock < this.swapDelay.getValueInt()) {
                ++this.delayClock;
                return;
            }
            this.delayClock = 0;
            this.doTotemSwap();
            return;
        }
        if (this.stage == Stage.BLOW_DELAY) {
            if (this.delayClock < this.blowDelay.getValueInt()) {
                ++this.delayClock;
                return;
            }
            this.delayClock = 0;
            this.prepareBlow();
            return;
        }
    }

    private void beginPlaceSequence() {
        class_2338[] candidates;
        boolean falling;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        class_1792 shieldItem = this.shieldType.isMode(ShieldType.Glowstone) ? class_1802.field_8801 : class_1802.field_8281;
        int slot = this.findInHotbar(shieldItem);
        if (slot == -1) {
            this.onShieldPlaced();
            return;
        }
        class_2338 anchorPos = this.pendingAnchorPos;
        class_2338 playerFeet = this.mc.field_1724.method_24515();
        class_2338 playerHead = playerFeet.method_10084();
        class_243 anchorCenter = anchorPos.method_46558();
        class_243 playerCenter = playerFeet.method_46558();
        class_243 dir = playerCenter.method_1020(anchorCenter).method_1029();
        class_2338 primary = class_2338.method_49638((class_2374)anchorCenter.method_1019(dir.method_1021(1.5)));
        boolean smart = this.smartMode.getValue();
        boolean anchorBelow = anchorPos.method_10264() < playerFeet.method_10264();
        boolean bl = falling = this.mc.field_1724.field_6017 > 0.0 || !this.mc.field_1724.method_24828();
        if (falling) {
            smart = false;
        }
        if (smart && anchorPos.method_10264() == playerFeet.method_10264() + 1) {
            this.onShieldPlaced();
            return;
        }
        if (!(!smart || primary.equals((Object)playerFeet) || primary.equals((Object)playerHead) || primary.equals((Object)anchorPos) || this.mc.field_1687.method_8320(primary).method_45474())) {
            this.onShieldPlaced();
            return;
        }
        for (class_2338 candidate : candidates = this.buildCandidates(anchorPos, anchorCenter, playerCenter, primary, anchorBelow, smart)) {
            class_2338 below;
            if (candidate == null || candidate.equals((Object)anchorPos) || candidate.equals((Object)playerFeet) || candidate.equals((Object)playerHead) || smart && anchorBelow && candidate.equals((Object)anchorPos.method_10084()) || !this.mc.field_1687.method_8320(below = candidate.method_10074()).method_26234((class_1922)this.mc.field_1687, below) && !below.equals((Object)anchorPos)) continue;
            if (!this.mc.field_1687.method_8320(candidate).method_45474()) {
                this.onShieldPlaced();
                return;
            }
            class_3965 hit = this.findPlaceableHit(candidate, anchorPos);
            if (hit == null) continue;
            class_243 lookTarget = this.randomFacePoint(candidate, hit.method_17780());
            Rotation rawTarget = RotationUtils.getRotationToVec(RotationUtils.getEyesPos((class_1657)this.mc.field_1724), lookTarget);
            float yawDrift = (RNG.nextFloat() - 0.5f) * 1.2f;
            float pitchDrift = (RNG.nextFloat() - 0.5f) * 0.6f;
            this.pendingHit = hit;
            this.pendingSlot = slot;
            this.targetRot = new Rotation(rawTarget.yaw() + yawDrift, rawTarget.pitch() + pitchDrift);
            this.sweepStart = new Rotation(this.mc.field_1724.method_36454(), this.mc.field_1724.method_36455());
            this.sweepTick = 0;
            this.stage = Stage.ROTATE;
            return;
        }
        this.onShieldPlaced();
    }

    private void tickRotateSweep() {
        if (this.mc.field_1724 == null || this.targetRot == null || this.sweepStart == null) {
            this.resetState();
            return;
        }
        if (this.rotationMode.isMode(RotationMode.Normal)) {
            float prevYaw = this.mc.field_1724.method_36454();
            float prevPitch = this.mc.field_1724.method_36455();
            this.mc.field_1724.method_36456(this.targetRot.yaw());
            this.mc.field_1724.method_36457(this.targetRot.pitch());
            if (this.pendingHit != null && this.pendingSlot >= 0) {
                int prevSlot = this.mc.field_1724.method_31548().method_67532();
                this.mc.field_1724.method_31548().method_61496(this.pendingSlot);
                WorldUtils.placeBlock(this.pendingHit, true);
                this.mc.field_1724.method_31548().method_61496(prevSlot);
            }
            this.mc.field_1724.method_36456(prevYaw);
            this.mc.field_1724.method_36457(prevPitch);
            this.pendingHit = null;
            this.pendingSlot = -1;
            this.onShieldPlaced();
            return;
        }
        int totalTicks = Math.max(1, this.rotateTicks.getValueInt());
        ++this.sweepTick;
        if (totalTicks <= 1) {
            Sweto.INSTANCE.rotatorManager.setRotation(this.targetRot);
        } else {
            float t = (float)this.sweepTick / (float)totalTicks;
            float smoothT = t * t * (3.0f - 2.0f * t);
            float startYaw = this.sweepStart.yaw();
            float endYaw = this.targetRot.yaw();
            float yawDelta = _54f34nch0r.wrapYaw(endYaw - startYaw);
            float yaw = startYaw + yawDelta * smoothT;
            float pitch = this.sweepStart.pitch() + (this.targetRot.pitch() - this.sweepStart.pitch()) * smoothT;
            Sweto.INSTANCE.rotatorManager.setRotation(new Rotation(yaw, pitch));
        }
        if (this.sweepTick >= totalTicks) {
            if (this.pendingHit != null && this.pendingSlot >= 0) {
                int prevSlot = this.mc.field_1724.method_31548().method_67532();
                this.mc.field_1724.method_31548().method_61496(this.pendingSlot);
                WorldUtils.placeBlock(this.pendingHit, true);
                this.mc.field_1724.method_31548().method_61496(prevSlot);
            }
            this.pendingHit = null;
            this.pendingSlot = -1;
            this.onShieldPlaced();
        }
    }

    private static float wrapYaw(float delta) {
        if ((delta %= 360.0f) > 180.0f) {
            delta -= 360.0f;
        }
        if (delta < -180.0f) {
            delta += 360.0f;
        }
        return delta;
    }

    private void onShieldPlaced() {
        this.shieldPlaced = true;
        this.timeoutClock = 0;
        this.targetRot = null;
        this.sweepStart = null;
        this.sweepTick = 0;
        boolean needSwap = this.swapToTotem.getValue() && this.shieldType.isMode(ShieldType.Obsidian);
        boolean needBlow = this.autoBlow.getValue();
        if (needSwap) {
            this.delayClock = 0;
            this.stage = Stage.SWAP_DELAY;
        } else if (needBlow) {
            this.delayClock = 0;
            this.stage = Stage.BLOW_DELAY;
        } else {
            class_2338 pos = this.pendingAnchorPos;
            this.finalizeShield(pos);
        }
    }

    private void doTotemSwap() {
        if (this.mc.field_1724 == null) {
            this.finalizeShield(this.pendingAnchorPos);
            return;
        }
        this.mc.field_1724.method_31548().method_61496(this.totemSlot.getValueInt() - 1);
        this.targetRot = null;
        if (this.autoBlow.getValue()) {
            this.delayClock = 0;
            this.stage = Stage.BLOW_DELAY;
        } else {
            class_2338 pos = this.pendingAnchorPos;
            this.finalizeShield(pos);
        }
    }

    private void prepareBlow() {
        if (this.mc.field_1724 == null || this.pendingAnchorPos == null) {
            this.finalizeShield(this.pendingAnchorPos);
            return;
        }
        class_243 anchorCenter = this.pendingAnchorPos.method_46558().method_1031(0.0, 0.5, 0.0);
        Rotation rawTarget = RotationUtils.getRotationToVec(RotationUtils.getEyesPos((class_1657)this.mc.field_1724), anchorCenter);
        if (this.rotationMode.isMode(RotationMode.Normal)) {
            float prevYaw = this.mc.field_1724.method_36454();
            float prevPitch = this.mc.field_1724.method_36455();
            this.mc.field_1724.method_36456(rawTarget.yaw());
            this.mc.field_1724.method_36457(rawTarget.pitch());
            this.mc.field_1724.method_31548().method_61496(this.blowSlot.getValueInt() - 1);
            class_3965 hit = new class_3965(this.pendingAnchorPos.method_46558().method_1031(0.0, 0.5, 0.0), class_2350.field_11036, this.pendingAnchorPos, false);
            WorldUtils.placeBlock(hit, true);
            this.mc.field_1724.method_36456(prevYaw);
            this.mc.field_1724.method_36457(prevPitch);
            class_2338 pos = this.pendingAnchorPos;
            this.finalizeShield(pos);
            return;
        }
        Sweto.INSTANCE.rotatorManager.setRotation(rawTarget);
        this.mc.field_1724.method_31548().method_61496(this.blowSlot.getValueInt() - 1);
        class_3965 hit = new class_3965(this.pendingAnchorPos.method_46558().method_1031(0.0, 0.5, 0.0), class_2350.field_11036, this.pendingAnchorPos, false);
        WorldUtils.placeBlock(hit, true);
        class_2338 pos = this.pendingAnchorPos;
        this.finalizeShield(pos);
    }

    private void tickRotation() {
        if (this.stage == Stage.ROTATE) {
            return;
        }
        if (this.rotationMode.isMode(RotationMode.Normal)) {
            Sweto.INSTANCE.rotatorManager.reset();
            return;
        }
        if (this.targetRot != null) {
            Sweto.INSTANCE.rotatorManager.setRotation(this.targetRot);
        } else {
            Sweto.INSTANCE.rotatorManager.reset();
        }
    }

    private class_2338[] buildCandidates(class_2338 anchorPos, class_243 anchorCenter, class_243 playerCenter, class_2338 primary, boolean anchorBelow, boolean smart) {
        if (smart && anchorBelow) {
            double dx = playerCenter.field_1352 - anchorCenter.field_1352;
            double dz = playerCenter.field_1350 - anchorCenter.field_1350;
            class_2350 closest = Math.abs(dx) > Math.abs(dz) ? (dx > 0.0 ? class_2350.field_11034 : class_2350.field_11039) : (dz > 0.0 ? class_2350.field_11035 : class_2350.field_11043);
            class_2350[] others = new class_2350[3];
            int idx = 0;
            for (class_2350 d : new class_2350[]{class_2350.field_11043, class_2350.field_11035, class_2350.field_11034, class_2350.field_11039}) {
                if (d == closest) continue;
                others[idx++] = d;
            }
            return new class_2338[]{anchorPos.method_10093(closest), anchorPos.method_10093(others[0]), anchorPos.method_10093(others[1]), anchorPos.method_10093(others[2]), primary, anchorPos.method_10074()};
        }
        class_2350[] dirs = class_2350.values();
        class_2338[] candidates = new class_2338[dirs.length + 1];
        candidates[0] = primary;
        for (int i = 0; i < dirs.length; ++i) {
            candidates[i + 1] = anchorPos.method_10093(dirs[i]);
        }
        return candidates;
    }

    private class_3965 findPlaceableHit(class_2338 targetPos, class_2338 anchorPos) {
        if (this.mc.field_1687 == null) {
            return null;
        }
        for (class_2350 dir : class_2350.values()) {
            class_2338 neighbor = targetPos.method_10093(dir);
            boolean isAnchor = neighbor.equals((Object)anchorPos);
            boolean isFullCube = this.mc.field_1687.method_8320(neighbor).method_26234((class_1922)this.mc.field_1687, neighbor);
            if (!isFullCube && !isAnchor) continue;
            class_243 hitVec = class_243.method_24953((class_2382)targetPos).method_1019(class_243.method_24954((class_2382)dir.method_62675()).method_1021(0.5));
            return new class_3965(hitVec, dir.method_10153(), neighbor, false);
        }
        return null;
    }

    private class_2338 getLookedAnchorPos() {
        class_2338 behind;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return null;
        }
        class_239 class_2392 = this.mc.field_1765;
        if (!(class_2392 instanceof class_3965)) {
            return null;
        }
        class_3965 bhr = (class_3965)class_2392;
        if (bhr.method_17783() != class_239.class_240.field_1332) {
            return null;
        }
        class_2338 pos = bhr.method_17777();
        if (this.mc.field_1687.method_8320(pos).method_27852(class_2246.field_23152)) {
            return pos;
        }
        class_2338 offset = pos.method_10093(bhr.method_17780());
        if (this.mc.field_1687.method_8320(offset).method_27852(class_2246.field_23152)) {
            return offset;
        }
        if (this.mc.field_1687.method_8320(pos).method_45474() && this.mc.field_1687.method_8320(behind = pos.method_10093(bhr.method_17780().method_10153())).method_27852(class_2246.field_23152)) {
            return behind;
        }
        return null;
    }

    private int findInHotbar(class_1792 item) {
        if (this.mc.field_1724 == null) {
            return -1;
        }
        for (int i = 0; i < 9; ++i) {
            if (!this.mc.field_1724.method_31548().method_5438(i).method_31574(item)) continue;
            return i;
        }
        return -1;
    }

    private class_243 randomFacePoint(class_2338 pos, class_2350 face) {
        double x = (double)pos.method_10263() + RNG.nextDouble(0.05, 0.95);
        double y = (double)pos.method_10264() + RNG.nextDouble(0.05, 0.95);
        double z = (double)pos.method_10260() + RNG.nextDouble(0.05, 0.95);
        return switch (face) {
            default -> throw new MatchException(null, null);
            case class_2350.field_11033 -> new class_243(x, (double)pos.method_10264(), z);
            case class_2350.field_11036 -> new class_243(x, (double)pos.method_10264() + 1.0, z);
            case class_2350.field_11043 -> new class_243(x, y, (double)pos.method_10260());
            case class_2350.field_11035 -> new class_243(x, y, (double)pos.method_10260() + 1.0);
            case class_2350.field_11039 -> new class_243((double)pos.method_10263(), y, z);
            case class_2350.field_11034 -> new class_243((double)pos.method_10263() + 1.0, y, z);
        };
    }

    public static enum RotationMode {
        Silent,
        Normal;

    }

    public static enum ShieldType {
        Glowstone,
        Obsidian;

    }

    private static enum Stage {
        IDLE,
        PLACE_DELAY,
        ROTATE,
        SWAP_DELAY,
        BLOW_DELAY;

    }
}

