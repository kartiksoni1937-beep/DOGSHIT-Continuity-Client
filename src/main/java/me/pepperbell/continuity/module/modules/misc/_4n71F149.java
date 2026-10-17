/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.misc;

import java.util.LinkedList;
import java.util.Queue;
import me.pepperbell.continuity.event.events.PacketSendListener;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.utils.EncryptedString;
import net.minecraft.class_2596;
import net.minecraft.class_2824;

public final class _4n71F149
extends Module
implements PacketSendListener,
TickListener {
    private final Queue<class_2596<?>> packetQueue = new LinkedList();
    private boolean sendingPacket = false;

    public _4n71F149() {
        super(EncryptedString.of("A*|-\u00b7n_*t-_i\u00b7\u00ad |~^F\u00b7`l_-a*^\u00b7g"), EncryptedString.of("Q.*^u~\u00b7^e~-$u*`e\u00ad\u00b7_$s-\u00ad \u00b7~~e`$.\u00adn^|\u00b7|t`\u00b7$^i\u00ad_t-`\u00b7-y.$ |-i|_`n$~_t\u00b7\u00ade-$_$r^^$a|^c`\u00ad^.t\u00b7~i^-o--n`~.\u00ad _.\u00ad^p\u00ad~`_a-.*^c~^.k\u00b7\u00ade||t\u00b7.$\u00ads._*\u00ad *\u00ad.-t.`o$| _*p*\u00adr\u00ad~e*$v^**$e__n_\u00ad`t*~. -$-m-*.~u_|.l*_|t.\u00ad$i^\u00ad\u00b7p$.^*l.*\u00b7e^|| |~|a|*c\u00ad$`\u00adt-$i\u00ad-o*\u00ad~n^~|*s_$-$ ~*.p.\u00ad\u00ad~e$.$r** |--t~~`|i`*c~-^$k"), -1, Category.MISC);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(PacketSendListener.class, this);
        this.eventManager.add(TickListener.class, this);
        this.packetQueue.clear();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(PacketSendListener.class, this);
        this.eventManager.remove(TickListener.class, this);
        if (this.mc.method_1562() != null && this.mc.field_1724 != null) {
            while (!this.packetQueue.isEmpty()) {
                this.sendingPacket = true;
                this.mc.method_1562().method_52787(this.packetQueue.poll());
                this.sendingPacket = false;
            }
        }
        this.packetQueue.clear();
        super.onDisable();
    }

    @Override
    public void onTick() {
        if (this.mc.field_1724 == null) {
            return;
        }
        if (!this.packetQueue.isEmpty()) {
            this.sendingPacket = true;
            this.mc.method_1562().method_52787(this.packetQueue.poll());
            this.sendingPacket = false;
        }
    }

    @Override
    public void onPacketSend(PacketSendListener.PacketSendEvent event) {
        if (this.mc.field_1724 == null) {
            return;
        }
        if (event.packet instanceof class_2824 && !this.sendingPacket) {
            event.cancel();
            this.packetQueue.add(event.packet);
        }
    }
}

