/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.ItemUseListener;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.BlockUtils;
import me.pepperbell.continuity.utils.CrystalUtils;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.KeyUtils;
import me.pepperbell.continuity.utils.MathUtils;
import me.pepperbell.continuity.utils.MouseSimulation;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1621;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import org.lwjgl.glfw.GLFW;

public final class R4nd14u70Cry5741
extends Module
implements TickListener,
ItemUseListener {
    private final KeybindSetting activateKey = (KeybindSetting)new KeybindSetting(EncryptedString.of("A_|c.^\u00adt~\u00b7-^i*-v^^\u00ada\u00b7_|\u00b7t|-e*.\u00b7~ `*`K$`~e\u00ad-`_y"), 1, false).setDescription(EncryptedString.of("K~.e-*_y`~|~ ^^t\u00ad\u00adh\u00ad$a~|\u00b7t*\u00ad* \u00ad|$\u00add|$o\u00b7\u00b7|e|\u00b7_.s$|- ~\u00ad|^t|`~$h-$*e`-|- \u00b7.c.*^r-\u00b7y-.\u00b7\u00b7s~-t$|*_a-$^-l$$l$^i\u00ad~$n_-.g"));
    private final SliderSetting placeDelay = new SliderSetting(EncryptedString.of("P_\u00b7*$l__-a\u00b7*`c`*e*$|~ -\u00adD^^e___~l-.a|-^*y"), 0.0, 20.0, 0.0, 1.0);
    private final SliderSetting breakDelay = new SliderSetting(EncryptedString.of("B\u00ad-r~|*.e`~a.-~k-*^ \u00b7`D\u00b7-~e$^$|l^*a$^*y"), 0.0, 20.0, 0.0, 1.0);
    private final SliderSetting placeChance = (SliderSetting)new SliderSetting(EncryptedString.of("P-^l$\u00b7.*a~*|`c.|\u00ade^^ \u00b7_*`C$~$h_$*-a~^-n^$$.c^_\u00b7e"), 0.0, 100.0, 100.0, 1.0).setDescription(EncryptedString.of("R\u00b7_--a$^n|-._d^-\u00b7o\u00b7.m__i-\u00ad|~z.__-a_\u00ad$|t|~.i`$--o-``*n"));
    private final SliderSetting breakChance = (SliderSetting)new SliderSetting(EncryptedString.of("B`^r\u00b7\u00b7_e~*~^a~`-k\u00ad_*~ .\u00ad~\u00b7C-\u00ad-$h$$a**^n\u00b7-c$*\u00ade"), 0.0, 100.0, 100.0, 1.0).setDescription(EncryptedString.of("R``a$*n~^~~d*^~o`_|m`\u00adi\u00b7.\u00ad_z~~-a.$-\u00adt`^i\u00b7`$$o~^n"));
    private final BooleanSetting stopOnKill = (BooleanSetting)new BooleanSetting(EncryptedString.of("S*$`t*$\u00b7o_^p-_- \u00ad$o\u00b7\u00b7$|n^* -_\u00b7~K^_-i\u00b7\u00adl\u00ad_l"), false).setDescription(EncryptedString.of("W-^o^-`~n_^_'*_\u00ad~t_$^_ ^^\u00ad*c-\u00adr$*^|y\u00b7*~.s~*t`|`a**-l\u00ad_| .~^_i-_|-f\u00b7~ *$.a.-^ *`||d\u00b7-e*^|a$~_d_.~$ _$\u00adp^`\u00b7*l`-`~a.\u00ad`_y\u00ad~|_e***r$$$ _$$i^~^.s-\u00b7-| \u00b7`.n--e^\u00ad|a\u00ad._\u00b7r~^*^b~`_\u00ady"));
    private final BooleanSetting fakePunch = (BooleanSetting)new BooleanSetting(EncryptedString.of("F|\u00ad$a.^*^k-^$*e_~$ -~_P|~\u00adu^`n~\u00ad~.c*\u00b7$\u00adh"), false).setDescription(EncryptedString.of("W*-$i-*.~l-|l-^ \u00b7*|^h\u00ad**|i_*\u00adt^_ \u00ad-\u00ade~~_v.\u00b7`e-`\u00ad^r-*-y|$ -_e`^|_n~\u00b7~`t^~|^i*\u00ad|t|~|`y--$ \u00b7_a`$n-.d_$$_ $.b.\u00adl`\u00ad^~o_*c*^`|k_.` \u00b7^\u00adi\u00ad\u00b7\u00b7f\u00ad*.- ^\u00b7\u00b7y*.\u00ado^\u00ad|u_* ~^m`_|\u00adi-.~\u00b7s~*|s*\u00b7~ *^a\u00b7`-- -||^h\u00ad$_`i.*\u00ad_t~\u00b7\u00b7`c`*r.\u00b7y|`^s.\u00ad*t*^\u00ad\u00b7a*~$l"));
    private final BooleanSetting clickSimulation = (BooleanSetting)new BooleanSetting(EncryptedString.of("C_-~l~~$|i\u00ad\u00ad.c*-\u00adk`-` -\u00adS|.i-*m.^^~u\u00ad~_`l$\u00b7_-a-|\u00b7.t^*`i*\u00ad~-o~|`n"), false).setDescription(EncryptedString.of("M|*._a--\u00b7k|\u00b7^*e||^|s.^ .$_t~|~h*$e|.~` $_|_C\u00ad-P-*S$\u00ad-~ .\u00ad$.h\u00ad^^~u~\u00b7d`|$~ `.-~t\u00ad^h\u00b7*^|i._n\u00b7\u00ad_k__ \u00b7\u00ady*~o-|.u`\u00ad\u00ad$'^$-r.-^`e$_\u00ad *~l**-\u00ade\u00b7|^g*~i\u00b7.^t"));
    private final MinMaxSetting leftClickDelay = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("L$^_e$~-f$*`t\u00ad|`\u00b7 ~.C|_^l\u00b7\u00b7$i~\u00ad\u00b7~c$.~k~\u00ad~| _|$.D$`\u00ade.|`*l--`|a*\u00ady"), 0.0, 200.0, 1.0, 30.0, 40.0).setDescription(EncryptedString.of("D\u00b7_$$e_|\u00b7-l$*-`a\u00ad.-`y$\u00ad ^*~`r|*a\u00b7^~_n_$-g^|e|^~~ \u00b7\u00ad\u00ad^f~|_o^$^.r*. |~~~s$\u00ad\u00adi*\u00ad*m.`_u$*-|l|-*.a\u00b7|^\u00b7t$`^~e`\u00ad\u00add|_ ~_\u00ad\u00b7l\u00b7*.e_*f._..t``\u00b7^ _*\u00b7c..l~|_~i~-$^c^$k*$s^\u00b7*| |~($-|m`.^s|-~*)"));
    private final MinMaxSetting rightClickDelay = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("R$~_.i_^g*``\u00b7h_^\u00b7\u00b7t\u00b7-^` ~*C\u00ad|l$$*i|.c\u00ad`k_*-\u00b7 *\u00b7^D\u00b7$e||l-`-`a$._y"), 0.0, 200.0, 1.0, 30.0, 40.0).setDescription(EncryptedString.of("D.\u00ad.e-^l$*a.^.y\u00b7\u00b7-. $*r.\u00b7a__\u00b7~n.\u00b7g-~e~_- `|-_f|.o^-r*\u00b7 ~\u00ads~*\u00b7i\u00b7\u00adm~$u`\u00b7l\u00b7_`a$.t^\u00ad*e.\u00ad.\u00add\u00b7*- $.r.$_.i*$g*`h\u00b7\u00b7t*|. |~c_~--l`\u00b7.*i^_~\u00adc\u00b7~*\u00b7k\u00b7^\u00ads.| |~(|`m.\u00ads-*\u00b7)"));
    private final BooleanSetting damageTick = (BooleanSetting)new BooleanSetting(EncryptedString.of("D`~$a_^~m\u00ad\u00b7a~|\u00ad~g_.e$|. `*.T_\u00adi-$_`c-^k"), false).setDescription(EncryptedString.of("T.^_i\u00b7-m\u00ad\u00b7^e`*-s$^ \u00ad\u00b7y$\u00b7o\u00ad|u$^~r\u00ad-^ \u00ad._.c_|r`_*|y.`*\u00b7s^`t-.a|.l`^-s~_| -|-f~^|_o$_|`r-*|_ \u00b7\u00ada|\u00ad-| -^p`.e*$__r~$\u00ad*f\u00ad*~e_--c.~|^t*|| _`^$d\u00ad*t_\u00ad~`a.~\u00b7p"));
    private final BooleanSetting antiWeakness = (BooleanSetting)new BooleanSetting(EncryptedString.of("A$*_\u00adn_|t~__|i-_~.W``\u00ad_e\u00b7\u00b7a|~_`k\u00b7~n_\u00ad*$e\u00ad~~~s\u00ad**s"), false).setDescription(EncryptedString.of("S\u00b7$\u00adi*--l~-*\u00b7e\u00b7_^n-\u00ad.`t|\u00b7l~$-y.\u00ad~ |$s`\u00ad..w.-i\u00b7^^|t~$$c`|.^h`$`e|\u00b7s|`- ~\u00b7~t.**\u00b7o.| ~.-$a~\u00ad~$ _|`~s~|_w$`_o\u00b7_r~.d__ ^_\u00ad\u00b7a^**n~-_~d^.\u00ad\u00b7 **-\u00adt.`h$`-e_..^n$-$ *.^h\u00b7_|i`.\u00b7*t|\u00ads\u00b7~~ *^-.t~--h-\u00ade~*^ \u00ad.\u00ad~c\u00ad.*^r$**y^*\u00ad\u00ads.$_t$*a_*l$^ -_\u00b7i\u00ad\u00b7`$f$\u00ad`- *|`~y|\u00ad.~o|.\u00b7\u00b7u^|.$ ^\u00b7|`h-~$a||-v*\u00b7e.\u00b7$$ ^_$-w^^~*e\u00b7^\u00ada_~k`_|n$\u00b7`\u00b7e\u00ad\u00b7s-`s"));
    private final SliderSetting particleChance = (SliderSetting)new SliderSetting(EncryptedString.of("P\u00ad*^`a\u00ad^~~r.\u00b7^~t-.-i`|*c`~^l`|_e`| |_.C\u00ad`~-h$`\u00ada``**n`^c-$e"), 0.0, 100.0, 20.0, 1.0).setDescription(EncryptedString.of("A\u00b7_`$d*$\u00add_-s\u00b7_* .|*|b\u00ad\u00ad\u00ad$l*^\u00ado*_*-c|*$k$|- -.\u00b7.b__-r^^-|e\u00b7.*a|-|k^~|i_*|\u00b7n`\u00b7g\u00ad\u00b7| ^~`~p~-a|\u00adr~_^t~^.\u00adi_~_*c-`*-l*`e`*\u00ad_s*\u00ad| *|$^t^__^o-\u00b7-- .^`m\u00b7~a`\u00b7_k`*\u00b7e`$| ^_\u00adi___$t\u00b7\u00b7_ $\u00ads.\u00ade~-^e._m^\u00ad \u00b7.$m_`_o\u00ad*r\u00b7.`*e`$$ ^\u00ad|$l_^-e~..g\u00ad\u00ad_i|\u00b7t_| *^_f$*-r^.*o*$~~m\u00b7~_ .``y._o.._u|\u00b7r\u00b7` ^^~$P..~O-~|V~`^ *\u00ad~`($`O_|\u00b7n`~~\u00b7l\u00b7\u00b7\u00ady*^` -$w`~$\u00b7o|`_$r\u00ad*`k*_s.\u00ad `~|w|^\u00b7i\u00b7.t\u00b7_h\u00ad\u00b7_ |\u00b7*^f*`*a`\u00b7k\u00ad$e*- $|-*p^^u|~-\u00b7n_`\u00adc_*\u00b7h~`~)"));
    private final BooleanSetting breakAndPlace = (BooleanSetting)new BooleanSetting(EncryptedString.of("B.^*|r`**e$\u00b7a~|\u00b7k\u00ad. $|`a$\u00ad|_n\u00b7.^d-. ^$P$`l~\u00b7\u00ad-a__*\u00b7c`~*\u00b7e"), true).setDescription(EncryptedString.of("B\u00b7*\u00b7r\u00ad|e.*a\u00ad.\u00ad_k`~ *^*_e$|^x.|\u00ad$i_||s$_.t\u00b7~|i.\u00b7n`~\u00adg-*_ $\u00adc.\u00b7r-~\u00ad*y`-|$s~`.t~.\u00ada|^\u00b7$l`.`* \u00ad\u00b7.a~$_-n~|d*- _^*\u00adp.^*l^^a\u00b7|.\u00b7c|*e`*|| ``|.n|\u00b7e||-^w\u00ad-\u00b7 _^*o~\u00ad.`n*_$e^~. \u00ad_$\u00adw`__h*~e_\u00b7n-~\u00ad. $^*-r||\u00ad~i\u00b7$_-g*`h-~\u00b7_t^`\u00b7c-*l|-i\u00ad$c|.k\u00ad``i._-n_^~^g.|*. *^^o\u00b7\u00b7`n~_- _~|~p-\u00b7\u00adl~_.a^^\u00adc\u00ad_\u00b7e\u00ad_d^.\u00ad\u00ad ~\u00b7c.$*_r-`y\u00b7-s\u00ad|*\u00adt\u00b7*_a\u00ad*_*l"));
    private final BooleanSetting autoSwapToCrystal = (BooleanSetting)new BooleanSetting(EncryptedString.of("A$\u00ad\u00adu.-*\u00b7t-~-$o``. ~~.^S.$w*`_|a`^`p$_^ $_\u00b7-t^*o`~* ^_C^_$^r`$-y*-|s^|t-||*a`~`l"), true).setDescription(EncryptedString.of("A~`u\u00b7\u00b7|$t*\u00ado$~|~m\u00ad|a\u00ad\u00ad|.t^$^-i_\u00b7\u00b7-c``a$.l`-l$~$\u00b7y\u00b7*.\u00ad *.s\u00b7-$w_|_.a\u00ad*`^p`__ \u00ad^t\u00ad|o\u00b7` -.|\u00b7c`$^|r*^y~\u00ads_\u00adt^\u00ada-\u00b7^`l\u00ad$$- ~-_w$.h-_^e_-n_~| |_~-h*\u00ad^.o_|l-$\u00b7d\u00ad~\u00adi~`n_\u00b7g|\u00ad| _~s~*|_w\u00b7|.o`|$$r._`\u00add\u00ad*~ ^~*a*_|.n~`*~d\u00b7.`~ \u00ad\u00ad~~r$^i\u00ad`^g~.~|h-`_t\u00ad^c\u00ad*$\u00b7l~*i_|*\u00adc$$\u00adk|\u00b7\u00b7i\u00b7.n-^$g*^| \u00ad\u00ado^.-n`\u00b7\u00b7| ^\u00ad|\u00b7o|\u00ad$|b$.`\u00b7s|_$*i.`-d$`**i*.a^\u00b7n^\u00b7*-/^*c\u00ad-~$r\u00ad_|y|.s\u00ad_t^.a-\u00b7l"));
    private int placeClock;
    private int breakClock;
    public boolean crystalling;

    public R4nd14u70Cry5741() {
        super(EncryptedString.of("R^*~a\u00ad\u00b7n.\u00b7\u00b7\u00b7d_*i`-.$ ^|$A\u00ad`_u||$t_-_o~\u00ad^~ -\u00adC~`~*r_\u00b7.|y\u00ad-s~_~t`.^a-\u00b7*l"), EncryptedString.of("A__u_~t_$\u00ad.o^__~m$\u00ada\u00b7~_t\u00b7_$i.\u00ad\u00b7|c\u00b7_a^-l$|.*l|\u00b7^y|*` ..^c\u00ad\u00ad^\u00b7r\u00b7\u00ad-y\u00b7``s\u00b7\u00ad\u00b7^t`~$a\u00b7^.\u00adl-\u00b7s_` *.~f$.~a_\u00b7\u00ads_.^t.\u00ad$\u00ad `**f.|$\u00ado.~r\u00b7_ `*`^y*||.o^.u"), -1, Category.COMBAT);
        this.addSettings(this.activateKey, this.placeDelay, this.breakDelay, this.placeChance, this.breakChance, this.stopOnKill, this.fakePunch, this.clickSimulation, this.leftClickDelay, this.rightClickDelay, this.damageTick, this.antiWeakness, this.particleChance, this.breakAndPlace, this.autoSwapToCrystal);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.eventManager.add(ItemUseListener.class, this);
        this.placeClock = 0;
        this.breakClock = 0;
        this.crystalling = false;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.eventManager.remove(ItemUseListener.class, this);
        super.onDisable();
    }

    @Override
    public void onTick() {
        class_3965 hit;
        boolean dontBreak;
        if (this.mc.field_1755 != null) {
            return;
        }
        if (this.autoSwapToCrystal.getValue() && WorldUtils.isSword(this.mc.field_1724.method_6047())) {
            boolean rightClickPressed;
            boolean bl = rightClickPressed = GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)1) == 1;
            if (rightClickPressed && this.isValidCrystalTarget() && InventoryUtils.hasItemInHotbar(i -> i == class_1802.field_8301)) {
                InventoryUtils.selectItemFromHotbar(class_1802.field_8301);
                return;
            }
        }
        boolean dontPlace = this.placeClock != 0;
        boolean bl = dontBreak = this.breakClock != 0;
        if (this.stopOnKill.getValue() && WorldUtils.isDeadBodyNearby()) {
            return;
        }
        int randomInt = MathUtils.randomInt(1, 100);
        if (dontPlace) {
            --this.placeClock;
        }
        if (dontBreak) {
            --this.breakClock;
        }
        if (this.mc.field_1724.method_6115()) {
            return;
        }
        if (this.damageTick.getValue() && this.damageTickCheck()) {
            return;
        }
        if (this.activateKey.getKey() != -1 && !KeyUtils.isKeyPressed(this.activateKey.getKey())) {
            this.placeClock = 0;
            this.breakClock = 0;
            this.crystalling = false;
            return;
        }
        this.crystalling = true;
        if (this.mc.field_1724.method_6047().method_7909() != class_1802.field_8301) {
            return;
        }
        class_239 class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3965) {
            hit = (class_3965)class_2392;
            if (this.mc.field_1765.method_17783() == class_239.class_240.field_1332) {
                if (!dontPlace && randomInt <= this.placeChance.getValueInt() && (BlockUtils.isBlock(hit.method_17777(), class_2246.field_10540) || BlockUtils.isBlock(hit.method_17777(), class_2246.field_9987) && CrystalUtils.canPlaceCrystalClientAssumeObsidian(hit.method_17777()))) {
                    if (this.clickSimulation.getValue()) {
                        int clickDelay = this.rightClickDelay.getRandomValueInt();
                        MouseSimulation.mouseClick(1, clickDelay);
                    }
                    WorldUtils.placeBlock(hit, true);
                    if (this.fakePunch.getValue() && randomInt <= this.particleChance.getValueInt() && CrystalUtils.canPlaceCrystalClientAssumeObsidian(hit.method_17777()) && hit.method_17780() == class_2350.field_11036) {
                        this.mc.field_1687.method_31595(hit.method_17777(), this.mc.field_1687.method_8320(hit.method_17777()));
                    }
                    this.placeClock = this.placeDelay.getValueInt();
                }
                if (this.fakePunch.getValue()) {
                    if (!dontBreak && randomInt <= this.breakChance.getValueInt()) {
                        if (BlockUtils.isBlock(hit.method_17777(), class_2246.field_10540) || BlockUtils.isBlock(hit.method_17777(), class_2246.field_9987)) {
                            return;
                        }
                        if (this.clickSimulation.getValue()) {
                            if (BlockUtils.isBlock(hit.method_17777(), class_2246.field_10540) || BlockUtils.isBlock(hit.method_17777(), class_2246.field_9987)) {
                                if (CrystalUtils.canPlaceCrystalClientAssumeObsidian(hit.method_17777())) {
                                    int clickDelay = this.leftClickDelay.getRandomValueInt();
                                    MouseSimulation.mouseClick(0, clickDelay);
                                }
                            } else {
                                int clickDelay = this.leftClickDelay.getRandomValueInt();
                                MouseSimulation.mouseClick(0, clickDelay);
                            }
                        }
                        this.mc.field_1761.method_2910(hit.method_17777(), hit.method_17780());
                        this.mc.field_1724.method_6104(class_1268.field_5808);
                        this.mc.field_1687.method_31595(hit.method_17777(), this.mc.field_1687.method_8320(hit.method_17777()));
                        this.mc.field_1761.method_2902(hit.method_17777(), hit.method_17780());
                        this.breakClock = this.breakDelay.getValueInt();
                    }
                    if (!dontPlace && randomInt <= this.placeChance.getValueInt() && dontBreak && this.clickSimulation.getValue()) {
                        int clickDelay = this.rightClickDelay.getRandomValueInt();
                        MouseSimulation.mouseClick(1, clickDelay);
                    }
                }
            }
            if (this.mc.field_1765.method_17783() == class_239.class_240.field_1333 && this.fakePunch.getValue()) {
                if (!dontBreak && randomInt <= this.breakChance.getValueInt()) {
                    if (this.mc.field_1761.method_2924()) {
                        this.mc.field_1771 = 10;
                    }
                    if (this.clickSimulation.getValue()) {
                        int clickDelay = this.leftClickDelay.getRandomValueInt();
                        MouseSimulation.mouseClick(0, clickDelay);
                    }
                    this.mc.field_1724.method_6104(class_1268.field_5808);
                    this.breakClock = this.breakDelay.getValueInt();
                }
                if (!dontPlace && randomInt <= this.placeChance.getValueInt() && dontBreak && this.clickSimulation.getValue()) {
                    int clickDelay = this.rightClickDelay.getRandomValueInt();
                    MouseSimulation.mouseClick(1, clickDelay);
                }
            }
        }
        randomInt = MathUtils.randomInt(1, 100);
        class_239 clickDelay = this.mc.field_1765;
        if (clickDelay instanceof class_3966) {
            class_3966 entityHit = (class_3966)clickDelay;
            class_1297 entity = entityHit.method_17782();
            if (this.breakAndPlace.getValue() && entity instanceof class_1511 && !dontBreak && !dontPlace && this.canPlaceCrystalAtEntity((class_1511)entity)) {
                int previousSlot = this.mc.field_1724.method_31548().method_67532();
                if (this.antiWeakness.getValue() && this.cantBreakCrystal()) {
                    InventoryUtils.selectSword();
                }
                if (this.clickSimulation.getValue()) {
                    int clickDelay2 = this.leftClickDelay.getRandomValueInt();
                    MouseSimulation.mouseClick(0, clickDelay2);
                }
                WorldUtils.hitEntity(entity, true);
                if (this.antiWeakness.getValue()) {
                    InventoryUtils.setInvSlot(previousSlot);
                }
                this.placeCrystalAtEntityPosition((class_1511)entity);
                this.breakClock = this.breakDelay.getValueInt();
                this.placeClock = this.placeDelay.getValueInt();
                return;
            }
            if (!dontBreak && randomInt <= this.breakChance.getValueInt()) {
                if (!(this.fakePunch.getValue() || entity instanceof class_1511 || entity instanceof class_1621)) {
                    return;
                }
                int previousSlot = this.mc.field_1724.method_31548().method_67532();
                if ((entity instanceof class_1511 || entity instanceof class_1621) && this.antiWeakness.getValue() && this.cantBreakCrystal()) {
                    InventoryUtils.selectSword();
                }
                if (this.clickSimulation.getValue()) {
                    int clickDelay3 = this.leftClickDelay.getRandomValueInt();
                    MouseSimulation.mouseClick(0, clickDelay3);
                }
                WorldUtils.hitEntity(entity, true);
                this.breakClock = this.breakDelay.getValueInt();
                if (this.antiWeakness.getValue()) {
                    InventoryUtils.setInvSlot(previousSlot);
                }
            }
        }
    }

    @Override
    public void onItemUse(ItemUseListener.ItemUseEvent event) {
        class_239 class_2392;
        if (this.mc.field_1724.method_6047().method_7909() == class_1802.field_8301 && (class_2392 = this.mc.field_1765) instanceof class_3965) {
            class_3965 h = (class_3965)class_2392;
            if (this.mc.field_1765.method_17783() == class_239.class_240.field_1332 && (BlockUtils.isBlock(h.method_17777(), class_2246.field_10540) || BlockUtils.isBlock(h.method_17777(), class_2246.field_9987))) {
                event.cancel();
            }
        }
    }

    private boolean cantBreakCrystal() {
        assert (this.mc.field_1724 != null);
        class_1293 weakness = this.mc.field_1724.method_6112(class_1294.field_5911);
        class_1293 strength = this.mc.field_1724.method_6112(class_1294.field_5910);
        return weakness != null && (strength == null || strength.method_5578() <= weakness.method_5578()) && !WorldUtils.isTool(this.mc.field_1724.method_6047());
    }

    private boolean damageTickCheck() {
        return this.mc.field_1687.method_18456().parallelStream().filter(e -> e != this.mc.field_1724).filter(e -> e.method_5858((class_1297)this.mc.field_1724) < 36.0).filter(e -> e.method_49107() == null).filter(e -> !e.method_24828()).anyMatch(e -> e.field_6235 >= 2) && !(this.mc.field_1724.method_6052() instanceof class_1657);
    }

    private boolean canPlaceCrystalAtEntity(class_1511 crystal) {
        class_2338 crystalPos = crystal.method_24515();
        class_2338 blockBelow = crystalPos.method_10074();
        return (BlockUtils.isBlock(blockBelow, class_2246.field_10540) || BlockUtils.isBlock(blockBelow, class_2246.field_9987)) && CrystalUtils.canPlaceCrystalClientAssumeObsidian(blockBelow);
    }

    private void placeCrystalAtEntityPosition(class_1511 crystal) {
        class_2338 crystalPos = crystal.method_24515();
        class_2338 blockBelow = crystalPos.method_10074();
        class_3965 hitResult = new class_3965(blockBelow.method_46558().method_1031(0.0, 0.5, 0.0), class_2350.field_11036, blockBelow, false);
        if (this.clickSimulation.getValue()) {
            int clickDelay = this.rightClickDelay.getRandomValueInt();
            MouseSimulation.mouseClick(1, clickDelay);
        }
        WorldUtils.placeBlock(hitResult, true);
    }

    private boolean isValidCrystalTarget() {
        class_239 target;
        class_3965 hit;
        if (this.mc.field_1765 == null) {
            return false;
        }
        class_239 class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3965) {
            hit = (class_3965)class_2392;
            if (this.mc.field_1765.method_17783() == class_239.class_240.field_1332) {
                boolean isObsidianOrBedrock = BlockUtils.isBlock(hit.method_17777(), class_2246.field_10540) || BlockUtils.isBlock(hit.method_17777(), class_2246.field_9987);
                return isObsidianOrBedrock;
            }
        }
        if ((target = this.mc.field_1765) instanceof class_3966) {
            class_3966 entityHit = (class_3966)target;
            class_1297 entity = entityHit.method_17782();
            return entity instanceof class_1511 && this.breakAndPlace.getValue();
        }
        return false;
    }
}

