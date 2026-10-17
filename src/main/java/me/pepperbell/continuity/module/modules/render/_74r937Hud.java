/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.render;

import java.awt.Color;
import me.pepperbell.continuity.event.events.HudListener;
import me.pepperbell.continuity.event.events.PacketSendListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.AnimationUtils;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.MathUtils;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import me.pepperbell.continuity.utils.Utils;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_332;
import net.minecraft.class_640;
import net.minecraft.class_7532;
import net.minecraft.class_8685;
import org.joml.Matrix3x2fStack;

public final class _74r937Hud
extends Module
implements HudListener,
PacketSendListener {
    private final SliderSetting xCoord = new SliderSetting(EncryptedString.of("X"), 0.0, 1920.0, 500.0, 1.0);
    private final SliderSetting yCoord = new SliderSetting(EncryptedString.of("Y"), 0.0, 1080.0, 500.0, 1.0);
    private final BooleanSetting hudTimeout = (BooleanSetting)new BooleanSetting(EncryptedString.of("T\u00b7-\u00adi`~`*m`-e^|_o\u00ad.~*u~*-|t"), true).setDescription(EncryptedString.of("T$~a^\u00ad\u00ad$r*``g.\u00ade_$t_| `~h|\u00ad_\u00adu\u00ad^d*-$. `^`-w\u00ad|~i\u00ad~l~__l$^ _^\u00add\u00b7`^i__-|s-\u00b7.~a__^-p\u00ad_p`*^$e|`^$a.$r\u00ad*. `\u00b7``a^\u00ad-.f~~.t\u00b7.e*~$\u00adr`^ `\u00ad.1*$$*0-__ ^$s`.|e..c.~$-o.\u00adn~|\u00b7_d*.`.s"));
    private long lastAttackTime = 0L;
    public static float animation;
    private static final long timeout = 10000L;
    private final AnimationUtils healthAnim = new AnimationUtils(0.0);

    public _74r937Hud() {
        super(EncryptedString.of("T\u00ad|$a\u00ad^.\u00adr\u00b7-~`g-\u00ad-~e`-\u00adt.^^ ~_H.-\u00b7U_\u00ad\u00adD"), EncryptedString.of("G..|i~$|v.\u00ad.e`$|s-_ .\u00ad\u00b7y|~o_`-u~$.* ~^~.i.`n.~*f~\u00b7-o^\u00ad$r^^$m..**a\u00ad*t^|-i^_`\u00ado-.-n*$$ \u00ad~a_^*|b\u00ad__o`^u$\u00b7t*``^ $.\u00ad_t-^`h*\u00ad|.e-\u00b7 *.e.$\u00b7`n.^~e\u00b7\u00adm-|-\u00ady\u00ad~_ ^\u00ad~p__-l-~a*`\u00ady^``e`\u00b7`r"), -1, Category.HUD);
        this.addSettings(this.xCoord, this.yCoord, this.hudTimeout);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(HudListener.class, this);
        this.eventManager.add(PacketSendListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(HudListener.class, this);
        this.eventManager.remove(PacketSendListener.class, this);
        super.onDisable();
    }

    @Override
    public void onRenderHud(HudListener.HudEvent event) {
        class_1657 player;
        class_1309 class_13092;
        class_332 context = event.context;
        int x = this.xCoord.getValueInt();
        int y = this.yCoord.getValueInt();
        RenderUtils.unscaledProjection(context, 1.0f);
        if ((!this.hudTimeout.getValue() || System.currentTimeMillis() - this.lastAttackTime <= 10000L) && this.mc.field_1724.method_6052() != null && (class_13092 = this.mc.field_1724.method_6052()) instanceof class_1657 && (player = (class_1657)class_13092).method_5805()) {
            class_1657 player1;
            class_1309 class_13093 = this.mc.field_1724.method_6052();
            animation = RenderUtils.fast(animation, !(class_13093 instanceof class_1657) || !(player1 = (class_1657)class_13093).method_5805() ? 1 : 0, 15.0f);
            class_640 entry = this.mc.method_1562().method_2871(player.method_5667());
            Matrix3x2fStack matrices = context.method_51448();
            matrices.pushMatrix();
            matrices.scaleAround(Math.max(0.0f, 1.0f - animation), 1.0f, (float)x + 90.0f, (float)y + 40.0f);
            RenderUtils.renderRoundedQuad(context, new Color(15, 15, 20, 180), x, y, x + 180, y + 80, 2.5, 2.5, 2.5, 2.5, 10.0);
            RenderUtils.renderRoundedQuad(context, Utils.getMainColor(255), x, y + 22, x + 180, y + 23, 0.0, 0.0, 0.0, 0.0, 10.0);
            TextRenderer.drawString(player.method_5477().getString(), context, x + 25, y + 6, Color.WHITE.getRGB());
            if (entry == null) {
                TextRenderer.drawString("Type: Bot", context, x + 5, y + 28, new Color(255, 80, 80, 255).getRGB());
                matrices.popMatrix();
                RenderUtils.scaledProjection(context);
                return;
            }
            TextRenderer.drawString("Health: " + Math.round(player.method_6032() + player.method_6067()), context, x + 5, y + 28, Color.GREEN.getRGB());
            double currentHealth = player.method_6032() + player.method_6067();
            double maxHealth = player.method_6063() + player.method_6067();
            double animatedHealth = this.healthAnim.animate(RenderUtils.tickProgress(), currentHealth, 15.0);
            float healthWidth = (float)(animatedHealth / maxHealth * 170.0);
            RenderUtils.renderRoundedQuad(context, Color.GREEN.darker(), x + 5, y + 38, x + 175, y + 40, 1.0, 1.0, 1.0, 1.0, 10.0);
            RenderUtils.renderRoundedQuad(context, Color.GREEN, x + 5, y + 38, (float)(x + 5) + healthWidth, y + 40, 1.0, 1.0, 1.0, 1.0, 10.0);
            TextRenderer.drawString("Ping: " + entry.method_2959() + "ms", context, x + 5, y + 45, Color.WHITE.getRGB());
            TextRenderer.drawString("Dist: " + MathUtils.roundToDecimal((double)player.method_5739((class_1297)this.mc.field_1724), 0.5) + "m", context, x + 80, y + 45, Color.WHITE.getRGB());
            class_7532.method_52722((class_332)context, (class_8685)entry.method_52810(), (int)(x + 3), (int)(y + 3), (int)16);
            if (player.field_6235 != 0) {
                context.method_25294(x + 5, y + 60, x + 5 + player.field_6235 * 17, y + 62, this.getDamageTickColor(player.field_6235).getRGB());
            }
            matrices.popMatrix();
        } else {
            animation = RenderUtils.fast(animation, 1.0f, 15.0f);
        }
        RenderUtils.scaledProjection(context);
    }

    private Color getDamageTickColor(int hurtTime) {
        return switch (hurtTime) {
            case 0 -> null;
            case 10 -> new Color(255, 0, 0, 255);
            case 9 -> new Color(255, 50, 0, 255);
            case 8 -> new Color(255, 100, 0, 255);
            case 7 -> new Color(255, 150, 0, 255);
            case 6 -> new Color(255, 255, 0, 255);
            case 5 -> new Color(200, 255, 0, 255);
            case 4 -> new Color(175, 255, 0, 255);
            case 3 -> new Color(100, 255, 0, 255);
            case 2 -> new Color(50, 255, 0, 255);
            case 1 -> new Color(0, 255, 0, 255);
            default -> throw new IllegalStateException("uv" + hurtTime);
        };
    }

    @Override
    public void onPacketSend(PacketSendListener.PacketSendEvent event) {
        class_2596 class_25962 = event.packet;
        if (class_25962 instanceof class_2824) {
            class_2824 packet = (class_2824)class_25962;
            packet.method_34209(new class_2824.class_5908(){

                public void method_34219(class_1268 hand) {
                }

                public void method_34220(class_1268 hand, class_243 pos) {
                }

                public void method_34218() {
                    if (((_74r937Hud)_74r937Hud.this).mc.field_1692 instanceof class_1657) {
                        _74r937Hud.this.lastAttackTime = System.currentTimeMillis();
                    }
                }
            });
        }
    }
}

