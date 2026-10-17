/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.List;
import me.pepperbell.continuity.event.events.GameRenderListener;
import me.pepperbell.continuity.event.events.ItemUseListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.BlockUtils;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.MathUtils;
import me.pepperbell.continuity.utils.MouseSimulation;
import net.minecraft.class_1268;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1621;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2868;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_640;
import org.lwjgl.glfw.GLFW;

public final class _4u70Cry5741
extends Module
implements ItemUseListener,
GameRenderListener {
    private final SliderSetting placeDelay = (SliderSetting)new SliderSetting(EncryptedString.of("P.\u00b7l-.\u00b7$a\u00b7\u00adc~|``e~\u00ad`\u00b7 \u00ad\u00ad$D-`~e\u00b7~^l*`a|_y"), 0.0, 500.0, 0.0, 10.0).setDescription(EncryptedString.of("M|$-s.\u00ad$ |-b_\u00b7^$e$$\u00adt`*w_\u00ad^e.\u00b7\u00b7e*-_n$~$ `^.p`$|l$`*\u00b7a-\u00ad~c~.~|e\u00b7~*.m|.-^e\u00ad~n~-\u00adt\u00ad-s"));
    private final SliderSetting breakDelay = (SliderSetting)new SliderSetting(EncryptedString.of("B\u00b7-r^`\u00b7e*^a``k*^ |*D-`.~e\u00ad^l._~a_^y"), 0.0, 500.0, 0.0, 10.0).setDescription(EncryptedString.of("M.*_-s`**^ \u00ad*.b...$e_|t~*.w|\u00b7`^e*\u00ad$e\u00b7-n\u00ad^ `**b|~$r~||`e_`a_.|\u00adk$**s"));
    private final SliderSetting pingCompensation = (SliderSetting)new SliderSetting(EncryptedString.of("P|-|i.\u00b7n~^g^^- _._~C-|o.\u00b7\u00ad^m-|p*$-_e~$n*_.s|*\u00ad$a.|t**i_*.o*$n"), 0.0, 200.0, 50.0, 5.0).setDescription(EncryptedString.of("E``x-~$^t*~r$~\u00ad\u00ada-$|| ._*d$_^\u00ade^*l*\u00b7~a_.*y\u00b7**. _|b\u00ad*|_a$_s\u00b7_`*e-*\u00b7-d..`` _-*\u00b7o$_n_- ^$^\u00adp\u00ad|i~*~n-^|`g-_* **(**0`^^2.*.0_*0|*%`~^`)"));
    private final MinMaxSetting placeChance = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("P*$`.l-.a_*$c\u00b7\u00b7|\u00b7e^$~ _^*-C\u00b7*h*^-`a-.\u00b7\u00adn\u00ad*~~c$`-e"), 0.0, 100.0, 1.0, 100.0, 100.0).setDescription(EncryptedString.of("R|^_`a\u00b7\u00b7n_~\u00b7_d\u00b7\u00ado\u00ad.-m-_-.i\u00ad.z\u00b7\u00ad`e_** `*p\u00ad~$~l*~*a__c-$*e$-.m*~e-*.n|\u00b7t$` ||(`*.%.$~)"));
    private final MinMaxSetting breakChance = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("B$_.`r.^$e|~^a-`-k_^ ^~$C|~*h`\u00b7a`^n$$c\u00b7_e"), 0.0, 100.0, 1.0, 100.0, 100.0).setDescription(EncryptedString.of("R_*\u00b7\u00ada._-_n\u00b7_-d__^~o-._m|`i\u00ad~$$z`~\u00ade`-` *`|$b*\u00b7r.`$e|*`a|\u00b7*k\u00ad|| *_(_\u00b7%\u00b7*\u00ad^)"));
    private final BooleanSetting stopOnKill = (BooleanSetting)new BooleanSetting(EncryptedString.of("S`.*\u00b7t$~\u00ad-o^$p\u00ad\u00b7|\u00b7 \u00ad~$\u00b7O^_\u00adn^$$$ |._$K$$i\u00ad$\u00b7l\u00ad$|*l"), false).setDescription(EncryptedString.of("W\u00b7|\u00b7o^^n|-.`'~~**t|-_\u00ad $\u00adc-\u00b7-r_^*y-|s$.\u00ad_t__\u00b7*a`-`\u00b7l^- \u00ad__i~^f|`^ `~*a\u00ad.~ $\u00b7-\u00b7d.*e_`\u00ada-^\u00b7.d*\u00b7_* ~.^^p-^\u00ad^l__$.a-.\u00ad.y\u00b7.|e``$`r$$ *._-i~\u00ads\u00ad$ -\u00ad_n|$`*e-^~a^`.\u00b7r\u00ad.~`b|\u00ad`y"));
    private final BooleanSetting damageTick = (BooleanSetting)new BooleanSetting(EncryptedString.of("D_\u00b7a.~^`m_--~a_.g__e-^|. _..T~~i`*c-^k"), false).setDescription(EncryptedString.of("T^_i~^.m-$||e\u00ad_s_` \u00b7\u00adc$_\u00adr_~\u00ady|$^~s~.t\u00b7$_a_`$~l$|.s$$\u00b7 `|*f$~$o$^r-_| _|.a\u00b7\u00ad\u00b7 |*\u00adp$.\u00b7e~.-\u00b7r~.f|`*e_\u00b7$\u00b7c||\u00b7_t-** \u00ad~\u00add~*|t*$^a.$*p"));
    private final BooleanSetting antiWeakness = (BooleanSetting)new BooleanSetting(EncryptedString.of("A_~n``^t-\u00b7~i~$*` *$-\u00adW^~.\u00ade\u00ad^^$a\u00ad`$k*~n*$\u00b7e\u00ad*\u00b7-s*_-*s"), false).setDescription(EncryptedString.of("S\u00ad-i$\u00adl\u00b7`-e*~`n*|`t|`l\u00ad-^y|- *`|^s~\u00b7|`w..|i~\u00ad*|t$`.\u00b7c_.h.\u00ad*.e.\u00ads^$ .``t|`^^o*^. -~s^^w~~$o.`^r\u00ad.`_d__`~ _`t$\u00ad._o`* `-|h|-i*-`t\u00ad___ $^*_c*.._r|-.`y\u00ad.-s|`*t.^\u00ad\u00b7a*`$l-*`\u00ad $^_^u\u00b7.n_*`\u00b7d_\u00ade-_r-\u00b7|\u00ad _`_|w^~^e-|a$`k-~-\u00b7n\u00ad|e_^s$*s"));
    private final BooleanSetting breakAndPlace = (BooleanSetting)new BooleanSetting(EncryptedString.of("B-*r\u00b7.e_.~a|*k$* \u00ad.A$.~\u00adn_|.|d`\u00ad| \u00ad\u00adP\u00b7_l~-^`a*\u00ad~`c*\u00ad\u00ade"), true).setDescription(EncryptedString.of("B^_|.r|~*e||a_^.\u00adk-. ||\u00b7.e|^x|**\u00adi`\u00b7**s~$.t^`__i~.n|\u00b7-g^^ \u00ad^c\u00b7.|.r$_y$~\u00ads~~t\u00b7^a`*-$l-^`$ ~~a**`*n~$^-d~._~ ^\u00b7`_p.-^l$$\u00b7$a^*\u00ad`c~_$$e_~\u00ad| ^-^|n^`.e^^$w|\u00b7- .\u00b7\u00b7*o.*.-n\u00b7\u00ad\u00b7$e^~.. ~$w*-\u00b7|h\u00ad\u00b7e_|\u00b7`n*- -^^l\u00b7\u00ado$-.^o|^k**`i_.-n`^g_^\u00b7` ^^.a_-\u00ad^t\u00ad``\u00b7 \u00b7|a-_|* .|c_|$r|\u00b7`^y~`s*.*t$^a$~`l"));
    private final BooleanSetting autoSwapToCrystal = (BooleanSetting)new BooleanSetting(EncryptedString.of("A\u00ad|`u$\u00adt$_._o._$ `-S|\u00adw\u00b7*a|^^-p.^\u00b7- `~*T$.o_~ `_-$C.\u00ad*r_|~y*$*s$-*t|``$a\u00b7^l"), true).setDescription(EncryptedString.of("S~\u00ad^`w|\u00b7_a$\u00b7-_p\u00b7_. -~t_\u00ad|`o|~ ~~c_$$.r|$\u00ad$y$$|s^`t.~\u00b7\u00b7a\u00b7-l_._- ~~w`\u00b7h\u00b7\u00b7`|e\u00ad|n-~ |~\u00b7h|~.o|~^l|.d-~|i|\u00adn_$g*\u00ad~^ *\u00ad**s\u00ad$w*_o|$|\u00b7r$|\u00b7d$\u00ad ^\u00ad~a.~^\u00adn`\u00b7\u00add`| ._$l$_-\u00b7o^\u00ad.$o|*||k\u00ad^|*i__$n`$`_g|\u00b7$ _\u00ad..a|^t`\u00ad -^`o_|b$`s\u00ad\u00ad\u00ad|i\u00ad_\u00add.\u00ad\u00b7i~_\u00b7a**n\u00ad~/*\u00ad^-c.__*r_$^$y_~$s_~~`t-^a\u00ad\u00ad`l"));
    private final BooleanSetting fakePunch = (BooleanSetting)new BooleanSetting(EncryptedString.of("F\u00b7``-a*^.k*\u00b7|e-~| `^^P\u00ad-^u~`\u00adn|$c\u00b7$h"), false).setDescription(EncryptedString.of("H~\u00b7$i\u00b7.\u00b7~t|\u00ad\u00ad `$$|e$|_v\u00b7$`e|$*`r|-.y_\u00ad*^ \u00b7$~e~\u00b7n-^-*t\u00ad\u00ad^\u00b7i~-t^*~y*^~/.`-b$_l_$*o\u00ad\u00adc_|k\u00b7` -\u00ado^~|-n\u00ad_ \u00b7\u00b7m\u00b7^$i\u00b7-\u00b7~s.*s|~` ``f..`^o\u00ad.r\u00ad|-- \u00b7*l*\u00ade^*g|~i_`t^_|~ `.$l*^.*o*^o\u00b7^\u00b7k"));
    private final BooleanSetting stopOnAir = (BooleanSetting)new BooleanSetting(EncryptedString.of("S^$t\u00ad$o_$||p**\u00b7$ \u00b7^O^$..n\u00b7. -^~^A.$$|i$\u00b7|r"), false).setDescription(EncryptedString.of("W|.-o|.n$.$_'`$-_t\u00b7** -\u00ad..c\u00ad*|r^\u00b7y\u00ad|.s.\u00ad_*t*\u00ada*\u00b7|l_. ~^w$|-h.\u00b7i*\u00ad_.l^-$`e.~$` `~\u00ad-i|$~.n\u00ad$*\u00ad ^|t\u00ad\u00ad_h`\u00b7e.\u00ad\u00b7 -.^a^*i|-_r"));
    private final MinMaxSetting leftCps = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("L`\u00ad``e\u00ad~|~f\u00ad.\u00ad\u00adt^``^ ~|-|C*~_P_*S"), 1.0, 30.0, 1.0, 8.0, 14.0).setDescription(EncryptedString.of("C|`\u00adP~*S.*| *`r-^~a\u00b7|\u00adn$_*~g~$$-e~-~\u00ad -\u00ad~\u00b7f^\u00b7o\u00ad*-r.* *|.|l\u00ad`_e$~f\u00ad*t\u00ad$ *^c*`_^l`*_i||c||`k_\u00b7 `-s-|\u00b7_i|\u00b7`*m$^_u*^l|`-a-\u00b7^\u00b7t_|.i^.o_|n"));
    private final MinMaxSetting rightCps = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("R\u00b7~.^i_\u00ad-^g^_h\u00ad\u00b7$*t^-. $~\u00ad\u00adC`$_\u00b7P$$S"), 1.0, 30.0, 1.0, 8.0, 14.0).setDescription(EncryptedString.of("C_|-P`\u00ad_S-.\u00ad\u00b7 ^~\u00adr_\u00b7~a\u00ad\u00b7-.n***g|$e`.~ $*f-\u00ad|o|-`r^^.$ `*$r.\u00ad\u00adi$~*g^*h-^t`\u00b7~ -*^c-.`l~$i*-c\u00ad$k^- -^_\u00b7s\u00b7`i_\u00b7|m|*u|..l*~a*\u00ad`t\u00b7\u00ad`i-\u00b7\u00ad.o$\u00b7~n"));
    private long nextPlaceMs = 0L;
    private long nextBreakMs = 0L;
    public boolean crystalling = false;
    private long lastPlaceAttemptMs = 0L;
    private static final long MIN_PLACE_INTERVAL_MS = 50L;
    private long nextLeftClickMs = 0L;
    private long nextRightClickMs = 0L;

    public _4u70Cry5741() {
        super(EncryptedString.of("A$\u00b7\u00b7$u-^$~t\u00b7^~o*\u00b7$* $__C*\u00b7\u00b7*r~`\u00ady-\u00ad~s`\u00b7`t-*^a~\u00b7l"), EncryptedString.of("A.\u00ad~u_.-*t~_o*\u00ad~m-|a.\u00ad$.t-~-.i\u00ad.`c.\u00ad*a\u00ad\u00ad*\u00adl`_l$~$y-. \u00b7*`p_-$l~$\u00b7a||~-c\u00b7$`\u00b7e^\u00b7|s*\u00ad~ .$\u00ad\u00b7a\u00b7`-n^$.d|^$ ^`-b|~~r`~~`e^\u00ada~^*_k._$.s_^~ $\u00ade\u00b7*`n`_d~`_$ `$$`c-\u00b7.r|-`y|^|\u00b7s~-`t\u00ad*`\u00ada$\u00ad\u00ad*l|_s"), -1, Category.COMBAT);
        this.addSettings(this.placeDelay, this.breakDelay, this.pingCompensation, this.placeChance, this.breakChance, this.stopOnKill, this.damageTick, this.antiWeakness, this.breakAndPlace, this.autoSwapToCrystal, this.fakePunch, this.stopOnAir, this.leftCps, this.rightCps);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(ItemUseListener.class, this);
        this.eventManager.add(GameRenderListener.class, this);
        this.nextPlaceMs = 0L;
        this.nextBreakMs = 0L;
        this.crystalling = false;
        this.lastPlaceAttemptMs = 0L;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(ItemUseListener.class, this);
        this.eventManager.remove(GameRenderListener.class, this);
        this.nextPlaceMs = 0L;
        this.nextBreakMs = 0L;
        this.crystalling = false;
        this.lastPlaceAttemptMs = 0L;
        super.onDisable();
    }

    @Override
    public void onGameRender(GameRenderListener.GameRenderEvent event) {
        int slot;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null || this.mc.field_1761 == null) {
            return;
        }
        if (this.mc.field_1755 != null) {
            return;
        }
        if (this.autoSwapToCrystal.getValue() && this.isHoldingSword() && !this.mc.field_1724.method_6047().method_31574(class_1802.field_8301) && this.mc.field_1690.field_1904.method_1434() && this.isValidCrystalTarget() && (slot = this.findInHotbar(class_1802.field_8301)) != -1) {
            this.swapTo(slot);
            return;
        }
        this.tick();
    }

    public void tick() {
        class_3965 bhr;
        boolean usePressed;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null || this.mc.field_1761 == null) {
            return;
        }
        if (this.mc.field_1755 != null) {
            return;
        }
        boolean bl = usePressed = this.mc.field_1690.field_1904.method_1434() || GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)1) == 1;
        if (!usePressed) {
            this.crystalling = false;
            return;
        }
        if (!this.mc.field_1724.method_6047().method_31574(class_1802.field_8301)) {
            return;
        }
        if (this.mc.field_1724.method_6115()) {
            return;
        }
        if (this.stopOnKill.getValue() && this.isDeadBodyNearby()) {
            return;
        }
        if (this.stopOnAir.getValue() && !this.mc.field_1724.method_24828()) {
            return;
        }
        if (this.damageTick.getValue() && this.damageTickCheck()) {
            return;
        }
        this.crystalling = true;
        this.advanceClickSimulation();
        long now = System.currentTimeMillis();
        long pingAdaptiveInterval = this.calculatePingAdaptiveInterval();
        int rand = MathUtils.randomInt(1, 100);
        class_239 target = this.mc.field_1765;
        if (target instanceof class_3965 && (bhr = (class_3965)target).method_17783() == class_239.class_240.field_1332) {
            boolean isBase;
            class_2338 pos = bhr.method_17777();
            boolean bl2 = isBase = BlockUtils.isBlock(pos, class_2246.field_10540) || BlockUtils.isBlock(pos, class_2246.field_9987);
            if (isBase && this.canPlaceCrystal(pos) && now >= this.nextPlaceMs && now - this.lastPlaceAttemptMs >= pingAdaptiveInterval && rand <= this.getChance(this.placeChance)) {
                this.placeCrystal(bhr);
                this.nextPlaceMs = now + (long)this.placeDelay.getValue();
                this.lastPlaceAttemptMs = now;
            }
            if (this.fakePunch.getValue() && !isBase && now >= this.nextBreakMs && rand <= this.getChance(this.breakChance)) {
                this.mc.field_1761.method_2910(pos, bhr.method_17780());
                this.mc.field_1724.method_6104(class_1268.field_5808);
                this.nextBreakMs = now + (long)this.breakDelay.getValue();
            }
        }
        if (target != null && target.method_17783() == class_239.class_240.field_1333 && this.fakePunch.getValue() && now >= this.nextBreakMs && rand <= this.getChance(this.breakChance)) {
            this.mc.field_1724.method_6104(class_1268.field_5808);
            this.nextBreakMs = now + (long)this.breakDelay.getValue();
        }
        if (target instanceof class_3966) {
            class_3966 ehr = (class_3966)target;
            class_1297 entity = ehr.method_17782();
            if (this.breakAndPlace.getValue() && entity instanceof class_1511) {
                class_1511 crystal = (class_1511)entity;
                if (now >= this.nextBreakMs && now >= this.nextPlaceMs && now - this.lastPlaceAttemptMs >= pingAdaptiveInterval && this.canPlaceCrystalAtEntity(crystal)) {
                    int prevSlot = this.mc.field_1724.method_31548().method_67532();
                    if (this.antiWeakness.getValue() && this.cantBreakCrystal()) {
                        this.swapToSword();
                    }
                    this.hitEntity(entity);
                    if (this.antiWeakness.getValue()) {
                        this.swapTo(prevSlot);
                    }
                    this.placeCrystalAtEntity(crystal);
                    this.nextBreakMs = now + (long)this.breakDelay.getValue();
                    this.nextPlaceMs = now + (long)this.placeDelay.getValue();
                    this.lastPlaceAttemptMs = now;
                    return;
                }
            }
            if (now >= this.nextBreakMs && rand <= this.getChance(this.breakChance)) {
                if (!(this.fakePunch.getValue() || entity instanceof class_1511 || entity instanceof class_1621)) {
                    return;
                }
                int prevSlot = this.mc.field_1724.method_31548().method_67532();
                if ((entity instanceof class_1511 || entity instanceof class_1621) && this.antiWeakness.getValue() && this.cantBreakCrystal()) {
                    this.swapToSword();
                }
                this.hitEntity(entity);
                this.nextBreakMs = now + (long)this.breakDelay.getValue();
                if (this.antiWeakness.getValue()) {
                    this.swapTo(prevSlot);
                }
            }
        }
    }

    @Override
    public void onItemUse(ItemUseListener.ItemUseEvent event) {
        class_239 class_2392;
        if (this.mc.field_1724 == null) {
            return;
        }
        if (this.mc.field_1724.method_6047().method_31574(class_1802.field_8301) && (class_2392 = this.mc.field_1765) instanceof class_3965) {
            class_3965 h = (class_3965)class_2392;
            if (this.mc.field_1765.method_17783() == class_239.class_240.field_1332 && (BlockUtils.isBlock(h.method_17777(), class_2246.field_10540) || BlockUtils.isBlock(h.method_17777(), class_2246.field_9987))) {
                event.cancel();
            }
        }
    }

    private void advanceClickSimulation() {
        long now = System.currentTimeMillis();
        if (now >= this.nextLeftClickMs) {
            MouseSimulation.mouseClick(0);
            this.nextLeftClickMs = now + this.cpsToMs(this.leftCps);
        }
        if (now >= this.nextRightClickMs) {
            MouseSimulation.mouseClick(1);
            this.nextRightClickMs = now + this.cpsToMs(this.rightCps);
        }
    }

    private long cpsToMs(MinMaxSetting cps) {
        double minCps = cps.getMinValue();
        double maxCps = cps.getMaxValue();
        double randomCps = minCps + Math.random() * (maxCps - minCps);
        return (long)(1000.0 / Math.max(randomCps, 1.0));
    }

    private long calculatePingAdaptiveInterval() {
        class_640 playerListEntry;
        long basePing = 50L;
        if (this.mc.field_1724 != null && this.mc.field_1724.field_3944 != null && (playerListEntry = this.mc.field_1724.field_3944.method_2871(this.mc.field_1724.method_5667())) != null) {
            int ping = playerListEntry.method_2959();
            float compensation = (float)this.pingCompensation.getValue() / 100.0f;
            basePing = 50L + (long)((float)ping * compensation);
        }
        return basePing;
    }

    private void placeCrystal(class_3965 hit) {
        this.mc.field_1761.method_2896(this.mc.field_1724, class_1268.field_5808, hit);
        this.mc.field_1724.method_6104(class_1268.field_5808);
    }

    private void hitEntity(class_1297 entity) {
        this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, entity);
        this.mc.field_1724.method_6104(class_1268.field_5808);
    }

    private boolean canPlaceCrystal(class_2338 base) {
        if (this.mc.field_1687 == null) {
            return false;
        }
        class_2338 up = base.method_10084();
        if (!this.mc.field_1687.method_22347(up)) {
            return false;
        }
        class_238 box = new class_238((double)up.method_10263(), (double)up.method_10264(), (double)up.method_10260(), (double)(up.method_10263() + 1), (double)(up.method_10264() + 2), (double)(up.method_10260() + 1));
        List entities = this.mc.field_1687.method_8335(null, box);
        entities.removeIf(e -> !(e instanceof class_1511));
        return entities.isEmpty();
    }

    private boolean canPlaceCrystalAtEntity(class_1511 crystal) {
        class_2338 below = crystal.method_24515().method_10074();
        return (BlockUtils.isBlock(below, class_2246.field_10540) || BlockUtils.isBlock(below, class_2246.field_9987)) && this.canPlaceCrystal(below);
    }

    private void placeCrystalAtEntity(class_1511 crystal) {
        class_2338 below = crystal.method_24515().method_10074();
        class_3965 hit = new class_3965(class_243.method_24953((class_2382)below).method_1031(0.0, 0.5, 0.0), class_2350.field_11036, below, false);
        this.mc.field_1761.method_2896(this.mc.field_1724, class_1268.field_5808, hit);
        this.mc.field_1724.method_6104(class_1268.field_5808);
    }

    private boolean isValidCrystalTarget() {
        class_3965 bhr;
        if (this.mc.field_1765 == null) {
            return false;
        }
        class_239 class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3965 && (bhr = (class_3965)class_2392).method_17783() == class_239.class_240.field_1332) {
            return BlockUtils.isBlock(bhr.method_17777(), class_2246.field_10540) || BlockUtils.isBlock(bhr.method_17777(), class_2246.field_9987);
        }
        class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3966) {
            class_3966 ehr = (class_3966)class_2392;
            return ehr.method_17782() instanceof class_1511 && this.breakAndPlace.getValue();
        }
        return false;
    }

    private boolean isHoldingSword() {
        if (this.mc.field_1724 == null) {
            return false;
        }
        return this.mc.field_1724.method_6047().method_31574(class_1802.field_8802) || this.mc.field_1724.method_6047().method_31574(class_1802.field_22022) || this.mc.field_1724.method_6047().method_31574(class_1802.field_8371) || this.mc.field_1724.method_6047().method_31574(class_1802.field_8528) || this.mc.field_1724.method_6047().method_31574(class_1802.field_8845) || this.mc.field_1724.method_6047().method_31574(class_1802.field_8091);
    }

    private boolean isDeadBodyNearby() {
        if (this.mc.field_1687 == null || this.mc.field_1724 == null) {
            return false;
        }
        return this.mc.field_1687.method_18456().stream().filter(p -> p != this.mc.field_1724).filter(p -> p.method_5858((class_1297)this.mc.field_1724) < 100.0).anyMatch(p -> p.method_29504() || p.method_6032() <= 0.0f);
    }

    private boolean damageTickCheck() {
        if (this.mc.field_1687 == null || this.mc.field_1724 == null) {
            return false;
        }
        return this.mc.field_1687.method_18456().stream().filter(p -> p != this.mc.field_1724).filter(p -> p.method_5858((class_1297)this.mc.field_1724) < 36.0).filter(p -> p.method_49107() == null).filter(p -> !p.method_24828()).anyMatch(p -> p.field_6235 >= 2) && !(this.mc.field_1724.method_6052() instanceof class_1657);
    }

    private boolean cantBreakCrystal() {
        if (this.mc.field_1724 == null) {
            return false;
        }
        class_1293 weakness = this.mc.field_1724.method_6112(class_1294.field_5911);
        class_1293 strength = this.mc.field_1724.method_6112(class_1294.field_5910);
        return weakness != null && (strength == null || strength.method_5578() <= weakness.method_5578());
    }

    private void swapToSword() {
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = this.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960() || !stack.method_31574(class_1802.field_8802) && !stack.method_31574(class_1802.field_22022) && !stack.method_31574(class_1802.field_8371) && !stack.method_31574(class_1802.field_8528) && !stack.method_31574(class_1802.field_8845) && !stack.method_31574(class_1802.field_8091)) continue;
            this.swapTo(i);
            return;
        }
    }

    private int findInHotbar(class_1792 item) {
        if (this.mc.field_1724 == null) {
            return -1;
        }
        for (int i = 0; i < 9; ++i) {
            if (!this.mc.field_1724.method_31548().method_5438(i).method_31574(item)) continue;
            return i;
        }
        return -1;
    }

    private void swapTo(int slot) {
        if (this.mc.field_1724 == null || this.mc.method_1562() == null) {
            return;
        }
        if (this.mc.field_1724.method_31548().method_67532() != slot) {
            this.mc.field_1724.method_31548().method_61496(slot);
            this.mc.method_1562().method_52787((class_2596)new class_2868(slot));
        }
    }

    private int getChance(MinMaxSetting setting) {
        float max;
        float min = setting.getMinFloat();
        if (min >= (max = setting.getMaxFloat())) {
            return (int)max;
        }
        return (int)((double)min + Math.random() * (double)(max - min + 1.0f));
    }
}

