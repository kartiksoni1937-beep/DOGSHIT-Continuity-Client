/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.PacketSendListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.RotationUtils;
import me.pepperbell.continuity.utils.rotation.Rotation;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_3966;

public final class H1780x
extends Module
implements PacketSendListener {
    public final SliderSetting expand = (SliderSetting)new SliderSetting(EncryptedString.of("E`|`-x\u00ad|`-p*-|*a$\u00b7n^$d"), 0.0, 1.0, 0.12, 0.01).setDescription(EncryptedString.of("E\u00ad`$x\u00ad~$p|^.~a\u00ad|\u00ad^n`|d*^$ \u00ad$^t~.|h~*e`|\u00b7. -.`e`|*n*`t|*-~i\u00ad`*$t^^y~$^'\u00b7.\u00ads~-\u00b7 \u00ad*h\u00b7-$_i_*\u00ad_t\u00ad$*b$_o$^*~x"));
    public final BooleanSetting legitMode = (BooleanSetting)new BooleanSetting(EncryptedString.of("L_^e~--^g^.\u00ad\u00adi`|t\u00b7\u00b7 ~\u00b7M.*o_-\u00add.~e"), true).setDescription(EncryptedString.of("C\u00b7_a\u00ad^p`_^~s~` \u00ad-\u00ad^e`.x-~p$\u00b7~`a-|\u00ad_n__$$s_$$i-.o\u00b7-$n|^ $_^|a^|_~t$_ ~~\u00ad0\u00ad_\u00ad|1|~`~ ^*|f_.\u00ad*o`\u00b7^r_\u00ad ^$\u00b7-a\u00b7|n\u00ad\u00ad.t.\u00ad$i_^*`c$\u00ad*h|-\u00b7e\u00b7.\u00ad\u00ada-*$t*||` ~-*b*_$y|~.-p.|.a^.*.s\u00ad~$*s"));
    public final BooleanSetting silentRotation = (BooleanSetting)new BooleanSetting(EncryptedString.of("S|`*\u00b7i~\u00adl\u00b7\u00ade*$-$n$..\u00adt.\u00ad ^*.R^~-o-$t_\u00ad^\u00b7a_\u00adt-$-.i\u00b7~o_~n"), true).setDescription(EncryptedString.of("S__^*i_.*`l\u00ad-\u00b7e$._n$`t^*.|l^|_y^* `^|\u00b7r^``.o|.*~t$|*\u00b7a^--_t`-e$`s$__^ \u00ad-`t.~-o\u00ad\u00ad $`-`t$*-h\u00ad\u00b7_e^$\u00b7 ^-`|e__-.n|`t`\u00b7.i^*\u00b7t.-y``` \u00b7\u00ad\u00b7o~\u00b7\u00ad^n-`$ _\u00b7a\u00b7_t_*t--~\u00ada\u00ad*|*c`\u00b7$k_^ `$|~t`\u00ado.\u00ad* _--b_~\u00ad*y^_\u00ad-p\u00b7-a$^*-s\u00ad`$s*^ ^^*^S\u00ad$`i|\u00b7m~_.\u00adu||$l-|a-^~t~_^`i_.~\u00ado^.n*\u00ad*` ^**f~~l~$a`-~\u00adg`_|s"));

    public H1780x() {
        super(EncryptedString.of("H|.^$i~^\u00b7t.`b~$o`-`x"), EncryptedString.of("E`$_x_\u00b7|$p.~$a`^-\u00b7n*`d`|_\u00ads`` **_$e$_n~\u00b7^t$\u00ad^i^|\u00b7t||-y.\u00ad`| -$h__i.`|\u00b7t\u00ad`\u00ad_b||\u00b7-o-\u00b7x$*\u00b7e|$^-s"), -1, Category.COMBAT);
        this.addSettings(this.expand, this.legitMode, this.silentRotation);
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
        if (this.mc.field_1724 == null || this.mc.field_1687 == null || !this.silentRotation.getValue()) {
            return;
        }
        class_2596 class_25962 = event.packet;
        if (class_25962 instanceof class_2824) {
            class_2824 packet = (class_2824)class_25962;
            class_239 class_2392 = this.mc.field_1765;
            if (class_2392 instanceof class_3966) {
                class_3966 hitResult = (class_3966)class_2392;
                if (this.mc.field_1765.method_17783() == class_239.class_240.field_1331) {
                    class_1297 entity = hitResult.method_17782();
                    class_243 eyes = RotationUtils.getEyesPos((class_1657)this.mc.field_1724);
                    class_243 lookVec = RotationUtils.getPlayerLookVec((class_1657)this.mc.field_1724);
                    class_243 closestPoint = RotationUtils.getClosestPointOnBox(eyes, lookVec, entity.method_5829());
                    Rotation rot = RotationUtils.getRotationToVec(eyes, closestPoint);
                    Rotation serverRot = Sweto.INSTANCE.rotatorManager.getServerRotation();
                    if (RotationUtils.getTotalDiff(serverRot, rot) > 0.1) {
                        this.mc.method_1562().method_52787((class_2596)new class_2828.class_2831(rot.yaw(), rot.pitch(), this.mc.field_1724.method_24828(), false));
                        Sweto.INSTANCE.rotatorManager.setRotation(rot);
                    }
                }
            }
        }
    }
}

