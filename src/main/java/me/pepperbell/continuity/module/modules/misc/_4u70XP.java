/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.misc;

import me.pepperbell.continuity.event.events.ItemUseListener;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.MathUtils;
import me.pepperbell.continuity.utils.MouseSimulation;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import org.lwjgl.glfw.GLFW;

public final class _4u70XP
extends Module
implements TickListener,
ItemUseListener {
    private final SliderSetting delay = new SliderSetting(EncryptedString.of("D$-e**l\u00b7~~a_*\u00ad|y"), 0.0, 20.0, 0.0, 1.0);
    private final SliderSetting chance = (SliderSetting)new SliderSetting(EncryptedString.of("C$`$`h$_`a_^|$n\u00ad__$c$.^`e"), 0.0, 100.0, 100.0, 1.0).setDescription(EncryptedString.of("R-|\u00b7a_\u00ad.\u00b7n|_d|*_$o^$\u00b7$m-\u00b7i`*$z\u00b7`a\u00ad._\u00b7t|..*i`__o$`n"));
    private final BooleanSetting clickSimulation = (BooleanSetting)new BooleanSetting(EncryptedString.of("C\u00b7^$l.-i$$c-*k-$ |$|\u00adS\u00b7$i^\u00b7-$m\u00ad-*.u_\u00b7l*\u00ad-a-^_t.-i_\u00b7o`^`_n"), false).setDescription(EncryptedString.of("M**a`$\u00ad_k_-_e*`s^|~\u00ad ~~t_^\u00adh-\u00b7^_e\u00b7\u00b7~| ~\u00b7\u00ad|C-_~`P*`S-\u00b7 |-\u00b7h*^u|~\u00b7d\u00b7^^| `\u00adt^-\u00b7h\u00ad*~$i|~^n^~_k|\u00b7~` $$~.y-\u00ado_.-$u$~-'|~r\u00b7|e^- \u00ad~_.l\u00ad-e^|_~g\u00b7*\u00adi_`t"));
    int clock;

    public _4u70XP() {
        super(EncryptedString.of("A\u00ad_|\u00adu_\u00b7\u00b7|t_`\u00b7o\u00b7| ~-X`*^~P"), EncryptedString.of("A*\u00b7~u\u00ad^\u00ad|t^~-*o-.^^m*\u00b7a\u00ad\u00ad~t-$i\u00ad`c`|a|_|l_*-l-^y$.|\u00b7 *.\u00b7t\u00ad^h\u00b7^^r`|`*o\u00ad~^|w\u00b7~^*s_.`| _`X\u00b7-\u00adP`_\u00b7| ~^\u00b7_b\u00b7\u00b7o$.t_-\u00b7t\u00b7_$l_*e*~^*s$_` `_^.f$|$o^`\u00b7\u00b7r\u00b7*| --_-y~`o`\u00b7~u"), -1, Category.MISC);
        this.addSettings(this.delay, this.chance, this.clickSimulation);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.eventManager.add(ItemUseListener.class, this);
        this.clock = 0;
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
        if (this.mc.field_1755 != null) {
            return;
        }
        boolean dontThrow = this.clock != 0;
        int randomInt = MathUtils.randomInt(1, 100);
        if (this.mc.field_1724.method_6047().method_7909() != class_1802.field_8287) {
            return;
        }
        if (GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)1) != 1) {
            return;
        }
        if (dontThrow) {
            --this.clock;
        }
        if (!dontThrow && randomInt <= this.chance.getValueInt()) {
            class_1269 result;
            if (this.clickSimulation.getValue()) {
                MouseSimulation.mouseClick(1);
            }
            if ((result = this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, class_1268.field_5808)).method_23665()) {
                this.mc.field_1724.method_6104(class_1268.field_5808);
            }
            this.clock = this.delay.getValueInt();
        }
    }

    @Override
    public void onItemUse(ItemUseListener.ItemUseEvent event) {
        if (this.mc.field_1724.method_6047().method_7909() == class_1802.field_8287) {
            event.cancel();
        }
    }
}

