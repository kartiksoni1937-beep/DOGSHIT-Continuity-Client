/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.modules.combat.M4c35w4p;
import me.pepperbell.continuity.module.modules.combat._5w0rdH17;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.ItemSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.TimerUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1296;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_239;
import net.minecraft.class_3489;
import net.minecraft.class_3966;
import net.minecraft.class_9362;
import org.lwjgl.glfw.GLFW;

public final class _7r1993r807
extends Module
implements TickListener {
    private final MinMaxSetting swordDelay = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("S`--w.~o..|r$\u00b7.d|**- \u00b7|*D.~\u00ade|-`|l_|a\u00ad`y"), 0.0, 1000.0, 1.0, 540.0, 560.0).setDescription(EncryptedString.of("A_~.t~.t``_a_\u00b7c~\u00adk.~|. .$d$$e|`l`*~a$`.y^_-~ \u00b7$_\u00adf\u00b7\u00ad.o\u00b7-_^r~|~` *\u00ads~-\u00ad\u00b7w...o|^r~^d**-^s\u00b7`^- $~|\u00b7(^~|m*-$s\u00b7$\u00ad$)"));
    private final MinMaxSetting axeDelay = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("A~-$\u00adx_^\u00ade\u00ad\u00b7` |^\u00adD*.-$e^.\u00b7|l$_~a_\u00ady"), 0.0, 1000.0, 1.0, 780.0, 820.0).setDescription(EncryptedString.of("A~|_t*_t^\u00ad_\u00ada`.|c\u00b7\u00adk\u00b7^|$ \u00ad.\u00ad.d\u00ad`|\u00ade\u00b7\u00b7l.\u00b7_~a`\u00b7*^y|._ ^.|f_$$$o|.r*$`| `||a-`~x-\u00ad`e|~~s$-. `^-\u00ad(\u00b7`m.~*s$||)"));
    private final MinMaxSetting maceDelay = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("M~*a**`c.^-e|^ ~`\u00b7.D|\u00b7-e$`l*~a_~*y"), 0.0, 1000.0, 1.0, 700.0, 750.0).setDescription(EncryptedString.of("A\u00ad*.t\u00ad-t|^$^a*^-c--k^$ \u00ad-d`*\u00ad|e-|^l\u00ad\u00b7^a$-~y~_`| $~|f`*$\u00ado*~.\u00adr**_ ||~m|-^|a^|*c$\u00ade*$s~_ _~(\u00b7^m^-.s|~)"));
    private final BooleanSetting allItems = (BooleanSetting)new BooleanSetting(EncryptedString.of("A^\u00b7-l$.\u00b7$l$\u00b7^$ --`I$*.\u00b7t*|e^`_m|~-s"), false).setDescription(EncryptedString.of("W`$.`o$\u00ad\u00b7r*\u00b7\u00adk*|-s.*^ \u00ad\u00ad^w_-i`-t.$.^h|_^. ^|a~_\u00b7$l-|~l**_ _$i\u00b7\u00ad|\u00adt\u00b7\u00b7e\u00ad_\u00b7\u00adm|*_\u00b7s^`~* -|*(|*|.u$$*\u00ads$_$e-*-*s~||_ \u00b7`~s-\u00adw$*o.^|r`|d-^`` `$d-`e.-$l*|-a\u00ad_y\u00ad\u00b7^)"));
    private final BooleanSetting allowMace = (BooleanSetting)((BooleanSetting)new BooleanSetting(EncryptedString.of("A$$l^\u00ad`l|`-_o-.w*$| _\u00adM\u00ad|a~_|c|^.`e"), true).setDescription(EncryptedString.of("A^**t$-t|\u00b7$a~_|\u00b7c-|$.k|` |\u00ad-w\u00ad\u00ad~$i.\u00b7t|-h*.| _*m\u00ad^`a*~\u00b7$c*$|~e"))).visible(() -> !this.allItems.getValue());
    private final BooleanSetting onlyOnLeftClick = (BooleanSetting)new BooleanSetting(EncryptedString.of("O_._$n``~l~\u00b7$y^\u00b7` \u00ad\u00b7O.`n\u00b7`` -$*L.-`e``_f\u00b7.-^t\u00b7. .\u00b7_~C-`~.l*`-i\u00b7~c^\u00ad~\u00adk"), false).setDescription(EncryptedString.of("O-_-n.`\u00ad|l`\u00ady\u00b7~*- \u00ad\u00ad\u00b7t||~r*^i*-g`-g~|\u00b7e.-\u00b7\u00b7r~-| \u00b7^^w|$h_`$.i*~l|^\u00b7e.~ .$-h`_o*$_l`.$d|^$i~`~\u00adn__g\u00b7$|- \u00ad\u00b7l`$e.|^f|_-t~-| \u00b7-m$|~*o_\u00ad\u00b7u~|\u00ads~_`.e\u00b7|| \u00ad_b~-``u-~t^_|t\u00b7|o^.n"));
    private final BooleanSetting whileAscend = (BooleanSetting)new BooleanSetting(EncryptedString.of("W.$*\u00adh_-_~i*\u00b7l$|$e_|- -_A*_s\u00b7~c$_|~e`\u00ad\u00ad*n-|`d|~_$i$~n-~_|g"), false).setDescription(EncryptedString.of("A\u00ad\u00adl_^^l||o~.w\u00b7\u00ad* .\u00b7h|_i$*t-^$s|`^~ .|^w~.$h$~`i^\u00b7l~^-e-$$ ~~*j*`_u~~.m_-*\u00adp`.*i\u00b7$|.n*|g-*|\u00b7 \u00ad|_^u\u00ad-p\u00ad\u00b7*w|-_a\u00ad`r$*-d"));
    private final BooleanSetting onlyCritSword = (BooleanSetting)new BooleanSetting(EncryptedString.of("O`.\u00ad\u00adn~|l\u00ad*\u00ad*y.\u00b7` _^C.^_-r^.^i.~*t-`$ *\u00adS-.w.*~^o`^r\u00ad_\u00ad^d"), false).setDescription(EncryptedString.of("O$*$-n|_l_.y$*| \u00ad`^c-$*.r^\u00b7$^i$$~t`^\u00b7| _`\u00b7w^\u00b7^i*~t_|h\u00b7\u00b7 ^.-*s_~w~$o|~r~*_d"));
    private final BooleanSetting onlyCritAxe = (BooleanSetting)new BooleanSetting(EncryptedString.of("O`|n~.l`.y|_~* -`_-C``$r_\u00ad$-i\u00ad^\u00b7*t\u00b7.$ _-\u00b7A*-x|*e"), false).setDescription(EncryptedString.of("O_\u00adn~\u00adl~`y_\u00ad* \u00ad\u00ad\u00adc~.r*$^*i.**t|$ *-\u00b7w~$i$_-\u00adt\u00ad$~h*-\u00b7\u00b7 -$-\u00b7a$\u00b7.x|_|_e"));
    private final BooleanSetting checkShield = (BooleanSetting)new BooleanSetting(EncryptedString.of("C*\u00ad~~h.$$`e_*-c*~`k~|.` \u00ad\u00b7S\u00b7`|h$|i~|_.e$|`*l\u00b7~^d"), false).setDescription(EncryptedString.of("S~.||k.^~i$\u00b7\u00b7p\u00ad. \u00b7--a__\u00ad_t~.t_`$a*\u00b7c$|_$k\u00b7~^ *\u00b7i-~\u00adf\u00b7| \u00ad`-t`--~a|*r\u00ad||$g$^`\u00ade~`\u00b7\u00b7t`- _\u00b7$^i-~-s`~. `.-b-^l\u00ad$-~o~\u00b7_^c`$k_|i$$n$|g"));
    private final BooleanSetting sticky = (BooleanSetting)new BooleanSetting(EncryptedString.of("S*\u00b7`a_~*\u00adm.`$e|. -*P\u00ad_l|`\u00b7a*_\u00ad\u00b7y_`~e^-$r"), false).setDescription(EncryptedString.of("K|-~e\u00ad|-|e``$^p.^. *.`h\u00ad^\u00ad$i-$$t.|\u00adt|~^~i^~n_\u00b7~g^_^ \u00b7~~-t\u00ad-h-_*e-*|- *-l.~_a-.`s$*$t|\u00ad` ~`-a^\u00ad|t_^\u00ad`t\u00ad--\u00ada$$$`c\u00b7\u00b7~^k-$_e\u00b7\u00b7-d.* _*~p`|l-$a`\u00b7y\u00ad|\u00ade_|r"));
    private final BooleanSetting useShield = (BooleanSetting)new BooleanSetting(EncryptedString.of("U`~\u00b7s.*|e\u00ad-` ^*\u00adS`|*^h|\u00adi~_-e`^~|l^$^|d"), false).setDescription(EncryptedString.of("R-*^a_-i...-s\u00b7`^\u00b7e``~ **s|__h-`-i|`e-.^l|\u00b7.`d\u00ad\u00ad \u00ad\u00b7|b\u00ad^.e~$t$.w$--\u00ade~_\u00b7e$~|n_`^* \u00ad..a`\u00ad^t^-\u00adt*|_\u00b7a~^^c-\u00b7k`^$\u00ads^- `|*i_*`f*^ _\u00b7**i^|^*n|.$ |..o\u00ad$*f|._f$`\u00ad_h*`a\u00ad~`_n^|d"));
    private final MinMaxSetting shieldTime = (MinMaxSetting)((MinMaxSetting)new MinMaxSetting(EncryptedString.of("S^$h`*.$i-``.e*|l-~$-d_~$ \u00ad.^\u00b7T\u00ad$~*i~~\u00b7m$_-|e"), 100.0, 1000.0, 1.0, 300.0, 400.0).setDescription(EncryptedString.of("H_.o..w^\u00ad.. _\u00ad|-l~\u00b7o\u00ad$n.~g_\u00b7 ~~`t\u00ad--o-~* _.|`h-`o$~^.l-._*d\u00ad-\u00ad \u00b7~*$s\u00ad~\u00b7`h$$-i-\u00b7\u00ad.e|\u00adl$|\u00b7\u00b7d`* \u00b7*(|\u00adm\u00ad-\u00ads-.||)"))).visible(this.useShield::getValue);
    private final BooleanSetting hitInvisible = (BooleanSetting)new BooleanSetting(EncryptedString.of("H\u00b7^i*\u00b7t\u00ad-\u00b7| ~.I-.*.n|`v._-i`$s`|.^i*.*.b.\u00adl\u00ad*$\u00ade"), false).setDescription(EncryptedString.of("A.*.\u00b7t*$t|^|a.`|c\u00b7-~k._ _\u00b7^i~`n$\u00ad~v~$\u00ad.i--s\u00b7\u00b7*^i|~$*b^$^`l\u00b7|e$^` $||_e^~--n^^|\u00adt|-\u00adi-.^|t\u00ad.i^\u00b7e-^s"));
    private final BooleanSetting hitPassive = (BooleanSetting)new BooleanSetting(EncryptedString.of("H*$i_`*.t\u00ad_ .`P-~$a`~|s_|s\u00b7|\u00ad\u00b7i$\u00ad$v\u00ad^e"), false).setDescription(EncryptedString.of("A~.`_t._^t~~.a~-c.~k-|- *^^|p_\u00b7a\u00ad`\u00b7\u00b7s|\u00b7s*~*$i^\u00b7v*$\u00b7~e_`* .-.|m^-\u00ad*o.$b^*.s"));
    private final BooleanSetting hitCrystal = (BooleanSetting)new BooleanSetting(EncryptedString.of("H^-i\u00b7.~~t||`^ ___C*_~$r..y`^s|`t|\u00ad~a`^~l"), false).setDescription(EncryptedString.of("A$\u00b7t^.~$t`\u00b7^$a^$|c_`_k.. *~\u00b7e`$n||d\u00ad\u00ad _\u00b7^c`_\u00b7r_\u00b7|y$._s||~t_`a``l`$s"));
    private final BooleanSetting excludeItems = (BooleanSetting)new BooleanSetting(EncryptedString.of("E\u00ad^\u00b7~x`~\u00b7c~^~$l^-u\u00b7-\u00b7\u00add-\u00b7e-\u00ad_ \u00ad\u00b7I_`t*_$`e\u00b7_-\u00adm`\u00b7s"), false).setDescription(EncryptedString.of("S*$\u00adk~|i$._p|\u00ad \u00b7\u00b7a.$t-.\u00b7^t.|.a-_.-c`$`-k.~ ^|`_w..^^h\u00ad\u00ade**`n`_$\u00b7 $\u00adh\u00ad_o..l`$$.d``i\u00ad`~^n^~\u00b7*g\u00b7\u00b7 .*^_a-\u00b7^_n.^`^ ~_e*~|.x~$c\u00ad*_l$*`_u~\u00ad~~d\u00b7..\u00ade-\u00b7d^\u00ad| *.i\u00ad~t.*-e\u00b7^*.m"));
    private final ItemSetting excludedItems = new ItemSetting(EncryptedString.of("E~$x-~c`**\u00b7l~$_|u\u00b7^\u00b7d\u00ad~.e-_|\u00b7d*^ \u00b7|-*I-.t\u00ad~\u00ade~\u00b7\u00adm`.\u00ad\u00ads"), EncryptedString.of("I-^^t||.-e.$$m.-*s__^ \u00b7_$*t\u00b7\u00adh*.-a.|t\u00b7\u00ad. ^|.w$^i|$l|.l~* \u00b7^-*n-.~o\u00b7``t_$` \u00b7*__t.|_^r`.i|~g_\u00b7_g-*|\u00ade~*`r\u00ad..\u00b7 \u00ad\u00ad*\u00b7a-|*~n__ -\u00ad~a|~\u00b7.t--\u00ad$t._\u00ada|^\u00adc-`k")).visible(this.excludeItems::getValue);
    private final TimerUtils timer = new TimerUtils();
    private long currentDelay = 0L;
    private class_1297 lastAttacked = null;

    public _7r1993r807() {
        super(EncryptedString.of("T\u00ad`r^$*-i$\u00ad`g|`g**\u00ad\u00b7e|`r-\u00ad*-B|_.o$_~t"), EncryptedString.of("A`\u00b7.u\u00ad\u00b7$t.\u00b7o`$*~m_\u00ada$\u00b7t``\u00b7^i^`c~_-*a\u00b7`|~l.\u00ad|l$\u00ady.*\u00b7 .._`a\u00b7~*t_`t|\u00b7a~-c-*k$-\u00ad_s`* `\u00ad--t-$a-|~r_$\u00ad*g~.\u00b7_e-^-t$~|*e^^d~~~$ _*$e$$|`n-$~~t*.|i*`*t*~\u00b7`i_~^e^..s"), -1, Category.COMBAT);
        this.setSubCategory("Sword");
        this.addSettings(this.swordDelay, this.axeDelay, this.maceDelay, this.allItems, this.allowMace, this.onlyOnLeftClick, this.whileAscend, this.onlyCritSword, this.onlyCritAxe, this.checkShield, this.sticky, this.useShield, this.shieldTime, this.hitInvisible, this.hitPassive, this.hitCrystal, this.excludeItems, this.excludedItems);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.timer.reset();
        this.currentDelay = 0L;
        this.lastAttacked = null;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.timer.reset();
        this.currentDelay = 0L;
        this.lastAttacked = null;
        super.onDisable();
    }

    @Override
    public void onTick() {
        _5w0rdH17 _5w0rdH172;
        class_1657 tp;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (this.mc.field_1755 != null) {
            return;
        }
        if (this.mc.field_1724.method_6115()) {
            return;
        }
        if (this.onlyOnLeftClick.getValue() && GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)0) != 1) {
            return;
        }
        if (!this.whileAscend.getValue() && !this.mc.field_1724.method_24828() && this.mc.field_1724.method_18798().field_1351 >= -0.2) {
            return;
        }
        class_1297 target = this.resolveTarget();
        if (target == null) {
            return;
        }
        if (!this.isValidTarget(target)) {
            return;
        }
        if (!this.isValidWeapon()) {
            return;
        }
        if (this.checkShield.getValue() && target instanceof class_1657 && (tp = (class_1657)target).method_6039()) {
            return;
        }
        M4c35w4p breachSwap = Sweto.INSTANCE.getModuleManager().getModule(M4c35w4p.class);
        boolean breachSwapActive = breachSwap != null && breachSwap.isEnabled();
        class_1792 held = this.mc.field_1724.method_6047().method_7909();
        boolean isSword = held.method_7876().toLowerCase().contains("sword");
        boolean isAxe = held instanceof class_1743;
        if (!breachSwapActive && this.onlyCritSword.getValue() && isSword && this.mc.field_1724.field_6017 <= 0.0) {
            return;
        }
        if (!breachSwapActive && this.onlyCritAxe.getValue() && isAxe && this.mc.field_1724.field_6017 <= 0.0) {
            return;
        }
        if (!this.timer.hasReached(this.currentDelay)) {
            return;
        }
        if (this.useShield.getValue() && this.mc.field_1724.method_6039()) {
            this.mc.field_1724.method_6075();
        }
        if (this.allItems.getValue() && (_5w0rdH172 = Sweto.INSTANCE.getModuleManager().getModule(_5w0rdH17.class)) != null && _5w0rdH172.isEnabled()) {
            int swordSlot;
            class_1792 currentItem = this.mc.field_1724.method_6047().method_7909();
            boolean currentIsSword = this.mc.field_1724.method_6047().method_31573(class_3489.field_42611);
            boolean currentIsAxe = currentItem instanceof class_1743;
            boolean currentIsMace = currentItem instanceof class_9362;
            if (!(currentIsSword || currentIsAxe || currentIsMace || (swordSlot = this.findSwordSlot()) == -1)) {
                this.mc.field_1724.method_31548().method_61496(swordSlot);
            }
        }
        this.performAttack(target);
    }

    private class_1297 resolveTarget() {
        class_239 hit = this.mc.field_1765;
        if (!(hit instanceof class_3966)) {
            return null;
        }
        class_3966 entityHit = (class_3966)hit;
        class_1297 entity = entityHit.method_17782();
        if (this.sticky.getValue() && this.lastAttacked != null && this.lastAttacked.method_5805() && this.lastAttacked != this.mc.field_1724) {
            return this.lastAttacked;
        }
        return entity;
    }

    private void performAttack(class_1297 target) {
        this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, target);
        this.mc.field_1724.method_6104(class_1268.field_5808);
        this.lastAttacked = target;
        this.timer.reset();
        class_1792 held = this.mc.field_1724.method_6047().method_7909();
        this.currentDelay = held instanceof class_1743 ? (long)this.axeDelay.getRandomValue() : (held instanceof class_9362 && this.allowMace.getValue() ? (long)this.maceDelay.getRandomValue() : (long)this.swordDelay.getRandomValue());
    }

    private boolean isValidWeapon() {
        class_1792 item = this.mc.field_1724.method_6047().method_7909();
        if (this.excludeItems.getValue() && this.excludedItems.isSelected(item)) {
            return false;
        }
        if (this.allItems.getValue()) {
            return true;
        }
        String key = item.method_7876().toLowerCase();
        if (key.contains("sword") || key.contains("axe")) {
            return true;
        }
        return item instanceof class_9362 && this.allowMace.getValue();
    }

    private boolean isValidTarget(class_1297 entity) {
        if (!entity.method_5805()) {
            return false;
        }
        if (entity == this.mc.field_1724) {
            return false;
        }
        if (!this.hitInvisible.getValue() && entity.method_5767()) {
            return false;
        }
        if (!this.hitCrystal.getValue() && entity instanceof class_1511) {
            return false;
        }
        return this.hitPassive.getValue() || !(entity instanceof class_1296);
    }

    private int findSwordSlot() {
        if (this.mc.field_1724 == null) {
            return -1;
        }
        for (int i = 0; i < 9; ++i) {
            if (!this.mc.field_1724.method_31548().method_5438(i).method_31573(class_3489.field_42611)) continue;
            return i;
        }
        return -1;
    }

    public boolean isInCritWindow() {
        return this.isEnabled() && !this.mc.field_1724.method_24828() && this.mc.field_1724.method_18798().field_1351 < 0.0;
    }
}

