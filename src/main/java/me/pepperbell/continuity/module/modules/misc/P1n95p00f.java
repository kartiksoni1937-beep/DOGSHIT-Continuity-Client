/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.misc;

import me.pepperbell.continuity.event.events.PacketReceiveListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import net.minecraft.class_2596;
import net.minecraft.class_2670;
import net.minecraft.class_2827;

public final class P1n95p00f
extends Module
implements PacketReceiveListener {
    private final MinMaxSetting ping = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("P**^i^^_n\u00ad\u00adg"), 0.0, 1000.0, 1.0, 0.0, 600.0).setDescription(EncryptedString.of("T\u00b7_-\u00b7h*`e-`_ -~^`p\u00b7*i\u00b7-*$n.^g`._\u00ad |-y*$-o--\u00ad$u~- _^.w|~.a\u00ad`*n.`^-t~^| |_|t|\u00ado\u00ad_ _|\u00b7^a\u00ad\u00adc|\u00adh_$_^i^^e$\u00ad*_v_.-^e"));
    private int delay;

    public P1n95p00f() {
        super(EncryptedString.of("P``~i|`_n_`~g~\u00b7 \u00b7~\u00b7S_|^p$\u00ado``-o~|f"), EncryptedString.of("H^\u00ado\u00ad|_l\u00ad.--d~$s\u00b7*~ $-^.b|`\u00b7-a|\u00b7c`||k.\u00ad~- ^|_\u00b7p~$a\u00ad**`c_\u00b7k_^e-_`t_*s.* $*m\u00b7\u00ad\u00ad\u00ada$^-$k\u00b7$\u00b7i|*n\u00ad`\u00adg`* ~$t^-h^$`-e^\u00b7 _\u00ads^\u00ad-^e`-r|*v\u00b7*e**|r$$ *~.\u00b7t_~h\u00ad\u00adi*-^n_|*k|_| .|*$y$-\u00b7o~`^u`*~\u00b7r_- ~``i$\u00b7$$n^\u00b7$t*_e.*$~r^*\u00b7n$$\u00b7e_\u00ad$t~|\u00ad^ _-~-c\u00b7-~.o-*n.`\u00ad.n`*~\u00ade\u00ad\u00ad-_c\u00ad|t-|^i-*.~o$|.n-|** \u00b7\u00ad^i^~^\u00ads`_ ~~b^|*\u00b7a*\u00add"), -1, Category.MISC);
        this.addSettings(this.ping);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(PacketReceiveListener.class, this);
        this.delay = this.ping.getRandomValueInt();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(PacketReceiveListener.class, this);
        super.onDisable();
    }

    @Override
    public void onPacketReceive(PacketReceiveListener.PacketReceiveEvent event) {
        class_2596 class_25962 = event.packet;
        if (class_25962 instanceof class_2670) {
            class_2670 packet = (class_2670)class_25962;
            new Thread(() -> {
                try {
                    Thread.sleep(this.delay);
                    this.mc.method_1562().method_48296().method_10743((class_2596)new class_2827(packet.method_11517()));
                    this.delay = this.ping.getRandomValueInt();
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }).start();
            event.cancel();
        }
    }
}

