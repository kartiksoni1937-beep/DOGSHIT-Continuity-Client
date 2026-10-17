/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.misc;

import me.pepperbell.continuity.event.events.AttackListener;
import me.pepperbell.continuity.event.events.BlockBreakingListener;
import me.pepperbell.continuity.event.events.ItemUseListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.utils.BlockUtils;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_239;
import net.minecraft.class_3965;

public final class Pr3v3n7
extends Module
implements ItemUseListener,
AttackListener,
BlockBreakingListener {
    private final BooleanSetting doubleGlowstone = (BooleanSetting)new BooleanSetting(EncryptedString.of("D~`|`o|-_u-.$.b$||l.`-|e`. _~_G`\u00b7$$l\u00b7.-_o-_w\u00ad$*s|`t\u00ad|\u00ad^o^^_.n$\u00b7e"), false).setDescription(EncryptedString.of("M\u00ad^$`a-._-k.$e`|*$s-*`- *--\u00adi|*~t_\u00b7 |~s^|o\u00b7`\u00ad `$_y$^|o$`.u-`. _`_c`-_a^-_n\u00ad^`\u00b7'.$`t$$^_ ^\u00ad.c*\u00adh||\u00b7_a$$.^r*^*g*.|`e^$^. ~^^t|$h^-$e$| _|^a`$\u00b7`n*._c.$^.h^-_`o\u00b7^\u00b7r\u00ad~- ~_\u00b7-a`~.g^$*a\u00b7\u00b7i|~-\u00adn_| ..-i$|*f~` *~`.i_`t`-\u00ad'-\u00ad|*s|`~ $*_a_-*l~-\u00ad*r~|\u00b7e^-~a$~`d\u00b7.~.y-^*\u00ad \u00ad`_c.-h\u00b7\u00ad_a..`.r$_^g\u00b7|\u00ad\u00ade\u00ad*\u00b7d"));
    private final BooleanSetting glowstoneMisplace = (BooleanSetting)new BooleanSetting(EncryptedString.of("G^*l\u00b7.~\u00ado_~w\u00ad_s-`t*~^*o|-\u00b7_n\u00ad~e|- $~~M~..i-._s^$~p._l.*$a\u00ad`-~c^^.e"), false).setDescription(EncryptedString.of("M\u00ad--a~~k\u00ad*~*e\u00ad|-^s\u00ad-| ~$*i-*t\u00b7-* \u00b7-.s`*|o^~.. -`\u00b7y|.$\u00b7o-\u00b7\u00b7u\u00ad$^` _~c\u00b7|*a.$~~n\u00ad. |*`\u00b7o|.^n\u00ad|\u00b7\u00b7l~_~_y-.- ^-$r\u00b7-`_i^\u00b7\u00b7g$_``h.|t^\u00ad_c\u00b7|-^l--$i*_.c.~^k-_ ^_-w_\u00b7i--t`\u00ad\u00adh$. ^_\u00ad_g~-\u00ad*l\u00ad||o$\u00b7*_w\u00b7^~s~^t.._.o$|*n-$^~e^~ |$~o-.|n`~-\u00adl-|*~y.|- `-w_$`h|`$e|~*n\u00b7\u00ad *$||a-_`^i^^m|_*$i_-n_\u00ad\u00adg\u00ad\u00ad -.a|^^*t`\u00b7_ `\u00b7.a``~\u00adn.-$ |~$.a-$_\u00b7n**c`_h^-~o^.r"));
    private final BooleanSetting anchorOnAnchor = (BooleanSetting)new BooleanSetting(EncryptedString.of("A-*~n|^.c|_*h\u00b7.o*^.r\u00b7*.- |*~\u00b7o\u00b7_n$_` |$A~\u00ad.n\u00ad\u00b7.c-`h-_o`-r"), false).setDescription(EncryptedString.of("M~\u00b7*a-`-k|~e$\u00b7s..\u00b7* ^\u00ad-i\u00b7$`\u00b7t-_ ^$`s.^^.o\u00b7\u00ad_ *|*y$|$^o-.u_\u00ad$ |\u00b7$*c-\u00b7a.`n*|_\u00b7'*_^.t|` -$~p\u00ad.l*\u00ada~|`_c\u00ad\u00b7`e.$| ^.|a`-$n\u00ad.\u00ad` $|_a~~^n|\u00ad_c|^|\u00b7h*`o`^r`$ \u00ad^~o~~`n\u00ad\u00ad_/^-.n*\u00b7~|e``x.._|t\u00ad^~| ---t~~o-- \u00b7`__a_*|n.*$\u00ado$^-t~$^h_*e`^r|`` .$~$a\u00b7-.n^_-c|.`h*\u00b7.\u00ado||$`r-^*_ *\u00ad^u```n.|l_*\u00b7e^~`_s~\u00ad`\u00ads^*^ \u00ad\u00b7^.c_.\u00adh~`*a-_^r_|g`-\u00ade~.-.d"));
    private final BooleanSetting obiPunch = (BooleanSetting)new BooleanSetting(EncryptedString.of("O.~\u00adb|.i|_ *`P\u00b7``u\u00b7\u00b7--n$**c^\u00b7*\u00adh"), false).setDescription(EncryptedString.of("M-.-a*$^\u00b7k-$`~e\u00b7_*s`\u00b7* $^i__t$` *`s`\u00ad_o-\u00ad.` ``^y_$_o-`u|` *`~~c.*$|a|\u00b7n-*$ |\u00ad\u00b7|c$_\u00b7r***y|-\u00b7s\u00b7\u00b7\u00adt.*a|.`\u00adl|_|| -\u00ad\u00b7f`_a~**s~\u00adt`|e^`r^_^\u00ad *~|b``\u00ad*y$.~\u00b7 $|^n\u00b7^o_^t\u00b7~| ^\u00b7$\u00adl\u00ad~e||\u00adt~^|t|_i\u00b7.n|``_g\u00ad-*\u00b7 $.y-\u00b7o_.-|u\u00b7^$- |_*l_*\u00ad*e--f\u00ad`|t|$\u00b7` _|c$*l_$\u00ad|i*`c\u00ad^-~k$`/\u00ad$s^|_.t^_\u00b7a^|\u00ad*r*_\u00adt\u00ad.~- ~-b\u00b7\u00b7^r\u00ad**_e|.a\u00ad`_k_**-i.\u00b7.|n*\u00ad`|g\u00b7-\u00ad^ -_\u00adt~*h-~e^--$ _|o~$|\u00b7b.~s~``.i$\u00ad\u00b7$d`$_^i\u00ad-^a|_n"));
    private final BooleanSetting antiGlowstoneOnGlowstone = (BooleanSetting)new BooleanSetting(EncryptedString.of("A"), false).setDescription(EncryptedString.of("W"));
    private final BooleanSetting echestClick = (BooleanSetting)new BooleanSetting(EncryptedString.of("E.._-c\u00ad\u00b7h-.e-$$s_~_t\u00b7-*^ \u00b7^_c-.l~*$i*-c\u00b7$.k"), false).setDescription(EncryptedString.of("M*\u00b7_a\u00b7\u00b7|`k.|`-e\u00b7*-$s^|` .$`$i`\u00ad__t`$_ *$_s\u00b7\u00ado$\u00b7. ^-^|y`\u00ad.-o~_u|~\u00b7_ **.|c.^_-a_~n-^$|'_`\u00b7_t\u00ad-* *`$\u00adc*.l\u00ad-_i`_^c^~`k$\u00ad ~\u00ad$o\u00ad\u00b7$\u00adn-~~~ ^_*e$__~c-\u00ad\u00adh\u00b7|-e.~~~s_\u00b7~_t.$~s-_^$ -\u00b7w*~\u00adi\u00ad|t_`\u00b7h$|- ||~$P`^\u00adv**|P\u00ad`$ _\u00ad|~i^\u00ad|t$\u00b7e~`m`_*s"));

    public Pr3v3n7() {
        super(EncryptedString.of("P_~r\u00ad.e...`v$|e\u00ad\u00b7n_$_t"), EncryptedString.of("P_.^\u00adr~\u00b7e|`||v`|.e`^n_-.|t*~_s.`_ `\u00ad$_y\u00b7\u00b7-o.*\u00b7u|\u00b7.~ ^|*f*\u00adr$-o_`\u00ad~m-| -*c*-|e|~^r*^t^___a|\u00ad^i\u00b7$*n*$|\u00ad -|-$a$\u00b7c$\u00b7$t$\u00b7*|i~$o~\u00adn|.s"), -1, Category.MISC);
        this.addSettings(this.doubleGlowstone, this.glowstoneMisplace, this.anchorOnAnchor, this.obiPunch, this.antiGlowstoneOnGlowstone, this.echestClick);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(BlockBreakingListener.class, this);
        this.eventManager.add(AttackListener.class, this);
        this.eventManager.add(ItemUseListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(BlockBreakingListener.class, this);
        this.eventManager.remove(AttackListener.class, this);
        this.eventManager.remove(ItemUseListener.class, this);
        super.onDisable();
    }

    @Override
    public void onAttack(AttackListener.AttackEvent event) {
        class_3965 hit;
        class_239 class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3965 && BlockUtils.isBlock((hit = (class_3965)class_2392).method_17777(), class_2246.field_10540) && this.obiPunch.getValue() && this.mc.field_1724.method_24518(class_1802.field_8301)) {
            event.cancel();
        }
    }

    @Override
    public void onBlockBreaking(BlockBreakingListener.BlockBreakingEvent event) {
        class_3965 hit;
        class_239 class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3965 && BlockUtils.isBlock((hit = (class_3965)class_2392).method_17777(), class_2246.field_10540) && this.obiPunch.getValue() && this.mc.field_1724.method_24518(class_1802.field_8301)) {
            event.cancel();
        }
    }

    @Override
    public void onItemUse(ItemUseListener.ItemUseEvent event) {
        class_239 class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3965) {
            class_3965 hit = (class_3965)class_2392;
            if (BlockUtils.isAnchorCharged(hit.method_17777()) && this.doubleGlowstone.getValue() && this.mc.field_1724.method_24518(class_1802.field_8801)) {
                event.cancel();
            }
            if (!BlockUtils.isBlock(hit.method_17777(), class_2246.field_23152) && this.glowstoneMisplace.getValue() && this.mc.field_1724.method_24518(class_1802.field_8801)) {
                event.cancel();
            }
            if (BlockUtils.isAnchorNotCharged(hit.method_17777()) && this.anchorOnAnchor.getValue() && this.mc.field_1724.method_24518(class_1802.field_23141)) {
                event.cancel();
            }
            if (BlockUtils.isBlock(hit.method_17777(), class_2246.field_10443) && this.echestClick.getValue() && (WorldUtils.isSword(this.mc.field_1724.method_6047()) || this.mc.field_1724.method_6047().method_7909() == class_1802.field_8301 || this.mc.field_1724.method_6047().method_7909() == class_1802.field_8281 || this.mc.field_1724.method_6047().method_7909() == class_1802.field_23141 || this.mc.field_1724.method_6047().method_7909() == class_1802.field_8801)) {
                event.cancel();
            }
            if (BlockUtils.isBlock(hit.method_17777(), class_2246.field_10171) && this.antiGlowstoneOnGlowstone.getValue() && this.mc.field_1724.method_24518(class_1802.field_8801)) {
                event.cancel();
            }
        }
    }
}

