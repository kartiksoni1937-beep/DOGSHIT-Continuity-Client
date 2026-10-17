/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import me.pepperbell.continuity.utils.S;
import org.jetbrains.annotations.NotNull;

public class EncryptedString
implements CharSequence {
    public static final char[] INVISIBLE = new char[]{'-', '$', '^', '~', '`', '|', '*', '.', '_', '\u00ad', '\u00b7'};
    private final String string;
    private transient String cleaned = null;

    public EncryptedString(String s) {
        this.string = s != null ? s : "";
    }

    public static EncryptedString of(String s) {
        return new EncryptedString(s);
    }

    public static EncryptedString of(String encrypted, String key) {
        return new EncryptedString(encrypted);
    }

    public String getString() {
        return this.string;
    }

    public String getRaw() {
        return this.string;
    }

    public String getClean() {
        if (this.cleaned != null) {
            return this.cleaned;
        }
        String decrypted = S.d(this.string);
        StringBuilder sb = new StringBuilder(decrypted.length());
        for (int i = 0; i < decrypted.length(); ++i) {
            char c = decrypted.charAt(i);
            if (EncryptedString.isInvisible(c)) continue;
            sb.append(c);
        }
        this.cleaned = sb.toString();
        return this.cleaned;
    }

    public static boolean isInvisible(char c) {
        for (char inv : INVISIBLE) {
            if (c != inv) continue;
            return true;
        }
        return false;
    }

    public static String clean(String s) {
        if (s == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (EncryptedString.isInvisible(c)) continue;
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public int length() {
        return this.getClean().length();
    }

    @Override
    public char charAt(int index) {
        return this.getClean().charAt(index);
    }

    @Override
    @NotNull
    public String toString() {
        return this.getClean();
    }

    @Override
    @NotNull
    public CharSequence subSequence(int start, int end) {
        return this.getClean().subSequence(start, end);
    }
}

