/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.misc;

import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.utils.EncryptedString;
import org.lwjgl.glfw.GLFW;

public final class N0JumpD314y
extends Module
implements TickListener {
    public N0JumpD314y() {
        super(EncryptedString.of("N\u00ad_-`o^-\u00ad .$J~~u\u00ad\u00b7^m`*\u00adp^^$. -|$D.\u00b7e-|*l.$a$`|y"), EncryptedString.of("L|*e*\u00b7^-t$*s-\u00ad^\u00ad \u00ad~y^^\u00b7o`$-u_~-\u00ad *|j_*u`\u00b7m\u00b7*`^p||^\u00b7 |*-f|**.a\u00ad_.\u00ads*`-~t$.*`e-`*-r`$^*,^_ |``\u00adr`_e.\u00adm\u00b7\u00ad`-o~-v^\u00ad`*i_|\u00adn_$`~g\u00b7|*\u00b7 .-^t|.^h*~*e$.\u00ad |\u00ad\u00ad\u00b7d|\u00b7\u00b7~e``|-l_\u00b7|a._*\u00b7y"), -1, Category.MISC);
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
        if (this.mc.field_1755 != null) {
            return;
        }
        if (!this.mc.field_1724.method_24828()) {
            return;
        }
        if (GLFW.glfwGetKey((long)this.mc.method_22683().method_4490(), (int)32) != 1) {
            return;
        }
        this.mc.field_1690.field_1903.method_23481(false);
        this.mc.field_1724.method_6043();
    }
}

