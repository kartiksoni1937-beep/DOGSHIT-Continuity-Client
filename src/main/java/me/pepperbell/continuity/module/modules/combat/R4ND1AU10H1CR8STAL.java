/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.AttackListener;
import me.pepperbell.continuity.event.events.ItemUseListener;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.modules.combat._4u70Cry5741;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.BlockUtils;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.KeyUtils;
import me.pepperbell.continuity.utils.MathUtils;
import me.pepperbell.continuity.utils.MouseSimulation;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_3965;
import org.lwjgl.glfw.GLFW;

public final class R4ND1AU10H1CR8STAL
extends Module
implements TickListener,
ItemUseListener,
AttackListener {
    private final KeybindSetting activateKey = (KeybindSetting)new KeybindSetting(EncryptedString.of("A~*-c~$.t**$i\u00ad.`v^.^a.|t.-^^e|\u00b7* `^K~|e..|-y"), 1, false).setDescription(EncryptedString.of("K$*e$_y\u00b7$ |\u00ad-t^|h-_\u00ad~a~\u00b7t|~ -~`-d_\u00ado|_^^e\u00ad||s.^ _|.h$.\u00b7-i\u00ad-t_$ \u00ad`\u00b7|c|~r.$\u00ad_y.-*s`-$t--*a^\u00adl-`l`\u00adi*\u00ad_`n_**g"));
    private final BooleanSetting checkPlace = (BooleanSetting)new BooleanSetting(EncryptedString.of("C||\u00b7~h`...e^\u00b7\u00b7~c-\u00b7._k\u00ad\u00b7- \u00b7-$P\u00ad-\u00ad*l\u00ad`a^\u00ad_c~|e"), false).setDescription(EncryptedString.of("C.*~h*\u00ad~e\u00ad-c*``|k\u00b7$^s_*|\u00b7 $|i**~ $.f\u00b7-y._|\u00b7o.$u~* .`\u00adc--a_*$n-`~. -`.p|-l*`\u00b7|a|.\u00b7c\u00b7.e~.`. *\u00b7-$t._h_\u00ad-e\u00ad^ \u00ad^*_o|--.b`$s-^i\u00b7|.d-`i|^-a~_n`$\u00b7 ^~-`o\u00b7\u00ad$n.\u00ad .$-t*_h`~\u00b7a^|\u00ad_t~~ \u00b7_b.`~l\u00ad\u00ad~\u00ado*.c.`k"));
    private final SliderSetting obsidianSwapDelay = (SliderSetting)new SliderSetting(EncryptedString.of("O$*b*_\u00b7s*._i_\u00ad$_d^.i$$_a_`n^-` `-S._\u00adw`|\u00b7\u00b7a$-p\u00ad\u00ad --_`D`|`~e|.*.l\u00b7|a~~`y"), 0.0, 20.0, 0.0, 1.0).setDescription(EncryptedString.of("T`|_`i\u00ad.c\u00ad-_`k-$s.\u00ad*$ $-^t_-`o~_* ^$w|*a**i-\u00ad$~t^*\u00ad `_^`b-.e|*.t\u00b7|`w`_e-~*e\u00b7~\u00b7n~\u00b7 \u00b7-s|\u00b7w_^a^_`p\u00ad||-p*$~~i.\u00b7\u00b7~n^_-g`-~ *_t`^~`o`$ `\u00b7*o\u00ad^.b$*s$-$i\u00ad`d*\u00ad\u00adi``~a~.**n"));
    private final SliderSetting obsidianPlaceDelay = (SliderSetting)new SliderSetting(EncryptedString.of("O^*|b\u00b7`.-s.`|i^|\u00ad$d|*$$i*\u00ada~^n$| |-_\u00adP\u00ad*l~\u00ad`_a|*c$$\u00ade_`*` __~*D_|`e\u00ad~l-|.~a$\u00b7\u00ady"), 0.0, 20.0, 0.0, 1.0).setDescription(EncryptedString.of("T\u00ad~\u00ad_i\u00b7\u00b7\u00adc*$k^\u00ads\u00ad^|^ \u00b7^`~t^*o.*~* *^.\u00adw_^a||i$.*-t--\u00b7\u00b7 \u00ad$|_b--e*$t__\u00adw---\u00ade\u00b7.*.e.~n-`.. _\u00ad_p.$-_l~-\u00ad`a|\u00b7c_^i*^`.n\u00ad\u00ad*g^\u00b7. ~\u00b7o~*b`\u00b7s*-$i*_^d\u00b7.*i~*`a^\u00b7$|n"));
    private final SliderSetting crystalSwapDelay = (SliderSetting)new SliderSetting(EncryptedString.of("C$_-r~^\u00ad_y~|~*s_*t\u00b7$a$|*l$-^ ^-S$_\u00b7\u00b7w.\u00ada^\u00b7-p|$`` ^-D\u00ad\u00b7\u00ad\u00ade*^*l$_a^~y"), 0.0, 20.0, 0.0, 1.0).setDescription(EncryptedString.of("T_\u00adi.|`c\u00ad~`k*-~\u00b7s\u00b7_\u00ad \u00ad*t-$o^-$ \u00b7..`w.*a|-$i__t.-* ^~~b^`.\u00ade||t\u00b7$|w._\u00b7e\u00ad^-e-_n$\u00b7 .\u00ad^|s-``w-~$a.\u00ad|`p|\u00ad$-p*~|`i^`~\u00b7n|.^_g-*^ ^~`t~~.^o`\u00ad|` -^c\u00b7`~r^~y$\u00ad\u00ad`s~\u00adt``\u00ad.a$^-l"));
    private final SliderSetting crystalPlaceDelay = (SliderSetting)new SliderSetting(EncryptedString.of("C\u00b7_$-r^\u00ady|$s^.|t*$-^a^_|^l|` *$P|*|l\u00b7$a$_|c~_-e~* $`$^D_$_e.\u00ad|$l`$*a$\u00b7|_y"), 0.0, 20.0, 0.0, 1.0).setDescription(EncryptedString.of("T-\u00ad_i_^|c_\u00b7k.*__s~\u00ad._ _^\u00b7.t\u00b7\u00ado$- ^.^~w_`$`a^\u00ad$i`_-t\u00b7|~- ^*~b|$\u00b7e`$\u00b7*t*~\u00ad_w_`\u00b7|e\u00ad$e`-.n\u00ad` \u00ad-`p`-|$l|\u00b7a*-c_$-i^\u00ad$^n\u00ad-\u00adg..|. _*\u00b7c_~_`r$-.`y..^s.\u00adt-$\u00ad`a*\u00b7l"));
    private final BooleanSetting smoothSwitch = (BooleanSetting)new BooleanSetting(EncryptedString.of("S$_\u00adm$~$o^$o.*|t$`$h\u00ad\u00b7~~ ~\u00b7_`S\u00b7\u00b7_w*.i*-*.t$`c\u00ad_h"), true).setDescription(EncryptedString.of("A|`$|d$*d^`\u00ads\u00b7^^` \u00ad|\u00ad-s*|^l_\u00ad$^i$\u00b7..g`|-h`-t-|*| ^|\u00ad*d*-~$e$$l~`a.-^`y|^. \u00ad\u00b7\u00b7b\u00b7|$`e_\u00b7*t|.$*w*\u00ad$^e\u00ad.\u00b7$e^-$\u00b7n-~_- _-r`\u00b7..a_-|.p\u00b7`|_i``^d\u00ad\u00b7 |_.^s.-w\u00b7|i*\u00ad|^t\u00b7||_c*|h~`e\u00ad-~$s"));
    private final SliderSetting smoothSwapDelay = (SliderSetting)new SliderSetting(EncryptedString.of("S\u00b7-m.$|^o_`-o\u00b7|t_-\u00adh*^`\u00b7 ~$\u00adS|-w*_*a^-$*p~` \u00ad.-D~$~_e--\u00b7l*$~~a`^-y"), 0.0, 500.0, 100.0, 10.0).setDescription(EncryptedString.of("D_$-e^\u00b7l$_a|-_`y^- |\u00adi$^n_* \u00ad\u00b7m~^_i\u00b7$``l\u00ad~-$l\u00b7-i_|||s.-e.|\u00b7c*_o~_n_`d-\u00b7s\u00ad~$* `^*-b~..e~|t\u00ad_w^^\u00ad-e~\u00ad\u00b7e^$`n*_|^ -.r*$a~$p^\u00b7--i^`d$$`\u00ad |\u00ad*s|-$~w$-~`i|$t`.c..h^^~e^.s"));
    private final int switchChance = 100;
    private final int placeChance = 100;
    private final BooleanSetting workWithTotem = new BooleanSetting(EncryptedString.of("W~-~o*~.\u00adr$\u00ad*`k\u00ad`~| *`W`~~$i-~\u00b7-t\u00b7.h_-^- _|T^.o\u00ad|\u00adt*-\u00b7\u00ade$|m"), false);
    private final BooleanSetting workWithCrystal = new BooleanSetting(EncryptedString.of("W.`o~|\u00b7r^$k.`$ `^~W|-i^$^t$`h|.$$ _`$C\u00ad|r|\u00ady*^_^s|^t.$`a*\u00b7|^l"), false);
    private final BooleanSetting clickSimulation = (BooleanSetting)new BooleanSetting(EncryptedString.of("C|^_l\u00ad\u00ad`i\u00b7$c\u00ad|__k.^^ *\u00b7-S.$i*_.|m$.u^~.-l`_a_~$t-\u00b7$`i\u00ad$o_`.\u00adn"), false).setDescription(EncryptedString.of("M$\u00b7.a*-.k*-~e\u00b7~s_\u00ad^* |\u00b7|\u00adt~^*h.`e\u00b7|\u00b7~ $*_|C`.`-P$_S-^` \u00ad..h^`\u00adu..$_d^.`* ^\u00b7~*t\u00ad|\u00ad.h~\u00b7i_|n$`\u00adk_\u00b7.\u00ad \u00ad-_y**o^~^u$*^^'-.^r-`e_\u00b7\u00b7 \u00ad$`l`^e__~-g_\u00b7i~|*t"));
    private final BooleanSetting swordSwap = new BooleanSetting(EncryptedString.of("S\u00ad\u00ad|w\u00b7-o`|r\u00b7\u00b7d*^* \u00b7~|-S|-\u00ad^w*`-a_`.p"), true);
    private final BooleanSetting useBackPlace = (BooleanSetting)new BooleanSetting(EncryptedString.of("B^$a$_c.-..k**P*^l$*a^^`|c*_e.^ $.\u00b7-I`$n|\u00b7\u00adt_`~e$-~_g^\u00b7*r`\u00b7\u00ad`a~^_^t\u00ad.i`\u00b7\u00b7o$.n"), false).setDescription(EncryptedString.of("U|-^s\u00ad~-e^_\u00b7_ .\u00ad~B\u00ad`\u00b7a.**|c.~\u00b7k~~-\u00b7P\u00b7_l._\u00ad`a-\u00ad-`c^_-\u00ade`\u00ad^ ~`$m\u00ad`o$~d\u00ad~^^u|.l*.-~e_~ ^\u00adt-\u00ad-^o|^` `\u00ad.p\u00ad\u00b7$*l^$a$~~c\u00ad_$e|- ^^c\u00b7_r|*y-_`_s`^_t|\u00ad*~a_^\u00adl_\u00b7\u00ad$s_$* |\u00b7|\u00b7t^^$|h^\u00adr^`^o..u\u00b7-`g~\u00ad^`h~.$| |-`e^`n~-\u00adt\u00b7\u00b7*i$^t\u00ad|i\u00b7*-`e.|s"));
    private int placeClock = 0;
    private int switchClock = 0;
    private boolean active;
    private boolean crystalling;
    private boolean crystalSelected;
    private boolean obsidianPlaced = false;
    private class_2338 lastPlacedPos = null;
    private boolean hasPlacedInCurrentCycle = false;
    private long lastSwitchTimeMs = 0L;
    private int lastSlot = -1;

    public R4ND1AU10H1CR8STAL() {
        super(EncryptedString.of("R$*a\u00b7|~n*^$\u00b7d$*~i.~\u00b7*K-^~a\u00b7~ _*H.``^i*`t_^- \u00ad~C-$^r\u00b7\u00ad**y-~*s_~^*t^`a~\u00b7l"), EncryptedString.of("A-\u00adu$-t`\u00b7\u00ad~o-$m|~\u00b7`a$`t*~i|$c`|-\u00b7a-$^`l|-.\u00adl\u00ad.$\u00b7y.\u00ad^| -\u00b7h^^|~i_\u00ad~t~.c~`.^r\u00b7*y*|$$s-~t|~*.a|*l^|`^s.~\u00b7 -.|.f*.`o$^.-r-. _~.`y\u00b7|o^$_~u"), -1, Category.COMBAT);
        this.setSubCategory("Crystal");
        this.addSettings(this.activateKey, this.checkPlace, this.obsidianSwapDelay, this.obsidianPlaceDelay, this.crystalSwapDelay, this.crystalPlaceDelay, this.smoothSwitch, this.smoothSwapDelay, this.workWithTotem, this.workWithCrystal, this.clickSimulation, this.swordSwap);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.eventManager.add(ItemUseListener.class, this);
        this.eventManager.add(AttackListener.class, this);
        this.reset();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.eventManager.remove(ItemUseListener.class, this);
        this.eventManager.remove(AttackListener.class, this);
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1755 != null) {
            return;
        }
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (KeyUtils.isKeyPressed(this.activateKey.getKey())) {
            class_239 hit;
            class_1799 mainHandStack;
            boolean isValidItem;
            class_239 class_2392 = this.mc.field_1765;
            if (class_2392 instanceof class_3965) {
                class_3965 hitResult = (class_3965)class_2392;
                if (this.mc.field_1765.method_17783() == class_239.class_240.field_1332 && !this.active && !BlockUtils.canPlaceBlockClient(hitResult.method_17777()) && this.checkPlace.getValue()) {
                    return;
                }
            }
            boolean bl = isValidItem = WorldUtils.isSword(mainHandStack = this.mc.field_1724.method_6047()) || this.workWithTotem.getValue() && mainHandStack.method_31574(class_1802.field_8288) || this.workWithCrystal.getValue() && mainHandStack.method_31574(class_1802.field_8301);
            if (!isValidItem && !this.active) {
                return;
            }
            class_239 class_2393 = this.mc.field_1765;
            if (class_2393 instanceof class_3965) {
                class_3965 hitResult = (class_3965)class_2393;
                if (!this.active && this.swordSwap.getValue() && this.mc.field_1765.method_17783() == class_239.class_240.field_1332) {
                    class_2248 block = this.mc.field_1687.method_8320(hitResult.method_17777()).method_26204();
                    this.crystalling = block == class_2246.field_10540 || block == class_2246.field_9987;
                }
            }
            this.active = true;
            if (!this.crystalling && (hit = this.mc.field_1765) instanceof class_3965) {
                class_3965 blockHit = (class_3965)hit;
                if (blockHit.method_17783() == class_239.class_240.field_1333) {
                    return;
                }
                class_2338 targetPos = blockHit.method_17777();
                if (this.obsidianPlaced && this.lastPlacedPos != null && this.lastPlacedPos.equals((Object)targetPos)) {
                    this.crystalling = true;
                    return;
                }
                if (!BlockUtils.isBlock(targetPos, class_2246.field_10540)) {
                    if (BlockUtils.isBlock(targetPos, class_2246.field_23152) && BlockUtils.isAnchorCharged(targetPos)) {
                        return;
                    }
                    this.mc.field_1690.field_1904.method_23481(false);
                    if (this.hasPlacedInCurrentCycle) {
                        return;
                    }
                    if (!this.mc.field_1724.method_24518(class_1802.field_8281)) {
                        if (this.switchClock > 0) {
                            --this.switchClock;
                            return;
                        }
                        if (MathUtils.randomInt(1, 100) <= 100) {
                            this.switchClock = this.obsidianSwapDelay.getValueInt();
                            if (!this.smoothSwitchToItem(class_1802.field_8281)) {
                                return;
                            }
                        }
                    }
                    if (this.mc.field_1724.method_24518(class_1802.field_8281)) {
                        if (this.placeClock > 0) {
                            --this.placeClock;
                            return;
                        }
                        if (this.clickSimulation.getValue()) {
                            MouseSimulation.mouseClick(1);
                        }
                        if (MathUtils.randomInt(1, 100) <= 100) {
                            WorldUtils.placeBlock(blockHit, true);
                            this.placeClock = this.obsidianPlaceDelay.getValueInt();
                            this.crystalling = true;
                            this.obsidianPlaced = true;
                            this.hasPlacedInCurrentCycle = true;
                            this.lastPlacedPos = targetPos;
                        }
                    }
                } else {
                    this.crystalling = true;
                    this.obsidianPlaced = true;
                    this.lastPlacedPos = targetPos;
                }
            }
            if (this.crystalling) {
                _4u70Cry5741 autoCrystal;
                if (!this.mc.field_1724.method_24518(class_1802.field_8301) && !this.crystalSelected) {
                    if (this.switchClock > 0) {
                        --this.switchClock;
                        return;
                    }
                    if (MathUtils.randomInt(1, 100) <= 100) {
                        this.switchClock = this.crystalSwapDelay.getValueInt();
                        this.crystalSelected = this.smoothSwitchToItem(class_1802.field_8301);
                        if (!this.crystalSelected) {
                            return;
                        }
                    }
                }
                if (this.mc.field_1724.method_24518(class_1802.field_8301) && (autoCrystal = Sweto.INSTANCE.getModuleManager().getModule(_4u70Cry5741.class)) != null && !autoCrystal.isEnabled()) {
                    autoCrystal.tick();
                }
            }
        } else {
            this.reset();
        }
    }

    private boolean smoothSwitchToItem(class_1792 item) {
        long timeSinceLast;
        int targetSlot = -1;
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = this.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960() || stack.method_7909() != item) continue;
            targetSlot = i;
            break;
        }
        if (targetSlot == -1) {
            return false;
        }
        if (this.mc.field_1724.method_31548().method_67532() == targetSlot) {
            return true;
        }
        if (this.smoothSwitch.getValue() && this.lastSlot != -1 && this.lastSlot != targetSlot && (timeSinceLast = System.currentTimeMillis() - this.lastSwitchTimeMs) < (long)this.smoothSwapDelay.getValueInt()) {
            return false;
        }
        InventoryUtils.setInvSlot(targetSlot);
        this.lastSwitchTimeMs = System.currentTimeMillis();
        this.lastSlot = targetSlot;
        return true;
    }

    @Override
    public void onItemUse(ItemUseListener.ItemUseEvent event) {
        class_1799 mainHandStack = this.mc.field_1724.method_6047();
        if ((mainHandStack.method_31574(class_1802.field_8301) || mainHandStack.method_31574(class_1802.field_8281)) && GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)1) != 1) {
            event.cancel();
        }
    }

    public void reset() {
        this.placeClock = this.obsidianPlaceDelay.getValueInt();
        this.switchClock = this.obsidianSwapDelay.getValueInt();
        this.active = false;
        this.crystalling = false;
        this.crystalSelected = false;
        this.obsidianPlaced = false;
        this.lastPlacedPos = null;
        this.lastSwitchTimeMs = 0L;
        this.lastSlot = -1;
        this.hasPlacedInCurrentCycle = false;
    }

    @Override
    public void onAttack(AttackListener.AttackEvent event) {
        if (this.mc.field_1724.method_6047().method_31574(class_1802.field_8301) && GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)0) != 1) {
            event.cancel();
        }
    }
}

