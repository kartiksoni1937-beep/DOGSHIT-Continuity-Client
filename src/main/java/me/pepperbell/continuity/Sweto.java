/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.manager.ColorManager;
import me.pepperbell.continuity.managers.FriendManager;
import me.pepperbell.continuity.managers.ProfileManager;
import me.pepperbell.continuity.module.ModuleManager;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.rotation.R0T1AT0RM4NG3R;
import net.minecraft.class_310;
import net.minecraft.class_437;

public final class Sweto {
    public R0T1AT0RM4NG3R rotatorManager;
    public ProfileManager profileManager;
    public ModuleManager moduleManager;
    public EventManager eventManager;
    public FriendManager friendManager;
    public static class_310 mc;
    public CharSequence version = EncryptedString.of(" ..b~.1~-$-3");
    public static boolean BETA;
    public static Sweto INSTANCE;
    public boolean guiInitialized;
    public class_437 previousScreen = null;
    public long lastModified;
    public File swetoJar;

    public Sweto() throws InterruptedException, IOException {
        INSTANCE = this;
        mc = class_310.method_1551();
        this.eventManager = new EventManager();
        this.moduleManager = new ModuleManager();
        this.rotatorManager = new R0T1AT0RM4NG3R();
        this.profileManager = new ProfileManager();
        this.friendManager = new FriendManager();
        new ColorManager();
        this.setLastModified();
        this.guiInitialized = false;
    }

    public ProfileManager getProfileManager() {
        return this.profileManager;
    }

    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }

    public FriendManager getFriendManager() {
        return this.friendManager;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public void resetModifiedDate() {
        this.swetoJar.setLastModified(this.lastModified);
    }

    public String getVersion() {
        return this.version.toString();
    }

    public void setLastModified() {
        try {
            this.swetoJar = new File(Sweto.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            this.lastModified = this.swetoJar.lastModified();
        }
        catch (URISyntaxException uRISyntaxException) {
            // empty catch block
        }
    }
}

