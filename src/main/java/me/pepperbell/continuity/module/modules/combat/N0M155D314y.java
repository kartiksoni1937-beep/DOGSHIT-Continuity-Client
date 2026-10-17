/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.AttackListener;
import me.pepperbell.continuity.event.events.BlockBreakingListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_239;

public final class N0M155D314y
extends Module
implements AttackListener,
BlockBreakingListener {
    private final BooleanSetting onlyWeapon = new BooleanSetting(EncryptedString.of("O-|\u00adn\u00b7`|*l*_.~y$\u00b7_ $_w_-~e|-__a_-$.p|*o^\u00adn"), true);
    private final BooleanSetting air = (BooleanSetting)new BooleanSetting(EncryptedString.of("A|.i\u00ad.|$r"), true).setDescription(EncryptedString.of("W_$*h.\u00b7e\u00b7*-~t*.^*h.-*e~\u00ad_^r`|-- \u00b7|t$^o*--` ~\u00ads-|\u00b7t-~_~o\u00b7|p\u00b7. |\u00ad~h-\u00ad``i\u00ad~`|t$-s\u00ad. -\u00ad`d-.i\u00ad.\u00b7r^$e*\u00b7c$_t*\u00ade.|$d_^ |-.t^|\u00ad-o_\u00ad .~`t`--^h\u00ad_`e\u00b7`\u00ad |-^|a^`i._\u00adr"));
    private final BooleanSetting blocks = (BooleanSetting)new BooleanSetting(EncryptedString.of("B-**-l_-o``c*-_k`_-s"), false).setDescription(EncryptedString.of("W~.\u00ad$h_\u00b7e~_\u00b7~t-_h-`*~e-*-~r.^~ \u00b7_-t|~|o~._ \u00b7^-s`$*t`\u00b7`o$\u00b7|_p`-| ~-*h$|i|\u00b7*t\u00b7~\u00b7*s..~` _^*\u00add`$-i^-r$.$$e\u00ad\u00b7.-c\u00b7\u00ad-^t`-_\u00b7e^*d|\u00b7`^ .*`t-*$.o_^ \u00b7-\u00ad-b$\u00b7-l_$`o^-`c.\u00adk\u00ad\u00b7s"));

    public N0M155D314y() {
        super(EncryptedString.of("N.`o-`.- .|M*`$i^`^s$\u00b7-s$~_ .-D.^\u00b7.e\u00b7~l__a||y"), EncryptedString.of("D^|o.^-~e\u00ad.`s*||n^~~'\u00b7\u00adt\u00b7-$ ~$*l`\u00ade\u00ad~|$t-_ \u00ad^\u00ady`\u00ado`|~u^-\u00b7 __-m||`\u00adi*`s\u00ad`|~s_~. ^~`y.-\u00b7.o-.*^u|$r-$` *\u00ad^|s|^w~^`.o*~~r~\u00b7$d$`/`\u00ada\u00ad\u00b7x*_`e-*_\u00b7 ^.h~$i*_*t-$~s"), -1, Category.COMBAT);
        this.addSettings(this.onlyWeapon, this.air, this.blocks);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(AttackListener.class, this);
        this.eventManager.add(BlockBreakingListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(AttackListener.class, this);
        this.eventManager.remove(BlockBreakingListener.class, this);
        super.onDisable();
    }

    @Override
    public void onAttack(AttackListener.AttackEvent event) {
        if (this.onlyWeapon.getValue() && !WorldUtils.isWeapon(this.mc.field_1724.method_6047())) {
            return;
        }
        switch (this.mc.field_1765.method_17783()) {
            case field_1333: {
                if (!this.air.getValue()) break;
                event.cancel();
                break;
            }
            case field_1332: {
                if (!this.blocks.getValue()) break;
                event.cancel();
            }
        }
    }

    @Override
    public void onBlockBreaking(BlockBreakingListener.BlockBreakingEvent event) {
        if (this.onlyWeapon.getValue() && !WorldUtils.isWeapon(this.mc.field_1724.method_6047())) {
            return;
        }
        if (this.mc.field_1765.method_17783() == class_239.class_240.field_1332 && this.blocks.getValue()) {
            event.cancel();
        }
    }
}

