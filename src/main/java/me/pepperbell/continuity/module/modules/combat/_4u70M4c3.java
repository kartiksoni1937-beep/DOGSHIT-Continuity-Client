/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.GameRenderListener;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.RotationUtils;
import me.pepperbell.continuity.utils.TimerUtils;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2868;
import net.minecraft.class_3532;

public final class _4u70M4c3
extends Module
implements TickListener,
GameRenderListener {
    private final SliderSetting fallDistance = (SliderSetting)new SliderSetting(EncryptedString.of("F--_`a-*$l^~.l.~ ^*~D\u00ad~|-i*.\u00b7s\u00ad---t_-a_^n.`\u00b7c~|~e"), 0.0, 20.0, 3.0, 0.5).setDescription(EncryptedString.of("M~`-.i`\u00adn_|~*i-*..m_|u|\u00b7|*m_^| |$~f|*`a~~|l^_$l.~-. |\u00add\u00ad~\u00b7i*_.s^*t$^a$-^\u00b7n^`^^c_^\u00ade$`$$ -.|^i$*\u00adn*\u00b7$ $`\u00b7b~$l\u00b7.**o`|\u00b7^c*\u00ad_\u00adk*|-s~\u00ad~- *^~$t`^*|o_|-` \u00ad^~_t_~^r|$$i\u00b7\u00b7`.g$_-|g^*\u00ad|e-\u00ad~~r*_` ~$a\u00ad_\u00b7t.`\u00b7`t`-\u00ad$a|_|c|~_k"));
    private final BooleanSetting targetPlayers = (BooleanSetting)new BooleanSetting(EncryptedString.of("T|\u00ad\u00ad.a\u00b7-`r|\u00b7_g$\u00b7e\u00ad*~\u00b7t*^` --*P$`l\u00ad\u00b7$-a^*|y^\u00ad_e\u00ad*|^r*$s"), true).setDescription(EncryptedString.of("A*$~-t|\u00ad\u00ad*t\u00ad`a~-$\u00b7c_`k^$- $|p\u00ad_^l^-`_a\u00b7\u00ad$y|*__e~-r`\u00ad.s"));
    private final BooleanSetting targetMobs = (BooleanSetting)new BooleanSetting(EncryptedString.of("T`$`a\u00ad^$^r$\u00b7*_g`--e`~*|t_- ~`M_\u00b7-\u00ado\u00ad\u00adb^\u00b7^s"), false).setDescription(EncryptedString.of("A$\u00ad.t**_~t`*|a\u00ad$c\u00ad^k*.$` `~|\u00b7m*\u00b7`o|`b`\u00b7|s"));
    private final BooleanSetting stunSlam = (BooleanSetting)new BooleanSetting(EncryptedString.of("S|\u00ad_\u00b7t_-u$^n~$ ~$|*S-`-l~||a~~.m"), true).setDescription(EncryptedString.of("A.$_|u`-^t*\u00ad~o^~m.\u00b7$a\u00b7-t__i*\u00b7c\u00b7|a.-l|*l`_y$\u00ad| ~\u00b7._d~`|i_-_s.^-\u00b7a|.`b_$||l~\u00b7\u00b7~e-*\u00ads.\u00ad\u00b7_ ..e\u00ad.n~*e..\u00b7*m_\u00ad$-y.`- `^s.|\u00b7h-$-i\u00b7|e^._~l_`d-` \u00ad.*w._\u00b7|i\u00b7$t~^\u00ad.h.- -..^a^^n$- \u00ad|\u00b7.a\u00b7\u00adx*|$e-~ \u00ad^$b\u00b7^_*e-`$f\u00b7.o~_^.r-$$\u00ade\u00ad`.. _\u00b7``m~\u00b7.*a_^`c--^.e~$|$ `*~_h|^~*i^|_`t^\u00ad__t$.i|~*\u00adn..$g"));
    private final BooleanSetting silentSwap = (BooleanSetting)new BooleanSetting(EncryptedString.of("S**_i\u00b7-_\u00b7l~*_\u00ade\u00ad|*_n\u00b7-~~t|\u00b7. *^|S_-w``\u00ad`a-|^p"), true).setDescription(EncryptedString.of("U\u00ad\u00ad`s-\u00ad.e-_*|s$**- `.p-*a``_.c-*k~-e.`$t\u00ad^l\u00b7\u00ad|e.~*v*~e`\u00adl-| |`s^|w\u00ad-a.^.p\u00ad\u00b7^. -|\u00ad*f^$$o~_-r*$- *$*a~`.|t\u00b7\u00ad**t^*.~a`$_\u00b7c..k_*|s"));
    private final BooleanSetting swapBack = (BooleanSetting)new BooleanSetting(EncryptedString.of("S^\u00ad-w\u00b7-.a\u00ad-$\u00b7p.| -\u00ad|B-|$.a_*c_\u00b7k"), true).setDescription(EncryptedString.of("S~^w_-^.a-^$^p$\u00b7|\u00ad ^^^b\u00b7\u00ad*\u00ada^~_$c_``k\u00b7- |||_t$_o\u00b7$` *..o`*_r-*i|.$$g-*$i`$n`$a^_l$~. `~--s__*.l.^*o-\u00ad\u00adt|~| \u00b7~_a*~f-\u00b7t_$e.$|~r*\u00ad ~``*h|`i*|t`\u00b7t\u00b7~.~i~|-n\u00ad--g"));
    private final MinMaxSetting swapBackDelay = (MinMaxSetting)((MinMaxSetting)new MinMaxSetting(EncryptedString.of("S-\u00b7w*~|a``~p_^ |``.B^.\u00ad-a\u00b7``c`\u00adk$^ \u00b7|`D^\u00ade_^^\u00adl^`_*a__-y"), 0.0, 20.0, 1.0, 1.0, 3.0).setDescription(EncryptedString.of("T-|^i^|-c-^k.\u00ad.|s*|~\u00ad *|`t^^o|- ~*w``^a``-i_^`$t-$\u00ad| |\u00b7\u00ad*b$\u00ade~^f$^^o`-r|**e`--^ $~s_*w^$a-~_*p`|_^p*\u00b7.i^-n-.g\u00ad._- -`_b`^a^^~c-\u00adk"))).visible(() -> this.swapBack.getValue());
    private final BooleanSetting breakCrystals = (BooleanSetting)new BooleanSetting(EncryptedString.of("B\u00ad^r*^e^`a^_~k$~~` .-^*C~*$~r|\u00ady\u00ad\u00b7s.*~\u00b7t\u00b7\u00ad\u00b7a~.\u00adl.*\u00b7s"), true).setDescription(EncryptedString.of("B~^r|\u00b7^|e$$$a`|k*_ ^-*\u00b7e~^*n_.d.-* |..c~_.r.|\u00ady**^s\u00b7_t*\u00b7\u00ada_-~.l\u00ad$_`s~*- \u00ad^|b~|^\u00adl\u00ad|^o^*c-*k~~i_.n.^^g|_`^ $~*^t^-h`\u00ade`.~\u00b7 ^^t`\u00ad\u00b7.a^*\u00adr`~*|g^$e-.-|t.\u00ad` *.b\u00b7\u00b7$e-.f\u00ad*-o-~r-|e\u00ad^ \u00ad`*a|^*^t\u00ad.t|~-$a\u00ad^$c|$`-k^\u00adi-_n|``g"));
    private final SliderSetting crystalBreakRange = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("C`~_^r^-y\u00b7-_$s-\u00adt**$a^__l^-* \u00ad$~|B__\u00adr.$`e|*.$a_|k`*_ ~-`$R_-^a\u00b7$.n`.~$g~_\u00b7\u00b7e"), 1.0, 8.0, 4.5, 0.5).setDescription(EncryptedString.of("M\u00b7$$_a_|-$x^*^ --d`_~i|-.s*|^t.\u00ad*a_~`|n$_c~`e^^* \u00ad$.t^|o..-- $^b\u00ad`r`_~e*.a-.^$k*_|^ -^a`.- _\u00b7~b$|.$l\u00ad\u00ad`^o$..c\u00b7\u00adk**|i~~_n\u00b7\u00b7\u00ad\u00b7g**$\u00ad ._^c_~*r.|y*|^_s^_t_-\u00ad^a|-^l"))).visible(() -> this.breakCrystals.getValue());
    private final SliderSetting crystalProximity = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("C^^r`*\u00b7_y.\u00b7~$s~*\u00b7t^*\u00ad\u00ada\u00b7.^-l--$\u00ad \u00b7|P.|~r`|\u00b7`o.`x`_\u00b7$i*_`*m*.i\u00ad^`t\u00b7`y"), 1.0, 6.0, 3.0, 0.5).setDescription(EncryptedString.of("H.\u00ad$o$\u00b7w~- .~_~c-*|l\u00b7*-o\u00ad*^s_-e~^\u00b7 ``a***_ .-~c\u00ad._\u00b7r\u00b7^\u00ady-$$s~^t\u00ad-a\u00ad~l^_` --m._\u00b7-u-*s^|t\u00ad\u00ad.^ _.$~b^\u00b7e*\u00b7$~ ..t|_\u00ado*^~ __t^\u00adh\u00b7`~_e~||$ $-.t.\u00b7a$-r`|_|g\u00b7.\u00ade_^^t|. *_$t~\u00ad\u00ado-~.- $|`c^.*^o.\u00b7u^-n$\u00b7t\u00ad*\u00b7 \u00ad-$a$$`-s`* \u00b7_b~|l|\u00b7o|_*\u00b7c.^^-k^_i-*^-n$|-$g"))).visible(() -> this.breakCrystals.getValue());
    private final SliderSetting R34ch = (SliderSetting)new SliderSetting(EncryptedString.of("R`^e.$|\u00ada-_*^c^.h"), 1.0, 8.0, 4.0, 0.5).setDescription(EncryptedString.of("M_`$a^$\u00ad`x.$^i*|-m\u00ad$\u00ad^u_*-$m*|.$ *.`~d_^i-\u00ad_s.$t^||\u00ada$*^\u00adn~\u00b7c-.-e$`|- -^`\u00adt..`\u00b7o`. ._t^-a`~_^r\u00ad_\u00ad*g\u00ad`\u00ade$|.*t~^` $~`w__*h.|\u00b7*e-\u00b7*~n-`^ *_~\u00ada~$t$*$$t^_a~_-c-`*\u00b7k|*i\u00ad~|n\u00ad`^\u00adg"));
    private final BooleanSetting autoAim = (BooleanSetting)new BooleanSetting(EncryptedString.of("A~_u_~*.t*|\u00ad\u00b7o-. _-.$A*\u00b7~$i~~m"), true).setDescription(EncryptedString.of("A|.i~_\u00b7m|.^ *^.*a^-t|\u00b7** `~|t`\u00b7a^^_^r|\u00b7g*~^.e\u00b7.t\u00b7^\u00b7 |\u00ad$.w\u00b7~-*h\u00b7$\u00b7i-`l_\u00b7$~e$-\u00ad. -|f**~a`._*l$.\u00b7l\u00ad$-i**|\u00b7n|~.\u00adg"));
    private final BooleanSetting strictAim = (BooleanSetting)((BooleanSetting)new BooleanSetting(EncryptedString.of("S_\u00adt$^r^^|$i\u00b7-`\u00adc^`_t$*\u00ad. \u00ad.A^.`i$-$m"), false).setDescription(EncryptedString.of("L_\u00ado||`c.`k\u00ad\u00ad^ \u00ad\u00ad*-a|*^_i`\u00ad*m~^* *-.~c||~-o\u00ad_\u00adm`~\u00adp~*$-l*$|~e\u00ad\u00b7^.t~**e-`~l|_y\u00b7\u00b7|\u00b7,--\u00ad ||^p-|r._^e~$v..$`e`^n~$-^t|*.s$\u00b7$^ ~~m*^~a|.~n\u00b7-^\u00b7u$\u00b7^.a_~l|~ $*$m_\u00ad--o$._v*|\u00b7_e`\u00ad*-m\u00ad$-|e_\u00ad$n$\u00ad\u00b7$t"))).visible(() -> this.autoAim.getValue());
    private final SliderSetting aimFov = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("A-~\u00b7`i\u00ad`^m.$ `--F|\u00adO$||V"), 10.0, 360.0, 90.0, 5.0).setDescription(EncryptedString.of("S\u00b7`~.e|\u00b7a~^r|\u00b7`_c_^~_h^~\u00b7 -`\u00adr\u00b7^a$\u00ad-\u00add_.-i$.^u_.^s\u00ad$| ~*$*i--\u00b7~n_*_` \u00ad~d$.`$e^`^g|\u00adr\u00ad|e|.e^__s"))).visible(() -> this.autoAim.getValue());
    private final SliderSetting aimRange = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("A\u00b7.\u00b7~i-\u00ad~$m|^| -$~R\u00ad^`a~_^|n\u00b7\u00b7g_|\u00b7*e"), 1.0, 10.0, 4.0, 0.5).setDescription(EncryptedString.of("M`^.|a~*|$x|-*i-\u00b7*_m$.\u00b7$u_.m\u00ad`- -\u00b7t`||a|*r_~g`_~_e`-t_$\u00adi|$n.^g__~ -$d.^^i.^*s^__.t$\u00b7a-$*`n^*`c_*~e"))).visible(() -> this.autoAim.getValue());
    private final MinMaxSetting aimYawSpeed = (MinMaxSetting)((MinMaxSetting)new MinMaxSetting(EncryptedString.of("Y\u00ad~a_$^w$\u00ad* _|`S|\u00ad*p\u00b7.\u00ade.^\u00b7$e^`-.d"), 0.1, 100.0, 0.5, 20.0, 40.0).setDescription(EncryptedString.of("M*__|i``~n\u00ad*/$_\u00adm_\u00ad$a*`_x\u00ad^$- -^y|**_a._w`. _*r._^$o_.t\u00b7-.*a^*t\u00b7\u00b7|$i~|\u00b7o\u00ad^$n*|\u00ad. `\u00b7*s^\u00b7`p^_$*e-`e-.d"))).visible(() -> this.autoAim.getValue() && !this.strictAim.getValue());
    private final MinMaxSetting aimPitchSpeed = (MinMaxSetting)((MinMaxSetting)new MinMaxSetting(EncryptedString.of("P--$^i|\u00b7`*t-_|c$\u00b7`*h_^.^ ~^-\u00adS*._p|.e-~e\u00ad`*d"), 0.1, 100.0, 0.5, 20.0, 40.0).setDescription(EncryptedString.of("M.^i$~|.n\u00ad\u00ad`/**^m^-\u00b7-a|.\u00b7x.` ~_|p._-$i|~t$-_^c^*..h^\u00ad$- ~$r-\u00b7o\u00b7^^t**-|a|`~\u00adt^-|$i$~o~|`_n|*^ \u00b7_s.|\u00b7p*-.\u00ade\u00b7_e_$*d"))).visible(() -> this.autoAim.getValue() && !this.strictAim.getValue());
    private final SliderSetting aimSmoothing = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("A^-i\u00ad*m^.| \u00b7\u00b7^$S*~~m|$|o~`\u00b7o^__t\u00ad\u00ad\u00adh\u00ad~$i\u00b7*^n`-`_g"), 1.0, 20.0, 1.5, 0.5).setDescription(EncryptedString.of("H|\u00ad\u00b7`i_~g$$.h`-\u00ade|.\u00ad\u00adr\u00b7^ -_=$| .$s_\u00ad$l*`o\u00ad.w_$_e*$r\u00ad.~/`.$s~$-m-\u00b7|\u00b7o$~-~o-\u00b7`_t^-*h*_`.e\u00ad$_r"))).visible(() -> this.autoAim.getValue() && !this.strictAim.getValue());
    private final SliderSetting aimHitThreshold = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("A.||i^^m~.|~ .^*\u00adH\u00ad^i\u00b7`t\u00ad\u00b7^ |`|`T\u00ad^\u00b7h-^~|r~`e_`^s$`h\u00b7$-o.-l^~\u00b7~d"), 1.0, 45.0, 15.0, 0.5).setDescription(EncryptedString.of("D\u00b7\u00ad\u00ad*e^$|g|-.^r--_-e~`e|\u00b7|-s.~^* -._o^\u00b7._f$_\u00ad `|a~*i$*\u00b7m-.$ -_$a\u00ad|$_c\u00b7-c`^u--~r$~a*\u00b7c__y$\u00ad* |_r`\u00ad^e*~*q^-u-\u00adi`^^-r-_e~.\u00b7$d*~ |^$b*_e_`~f$-$o*^.r~_\u00b7`e~`\u00b7 *`h*^~i\u00ad^.t~*.t`\u00adi$.\u00b7^n\u00ad\u00b7$g"))).visible(() -> this.autoAim.getValue());
    private Phase phase = Phase.IDLE;
    private int ticksWaited = 0;
    private int currentSwapBackDelay = 0;
    private int savedSlot = -1;
    private double fallStartY = -1.0;
    private boolean isFalling = false;
    private boolean maceHit = false;
    private class_1511 crystalTarget = null;
    private long crystalBreakTime = -1L;
    private long lastAimTime = 0L;
    private class_1297 currentTarget = null;
    private long targetLockTime = 0L;
    private int stunSlamStage = 0;
    private class_1297 stunSlamTarget = null;
    private final TimerUtils stunSlamTimer = new TimerUtils();

    public _4u70M4c3() {
        super(EncryptedString.of("A\u00ad__u$\u00ad$^t|$o-$| $$M.||a\u00b7*_\u00adc`|e"), EncryptedString.of("A$~`u-$_t`\u00ad~_o._m-.^~a`.t$\u00adi-*|c~\u00ada|..l^~.l\u00ad$$\u00ady|^.. .*\u00ada-`.t~\u00b7$t~\u00b7~~a\u00ad*~c*`k\u00ad\u00ads$$ \u00ad|w\u00ad.`.i`^|\u00b7t~``^h^$** ~~m^.\u00ad$a|*c-*-_e*\u00ad \u00b7$w~*h`-i-`l-*e.^.` ^-f.~.a\u00ad||l*~l.^^\u00b7i~~n*|`~g"), -1, Category.COMBAT);
        this.addSettings(this.fallDistance, this.targetPlayers, this.targetMobs, this.stunSlam, this.silentSwap, this.breakCrystals, this.crystalBreakRange, this.crystalProximity, this.swapBack, this.swapBackDelay, this.R34ch, this.autoAim, this.strictAim, this.aimFov, this.aimRange, this.aimYawSpeed, this.aimPitchSpeed, this.aimSmoothing, this.aimHitThreshold);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.eventManager.add(GameRenderListener.class, this);
        this.resetAll();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.eventManager.remove(GameRenderListener.class, this);
        if (this.savedSlot != -1 && this.mc.field_1724 != null) {
            InventoryUtils.setInvSlot(this.savedSlot);
        }
        this.resetAll();
        Sweto.INSTANCE.rotatorManager.reset();
        super.onDisable();
    }

    @Override
    public void onTick() {
        double fallen;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        this.updateFall();
        if (this.phase == Phase.AWAIT_SWAP_BACK) {
            ++this.ticksWaited;
            if (this.ticksWaited >= this.currentSwapBackDelay) {
                if (this.savedSlot != -1) {
                    InventoryUtils.setInvSlot(this.savedSlot);
                }
                this.resetState();
                this.maceHit = false;
            }
            return;
        }
        if (!this.isFalling || this.mc.field_1724.method_18798().field_1351 >= -0.1) {
            return;
        }
        double d = fallen = this.fallStartY == -1.0 ? 0.0 : Math.max(0.0, this.fallStartY - this.mc.field_1724.method_23318());
        if (fallen < this.fallDistance.getValue()) {
            return;
        }
        class_1297 target = this.getTarget();
        if (!this.isValidTarget(target)) {
            target = this.findTarget();
        }
        if (!this.isValidTarget(target) && this.phase == Phase.IDLE) {
            return;
        }
        if (this.phase == Phase.CRYSTAL_BREAK) {
            this.handleCrystalBreak(target);
            return;
        }
        if (this.phase == Phase.IDLE) {
            class_1511 crystal;
            if (this.breakCrystals.getValue() && (crystal = this.findBlockingCrystal(target)) != null) {
                this.crystalTarget = crystal;
                this.crystalBreakTime = System.currentTimeMillis();
                this.phase = Phase.CRYSTAL_BREAK;
                return;
            }
            Phase phase = this.phase = this.autoAim.getValue() ? Phase.AWAIT_AIM : Phase.AWAIT_MACE;
        }
        if (this.stunSlam.getValue() && target instanceof class_1657) {
            int axeSlot;
            boolean lookingAt;
            class_1657 player = (class_1657)target;
            float[] targetRot = this.calcRotation(target.method_5829().method_1005());
            double lookAngle = this.getFovDist(targetRot[0], targetRot[1]);
            boolean bl = lookingAt = lookAngle <= 45.0;
            if (this.stunSlamStage == 0 && player.method_6039() && !WorldUtils.isShieldFacingAway(player) && lookingAt && (axeSlot = this.findAxeSlot()) != -1) {
                this.attackEntity(target, axeSlot);
                this.stunSlamStage = 1;
                this.stunSlamTarget = target;
                this.stunSlamTimer.reset();
                return;
            }
            if (this.stunSlamStage == 1 && this.stunSlamTarget == target) {
                if (!lookingAt) {
                    this.stunSlamStage = 0;
                    this.stunSlamTarget = null;
                    return;
                }
                if (this.stunSlamTimer.hasReached(50.0)) {
                    this.stunSlamStage = 2;
                } else {
                    return;
                }
            }
            if (this.stunSlamStage == 2 && this.stunSlamTarget == target) {
                if (!lookingAt) {
                    this.stunSlamStage = 0;
                    this.stunSlamTarget = null;
                    return;
                }
                this.handleMaceAttack(target);
                this.stunSlamStage = 0;
                this.stunSlamTarget = null;
                return;
            }
        }
        if (this.phase == Phase.AWAIT_AIM) {
            float checkPitch;
            float checkYaw;
            class_1297 aimTarget = this.getTarget();
            if (!this.isValidTarget(aimTarget)) {
                aimTarget = this.findAimTarget();
            }
            if (!this.isValidTarget(aimTarget)) {
                return;
            }
            class_243 aimPos = this.getAimPos(aimTarget);
            float[] needed = this.calcRotation(aimPos);
            if (Sweto.INSTANCE.rotatorManager.isActive()) {
                checkYaw = Sweto.INSTANCE.rotatorManager.getServerYaw();
                checkPitch = Sweto.INSTANCE.rotatorManager.getServerPitch();
            } else {
                checkYaw = this.mc.field_1724.method_36454();
                checkPitch = this.mc.field_1724.method_36455();
            }
            double fovDist = this.getFovDist(needed[0], needed[1], checkYaw, checkPitch);
            if (fovDist <= this.aimHitThreshold.getValue()) {
                this.phase = Phase.AWAIT_MACE;
            } else {
                return;
            }
        }
        if (this.phase == Phase.AWAIT_MACE && !this.maceHit) {
            this.executeMaceHit(fallen);
        }
    }

    @Override
    public void onGameRender(GameRenderListener.GameRenderEvent event) {
        double fallen;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (!this.autoAim.getValue()) {
            return;
        }
        if (!this.isFalling) {
            return;
        }
        class_1799 chestStack = this.mc.field_1724.method_6118(class_1304.field_6174);
        if (chestStack.method_7909() == class_1802.field_8833) {
            return;
        }
        if (this.findAnyMaceSlot() == -1) {
            return;
        }
        if (this.maceHit) {
            Sweto.INSTANCE.rotatorManager.reset();
            this.currentTarget = null;
            this.targetLockTime = 0L;
            this.lastAimTime = 0L;
            return;
        }
        double d = fallen = this.fallStartY == -1.0 ? 0.0 : Math.max(0.0, this.fallStartY - this.mc.field_1724.method_23318());
        if (fallen < this.fallDistance.getValue()) {
            return;
        }
        if (this.mc.field_1724.method_18798().field_1351 >= -0.1) {
            return;
        }
        this.updateAiming();
    }

    private void updateFall() {
        boolean onGround = this.mc.field_1724.method_24828();
        double currentY = this.mc.field_1724.method_23318();
        if (onGround) {
            if (this.isFalling) {
                this.isFalling = false;
                this.fallStartY = -1.0;
                this.maceHit = false;
                if (this.savedSlot != -1 && this.swapBack.getValue()) {
                    InventoryUtils.setInvSlot(this.savedSlot);
                }
                this.resetState();
                this.lastAimTime = 0L;
                this.currentTarget = null;
                this.targetLockTime = 0L;
                Sweto.INSTANCE.rotatorManager.reset();
            }
            return;
        }
        if (!this.isFalling) {
            this.isFalling = true;
            this.fallStartY = currentY;
            this.maceHit = false;
            this.lastAimTime = 0L;
            this.currentTarget = null;
            this.targetLockTime = 0L;
            Sweto.INSTANCE.rotatorManager.reset();
        } else if (this.mc.field_1724.method_18798().field_1351 > 0.1 && this.fallStartY != -1.0 && currentY > this.fallStartY) {
            this.fallStartY = currentY;
            this.maceHit = false;
            this.lastAimTime = 0L;
            this.currentTarget = null;
            this.targetLockTime = 0L;
            Sweto.INSTANCE.rotatorManager.reset();
            if (this.phase != Phase.AWAIT_SWAP_BACK) {
                this.phase = Phase.IDLE;
            }
        }
    }

    private void handleCrystalBreak(class_1297 mainTarget) {
        if (this.crystalTarget == null || this.crystalTarget.method_31481()) {
            class_1511 next;
            if (this.breakCrystals.getValue() && (next = this.findBlockingCrystal(mainTarget)) != null) {
                this.crystalTarget = next;
                this.crystalBreakTime = System.currentTimeMillis();
                return;
            }
            this.crystalTarget = null;
            this.crystalBreakTime = -1L;
            this.phase = Phase.IDLE;
            return;
        }
        if ((double)this.mc.field_1724.method_5739((class_1297)this.crystalTarget) > this.crystalBreakRange.getValue()) {
            this.crystalTarget = null;
            this.crystalBreakTime = -1L;
            this.phase = Phase.IDLE;
            return;
        }
        class_243 crystalPos = this.crystalTarget.method_5829().method_1005();
        float[] crystalRot = this.calcRotation(crystalPos);
        this.mc.field_1724.method_36456(crystalRot[0]);
        this.mc.field_1724.method_36457(class_3532.method_15363((float)crystalRot[1], (float)-89.0f, (float)89.0f));
        if (this.mc.field_1761 != null) {
            this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, (class_1297)this.crystalTarget);
            this.mc.field_1724.method_6104(class_1268.field_5808);
        }
    }

    private void updateAiming() {
        long now = System.currentTimeMillis();
        if (this.currentTarget != null && !this.isValidAimTarget(this.currentTarget)) {
            this.currentTarget = null;
            this.targetLockTime = 0L;
            Sweto.INSTANCE.rotatorManager.reset();
        }
        if (this.currentTarget == null) {
            this.currentTarget = this.findAimTarget();
            if (this.currentTarget != null) {
                this.targetLockTime = now;
                Sweto.INSTANCE.rotatorManager.reset();
            } else {
                this.lastAimTime = 0L;
                Sweto.INSTANCE.rotatorManager.reset();
                return;
            }
        }
        if (this.strictAim.getValue()) {
            this.applyStrictAim(this.currentTarget);
        } else {
            this.applySmoothAim(this.currentTarget);
        }
    }

    private void applyStrictAim(class_1297 target) {
        class_243 targetPos = this.getAimPos(target);
        float[] rotation = this.calcRotation(targetPos);
        this.mc.field_1724.method_36456(rotation[0]);
        this.mc.field_1724.method_36457(class_3532.method_15363((float)rotation[1], (float)-89.0f, (float)89.0f));
    }

    private void applySmoothAim(class_1297 target) {
        class_243 targetPos = this.getAimPos(target);
        float[] rotation = this.calcRotation(targetPos);
        float targetYaw = rotation[0];
        float targetPitch = class_3532.method_15363((float)rotation[1], (float)-89.0f, (float)89.0f);
        float yawSpeed = (float)(this.aimYawSpeed.getMinValue() + Math.random() * (this.aimYawSpeed.getMaxValue() - this.aimYawSpeed.getMinValue()));
        float pitchSpeed = (float)(this.aimPitchSpeed.getMinValue() + Math.random() * (this.aimPitchSpeed.getMaxValue() - this.aimPitchSpeed.getMinValue()));
        float factor = this.aimSmoothing.getValueFloat();
        float currentYaw = this.mc.field_1724.method_36454();
        float currentPitch = this.mc.field_1724.method_36455();
        float newYaw = currentYaw + class_3532.method_15393((float)(targetYaw - currentYaw)) * (yawSpeed / factor / 100.0f);
        float newPitch = currentPitch + (targetPitch - currentPitch) * (pitchSpeed / factor / 100.0f);
        this.mc.field_1724.method_36456(newYaw);
        this.mc.field_1724.method_36457(class_3532.method_15363((float)newPitch, (float)-89.0f, (float)89.0f));
    }

    private class_1297 getTarget() {
        return this.mc.field_1692;
    }

    private class_1297 findTarget() {
        List<class_1297> targets = StreamSupport.stream(this.mc.field_1687.method_18112().spliterator(), false).filter(this::isValidTarget).filter(e -> (double)this.mc.field_1724.method_5739(e) <= this.R34ch.getValue()).sorted(Comparator.comparingDouble(e -> this.mc.field_1724.method_5739(e))).toList();
        return targets.isEmpty() ? null : targets.get(0);
    }

    private boolean isValidTarget(class_1297 entity) {
        if (entity == null || entity == this.mc.field_1724) {
            return false;
        }
        if (!(entity instanceof class_1309)) {
            return false;
        }
        class_1309 living = (class_1309)entity;
        if (!living.method_5805()) {
            return false;
        }
        if (entity instanceof class_1657) {
            return this.targetPlayers.getValue();
        }
        if (entity instanceof class_1308) {
            return this.targetMobs.getValue();
        }
        return false;
    }

    private boolean isValidAimTarget(class_1297 entity) {
        class_1657 player;
        class_1799 chest;
        if (entity == null || !entity.method_5805()) {
            return false;
        }
        if (entity == this.mc.field_1724) {
            return false;
        }
        if (!(entity instanceof class_1309)) {
            return false;
        }
        class_1309 living = (class_1309)entity;
        if (living.method_6032() <= 0.0f) {
            return false;
        }
        float maxReach = this.aimRange.getValueFloat();
        if (entity instanceof class_1657 && (chest = (player = (class_1657)entity).method_6118(class_1304.field_6174)).method_7909() == class_1802.field_8833 && !player.method_24828() && player.method_18798().method_1033() > 0.3) {
            maxReach += 2.0f;
        }
        if (entity.method_5739((class_1297)this.mc.field_1724) > maxReach) {
            return false;
        }
        if (entity instanceof class_1657) {
            return this.targetPlayers.getValue();
        }
        if (entity instanceof class_1308) {
            return this.targetMobs.getValue();
        }
        return false;
    }

    private class_1297 findAimTarget() {
        float halfFov = this.aimFov.getValueFloat() / 2.0f;
        boolean fullCircle = halfFov >= 180.0f;
        float maxRange = this.aimRange.getValueFloat();
        float camYaw = this.mc.field_1724.method_36454();
        float camPitch = this.mc.field_1724.method_36455();
        if (this.mc.field_1687 == null) {
            return null;
        }
        List<class_1309> candidates = StreamSupport.stream(this.mc.field_1687.method_18112().spliterator(), false).filter(e -> e instanceof class_1309 && e != this.mc.field_1724).map(e -> (class_1309)e).filter(e -> !e.method_29504() && e.method_6032() > 0.0f).filter(e -> {
            if (e instanceof class_1657) {
                return this.targetPlayers.getValue();
            }
            if (e instanceof class_1308) {
                return this.targetMobs.getValue();
            }
            return false;
        }).filter(e -> {
            class_1657 player;
            class_1799 chest;
            float range = maxRange;
            if (e instanceof class_1657 && (chest = (player = (class_1657)e).method_6118(class_1304.field_6174)).method_7909() == class_1802.field_8833 && !player.method_24828() && player.method_18798().method_1033() > 0.3) {
                range += 2.0f;
            }
            return e.method_5739((class_1297)this.mc.field_1724) <= range;
        }).filter(e -> {
            if (fullCircle) {
                return true;
            }
            class_243 aimPos = this.getAimPos((class_1297)e);
            float[] rot = this.calcRotation(aimPos);
            double fovDist = this.getFovDist(rot[0], rot[1], camYaw, camPitch);
            return fovDist <= (double)halfFov;
        }).toList();
        if (candidates.isEmpty()) {
            return null;
        }
        return candidates.stream().min(Comparator.comparingDouble(e -> {
            class_243 aimPos = this.getAimPos((class_1297)e);
            float[] rot = this.calcRotation(aimPos);
            double fovDist = this.getFovDist(rot[0], rot[1], camYaw, camPitch);
            return (double)e.method_5739((class_1297)this.mc.field_1724) * 3.0 + fovDist;
        })).orElse(null);
    }

    private class_243 getAimPos(class_1297 entity) {
        class_1657 player;
        class_1799 chest;
        if (entity instanceof class_1657 && (chest = (player = (class_1657)entity).method_6118(class_1304.field_6174)).method_7909() == class_1802.field_8833 && !player.method_24828() && player.method_18798().method_1033() > 0.3) {
            class_243 currentCenter = entity.method_5829().method_1005();
            class_243 V310c17y2 = entity.method_18798();
            double fallSpeed = Math.abs(this.mc.field_1724.method_18798().field_1351);
            double verticalDist = Math.abs(this.mc.field_1724.method_23318() - entity.method_23318());
            double timeToImpact = fallSpeed > 0.1 ? verticalDist / fallSpeed : 0.5;
            double targetHorizontalSpeed = Math.sqrt(V310c17y2.field_1352 * V310c17y2.field_1352 + V310c17y2.field_1350 * V310c17y2.field_1350);
            if (targetHorizontalSpeed > 0.5) {
                timeToImpact *= 1.15;
            }
            timeToImpact = class_3532.method_15350((double)timeToImpact, (double)0.05, (double)1.2);
            class_243 predicted = currentCenter.method_1019(V310c17y2.method_1021(timeToImpact));
            if (V310c17y2.field_1351 > 0.1) {
                predicted = predicted.method_1031(0.0, 0.3, 0.0);
            }
            return predicted;
        }
        return entity.method_5829().method_1005();
    }

    private void executeMaceHit(double fallen) {
        int maceSlot;
        int n = maceSlot = fallen >= 7.0 ? this.findMaceWithDensity() : this.findMaceWithBreach();
        if (maceSlot == -1) {
            maceSlot = this.findAnyMaceSlot();
        }
        if (maceSlot == -1) {
            return;
        }
        class_1297 t = this.getTarget();
        if (!this.isValidTarget(t)) {
            t = this.findAimTarget();
        }
        if (!this.isValidTarget(t)) {
            return;
        }
        if ((double)this.mc.field_1724.method_5739(t) > this.R34ch.getValue()) {
            return;
        }
        if (this.savedSlot == -1) {
            this.savedSlot = this.mc.field_1724.method_31548().method_67532();
        }
        InventoryUtils.setInvSlot(maceSlot);
        this.attackEntity(t);
        this.maceHit = true;
        if (this.swapBack.getValue()) {
            this.currentSwapBackDelay = this.randomTicks((float)this.swapBackDelay.getMinValue(), (float)this.swapBackDelay.getMaxValue());
            this.ticksWaited = 0;
            this.phase = Phase.AWAIT_SWAP_BACK;
        } else {
            this.resetState();
            this.maceHit = false;
        }
    }

    private void attackEntity(class_1297 target) {
        if (this.mc.field_1761 != null && this.mc.field_1724 != null) {
            this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, target);
            this.mc.field_1724.method_6104(class_1268.field_5808);
        }
    }

    private void attackEntity(class_1297 target, int slot) {
        int prev = this.mc.field_1724.method_31548().method_67532();
        boolean silent = this.silentSwap.getValue();
        if (slot != prev) {
            this.mc.method_1562().method_52787((class_2596)new class_2868(slot));
            if (!silent) {
                this.mc.field_1724.method_31548().method_61496(slot);
            }
        }
        this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, target);
        this.mc.field_1724.method_6104(class_1268.field_5808);
        if (silent && slot != prev) {
            this.mc.method_1562().method_52787((class_2596)new class_2868(prev));
        }
    }

    private void handleMaceAttack(class_1297 target) {
        int maceSlot;
        double fallDist = this.fallStartY == -1.0 ? 0.0 : Math.max(0.0, this.fallStartY - this.mc.field_1724.method_23318());
        int n = maceSlot = fallDist >= 7.0 ? this.findMaceWithDensity() : this.findMaceWithBreach();
        if (maceSlot == -1) {
            maceSlot = this.findAnyMaceSlot();
        }
        if (maceSlot == -1) {
            return;
        }
        if (this.savedSlot == -1) {
            this.savedSlot = this.mc.field_1724.method_31548().method_67532();
        }
        this.attackEntity(target, maceSlot);
        this.maceHit = true;
    }

    private int findAxeSlot() {
        for (int i = 0; i < 9; ++i) {
            String key = this.mc.field_1724.method_31548().method_5438(i).method_7909().method_7876().toLowerCase();
            if (!key.contains("axe")) continue;
            return i;
        }
        return -1;
    }

    private int findMaceWithDensity() {
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = this.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != class_1802.field_49814 || !this.hasEnchantment(stack, "density")) continue;
            return i;
        }
        return -1;
    }

    private int findMaceWithBreach() {
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = this.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != class_1802.field_49814 || !this.hasEnchantment(stack, "breach")) continue;
            return i;
        }
        return -1;
    }

    private int findAnyMaceSlot() {
        for (int i = 0; i < 9; ++i) {
            if (this.mc.field_1724.method_31548().method_5438(i).method_7909() != class_1802.field_49814) continue;
            return i;
        }
        return -1;
    }

    private boolean hasEnchantment(class_1799 stack, String name) {
        if (stack == null || stack.method_7960()) {
            return false;
        }
        return stack.method_58657().method_57534().stream().anyMatch(enchantment -> enchantment.method_55840().contains(name));
    }

    private class_1511 findBlockingCrystal(class_1297 target) {
        if (this.mc.field_1687 == null || target == null) {
            return null;
        }
        float maxCrystalRange = this.crystalBreakRange.getValueFloat();
        float proximity = this.crystalProximity.getValueFloat();
        class_1511 closest = null;
        double closestDist = Double.MAX_VALUE;
        for (class_1297 e : this.mc.field_1687.method_18112()) {
            double distToTarget;
            float distToPlayer;
            class_1511 crystal;
            if (!(e instanceof class_1511) || (crystal = (class_1511)e).method_31481() || (distToPlayer = this.mc.field_1724.method_5739((class_1297)crystal)) > maxCrystalRange || (distToTarget = Math.sqrt(crystal.method_5649(target.method_23317(), target.method_23318(), target.method_23321()))) > (double)proximity || !((double)distToPlayer < closestDist)) continue;
            closestDist = distToPlayer;
            closest = crystal;
        }
        return closest;
    }

    private float[] calcRotation(class_243 to) {
        class_243 from = RotationUtils.getEyesPos((class_1657)this.mc.field_1724);
        double dx = to.field_1352 - from.field_1352;
        double dy = to.field_1351 - from.field_1351;
        double dz = to.field_1350 - from.field_1350;
        double dist = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0));
        float pitch = (float)(-class_3532.method_15338((double)Math.toDegrees(Math.atan2(dy, dist))));
        return new float[]{yaw, class_3532.method_15363((float)pitch, (float)-89.0f, (float)89.0f)};
    }

    private double getFovDist(float targetYaw, float targetPitch) {
        return this.getFovDist(targetYaw, targetPitch, this.mc.field_1724.method_36454(), this.mc.field_1724.method_36455());
    }

    private double getFovDist(float targetYaw, float targetPitch, float camYaw, float camPitch) {
        double yawDiff = class_3532.method_15393((float)(targetYaw - camYaw));
        double pitchDiff = targetPitch - camPitch;
        return Math.sqrt(yawDiff * yawDiff + pitchDiff * pitchDiff);
    }

    private int randomTicks(float min, float max) {
        if (min >= max) {
            return (int)min;
        }
        return (int)((double)min + Math.random() * (double)(max - min + 1.0f));
    }

    private void resetState() {
        this.phase = Phase.IDLE;
        this.ticksWaited = 0;
        this.savedSlot = -1;
        this.currentSwapBackDelay = 0;
        this.crystalTarget = null;
        this.crystalBreakTime = -1L;
        this.stunSlamStage = 0;
        this.stunSlamTarget = null;
    }

    private void resetAll() {
        this.resetState();
        this.isFalling = false;
        this.fallStartY = -1.0;
        this.maceHit = false;
        this.lastAimTime = 0L;
        this.currentTarget = null;
        this.targetLockTime = 0L;
        this.stunSlamStage = 0;
        this.stunSlamTarget = null;
    }

    private static enum Phase {
        IDLE,
        CRYSTAL_BREAK,
        AWAIT_AIM,
        AWAIT_MACE,
        AWAIT_SWAP_BACK;

    }
}

