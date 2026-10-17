/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1291;
import net.minecraft.class_1294;
import net.minecraft.class_1657;

public final class _4u70P07
extends Module
implements TickListener {
    private final SliderSetting minHealth = new SliderSetting(EncryptedString.of("M**i_`*n_`|* ~*H-_`e~^*~a^~l_`t-\u00adh"), 1.0, 20.0, 10.0, 1.0);
    private final SliderSetting switchDelay = new SliderSetting(EncryptedString.of("S^_~w|.i\u00b7*_.t^\u00ad_|c||h~.. .`D\u00ad\u00b7-e^--*l~$$\u00b7a\u00ad`.*y"), 0.0, 10.0, 0.0, 1.0);
    private final SliderSetting throwDelay = new SliderSetting(EncryptedString.of("T~*`*h~_r_~o\u00b7-w\u00ad^ `$|D$~`e.\u00ad$~l-*\u00b7\u00ada$|y"), 0.0, 10.0, 0.0, 1.0);
    private final BooleanSetting goToPrevSlot = new BooleanSetting(EncryptedString.of("S_|_w-|i_-*`t$._|c~-*~h*| `|B\u00b7|`.a-\u00b7c\u00ad\u00ad*k"), true);
    private final BooleanSetting lookDown = new BooleanSetting(EncryptedString.of("L.\u00ado--_$o`^$\u00adk.*.- *^`\u00b7D-$$o_^~^w-\u00ad`n"), true);
    private int switchClock;
    private int throwClock;
    private int prevSlot;
    private float prevPitch;
    private boolean bool;

    public _4u70P07() {
        super(EncryptedString.of("A`$.~u_~t|.\u00ado\u00b7*_ ~.\u00b7P|.o\u00ad._.t"), EncryptedString.of("A\u00ad`u$.\u00b7.t`*.o_-m`^~^a\u00b7-\u00b7t*^i~^c|~_$a*-$*l*.`l\u00b7\u00b7y*^.^ \u00b7\u00b7*.t\u00b7*|$h\u00ad\u00b7\u00b7*r_~\u00b7o\u00ad*-w^|s^^~` _$*h$\u00ad*e^_\u00b7.a*\u00b7|^l.|t*|\u00ad\u00adh-*. .\u00b7.-p`-~o^*$$t^**i|.o`\u00b7$n_~s\u00b7` $.w_|`h*~.\u00b7e~^_\u00adn\u00ad*. \u00b7*~^l|-o_`~^w||$. -^$^o-*`$n_.$ *|h-`~*e|*|\u00b7a*|l^*t*-\u00ad^h"), -1, Category.COMBAT);
        this.addSettings(this.minHealth, this.switchDelay, this.throwDelay, this.goToPrevSlot, this.lookDown);
    }

    private void reset() {
        this.switchClock = 0;
        this.throwClock = 0;
        this.prevSlot = -1;
        this.prevPitch = -1.0f;
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
        if (this.mc.field_1724.method_6032() <= this.minHealth.getValueFloat() || this.bool) {
            if (this.bool && this.mc.field_1724.method_6032() >= this.mc.field_1724.method_6063()) {
                this.bool = false;
                return;
            }
            if (!InventoryUtils.isThatSplash((class_1291)class_1294.field_5915.comp_349(), 1, 1, this.mc.field_1724.method_6047())) {
                int potSlot;
                if ((double)this.switchClock < this.switchDelay.getValue()) {
                    ++this.switchClock;
                    return;
                }
                if (this.goToPrevSlot.getValue() && this.prevSlot == -1) {
                    this.prevSlot = this.mc.field_1724.method_31548().method_67532();
                }
                if (this.lookDown.getValue() && this.prevPitch == -1.0f) {
                    this.prevPitch = this.mc.field_1724.method_36455();
                }
                if ((potSlot = InventoryUtils.findSplash((class_1291)class_1294.field_5915.comp_349(), 1, 1)) != -1) {
                    InventoryUtils.setInvSlot(potSlot);
                    this.switchClock = 0;
                }
            }
            if (InventoryUtils.isThatSplash((class_1291)class_1294.field_5915.comp_349(), 1, 1, this.mc.field_1724.method_6047())) {
                class_1269 actionResult;
                if ((double)this.throwClock < this.throwDelay.getValue()) {
                    ++this.throwClock;
                    return;
                }
                if (this.lookDown.getValue()) {
                    this.mc.field_1724.method_36457(90.0f);
                }
                if ((actionResult = this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, class_1268.field_5808)).method_23665()) {
                    this.mc.field_1724.method_6104(class_1268.field_5808);
                }
                this.throwClock = 0;
            }
        } else if (this.prevSlot != -1 || this.prevPitch != -1.0f) {
            InventoryUtils.setInvSlot(this.prevSlot);
            this.prevSlot = -1;
            this.mc.field_1724.method_36457(this.prevPitch);
            this.prevPitch = -1.0f;
        }
    }
}

