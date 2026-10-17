/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.HashSet;
import java.util.Set;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.ItemUseListener;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.modules.combat._54f34nch0r;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.BlockUtils;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
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

public final class _4nch0rM4cr0
extends Module
implements TickListener,
ItemUseListener {
    private final BooleanSetting whileUse = (BooleanSetting)new BooleanSetting(EncryptedString.of("W_$|.h.-i~\u00b7$`l\u00b7~e_$ _\u00b7`-U$\u00ads^`e"), true).setDescription(EncryptedString.of("I|^`-f^_ _.i\u00b7~t^_~ *|s`\u00ad~^h\u00b7\u00ad_*o\u00ad*$|u^.l$$$`d^-\u00ad -~t__*-r`*-$i.*\u00adg`|^g^*_e\u00b7*.~r_\u00ad~^ _^w~-|`h.*.|i^|_.l`|e\u00ad*$ \u00b7$*\u00b7e||a|_t*-..i$^n||_g\u00ad.-/._-u$\u00b7*s-.|^i..^n_$g\u00b7_ `_~s|~_`h-\u00ad\u00adi$.e\u00b7\u00ad^|l-*\u00add"));
    private final BooleanSetting stopOnKill = (BooleanSetting)new BooleanSetting(EncryptedString.of("S~^$$t^~\u00ado^`$`p-~~ .\u00b7`^o$|\u00adn``\u00b7\u00b7 .^_`K*~`$i`--\u00b7l^.\u00ad_l"), false).setDescription(EncryptedString.of("D`*-o$*`e~|-_s*`$n\u00ad|_'`~t.*| |_*~a|~\u00b7*n\u00ad.\u00b7\u00b7c\u00ad`h|.o_^**r$$\u00ad| `^\u00b7_i||`f^$~ ..b^|^`o-_$d~|\u00ady_`.. ~^n_.e^^a*\u00ad_|r\u00b7\u00adb|_y"));
    private final BooleanSetting fastCharge = (BooleanSetting)new BooleanSetting(EncryptedString.of("F\u00ad.`a...s`^~t`. -`\u00adC-~h$_a_|~|r\u00b7`g\u00ad\u00b7e"), false).setDescription(EncryptedString.of("I$`n~~s\u00b7_t.`.|a~.|~n-\u00b7t\u00ad.\u00ad \u00ad\u00b7\u00ad$g_.$l~*o\u00b7~\u00adw.^_$s\u00b7\u00ad|`t\u00b7~$o-$n$*^_e$--- --||p`^$l*_|_a`~*~c\u00b7_i~_.~n_.\u00b7g"));
    private final SliderSetting fastChargeDebounce = (SliderSetting)new SliderSetting(EncryptedString.of("F^$\u00ada\u00b7$_s.``$t\u00b7~- ~``|C~_\u00ad\u00adh*~|`a^.|r`~\u00adg`_e.`*\u00ad ~\u00b7*D*^^*e--b|\u00b7|o^.u.$`n_`c*.e"), 0.0, 500.0, 50.0, 1.0).setDescription(EncryptedString.of("D\u00ad*~~e*^~|l_^.a~_^y-.$$ ^`^i-_\u00b7\u00b7n\u00b7$ $*.|m_`-~s-\u00b7~ ~\u00ad_a`\u00ad\u00ad\u00adf\u00ad|-.t~^`\u00ade$|`\u00adr`~_ \u00ad-`p~$l_.a~\u00b7^$c\u00b7|\u00b7i\u00ad```n-.`*g.\u00ad `-*|a\u00b7\u00ad^-n~.c\u00b7-$.h\u00ad-o\u00b7\u00b7_^r-^^ ^$.b|`e`\u00b7.`f*\u00b7o\u00b7~^r|\u00b7e~$- _-\u00b7g\u00b7`$*l^_-o.^w\u00ad-s$~-t\u00ad~\u00ado~^|n_$\u00ad_e\u00ad\u00b7 \u00b7^\u00ad\u00b7p^~-l-|`a`\u00ad^~c|$`$e~$|\u00b7m*.e.-n\u00b7$\u00adt"));
    private final BooleanSetting clickSimulation = (BooleanSetting)new BooleanSetting(EncryptedString.of("C\u00b7_-*l\u00ad\u00b7|$i^*|\u00b7c~\u00b7~.k`|.* \u00b7\u00ad|.S-\u00adi|*m~-\u00b7-u.`l_^._a$~t\u00ad*^i^\u00ad\u00b7-o-_*^n"), false).setDescription(EncryptedString.of("M--a_\u00b7`-k*~\u00ad*e|.`s*\u00b7_- `^\u00b7t~\u00b7h*\u00ad|_e.` ._C$-P|_-~S`\u00ad| $`\u00b7h$\u00adu`~d..| ~^t\u00b7._\u00adh*`i^\u00b7n`|*.k*.| *-y*^\u00ad*o-^$_u*`$\u00ad'*--r^\u00b7e$*\u00ad~ \u00ad-l~|.e`.\u00adg.~i--t"));
    private final MinMaxSetting clickSimulationCps = new MinMaxSetting(EncryptedString.of("C|**l\u00ad.i__\u00b7.c.^~k`$~ ~_-_S~|_~i|*--m.``-u|_l~~a\u00b7\u00ad\u00b7t$`$i.|*\u00b7o\u00ad^n\u00b7\u00ad- .^_`C^~|\u00adP`*S"), 1.0, 20.0, 1.0, 6.0, 9.0);
    private final SliderSetting switchDelay = new SliderSetting(EncryptedString.of("S*.w`\u00b7\u00b7i\u00ad-\u00adt$`\u00adc\u00ad`h^\u00b7_^ |_D-|^e*-\u00b7l.\u00ad^a._y"), 0.0, 20.0, 0.0, 1.0);
    private final SliderSetting glowstoneDelay = new SliderSetting(EncryptedString.of("G`_~-l|\u00b7^o**-w\u00b7^s|_~^t*\u00b7|$o_-\u00ad|n.\u00ad^_e.^ *^D.\u00b7|\u00ade~..l^`a.*_y"), 0.0, 20.0, 0.0, 1.0);
    private final SliderSetting explodeDelay = new SliderSetting(EncryptedString.of("E$|x\u00b7^p~.l\u00ad_o*|d_-\u00ad.e.~` \u00ad$.\u00b7D|_\u00ade^\u00adl|`a|^^~y"), 0.0, 20.0, 0.0, 1.0);
    private final SliderSetting explodeSlot = new SliderSetting(EncryptedString.of("E*.x`|-\u00adp$.*l\u00b7^o~`d~|\u00ade_|~$ \u00ad*$\u00b7S`|\u00adl.$\u00ad|o^`t"), 1.0, 9.0, 1.0, 1.0);
    private final BooleanSetting onlyOwn = new BooleanSetting(EncryptedString.of("O\u00ad_~n-~l\u00ad|\u00ady^\u00ad~ -~|~O.~*w|*n"), false);
    private final BooleanSetting onlyCharge = new BooleanSetting(EncryptedString.of("O_\u00ad\u00b7*n_-l*-*\u00b7y-.~ __*_C|^h_.a$|$*r||`_g*|e"), false);
    private final BooleanSetting tickWait = (BooleanSetting)new BooleanSetting(EncryptedString.of("T.^|\u00adi.~.c~~k_$ .*W-\u00ad|$a\u00ad-.i|.|\u00b7t"), false).setDescription(EncryptedString.of("A-\u00b7$d\u00ad`|_d..\u00ads$| _.$m\u00ad*_~s$\u00ad *\u00b7d-**e\u00b7\u00adl-*a_*$y_$- $^b\u00ad-e\u00ad-t\u00b7-w~|e.^e\u00b7~`n_.` |$_`e|~*a*`c^*^~h`_|\u00b7 --`.s~*|w\u00b7^a\u00b7__p"));
    private final SliderSetting tickWaitDelay = new SliderSetting(EncryptedString.of("T~*||i\u00ad\u00b7-c-`k-`~| \u00ad\u00b7\u00adW^-$a.*~i\u00b7-t-|_` |~D`_\u00b7*e|$|l\u00b7.a`~*y"), 0.0, 500.0, 50.0, 1.0);
    private int switchClock = 0;
    private int glowstoneClock = 0;
    private int explodeSwitchClock = 0;
    private int explodeClock = 0;
    private long lastClickSimulationTime = 0L;
    private long lastActionTime = 0L;
    private long fastChargeDebounceEnd = 0L;
    private final Set<class_2338> ownedAnchors = new HashSet<class_2338>();
    private final Set<class_2338> justChargedPositions = new HashSet<class_2338>();
    private class_2338 processingAnchorPos = null;
    private class_2338 lastChargedAnchorPos = null;

    public _4nch0rM4cr0() {
        super(EncryptedString.of("A\u00b7`|n-^c|`h-`o^~-r~\u00b7_` $^M^\u00ada^-~*c-\u00adr_~~o"), EncryptedString.of("A\u00b7_u-~t\u00ad\u00ado\u00b7\u00adm^`^a_-t^$$i`^^c|\u00b7_-a_~`^l_\u00adl$.y^-\u00b7- ~^-b-.l|*._o^~w\u00b7_\u00b7`s*_^| *|~*u|$p___ ~~r^$.e$_s`__p^\u00b7\u00ada$_\u00adw.~__n`$-` |.$^a^$n_\u00ad`c.~|_h~`o_`$r||*`s-~ _\u00adf-`o**~$r_\u00ad$. \u00ad\u00ad|_y.~*~o|*$|u"), -1, Category.COMBAT);
        this.addSettings(this.whileUse, this.stopOnKill, this.fastCharge, this.fastChargeDebounce, this.clickSimulation, this.clickSimulationCps, this.switchDelay, this.glowstoneDelay, this.explodeDelay, this.explodeSlot, this.onlyOwn, this.onlyCharge, this.tickWait, this.tickWaitDelay);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.eventManager.add(ItemUseListener.class, this);
        this.switchClock = 0;
        this.glowstoneClock = 0;
        this.explodeSwitchClock = 0;
        this.explodeClock = 0;
        this.lastClickSimulationTime = 0L;
        this.lastActionTime = 0L;
        this.lastChargedAnchorPos = null;
        this.justChargedPositions.clear();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.eventManager.remove(ItemUseListener.class, this);
        this.lastChargedAnchorPos = null;
        this.processingAnchorPos = null;
        this.justChargedPositions.clear();
        super.onDisable();
    }

    public class_2338 getLastChargedAnchorPos() {
        return this.lastChargedAnchorPos;
    }

    private void simulateClick() {
        if (!this.clickSimulation.getValue()) {
            return;
        }
        int targetCps = this.clickSimulationCps.getRandomValueInt();
        long interval = 1000 / Math.max(1, targetCps);
        long now = System.currentTimeMillis();
        if (now - this.lastClickSimulationTime >= interval) {
            this.lastClickSimulationTime = now;
            MouseSimulation.mouseClick(1);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onTick() {
        boolean inSlot;
        boolean rightHeld;
        if (this.mc.field_1755 != null) return;
        if (this.mc.field_1724 == null) {
            return;
        }
        class_1799 hand = this.mc.field_1724.method_6047();
        if (hand.method_31574(class_1802.field_8281)) return;
        if (hand.method_31574(class_1802.field_8463)) return;
        if (hand.method_31574(class_1802.field_8367)) {
            return;
        }
        int rightBtn = 1;
        boolean bl = rightHeld = GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)rightBtn) == 1;
        if (this.whileUse.getValue() && rightHeld) {
            if (hand.method_7909().method_57347().method_57832(class_9334.field_50075)) return;
            if (hand.method_7909() instanceof class_1819) return;
            if (this.mc.field_1724.method_6079().method_7909() instanceof class_1819) return;
            if (this.mc.field_1724.method_6079().method_7909().method_57347().method_57832(class_9334.field_50075)) {
                return;
            }
        }
        if (this.stopOnKill.getValue() && WorldUtils.isDeadBodyNearby()) {
            return;
        }
        if (this.mc.field_1724.method_6039()) {
            return;
        }
        if (this.mc.field_1724.method_6115() && this.mc.field_1724.method_6030().method_58694(class_9334.field_50075) != null) {
            return;
        }
        if (hand.method_31574(class_1802.field_8639)) return;
        if (hand.method_31574(class_1802.field_8450)) {
            return;
        }
        if (this.tickWait.getValue() && System.currentTimeMillis() - this.lastActionTime < (long)this.tickWaitDelay.getValueInt()) {
            return;
        }
        if (!rightHeld) {
            if (this.processingAnchorPos == null) return;
            this.lastChargedAnchorPos = null;
            this.processingAnchorPos = null;
            return;
        }
        class_239 class_2392 = this.mc.field_1765;
        if (!(class_2392 instanceof class_3965)) return;
        class_3965 hit = (class_3965)class_2392;
        if (!BlockUtils.isBlock(hit.method_17777(), class_2246.field_23152)) {
            return;
        }
        if (this.onlyOwn.getValue() && !this.ownedAnchors.contains(hit.method_17777())) {
            return;
        }
        if (this.processingAnchorPos != null && !hit.method_17777().equals((Object)this.processingAnchorPos)) {
            this.lastChargedAnchorPos = null;
            this.processingAnchorPos = null;
            this.explodeSwitchClock = 0;
            this.explodeClock = 0;
        }
        this.justChargedPositions.clear();
        this.mc.field_1690.field_1904.method_23481(false);
        if (BlockUtils.isAnchorNotCharged(hit.method_17777())) {
            boolean holdingGlowstone = this.mc.field_1724.method_6047().method_31574(class_1802.field_8801);
            if (this.fastCharge.getValue()) {
                if (System.currentTimeMillis() >= this.fastChargeDebounceEnd) {
                    if (!holdingGlowstone) {
                        InventoryUtils.selectItemFromHotbar(class_1802.field_8801);
                    }
                    this.simulateClick();
                    WorldUtils.placeBlock(hit, true);
                    this.fastChargeDebounceEnd = System.currentTimeMillis() + (long)this.fastChargeDebounce.getValueInt();
                    this.justChargedPositions.add(hit.method_17777());
                    this.processingAnchorPos = hit.method_17777();
                }
            } else if (!holdingGlowstone) {
                if (this.switchDelay.getValueInt() == 0) {
                    InventoryUtils.selectItemFromHotbar(class_1802.field_8801);
                    this.lastActionTime = System.currentTimeMillis();
                    this.simulateClick();
                    WorldUtils.placeBlock(hit, true);
                    this.lastActionTime = System.currentTimeMillis();
                    this.justChargedPositions.add(hit.method_17777());
                    this.processingAnchorPos = hit.method_17777();
                } else {
                    if (this.switchClock < this.switchDelay.getValueInt()) {
                        ++this.switchClock;
                        return;
                    }
                    this.switchClock = 0;
                    InventoryUtils.selectItemFromHotbar(class_1802.field_8801);
                    this.lastActionTime = System.currentTimeMillis();
                }
            } else if (this.glowstoneDelay.getValueInt() == 0) {
                this.glowstoneClock = 0;
                this.simulateClick();
                WorldUtils.placeBlock(hit, true);
                this.lastActionTime = System.currentTimeMillis();
                this.justChargedPositions.add(hit.method_17777());
                this.processingAnchorPos = hit.method_17777();
            } else {
                if (this.glowstoneClock < this.glowstoneDelay.getValueInt()) {
                    ++this.glowstoneClock;
                    return;
                }
                this.glowstoneClock = 0;
                this.simulateClick();
                WorldUtils.placeBlock(hit, true);
                this.lastActionTime = System.currentTimeMillis();
                this.justChargedPositions.add(hit.method_17777());
                this.processingAnchorPos = hit.method_17777();
            }
        }
        if (!BlockUtils.isAnchorCharged(hit.method_17777())) {
            this.lastChargedAnchorPos = null;
            return;
        }
        if (this.justChargedPositions.contains(hit.method_17777())) {
            return;
        }
        this.processingAnchorPos = hit.method_17777();
        this.lastChargedAnchorPos = hit.method_17777();
        _54f34nch0r _54f34nch0r2 = Sweto.INSTANCE.moduleManager.getModule(_54f34nch0r.class);
        if (_54f34nch0r2 != null && !_54f34nch0r2.isBusyWith(hit.method_17777())) {
            _54f34nch0r2.notifyAnchorCharged(hit.method_17777());
        }
        if (_54f34nch0r2 != null && _54f34nch0r2.isBusyWith(hit.method_17777())) {
            this.explodeSwitchClock = 0;
            this.explodeClock = 0;
            return;
        }
        int slot = this.explodeSlot.getValueInt() - 1;
        boolean bl2 = inSlot = this.mc.field_1724.method_31548().method_67532() == slot;
        if (!inSlot && this.switchDelay.getValueInt() == 0) {
            this.mc.field_1724.method_31548().method_61496(slot);
            this.lastActionTime = System.currentTimeMillis();
            inSlot = true;
        } else if (!inSlot && this.explodeSwitchClock >= this.switchDelay.getValueInt()) {
            this.explodeSwitchClock = 0;
            this.mc.field_1724.method_31548().method_61496(slot);
            this.lastActionTime = System.currentTimeMillis();
            inSlot = true;
        } else if (!inSlot) {
            ++this.explodeSwitchClock;
            return;
        }
        if (inSlot && this.explodeDelay.getValueInt() == 0 && !this.onlyCharge.getValue()) {
            this.explodeClock = 0;
            this.simulateClick();
            WorldUtils.placeBlock(hit, true);
            this.lastActionTime = System.currentTimeMillis();
            this.ownedAnchors.remove(hit.method_17777());
            this.lastChargedAnchorPos = null;
            this.processingAnchorPos = null;
            return;
        }
        if (inSlot && this.explodeClock >= this.explodeDelay.getValueInt()) {
            this.explodeClock = 0;
            if (this.onlyCharge.getValue()) return;
            this.simulateClick();
            WorldUtils.placeBlock(hit, true);
            this.lastActionTime = System.currentTimeMillis();
            this.ownedAnchors.remove(hit.method_17777());
            this.lastChargedAnchorPos = null;
            this.processingAnchorPos = null;
            return;
        }
        if (!inSlot) return;
        ++this.explodeClock;
    }

    @Override
    public void onItemUse(ItemUseListener.ItemUseEvent event) {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        class_239 class_2392 = this.mc.field_1765;
        if (!(class_2392 instanceof class_3965)) {
            return;
        }
        class_3965 hitResult = (class_3965)class_2392;
        class_1799 hand = this.mc.field_1724.method_6047();
        if ((hand.method_31574(class_1802.field_8281) || hand.method_31574(class_1802.field_8463) || hand.method_31574(class_1802.field_8367)) && BlockUtils.isAnchorCharged(hitResult.method_17777())) {
            event.cancel();
            return;
        }
        if (hitResult.method_17783() == class_239.class_240.field_1332) {
            class_2338 bp;
            if (hand.method_7909() == class_1802.field_23141) {
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
                if (this.fastCharge.getValue()) {
                    this.fastChargeDebounceEnd = System.currentTimeMillis() + (long)this.fastChargeDebounce.getValueInt();
                }
            }
            if (BlockUtils.isAnchorCharged(bp = hitResult.method_17777())) {
                this.ownedAnchors.remove(bp);
            }
        }
    }
}

