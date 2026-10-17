/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.render;

import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.utils.EncryptedString;

public final class N00v3r14y
extends Module {
    public final BooleanSetting fire = (BooleanSetting)new BooleanSetting(EncryptedString.of("F$_i_*\u00b7.r-`e"), true).setDescription(EncryptedString.of("R`$e-*^^m$\u00ad`o*|\u00ad\u00adv*\u00ad|$e.~\u00b7*s\u00ad^* ^|.t\u00ad*$h$-$e|__^ *$f.$*_i$_||r-\u00b7e*| |`-o.$~v*$e\u00b7$_r_$l_\u00ad\u00ad*a*~y\u00ad\u00b7_| _|~\u00ado_$_n~| \u00b7\u00ad``y.\u00b7\u00b7o$^\u00b7*u-.._r`~$. $--s__c..r-|_\u00b7e-*`*e-*n"));
    public final BooleanSetting water = (BooleanSetting)new BooleanSetting(EncryptedString.of("W$^a~|t\u00b7.~e\u00b7\u00ad\u00b7r"), true).setDescription(EncryptedString.of("R\u00ad|^e*|\u00b7\u00b7m-*o\u00ad$$v\u00ad\u00ad\u00b7|e.$`s*.-~ \u00b7*t|\u00b7h-.^e|$$_ ^*~w*`$a$.t$\u00ad^~e-^r~|*/|^$u_-^n$~~~d~~$e*~.|r*-\u00ad.w--a_\u00adt||e_.\u00adr...` ...o~\u00b7_v.-\u00b7e._-r_\u00ad|-l`^-a\u00b7*`-y"));
    public final BooleanSetting blocks = (BooleanSetting)new BooleanSetting(EncryptedString.of("B_$~.l^^~$o_~c~~\u00b7k-|s"), true).setDescription(EncryptedString.of("R`^*e_|^m-\u00ad-o^~$v-_.e~^|s|`* |__$t*.$h\u00b7^^\u00ade`$_ *\u00ad`.o\u00ad\u00ad.v`~.`e$_r~_$l*||-a`^|`y|* `..w\u00b7~|h\u00ad.~-e^\u00b7\u00b7^n.. |_~y_|~~o`~u\u00b7_ `**a\u00ad~|\u00b7r_~$e$$ |_i_$|n-\u00b7s`\u00adi$*$-d-|-\u00b7e-_ ~$^a$`| ~\u00adb*-l$_|o`_c-*\u00b7_k"));

    public N00v3r14y() {
        super(EncryptedString.of("N_~_o.* ^`^O^-$\u00adv^\u00ade*`-r\u00b7`l.*||a||y"), EncryptedString.of("R-_\u00b7_e`*m|_o._|.v_^`~e$*s*_\u00ad ^~a`_*n-$._n-_o-`y~^\u00adi_\u00b7n^`\u00b7g.|~ `$s\u00ad^_c`|~r~_e~\u00b7`e*\u00b7n\u00ad$ \u00b7_o\u00b7$v_\u00b7^e$-*_r^`..l\u00ad\u00ada-`*y_\u00b7|s"), -1, Category.RENDER);
        this.addSettings(this.fire, this.water, this.blocks);
    }
}

