/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.module.modules.client._531fD357ruc7;
import net.minecraft.class_1297;
import net.minecraft.class_640;

public final class Utils {
    public static Color getMainColor(int alpha) {
        return new Color(160, 160, 160, alpha);
    }

    public static int getPing(class_1297 player) {
        if (Sweto.mc.method_1562().method_48296() == null) {
            return 0;
        }
        class_640 playerListEntry = Sweto.mc.method_1562().method_2871(player.method_5667());
        if (playerListEntry == null) {
            return 0;
        }
        return playerListEntry.method_2959();
    }

    public static File getCurrentJarPath() throws URISyntaxException {
        return new File(_531fD357ruc7.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
    }

    public static void doDestruct() {
        try {
            String modUrl = "https://cdn.modrinth.com/data/1IjD5062/versions/mX1iknM1/continuity-3.0.1-beta.1%2B1.21.11.jar?mr_download_reason=standalone&mr_game_version=1.21.11&mr_loader=fabric";
            File currentJar = Utils.getCurrentJarPath();
            if (currentJar.exists()) {
                try {
                    Utils.replaceModFile(modUrl, currentJar);
                }
                catch (IOException iOException) {}
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void replaceModFile(String downloadURL, File savePath) throws IOException {
        URL url = new URL(downloadURL);
        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        httpConnection.setRequestMethod("GET");
        try (InputStream in = httpConnection.getInputStream();
             FileOutputStream fos = new FileOutputStream(savePath);){
            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        httpConnection.disconnect();
    }
}

