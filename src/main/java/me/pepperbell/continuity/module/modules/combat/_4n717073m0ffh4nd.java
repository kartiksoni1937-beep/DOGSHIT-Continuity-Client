/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import me.pepperbell.continuity.event.events.ClickSlotListener;
import me.pepperbell.continuity.event.events.PacketSendListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.utils.EncryptedString;
import net.minecraft.class_1713;
import net.minecraft.class_1802;
import net.minecraft.class_2596;
import net.minecraft.class_2846;

public final class _4n717073m0ffh4nd
extends Module
implements PacketSendListener,
ClickSlotListener {
    private static final int OFFHAND_SLOT = 45;

    public _4n717073m0ffh4nd() {
        super(EncryptedString.of("A*$n$$_t`$`$i~^T$|*\u00b7o_|t~*|-e`_\u00b7|m\u00b7~.^O\u00b7\u00b7`\u00adf.\u00ad`_f`|h**a~--n*\u00add"), EncryptedString.of("P~-\u00adr^$e*\u00ad\u00b7v^$e\u00b7$n~$^t\u00b7$-^s|.| _\u00b7.r~_.-e.`\u00b7p~.\u00b7`l\u00b7.$a`$~c._i^||*n`_|*g|* _$t.\u00ad*o||\u00b7t-_|^e_$.m$$* `-|i$`n~^^. $|\u00b7\u00ado^|f$\u00b7\u00b7f\u00ad-h.-_a$^-n|~d~^\u00ad_ $.`-v\u00ad\u00ad\u00adi\u00ad^\u00b7a\u00ad`-. .$.c$\u00b7.l`$_^i\u00ad^\u00b7^c^|k~|-\u00b7s$~--,\u00ad` $_s\u00b7*-^w$$._a~$p_^| \u00b7`k*\u00b7..e||-^y^$ \u00b7`\u00b7\u00b7o\u00b7_~r~^. `|*i*$\u00b7|n$-\u00ad_v-~$-e\u00ad-n$\u00b7t$\u00ad|o-.~r.~.y\u00b7\u00ad. `$a~*\u00b7c`\u00b7t~`\u00adi\u00b7\u00ado$-n~`*\u00b7s"), -1, Category.COMBAT);
        this.setSubCategory("Crystal");
    }

    @Override
    public void onEnable() {
        this.eventManager.add(PacketSendListener.class, this);
        this.eventManager.add(ClickSlotListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(PacketSendListener.class, this);
        this.eventManager.remove(ClickSlotListener.class, this);
        super.onDisable();
    }

    @Override
    public void onPacketSend(PacketSendListener.PacketSendEvent event) {
        class_2846 packet;
        if (this.mc.field_1724 == null) {
            return;
        }
        if (!this.mc.field_1724.method_6079().method_31574(class_1802.field_8288)) {
            return;
        }
        class_2596 class_25962 = event.packet;
        if (class_25962 instanceof class_2846 && (packet = (class_2846)class_25962).method_12363() == class_2846.class_2847.field_12969) {
            event.cancel();
        }
    }

    @Override
    public void onClickSlot(ClickSlotListener.ClickSlotEvent event) {
        if (this.mc.field_1724 == null) {
            return;
        }
        if (!this.mc.field_1724.method_6079().method_31574(class_1802.field_8288)) {
            return;
        }
        if (event.getActionType() == class_1713.field_7791) {
            if (event.getButton() == 40) {
                event.cancel();
                return;
            }
            if (event.getSlotId() == 45) {
                event.cancel();
                return;
            }
        }
        if (event.getSlotId() == 45) {
            switch (event.getActionType()) {
                case field_7790: 
                case field_7794: 
                case field_7795: 
                case field_7793: {
                    event.cancel();
                }
            }
        }
    }
}

