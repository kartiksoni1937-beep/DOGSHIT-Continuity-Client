/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.player;

import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.ItemSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;

public final class F457P14c3
extends Module {
    private static F457P14c3 instance;
    public final SliderSetting delayTicks = (SliderSetting)new SliderSetting(EncryptedString.of("D~~\u00ad.e|^`l*^~a*^`\u00b7y"), 0.0, 4.0, 0.0, 1.0).setDescription(EncryptedString.of("C`\u00b7o~~\u00ad.o-~$l|$$d*.*`o\u00ad~\u00ad\u00b7w_-`$n^\u00b7\u00b7 $`~t*.i\u00b7$-c_~$*k_^-s|.\u00b7\u00b7 |$-$b~\u00b7_$e-_t.\u00b7`w$_\u00b7e~*`\u00b7e\u00b7|\u00ad^n\u00b7^| |_p^|^`l\u00ad\u00b7~\u00ada`_\u00b7^c$\u00ad$e`^\u00ad$m\u00b7\u00ad.e~*-^n\u00ad^t.``-s"));
    private final BooleanSetting onlySelected = (BooleanSetting)new BooleanSetting(EncryptedString.of("O_`n-*l-|y~\u00b7*. ||`S`|e_|\u00b7-l$\u00ad_e-*c\u00ad^t..e$.d"), false).setDescription(EncryptedString.of("O*$n~\u00b7-l~\u00b7y~^ $_-`f\u00ad**\u00b7a`\u00b7`~s^__t^*.p~\u00b7l*|.$a__c_.e`$$^ ^*^`s-`e^$l*~e.*`c_$$_t-|``e$^`^d$*.$ _.~b_\u00ad$\u00adl*`|o~`c.|~k\u00ad~$-s"));
    private final ItemSetting selectedBlocks = new ItemSetting(EncryptedString.of("S*.e\u00ad.\u00b7l$$~-e\u00b7~|\u00b7c`\u00ad~*t\u00ad\u00ad-e$^d\u00ad^-\u00ad ^^.|B*`_*l|-^o.*c^|$-k.-s"), EncryptedString.of("B~\u00adl_\u00b7o-_c|\u00b7`$k|*s\u00b7. ||$t|*.o*| ~^.f\u00ad|a*\u00b7-*s\u00b7\u00b7\u00ad\u00adt^-`p\u00b7\u00b7^$l\u00ad`a|~c^*\u00b7e")).visible(this.onlySelected::getValue);

    public F457P14c3() {
        super(EncryptedString.of("F_-a.\u00b7s.^-t.`P~`|l**^a~.c$\u00b7e"), EncryptedString.of("P\u00ad._-l-`$a^^`c*--e^-\u00b7_s.$ **|b||\u00adl-`o.|c*|k$`s$_~ _`\u00b7*f~~^-a^*~s~\u00b7\u00b7t** _*b_~y|\u00b7~$ `$**b-_.y|~-.p``\u00b7$a`\u00ad\u00b7s~.^-s$.\u00b7_i$.$n-_g^~*\u00ad ~_i`_t^`_~e^$m*\u00b7\u00ad $.`u-\u00b7_\u00ads\u00ad\u00ade^|~_ .\u00ad.c\u00ad$o|*o^\u00b7*\u00b7l\u00b7..-d~|*o-`_w`\u00b7n"), -1, Category.MISC);
        instance = this;
        this.addSettings(this.delayTicks, this.onlySelected, this.selectedBlocks);
    }

    public boolean e() {
        if (this.onlySelected.getValue()) {
            return this.selectedBlocks.isSelected(this.mc.field_1724.method_6047().method_7909());
        }
        return true;
    }

    public static F457P14c3 getInstance() {
        return instance;
    }
}

