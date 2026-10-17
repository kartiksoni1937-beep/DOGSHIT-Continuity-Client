/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.HudListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.modules.combat._4u70Cry5741;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.BlockUtils;
import me.pepperbell.continuity.utils.CrystalUtils;
import me.pepperbell.continuity.utils.DamageUtils;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.RotationUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public final class _4u70D0u813H4nd
extends Module
implements HudListener {
    private final BooleanSetting stopOnCrystal = (BooleanSetting)new BooleanSetting(EncryptedString.of("S-|t.^_^o^|p~|_` ^-O.$n|.-^ -^C|\u00ad.|r^$||y*|s__t`^$_a~.l"), false).setDescription(EncryptedString.of("S*|--t-$o|\u00adp_.__s_-\u00b7` ._w$`-h\u00b7$\u00ad$i$\u00b7*~l_.e_\u00b7| -|$A`^u\u00ad|^t-$\u00b7.o\u00ad`^\u00b7 *-C\u00ad`\u00b7\u00adr-`y~\u00ads\u00b7\u00b7\u00b7~t.\u00ad$*a\u00ad\u00b7l\u00b7^~* *|*i_$`_s_\u00ad $\u00ad^|r-~|\u00b7u\u00ad$`n$$|n^|\u00adi\u00b7$\u00adn\u00b7~g"));
    private final BooleanSetting checkShield = (BooleanSetting)new BooleanSetting(EncryptedString.of("C|_$h`*`e|.c\u00ad-$\u00adk|^`~ _|$.S-$`h.._i.~\u00b7e^-$l~`\u00ad\u00add"), false).setDescription(EncryptedString.of("C_-\u00b7$h\u00b7$`e^\u00b7c`\u00ad-_k.~$s~$^\u00ad $$`~i\u00ad~f*\u00ad |.y|.o$|_-u~-^.'$_\u00adr-^\u00b7.e^- _$$b\u00b7`.~l`\u00ad.o^.~\u00adc_\u00ad.k*~i\u00ad$n.$g-^^ `*~w|\u00ad\u00b7_i^|^t_|-h..| `$a\u00ad|`. -^s*.h^-\u00adi-`~e\u00b7\u00b7_l|~-^d"));
    private final BooleanSetting onPop = (BooleanSetting)new BooleanSetting(EncryptedString.of("O^\u00adn^\u00ad~- -\u00b7.P~\u00b7_o_\u00ad\u00b7p"), false).setDescription(EncryptedString.of("S\u00ad\u00b7*w.|i~$_t*-c^_^h\u00b7_\u00b7$e_~*s^\u00ad |`$\u00b7t_^o$- $-a-\u00ad* \u00b7\u00b7_t$`o|^t~|_^e$~m.~_ -.i*-.f_\u00ad~$ ^-^y**^o`~u*`. ~~|p*$$`o|$`p"));
    private final BooleanSetting onHealth = (BooleanSetting)new BooleanSetting(EncryptedString.of("O.*|$n`\u00ad ~_\u00ad^H-\u00b7\u00ade\u00b7*a~$`-l^_-`t~-.\u00adh"), false).setDescription(EncryptedString.of("S$~_w`*i`\u00ad_t.|c\u00b7*h_`*`e|.s~\u00ad |~t_-o$_` ~$t^.*o|\u00ad|t.`|.e.^`\u00adm~` `**i*`_f`.\u00ad~ ^-`l-`$^o\u00ad|w._| \u00b7|$$o\u00b7-n|-\u00ad^ -~h|-.e|.\u00ada|`-`l\u00b7^t\u00ad$h"));
    private final BooleanSetting predict = new BooleanSetting(EncryptedString.of("P\u00b7.\u00ad\u00b7r~$^e_`|d\u00ad_\u00b7-i_~$c|.*t** \u00ad*D|..a\u00ad``$m.|$a^`\u00b7g*^|_e"), true);
    private final SliderSetting health = (SliderSetting)new SliderSetting(EncryptedString.of("H_^e|.-a|\u00adl-\u00ad``t\u00b7\u00ad`h"), 1.0, 20.0, 2.0, 1.0).setDescription(EncryptedString.of("H*\u00ad`e-`\u00b7a^~`*l^\u00adt$\u00adh\u00b7~` |..\u00b7t\u00b7~*\u00ado\u00ad~\u00b7| ^\u00b7^-t^**r``i**^^g.|_g$*e_`-r~~-\u00ad `\u00ad-a\u00b7~`t"));
    private final BooleanSetting onGround = (BooleanSetting)new BooleanSetting(EncryptedString.of("O\u00ad$_n^$-\u00b7 *~G|^r\u00ad_\u00b7\u00ado-$-u-\u00ad~\u00adn-__d"), true).setDescription(EncryptedString.of("W*\u00ad\u00b7\u00adh|`e-$`|t`|\u00b7h$`e-`.^r*| |~~c_\u00b7|r$$y|\u00b7*s\u00ad-t$-a^_l_.\u00ad\u00ad \u00b7\u00b7d```*a^|\u00b7m|-a*--g^\u00ade~\u00ad*| ^.i~.s_-`_ \u00b7_c`^_.h_\u00b7.e|$^c_|~\u00adk$|\u00b7e.|d_$ \u00b7|_-o.\u00adn.\u00ad \u00b7_|`g-\u00ad`.r\u00ad~o*_\u00ad.u^*-n_\u00b7d.\u00ad ~--o.^r$\u00ad| .`\u00b7n~_$\u00b7o|\u00b7-~t"));
    private final BooleanSetting checkPlayers = (BooleanSetting)new BooleanSetting(EncryptedString.of("C\u00b7`$|h_|^\u00b7e_*\u00b7.c~-|k_|. `_\u00b7P---l^*a**y*\u00ade`_$`r-\u00ads"), true).setDescription(EncryptedString.of("C`^h-|e^`\u00ad`c*$`k*|.\u00b7s\u00ad`*\u00b7 \u00b7__\u00adf_\u00ad*`o^^|^r`$ -^|n^-e\u00ad*_^a--r$$b$`\u00ad\u00ady.-\u00b7_ \u00ad*p~-l^_$a~~__y.|~\u00b7e|-`r--s"));
    private final SliderSetting distance = (SliderSetting)new SliderSetting(EncryptedString.of("D``*i\u00ad`-s-~t$~-|a_\u00b7.n^~~\u00b7c-_|^e"), 1.0, 10.0, 5.0, 0.1).setDescription(EncryptedString.of("P_^.~l-$.a~^\u00ady^~`e\u00ad$r*_ ~^\u00ad~d~*_i\u00b7\u00b7\u00b7s`--t|-a*_n$-*c*..e"));
    private final BooleanSetting predictCrystals = new BooleanSetting(EncryptedString.of("P^*\u00b7$r_\u00ad.e*`~d^`~^i.\u00b7^c^~-t`^~ ^^_*C\u00b7`r$^y$~.s\u00b7$t*`a~*l|$\u00ad_s"), false);
    private final BooleanSetting checkAim = (BooleanSetting)new BooleanSetting(EncryptedString.of("C^_`h|_\u00b7e$\u00b7|$c$*k\u00ad^*~ \u00b7~A_**_i_\u00ad_m"), false).setDescription(EncryptedString.of("C\u00ad`h$~\u00ad\u00ade~.c|$`k_`\u00ad~s~\u00ad\u00ad. `^-i*`~f_- $*~t\u00b7.\u00ad\u00adh..e-_._ _`_.o`-\u00b7~p*.\u00ad*p^`$$o|`n*^^~e`|_n$**t~^~_ ~_\u00ad_i`|\u00ads~^^* \u00b7$\u00ada$~i^|m.\u00ad`i\u00b7_n.|~_g~\u00ad|_ $-a^^-t*`|~ |^o*-\u00b7b$```s*|i`\u00b7d\u00ad.i\u00b7|a$^n"));
    private final BooleanSetting checkItems = (BooleanSetting)new BooleanSetting(EncryptedString.of("C~-h-_e$.\u00ad$c$\u00ad$*k.- $.I-~t|\u00ad\u00b7e\u00ad.m_|s"), false).setDescription(EncryptedString.of("C$\u00ad|h.\u00ad.e-\u00adc`*-|k.$s\u00ad\u00ad`^ *|--i$-f.-^\u00ad .\u00ad$t.~h_|_e$$^ -`\u00ado\u00ad$p*.p`-o\u00ad_$n*\u00ad\u00b7~e.\u00b7_^n_`.t^\u00b7_ ^^*|i-\u00ad|s\u00ad.. -\u00b7|^h-\u00ado-$`l_^d`\u00ad_i*$\u00b7.n^\u00b7_^g|\u00ad \u00ad*^c-\u00ad*r|~y_\u00b7-s$__`t_^a\u00ad~~l\u00b7\u00b7|s"));
    private final SliderSetting activatesAbove = (SliderSetting)new SliderSetting(EncryptedString.of("A\u00ad`$c\u00b7.t_`_`i__$v.\u00b7\u00ada*.t|\u00b7^\u00b7e_\u00ad^_s\u00b7\u00b7 $\u00ad*-A-`*b$-\u00ad`o^~~\u00adv-\u00ad\u00b7e"), 0.0, 4.0, 0.2, 0.1).setDescription(EncryptedString.of("H\u00b7\u00ade$.^i*$``g~.\u00adh-$.t-. .`t^$`_o|$ _\u00b7\u00b7^t$~\u00b7r-|i_|^g*~g___-e\u00ad^r^~.* \u00b7~`a$`_t"));
    private boolean belowHealth;
    private boolean offhandHasNoTotem;

    public _4u70D0u813H4nd() {
        super(EncryptedString.of("A*~u~$\u00adt\u00b7$-o.- \u00ad$|D\u00b7--*o\u00ad^_*u__b$^.\u00adl_|$|e.*._ $\u00b7\u00b7H.\u00ad_a-_^|n$`d"), EncryptedString.of("A~.u__.t-$o|\u00b7m\u00b7..a*~t`\u00ad.~i*\u00adc$\u00b7a\u00ad\u00ad^l_$$~l_-`\u00ady-.$ $^*s*.$w\u00b7$~|i|\u00adt$\u00ad$`c\u00ad*h-|$e_~|s.\u00ad.~ ^-`.t*^o$`\u00b7. -$y^~o^~u$_~\u00adr~-^^ _.t\u00b7\u00b7o.~$.t*^-e$`m*. `$$w.$.^h\u00b7^e^$n|\u00b7 ~-\u00b7y._-o^|*u~*\u00b7$'$.`~r^$-|e^*` `\u00b7$$a$\u00b7b^^`o*\u00adu|`\u00adt`_* ~_|t\u00ad_-\u00b7o~$. _|`~p$$o**p"), -1, Category.COMBAT);
        this.addSettings(this.stopOnCrystal, this.checkShield, this.onPop, this.onHealth, this.predict, this.health, this.onGround, this.checkPlayers, this.distance, this.predictCrystals, this.checkAim, this.checkItems, this.activatesAbove);
        this.belowHealth = false;
        this.offhandHasNoTotem = false;
    }

    @Override
    public void onEnable() {
        this.eventManager.add(HudListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(HudListener.class, this);
        super.onDisable();
    }

    @Override
    public void onRenderHud(HudListener.HudEvent event) {
        if (this.mc.field_1724 == null) {
            return;
        }
        if (Sweto.INSTANCE.getModuleManager().getModule(_4u70Cry5741.class).crystalling && this.stopOnCrystal.getValue()) {
            return;
        }
        double squaredDistance = this.distance.getValue() * this.distance.getValue();
        class_1661 inventory = this.mc.field_1724.method_31548();
        if (this.checkShield.getValue() && this.mc.field_1724.method_6039()) {
            return;
        }
        if (this.mc.field_1724.method_6079().method_7909() != class_1802.field_8288 && this.onPop.getValue() && !this.offhandHasNoTotem) {
            this.offhandHasNoTotem = true;
            InventoryUtils.selectItemFromHotbar(class_1802.field_8288);
        }
        if (this.mc.field_1724.method_6079().method_7909() == class_1802.field_8288) {
            this.offhandHasNoTotem = false;
        }
        if ((double)this.mc.field_1724.method_6032() <= this.health.getValue() && this.onHealth.getValue() && !this.belowHealth) {
            this.belowHealth = true;
            InventoryUtils.selectItemFromHotbar(class_1802.field_8288);
        }
        if ((double)this.mc.field_1724.method_6032() > this.health.getValue()) {
            this.belowHealth = false;
        }
        if (!this.predict.getValue()) {
            return;
        }
        if (this.mc.field_1724.method_6032() > 19.0f) {
            return;
        }
        if (!this.onGround.getValue() && this.mc.field_1724.method_24828()) {
            return;
        }
        if (this.checkPlayers.getValue() && this.mc.field_1687.method_18456().parallelStream().filter(e -> e != this.mc.field_1724).noneMatch(p -> this.mc.field_1724.method_5858((class_1297)p) <= squaredDistance)) {
            return;
        }
        double above = this.activatesAbove.getValue();
        int floor = (int)Math.floor(above);
        for (int i = 1; i <= floor; ++i) {
            if (this.mc.field_1687.method_8320(this.mc.field_1724.method_24515().method_10069(0, -i, 0)).method_26215()) continue;
            return;
        }
        class_243 playerPos = this.mc.field_1724.method_73189();
        class_2338 playerBlockPos = new class_2338((int)playerPos.field_1352, (int)playerPos.field_1351 - (int)above, (int)playerPos.field_1350);
        if (!this.mc.field_1687.method_8320(new class_2338((class_2382)playerBlockPos)).method_26215()) {
            return;
        }
        List<class_1511> crystals = this.nearbyCrystals();
        ArrayList<class_243> pos = new ArrayList<class_243>();
        crystals.forEach(e -> pos.add(e.method_73189()));
        if (this.predictCrystals.getValue()) {
            Stream<class_2338> s = BlockUtils.getAllInBoxStream(this.mc.field_1724.method_24515().method_10069(-6, -8, -6), this.mc.field_1724.method_24515().method_10069(6, 2, 6)).filter(e -> this.mc.field_1687.method_8320(e).method_26204() == class_2246.field_10540 || this.mc.field_1687.method_8320(e).method_26204() == class_2246.field_9987).filter(CrystalUtils::canPlaceCrystalClient);
            if (this.checkAim.getValue()) {
                s = this.checkItems.getValue() ? s.filter(this::arePeopleAimingAtBlockAndHoldingCrystals) : s.filter(this::arePeopleAimingAtBlock);
            }
            s.forEachOrdered(e -> pos.add(class_243.method_24955((class_2382)e).method_1031(0.0, 1.0, 0.0)));
        }
        for (class_243 crys : pos) {
            double damage = DamageUtils.crystalDamage((class_1309)this.mc.field_1724, crys);
            if (!(damage >= (double)(this.mc.field_1724.method_6032() + this.mc.field_1724.method_6067()))) continue;
            InventoryUtils.selectItemFromHotbar(class_1802.field_8288);
            break;
        }
    }

    private List<class_1511> nearbyCrystals() {
        class_243 pos = this.mc.field_1724.method_73189();
        return this.mc.field_1687.method_8390(class_1511.class, new class_238(pos.method_1031(-6.0, -6.0, -6.0), pos.method_1031(6.0, 6.0, 6.0)), e -> true);
    }

    private boolean arePeopleAimingAtBlock(class_2338 block) {
        class_243[] eyesPos = new class_243[1];
        class_3965[] hitResult = new class_3965[1];
        return this.mc.field_1687.method_18456().parallelStream().filter(e -> e != this.mc.field_1724).anyMatch(e -> {
            eyesPos[0] = RotationUtils.getEyesPos((class_1657)e);
            hitResult[0] = this.mc.field_1687.method_17742(new class_3959(eyesPos[0], eyesPos[0].method_1019(RotationUtils.getPlayerLookVec((class_1657)e).method_1021(4.5)), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)e));
            return hitResult[0] != null && hitResult[0].method_17777().equals((Object)block);
        });
    }

    private boolean arePeopleAimingAtBlockAndHoldingCrystals(class_2338 block) {
        class_243[] eyesPos = new class_243[1];
        class_3965[] hitResult = new class_3965[1];
        return this.mc.field_1687.method_18456().parallelStream().filter(e -> e != this.mc.field_1724).filter(e -> e.method_24518(class_1802.field_8301)).anyMatch(e -> {
            eyesPos[0] = RotationUtils.getEyesPos((class_1657)e);
            hitResult[0] = this.mc.field_1687.method_17742(new class_3959(eyesPos[0], eyesPos[0].method_1019(RotationUtils.getPlayerLookVec((class_1657)e).method_1021(4.5)), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)e));
            return hitResult[0] != null && hitResult[0].method_17777().equals((Object)block);
        });
    }
}

