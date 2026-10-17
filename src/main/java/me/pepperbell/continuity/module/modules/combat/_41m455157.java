/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.HudListener;
import me.pepperbell.continuity.event.events.MouseMoveListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.MathUtils;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.RotationUtils;
import me.pepperbell.continuity.utils.TimerUtils;
import me.pepperbell.continuity.utils.WorldUtils;
import me.pepperbell.continuity.utils.rotation.Rotation;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3966;
import org.lwjgl.glfw.GLFW;

public final class _41m455157
extends Module
implements HudListener,
MouseMoveListener {
    private final BooleanSetting stickyAim = (BooleanSetting)new BooleanSetting(EncryptedString.of("S|__`t`\u00b7_\u00b7i\u00b7\u00adc|~k\u00b7`\u00b7.y\u00ad~ ^\u00ad\u00adA|_i|_~m"), false).setDescription(EncryptedString.of("A|\u00adi_^-m$\u00ad\u00ads\u00b7| *`~a.-`\u00adt$``\u00b7 -`\u00ad`t-*\u00ad~h\u00ad|$\u00ade|$`- `.*l\u00b7-a*_\u00ads.|$t\u00ad- \u00b7~a^^~~t\u00ad`t.-`\u00ada\u00ad_c|~\u00b7*k~_-e|*^d$-_ -~`.p`_l$^a|.--y|\u00ad|\u00b7e~`-r"));
    private final BooleanSetting onlyWeapon = new BooleanSetting(EncryptedString.of("O\u00b7$|\u00adn-`l^*__y\u00b7\u00ad`~ \u00b7.W\u00b7_e`$\u00b7a\u00ad~p~`\u00ado_$n"), true);
    private final BooleanSetting onLeftClick = (BooleanSetting)new BooleanSetting(EncryptedString.of("O*||n\u00b7| ``L-*$_e^--f|.-t.^~_ ~.$.C|-$-l|_~i-.~-c|$k"), false).setDescription(EncryptedString.of("O.-^\u00adn-\u00b7`l$\u00b7y$-`$ `|\u00b7*g^`$e.~*.t.-\u00b7s-~\u00b7 *\u00b7~\u00adt._-r_$$i`-$`g\u00ad^g~.e~\u00ad$r-\u00b7.e|*\u00b7`d|-*_ ^$\u00b7i\u00b7$f~^\u00b7 -`\u00b7h-|o~.\u00ad~l*\u00ad-_d^*_i-`n~^``g_* *_\u00b7d._`o~^w$\u00ad$n.~_ ~\u00b7l.\u00ad.\u00ade`\u00ad..f^*^*t\u00b7_ ^|\u00b7c_-\u00b7\u00b7l^^i$$c^_-_k"));
    private final ModeSetting<AimMode> aimAt = new ModeSetting<AimMode>(EncryptedString.of("A~__i\u00ad.\u00b7m_^ $-~*A^-t"), AimMode.Head, AimMode.class);
    private final BooleanSetting yawAssist = new BooleanSetting(EncryptedString.of("H_\u00b7o*$\u00b7r\u00ad$--i~.z\u00ad_o\u00b7*.|n~*\u00b7^t|^*a\u00b7\u00ad`l"), true);
    private final BooleanSetting pitchAssist = new BooleanSetting(EncryptedString.of("V$\u00b7`e-|r\u00b7~\u00b7t_*\u00ad*i`|c$`\u00b7`a\u00b7-l"), true);
    private final BooleanSetting stopAtTargetVertical = (BooleanSetting)((BooleanSetting)new BooleanSetting(EncryptedString.of("S|.t`*o~^\u00adp*^^ -\u00b7|.a.\u00b7t.^^| -.\u00b7T**a\u00b7$-r-_*~g~~e|\u00b7t.-*\u00ad ~^V*\u00ad~e.|*r_-*\u00adt"), true).setDescription(EncryptedString.of("S|$$t.\u00ado^|p-\u00b7`-s*`~ $-v*\u00ad|~e`-\u00b7`r$^t|^-i~\u00b7c\u00ad*_a**\u00ad|l-\u00b7l-\u00ad\u00b7`y$\u00b7.- \u00b7$^^a_$|^s~.s^**^i_-s\u00b7~^t|`i-|**n^\u00ad.-g`|` _.-i~\u00b7~\u00b7f|~ $^a*~^\u00adl.-\u00b7r~`-e_|~a~*^d-^y-\u00ad \u00ad.a`$-i|.\u00b7m\u00b7~~i*^-n^~.$g\u00b7~* -$~a.-|\u00b7t_|-` |~|t\u00ad*h-$^e\u00b7\u00ad $.-e|*`|n_~_$t\u00ad\u00b7~i\u00ad__$t^$y*^^,_$ -|`h$-_e.~-~l|^-p`|\u00ads*^ \u00b7^*|b^\u00ad`|y-\u00b7\u00ad*p|`~^a||\u00ads*_s_. `\u00ada\u00ad`^$n\u00ad_^t_|i..^`c`$\u00b7h~`|\u00ade*\u00b7*a\u00ad$t"))).visible(() -> this.pitchAssist.getValue());
    private final BooleanSetting stopAtTargetHorizontal = (BooleanSetting)((BooleanSetting)new BooleanSetting(EncryptedString.of("S-_.t\u00ad-o`|\u00ad-p._ |~\u00ad.a$`\u00ad$t~-~ _`\u00ad_T~-||a`\u00b7r$|.g~$e$*\u00ad`t~$ -\u00b7\u00b7H_$*^o`-r.^*\u00adi|-\u00b7z"), false).setDescription(EncryptedString.of("S$\u00adt\u00ad^^|o\u00b7^p$`_s^\u00ad.| \u00b7^\u00b7h*^\u00b7\u00ado$.\u00b7r\u00b7\u00ad_i.^z_|`o_._$n`|_t~|a_*l_|l~|\u00ad$y_.$ -_`a*_s$*$*s_^~i|`s**__t`~i.\u00b7n._^g_`\u00ad \u00b7^*~i`\u00b7^f._ |`.a.^_l\u00ad_-r..e$$a*~d*$y~~ \u00b7~|~a\u00b7~|_i``\u00ad.m`--i$\u00b7`n\u00b7.g$$_. $*a*\u00b7_.t~* |$-.t_\u00b7|h~_^\u00b7e`-_- *~\u00ad^e\u00b7`\u00b7n-\u00adt.\u00ad_i`\u00b7^|t_-_y*$~,*_*\u00b7 \u00b7\u00ad\u00adh^\u00ade|\u00adl_~p\u00b7\u00b7\u00ads\u00b7.-- .-b$*y\u00b7__p\u00ad~\u00ada|$s^~-.s\u00b7* |-a^\u00ad|n~-`*t.\u00b7i~-|c.$^h^\u00b7e.`~.a____t"))).visible(() -> this.yawAssist.getValue());
    private final SliderSetting radius = new SliderSetting(EncryptedString.of("R.\u00b7^a`$d-`~i-*_|u_\u00b7\u00ad\u00b7s"), 0.1, 6.0, 5.0, 0.1);
    private final BooleanSetting seeOnly = new BooleanSetting(EncryptedString.of("S-_|`e`$^e\u00ad`- ^$-\u00b7O-|n^^l_^y"), true);
    private final BooleanSetting lookAtNearest = new BooleanSetting(EncryptedString.of("L~~*o~-\u00ad\u00b7o`.k|*_ *\u00ad`a$|t$|$- ^|N|_e$^~.a\u00b7.\u00ad~r\u00ad-|e.\u00b7s|\u00b7\u00adt"), false);
    private final SliderSetting fov = new SliderSetting(EncryptedString.of("F\u00ad$\u00adO\u00b7~V"), 5.0, 360.0, 180.0, 1.0);
    private final MinMaxSetting pitchSpeed = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("V..e_~-r\u00ad\u00adt`||-i\u00ad_\u00ad.c$$$\u00ada$*-\u00adl~.. \u00b7-S~\u00b7$|p\u00b7~e*.\u00b7e^-d"), 0.0, 10.0, 0.1, 2.0, 4.0).visible(() -> this.pitchAssist.getValue());
    private final MinMaxSetting yawSpeed = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("H*-.`o`^**r*_i`~|z~\u00ado$.\u00ad\u00b7n$_^t\u00b7_\u00ada|_$l`_ \u00ad|S`\u00adp`|_.e`.$_e^\u00b7^d"), 0.0, 10.0, 0.1, 2.0, 4.0).visible(() -> this.yawAssist.getValue());
    private final SliderSetting speedChange = (SliderSetting)((SliderSetting)new SliderSetting(EncryptedString.of("S\u00b7\u00adp|*e_$~^e*`d|* $*D_\u00ade||\u00b7_l.\u00b7^~a\u00ad`$y"), 0.0, 1000.0, 250.0, 1.0).setDescription(EncryptedString.of("T*._i||m-`e*$ ~|`-i~|`n_|*_ `^m$$.`i.^*l^$l.\u00ad^~i`~`-s^*~e_~\u00b7$c.^`\u00ado*$n~$d`\u00b7|.s~- .\u00ad\u00b7t..^o$\u00b7 _\u00ad$$w_|\u00ada.\u00b7`i|-t.^\u00ad $_$~a\u00b7.$f-$-*t-\u00b7e^|\u00b7r~`|_ $*-\u00b7r$\u00ad_e^\u00ad-~s.\u00ad-\u00ade`|\u00ad.t_|`.t|*$i*_n^-g|` *$_\u00b7r\u00ad\u00ada~$n*.\u00b7~d$\u00b7o-~$m|* \u00ad.s*\u00ad|^p~__|e`$-e\u00ad.$d"))).visible(() -> this.yawAssist.getValue() || this.pitchAssist.getValue());
    private final SliderSetting randomization = (SliderSetting)new SliderSetting(EncryptedString.of("C|`-h^_~`a\u00ad~n`-c\u00ad\u00b7.`e"), 0.0, 100.0, 50.0, 1.0).visible(() -> this.yawAssist.getValue() || this.pitchAssist.getValue());
    private final SliderSetting waitFor = (SliderSetting)new SliderSetting(EncryptedString.of("W^`\u00ada`*~i\u00b7_$\u00b7t-``~ \u00b7`o$_$*n\u00ad~ *$`M-`_-o\u00ad-v$$\u00b7e"), 0.0, 1000.0, 0.0, 1.0).setDescription(EncryptedString.of("A-^f\u00ad`t\u00ad-.|e-~|-r~| $\u00b7y-$o^-$u-$` $\u00ad*\u00adm^\u00b7o.`\u00b7v*_e`^*~ ~\u00ad^y`.o|$$u.\u00b7\u00b7r$* ^.m\u00b7*o^*u_^s*$e_\u00ad \u00b7\u00b7\u00b7*a|.\u00b7i^-\u00ad\u00b7m.`- *_a|_*s_|s`$`.i-\u00ad\u00ad|s^$_$t~_ ~\u00ad^w-^\u00ad_i|~-l^^l$$ |*\u00b7s_~-t|`o-~|p~\u00ad |.w.^o^~r\u00ad.k`~$~i\u00b7~|~n~.^g-~ -$|\u00adf_~o~$$r|^ \u00b7$.t`\u00b7h^.e^\u00b7 |-.\u00b7s_-.\u00ade$|\u00b7-l_.e`-*c\u00b7.\u00adt^^e^|~.d$.|| |-\u00b7~a$|m|~.\u00ado_.u.`n*|$t*`\u00b7^ \u00b7_o`.f|~$ ~`\u00adt\u00b7-$.i*..m-\u00ad\u00ade"));
    private final ModeSetting<LerpMode> lerp = (ModeSetting)((ModeSetting)new ModeSetting<LerpMode>(EncryptedString.of("L*-|e$_~r*`p"), LerpMode.Normal, LerpMode.class).setDescription(EncryptedString.of("L.~i\u00ad-`.n\u00b7..e|$|.a\u00ad~~$r`..* -.|*i||\u00adn|\u00b7t~$^e|`|\u00b7r\u00b7\u00b7-*p-*~o_`_l\u00b7*-~a**_t-_`i\u00b7$~o`~n_._- __t\u00ad~o`` `*u`\u00b7\u00b7s\u00b7`\u00b7\u00ade\u00ad\u00b7 `._t\u00b7~$\u00ado|.^$ _~.\u00b7r.\u00ado~\u00b7~t_.a.|t$_e"))).visible(() -> this.yawAssist.getValue() || this.pitchAssist.getValue());
    private final ModeSetting<PosMode> posMode = (ModeSetting)new ModeSetting<PosMode>(EncryptedString.of("P__$o$`.s$` `|\u00b7m.|\u00ad\u00ado|^d\u00ad\u00ad\u00b7*e"), PosMode.Normal, PosMode.class).setDescription(EncryptedString.of("P|\u00ad_r\u00b7..\u00ade|.^\u00adc.|i_.s*^_\u00adi.^__o$\u00ad~n\u00ad^~$ *.|_o$^_f^*_* ^\u00b7t\u00b7\u00b7h_`e~-^ .|t\u00b7*a~\u00b7~r_-`^g*\u00b7e*^..t*|^ *.^p*~o\u00ad*.s^`.i^^t$\u00ad^*i_|o.*n"));
    private final TimerUtils timer = new TimerUtils();
    private final TimerUtils resetSpeed = new TimerUtils();
    private boolean move;
    private float pitch;
    private float yaw;

    public _41m455157() {
        super(EncryptedString.of("A_$i~.\u00b7m~^ \u00b7_A^*$s|^s*.\u00b7i.$~$s|`t"), EncryptedString.of("A\u00b7_`u\u00b7`*\u00adt~`\u00b7o*^m~`*a^_|t*-i_.c.$a.|l-.*|l^\u00ad~y~| $`a^__i-.$m\u00b7_^s_.$_ \u00ad\u00b7a.~t~__ ^|p\u00ad~l\u00ad.*-a\u00ad.y-*.e-\u00b7r^\u00b7`^s~~*- -^\u00b7^f~\u00ad.o*`-.r_~ `|\u00b7y\u00ad\u00ad\u00ad|o$_u"), -1, Category.COMBAT);
        this.addSettings(this.stickyAim, this.onlyWeapon, this.onLeftClick, this.aimAt, this.radius, this.fov, this.seeOnly, this.lookAtNearest, this.yawAssist, this.yawSpeed, this.stopAtTargetHorizontal, this.pitchAssist, this.pitchSpeed, this.stopAtTargetVertical, this.speedChange, this.randomization, this.waitFor, this.lerp, this.posMode);
    }

    @Override
    public void onEnable() {
        this.move = true;
        this.pitch = this.pitchSpeed.getRandomValueFloat();
        this.yaw = this.yawSpeed.getRandomValueFloat();
        this.eventManager.add(HudListener.class, this);
        this.eventManager.add(MouseMoveListener.class, this);
        this.timer.reset();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(HudListener.class, this);
        this.eventManager.remove(MouseMoveListener.class, this);
        super.onDisable();
    }

    @Override
    public void onRenderHud(HudListener.HudEvent event) {
        Rotation rotation;
        double angleToRotation;
        class_243 targetPos;
        class_1657 player;
        class_1309 class_13092;
        if (this.timer.delay(this.waitFor.getValueFloat()) && !this.move) {
            this.move = true;
            this.timer.reset();
        }
        if (this.mc.field_1724 == null || this.mc.field_1755 != null) {
            return;
        }
        if (this.onlyWeapon.getValue() && !WorldUtils.isWeapon(this.mc.field_1724.method_6047())) {
            return;
        }
        if (this.onLeftClick.getValue() && GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)0) != 1) {
            return;
        }
        class_1657 target = WorldUtils.findNearestPlayer((class_1657)this.mc.field_1724, this.radius.getValueFloat(), this.seeOnly.getValue(), true);
        if (this.stickyAim.getValue() && (class_13092 = this.mc.field_1724.method_6052()) instanceof class_1657 && (double)(player = (class_1657)class_13092).method_5739((class_1297)this.mc.field_1724) < this.radius.getValue()) {
            target = player;
        }
        if (target == null) {
            return;
        }
        if (this.resetSpeed.delay(this.speedChange.getValueFloat())) {
            this.pitch = this.pitchSpeed.getRandomValueFloat();
            this.yaw = this.yawSpeed.getRandomValueFloat();
            this.resetSpeed.reset();
        }
        class_243 class_2432 = targetPos = this.posMode.isMode(PosMode.Normal) ? target.method_73189() : target.method_30950(RenderUtils.tickProgress());
        if (this.aimAt.isMode(AimMode.Chest)) {
            targetPos = targetPos.method_1031(0.0, -0.5, 0.0);
        } else if (this.aimAt.isMode(AimMode.Legs)) {
            targetPos = targetPos.method_1031(0.0, -1.2, 0.0);
        }
        if (this.lookAtNearest.getValue()) {
            double offsetX = this.mc.field_1724.method_23317() - target.method_23317() > 0.0 ? 0.29 : -0.29;
            double offsetZ = this.mc.field_1724.method_23321() - target.method_23321() > 0.0 ? 0.29 : -0.29;
            targetPos = targetPos.method_1031(offsetX, 0.0, offsetZ);
        }
        if ((angleToRotation = RotationUtils.getAngleToRotation(rotation = RotationUtils.getDirection((class_1297)this.mc.field_1724, targetPos))) > (double)this.fov.getValueInt() / 2.0) {
            return;
        }
        float yawStrength = this.yaw / 50.0f;
        float pitchStrength = this.pitch / 50.0f;
        float yaw = this.mc.field_1724.method_36454();
        float pitch = this.mc.field_1724.method_36455();
        if (this.lerp.isMode(LerpMode.Smoothstep)) {
            yaw = (float)this.smoothStepLerp(yawStrength, this.mc.field_1724.method_36454(), rotation.yaw());
            pitch = (float)this.smoothStepLerp(pitchStrength, this.mc.field_1724.method_36455(), rotation.pitch());
        }
        if (this.lerp.isMode(LerpMode.Normal)) {
            yaw = this.lerp(yawStrength, this.mc.field_1724.method_36454(), rotation.yaw());
            pitch = this.lerp(pitchStrength, this.mc.field_1724.method_36455(), rotation.pitch());
        }
        if (this.lerp.isMode(LerpMode.EaseOut)) {
            yaw = (float)_41m455157.easeOutBackDegrees(this.mc.field_1724.method_36454(), rotation.yaw(), yawStrength * RenderUtils.frameDelta());
            pitch = (float)_41m455157.easeOutBackDegrees(this.mc.field_1724.method_36455(), rotation.pitch(), pitchStrength * RenderUtils.frameDelta());
        }
        if (MathUtils.randomInt(1, 100) <= this.randomization.getValueInt() && this.move) {
            class_3966 hitResult;
            class_239 class_2392;
            if (this.yawAssist.getValue()) {
                if (this.stopAtTargetHorizontal.getValue() && (class_2392 = WorldUtils.getHitResult(this.radius.getValue())) instanceof class_3966 && (hitResult = (class_3966)class_2392).method_17782() == target) {
                    return;
                }
                this.mc.field_1724.method_36456(yaw);
            }
            if (this.pitchAssist.getValue()) {
                if (this.stopAtTargetVertical.getValue() && (class_2392 = WorldUtils.getHitResult(this.radius.getValue())) instanceof class_3966 && (hitResult = (class_3966)class_2392).method_17782() == target) {
                    return;
                }
                this.mc.field_1724.method_36457(pitch);
            }
        }
    }

    public float lerp(float delta, float start, float end) {
        return start + class_3532.method_15393((float)(end - start)) * delta;
    }

    public static double easeOutBackDegrees(double start, double end, float speed) {
        double c1 = 1.70158;
        double c3 = 2.70158;
        double x = 1.0 - Math.pow(1.0 - (double)speed, 3.0);
        return start + class_3532.method_15338((double)(end - start)) * (1.0 + c3 * Math.pow(x - 1.0, 3.0) + c1 * Math.pow(x - 1.0, 2.0));
    }

    public double smoothStepLerp(double delta, double start, double end) {
        delta = Math.max(0.0, Math.min(1.0, delta));
        double t = delta * delta * (3.0 - 2.0 * delta);
        double value = start + class_3532.method_15338((double)(end - start)) * t;
        return value;
    }

    @Override
    public void onMouseMove(MouseMoveListener.MouseMoveEvent event) {
        this.move = false;
        this.timer.reset();
    }

    public static enum AimMode {
        Head,
        Chest,
        Legs;

    }

    public static enum LerpMode {
        Normal,
        Smoothstep,
        EaseOut;

    }

    public static enum PosMode {
        Normal,
        Lerped;

    }
}

