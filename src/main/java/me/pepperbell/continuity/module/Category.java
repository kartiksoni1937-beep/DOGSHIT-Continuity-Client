/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module;

import me.pepperbell.continuity.utils.EncryptedString;

public enum Category {
    COMBAT(EncryptedString.of("C^-o^__m$*.`b|*a`~\u00adt")),
    MISC(EncryptedString.of("M*$i~\u00b7s\u00ad|$\u00b7c")),
    RENDER(EncryptedString.of("R.*|e\u00b7.\u00b7n^|^`d_|*-e$.\u00b7\u00adr")),
    CLIENT(EncryptedString.of("C`~_l`*.i*-|e*.^$n*`~t")),
    HUD(EncryptedString.of("H~\u00b7u^|d")),
    HIDDEN(EncryptedString.of("H||_-i^|.*d_-d_$e\u00b7$n"));

    public final CharSequence name;

    private Category(CharSequence name) {
        this.name = name;
    }
}

