/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.Optional;
import me.pepperbell.continuity.event.events.AttackListener;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.mixin.MinecraftClientAccessor;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_239;
import net.minecraft.class_3489;
import net.minecraft.class_3966;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_9304;
import net.minecraft.class_9334;
import net.minecraft.class_9362;

public final class M4c35w4p
extends Module
implements AttackListener,
TickListener {
    private final MinMaxSetting switchDelay = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("S\u00ad*w$_i|\u00b7t_^c_~h|_| ~`~~D-\u00b7`~e$-*l-`a~||_y"), 0.0, 20.0, 1.0, 1.0, 3.0).setDescription(EncryptedString.of("T^`i.\u00b7c\u00ad|--k.*s\u00b7_ \u00b7$$b-`e__|f-$~^o\u00ad~r.$^e-\u00b7\u00ad __s\u00ad-`w*_a\u00b7-p^||*p~^``i*\u00b7|$n\u00b7^\u00adg~|-- _-b$\u00ad$a-.\u00adc\u00b7~\u00b7k"));
    private final BooleanSetting onlyOnGround = (BooleanSetting)new BooleanSetting(EncryptedString.of("O*$$n\u00b7_\u00b7.l^|y`~\u00b7^ -`*o.\u00b7\u00adn\u00b7\u00ad\u00b7 _~|.G.*r^~o|.u`-n|`d"), false).setDescription(EncryptedString.of("O^\u00b7~n\u00b7\u00ad_-l^`y$\u00b7 |~s-._w^_`a**.p`\u00b7\u00b7^ \u00b7`w\u00b7\u00ad\u00b7\u00b7h$^*e\u00ad~~^n*.|* ~^.~o.-`|n\u00b7~ -$~t\u00b7\u00ad-\u00b7h.^$-e\u00ad_$. *\u00b7\u00b7g_|r|.\u00ado\u00b7.u-\u00adn~$^^d"));
    private final BooleanSetting silentSwap = (BooleanSetting)new BooleanSetting(EncryptedString.of("S\u00ad\u00b7~^i`$l\u00b7`.e..n`_t~--\u00b7 *_*S\u00ad*$~w\u00b7-`$a__.p"), true).setDescription(EncryptedString.of("S^|\u00ad`w|^a|\u00ad~p_\u00ad^ $^w*\u00adi|-$$t|.|h\u00ad~\u00ado_-u|_.~t_| ^`v-`.i^^$\u00b7s\u00ad*|u_^\u00b7\u00b7a`\u00ad|~l~^ ^-s\u00b7|l.~o~~-t-$ \u00ad~c^-h`|.|a.~n^_\u00ad\u00adg``~e"));
    private int originalSlot = -1;
    private boolean shouldSwitchBack = false;
    private int ticksWaited = 0;
    private int currentDelay = 0;
    private boolean isSwappingAttack = false;
    private int swapCooldownTicks = 0;

    public M4c35w4p() {
        super(EncryptedString.of("B--`.r-^e*|a-$^_c*^$~h*_S`\u00b7w*~a_^p"), EncryptedString.of("S-^^$w*-a\u00ad$p`^s~- `\u00b7t~^o~`^ _\u00b7b*~|r$_e_|^a^`^c.-_`h-.^~ -`|*m^\u00b7`a\u00ad\u00adc\u00ad|.e$_ **_.o$*\u00b7\u00b7n.`. ._$-a$*t.\u00b7t`.*a^~-c_~\u00b7-k_$^ ~-t\u00ad~|h|`e$-~n|`. _-*^b\u00b7\u00ad-\u00b7a\u00ad.~c.`$*k|\u00b7. ~.t_^o*$`| ||`s.**w^\u00ad~.o-||\u00b7r.^.d"), -1, Category.COMBAT);
        this.addSettings(this.switchDelay, this.onlyOnGround, this.silentSwap);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(AttackListener.class, this);
        this.eventManager.add(TickListener.class, this);
        this.reset();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(AttackListener.class, this);
        this.eventManager.remove(TickListener.class, this);
        if (this.originalSlot != -1 && this.mc.field_1724 != null) {
            this.mc.field_1724.method_31548().method_61496(this.originalSlot);
        }
        this.reset();
        super.onDisable();
    }

    @Override
    public void onAttack(AttackListener.AttackEvent event) {
        if (this.mc.field_1724 == null || this.isSwappingAttack) {
            return;
        }
        if (this.swapCooldownTicks > 0) {
            return;
        }
        if (this.onlyOnGround.getValue() && !this.mc.field_1724.method_24828()) {
            return;
        }
        if (!this.isHoldingSword()) {
            return;
        }
        class_239 hit = this.mc.field_1765;
        if (!(hit instanceof class_3966)) {
            return;
        }
        class_3966 entityHit = (class_3966)hit;
        class_1297 target = entityHit.method_17782();
        if (!(target instanceof class_1309)) {
            return;
        }
        class_1309 living = (class_1309)target;
        if (this.isBlocking(living)) {
            return;
        }
        int maceSlot = this.findBreachMaceSlot();
        if (maceSlot == -1) {
            return;
        }
        int currentSlot = this.mc.field_1724.method_31548().method_67532();
        if (this.originalSlot == -1) {
            this.originalSlot = currentSlot;
        }
        this.isSwappingAttack = true;
        this.swapCooldownTicks = 10;
        if (this.silentSwap.getValue()) {
            this.mc.field_1724.method_31548().method_61496(maceSlot);
            ((MinecraftClientAccessor)this.mc).invokeDoAttack();
            this.mc.field_1724.method_31548().method_61496(currentSlot);
            this.originalSlot = -1;
        } else {
            this.mc.field_1724.method_31548().method_61496(maceSlot);
            ((MinecraftClientAccessor)this.mc).invokeDoAttack();
            this.currentDelay = this.randomTicks((float)this.switchDelay.getMinValue(), (float)this.switchDelay.getMaxValue());
            this.ticksWaited = 0;
            this.shouldSwitchBack = true;
        }
        this.isSwappingAttack = false;
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null) {
            return;
        }
        if (this.swapCooldownTicks > 0) {
            --this.swapCooldownTicks;
        }
        if (!this.shouldSwitchBack) {
            return;
        }
        ++this.ticksWaited;
        if (this.ticksWaited >= this.currentDelay) {
            if (this.originalSlot != -1) {
                this.mc.field_1724.method_31548().method_61496(this.originalSlot);
                this.originalSlot = -1;
            }
            this.shouldSwitchBack = false;
            this.ticksWaited = 0;
        }
    }

    private boolean isHoldingSword() {
        class_1799 held = this.mc.field_1724.method_6047();
        return !held.method_7960() && held.method_31573(class_3489.field_42611);
    }

    private boolean isBlocking(class_1309 entity) {
        if (!(entity instanceof class_1657)) {
            return false;
        }
        class_1657 player = (class_1657)entity;
        return player.method_6039();
    }

    private int findBreachMaceSlot() {
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = this.mc.field_1724.method_31548().method_5438(i);
            if (!(stack.method_7909() instanceof class_9362) || !this.hasBreach(stack)) continue;
            return i;
        }
        return -1;
    }

    private boolean hasBreach(class_1799 stack) {
        class_9304 enchants = (class_9304)stack.method_58694(class_9334.field_49633);
        if (enchants == null) {
            return false;
        }
        for (class_6880 entry : enchants.method_57534()) {
            Optional key = entry.method_40230();
            if (!key.isPresent() || !((class_5321)key.get()).method_29177().method_12832().equalsIgnoreCase("breach")) continue;
            return true;
        }
        return false;
    }

    private int randomTicks(float min, float max) {
        if (min >= max) {
            return (int)min;
        }
        return (int)((double)min + Math.random() * (double)(max - min + 1.0f));
    }

    private void reset() {
        this.shouldSwitchBack = false;
        this.isSwappingAttack = false;
        this.ticksWaited = 0;
        this.swapCooldownTicks = 0;
        this.originalSlot = -1;
    }
}

