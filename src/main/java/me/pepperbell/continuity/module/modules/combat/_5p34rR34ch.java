/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.WorldUtils;

public final class _5p34rR34ch
extends Module {
    private final SliderSetting range = new SliderSetting(EncryptedString.of("R^^\u00b7.a$*|$n|`g\u00ad\u00ad_e"), 3.0, 6.0, 5.0, 0.1);

    public _5p34rR34ch() {
        super(EncryptedString.of("S||.p|*|e*.\u00b7_a^^*r$^ ..\u00b7R^`e`.a\u00b7-^c\u00ad~h"), EncryptedString.of("I^$^_n-.*c.|r|^|e.\u00b7*_a||\u00b7s^.*e^.s\u00b7-$$ ._^$y```o*$_`u`\u00ad.r_| $.r~$e$_``a`_c...h_\u00ad.^ ^_w\u00b7~$h`|~*e^|*n^~\u00b7\u00ad \u00b7$h|\u00b7$*o\u00b7--\u00adl\u00b7..|d..`_i\u00ad|.n-.$g.\u00b7\u00b7 *-**a\u00ad|$ ~_s~^\u00ad^p~.|.e_\u00b7.\u00ada-`-r"), -1, Category.COMBAT);
        this.addSettings(this.range);
    }

    public float getReach() {
        if (!this.isEnabled()) {
            return 3.0f;
        }
        if (this.mc.field_1724 != null && WorldUtils.isSpear(this.mc.field_1724.method_6047())) {
            return this.range.getValueFloat();
        }
        return 3.0f;
    }
}

