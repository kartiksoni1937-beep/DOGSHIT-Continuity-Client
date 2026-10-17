/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.render;

import java.awt.Color;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.ColorSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;

public final class _4m813nc3
extends Module {
    public final ColorSetting skyColor = (ColorSetting)new ColorSetting(EncryptedString.of("S-_`_k`\u00b7y.`_~ $`*C\u00ad-\u00b7_o_\u00b7`l$$-o.^r"), new Color(0, 0, 0, 255)).setDescription(EncryptedString.of("C-\u00b7.\u00adu^$s\u00b7\u00adt^-|o~~.`m\u00ad. _.c~$.o^_~l|\u00b7.`o^`\u00adr$^-\u00ad `|_.f^\u00ado_`~\u00b7r\u00ad\u00b7^ |-_-t\u00ad^^h$*||e$|$. |-s^^k\u00b7$_y"));
    public final ColorSetting fogColor = (ColorSetting)new ColorSetting(EncryptedString.of("F|||o$-g\u00ad. \u00b7\u00b7^C^\u00ado`*\u00b7l`-.o*$r"), new Color(0, 0, 0, 255)).setDescription(EncryptedString.of("C|~u\u00b7*\u00ads$^~|t~_-o\u00b7*~\u00adm-_.\u00b7 ~~\u00b7c\u00ad`o.`l`_*~o~^|`r$* ^*-\u00b7f``*$o__\u00b7-r\u00b7_.^ $$|t\u00ad`~$h..$e_\u00b7 ^|_`f|`~o~\u00b7^g"));
    public final SliderSetting fogDensity = (SliderSetting)new SliderSetting(EncryptedString.of("F|-^*o.\u00b7g\u00b7*~| `$^~D\u00ad_.*e\u00b7*^n_|s_\u00b7i**\u00b7t--y"), 0.0, 1.0, 0.5, 0.1f).setDescription(EncryptedString.of("C`\u00b7u^-s-|*$t*`o`~.~m~`~. ..d\u00ad.|e._n`~$s`|i_\u00adt\u00ad\u00b7y\u00ad* \u00ad\u00b7f_\u00b7o.~r$$ _-\u00b7~t\u00b7$~h.~_e\u00ad-* --.\u00adf\u00b7~*o~~g"));

    public _4m813nc3() {
        super(EncryptedString.of("A-~-m.-^b~~i\u00ad^e~~n|*`c_`\u00b7~e"), EncryptedString.of("C`$u*\u00b7\u00ad|s\u00ad-t^|\u00b7o-\u00b7m*-i\u00b7\u00ad.z`*$e.~s\u00ad-^. \u00b7-$`t\u00b7.~h|_e$- ~_w_$$`o\u00ad*\u00adr.*l.\u00b7-d`$^ __\u00b7a*$$_t.\u00b7\u00b7m-^\u00b7o|$s-\u00b7`p\u00ad*__h*^e_\u00b7r_*_`i.~c|$ ~`$\u00b7c\u00b7|\u00b7^o`^$l^`o^\u00ad`|r$*s.~ `-`^a$.n.``d|.`_ ^\u00ad$\u00b7f`_.$o*~|*g"), -1, Category.RENDER);
        this.addSettings(this.skyColor, this.fogColor, this.fogDensity);
    }
}

