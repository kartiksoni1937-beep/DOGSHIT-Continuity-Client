/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.world;

import me.pepperbell.continuity.event.events.PlayerTickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import net.minecraft.class_1747;
import net.minecraft.class_3532;

public final class _8r1d93455157
extends Module
implements PlayerTickListener {
    private final SliderSetting range = (SliderSetting)new SliderSetting(EncryptedString.of("R\u00b7_-^a$|n`$g-$\u00b7^e"), 0.1, 1.0, 0.5, 0.05).setDescription(EncryptedString.of("D$`$$i\u00ad|\u00ads.\u00ad\u00ad$t-~`a^``n-\u00b7^_c_$e||_~ `*-f`*$r-_~|o.\u00ad\u00b7\u00adm.`_| |~e`-*d`|g$*e`$- *^.$t**-o`^* \u00b7.\u00b7s^\u00b7`_t$^*.a~^-r`~t*\u00ad^~ ^`a*\u00ad_s_`s^\u00ad*\u00b7i``|\u00b7s\u00b7.t\u00ad-.*i~\u00b7$n\u00b7*g"));
    private final BooleanSetting onlyBlocks = (BooleanSetting)new BooleanSetting(EncryptedString.of("O`_n.`\u00adl*-_y$~ _^-B*\u00ad^l*|-o\u00ad~c^**-k-^~\u00ads"), true).setDescription(EncryptedString.of("O*_`$n`\u00b7`$l\u00ad\u00b7\u00ad~y\u00b7~$$ *-$_a``^\u00b7s__s\u00ad_i.~\u00ads_.t~$|\u00ad ~~.*w`\u00adh*`$_e-^*_n_\u00b7`\u00b7 ~-h|*~_o$.l~|_d$`-i\u00ad\u00ad\u00b7n\u00ad$g-~*` *|a\u00b7_* ``b^*~l_-o_\u00b7\u00b7c|^k"));
    private final BooleanSetting onlyCrouching = (BooleanSetting)new BooleanSetting(EncryptedString.of("O_\u00ad^n^*l\u00b7^.~y`_ `\u00b7C^_^\u00b7r..\u00ado-.$\u00b7u$\u00ad$`c$\u00b7h--^i_$n|_g"), false).setDescription(EncryptedString.of("O*\u00b7n`**l^|`\u00ady\u00b7^_ \u00b7\u00b7a-\u00b7~\u00b7s|_s*-^*i^|~`s^\u00ad`\u00adt~$ *^*|w.\u00adh\u00ad-\u00ad\u00ade-.n*. _*.c\u00ad|~`r..|o|.u$~c\u00b7`|h^^i`.$n~^_g"));

    public _8r1d93455157() {
        super(EncryptedString.of("B|\u00ad$\u00adr~*_i\u00ad*$*d\u00b7^\u00ad\u00b7g`~--e$\u00ad $`A*\u00b7_`s`$|~s``^i\u00b7\u00b7$_s$.*$t"), EncryptedString.of("H\u00b7\u00b7e^~\u00adl|^-p~`$$s^\u00ad _|y-*o-_u\u00ad. *_*.a*\u00ad`l..^i^`\u00b7_g\u00b7|n`` $\u00b7w$`i\u00b7$t-$*|h.\u00b7 \u00ad.\u00b7t\u00b7_\u00b7h\u00b7\u00b7*$e.$$| ^`.e`*\u00b7|d~*\u00ad$g`\u00b7e||~ ~..|o$^\u00ad|f$** _.\u00adb^-*l\u00ad$.o^_-c|^k.^$s_`\u00ad\u00b7 |$..f\u00ad`|$o|\u00ad\u00ad*r\u00ad~*` *`f`|a\u00b7`^s-^t`_`.e|-`^r*|^- `^_$b-_`-r`$*|i\u00b7._\u00b7d-_g`\u00b7i^^$n\u00b7^g"), -1, Category.MISC);
        this.addSettings(this.range, this.onlyBlocks, this.onlyCrouching);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(PlayerTickListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(PlayerTickListener.class, this);
        super.onDisable();
    }

    @Override
    public void onPlayerTick() {
        float yaw;
        float snappedYaw;
        float diff;
        boolean nearEdgeZ;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (this.onlyBlocks.getValue() && !(this.mc.field_1724.method_6047().method_7909() instanceof class_1747)) {
            return;
        }
        if (this.onlyCrouching.getValue() && !this.mc.field_1724.method_5715()) {
            return;
        }
        double x = this.mc.field_1724.method_23317();
        double z = this.mc.field_1724.method_23321();
        double fracX = Math.abs(x % 1.0);
        double fracZ = Math.abs(z % 1.0);
        boolean nearEdgeX = fracX < this.range.getValue() || fracX > 1.0 - this.range.getValue();
        boolean bl = nearEdgeZ = fracZ < this.range.getValue() || fracZ > 1.0 - this.range.getValue();
        if ((nearEdgeX || nearEdgeZ) && Math.abs(diff = class_3532.method_15393((float)((snappedYaw = (float)Math.round((yaw = this.mc.field_1724.method_36454()) / 45.0f) * 45.0f) - yaw))) > 0.1f) {
            this.mc.field_1724.method_36456(yaw + diff * 0.2f);
        }
    }
}

