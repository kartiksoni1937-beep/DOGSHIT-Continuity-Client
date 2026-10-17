/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.misc;

import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.KeyUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1802;

public final class K3yP34r1
extends Module
implements TickListener {
    private final KeybindSetting activateKey = new KeybindSetting(EncryptedString.of("A$\u00ad_~c\u00b7~t-.i|~v\u00b7-`*a~-|t^$_e|. \u00b7~K_^e~*y"), -1, false);
    private final SliderSetting delay = new SliderSetting(EncryptedString.of("D..`-e-^.\u00b7l_|`*a~\u00b7`y"), 0.0, 20.0, 0.0, 1.0);
    private final BooleanSetting switchBack = new BooleanSetting(EncryptedString.of("S\u00b7$^-w^_$$i$^t.*$$c~\u00adh-$~$ `**_B^^.a~\u00b7-.c`\u00ad.|k"), true);
    private final SliderSetting switchDelay = (SliderSetting)new SliderSetting(EncryptedString.of("S^*w.-_i*^~t\u00ad|.c`$$h\u00b7*| |*^|D`-e**$-l$`*$a|-*-y"), 0.0, 20.0, 0.0, 1.0).setDescription(EncryptedString.of("D*_-_e||l~~a_|-y|~$| \u00ad^a`.f$*~t*._~e`\u00ad|r\u00ad.\u00ad ~^`_t^\u00ad_.h$|^r$\u00b7*\u00ado$`\u00b7-w~|_`i**n.`.g\u00ad- |_p*|e*~\u00b7a_`\u00b7r.\u00b7l.`~| $__b\u00ad`e__f$_\u00b7o$-r-.\u00b7e|_-- \u00b7$s-|w-\u00b7^i`|*^t`$`.c|.h*\u00adi~`$|n*~g$. \u00b7.b_^^a`_c\u00ad^k"));
    private boolean active;
    private boolean hasActivated;
    private int clock;
    private int previousSlot;
    private int switchClock;

    public K3yP34r1() {
        super(EncryptedString.of("K\u00b7|$$e`*y~.\u00b7 .`P^.e|`$a$..r\u00b7|$*l"), EncryptedString.of("S.\u00b7w~.i-~.t-\u00b7-c^-.h`|.e.-s.\u00b7| -$|\u00b7t_^^o^_\u00b7\u00b7 `\u00ad~|a~^^\u00b7n-_-\u00ad -.~$e~--`n^^$-d|`\u00ad~e~*-r.|\u00ad^ ~-^p.*e\u00ad~a_$\u00b7$r\u00b7*\u00adl__\u00b7 _`a|*n--\u00ad|d|_ -^t\u00b7$.*h\u00b7|~r-$o-|-\u00adw**s_\u00ad ..i*~~.t\u00b7~ $|$w.^~\u00b7h|-e$`n`. `$_.y$_o-`\u00adu*_ \u00ad_p\u00b7.``r`^e\u00b7_`s\u00b7-_s-| $\u00ada\u00ad\u00ad`. *.._b--i_^n*-*d"), -1, Category.MISC);
        this.addSettings(this.activateKey, this.delay, this.switchBack, this.switchDelay);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.reset();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1755 != null) {
            return;
        }
        if (KeyUtils.isKeyPressed(this.activateKey.getKey())) {
            this.active = true;
        }
        if (this.active) {
            if (this.previousSlot == -1) {
                this.previousSlot = this.mc.field_1724.method_31548().method_67532();
            }
            InventoryUtils.selectItemFromHotbar(class_1802.field_8634);
            if (this.clock < this.delay.getValueInt()) {
                ++this.clock;
                return;
            }
            if (!this.hasActivated) {
                class_1269 result = this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, class_1268.field_5808);
                if (result.method_23665()) {
                    this.mc.field_1724.method_6104(class_1268.field_5808);
                }
                this.hasActivated = true;
            }
            if (this.switchBack.getValue()) {
                this.switchBack();
            } else {
                this.reset();
            }
        }
    }

    private void switchBack() {
        if (this.switchClock < this.switchDelay.getValueInt()) {
            ++this.switchClock;
            return;
        }
        InventoryUtils.setInvSlot(this.previousSlot);
        this.reset();
    }

    private void reset() {
        this.previousSlot = -1;
        this.clock = 0;
        this.switchClock = 0;
        this.active = false;
        this.hasActivated = false;
    }
}

