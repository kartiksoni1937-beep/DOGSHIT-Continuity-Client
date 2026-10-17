/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.render;

import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.StringSetting;
import me.pepperbell.continuity.utils.EncryptedString;

public final class N4m3Pr073c7
extends Module {
    private final StringSetting fakeName = (StringSetting)new StringSetting(EncryptedString.of("F_|-\u00ada_~k$~-e-\u00b7 *`N|_|a^``m$|e"), "You").setDescription(EncryptedString.of("T.__\u00adh\u00ad^$~e`^ .\u00adn$.a\u00ad-~^m_--e-*- |.t~\u00ad.h\u00ad*-a-`t^^ .__w*-^i-.\u00ad|l.--l_^.* ~\u00ad$r|$e\u00b7`-p-*\u00b7l\u00b7^a*_c~~e`|_ *\u00ad_|y.`*_o-`u|.r^~$ ^\u00adr\u00b7^\u00ade_`_a\u00ad-l-.* __~n|~a**\u00adm\u00ad$e"));

    public N4m3Pr073c7() {
        super(EncryptedString.of("N\u00ad*a~*m$_`^e\u00ad~ |*\u00adP`\u00b7\u00ad\u00b7r`^`o_.||t\u00b7*e~_^c$$\u00b7t"), EncryptedString.of("H-$i|^d~`-.e^$s.\u00b7$| `$.y*...o*-u-*r~|~~ ~~\u00ad*n_$\u00ad\u00ada\u00ad-$`m_\u00ad.e~~ *_$\u00adf-\u00adr\u00ad-~-o^$-|m*~$` ~~|*c\u00ad$h*.|*a`-t\u00b7-,`` \u00b7__H\u00b7`U*\u00ad|D~-* -$a$^n-$d\u00ad.\u00ad *\u00adt|.|^a\u00ad|`-g\u00ad^|s"), -1, Category.RENDER);
        this.addSettings(this.fakeName);
    }

    public String getFakeName() {
        return this.fakeName.getValue();
    }
}

