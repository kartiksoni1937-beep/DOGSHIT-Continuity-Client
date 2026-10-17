/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.misc;

import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.mixin.MinecraftClientAccessor;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.MathUtils;
import me.pepperbell.continuity.utils.MouseSimulation;
import me.pepperbell.continuity.utils.TimerUtils;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1792;
import net.minecraft.class_1811;
import net.minecraft.class_239;
import net.minecraft.class_9334;
import org.lwjgl.glfw.GLFW;

public final class _4u70C11ck3r
extends Module
implements TickListener {
    private final BooleanSetting onlyWeapon = (BooleanSetting)new BooleanSetting(EncryptedString.of("O$$_n|_$l~|\u00ady-~ \u00b7|W^~e*~\u00ada|`$|p\u00b7_\u00b7o-\u00b7n"), true).setDescription(EncryptedString.of("O-$_n$$l**y-\u00ad` ^`\u00adl|.\u00b7*e.|\u00ad|f_*-t.-* _$c*-_`l_~.i_*c.\u00b7k-$\u00b7\u00b7s.\u00b7* _`|w$`\u00b7^i_*_t\u00b7*h*. ~_\u00b7`w^_e.|a\u00b7`p--*\u00ado_\u00b7-n--\u00ad~ _\u00b7`-i$`$n`_~_ *.h*~a^_`n^^._d"));
    private final BooleanSetting onlyBlocks = (BooleanSetting)new BooleanSetting(EncryptedString.of("O*.$n^-..l$~y._\u00ad \u00b7$^\u00b7B`\u00adl`^$o^~c_|$*k|-\u00ad^s"), true).setDescription(EncryptedString.of("O.\u00b7n|^-\u00b7l$-y*\u00b7 ~^^~r^*i-^~g.`~h$$t^. ^\u00b7c`|\u00adl_.-i$.`c$^*-k^$~*s$$$_ *-^b_\u00b7-l^^$_o~\u00b7$`c*`|~k$|.$s"));
    private final BooleanSetting onClick = new BooleanSetting(EncryptedString.of("O~\u00b7*n.\u00b7_ \u00ad$C|*\u00b7\u00adl^\u00b7__i*$c\u00ad$k"), true);
    private final SliderSetting delay = new SliderSetting(EncryptedString.of("D`*e*$l\u00ad^-a_|\u00ad~y"), 0.0, 1000.0, 0.0, 1.0);
    private final SliderSetting chance = new SliderSetting(EncryptedString.of("C^^`\u00b7h`^$a$*\u00adn.^-$c~\u00ad`_e"), 0.0, 100.0, 100.0, 1.0);
    private final ModeSetting<Mode> mode = new ModeSetting<Mode>(EncryptedString.of("A.\u00adc-\u00b7t_*^\u00b7i-^^\u00ado^_n*-_^s"), Mode.All, Mode.class);
    private final TimerUtils timer = new TimerUtils();

    public _4u70C11ck3r() {
        super(EncryptedString.of("A.~|\u00adu$\u00ad-t*`o^\u00ad` \u00ad~~\u00b7C~\u00ad~^l-|-i\u00b7._c$.*k__.~e^$_r"), EncryptedString.of("A*$u$~$t`\u00b7*o*\u00b7\u00b7_m\u00b7$*.a.|$t\u00b7`.\u00adi\u00ad\u00ad_c*$$~a-\u00b7\u00b7$l-`$l`-|\u00ady-^~ *\u00ad|c$~~l^._~i\u00ad^\u00b7c~*k|_s|` \u00b7$\u00b7^f\u00ad.*\u00b7o|-\u00adr.\u00b7 \u00ad*~y`\u00ad_`o*\u00b7u"), -1, Category.MISC);
        this.addSettings(this.onlyWeapon, this.onClick, this.delay, this.chance, this.mode);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.timer.reset();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null) {
            return;
        }
        if (this.mc.field_1755 != null) {
            return;
        }
        if (this.mc.field_1765 == null) {
            return;
        }
        if (this.timer.delay(this.delay.getValueFloat()) && this.chance.getValueInt() >= MathUtils.randomInt(1, 100)) {
            if (this.mode.isMode(Mode.Left)) {
                this.performLeftClick();
            }
            if (this.mode.isMode(Mode.Right)) {
                this.performRightClick();
            }
            if (this.mode.isMode(Mode.All)) {
                this.performLeftClick();
                this.performRightClick();
            }
        }
    }

    private void performRightClick() {
        class_1792 mainhand = this.mc.field_1724.method_6047().method_7909();
        class_1792 offhand = this.mc.field_1724.method_6079().method_7909();
        if (mainhand.method_57347().method_57832(class_9334.field_50075)) {
            return;
        }
        if (offhand.method_57347().method_57832(class_9334.field_50075)) {
            return;
        }
        if (mainhand instanceof class_1811 || offhand instanceof class_1811) {
            return;
        }
        if (this.onClick.getValue() && GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)1) != 1) {
            return;
        }
        MouseSimulation.mouseClick(1);
        ((MinecraftClientAccessor)this.mc).invokeDoItemUse();
        this.timer.reset();
    }

    private void performLeftClick() {
        class_1792 mainhand = this.mc.field_1724.method_6047().method_7909();
        class_1792 offhand = this.mc.field_1724.method_6079().method_7909();
        if (this.mc.field_1765.method_17783() == class_239.class_240.field_1332) {
            return;
        }
        if (this.mc.field_1724.method_6115()) {
            return;
        }
        if (this.onlyWeapon.getValue() && !WorldUtils.isWeapon(this.mc.field_1724.method_6047())) {
            return;
        }
        if (this.onClick.getValue() && GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)0) != 1) {
            return;
        }
        MouseSimulation.mouseClick(0);
        ((MinecraftClientAccessor)this.mc).invokeDoAttack();
        this.timer.reset();
    }

    public static enum Mode {
        All,
        Left,
        Right;

    }
}

