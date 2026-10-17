/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Enumeration;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class S {
    private static final int GCM_IV_LEN = 12;
    private static final int GCM_TAG_LEN = 128;
    private static final SecureRandom RNG = new SecureRandom();
    private static volatile SecretKey KEY;

    private S() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static SecretKey key() {
        if (KEY != null) return KEY;
        Class<S> clazz = S.class;
        synchronized (S.class) {
            if (KEY != null) return KEY;
            KEY = S.deriveKey();
            // ** MonitorExit[var0] (shouldn't be in output)
            return KEY;
        }
    }

    private static SecretKey deriveKey() {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
            while (ifaces != null && ifaces.hasMoreElements()) {
                NetworkInterface iface = ifaces.nextElement();
                byte[] mac = iface.getHardwareAddress();
                if (mac == null || mac.length <= 0) continue;
                sha.update(mac);
                break;
            }
            sha.update(System.getProperty("user.name", "u").getBytes(StandardCharsets.UTF_8));
            byte[] salt = S.buildSalt();
            sha.update(salt);
            byte[] raw = sha.digest();
            return new SecretKeySpec(raw, "AES");
        }
        catch (Exception e) {
            try {
                MessageDigest sha = MessageDigest.getInstance("SHA-256");
                sha.update(System.getProperty("user.name", "default").getBytes(StandardCharsets.UTF_8));
                sha.update(S.buildSalt());
                return new SecretKeySpec(sha.digest(), "AES");
            }
            catch (Exception ex) {
                throw new RuntimeException("Key derivation failed", ex);
            }
        }
    }

    private static byte[] buildSalt() {
        byte[] s = new byte[]{37, -111, -121, -3, 117, 120, -50, 93, -108, 93, -25, 91, -127, -113, -82, 122};
        return s;
    }

    public static String e(String plain) {
        if (plain == null || plain.isEmpty()) {
            return plain;
        }
        try {
            byte[] iv = new byte[12];
            RNG.nextBytes(iv);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, (Key)S.key(), new GCMParameterSpec(128, iv));
            byte[] ct = cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8));
            ByteBuffer buf = ByteBuffer.allocate(12 + ct.length);
            buf.put(iv);
            buf.put(ct);
            return Base64.getEncoder().encodeToString(buf.array());
        }
        catch (Exception ex) {
            return Base64.getEncoder().encodeToString(plain.getBytes(StandardCharsets.UTF_8));
        }
    }

    public static String d(String enc) {
        if (enc == null || enc.isEmpty()) {
            return enc;
        }
        try {
            byte[] raw = Base64.getDecoder().decode(enc);
            if (raw.length < 28) {
                return new String(raw, StandardCharsets.UTF_8);
            }
            ByteBuffer buf = ByteBuffer.wrap(raw);
            byte[] iv = new byte[12];
            buf.get(iv);
            byte[] ct = new byte[buf.remaining()];
            buf.get(ct);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(2, (Key)S.key(), new GCMParameterSpec(128, iv));
            return new String(cipher.doFinal(ct), StandardCharsets.UTF_8);
        }
        catch (Exception ex) {
            return enc;
        }
    }
}

