/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.client;

import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.TickListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.ConfigSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.module.setting.StringSetting;
import me.pepperbell.continuity.utils.EncryptedString;

public class C0nf195
extends Module
implements TickListener {
    private final StringSetting name = new StringSetting("Config Name", "default");
    private final BooleanSetting autoSave = new BooleanSetting("Auto Save", true);
    private final SliderSetting autoSaveInterval = new SliderSetting("Interval (min)", 1.0, 60.0, 5.0, 1.0);
    private final ConfigSetting manage = new ConfigSetting("Manage C0nf195");
    private long lastSaveTime = System.currentTimeMillis();

    public C0nf195() {
        super(EncryptedString.of("C.`^|o\u00ad^n$\u00b7.*f``*~i*\u00adg*\u00b7-s"), EncryptedString.of("M**^~a\u00ad$\u00ad\u00adn.$-~a$`__g*|\u00b7_e^~^$ *.y~`|o.|~u*.`r`* ||~`c$\u00b7$l*~`i\u00ad|~e`__n.-*t-_$\u00ad .^p|_r\u00ad.`o|*f|*i-^l*_e\u00ad\u00b7\u00b7_s-\u00b7 *`s\u00b7`t~^e~-_a$$l._-*t.-h*-*`i~-\u00adl-\u00ady"), -1, Category.CLIENT);
        this.addSettings(this.name, this.autoSave, this.autoSaveInterval, this.manage);
    }

    @Override
    public void onTick() {
        if (!this.autoSave.getValue()) {
            return;
        }
        long intervalMs = (long)(this.autoSaveInterval.getValue() * 60000.0);
        if (System.currentTimeMillis() - this.lastSaveTime > intervalMs) {
            Sweto.INSTANCE.profileManager.saveProfile("default");
            this.lastSaveTime = System.currentTimeMillis();
        }
    }

    public String getConfigName() {
        return this.name.getValue();
    }
}

