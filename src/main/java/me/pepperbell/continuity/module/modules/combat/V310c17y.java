/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.PacketReceiveListener;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.MathUtils;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2664;
import net.minecraft.class_2743;

public final class V310c17y
extends Module
implements PacketReceiveListener,
TickListener {
    private final ModeSetting<VelocityMode> mode = new ModeSetting<VelocityMode>(EncryptedString.of("M$\u00b7_o-\u00ad^d$._~e"), VelocityMode.Simple, VelocityMode.class);
    private final SliderSetting horizontal = (SliderSetting)new SliderSetting(EncryptedString.of("H^*``o-\u00b7~r_.~*i\u00b7~_`z*^o``n\u00ad_t^-.$a`..l"), 0.0, 100.0, 80.0, 1.0).visible(() -> this.mode.getMode() == VelocityMode.Simple);
    private final SliderSetting vertical = (SliderSetting)new SliderSetting(EncryptedString.of("V*\u00b7\u00ad.e``\u00adr*|`t`$i^^c_~_$a``-l"), 0.0, 100.0, 100.0, 1.0).visible(() -> this.mode.getMode() == VelocityMode.Simple);
    private final SliderSetting chance = new SliderSetting(EncryptedString.of("C_^~~h~\u00b7a-_\u00ad-n^^c|~e"), 0.0, 100.0, 100.0, 1.0);

    public V310c17y() {
        super(EncryptedString.of("V^**~e~-l\u00b7\u00b7.o--\u00adc\u00ad|`i```_t|\u00b7y"), EncryptedString.of("M\u00ad\u00b7o.`d*-i_\u00b7-f^`i*\u00ad._e_$~_s.\u00ad -$t~*\u00b7^h$^e\u00b7\u00b7 $\u00adk__\u00ad|n_\u00ad-o\u00ad||~c|\u00adk$.*\u00adb|-^a$*c__\u00b7k~*\u00ad^ --y_*|\u00b7o-~~u-_^_ $-|t*.\u00ada$$k*\u00b7e"), -1, Category.COMBAT);
        this.addSettings(this.mode, this.horizontal, this.vertical, this.chance);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(PacketReceiveListener.class, this);
        this.eventManager.add(TickListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(PacketReceiveListener.class, this);
        this.eventManager.remove(TickListener.class, this);
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (this.mode.getMode() == VelocityMode.Jump && this.mc.field_1724.field_6235 == 9 && this.mc.field_1724.method_24828() && MathUtils.randomInt(1, 100) <= this.chance.getValueInt()) {
            this.mc.field_1724.method_6043();
        }
    }

    @Override
    public void onPacketReceive(PacketReceiveListener.PacketReceiveEvent event) {
        if (this.mc.field_1724 == null) {
            return;
        }
        if (MathUtils.randomInt(1, 100) > this.chance.getValueInt()) {
            return;
        }
        class_2596 p = event.packet;
        if (p instanceof class_2743) {
            class_2743 packet = (class_2743)p;
            if (packet.method_11818() == this.mc.field_1724.method_5628()) {
                if (this.mode.getMode() == VelocityMode.Packet) {
                    event.cancel();
                } else if (this.mode.getMode() == VelocityMode.Simple) {
                    double h = this.horizontal.getValue() / 100.0;
                    double v = this.vertical.getValue() / 100.0;
                    if (h == 0.0 && v == 0.0) {
                        event.cancel();
                    } else {
                        event.cancel();
                        class_243 vel = packet.method_73085();
                        this.mc.field_1724.method_18800(vel.method_10216() * h, vel.method_10214() * v, vel.method_10215() * h);
                    }
                }
            }
        } else if (p instanceof class_2664) {
            class_2664 packet = (class_2664)p;
            if (this.mode.getMode() == VelocityMode.Packet) {
                this.mc.field_1724.method_18800(0.0, 0.0, 0.0);
            } else if (this.mode.getMode() == VelocityMode.Simple) {
                double h = this.horizontal.getValue() / 100.0;
                double v = this.vertical.getValue() / 100.0;
                class_243 currentVel = this.mc.field_1724.method_18798();
                this.mc.field_1724.method_18800(currentVel.method_10216() * h, currentVel.method_10214() * v, currentVel.method_10215() * h);
            }
        }
    }

    public static enum VelocityMode {
        Simple,
        Jump,
        Packet;

    }
}

