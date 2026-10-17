/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.PacketSendListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_3966;

public final class Cry57410p71m1z3r
extends Module
implements PacketSendListener {
    public Cry57410p71m1z3r() {
        super(EncryptedString.of("C|__~r\u00b7\u00b7.|y\u00b7`||s\u00b7$`t*\u00ada__\u00b7l|~-$ $`.-O\u00ad\u00b7p^.t\u00ad\u00ad$i~$m^-_\u00adi`.z_^e|$r"), EncryptedString.of("M~\u00b7\u00b7.a~^$k--e.\u00b7`s\u00b7|_ \u00b7-y`*-\u00ado\u00ad$\u00b7^u^-r-\u00b7^$ ^$-`c^.r-|\u00b7*y.~$s$-``t\u00b7$a|`~-l^$s*^ *`d^\u00b7i\u00b7--s^$_a.\u00b7p._*p\u00b7`^e--a$^|r*$ ^$~^f$_a-$\u00b7s--~~t*`|\u00b7e_$\u00adr**\u00b7| *_*c**-_l$_~i~.\u00b7e-_^n\u00ad\u00b7.t\u00ad*~$s^-_^i$_d*\u00ade^.. $|*\u00ads~\u00b7~o_*`^ \u00b7***y`^.^o-^~u`~ ^^`c_.a_|`n~- \u00b7`p`_*|l\u00b7.~a\u00ad$.c-`^|e`*$- -\u00b7-c-_$.r|_y~.s$\u00adt$`a\u00ad_l|$^s~| $.^~f\u00b7$a|$s_._t|_.^e\u00ad$r"), -1, Category.COMBAT);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(PacketSendListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(PacketSendListener.class, this);
        super.onDisable();
    }

    @Override
    public void onPacketSend(PacketSendListener.PacketSendEvent event) {
        class_2596 class_25962 = event.packet;
        if (class_25962 instanceof class_2824) {
            class_2824 interactPacket = (class_2824)class_25962;
            interactPacket.method_34209(new class_2824.class_5908(){

                public void method_34219(class_1268 hand) {
                }

                public void method_34220(class_1268 hand, class_243 pos) {
                }

                public void method_34218() {
                    class_3966 hit;
                    class_239 class_2392;
                    if (((Cry57410p71m1z3r)Cry57410p71m1z3r.this).mc.field_1765 == null) {
                        return;
                    }
                    if (((Cry57410p71m1z3r)Cry57410p71m1z3r.this).mc.field_1765.method_17783() == class_239.class_240.field_1331 && (class_2392 = ((Cry57410p71m1z3r)Cry57410p71m1z3r.this).mc.field_1765) instanceof class_3966 && (hit = (class_3966)class_2392).method_17782() instanceof class_1511) {
                        class_1293 weakness = ((Cry57410p71m1z3r)Cry57410p71m1z3r.this).mc.field_1724.method_6112(class_1294.field_5911);
                        class_1293 strength = ((Cry57410p71m1z3r)Cry57410p71m1z3r.this).mc.field_1724.method_6112(class_1294.field_5910);
                        if (!(weakness == null || strength != null && strength.method_5578() > weakness.method_5578() || WorldUtils.isTool(((Cry57410p71m1z3r)Cry57410p71m1z3r.this).mc.field_1724.method_6047()))) {
                            return;
                        }
                        hit.method_17782().method_31472();
                        hit.method_17782().method_31745(class_1297.class_5529.field_26998);
                        hit.method_17782().method_36209();
                    }
                }
            });
        }
    }
}

