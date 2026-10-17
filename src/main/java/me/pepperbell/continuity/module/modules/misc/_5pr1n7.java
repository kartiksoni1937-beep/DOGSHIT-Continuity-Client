/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.misc;

import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.utils.EncryptedString;

public final class _5pr1n7
extends Module
implements TickListener {
    public _5pr1n7() {
        super(EncryptedString.of("S_``p|*`|r$*_i.._~n``.t"), EncryptedString.of("K*|e^`-e_--p|^s_`- |-*$y_-o_\u00b7u|~ `*s-\u00b7p_*\u00ad*r$\u00b7^i*`n\u00ad_~t|\u00b7i~$n~_^\u00adg-_` \u00ad~a\u00b7-.t`\u00b7 *$~a~_.l`|-$l\u00b7`| \u00b7-*t`$`^i_^^-m\u00b7`.^e\u00b7||_s"), -1, Category.MISC);
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
        this.mc.field_1724.method_5728(this.mc.field_1724.field_3913.method_20622());
    }
}

