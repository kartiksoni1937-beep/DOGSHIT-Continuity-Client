/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.awt.Color;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.AttackListener;
import me.pepperbell.continuity.event.events.GameRenderListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.RotationUtils;
import me.pepperbell.continuity.utils.TimerUtils;
import me.pepperbell.continuity.utils.rotation.Rotation;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_2596;
import net.minecraft.class_2848;
import net.minecraft.class_3489;
import net.minecraft.class_3532;

public final class Kn0ck84ckD15p14c3m3n7
extends Module
implements AttackListener,
GameRenderListener {
    private final ModeSetting<Mode> direction = new ModeSetting<Mode>(EncryptedString.of("D|\u00b7~i-$r~-~e.$`.c~|.t*`i$-o|`~n"), Mode.Left, Mode.class);
    private final SliderSetting cooldown = new SliderSetting(EncryptedString.of("C~~.\u00b7o\u00b7~o~--l\u00b7\u00b7d_*`o\u00b7^_w_*`~n"), 0.0, 1000.0, 0.0, 50.0);
    private final SliderSetting angle = new SliderSetting(EncryptedString.of("A\u00b7_|_n$~`g\u00b7-\u00adl\u00b7`e"), 10.0, 180.0, 90.0, 5.0);
    public final BooleanSetting hideRotation = new BooleanSetting(EncryptedString.of("H._i\u00ad^\u00b7d~\u00b7`e|`| -.r\u00b7^o..\u00b7_t`__a.|`t`~-i\u00b7_^|o.*\u00ad|n~\u00b7 *-\u00adf\u00ad^\u00ad-r-.o*`m^\u00b7$^ ~~\u00b7\u00b7p*$$l**a^$^y|\u00ad`e\u00b7*|r~*s"), true);
    private final BooleanSetting sprintReset = new BooleanSetting(EncryptedString.of("A``_~u_|t*`o\u00ad|$ ~^_.S$^p\u00ad^r._.i^\u00ad\u00adn_$t\u00b7- -.R---e$_-s_^|e_.*t"), true);
    private final BooleanSetting notTakingKnockback = new BooleanSetting(EncryptedString.of("N^$o_^|t.` .--t---^a\u00ad|~k_-_.i^`^n$_\u00b7g``^ -`k$\u00b7.n^^o^$c\u00b7$-k-~`b^~_a.\u00ad^c.|-$k"), false);
    private final BooleanSetting holdingWeapon = new BooleanSetting(EncryptedString.of("H~$o|\u00b7^l\u00ad--.d\u00ad^i\u00ad$*n^\u00adg$-* `_`w$\u00b7e^_a$\u00ad~p*~o-\u00ad~n"), false);
    private final BooleanSetting knockbackEnch = new BooleanSetting(EncryptedString.of("K$...n^|o--^\u00adc^-*-k*$b*~.a~-c|$*k\u00ad\u00b7 _\u00ad$-e$-|n\u00ad\u00b7c\u00ad~.h-|^a^\u00b7\u00ad*n\u00ad.t\u00ad__m._\u00b7\u00ade`-\u00b7n~.\u00adt"), false);
    private final BooleanSetting targetFacingAway = new BooleanSetting(EncryptedString.of("T\u00b7$a^_r^~-^g~-e._`|t-_. ^$~\u00b7f$-a-|^c_*\u00b7i.~_^n\u00b7~..g*\u00b7$` \u00ad^`a**||w-*a^$y"), false);
    private final TimerUtils cooldownTimer = new TimerUtils();
    public static Kn0ck84ckD15p14c3m3n7 INSTANCE;

    public Kn0ck84ckD15p14c3m3n7() {
        super(EncryptedString.of("K-$-n`~$o\u00ad-|c|~k$.\u00b7\u00b7b^\u00ada`-c_\u00ad\u00b7k.^\u00b7| ^.$_D`^i_^||s\u00ad`|*p`~l`\u00ad^|a_$-$c$|e"), EncryptedString.of("A**~.l-|l~_o~`.w_\u00ads^$$ ^-.d$^e*~\u00ada\u00ad`l`^i\u00b7_\u00b7$n_\u00ad`_g-^ ^-~`k*^n.._.o^..|c.$*$k^-$b^\u00ada-\u00ad^c_-k\u00b7~. ^..a\u00b7~t^~` -`-\u00b7a\u00b7\u00ad* _\u00add$_|i$$~.f_`f$~\u00b7e|-**r~$e~_n_.t`. \u00b7\u00ad*a\u00ad*~n$^g..*l*~*e"), -1, Category.COMBAT);
        INSTANCE = this;
        this.addSettings(this.direction, this.cooldown, this.angle, this.hideRotation, this.sprintReset, this.notTakingKnockback, this.holdingWeapon, this.knockbackEnch, this.targetFacingAway);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(AttackListener.class, this);
        this.eventManager.add(GameRenderListener.class, this);
        this.cooldownTimer.reset();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(AttackListener.class, this);
        this.eventManager.remove(GameRenderListener.class, this);
        Sweto.INSTANCE.rotatorManager.reset();
        super.onDisable();
    }

    private boolean checkConditions(class_1297 target) {
        float yawDiff;
        boolean hasKb;
        if (this.mc.field_1724 == null) {
            return false;
        }
        if (this.notTakingKnockback.getValue() && this.mc.field_1724.field_6235 > 0) {
            return false;
        }
        if (this.holdingWeapon.getValue() && !this.mc.field_1724.method_6047().method_31573(class_3489.field_42611) && !this.mc.field_1724.method_6047().method_31573(class_3489.field_42612)) {
            return false;
        }
        if (this.knockbackEnch.getValue() && !(hasKb = this.mc.field_1724.method_6047().method_58657().method_57534().stream().anyMatch(e -> e.method_55840().contains("knockback")))) {
            return false;
        }
        if (this.targetFacingAway.getValue() && (yawDiff = Math.abs(class_3532.method_15393((float)(this.mc.field_1724.method_36454() - target.method_36454())))) > 90.0f) {
            return false;
        }
        return this.cooldownTimer.hasReached(this.cooldown.getValueFloat());
    }

    @Override
    public void onAttack(AttackListener.AttackEvent event) {
        if (this.mc.field_1724 == null || this.mc.field_1692 == null) {
            return;
        }
        if (!this.checkConditions(this.mc.field_1692)) {
            return;
        }
        class_1297 target = this.mc.field_1692;
        float offset = (float)this.angle.getValue();
        float yawMod = this.getYawModifier(offset);
        Rotation targetRotation = RotationUtils.getRotationToVec(RotationUtils.getEyesPos((class_1657)this.mc.field_1724), target.method_5829().method_1005());
        float displacementYaw = targetRotation.yaw() + yawMod;
        Rotation safeRotation = RotationUtils.applySensitivity(new Rotation(displacementYaw, targetRotation.pitch()), new Rotation(this.mc.field_1724.method_36454(), this.mc.field_1724.method_36455()));
        Sweto.INSTANCE.rotatorManager.setRotation(safeRotation);
        if (this.sprintReset.getValue() && this.mc.field_1724.method_5624()) {
            this.mc.field_1724.method_5728(false);
            this.mc.method_1562().method_52787((class_2596)new class_2848((class_1297)this.mc.field_1724, class_2848.class_2849.field_12985));
        }
        this.cooldownTimer.reset();
        new Thread(() -> {
            try {
                Thread.sleep(150L);
                Sweto.INSTANCE.rotatorManager.reset();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }).start();
    }

    private float getYawModifier(float offset) {
        switch (this.direction.getMode().ordinal()) {
            case 0: {
                return -offset;
            }
            case 1: {
                return offset;
            }
            case 2: {
                return (float)(Math.random() > 0.5 ? 1 : -1) * offset;
            }
            case 3: {
                return 180.0f;
            }
        }
        return 0.0f;
    }

    @Override
    public void onGameRender(GameRenderListener.GameRenderEvent event) {
        if (this.mc.field_1724 == null || this.mc.field_1692 == null || !this.isEnabled()) {
            return;
        }
        class_1297 target = this.mc.field_1692;
        if (!(target instanceof class_1309)) {
            return;
        }
        float offset = (float)this.angle.getValue();
        float yawMod = this.getYawModifier(offset);
        Rotation targetRotation = RotationUtils.getRotationToVec(RotationUtils.getEyesPos((class_1657)this.mc.field_1724), target.method_5829().method_1005());
        float displacementYaw = targetRotation.yaw() + yawMod;
        float radYaw = (float)Math.toRadians(displacementYaw + 90.0f);
        double kbX = Math.cos(radYaw) * 2.5;
        double kbZ = Math.sin(radYaw) * 2.5;
        class_238 predictedBox = target.method_5829().method_989(kbX, 0.0, kbZ);
        RenderUtils.renderFilledBox(event.matrices, (float)predictedBox.field_1323, (float)predictedBox.field_1322, (float)predictedBox.field_1321, (float)predictedBox.field_1320, (float)predictedBox.field_1325, (float)predictedBox.field_1324, new Color(255, 100, 255, 80));
        RenderUtils.renderBoxOutline(event.matrices, predictedBox, new Color(255, 100, 255, 255), 1.5f);
    }

    public static enum Mode {
        Left,
        Right,
        Random,
        Behind;

    }
}

