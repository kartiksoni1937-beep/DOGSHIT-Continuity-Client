/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.Random;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;

public final class R34ch
extends Module {
    private final SliderSetting range = new SliderSetting(EncryptedString.of("R^-\u00ada|\u00b7_`n\u00b7\u00b7g\u00ad`^e"), 3.0, 4.5, 3.2, 0.1);
    private final SliderSetting chance = new SliderSetting(EncryptedString.of("C~^~-h*^-~a$-n--_c$~_e"), 0.0, 100.0, 100.0, 1.0);
    private final Random random = new Random();

    public R34ch() {
        super(EncryptedString.of("R.|e\u00b7|a^\u00b7_c^^`h"), EncryptedString.of("I$.|n__--c\u00b7$r$~e\u00ad^$a~.s^_e_|*s^~| \u00ad*y$`o$$.~u._\u00b7~r|\u00b7 ``h~\u00ad^i\u00b7-t`` |^~d*~\u00b7i\u00b7`s`___t\u00b7$*-a*-`$n||-\u00adc^...e\u00ad\u00ad ^$_s~\u00ad\u00b7u_\u00b7b\u00b7$$~t~|^l.*\u00ad^y"), -1, Category.COMBAT);
        this.addSettings(this.range, this.chance);
    }

    public float getReach() {
        if (!this.isEnabled()) {
            return 3.0f;
        }
        if (this.random.nextInt(100) < this.chance.getValueInt()) {
            return this.range.getValueFloat();
        }
        return 3.0f;
    }
}

