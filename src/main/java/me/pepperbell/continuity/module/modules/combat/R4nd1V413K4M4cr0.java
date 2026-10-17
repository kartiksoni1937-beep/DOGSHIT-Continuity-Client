/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.HashSet;
import java.util.Set;
import me.pepperbell.continuity.event.events.ItemUseListener;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.BlockUtils;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.KeyUtils;
import me.pepperbell.continuity.utils.MathUtils;
import me.pepperbell.continuity.utils.MouseSimulation;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1819;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_3965;
import net.minecraft.class_9334;
import org.lwjgl.glfw.GLFW;

public final class R4nd1V413K4M4cr0
extends Module
implements TickListener,
ItemUseListener {
    private final BooleanSetting whileUse = (BooleanSetting)new BooleanSetting(EncryptedString.of("W*\u00ad*~h`~$i\u00ad.l~~-e^~* \u00b7\u00adU~_``s-*-e"), true).setDescription(EncryptedString.of("I-*f|`. -~~-i.~.t|`. `\u00ad_s\u00ad.~\u00b7h_.*o-`u-|l_|_d-~ `\u00ad$t|\u00b7r~--i|\u00ad\u00b7g|`|$g`$\u00ade$-.r$` _|w|\u00ad_\u00b7h~|^$i`*^l^$-e\u00b7||$ `\u00ad$-e|~_a*^-|t`\u00b7_i$_-n`^\u00b7g_$/$_u|~|-s$\u00ad-i-\u00b7n*~-$g~\u00b7\u00b7 -*_|s$$h.~i-`e~$`-l.-^d"));
    private final BooleanSetting stopOnKill = (BooleanSetting)new BooleanSetting(EncryptedString.of("S-\u00b7t$\u00b7.o_\u00b7~p^` \u00b7*o|~`\u00b7n\u00b7|$` `~_K*$i~|l`|^`l"), false).setDescription(EncryptedString.of("D`*-$o\u00ad$e|*`s_-*n`~^'\u00b7|t--.- `\u00b7a|`_\u00b7n$*c\u00b7~\u00b7h*.-o-_*~r^_ .*$~i\u00b7^f*|^. ^-^|b$`|o_-\u00ad^d~`^.y|*~| |_n$_^e|`.a\u00b7\u00ad`_r|`_b\u00ad\u00ad^y"));
    private final BooleanSetting clickSimulation = (BooleanSetting)new BooleanSetting(EncryptedString.of("C_^~\u00b7l|_i\u00ad^\u00b7$c*\u00b7|`k^\u00b7_\u00b7 `^~S-`\u00b7$i^|-*m.$_.u~.l`\u00ada~_t\u00ad~\u00ad*i.$_o.*.\u00b7n"), false).setDescription(EncryptedString.of("M**|.a\u00ad$-k$|e_`$^s~*$^ .-t*^-h_`e_^. \u00ad.C`~-P*~S~|* .^h$^^\u00adu*\u00add.\u00ad _.$t~*\u00b7\u00b7h^.i..n-_._k**^ \u00ad|y$\u00ad\u00ado_|`\u00adu\u00ad~\u00ad'.`\u00ad\u00b7r^`^\u00ade-* \u00b7-_*l._-.e|\u00adg\u00ad*i_\u00adt"));
    private final SliderSetting minCPS = (SliderSetting)new SliderSetting(EncryptedString.of("M\u00b7\u00b7i`|n|\u00b7~ \u00b7~*C\u00b7-|P|.$S"), 1.0, 20.0, 8.0, 1.0).setDescription(EncryptedString.of("M$\u00b7$i_~|n|_i_^m$~^$u$\u00b7.^m-- \u00ad|\u00adc-\u00b7l--i-$$|c`*^_k*^\u00ad*s\u00b7\u00ad^_ $$*_p*-e_``r*_ |\u00ad~`s\u00b7`~e\u00ad\u00adc._\u00ado*`*-n$_d.~- _.\u00adf~~_o$.r-`| .|~s-.\u00b7i`-_\u00b7m~$$.u-`|-l^_a`|--t~^i\u00ad~^$o`-_n"));
    private final SliderSetting maxCPS = (SliderSetting)new SliderSetting(EncryptedString.of("M\u00ad$~a\u00ad\u00b7x~^\u00b7 ^$_^C`\u00b7P~^S"), 1.0, 20.0, 12.0, 1.0).setDescription(EncryptedString.of("M_.a`\u00adx*$*_i|.-~m-^^.u`*m\u00ad\u00b7** |$$c-~l.-i*_._c.-$$k^$\u00b7_s^~ $\u00b7p|.^*e\u00ad*.r$|-^ \u00ad*^s||e$$c^.$o$~\u00adn.|-d\u00b7*\u00ad .~f`$\u00ado`~\u00ad-r.\u00b7| |-|s~\u00b7i*\u00b7*^m..\u00adu--`l*~^|a^$\u00ad.t-^.*i|.o|\u00b7n"));
    private final SliderSetting switchDelay = new SliderSetting(EncryptedString.of("S\u00b7^_`w__\u00ad|i-`._t\u00b7*.*c\u00ad~\u00b7h-_\u00ad |--D--e*\u00ad~$l_~-|a^*.y"), 0.0, 20.0, 0.0, 1.0);
    private final SliderSetting glowstoneDelay = new SliderSetting(EncryptedString.of("G_.-l*$^-o^.$~w^-\u00ad\u00ads$.\u00ad-t`\u00b7-`o~`\u00ad-n*_e-$| `*D\u00ad\u00b7-e--l.\u00b7~*a**y"), 0.0, 20.0, 0.0, 1.0);
    private final SliderSetting glowstoneChance = new SliderSetting(EncryptedString.of("G\u00ad-l\u00b7-|o\u00ad\u00ad-_w*$s|-_t~\u00ad\u00b7o\u00ad-n~^e~\u00ad*- ``\u00b7C~*$h\u00b7`\u00ad\u00b7a\u00ad*n~**$c.|-`e"), 0.0, 100.0, 100.0, 1.0);
    private final SliderSetting explodeDelay = new SliderSetting(EncryptedString.of("E_*\u00b7x`\u00ad.p_\u00ad\u00b7l~_^o~\u00ad_d.^e~| .*_D|^e.~~l-\u00b7\u00b7^a.\u00b7|y"), 0.0, 20.0, 0.0, 1.0);
    private final SliderSetting explodeChance = new SliderSetting(EncryptedString.of("E|~\u00b7x._p-|.^l^\u00b7*o``d$|.-e`\u00ad\u00b7\u00b7 *^C*`_\u00adh\u00ad^a.$\u00b7_n\u00ad$c~.e"), 0.0, 100.0, 100.0, 1.0);
    private final SliderSetting explodeSlot = new SliderSetting(EncryptedString.of("E~^x-\u00b7-p`~l$|.^o._$d|$-\u00b7e._ ^$*S^\u00b7l~~o$`t"), 1.0, 9.0, 1.0, 1.0);
    private final BooleanSetting onlyOwn = new BooleanSetting(EncryptedString.of("O^\u00ad.n$`$l\u00b7\u00b7y|\u00b7 \u00ad`O_^\u00adw\u00ad`\u00b7n"), false);
    private final BooleanSetting onlyCharge = new BooleanSetting(EncryptedString.of("O_.n~^*l$~y\u00b7\u00b7$ \u00ad-|~C`^h\u00ad..|a`*r|`g``e"), false);
    private final SliderSetting postChargeDelay = new SliderSetting(EncryptedString.of("P|\u00ad_^o-*\u00b7^s.-t-^C~|\u00adh_\u00b7\u00b7_a-$r_-g_\u00b7e\u00ad\u00b7~ |_D_~e`.`$l|*_^a`~$y"), 0.0, 20.0, 2.0, 0.5);
    private final BooleanSetting fastCharge = new BooleanSetting(EncryptedString.of("F_^$.a-\u00ad_s^*t~^ -.\u00b7\u00adC..\u00ad^h..~a.~_$r~\u00ad|g|$e"), false);
    private final BooleanSetting humanize = (BooleanSetting)new BooleanSetting(EncryptedString.of("H*\u00b7*u|^`m.$`_a._.`n||i.^z~*_e"), true).setDescription(EncryptedString.of("M--a*||k$_-`e\u00ad~~s`$\u00ad. ~~~s_^-w|^i|`$.t_~*\u00adc$$`$h.\u00b7*i\u00ad*n-\u00ad_\u00b7g|$_ |^_|l-\u00b7o.-\u00ado.-k\u00ad|-. |\u00ad_m\u00b7*\u00ado$_-.r.\u00ade|. \u00b7|h\u00b7~u.*|m~^..a*`~|n|\u00ad|l--i^\u00b7k^~e"));
    private final SliderSetting humanizeDelay = (SliderSetting)new SliderSetting(EncryptedString.of("H$^~u.*m^|_-a-\u00b7n_`i$~z.~\u00b7e\u00ad~_^ \u00ad|\u00b7D.^$e_.~l`.~a_|y"), 0.0, 5.0, 1.0, 1.0).setDescription(EncryptedString.of("R\u00ad|a.-\u00ad^n|\u00b7\u00add\u00b7\u00ad\u00ado^\u00adm\u00ad| ~-d\u00ad$_e\u00b7|\u00ad|l^~.$a\u00ad.y^__* \u00b7~`~a*^d-*.|d`|`e^\u00b7*`d$$ .*t$*\u00b7|o$~ ^`||s\u00ad^w\u00ad`\u00ad_i..t``~c--.^h*\u00ad-e--*_s~$ `\u00b7($-*0\u00ad$`|X-` \u00ad\u00adt|~-i||.c\u00b7``k$__.s`_*)"));
    private final BooleanSetting smoothSwitch = (BooleanSetting)new BooleanSetting(EncryptedString.of("S$|\u00b7~m\u00b7_|o|^o_\u00b7|t^|`h~.|` ~`S\u00ad\u00ad\u00b7~w^~i|^\u00b7.t~$c~$||h"), true).setDescription(EncryptedString.of("A..d|\u00b7_d*_^s-^_ \u00b7\u00ad\u00b7s~*__l$*\u00ad.i-`g\u00b7_h^_t|^^\u00ad \u00ad|$d.\u00b7~$e-$\u00b7*l\u00b7\u00b7a~|\u00b7$y_\u00ad\u00b7\u00b7 |_*b`\u00b7|^e\u00ad-.|t\u00ad``-w--e~`e-_\u00ad*n_$$ `*~`r.||$a\u00b7|p~\u00b7i|~\u00b7d-..\u00ad `\u00b7s-$w|\u00b7-i$~t\u00b7*c$-\u00ad-h`_e`^s"));
    private final SliderSetting smoothSwapDelay = (SliderSetting)new SliderSetting(EncryptedString.of("S*\u00ad$~m`.~o~^o..t\u00ad\u00b7\u00b7_h\u00b7$\u00b7 *^-\u00adS_\u00ad_w\u00b7*^a*$\u00b7p-` \u00ad$-~D*|e$|l~`\u00b7^a-|~-y"), 0.0, 500.0, 100.0, 10.0).setDescription(EncryptedString.of("D-\u00b7.e-~l^_`$a|`y~\u00b7 -_.`i\u00ad\u00ad-n_~~ .-m*-$*i\u00b7.l~*_l^\u00ad-i\u00ad_s~^-|e*-c$|~o~\u00b7~^n`*-~d$.-s^_^* .|\u00adb~^^e`_t^$.*w`^e\u00b7`|e.`~n\u00ad$^\u00ad ~_|r-*a$.p_~i.$_d_\u00b7~. ^~\u00b7s-$`\u00adw~|`~i*~|*t~|-*c\u00ad|h~-*e\u00b7_^s"));
    private int switchClock = 0;
    private int glowstoneClock = 0;
    private int explodeClock = 0;
    private int postChargeClock = 0;
    private boolean postChargeActive = false;
    private double postChargeAccumulator = 0.0;
    private long lastSwitchTimeMs = 0L;
    private int humanizeRandomDelay = 0;
    private int lastSlot = -1;
    private final Set<class_2338> ownedAnchors = new HashSet<class_2338>();

    public void setExplodeSlot(int slot) {
        this.explodeSlot.setValue(slot);
    }

    private boolean humanizedSlotSwitch(int targetSlot) {
        long timeSinceLastSwitch;
        if (!this.humanize.getValue()) {
            InventoryUtils.setInvSlot(targetSlot);
            return true;
        }
        if (this.smoothSwitch.getValue() && this.lastSlot != -1 && this.lastSlot != targetSlot && (timeSinceLastSwitch = System.currentTimeMillis() - this.lastSwitchTimeMs) < (long)this.smoothSwapDelay.getValueInt()) {
            return false;
        }
        if (this.humanizeRandomDelay > 0) {
            --this.humanizeRandomDelay;
            return false;
        }
        if (this.humanizeDelay.getValueInt() > 0) {
            this.humanizeRandomDelay = MathUtils.randomInt(0, this.humanizeDelay.getValueInt());
        }
        InventoryUtils.setInvSlot(targetSlot);
        this.lastSwitchTimeMs = System.currentTimeMillis();
        this.lastSlot = targetSlot;
        return true;
    }

    public R4nd1V413K4M4cr0() {
        super(EncryptedString.of("R*^|a.*^n^~|d.^\u00adi|$V_`^\u00b7a|~`l|--.e*|K\u00ad*a~.M-\u00b7~a^.~.c^\u00ad|r\u00ad.o"), EncryptedString.of("A.\u00adu$~-\u00b7t.|o$_-m.|a--~t^_i$.|^c~\u00b7*a|\u00b7l^~^.l|^\u00ad\u00b7y^-- \u00b7*C\u00b7\u00ad-^h^`a$|r\u00ad|`.g`\u00ad~e^^$^s\u00b7* ..A|\u00b7|n^*``d**$. |~E|\u00b7\u00b7x^\u00ad^\u00adp|$l*~$o`.|d\u00ad$.e`^`s._ \u00b7\u00ad||A*\u00b7`n_|`.c$-.h\u00ad|.o|_r"), -1, Category.COMBAT);
        this.setSubCategory("Crystal");
        this.addSettings(this.whileUse, this.stopOnKill, this.clickSimulation, this.minCPS, this.maxCPS, this.switchDelay, this.glowstoneDelay, this.glowstoneChance, this.explodeDelay, this.explodeChance, this.explodeSlot, this.onlyOwn, this.onlyCharge, this.postChargeDelay, this.fastCharge, this.humanize, this.humanizeDelay, this.smoothSwitch, this.smoothSwapDelay);
    }

    public SliderSetting getPostChargeDelaySetting() {
        return this.postChargeDelay;
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.eventManager.add(ItemUseListener.class, this);
        this.switchClock = 0;
        this.glowstoneClock = 0;
        this.explodeClock = 0;
        this.postChargeClock = 0;
        this.postChargeActive = false;
        this.postChargeAccumulator = 0.0;
        this.lastSwitchTimeMs = 0L;
        this.humanizeRandomDelay = 0;
        this.lastSlot = -1;
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
        class_239 class_2392;
        if (this.mc.field_1755 != null) {
            return;
        }
        if (this.mc.field_1724 == null) {
            return;
        }
        class_1799 hand = this.mc.field_1724.method_6047();
        if (hand.method_31574(class_1802.field_8281) || hand.method_31574(class_1802.field_8463) || hand.method_31574(class_1802.field_8367)) {
            return;
        }
        if (this.mc.field_1724.method_6039()) {
            return;
        }
        if (this.mc.field_1724.method_6115() && this.mc.field_1724.method_6030().method_58694(class_9334.field_50075) != null) {
            return;
        }
        if (hand.method_31574(class_1802.field_8639) || hand.method_31574(class_1802.field_8450)) {
            return;
        }
        if (!this.whileUse.getValue()) {
            boolean isBlockingWithShield;
            boolean bl = isBlockingWithShield = this.mc.field_1724.method_6115() && this.mc.field_1724.method_6030().method_7909() instanceof class_1819;
            if (isBlockingWithShield) {
                return;
            }
        }
        if ((this.mc.field_1724.method_6047().method_7909().method_57347().method_57832(class_9334.field_50075) || this.mc.field_1724.method_6047().method_7909() instanceof class_1819 || this.mc.field_1724.method_6079().method_7909() instanceof class_1819 || this.mc.field_1724.method_6079().method_7909().method_57347().method_57832(class_9334.field_50075)) && GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)1) == 1 && !this.whileUse.getValue()) {
            return;
        }
        if (this.stopOnKill.getValue() && WorldUtils.isDeadBodyNearby()) {
            return;
        }
        int randomInt = MathUtils.randomInt(1, 100);
        if (KeyUtils.isKeyPressed(1) && (class_2392 = this.mc.field_1765) instanceof class_3965 && BlockUtils.isBlock((hit = (class_3965)class_2392).method_17777(), class_2246.field_23152)) {
            if (this.onlyOwn.getValue() && !this.ownedAnchors.contains(hit.method_17777())) {
                return;
            }
            this.mc.field_1690.field_1904.method_23481(false);
            if (BlockUtils.isAnchorNotCharged(hit.method_17777())) {
                if (this.fastCharge.getValue()) {
                    if (!this.mc.field_1724.method_6047().method_31574(class_1802.field_8801)) {
                        for (int i = 0; i < 9; ++i) {
                            if (!this.mc.field_1724.method_31548().method_5438(i).method_31574(class_1802.field_8801)) continue;
                            InventoryUtils.setInvSlot(i);
                            break;
                        }
                    }
                    this.postChargeActive = true;
                    this.postChargeClock = 0;
                    this.postChargeAccumulator = 0.0;
                    if (this.clickSimulation.getValue()) {
                        MouseSimulation.mouseClick(1);
                    }
                    WorldUtils.placeBlock(hit, true);
                    return;
                }
                if (!this.mc.field_1724.method_6047().method_31574(class_1802.field_8801)) {
                    if (this.switchClock != this.switchDelay.getValueInt()) {
                        ++this.switchClock;
                        return;
                    }
                    this.switchClock = 0;
                    for (int i = 0; i < 9; ++i) {
                        if (!this.mc.field_1724.method_31548().method_5438(i).method_31574(class_1802.field_8801)) continue;
                        if (this.humanizedSlotSwitch(i)) break;
                        return;
                    }
                }
                if (this.mc.field_1724.method_6047().method_31574(class_1802.field_8801)) {
                    if (this.glowstoneClock != this.glowstoneDelay.getValueInt()) {
                        ++this.glowstoneClock;
                        return;
                    }
                    this.glowstoneClock = 0;
                    this.postChargeActive = true;
                    this.postChargeClock = 0;
                    this.postChargeAccumulator = 0.0;
                    if (this.clickSimulation.getValue()) {
                        MouseSimulation.mouseClick(1);
                    }
                    WorldUtils.placeBlock(hit, true);
                }
            }
            if (BlockUtils.isAnchorCharged(hit.method_17777())) {
                int desiredSlot;
                if (this.postChargeActive) {
                    double required = this.fastCharge.getValue() ? Math.min(this.postChargeDelay.getValue(), 1.0) : this.postChargeDelay.getValue();
                    this.postChargeAccumulator += 1.0;
                    if (this.postChargeAccumulator < required) {
                        return;
                    }
                    this.postChargeAccumulator = 0.0;
                    this.postChargeActive = false;
                }
                int targetSlot = desiredSlot = this.explodeSlot.getValueInt() - 1;
                if (this.mc.field_1724.method_31548().method_67532() != targetSlot) {
                    if (this.switchClock != this.switchDelay.getValueInt()) {
                        ++this.switchClock;
                        return;
                    }
                    this.switchClock = 0;
                    if (!this.humanizedSlotSwitch(targetSlot)) {
                        return;
                    }
                }
                if (this.mc.field_1724.method_31548().method_67532() == targetSlot) {
                    if (this.explodeClock != this.explodeDelay.getValueInt()) {
                        ++this.explodeClock;
                        return;
                    }
                    randomInt = MathUtils.randomInt(1, 100);
                    if (randomInt <= this.explodeChance.getValueInt()) {
                        this.explodeClock = 0;
                        if (!this.onlyCharge.getValue()) {
                            if (this.clickSimulation.getValue()) {
                                MouseSimulation.mouseClick(1);
                            }
                            WorldUtils.placeBlock(hit, true);
                            this.ownedAnchors.remove(hit.method_17777());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onItemUse(ItemUseListener.ItemUseEvent event) {
        class_3965 hitResult;
        class_239 class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3965 && (hitResult = (class_3965)class_2392).method_17783() == class_239.class_240.field_1332) {
            class_2338 bp;
            class_1799 hand = this.mc.field_1724.method_6047();
            if ((hand.method_31574(class_1802.field_8281) || hand.method_31574(class_1802.field_8463) || hand.method_31574(class_1802.field_8367)) && BlockUtils.isAnchorCharged(hitResult.method_17777())) {
                event.cancel();
                return;
            }
            if (this.mc.field_1724.method_6047().method_7909() == class_1802.field_23141) {
                class_2350 dir = hitResult.method_17780();
                class_2338 pos = hitResult.method_17777();
                if (!this.mc.field_1687.method_8320(pos).method_45474()) {
                    switch (dir) {
                        case field_11036: {
                            this.ownedAnchors.add(pos.method_10069(0, 1, 0));
                            break;
                        }
                        case field_11033: {
                            this.ownedAnchors.add(pos.method_10069(0, -1, 0));
                            break;
                        }
                        case field_11034: {
                            this.ownedAnchors.add(pos.method_10069(1, 0, 0));
                            break;
                        }
                        case field_11039: {
                            this.ownedAnchors.add(pos.method_10069(-1, 0, 0));
                            break;
                        }
                        case field_11043: {
                            this.ownedAnchors.add(pos.method_10069(0, 0, -1));
                            break;
                        }
                        case field_11035: {
                            this.ownedAnchors.add(pos.method_10069(0, 0, 1));
                        }
                    }
                } else {
                    this.ownedAnchors.add(pos);
                }
            }
            if (BlockUtils.isAnchorCharged(bp = hitResult.method_17777())) {
                this.ownedAnchors.remove(bp);
            }
        }
        if (this.mc.field_1724.method_6047().method_31574(class_1802.field_8801) && GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)1) != 1) {
            event.cancel();
        }
    }
}

