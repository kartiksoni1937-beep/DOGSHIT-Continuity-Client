/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.AttackListener;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.MouseSimulation;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1802;
import net.minecraft.class_239;
import net.minecraft.class_3966;
import org.lwjgl.glfw.GLFW;

public final class _5h131dD154813r
extends Module
implements TickListener,
AttackListener {
    private final SliderSetting hitDelay = new SliderSetting(EncryptedString.of("H\u00b7`\u00b7i|\u00ad$t^\u00ad .\u00ad^D*.e.\u00b7.l|*`a\u00ad$`~y"), 0.0, 20.0, 0.0, 1.0);
    private final SliderSetting switchDelay = new SliderSetting(EncryptedString.of("S_\u00b7w\u00ad|\u00b7-i~\u00ad_|t.*c|**h\u00ad$\u00b7. ^-D~*e`*|.l.|-.a$\u00ad*\u00b7y"), 0.0, 20.0, 0.0, 1.0);
    private final BooleanSetting switchBack = new BooleanSetting(EncryptedString.of("S*||`w`$~\u00adi^\u00ad._t$~~c~_\u00b7`h*\u00b7 .~$B*$a||$`c*|k"), true);
    private final BooleanSetting stun = new BooleanSetting(EncryptedString.of("S$$t_^*`u`-~n"), false);
    private final BooleanSetting clickSimulate = new BooleanSetting(EncryptedString.of("C^~\u00ad^l_$^i_^\u00adc.-^^k\u00b7$ \u00b7.S^$_i*._m\u00ad$--u.|.`l^|~`a$\u00b7*t._i``\u00ad.o-_n"), false);
    private final BooleanSetting requireHoldAxe = new BooleanSetting(EncryptedString.of("H|-o~_--l$$\u00b7.d$| ^*A^*^x.\u00ade"), false);
    int previousSlot;
    int hitClock;
    int switchClock;

    public _5h131dD154813r() {
        super(EncryptedString.of("S`$h**i_.\u00ade$^~$l~*\u00add_..| ``D|-_~i\u00b7`s~\u00ad^~a`.b^\u00ad`_l^.e_^r"), EncryptedString.of("A~\u00b7**u_^t^_`o|_m_*a\u00ad.-`t^\u00ad-i-$\u00b7c*\u00ada`\u00adl._`*l$-~y`_| \u00ad\u00b7d|$_~i$~s||_a*_*.b\u00b7\u00ad$l*^~e*_~.s-|$ _*y`\u00b7o~_u-~\u00adr`. |.o`_~~p~~_p^`o~_^n_^^e\u00ad\u00b7.n^\u00ad^t^_.s.^ |-`s\u00b7`*-h^$*_i\u00b7$_-e*^\u00ad^l.*d"), -1, Category.COMBAT);
        this.addSettings(this.switchDelay, this.hitDelay, this.switchBack, this.stun, this.clickSimulate, this.requireHoldAxe);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.eventManager.add(AttackListener.class, this);
        this.hitClock = this.hitDelay.getValueInt();
        this.switchClock = this.switchDelay.getValueInt();
        this.previousSlot = -1;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.eventManager.remove(AttackListener.class, this);
        super.onDisable();
    }

    @Override
    public void onTick() {
        class_1657 player;
        class_3966 entityHit;
        class_1297 entity;
        if (this.mc.field_1755 != null) {
            return;
        }
        if (this.requireHoldAxe.getValue() && !(this.mc.field_1724.method_6047().method_7909() instanceof class_1743)) {
            return;
        }
        if (this.mc.field_1724.method_6115()) {
            return;
        }
        boolean targetBlocking = false;
        class_239 class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3966 && (entity = (entityHit = (class_3966)class_2392).method_17782()) instanceof class_1657 && !WorldUtils.isShieldFacingAway(player = (class_1657)entity) && player.method_24518(class_1802.field_8255) && player.method_6039()) {
            targetBlocking = true;
            if (this.switchClock > 0) {
                if (this.previousSlot == -1) {
                    this.previousSlot = this.mc.field_1724.method_31548().method_67532();
                }
                --this.switchClock;
                return;
            }
            if (InventoryUtils.selectAxe()) {
                if (this.hitClock > 0) {
                    --this.hitClock;
                } else {
                    if (this.clickSimulate.getValue()) {
                        MouseSimulation.mouseClick(0);
                    }
                    WorldUtils.hitEntity((class_1297)player, true);
                    if (this.stun.getValue()) {
                        if (this.clickSimulate.getValue()) {
                            MouseSimulation.mouseClick(0);
                        }
                        WorldUtils.hitEntity((class_1297)player, true);
                    }
                    this.hitClock = this.hitDelay.getValueInt();
                    this.switchClock = this.switchDelay.getValueInt();
                }
            }
        }
        if (!targetBlocking && this.previousSlot != -1) {
            if (this.switchBack.getValue()) {
                InventoryUtils.setInvSlot(this.previousSlot);
            }
            this.previousSlot = -1;
        }
    }

    @Override
    public void onAttack(AttackListener.AttackEvent event) {
        if (GLFW.glfwGetMouseButton((long)this.mc.method_22683().method_4490(), (int)0) != 1) {
            event.cancel();
        }
    }
}

