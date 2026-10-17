/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.List;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.AttackListener;
import me.pepperbell.continuity.event.events.ItemUseListener;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.modules.combat._4nch0rM4cr0;
import me.pepperbell.continuity.module.modules.combat._4u70Cry5741;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.BlockUtils;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.KeyUtils;
import me.pepperbell.continuity.utils.MouseSimulation;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_3965;
import org.lwjgl.glfw.GLFW;

public final class _4u70H17Cry5741
extends Module
implements TickListener,
ItemUseListener,
AttackListener {
    private final KeybindSetting activateKey = (KeybindSetting)new KeybindSetting(EncryptedString.of("A^`c**t.|i`-`-v|_*a`_t$-~e|$ ~`|$K\u00ad\u00ad\u00ad\u00b7e_\u00b7_-y"), 1, false).setDescription(EncryptedString.of("K^|e\u00ad|y-\u00b7\u00b7 _^$-t.`.h*.-*a~|t._\u00b7 |^d-*\u00b7o^|e**s_. ^\u00b7~\u00adh|\u00ad$-i`|t.|$$ \u00ad\u00b7*$c~\u00b7.\u00adr^\u00b7y..|\u00ads\u00b7*`t.$\u00b7-a$|l_^l|^i--$n\u00ad~g"));
    private final BooleanSetting checkPlace = (BooleanSetting)new BooleanSetting(EncryptedString.of("C\u00ad\u00b7$\u00adh\u00ad*_e-~|c\u00b7$k_\u00b7 *~*$P_`\u00b7l\u00ad~a~|c~-e"), false).setDescription(EncryptedString.of("C$~$h_`e--\u00adc~^^_k-_|s^^ `~$i||.f\u00ad- \u00b7.~y$\u00ado\u00b7^u\u00ad$| $`$$c\u00b7.`|a._n`~-$ -\u00ad_-p~-l*^$a\u00ad^*c|\u00b7e`|~ .*t.\u00ad-~h-._$e\u00ad``\u00ad \u00b7*~\u00ado^$-~b~_.`s$\u00ad_i$\u00b7d|_i$*a-\u00ad\u00adn*^|_ -`o^*.|n_| -^^t~*h..-.a*-`t$| \u00ad`~b^*`-l`\u00b7$*o|-|`c.\u00ad^k"));
    private final SliderSetting switchDelay = new SliderSetting(EncryptedString.of("S\u00ad-\u00b7~w\u00ad_i^\u00b7t\u00ad.|_c$*\u00b7h~~ \u00ad*$$D\u00ad\u00ad_*e~~^\u00adl~*a|\u00b7y"), 0.0, 20.0, 0.0, 1.0);
    private final SliderSetting placeDelay = new SliderSetting(EncryptedString.of("P.-|\u00adl$`*\u00ada\u00ad\u00b7c$`-e^~^- -_.`D^`\u00b7e-_\u00b7l_-\u00b7|a_.y"), 0.0, 20.0, 0.0, 1.0);
    private final BooleanSetting workWithTotem = new BooleanSetting(EncryptedString.of("W^\u00ad\u00b7~o\u00b7$-r*\u00b7`|k-^ \u00ad~$W$|i-|t$.-h\u00ad^` ^*^*T`|-.o~^t$_--e\u00b7\u00ad`^m"), false);
    private final BooleanSetting workWithCrystal = new BooleanSetting(EncryptedString.of("W\u00b7*`o-.r.._k|.| -$W.-i~\u00ad**t$|-h_~^ \u00ad`*C$`r\u00ad$\u00ady^.s\u00ad_$$t`\u00b7\u00ad~a\u00b7~_l"), false);
    private final BooleanSetting clickSimulation = (BooleanSetting)new BooleanSetting(EncryptedString.of("C-|\u00adl^^^i$$^$c^\u00adk\u00ad\u00b7|\u00b7 ^_S$^~\u00b7i\u00ad``m*|^`u--l*-a\u00ad^t\u00ad*i`^~|o*~$`n"), false).setDescription(EncryptedString.of("M*^a._`*k\u00ad\u00b7e*-s\u00ad`_~ $~~t`-~*h`^.\u00b7e*\u00ad\u00b7 *~^C\u00ad-\u00b7P\u00ad`\u00b7*S^~ |^$`h|\u00b7|.u$^$^d|- |.t^\u00ad`^h.*~i~-\u00b7.n.*k-$$ \u00ad*$^y-$-o.~u._'*\u00ad~-r^\u00b7|e~^$ \u00b7|l\u00b7\u00b7||e\u00b7\u00adg^\u00b7\u00adi~-..t"));
    private final MinMaxSetting clickSimulationCps = new MinMaxSetting(EncryptedString.of("C\u00ad*^\u00b7l_$**i-|-c_~k~*- \u00ad\u00b7S\u00b7\u00b7.i.`^m_$u._`l\u00ad^^a~._t|$i._\u00b7o\u00ad.\u00adn*\u00ad| $._C\u00ad\u00ad*\u00b7P.\u00adS"), 1.0, 20.0, 1.0, 6.0, 9.0);
    private final BooleanSetting fastMode = (BooleanSetting)new BooleanSetting(EncryptedString.of("F``*a~~s$~\u00b7~t\u00b7^$ _`-M^`o*_.d\u00ad^\u00b7$e"), false).setDescription(EncryptedString.of("I.`n$\u00b7s_|t`*|-a\u00b7|n*|t-\u00b7 -`|\u00ado-^b\u00ad-$s__-|i^*|`d^|$i_-a_*\u00ad|n$*`* .*p|\u00ad|.l\u00ad*a\u00b7-c^-e~^m|`|\u00b7e__*n$|t`|\u00ad\u00ad ||`*a*_\u00ad^n-$||d*\u00b7 ^_$c-_--r^-.|y~-`.s-||^t`^^a\u00b7-\u00adl^.^^ \u00ad\u00ad.`s_|w^$i\u00ad~-t_`\u00b7|c~$.*h"));
    private final SliderSetting fastModeDebounce = (SliderSetting)new SliderSetting(EncryptedString.of("F|~`-a~~*|s`\u00ad`t$|^$ \u00b7\u00adM`.o|$\u00ad~d.~e._\u00ad* ~_*-D_-e^\u00ad*-b`\u00b7_o.\u00b7u$$|~n`~c*_|-e"), 0.0, 500.0, 50.0, 1.0).setDescription(EncryptedString.of("D^-`e-^l-`.a~`._y`~~ |-i\u00ad~**n|^ |\u00adm\u00ad`^s-`$ -\u00b7\u00ad-a^\u00ad$f\u00b7~t~-_e`$r|. \u00b7`-`p*^\u00adl$$*a$|\u00b7\u00adc|~i-\u00adn-`g$``~ $\u00ad*.o|`|\u00b7b-_s*`\u00ad.i|^^d\u00b7*^$i-\u00b7|.a--n^^\u00b7- \u00ad*.$b$^~e|*._f``*-o`_\u00b7|r$~e-* --s\u00b7^w$*`~i^$-t`~.$c~|$$h*~\u00adi||~n*^_g$-\u00b7 -$t\u00ad.o..- `\u00b7$c^-*`r`-y`~`s.*t\u00b7|*a\u00ad\u00b7|l"));
    private final BooleanSetting obsidianBreak = (BooleanSetting)new BooleanSetting(EncryptedString.of("O\u00b7\u00ad|b_*s~_i-_\u00b7.d^\u00b7i-_-_a*-~n~^ `$\u00b7B`^\u00ad$r.$`e|$~a-_k"), true).setDescription(EncryptedString.of("W$\u00ad*h~*-|e*_.n\u00b7|*` _~|.r._*i`~.g_`|_h$|\u00adt~-$c\u00b7_-l`_^.i_-.c~-k_\u00adi~_n`^`\u00b7g^^~\u00b7 *^\u00ado_$|b\u00b7|s~*_i\u00ad.*$d|~i_.a$|n.~~. __w-|i-^t$|h\u00b7`^ ^$a.` ~\u00ad*$s`^w-*\u00ad|o$``\u00adr\u00b7-^d\u00b7~`$,_|-~ `\u00b7`b|$r.$$e-~|a.$~$k*^\u00ad\u00b7s~^_ \u00ad^.a_\u00b7n$\u00ady|-^ .\u00adc|_~r__$*y*~\u00ad*s\u00b7^t~.a^\u00b7l.~** ~^_`o_~_n\u00ad^*~ |``|t|-^*o$-*p\u00ad\u00b7-` ^~|~t||h|^|e$*n\u00b7^^* ||~-r|~u_~..n.-s`\u00b7| .-~A*-u\u00ad\u00ad$~t^$*o-`C-_-r_\u00ad*_y\u00ad_*s_.\u00ad^t*~\u00ad\u00ada|`\u00b7l"));
    private final SliderSetting breakDelay = (SliderSetting)new SliderSetting(EncryptedString.of("B`^|r\u00ad*_.e\u00ad^.a*~\u00ad|k..\u00b7- |\u00b7^~D_.e^\u00b7l`\u00b7a`~y"), 0.0, 20.0, 0.0, 1.0).setDescription(EncryptedString.of("T*\u00b7*-i\u00b7\u00ad$c*|k_~-|s*\u00b7 \u00ad.t-|_o-\u00b7$* **w_\u00ad`a`~\u00ad$i~~t_\u00ad*- \u00b7.^.b_--`e_**`f\u00ad\u00ado\u00b7$r$\u00ad._e|*\u00b7 *^`.a||t$|\u00b7t^_a~*c|\u00ad*k\u00b7.-i|*-*n_|.g.$$ \u00b7^|$t-\u00b7h*_e^| ^\u00ad|c^*r\u00b7|*y-_\u00ad~s~^.`t*`a^^~l\u00b7^- \u00ad*o_.^.n^~_~ ~~~o|-b.~_^s`\u00b7*i|--|d~~i\u00ad~\u00ad`a_.$*n"));
    private long lastClickSimulationTime = 0L;
    private int placeClock = 0;
    private int obsidianSwitchClock = 0;
    private int crystalSwitchClock = 0;
    private long fastModeStartTime = 0L;
    private boolean active = false;
    private boolean crystalling = false;
    private boolean obsidianBreakActive = false;
    private int breakClock = 0;
    private class_3965 lockedHit = null;

    public _4u70H17Cry5741() {
        super(EncryptedString.of("A.$u__t._~o__ ~*`H^``i$|^|t$^_$ |`~C$*.*r^|-_y\u00ad^_s|-~.t-~a.-\u00adl"), EncryptedString.of("A\u00ad_~*u-\u00adt`|^-o~_\u00b7m~_$*a~**t.~\u00b7^i$_._c\u00ad-*|a$\u00adl*.\u00b7l~`y-\u00b7`- `-h_-i$*^t_|c_$r|*y|^-s~^t-\u00ada.-l**~-s^^` $*\u00adf.\u00b7\u00b7*o*.\u00ad*r|. .*.y$$|*o~^u"), -1, Category.COMBAT);
        this.addSettings(this.activateKey, this.checkPlace, this.switchDelay, this.placeDelay, this.workWithTotem, this.workWithCrystal, this.clickSimulation, this.clickSimulationCps, this.fastMode, this.fastModeDebounce, this.obsidianBreak, this.breakDelay);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.eventManager.add(ItemUseListener.class, this);
        this.eventManager.add(AttackListener.class, this);
        this.reset();
        this.lastClickSimulationTime = 0L;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.eventManager.remove(ItemUseListener.class, this);
        this.eventManager.remove(AttackListener.class, this);
        super.onDisable();
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

    @Override
    public void onTick() {
        block42: {
            boolean validItem;
            class_3965 hitResult;
            block44: {
                block43: {
                    class_239 lookedHit;
                    class_239 class_2392;
                    if (this.mc.field_1755 != null) {
                        return;
                    }
                    if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
                        return;
                    }
                    if (!KeyUtils.isKeyPressed(this.activateKey.getKey())) {
                        this.reset();
                        return;
                    }
                    if (this.obsidianBreak.getValue() && !this.active && (class_2392 = this.mc.field_1765) instanceof class_3965) {
                        class_3965 obHit = (class_3965)class_2392;
                        if (this.mc.field_1765.method_17783() == class_239.class_240.field_1332) {
                            boolean onObs;
                            class_2248 lookedBlock = this.mc.field_1687.method_8320(obHit.method_17777()).method_26204();
                            boolean bl = onObs = lookedBlock == class_2246.field_10540 || lookedBlock == class_2246.field_9987;
                            if (onObs && WorldUtils.isSword(this.mc.field_1724.method_6047())) {
                                class_2338 above = obHit.method_17777().method_10084();
                                class_238 searchBox = new class_238(above).method_1014(0.5);
                                List crystals = this.mc.field_1687.method_8390(class_1511.class, searchBox, e -> true);
                                if (!crystals.isEmpty()) {
                                    if (!this.obsidianBreakActive) {
                                        this.obsidianBreakActive = true;
                                        this.breakClock = 0;
                                    }
                                    if (this.breakClock >= this.breakDelay.getValueInt()) {
                                        class_1511 target = (class_1511)crystals.get(0);
                                        this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, (class_1297)target);
                                        this.mc.field_1724.method_6104(class_1268.field_5808);
                                        this.obsidianBreakActive = false;
                                        this.breakClock = 0;
                                        this.active = true;
                                        this.crystalling = true;
                                    } else {
                                        ++this.breakClock;
                                    }
                                } else {
                                    this.obsidianBreakActive = false;
                                    this.breakClock = 0;
                                }
                            }
                        }
                    }
                    if (this.active || this.obsidianBreakActive) break block42;
                    lookedHit = this.mc.field_1765;
                    if (!(lookedHit instanceof class_3965)) break block43;
                    hitResult = (class_3965)lookedHit;
                    if (this.mc.field_1765.method_17783() == class_239.class_240.field_1332) break block44;
                }
                this.reset();
                return;
            }
            if (this.checkPlace.getValue() && !BlockUtils.canPlaceBlockClient(hitResult.method_17777())) {
                this.reset();
                return;
            }
            class_1799 mainHand = this.mc.field_1724.method_6047();
            boolean bl = validItem = WorldUtils.isSword(mainHand) || this.workWithTotem.getValue() && mainHand.method_31574(class_1802.field_8288) || this.workWithCrystal.getValue() && mainHand.method_31574(class_1802.field_8301);
            if (!validItem) {
                this.reset();
                return;
            }
            class_2248 block = this.mc.field_1687.method_8320(hitResult.method_17777()).method_26204();
            boolean bl2 = this.crystalling = block == class_2246.field_10540 || block == class_2246.field_9987;
            if (!this.crystalling) {
                if (BlockUtils.isBlock(hitResult.method_17777(), class_2246.field_23152)) {
                    if (BlockUtils.isAnchorCharged(hitResult.method_17777())) {
                        this.reset();
                        return;
                    }
                    _4nch0rM4cr0 _4nch0rM4cr02 = Sweto.INSTANCE.getModuleManager().getModule(_4nch0rM4cr0.class);
                    if (_4nch0rM4cr02 != null && _4nch0rM4cr02.isEnabled() && GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)1) == 1) {
                        this.reset();
                        return;
                    }
                }
                this.lockedHit = hitResult;
            }
            this.active = true;
        }
        if (!this.crystalling && this.active) {
            if (this.lockedHit == null || this.mc.field_1687.method_8320(this.lockedHit.method_17777()).method_26215()) {
                this.reset();
                return;
            }
            this.mc.field_1690.field_1904.method_23481(false);
            if (!this.mc.field_1724.method_24518(class_1802.field_8281)) {
                if (this.fastMode.getValue()) {
                    InventoryUtils.selectItemFromHotbar(class_1802.field_8281);
                } else if (this.obsidianSwitchClock >= this.switchDelay.getValueInt()) {
                    this.obsidianSwitchClock = 0;
                    InventoryUtils.selectItemFromHotbar(class_1802.field_8281);
                } else {
                    ++this.obsidianSwitchClock;
                }
            }
            if (this.mc.field_1724.method_24518(class_1802.field_8281)) {
                if (this.fastMode.getValue()) {
                    if (this.fastModeStartTime == 0L) {
                        this.fastModeStartTime = System.currentTimeMillis();
                    }
                    if (System.currentTimeMillis() - this.fastModeStartTime >= (long)this.fastModeDebounce.getValueInt()) {
                        this.simulateClick();
                        WorldUtils.placeBlock(this.lockedHit, true);
                        this.placeClock = 0;
                        this.fastModeStartTime = 0L;
                        this.crystalling = true;
                    }
                } else if (this.placeClock >= this.placeDelay.getValueInt()) {
                    this.simulateClick();
                    WorldUtils.placeBlock(this.lockedHit, true);
                    this.placeClock = 0;
                    this.crystalling = true;
                } else {
                    ++this.placeClock;
                }
            }
        }
        if (this.crystalling && this.active) {
            _4u70Cry5741 _4u70Cry57412;
            if (!this.mc.field_1724.method_24518(class_1802.field_8301)) {
                if (this.fastMode.getValue()) {
                    InventoryUtils.selectItemFromHotbar(class_1802.field_8301);
                } else if (this.crystalSwitchClock >= this.switchDelay.getValueInt()) {
                    InventoryUtils.selectItemFromHotbar(class_1802.field_8301);
                    this.crystalSwitchClock = 0;
                } else {
                    ++this.crystalSwitchClock;
                }
            }
            if ((_4u70Cry57412 = Sweto.INSTANCE.getModuleManager().getModule(_4u70Cry5741.class)) != null && !_4u70Cry57412.isEnabled()) {
                _4u70Cry57412.tick();
            }
        }
    }

    @Override
    public void onItemUse(ItemUseListener.ItemUseEvent event) {
        if (this.mc.field_1724 == null) {
            return;
        }
        class_1799 mainHand = this.mc.field_1724.method_6047();
        if ((mainHand.method_31574(class_1802.field_8301) || mainHand.method_31574(class_1802.field_8281)) && GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)1) != 1) {
            event.cancel();
        }
    }

    @Override
    public void onAttack(AttackListener.AttackEvent event) {
        if (this.mc.field_1724 == null) {
            return;
        }
        if (this.mc.field_1724.method_6047().method_31574(class_1802.field_8301) && GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)0) != 1) {
            event.cancel();
        }
    }

    public void reset() {
        this.placeClock = 0;
        this.obsidianSwitchClock = 0;
        this.crystalSwitchClock = 0;
        this.fastModeStartTime = 0L;
        this.active = false;
        this.crystalling = false;
        this.obsidianBreakActive = false;
        this.breakClock = 0;
        this.lockedHit = null;
    }
}

