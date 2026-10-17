/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import me.pepperbell.continuity.utils.RotationUtils;
import me.pepperbell.continuity.utils.TimerUtils;
import me.pepperbell.continuity.utils.rotation.Rotation;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2868;
import net.minecraft.class_2886;

public final class _41rP34r1
extends Module
implements TickListener {
    private final SliderSetting range = new SliderSetting(EncryptedString.of("T-~^a_-`r|.g|-^~e|`t.*|* ~~\u00b7R-$`^a.^.$n^^_g`\u00ad`*e"), 1.0, 50.0, 30.0, 1.0);
    private final SliderSetting prediction = new SliderSetting(EncryptedString.of("P_~_r$-$e*.*d`$`i^-c|\u00b7t`|^-i^\u00b7o$\u00ad-n"), 0.0, 5.0, 1.0, 0.1);
    private final SliderSetting throwDelay = new SliderSetting(EncryptedString.of("T|`h.*r~\u00ad~o-_w.~ ~|\u00b7D.~$e^._*l\u00b7_a\u00ad\u00ad-.y"), 0.0, 2000.0, 500.0, 50.0);
    private final BooleanSetting silent = new BooleanSetting(EncryptedString.of("S*\u00b7\u00b7i|.^l_-.^e^|$~n\u00b7^t\u00b7^ ~^A.~.*i$~m"), true);
    private final BooleanSetting autoSwap = new BooleanSetting(EncryptedString.of("A`*_\u00adu_.$t\u00ad`~o\u00ad\u00b7 $\u00b7\u00b7S_`\u00adw*\u00ada*|p"), true);
    private final BooleanSetting maceCombo = (BooleanSetting)new BooleanSetting(EncryptedString.of("M.$\u00ad|a*.*c-\u00ad-*e-|| |_-C__.o_^m_$b\u00ad.o"), true).setDescription(EncryptedString.of("A\u00ad.u^~`t\u00ad\u00b7o\u00b7\u00b7`^m.\u00ad`-a*$_\u00adt\u00b7*i-_^c.|~\u00ada`|l*-``l\u00ad*y`$. \u00ad$\u00b7s._w-*^a~^$.p_.|_s^\u00b7 ~^t`~o*`\u00b7- -*\u00b7M\u00b7$a^^`c_\u00ad**e-*.- -|*a-`_\u00b7n_*`d$*| -*h$*.-i*-t-$--s^|$~ ^$-t~\u00b7~a.*\u00ad.r`*g~|\u00b7e.^^t|_~ ~^a~-f-$|t\u00ad`.^e*~r~\u00b7 _.|*t_~\u00ad_e^|-*l$~e^-`p-`o-^|^r\u00b7_~t^-\u00b7|i^--n\u00b7-g"));
    private final BooleanSetting onlyHead = (BooleanSetting)new BooleanSetting(EncryptedString.of("T\u00b7^a-\u00b7^r\u00b7-|g..e-_|\u00adt$| |`\u00ad\u00adH*\u00ad$\u00ade\u00b7.|a`.d"), true).setDescription(EncryptedString.of("A__l-|w`|\u00b7a`|y^-s``* ||$\u00ada\u00b7$i\u00b7|.$m\u00b7\u00ad\u00b7s|~`- |^__f$.^o\u00b7\u00b7r*** .~*^t~\u00b7|.h||$e$|* ^_^p_^l|.~$a\u00b7$~$y^\u00ad*e^~|r~~ $``\u00adh.-e\u00ad\u00b7_`a-^d\u00b7* \u00ad`$\u00adf^\u00ad^o-\u00ad^r`.^- $\u00ad\u00ad\u00b7p^|$e~~r~\u00adf.\u00ade\u00b7*~$c__t$. .\u00b7c\u00ad~`o|*`-m_^.|b\u00ad\u00ad.*o|~\u00ads"));
    private final TimerUtils timer = new TimerUtils();
    private class_243 lastPos = class_243.field_1353;
    private long lastThrowTime = 0L;
    private class_1657 lastTarget = null;
    private boolean expectingTeleport = false;

    public _41rP34r1() {
        super(EncryptedString.of("A^\u00ad^i-^^r|$` $.P\u00b7|`e^*a..r..\u00b7\u00adl"), EncryptedString.of("A--u__~^t$\u00ado*`m^~a$.~t`~\u00adi.`*_c^\u00ada|$l^-`l\u00b7|-y~\u00b7-\u00ad _^.`t*|-^h|\u00b7.r\u00b7^..o_~-w__|s$|* ~$..p\u00ad.e\u00ad\u00ad**a~^^r\u00ad^`^l$\u00b7$s$*~ .^-a-.~\u00b7t^\u00ad -_$p|\u00ad$l._a.`|y`~e_^`r`~_`s$* \u00b7^*-i.$\u00b7n_*-\u00b7 _`t\u00b7`h_|e||*- |_a\u00ad~-i$._r"), -1, Category.COMBAT);
        this.addSettings(this.range, this.prediction, this.throwDelay, this.silent, this.autoSwap, this.maceCombo, this.onlyHead);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        this.timer.reset();
        this.expectingTeleport = false;
        if (this.mc.field_1724 != null) {
            this.lastPos = this.mc.field_1724.method_73189();
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        this.expectingTeleport = false;
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        this.checkTeleport();
        if (!this.timer.hasReached((float)this.throwDelay.getValue())) {
            return;
        }
        class_1657 target = this.findAirborneTarget();
        if (target == null) {
            return;
        }
        int pearlSlot = InventoryUtils.getSlot(class_1802.field_8634);
        if (pearlSlot == -1 || pearlSlot > 8) {
            return;
        }
        class_243 targetPos = target.method_73189().method_1019(target.method_18798().method_1021(this.prediction.getValue()));
        targetPos = this.onlyHead.getValue() ? targetPos.method_1031(0.0, (double)target.method_18381(target.method_18376()), 0.0) : targetPos.method_1031(0.0, (double)target.method_17682() / 2.0, 0.0);
        Rotation rot = this.calculateTrajectory(this.mc.field_1724.method_33571(), targetPos);
        if (rot == null) {
            return;
        }
        if (this.silent.getValue()) {
            Sweto.INSTANCE.rotatorManager.setRotation(rot);
        } else {
            this.mc.field_1724.method_36456(rot.yaw());
            this.mc.field_1724.method_36457(rot.pitch());
        }
        if (this.autoSwap.getValue()) {
            int prev = this.mc.field_1724.method_31548().method_67532();
            this.mc.method_1562().method_52787((class_2596)new class_2868(pearlSlot));
            this.mc.method_1562().method_52787((class_2596)new class_2886(class_1268.field_5808, 0, rot.yaw(), rot.pitch()));
            this.mc.method_1562().method_52787((class_2596)new class_2868(prev));
        } else if (this.mc.field_1724.method_31548().method_67532() == pearlSlot) {
            this.mc.method_1562().method_52787((class_2596)new class_2886(class_1268.field_5808, 0, rot.yaw(), rot.pitch()));
        }
        this.lastThrowTime = System.currentTimeMillis();
        this.lastTarget = target;
        this.expectingTeleport = true;
        this.timer.reset();
        if (this.silent.getValue()) {
            new Thread(() -> {
                try {
                    Thread.sleep(200L);
                    Sweto.INSTANCE.rotatorManager.reset();
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }).start();
        }
    }

    private void checkTeleport() {
        class_243 currentPos = this.mc.field_1724.method_73189();
        if (this.expectingTeleport && currentPos.method_1022(this.lastPos) > 8.0) {
            if (this.maceCombo.getValue() && this.lastTarget != null && this.lastTarget.method_5805() && (double)this.mc.field_1724.method_5739((class_1297)this.lastTarget) < 6.0) {
                this.executeMaceHit(this.lastTarget);
            }
            this.expectingTeleport = false;
        }
        this.lastPos = currentPos;
        if (this.expectingTeleport && System.currentTimeMillis() - this.lastThrowTime > 5000L) {
            this.expectingTeleport = false;
        }
    }

    private void executeMaceHit(class_1657 target) {
        int maceSlot = InventoryUtils.getMaceSlot();
        if (maceSlot == -1) {
            maceSlot = InventoryUtils.getAxeSlot();
        }
        if (maceSlot != -1) {
            int prev = this.mc.field_1724.method_31548().method_67532();
            this.mc.method_1562().method_52787((class_2596)new class_2868(maceSlot));
            Rotation hitRot = RotationUtils.getRotationToVec(this.mc.field_1724.method_33571(), target.method_33571());
            Sweto.INSTANCE.rotatorManager.setRotation(hitRot);
            this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, (class_1297)target);
            this.mc.field_1724.method_6104(class_1268.field_5808);
            this.mc.method_1562().method_52787((class_2596)new class_2868(prev));
            new Thread(() -> {
                try {
                    Thread.sleep(100L);
                    Sweto.INSTANCE.rotatorManager.reset();
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }).start();
        }
    }

    private class_1657 findAirborneTarget() {
        List players = this.mc.field_1687.method_18456().stream().filter(p -> p != this.mc.field_1724).filter(p -> !p.method_24828()).filter(p -> !p.method_29504()).filter(p -> (double)this.mc.field_1724.method_5739((class_1297)p) <= this.range.getValue()).sorted(Comparator.comparingDouble(p -> this.mc.field_1724.method_5739((class_1297)p))).collect(Collectors.toList());
        return players.isEmpty() ? null : (class_1657)players.get(0);
    }

    private Rotation calculateTrajectory(class_243 start, class_243 target) {
        double dx = target.field_1352 - start.field_1352;
        double dz = target.field_1350 - start.field_1350;
        float yaw = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0f;
        for (float pitch = -90.0f; pitch < 90.0f; pitch += 0.1f) {
            if (!this.simulatesHit(start, target, yaw, pitch)) continue;
            return new Rotation(yaw, pitch);
        }
        return null;
    }

    private boolean simulatesHit(class_243 start, class_243 target, float yaw, float pitch) {
        double radYaw = Math.toRadians(yaw + 90.0f);
        double radPitch = Math.toRadians(-pitch);
        double v0 = 1.5;
        double vx = Math.cos(radPitch) * Math.cos(radYaw) * v0;
        double vy = Math.sin(radPitch) * v0;
        double vz = Math.cos(radPitch) * Math.sin(radYaw) * v0;
        class_243 pVel = this.mc.field_1724.method_18798();
        vx += pVel.field_1352 * 0.75;
        vz += pVel.field_1350 * 0.75;
        if (!this.mc.field_1724.method_24828()) {
            vy += pVel.field_1351 * 0.75;
        }
        double currX = start.field_1352;
        double currY = start.field_1351;
        double currZ = start.field_1350;
        for (int i = 0; i < 100; ++i) {
            currX += vx;
            currY += vy;
            currZ += vz;
            vx *= 0.99;
            vy *= 0.99;
            vz *= 0.99;
            vy -= 0.03;
            double distSq = (currX - target.field_1352) * (currX - target.field_1352) + (currY - target.field_1351) * (currY - target.field_1351) + (currZ - target.field_1350) * (currZ - target.field_1350);
            if (distSq < 1.0) {
                return true;
            }
            if (currY < target.field_1351 - 10.0) break;
        }
        return false;
    }
}

