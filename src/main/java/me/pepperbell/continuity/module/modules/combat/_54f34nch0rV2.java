/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.Random;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.KeyUtils;
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
import net.minecraft.class_2596;
import net.minecraft.class_2769;
import net.minecraft.class_2828;
import net.minecraft.class_3965;
import net.minecraft.class_4969;

public final class _54f34nch0rV2
extends Module
implements TickListener {
    private final ModeSetting<ShieldType> shieldType = (ModeSetting)new ModeSetting<ShieldType>(EncryptedString.of("S_\u00ad\u00b7^h.$$i_*$_e|\u00adl\u00ad|~\u00add\u00b7| ^_T$$`y\u00b7**_p-\u00ade"), ShieldType.Glowstone, ShieldType.class).setDescription(EncryptedString.of("B|\u00b7l*\u00b7~|o\u00b7|\u00ad\u00adc.$~$k\u00ad\u00b7 ^`\u00ad~t~`_o~^_| \u00ad.u`.\u00ads|\u00b7e*$\u00ad *~a^$\u00b7s__- __|-s\u00ad*\u00adh~$~~i-.~e\u00ad.\u00b7^l.\u00b7_\u00b7d"));
    private final BooleanSetting smartMode = (BooleanSetting)new BooleanSetting(EncryptedString.of("S\u00ad-m||\u00ad$a|\u00b7|r.^\u00ad-t|.$\u00b7 `.-~M|\u00ado\u00ad_..d|^_e"), true).setDescription(EncryptedString.of("S$`-k|~i\u00ad-\u00adp^.~- \u00ad\u00b7-\u00ads-~~\u00b7h--`i\u00b7*`e~--\u00b7l^$.d*\u00ad \u00b7$|\u00b7w\u00ad*h^\u00b7\u00ade~`n`-\u00ad .*.p\u00ad`_a``\u00b7t$.**h_|*^ \u00b7-\u00ad_i*\u00b7s^. -_b$~l`_\u00b7o~|c|.^.k$_e|--d"));
    private final BooleanSetting autoBlow = (BooleanSetting)new BooleanSetting(EncryptedString.of("A|\u00ad^_u_`.t.-o\u00ad__ ``B|_`l`$o.\u00b7*~w"), true).setDescription(EncryptedString.of("B\u00ad_l|\u00ad~o\u00b7|.^w-.. \u00b7`a.-n$$$$c\u00b7$\u00ad$h\u00b7$o|-|_r$|$ $.-|a`\u00ad$|f.^t`|\u00ad~e.-r~\u00b7 \u00ad|s\u00b7-$^h\u00b7_~i^_~e$_\u00b7l-``d"));
    private final BooleanSetting placeAnchor = (BooleanSetting)new BooleanSetting(EncryptedString.of("P`-.\u00adl|*a^|\u00adc*`|e^. \u00ad$.|A.\u00b7n\u00b7\u00adc`_.\u00adh^|\u00b7o^\u00b7\u00b7r"), true).setDescription(EncryptedString.of("P_||~l|-~a$\u00adc`|*~e\u00ad\u00ad.| `~_a__\u00ad$n|^c`_|h*`o~^r^*$ *~\u00b7|i*\u00b7f.- -|$l$_`-o-\u00ad\u00ad^o$_k$$i\u00ad\u00adn$_$g|~- \u00b7-\u00ad\u00ada~-`t-|_ ~-$a.^^| .*~\u00b7v`-*|a-|l|\u00ad*\u00b7i_^*^d-`. \u00ad^$$b$-l.\u00b7o`-\u00ad*c|-~_k"));
    private final SliderSetting anchorSwitchDelay = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("A|~$n.~^|c\u00ad-h$*$-o`.r$~ ..-S^`w_\u00b7i|_t`.c`\u00b7h\u00ad\u00b7. -\u00b7|D^*e$\u00ad`*l~.|a$\u00ad\u00b7\u00ady"), 0.0, 500.0, 0.0, 10.0).setDescription(EncryptedString.of("M*~_*s`\u00ad| ^_d*`*\u00b7e.`_.l^$\u00b7|a\u00b7*_y$*~\u00ad \u00b7\u00ad~\u00adb\u00ad.\u00b7.e`-f*\u00b7o.~~r*~e-$ -`s_$w`.i$\u00adt-$\u00ad^c^|\u00b7.h`.i.---n-*g|\u00b7 --t_`^o$^ *~a-~n\u00b7.|c\u00ad~||h.~~o_\u00ad\u00ad`r"))).visible(this.placeAnchor::getValue);
    private final SliderSetting anchorPlaceDelay = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("A.*\u00b7n||^c^.h|.\u00ado$^r.\u00ad$~ ~.P$$-l__|a_^$c*\u00ade$. |\u00b7D\u00b7_e*~-l``^~a|-~*y"), 0.0, 500.0, 0.0, 10.0).setDescription(EncryptedString.of("M-`\u00b7s\u00b7`^ `.d`\u00b7\u00ad^e\u00ad-l^\u00ada|*y_- $_a||.f$_~t\u00ad``e\u00b7`r.| ~_|\u00b7p-^~l^\u00b7.a*$`c^.i*.n\u00ad\u00b7g\u00ad\u00ad -\u00ad\u00b7a`^n|*c~|*\u00adh^`o\u00ad$-r"))).visible(this.placeAnchor::getValue);
    private final SliderSetting glowstoneSwitchDelay = (SliderSetting)new SliderSetting(EncryptedString.of("G**l.\u00ad|\u00b7o~^`w*~s.$\u00adt.|*o^*~~n|.\u00ad`e^\u00b7` -~_S`*w-`i.*_`t.~|c^~^h*` *|__D\u00ad*~_e$\u00ad-~l~.-~a^_-y"), 0.0, 500.0, 0.0, 10.0).setDescription(EncryptedString.of("M*^-s_\u00b7 $*\u00b7d.*e-|l||~a_`\u00b7y^_\u00ad` *.-_b\u00ad_-e|\u00adf_*|o\u00ad^\u00b7|r\u00b7_e_- \u00ad*$\u00b7s`^w.\u00ad~i`$t*_-c~\u00b7|h|-i*.\u00b7|n_*g~$- |$\u00b7$t~`o~^ --g^|l|~o\u00ad-_w^\u00b7*`s*$.~t\u00ad_*o|_n$.~~e"));
    private final SliderSetting chargeDelay = (SliderSetting)new SliderSetting(EncryptedString.of("C^--\u00b7h`\u00b7a$~^\u00b7r~*.\u00b7g**e~__ `.|^D|\u00b7$e^$_l_|^_a|*.~y"), 0.0, 500.0, 0.0, 10.0).setDescription(EncryptedString.of("M\u00ad|s\u00b7$_~ ~_`\u00add~^~e|~l$`.a^.*-y|_ _\u00ad~$b*-^-e$|f~|$_o~*_\u00b7r^\u00ad|e~- _~c`**-h^`\u00b7$a\u00ad^$\u00adr\u00ad\u00ad*g^.~i_.n$--g"));
    private final SliderSetting shieldPlaceDelay = (SliderSetting)new SliderSetting(EncryptedString.of("S|`|h$.`~i\u00b7-e|*~l|_$_d-| --\u00b7_P\u00ad.l^*$~a._|c~^e^$\u00b7 `|D*|~\u00b7e\u00ad~\u00b7l_^a-|y"), 0.0, 500.0, 0.0, 10.0).setDescription(EncryptedString.of("M$\u00ad-s|\u00ad \u00ad|||d-^^e\u00ad$\u00b7l~$\u00ad^a*\u00b7~y|^ __\u00ad*b\u00b7_~-e_\u00adf-_*o.$~r^\u00b7.e^$`\u00b7 ~*\u00b7*p\u00b7_-l\u00b7`^|a`|c_.`\u00b7i\u00ad*\u00ad|n$^._g*$^| |*s^~_$h$^-*i.\u00ade\u00b7|^l*|-~d"));
    private final SliderSetting explodeDelay = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("E_\u00ad^x$.p_*~\u00adl`$o`$_d^*~e$~\u00b7 ..$D-$.e-~l$.a`^y"), 0.0, 500.0, 0.0, 10.0).setDescription(EncryptedString.of("M|~_s\u00ad-_ \u00b7\u00b7d$_e\u00b7\u00ad$l*$`~a$\u00b7y$\u00b7_ ~-._b|*e^~~*f``.o\u00b7**r^\u00b7-\u00ade_~\u00ad ^\u00b7_e\u00b7\u00b7\u00b7|x-*\u00b7p-\u00b7^l\u00b7~|o\u00b7\u00b7^\u00add-\u00ad.i^||`n$`\u00ad\u00b7g"))).visible(this.autoBlow::getValue);
    private final SliderSetting explodeSlot = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("E.-x~*.p^\u00ad^|l$~o*~d*|.^e|_\u00b7 \u00ad\u00b7^S^\u00adl\u00ad-_o~-t"), 1.0, 9.0, 1.0, 1.0).setDescription(EncryptedString.of("H\u00b7.*o|-t`\u00b7b.-a__*r-$ ~$^\u00b7s\u00ad~^l\u00b7~o`\u00b7t`|~ -.-t$-o\u00b7| |\u00b7b``l^-*o\u00b7--\u00adw..~ ~^`w*..^i._^-t*`*h$\u00ad|\u00b7 ^**$(..\u00ad1.`_9.\u00ad_)"))).visible(this.autoBlow::getValue);
    private final SliderSetting timeoutMs = (SliderSetting)new SliderSetting(EncryptedString.of("T\u00b7^.i\u00b7*m\u00b7$$e*-^o.-_$u-*t"), 500.0, 5000.0, 2000.0, 100.0).setDescription(EncryptedString.of("A~.~b-$\u00ado*$\u00adr-|`t$\u00ad$s$~ .^-c|~y\u00ad.~c_.~l-_e-`\u00b7. \u00ad|._i\u00ad.f^$* \u00ad~*^a.\u00ad^^n\u00ad`y|*_. .^*\u00ads.-\u00adt^|$e_~.p\u00b7~ **t`|a|.k*-e~$~s*\u00b7 |*_t`~\u00ado$\u00b7_o`_^` \u00ad|~\u00adl|`|_o..n\u00ad\u00ad\u00ad^g"));
    private final KeybindSetting activateKey = (KeybindSetting)new KeybindSetting(EncryptedString.of("A^^c*$t`.^-i~$~*v|`\u00b7a$-^^t$-e-- .-$|K\u00b7.-`e.^*\u00b7y"), -1, false).setDescription(EncryptedString.of("P`._r~`e_~\u00ads~\u00ad*`s.\u00b7* ^_t._o`_ $-s$$`t`-\u00ad_a`^$r$|t-\u00b7.^ $^\u00ad\u00adt|`.h\u00b7\u00ad^_e$*\u00ad^ _~^\u00adc^|``y^$|_c`-_*l^*e"));
    private State state = State.IDLE;
    private class_2338 targetPos = null;
    private class_2338 anchorPos = null;
    private long stepStartMs = 0L;
    private boolean started = false;
    private class_3965 pendingHit = null;
    private int pendingSlot = -1;
    private Rotation targetRot = null;
    private Rotation sweepStart = null;
    private int sweepTick = 0;
    private int rotateTicksSetting = 1;
    private boolean shieldPlaced = false;
    private int glowstoneCountOnCharge = 0;
    private int chargeRetries = 0;
    private static final Random RNG = new Random();

    public _54f34nch0rV2() {
        super(EncryptedString.of("S^\u00b7$a-_f-\u00b7~e_^\u00adA^~~$n-.\u00b7\u00b7c*-|h|_o\u00b7~r~$V^\u00b72"), EncryptedString.of("P`..-l^.|.a^|c~.`.e-\u00b7\u00b7\u00ads_*$` .^$`a\u00b7^\u00adn\u00ad~c..h|$*o^_`r~^\u00b7|,~* `-$c`|*h-\u00ada~~.r~~|g\u00ad|\u00ade.$~^s.`*\u00ad,^\u00b7|$ $_s^*`h^`i\u00b7_*|e~-l`-_^d-.~\u00b7s*.~,.$ $_.^a-\u00ad``n~-_$d_` $\u00b7-b-^$~l$.~\u00b7o|.`-w*_\u00ad\u00b7s~\u00b7| $-`^i`~\u00b7n$|$ *$o$$|n|\u00b7~e^~ ~~_`c_^.y~^c$|\u00b7l\u00b7*_e"), -1, Category.COMBAT);
        this.setSubCategory("Crystal");
        this.addSettings(this.shieldType, this.smartMode, this.autoBlow, this.placeAnchor, this.anchorSwitchDelay, this.anchorPlaceDelay, this.glowstoneSwitchDelay, this.chargeDelay, this.shieldPlaceDelay, this.explodeDelay, this.explodeSlot, this.timeoutMs, this.activateKey);
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
        Sweto.INSTANCE.rotatorManager.reset();
        this.reset();
        super.onDisable();
    }

    private void reset() {
        this.state = State.IDLE;
        this.targetPos = null;
        this.anchorPos = null;
        this.stepStartMs = 0L;
        this.started = false;
        this.pendingHit = null;
        this.pendingSlot = -1;
        this.targetRot = null;
        this.sweepStart = null;
        this.sweepTick = 0;
        this.shieldPlaced = false;
        this.glowstoneCountOnCharge = 0;
        this.chargeRetries = 0;
    }

    private void startStep(State next) {
        this.state = next;
        this.stepStartMs = System.currentTimeMillis();
    }

    private boolean hasDelayElapsed(int delayMs) {
        return delayMs <= 0 || System.currentTimeMillis() - this.stepStartMs >= (long)delayMs;
    }

    private void placeBlockWithRotation(class_3965 hit, Rotation rot) {
        Sweto.INSTANCE.rotatorManager.reset();
        this.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2831(rot.yaw(), rot.pitch(), this.mc.field_1724.method_24828(), false));
        float prevYaw = this.mc.field_1724.method_36454();
        float prevPitch = this.mc.field_1724.method_36455();
        this.mc.field_1724.method_36456(rot.yaw());
        this.mc.field_1724.method_36457(rot.pitch());
        WorldUtils.placeBlock(hit, true);
        this.mc.field_1724.method_36456(prevYaw);
        this.mc.field_1724.method_36457(prevPitch);
    }

    @Override
    public void onTick() {
        boolean bl;
        boolean processed;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (this.mc.field_1755 != null) {
            return;
        }
        do {
            processed = false;
            if (this.state == State.IDLE) {
                if (this.started) {
                    this.started = false;
                    break;
                }
                if (!KeyUtils.isKeyPressed(this.activateKey.getKey())) break;
                this.started = true;
                this.tryStart();
                if (this.state == State.IDLE) break;
                processed = true;
                break;
            }
            this.mc.field_1690.field_1904.method_23481(false);
            if (this.state == State.DONE) {
                this.reset();
                break;
            }
            if (System.currentTimeMillis() - this.stepStartMs > (long)this.timeoutMs.getValueInt()) {
                this.reset();
                break;
            }
            switch (this.state.ordinal()) {
                case 1: {
                    bl = this.tickSwapAnchor();
                    break;
                }
                case 2: {
                    bl = this.tickPlaceAnchor();
                    break;
                }
                case 3: {
                    bl = this.tickWaitAnchor();
                    break;
                }
                case 4: {
                    bl = this.tickSwapGlowstone();
                    break;
                }
                case 5: {
                    bl = this.tickCharge();
                    break;
                }
                case 6: {
                    bl = this.tickWaitCharge();
                    break;
                }
                case 7: {
                    bl = this.tickSwapShield();
                    break;
                }
                case 8: {
                    bl = this.tickPlaceShield();
                    break;
                }
                case 9: {
                    bl = this.tickRotateShield();
                    break;
                }
                case 10: {
                    bl = this.tickBlow();
                    break;
                }
                default: {
                    bl = false;
                }
            }
        } while (processed = bl);
    }

    private boolean hasAnchorSupport(class_2338 pos) {
        if (pos == null) {
            return false;
        }
        return this.mc.field_1687.method_8320(pos.method_10074()).method_26234((class_1922)this.mc.field_1687, pos.method_10074());
    }

    private void tryStart() {
        class_3965 bhr;
        class_239 class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3965 && (bhr = (class_3965)class_2392).method_17783() == class_239.class_240.field_1332) {
            class_2338 pos = bhr.method_17777();
            if (this.mc.field_1687.method_8320(pos).method_27852(class_2246.field_23152)) {
                if (!this.hasAnchorSupport(pos)) {
                    return;
                }
                this.anchorPos = pos;
                if (this.isAnchorCharged(pos)) {
                    this.startStep(State.SWAP_SHIELD);
                } else {
                    this.startStep(State.SWAP_GLOWSTONE);
                }
                return;
            }
            if (this.placeAnchor.getValue()) {
                this.targetPos = this.mc.field_1687.method_8320(pos).method_45474() ? pos : pos.method_10093(bhr.method_17780());
                if (this.targetPos != null && this.mc.field_1687.method_8320(this.targetPos).method_45474() && this.mc.field_1687.method_8320(this.targetPos.method_10074()).method_26234((class_1922)this.mc.field_1687, this.targetPos.method_10074())) {
                    this.startStep(State.SWAP_ANCHOR);
                    return;
                }
            }
        }
    }

    private boolean tickSwapAnchor() {
        if (!this.hasDelayElapsed(this.anchorSwitchDelay.getValueInt())) {
            return false;
        }
        if (!this.findAndSelect(class_1802.field_23141)) {
            this.reset();
            return false;
        }
        this.startStep(State.PLACE_ANCHOR);
        return true;
    }

    private boolean tickPlaceAnchor() {
        if (!this.hasDelayElapsed(this.anchorPlaceDelay.getValueInt())) {
            return false;
        }
        if (this.anchorPos != null && this.mc.field_1687.method_8320(this.anchorPos).method_27852(class_2246.field_23152)) {
            this.startStep(State.SWAP_GLOWSTONE);
            return true;
        }
        class_3965 hit = this.buildPlaceHit(this.targetPos);
        if (hit != null) {
            WorldUtils.placeBlock(hit, true);
            this.anchorPos = this.targetPos;
            this.startStep(State.WAIT_ANCHOR);
            return true;
        }
        this.reset();
        return false;
    }

    private boolean tickWaitAnchor() {
        if (this.mc.field_1687.method_8320(this.targetPos).method_27852(class_2246.field_23152)) {
            this.anchorPos = this.targetPos;
            this.startStep(State.SWAP_GLOWSTONE);
            return true;
        }
        if (System.currentTimeMillis() - this.stepStartMs > 300L) {
            this.reset();
        }
        return false;
    }

    private boolean tickSwapGlowstone() {
        if (!this.hasDelayElapsed(this.glowstoneSwitchDelay.getValueInt())) {
            return false;
        }
        if (!this.findAndSelect(class_1802.field_8801)) {
            this.reset();
            return false;
        }
        this.chargeRetries = 0;
        this.startStep(State.CHARGE);
        return true;
    }

    private boolean tickCharge() {
        if (this.isAnchorCharged(this.anchorPos)) {
            this.chargeRetries = 0;
            this.startStep(State.SWAP_SHIELD);
            return true;
        }
        if (this.chargeRetries >= 2) {
            this.reset();
            return false;
        }
        if (!this.hasDelayElapsed(this.chargeDelay.getValueInt())) {
            return false;
        }
        if (!this.mc.field_1724.method_6047().method_31574(class_1802.field_8801) && !this.findAndSelect(class_1802.field_8801)) {
            this.reset();
            return false;
        }
        if (this.mc.field_1724.method_6047().method_31574(class_1802.field_8801)) {
            this.glowstoneCountOnCharge = this.countGlowstone();
            class_243 anchorCenter = this.anchorPos.method_46558().method_1031(0.0, 0.5, 0.0);
            Rotation raw = RotationUtils.getRotationToVec(RotationUtils.getEyesPos((class_1657)this.mc.field_1724), anchorCenter);
            class_3965 hit = new class_3965(this.anchorPos.method_46558().method_1031(0.0, 0.5, 0.0), class_2350.field_11036, this.anchorPos, false);
            this.placeBlockWithRotation(hit, raw);
            this.startStep(State.WAIT_CHARGE);
            return true;
        }
        return false;
    }

    private boolean tickWaitCharge() {
        if (this.isAnchorCharged(this.anchorPos)) {
            this.chargeRetries = 0;
            this.startStep(State.SWAP_SHIELD);
            return true;
        }
        if (System.currentTimeMillis() - this.stepStartMs > 2000L) {
            if (this.countGlowstone() < this.glowstoneCountOnCharge) {
                this.chargeRetries = 0;
                this.startStep(State.SWAP_GLOWSTONE);
            } else {
                ++this.chargeRetries;
                this.startStep(State.CHARGE);
            }
            return true;
        }
        return false;
    }

    private int countGlowstone() {
        if (this.mc.field_1724 == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < this.mc.field_1724.method_31548().method_5439(); ++i) {
            if (!this.mc.field_1724.method_31548().method_5438(i).method_31574(class_1802.field_8801)) continue;
            count += this.mc.field_1724.method_31548().method_5438(i).method_7947();
        }
        return count;
    }

    private boolean tickSwapShield() {
        class_1792 shieldItem = this.shieldType.isMode(ShieldType.Glowstone) ? class_1802.field_8801 : class_1802.field_8281;
        int slot = this.findInHotbar(shieldItem);
        if (slot == -1) {
            this.onShieldDone();
            return true;
        }
        if (this.mc.field_1724.method_31548().method_67532() != slot) {
            this.mc.field_1724.method_31548().method_61496(slot);
        }
        this.startStep(State.PLACE_SHIELD);
        return true;
    }

    private boolean tickPlaceShield() {
        class_2338[] candidates;
        boolean falling;
        if (!this.hasDelayElapsed(this.shieldPlaceDelay.getValueInt())) {
            return false;
        }
        this.shieldPlaced = false;
        class_1792 shieldItem = this.shieldType.isMode(ShieldType.Glowstone) ? class_1802.field_8801 : class_1802.field_8281;
        int slot = this.findInHotbar(shieldItem);
        if (slot == -1) {
            this.onShieldDone();
            return true;
        }
        class_2338 playerFeet = this.mc.field_1724.method_24515();
        class_2338 playerHead = playerFeet.method_10084();
        class_243 anchorCenter = this.anchorPos.method_46558();
        class_243 playerCenter = playerFeet.method_46558();
        class_243 dir = playerCenter.method_1020(anchorCenter).method_1029();
        class_2338 primary = class_2338.method_49638((class_2374)anchorCenter.method_1019(dir.method_1021(1.5)));
        boolean smart = this.smartMode.getValue();
        boolean bl = falling = this.mc.field_1724.field_6017 > 0.0 || !this.mc.field_1724.method_24828();
        if (falling) {
            smart = false;
        }
        if (smart && this.anchorPos.method_10264() == playerFeet.method_10264() + 1) {
            this.onShieldDone();
            return true;
        }
        if (!(!smart || primary.equals((Object)playerFeet) || primary.equals((Object)playerHead) || primary.equals((Object)this.anchorPos) || this.mc.field_1687.method_8320(primary).method_45474())) {
            this.onShieldDone();
            return true;
        }
        boolean anchorBelow = this.anchorPos.method_10264() < playerFeet.method_10264();
        for (class_2338 candidate : candidates = this.buildCandidates(this.anchorPos, anchorCenter, playerCenter, primary, anchorBelow, smart)) {
            class_2338 below;
            if (candidate == null || candidate.equals((Object)this.anchorPos) || candidate.equals((Object)playerFeet) || candidate.equals((Object)playerHead) || smart && anchorBelow && candidate.equals((Object)this.anchorPos.method_10084()) || !this.mc.field_1687.method_8320(below = candidate.method_10074()).method_26234((class_1922)this.mc.field_1687, below) && !below.equals((Object)this.anchorPos)) continue;
            if (!this.mc.field_1687.method_8320(candidate).method_45474()) {
                this.onShieldDone();
                return true;
            }
            class_3965 hit = this.findPlaceableHit(candidate, this.anchorPos);
            if (hit == null) continue;
            class_243 lookTarget = this.randomFacePoint(candidate, hit.method_17780());
            Rotation raw = RotationUtils.getRotationToVec(RotationUtils.getEyesPos((class_1657)this.mc.field_1724), lookTarget);
            float yawDrift = (RNG.nextFloat() - 0.5f) * 1.2f;
            float pitchDrift = (RNG.nextFloat() - 0.5f) * 0.6f;
            this.targetRot = new Rotation(raw.yaw() + yawDrift, raw.pitch() + pitchDrift);
            this.sweepStart = new Rotation(this.mc.field_1724.method_36454(), this.mc.field_1724.method_36455());
            this.sweepTick = 0;
            this.rotateTicksSetting = 1;
            this.pendingHit = hit;
            this.pendingSlot = slot;
            this.startStep(State.ROTATE_SHIELD);
            return true;
        }
        this.onShieldDone();
        return true;
    }

    private boolean tickRotateShield() {
        if (this.mc.field_1724 == null || this.targetRot == null || this.sweepStart == null) {
            this.onShieldDone();
            return true;
        }
        int totalTicks = Math.max(1, this.rotateTicksSetting);
        ++this.sweepTick;
        if (totalTicks <= 1) {
            Sweto.INSTANCE.rotatorManager.setRotation(this.targetRot);
        } else {
            float t = (float)this.sweepTick / (float)totalTicks;
            float smoothT = t * t * (3.0f - 2.0f * t);
            float startYaw = this.sweepStart.yaw();
            float endYaw = this.targetRot.yaw();
            float yawDelta = _54f34nch0rV2.wrapYaw(endYaw - startYaw);
            float yaw = startYaw + yawDelta * smoothT;
            float pitch = this.sweepStart.pitch() + (this.targetRot.pitch() - this.sweepStart.pitch()) * smoothT;
            Sweto.INSTANCE.rotatorManager.setRotation(new Rotation(yaw, pitch));
        }
        if (this.sweepTick >= totalTicks) {
            if (this.pendingHit != null && this.pendingSlot >= 0) {
                int prevSlot = this.mc.field_1724.method_31548().method_67532();
                this.mc.field_1724.method_31548().method_61496(this.pendingSlot);
                this.placeBlockWithRotation(this.pendingHit, this.targetRot);
                this.mc.field_1724.method_31548().method_61496(prevSlot);
            }
            this.pendingHit = null;
            this.pendingSlot = -1;
            this.targetRot = null;
            this.sweepStart = null;
            this.shieldPlaced = true;
            this.onShieldDone();
            return true;
        }
        return false;
    }

    private void onShieldDone() {
        if (this.autoBlow.getValue()) {
            this.startStep(State.BLOW);
        } else {
            this.startStep(State.DONE);
        }
    }

    private boolean tickBlow() {
        if (!this.hasDelayElapsed(this.explodeDelay.getValueInt())) {
            return false;
        }
        if (!this.isAnchorCharged(this.anchorPos)) {
            this.reset();
            return true;
        }
        int slot = this.explodeSlot.getValueInt() - 1;
        if (slot >= 0 && slot < 9) {
            this.mc.field_1724.method_31548().method_61496(slot);
        }
        class_243 anchorCenter = this.anchorPos.method_46558().method_1031(0.0, 0.5, 0.0);
        Rotation raw = RotationUtils.getRotationToVec(RotationUtils.getEyesPos((class_1657)this.mc.field_1724), anchorCenter);
        class_3965 hit = new class_3965(this.anchorPos.method_46558().method_1031(0.0, 0.5, 0.0), class_2350.field_11036, this.anchorPos, false);
        this.placeBlockWithRotation(hit, raw);
        this.startStep(State.DONE);
        return true;
    }

    private boolean findAndSelect(class_1792 item) {
        int slot = this.findInHotbar(item);
        if (slot == -1) {
            return false;
        }
        if (this.mc.field_1724.method_31548().method_67532() != slot) {
            this.mc.field_1724.method_31548().method_61496(slot);
        }
        return true;
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

    private class_3965 buildPlaceHit(class_2338 pos) {
        for (class_2350 dir : class_2350.values()) {
            class_2338 neighbor = pos.method_10093(dir);
            if (!this.mc.field_1687.method_8320(neighbor).method_26234((class_1922)this.mc.field_1687, neighbor)) continue;
            class_243 hitVec = class_243.method_24953((class_2382)neighbor).method_1019(class_243.method_24954((class_2382)dir.method_62675()).method_1021(-0.5));
            return new class_3965(hitVec, dir.method_10153(), neighbor, false);
        }
        return null;
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

    private boolean isAnchorCharged(class_2338 pos) {
        if (pos == null) {
            return false;
        }
        if (!this.mc.field_1687.method_8320(pos).method_27852(class_2246.field_23152)) {
            return false;
        }
        try {
            return (Integer)this.mc.field_1687.method_8320(pos).method_11654((class_2769)class_4969.field_23153) > 0;
        }
        catch (Exception e) {
            return false;
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

    private static float wrapYaw(float delta) {
        if ((delta %= 360.0f) > 180.0f) {
            delta -= 360.0f;
        }
        if (delta < -180.0f) {
            delta += 360.0f;
        }
        return delta;
    }

    public static enum ShieldType {
        Glowstone,
        Obsidian;

    }

    private static enum State {
        IDLE,
        SWAP_ANCHOR,
        PLACE_ANCHOR,
        WAIT_ANCHOR,
        SWAP_GLOWSTONE,
        CHARGE,
        WAIT_CHARGE,
        SWAP_SHIELD,
        PLACE_SHIELD,
        ROTATE_SHIELD,
        BLOW,
        DONE;

    }
}

