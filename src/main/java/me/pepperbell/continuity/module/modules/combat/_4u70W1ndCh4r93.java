/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.rotation.Rotation;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1802;

public final class _4u70W1ndCh4r93
extends Module
implements TickListener {
    public _4u70W1ndCh4r93() {
        super(EncryptedString.of("A`-``u^`_t*$\u00b7\u00b7o|*\u00b7^ |_W*`-i_._n\u00ad.\u00b7`d$` \u00b7\u00ad**C^\u00b7_h_|_^a.`^\u00adr~\u00b7-|g$~-e"), EncryptedString.of("A.\u00b7_u\u00b7\u00ad$t\u00ad`\u00b7o*-m$*a|^\u00adt`\u00b7i._\u00ad$c-\u00ada.`l__\u00b7l-$_$y-- *_*\u00b7t_$*h.\u00b7~$r_\u00b7|o~~$_w`*\u00b7s-~_ \u00b7|_^a\u00ad\u00ad~$ |_w^-i-`.\u00adn`\u00ad*d_* |$~c.*^h~|*|a~-_^r\u00ad`^.g|-e$$* -\u00b7a*.~^t`\u00ad\u00b7 _|y\u00b7\u00b7o\u00ad$u~\u00adr.$`- \u00b7.*.f|\u00b7\u00ade~\u00b7~e^$*^t$` \u00ad~w\u00b7|`h^\u00b7e-_`^n\u00ad\u00b7. -*o*\u00adn~*` \u00b7^|\u00adg$\u00b7.r|\u00b7o-^\u00b7u-\u00b7_n~**d$`` |-t\u00ad^-h\u00ad~.~e$`n^--* `-d|-^\u00b7i$\u00b7s`-*a|~b*$l\u00ad.e.*\u00b7s"), -1, Category.COMBAT);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(TickListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(TickListener.class, this);
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null) {
            return;
        }
        if (!this.mc.field_1724.method_24828()) {
            return;
        }
        int slot = this.findSlot(class_1802.field_49098);
        if (slot == -1) {
            this.setEnabled(false);
            return;
        }
        Sweto.INSTANCE.rotatorManager.setRotation(new Rotation(this.mc.field_1724.method_36454(), 90.0f));
        int originalSlot = this.mc.field_1724.method_31548().method_67532();
        this.mc.field_1724.method_31548().method_61496(slot);
        this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, class_1268.field_5808);
        this.mc.field_1724.method_31548().method_61496(originalSlot);
        this.setEnabled(false);
    }

    private int findSlot(class_1792 item) {
        for (int i = 0; i < 9; ++i) {
            if (this.mc.field_1724.method_31548().method_5438(i).method_7909() != item) continue;
            return i;
        }
        return -1;
    }
}

