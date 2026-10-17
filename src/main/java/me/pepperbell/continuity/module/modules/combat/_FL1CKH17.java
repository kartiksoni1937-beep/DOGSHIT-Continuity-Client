/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.Comparator;
import java.util.stream.StreamSupport;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.KeyUtils;
import me.pepperbell.continuity.utils.RotationUtils;
import me.pepperbell.continuity.utils.rotation.Rotation;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public final class _FL1CKH17
extends Module
implements TickListener {
    private final KeybindSetting activateKey = new KeybindSetting(EncryptedString.of("A`|.*c*$t~$|i\u00ad**v~*`a.**t$*e_* __`|K*`|e.`~y"), 1, false);
    private final SliderSetting range = new SliderSetting(EncryptedString.of("R\u00b7`\u00b7\u00b7a^-^n*$`g~^-$e"), 3.0, 6.0, 4.5, 0.1);
    private final SliderSetting speed = new SliderSetting(EncryptedString.of("S.*|\u0323^^p`_^.e^|-`e_`~\u00b7d"), 1.0, 20.0, 8.0, 1.0);
    private final BooleanSetting attack = new BooleanSetting(EncryptedString.of("A*.t\u00ad_\u00adt_-a\u00b7_c*\u00adk"), true);
    private final BooleanSetting lookBack = new BooleanSetting(EncryptedString.of("L*$\u00b7o^~*^o|*k|._\u00adB\u00ad^a^-.^c_|_k"), true);
    private boolean wasActivated;
    private float prevYaw;
    private float prevPitch;

    public _FL1CKH17() {
        super(EncryptedString.of("F|$l_.i_\u00b7$|c_~\u00ad`k-.^H_\u00ad-^i^~~t"), EncryptedString.of("F_-l||.|i^_*c_`|k_^\u00b7~ \u00ad_t.$$*o-_ $\u00b7\u00ad.t\u00ad`^h^.~e*`_ _^|n~$|e*`a^$$|r~\u00ad$e__^^s^\u00b7^_t_-\u00ad .|.p\u00ad\u00b7\u00b7l$`\u00b7.a_--_y\u00ad.e`|r\u00b7`-\u00b7 |\u00b7a`$`_n~-.d$\u00ad_ .~-a|.|t$`t\u00ad~a~\u00b7~|c`~_*k"), -1, Category.COMBAT);
        this.setSubCategory("Flick");
        this.addSettings(this.activateKey, this.range, this.speed, this.attack, this.lookBack);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.wasActivated = false;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        boolean held = KeyUtils.isKeyPressed(this.activateKey.getKey());
        if (held) {
            class_1297 target;
            if (!this.wasActivated) {
                this.prevYaw = this.mc.field_1724.method_36454();
                this.prevPitch = this.mc.field_1724.method_36455();
                this.wasActivated = true;
            }
            if ((target = this.findNearestTarget()) == null) {
                return;
            }
            class_243 eyes = this.mc.field_1724.method_33571();
            class_243 targetCenter = target.method_5829().method_1005();
            Rotation targetRot = RotationUtils.getRotationToVec(eyes, targetCenter);
            float speedVal = this.speed.getValueFloat() / 50.0f;
            float newYaw = _FL1CKH17.lerpAngle(speedVal, this.mc.field_1724.method_36454(), targetRot.yaw());
            float newPitch = _FL1CKH17.lerpAngle(speedVal, this.mc.field_1724.method_36455(), targetRot.pitch());
            newPitch = class_3532.method_15363((float)newPitch, (float)-90.0f, (float)90.0f);
            this.mc.field_1724.method_36456(newYaw);
            this.mc.field_1724.method_36457(newPitch);
            double angleDiff = RotationUtils.getAngleToRotation(targetRot);
            if (this.attack.getValue() && angleDiff < 3.0) {
                this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, target);
                this.mc.field_1724.method_6104(class_1268.field_5808);
            }
        } else {
            if (this.wasActivated && this.lookBack.getValue()) {
                this.mc.field_1724.method_36456(this.prevYaw);
                this.mc.field_1724.method_36457(this.prevPitch);
            }
            this.wasActivated = false;
        }
    }

    private class_1297 findNearestTarget() {
        double reach = this.range.getValue();
        return StreamSupport.stream(this.mc.field_1687.method_18112().spliterator(), false).filter(e -> e != this.mc.field_1724).filter(e -> e instanceof class_1657).filter(e -> e instanceof class_1309 && ((class_1309)e).method_5805()).filter(e -> (double)this.mc.field_1724.method_5739(e) <= reach).min(Comparator.comparingDouble(e -> this.mc.field_1724.method_5739(e))).orElse(null);
    }

    private static float lerpAngle(float delta, float from, float to) {
        return from + class_3532.method_15393((float)(to - from)) * delta;
    }
}

