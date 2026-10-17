/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.AttackListener;
import me.pepperbell.continuity.event.events.PlayerTickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.InventoryUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_239;
import net.minecraft.class_3489;
import net.minecraft.class_3966;
import net.minecraft.class_9362;

public final class _5w0rdH17
extends Module
implements PlayerTickListener,
AttackListener {
    private boolean attackPressedLastTick = false;

    public _5w0rdH17() {
        super(EncryptedString.of("S||w\u00ad^$$o^|`\u00adr$.-*d~^``H\u00b7\u00b7\u00adi|$t"), EncryptedString.of("S`|\u00b7w^\u00adi``~t$*.c~^h`^e\u00ad\u00b7~^s_^` ``|t_-o\u00b7. $.$`s\u00b7`\u00b7.w_$o|-|r|*^^d-._$ .-`$w`^`*h`\u00b7_e_~n-.\u00ad ~*a_~^^t\u00b7_t...a\u00b7\u00b7$c~.|k||i~\u00b7n_\u00b7g_$\u00ad *_w-~|i.`^|t$\u00ad.h|` \u00ad-a\u00b7`\u00ad_n.*$`y^- .~n$^o-*n^^\u00b7*s.^w_-`o\u00b7\u00ad$\u00b7r.`\u00b7~d`|_/\u00ad-$a**x\u00b7*`.e-**\u00b7/-*$*m*-*-a\u00b7-c-_^~e\u00b7\u00b7 ^`*^i^\u00adt|`\u00b7^e|-m"), -1, Category.COMBAT);
        this.setSubCategory("Sword");
    }

    @Override
    public void onEnable() {
        this.eventManager.add(PlayerTickListener.class, this);
        this.eventManager.add(AttackListener.class, this);
        this.attackPressedLastTick = false;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(PlayerTickListener.class, this);
        this.eventManager.remove(AttackListener.class, this);
        this.attackPressedLastTick = false;
        super.onDisable();
    }

    @Override
    public void onAttack(AttackListener.AttackEvent event) {
        class_3966 entityHit;
        if (this.mc.field_1724 == null) {
            return;
        }
        class_1799 held = this.mc.field_1724.method_6047();
        if (held.method_31573(class_3489.field_42611) || held.method_7909() instanceof class_1743 || held.method_7909() instanceof class_9362) {
            return;
        }
        class_239 class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3966 && !((entityHit = (class_3966)class_2392).method_17782() instanceof class_1511)) {
            event.cancel();
        }
    }

    @Override
    public void onPlayerTick() {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        boolean attackPressed = this.mc.field_1690.field_1886.method_1434();
        if (attackPressed && !this.attackPressedLastTick) {
            int swordSlot;
            class_3966 entityHit;
            class_239 hitResult;
            class_1792 held = this.mc.field_1724.method_6047().method_7909();
            boolean isSword = this.mc.field_1724.method_6047().method_31573(class_3489.field_42611);
            boolean isAxe = held instanceof class_1743;
            boolean isMace = held instanceof class_9362;
            if (!(isSword || isAxe || isMace || !((hitResult = this.mc.field_1765) instanceof class_3966) || (entityHit = (class_3966)hitResult).method_17782() instanceof class_1511 || (swordSlot = this.findSwordSlot()) == -1)) {
                InventoryUtils.setInvSlot(swordSlot);
                this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, entityHit.method_17782());
                this.mc.field_1724.method_6104(class_1268.field_5808);
            }
        }
        this.attackPressedLastTick = attackPressed;
    }

    private int findSwordSlot() {
        if (this.mc.field_1724 == null) {
            return -1;
        }
        for (int i = 0; i < 9; ++i) {
            if (!this.mc.field_1724.method_31548().method_5438(i).method_31573(class_3489.field_42611)) continue;
            return i;
        }
        return -1;
    }
}

