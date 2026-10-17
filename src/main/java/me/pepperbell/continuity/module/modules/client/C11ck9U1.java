/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.client;

import java.awt.Color;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.PacketReceiveListener;
import me.pepperbell.continuity.gui.ClickGui;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.ColorSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import net.minecraft.class_3944;
import net.minecraft.class_437;

public final class C11ck9U1
extends Module
implements PacketReceiveListener {
    public final KeybindSetting chordKey1 = new KeybindSetting(EncryptedString.of("C-_h$\u00ad`o|*.-r\u00b7|*d$` `|^\u00adK_\u00ad^~e-\u00b7y\u00b7` -*^1"), 92, false);
    public final KeybindSetting chordKey2 = new KeybindSetting(EncryptedString.of("C*$|h.\u00ad`~o^-*r\u00b7.`d~\u00ad $`_K-~\u00ade~|``y\u00ad~` |-^2"), 61, false);
    public static final SliderSetting alphaWindow = new SliderSetting(EncryptedString.of("W_\u00ad.-i\u00b7`n**d__\u00ado~-w*-_ ~|.|A~|\u00adl_\u00ad|\u00b7p^.`h\u00ad\u00ad_.a"), 0.0, 255.0, 235.0, 1.0);
    public static final BooleanSetting background = (BooleanSetting)new BooleanSetting(EncryptedString.of("B*_a**~c~$k_*g^\u00b7\u00b7r|-\u00b7o`|\u00b7_u~`.*n^\u00ad*\u00b7d"), true).setDescription(EncryptedString.of("R|`e$$.n|$^.d\u00b7.-|e_-\u00b7r^|s.-|| \u00b7~t~*\u00b7.h~-$e^.|\u00ad \u00ad$-b--`\u00ada$$.^c|\u00b7\u00b7k_\u00ad\u00b7g`|r-*`~o^`u\u00ad*n.*d"));
    public static final BooleanSetting customFont = new BooleanSetting(EncryptedString.of("C_\u00adu~\u00b7\u00b7$s|~t*$o~~m|\u00b7 $*F$^*$o^`n-`t"), true);
    private final BooleanSetting preventClose = (BooleanSetting)new BooleanSetting(EncryptedString.of("P\u00ad\u00b7|r^\u00ade-*\u00ad*v~``$e-$$$n\u00ad\u00b7t^`_ `|~C$_\u00ad|l|_\u00b7o||~\u00b7s$$\u00b7e"), true).setDescription(EncryptedString.of("F\u00ad\u00b7o$\u00ad\u00b7r_\u00b7.\u00ad ^~\u00b7.s^.-`e.`-~r_~*$v^_e`_*r._$$s$^ -\u00ad-\u00adw.`i|$\u00ad*t~-~$h$||- -_\u00adf*_-r\u00ad~e`__-e_$-z__^e-\u00ad \u00b7-*p--l$\u00b7.\u00b7u-$g.|i^`_^n\u00ad\u00b7s"));
    public static final SliderSetting roundQuads = new SliderSetting(EncryptedString.of("R_`\u00ado\u00ad_^u``\u00b7n^~|d\u00ad\u00b7\u00b7n\u00ad|e|.``s\u00ad\u00b7.s"), 1.0, 15.0, 8.0, 1.0);
    public static final BooleanSetting antiAliasing = (BooleanSetting)new BooleanSetting(EncryptedString.of("M$\u00b7-S|_|_A$^_A"), true).setDescription(EncryptedString.of("A-_n\u00b7$t.*i~`^- ~`^~A*~\u00b7l\u00b7^~\u00b7i*\u00ad$~a_|$\u00ads-\u00b7.*i\u00ad`^n\u00b7\u00b7`g~\u00b7~^ ~\u00b7.f|`\u00b7o$^_r.$ ^_`t~\u00b7r|-|_a~~c^.e$\u00b7r-^s"));
    public static final BooleanSetting glowEffect = (BooleanSetting)new BooleanSetting(EncryptedString.of("G|`l`_\u00b7^o*~w"), true).setDescription(EncryptedString.of("G.|.l$~*.o*``\u00b7w.|- -`\u00ade\u00ad$f*.f$*$`e^`.|c-|^\u00b7t-|- \u00ad*a\u00b7\u00ad\u00b7r~.*$o$-u~.n*|d\u00b7\u00b7~| `-$.p\u00ad\u00ada|.\u00b7n|~*^e*^~l"));
    public static final BooleanSetting textGlow = (BooleanSetting)new BooleanSetting(EncryptedString.of("T.$-`e.*-\u00adx~|~t$\u00b7~\u00ad -**.G.\u00b7~l-`|`o~\u00b7~w"), true).setDescription(EncryptedString.of("A\u00ad\u00ad_$d`*\u00b7d~-$s|` \u00b7`\u00ada_^ _-*s|_o`_f|^^t**~_ --\u00ad\u00adg_`l~|*o_-_w_~ \u00b7^-_b*`e.-h~$\u00ad*i^\u00b7*n.^d|$ `^|t~~-e|\u00ad_^x\u00b7_*t"));
    public static final SliderSetting glowIntensity = new SliderSetting(EncryptedString.of("G`_l-$-o-^-`w\u00ad.\u00b7 ~$$I~\u00ad\u00b7n|~.t\u00ad\u00ad`e.*_$n.$\u00b7$s~-~$i\u00b7$`_t$`|y"), 0.1, 5.0, 1.5, 0.1);
    public static final ModeSetting<ShaderMode> shaderMode = new ModeSetting<ShaderMode>(EncryptedString.of("S\u00ad|~_h*-_.a~|d~*|e._\u00b7r"), ShaderMode.KAWASE, ShaderMode.class);
    public static final SliderSetting blurStrength = new SliderSetting(EncryptedString.of("B$$^\u00b7l^\u00b7|$u*_r--`_ ^.$S-*|\u00adt_|-r\u00ad$``e$*~$n^.\u00ad-g~*~t.`h"), 1.0, 10.0, 3.0, 1.0);
    public static final ColorSetting gradientStart = new ColorSetting(EncryptedString.of("G~\u00ad-\u00adr`-a--^^d_--i-$e\u00ad.n~~t$~ $.$S`*t_|_a`.r.--.t"), new Color(130, 80, 255));
    public static final ColorSetting gradientEnd = new ColorSetting(EncryptedString.of("G$$|r*$*_a\u00ad\u00b7|.d^\u00ad*\u00b7i$|e$.`n*--.t$_$- $`E|$\u00b7-n|`.$d"), new Color(50, 255, 230));
    public static final SliderSetting hoverAlpha = new SliderSetting(EncryptedString.of("H*\u00b7o.$_v|`e~|r_^. \u00b7||.A^~^l``-$p\u00ad`h\u00ad-a"), 0.0, 255.0, 180.0, 1.0);
    public static final SliderSetting backgroundAlpha = new SliderSetting(EncryptedString.of("B-$-a|^-c$^k^$g||-^r~*_-o~*\u00ad_u~~`n\u00b7$_d_.$ _.*$A^$|~l^~.-p^^\u00ad$h-^\u00b7a"), 0.0, 255.0, 230.0, 1.0);

    public float getAnimatedAlpha() {
        float duration;
        long time = System.currentTimeMillis();
        float progress = (float)(time % (long)(duration = 1500.0f)) / duration;
        float wave = progress < 0.5f ? progress * 2.0f : 2.0f - progress * 2.0f;
        return 150.0f + wave * 105.0f;
    }

    public C11ck9U1() {
        super(EncryptedString.of("C.$.o-._n|.~t\u00ad^i.~n`\u00b7|-u__`i_`t_^_y"), EncryptedString.of("S.\u00b7e\u00ad.t_*t_~\u00adi_-$`n*\u00adg^\u00b7~^s`^~ _*f.`o.^r__$^ ^~\u00b7*t.`-h`\u00ad_-e^~ `$c_$l`\u00b7i||.^e\u00ad\u00adn`-t"), 0, Category.CLIENT);
        this.addSettings(this.chordKey1, this.chordKey2, shaderMode, blurStrength, gradientStart, gradientEnd, alphaWindow, background, this.preventClose, roundQuads, antiAliasing, glowEffect, textGlow, glowIntensity, hoverAlpha, backgroundAlpha);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(PacketReceiveListener.class, this);
        Sweto.INSTANCE.previousScreen = this.mc.field_1755;
        if (this.mc.field_1755 == null) {
            this.mc.method_1507((class_437)new ClickGui());
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(PacketReceiveListener.class, this);
        super.onDisable();
    }

    @Override
    public void onPacketReceive(PacketReceiveListener.PacketReceiveEvent event) {
        if (Sweto.INSTANCE.guiInitialized && event.packet instanceof class_3944 && this.preventClose.getValue()) {
            event.cancel();
        }
    }

    public static enum ShaderMode {
        NONE,
        KAWASE,
        LIQUID;

    }
}

