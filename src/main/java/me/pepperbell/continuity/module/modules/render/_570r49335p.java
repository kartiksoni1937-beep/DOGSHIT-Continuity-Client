/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.render;

import java.awt.Color;
import me.pepperbell.continuity.event.events.GameRenderListener;
import me.pepperbell.continuity.event.events.PacketReceiveListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_2595;
import net.minecraft.class_2605;
import net.minecraft.class_2611;
import net.minecraft.class_2627;
import net.minecraft.class_2636;
import net.minecraft.class_2637;
import net.minecraft.class_2646;
import net.minecraft.class_2818;
import net.minecraft.class_3719;
import net.minecraft.class_3866;
import net.minecraft.class_4184;
import net.minecraft.class_7833;
import org.joml.Quaternionfc;

public final class _570r49335p
extends Module
implements GameRenderListener,
PacketReceiveListener {
    private final SliderSetting alpha = new SliderSetting(EncryptedString.of("A^^l|$_|p.~_-h-^a"), 1.0, 255.0, 125.0, 1.0);
    private final BooleanSetting donutBypass = new BooleanSetting(EncryptedString.of("D\u00ad^o_*`n--\u00adu*|`-t*\u00b7 $__^B_~\u00b7`y\u00b7~p|-*_a*|*s~-^-s"), false);
    private final BooleanSetting tracers = (BooleanSetting)new BooleanSetting(EncryptedString.of("T-^r$-a$$\u00b7c\u00b7*e.$_`r$_\u00b7s"), false).setDescription(EncryptedString.of("D._*`r\u00b7|\u00ad^a|~__w^^s\u00b7||$ -\u00ad\u00b7.a\u00b7`- *|l~**i\u00b7^n.`e`\u00b7 -$*f^~\u00adr$|\u00ado\u00ad*\u00b7_m_| `|y_-.o^.~.u^\u00b7|\u00adr_-^\u00ad \u00b7^*p-$l\u00ad$a.`\u00b7_y|~e--`r*\u00ad*| $\u00b7t`_~$o|~* ~^t\u00ad\u00b7h-~`_e``.. _^.s*$t\u00b7\u00b7\u00ad\u00b7o..r\u00ad*-\u00b7a$`g$-^|e$|` ~$*-b~\u00ad^l\u00b7\u00b7|\u00b7o.^-*c-\u00b7k"));

    public _570r49335p() {
        super(EncryptedString.of("S~$t`-^o\u00b7_-_r$|.a`$g\u00b7\u00b7-e-$| $\u00b7\u00ad`E$^S\u00ad.^_P"), EncryptedString.of("R$$e_$_n~\u00ad^d$\u00b7\u00ade_\u00ad.|r\u00ad_-s$_\u00ad. -\u00b7\u00ads*.*.t.\u00b7.o*~~-r*\u00ada_\u00b7`~g.*e|_ ^$*`b..*l._o|^||c|~k~|_s-$ ~-`t~$\u00b7$h.\u00b7r_~\u00ad|o\u00b7\u00adu$^|`g|~\u00adh\u00b7` .`$\u00b7w`$~a-$|l|$``l_`s"), -1, Category.RENDER);
        this.addSettings(this.donutBypass, this.alpha, this.tracers);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(PacketReceiveListener.class, this);
        this.eventManager.add(GameRenderListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(PacketReceiveListener.class, this);
        this.eventManager.remove(GameRenderListener.class, this);
        super.onDisable();
    }

    @Override
    public void onGameRender(GameRenderListener.GameRenderEvent event) {
        this.renderStorages(event);
    }

    private Color getColor(class_2586 blockEntity, int a) {
        if (blockEntity instanceof class_2646) {
            return new Color(200, 91, 0, a);
        }
        if (blockEntity instanceof class_2595) {
            return new Color(156, 91, 0, a);
        }
        if (blockEntity instanceof class_2611) {
            return new Color(117, 0, 255, a);
        }
        if (blockEntity instanceof class_2636) {
            return new Color(138, 126, 166, a);
        }
        if (blockEntity instanceof class_2627) {
            return new Color(134, 0, 158, a);
        }
        if (blockEntity instanceof class_3866) {
            return new Color(125, 125, 125, a);
        }
        if (blockEntity instanceof class_3719) {
            return new Color(255, 140, 140, a);
        }
        if (blockEntity instanceof class_2605) {
            return new Color(80, 80, 255, a);
        }
        return new Color(255, 255, 255, 0);
    }

    private void renderStorages(GameRenderListener.GameRenderEvent event) {
        class_4184 cam = this.mc.field_1773.method_19418();
        if (cam == null) {
            return;
        }
        event.matrices.method_22903();
        class_243 cameraPos = cam.method_71156();
        event.matrices.method_22907((Quaternionfc)class_7833.field_40714.rotationDegrees(cam.method_19329()));
        event.matrices.method_22907((Quaternionfc)class_7833.field_40716.rotationDegrees(cam.method_19330() + 180.0f));
        event.matrices.method_22904(-cameraPos.field_1352, -cameraPos.field_1351, -cameraPos.field_1350);
        for (class_2818 chunk : WorldUtils.getLoadedChunks().toList()) {
            for (class_2338 blockPos : chunk.method_12021()) {
                class_2586 blockEntity = this.mc.field_1687.method_8321(blockPos);
                if (blockEntity == null) continue;
                RenderUtils.renderFilledBox(event.matrices, (float)blockPos.method_10263() + 0.1f, (float)blockPos.method_10264() + 0.05f, (float)blockPos.method_10260() + 0.1f, (float)blockPos.method_10263() + 0.9f, (float)blockPos.method_10264() + 0.85f, (float)blockPos.method_10260() + 0.9f, this.getColor(blockEntity, this.alpha.getValueInt()));
                class_243 center = new class_243((double)blockPos.method_10263() + 0.5, (double)blockPos.method_10264() + 0.5, (double)blockPos.method_10260() + 0.5);
                if (!this.tracers.getValue() || this.mc.field_1765 == null) continue;
                RenderUtils.renderLine(event.matrices, this.getColor(blockEntity, 255), this.mc.field_1765.method_17784(), center);
            }
        }
        event.matrices.method_22909();
    }

    @Override
    public void onPacketReceive(PacketReceiveListener.PacketReceiveEvent event) {
        if (this.donutBypass.getValue() && event.packet instanceof class_2637) {
            event.cancel();
        }
    }
}

