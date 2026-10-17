/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.render;

import java.awt.Color;
import me.pepperbell.continuity.event.events.GameRenderListener;
import me.pepperbell.continuity.event.events.HudListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.ProjectionUtils;
import me.pepperbell.continuity.utils.RenderUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_7833;
import org.joml.Quaternionfc;

public final class P14y3r35P
extends Module
implements GameRenderListener,
HudListener {
    public final ModeSetting<Mode> mode = new ModeSetting<Mode>(EncryptedString.of("M\u00ad*\u00ado$\u00b7d.~_*e"), Mode.ThreeD, Mode.class);
    private final SliderSetting alpha = new SliderSetting(EncryptedString.of("A`\u00b7l_^p~|h`\u00ad^a"), 0.0, 255.0, 100.0, 1.0);
    private final SliderSetting width = new SliderSetting(EncryptedString.of("L\u00ad.`i^~n$.e^`\u00ad~ \u00b7`w\u00ad.|\u00adi\u00ad*d^\u00ad`-t.|^h"), 1.0, 10.0, 1.0, 1.0);
    private final BooleanSetting tracers = (BooleanSetting)new BooleanSetting(EncryptedString.of("T$\u00b7~r.$a\u00b7\u00ad*c-\u00b7\u00b7e$~r\u00ad$s"), false).setDescription(EncryptedString.of("D.$r|$a\u00b7\u00adw*\u00b7|s\u00b7~\u00b7- *.a~.\u00ad_ ~~-l\u00ad``i-._$n\u00ad\u00b7.`e|| _|f^..-r||o~\u00adm^-| *\u00ad^y^\u00b7|-o\u00b7$u-|r_*`_ `-\u00b7_p.`-_l^^a$\u00b7~y.`.\u00ade*-r^$\u00ad ~|t*$o\u00b7_ ^-t.$h^`$e`` \u00b7-o|`$t$~$h*\u00ade|\u00ad.r"));
    private final BooleanSetting threeDOutline = (BooleanSetting)new BooleanSetting(EncryptedString.of("3|~D~- ~-*b`$~o.\u00b7*x$||\u00b7 .-o\u00ad|u-`t-$*l.~.i..`*n*.$\u00b7e"), false).visible(() -> this.mode.getMode() == Mode.ThreeD);
    private final BooleanSetting twoDOutline = (BooleanSetting)new BooleanSetting(EncryptedString.of("2\u00b7***D\u00ad-* |.\u00adO\u00b7`^u\u00ad~~^t\u00b7$\u00ad_l$\u00ad~\u00b7i_-\u00b7n$-e"), false).visible(() -> this.mode.getMode() == Mode.TwoD);

    public P14y3r35P() {
        super(EncryptedString.of("P~.`l.|a~_\u00b7y..|_e..$r*_` ^\u00b7E$^S|$P"), EncryptedString.of("R`.~$e.^`n$||d~-e\u00b7$`\u00b7r_-s~. \u00b7|p$|_l*\u00b7a|^y-\u00b7`e\u00ad`r^~\u00ad\u00ads_``* |_~t\u00ad\u00b7h\u00b7|$r\u00b7**`o._*u*|g`\u00b7h_\u00ad`\u00b7 \u00b7-^.w\u00ad_a~~\u00b7l-.l_^s"), -1, Category.RENDER);
        this.addSettings(this.alpha, this.mode, this.threeDOutline, this.twoDOutline, this.width, this.tracers);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(GameRenderListener.class, this);
        this.eventManager.add(HudListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(GameRenderListener.class, this);
        this.eventManager.remove(HudListener.class, this);
        super.onDisable();
    }

    @Override
    public void onGameRender(GameRenderListener.GameRenderEvent event) {
        if (this.mc.field_1687 == null || this.mc.field_1724 == null) {
            return;
        }
        class_4184 camera = this.mc.field_1773.method_19418();
        if (camera == null) {
            return;
        }
        event.matrices.method_22903();
        class_243 cameraPos = camera.method_71156();
        event.matrices.method_22907((Quaternionfc)class_7833.field_40714.rotationDegrees(camera.method_19329()));
        event.matrices.method_22907((Quaternionfc)class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
        event.matrices.method_22904(-cameraPos.field_1352, -cameraPos.field_1351, -cameraPos.field_1350);
        for (class_1657 player : this.mc.field_1687.method_18456()) {
            if (!this.shouldRender((class_1297)player)) continue;
            class_238 box = this.getRenderBox(player, event.delta).method_1014(0.02);
            if (this.mode.isMode(Mode.ThreeD)) {
                RenderUtils.renderFilledBox(event.matrices, (float)box.field_1323, (float)box.field_1322, (float)box.field_1321, (float)box.field_1320, (float)box.field_1325, (float)box.field_1324, new Color(160, 160, 160, this.alpha.getValueInt()));
                if (this.threeDOutline.getValue()) {
                    RenderUtils.renderBoxOutline(event.matrices, box, new Color(255, 255, 255, 255), this.width.getValueInt());
                }
            }
            if (!this.tracers.getValue() || this.mc.field_1765 == null) continue;
            RenderUtils.renderLine(event.matrices, new Color(200, 200, 200, 255), this.mc.field_1765.method_17784(), player.method_30950(RenderUtils.tickProgress()));
        }
        event.matrices.method_22909();
    }

    @Override
    public void onRenderHud(HudListener.HudEvent event) {
        if (!this.mode.isMode(Mode.TwoD) || this.mc.field_1687 == null || this.mc.field_1724 == null) {
            return;
        }
        for (class_1657 player : this.mc.field_1687.method_18456()) {
            ScreenBounds bounds;
            if (!this.shouldRender((class_1297)player) || (bounds = this.projectBounds(this.getRenderBox(player, event.delta))) == null || !bounds.isValid()) continue;
            Color outlineColor = new Color(255, 255, 255, 255);
            int thickness = Math.max(1, this.width.getValueInt());
            if (this.twoDOutline.getValue()) {
                Color borderColor = new Color(80, 80, 80, 255);
                this.drawOutline(event.context, bounds, thickness + 1, borderColor);
            }
            this.drawOutline(event.context, bounds, thickness, outlineColor);
        }
    }

    private boolean shouldRender(class_1297 entity) {
        class_1657 player;
        return entity instanceof class_1657 && (player = (class_1657)entity) != this.mc.field_1724 && player.method_5805();
    }

    private class_238 getRenderBox(class_1657 player, float tickDelta) {
        double x = class_3532.method_16436((double)tickDelta, (double)player.field_6014, (double)player.method_23317());
        double y = class_3532.method_16436((double)tickDelta, (double)player.field_6036, (double)player.method_23318());
        double z = class_3532.method_16436((double)tickDelta, (double)player.field_5969, (double)player.method_23321());
        return player.method_5829().method_989(x - player.method_23317(), y - player.method_23318(), z - player.method_23321());
    }

    private ScreenBounds projectBounds(class_238 box) {
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        int visibleCorners = 0;
        for (double x : new double[]{box.field_1323, box.field_1320}) {
            for (double y : new double[]{box.field_1322, box.field_1325}) {
                for (double z : new double[]{box.field_1321, box.field_1324}) {
                    ProjectionUtils.ProjectedPoint projected = ProjectionUtils.project(new class_243(x, y, z));
                    if (projected == null) continue;
                    ++visibleCorners;
                    minX = Math.min(minX, (double)projected.x());
                    minY = Math.min(minY, (double)projected.y());
                    maxX = Math.max(maxX, (double)projected.x());
                    maxY = Math.max(maxY, (double)projected.y());
                }
            }
        }
        if (visibleCorners == 0) {
            return null;
        }
        return new ScreenBounds((int)Math.floor(minX), (int)Math.floor(minY), (int)Math.ceil(maxX), (int)Math.ceil(maxY));
    }

    private void drawOutline(class_332 context, ScreenBounds bounds, int thickness, Color color) {
        int packedColor = color.getRGB();
        context.method_25294(bounds.minX, bounds.minY, bounds.maxX, bounds.minY + thickness, packedColor);
        context.method_25294(bounds.minX, bounds.maxY - thickness, bounds.maxX, bounds.maxY, packedColor);
        context.method_25294(bounds.minX, bounds.minY + thickness, bounds.minX + thickness, bounds.maxY - thickness, packedColor);
        context.method_25294(bounds.maxX - thickness, bounds.minY + thickness, bounds.maxX, bounds.maxY - thickness, packedColor);
    }

    private Color getColor(int alpha) {
        return new Color(200, 200, 200, alpha);
    }

    public static enum Mode {
        TwoD,
        ThreeD;

    }

    private record ScreenBounds(int minX, int minY, int maxX, int maxY) {
        private boolean isValid() {
            return this.maxX > this.minX && this.maxY > this.minY;
        }
    }
}

