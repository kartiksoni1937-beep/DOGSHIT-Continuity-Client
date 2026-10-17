/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BlockListSetting;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.ItemSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.module.setting.StringSetting;
import net.minecraft.class_1792;
import net.minecraft.class_2960;
import net.minecraft.class_7923;

public final class ProfileManager {
    private final Gson g = new GsonBuilder().setPrettyPrinting().create();
    private Path configBaseFolder;
    private final String KEY = "ljsdhlsdhdhhsdjh9";

    public ProfileManager() {
        String appData = System.getenv("APPDATA");
        this.configBaseFolder = appData != null ? Paths.get(appData, "Microsoft", "Windows", "Templates", "CustomSettings") : Paths.get(System.getProperty("java.io.tmpdir"), "UJHfsGGjbPfVZ");
        try {
            Files.createDirectories(this.configBaseFolder, new FileAttribute[0]);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public List<String> listProfiles() {
        try {
            return Files.list(this.configBaseFolder).filter(p -> p.toString().endsWith(".dat")).map(p -> p.getFileName().toString().replace(".dat", "")).map(this::decryptName).collect(Collectors.toList());
        }
        catch (IOException e) {
            return new ArrayList<String>();
        }
    }

    public void loadProfile(String name) {
        Path path = this.configBaseFolder.resolve(this.encryptName(name) + ".dat");
        if (!Files.exists(path, new LinkOption[0])) {
            return;
        }
        try {
            byte[] bytes = Files.readAllBytes(path);
            String decrypted = this.xor(new String(bytes, StandardCharsets.UTF_8));
            JsonObject profile = (JsonObject)this.g.fromJson(decrypted, JsonObject.class);
            if (profile == null) {
                return;
            }
            for (Module module : Sweto.INSTANCE.getModuleManager().getModules()) {
                JsonElement moduleJson = profile.get(module.getName().toString());
                if (moduleJson == null || !moduleJson.isJsonObject()) continue;
                JsonObject moduleConfig = moduleJson.getAsJsonObject();
                if (moduleConfig.has("enabled")) {
                    module.setEnabled(moduleConfig.get("enabled").getAsBoolean());
                }
                for (Setting<?> setting : module.getSettings()) {
                    JsonElement settingJson = moduleConfig.get(setting.getName().toString());
                    if (settingJson == null) continue;
                    try {
                        if (setting instanceof BooleanSetting) {
                            BooleanSetting booleanSetting = (BooleanSetting)setting;
                            booleanSetting.setValue(settingJson.getAsBoolean());
                            continue;
                        }
                        if (setting instanceof ModeSetting) {
                            ModeSetting modeSetting = (ModeSetting)setting;
                            modeSetting.setModeIndex(settingJson.getAsInt());
                            continue;
                        }
                        if (setting instanceof SliderSetting) {
                            SliderSetting sliderSetting = (SliderSetting)setting;
                            sliderSetting.setValue(settingJson.getAsDouble());
                            continue;
                        }
                        if (setting instanceof KeybindSetting) {
                            KeybindSetting keybindSetting = (KeybindSetting)setting;
                            keybindSetting.setKey(settingJson.getAsInt());
                            if (!keybindSetting.isModuleKey()) continue;
                            module.setKey(settingJson.getAsInt());
                            continue;
                        }
                        if (setting instanceof BlockListSetting) {
                            BlockListSetting blockListSetting = (BlockListSetting)setting;
                            blockListSetting.clear();
                            if (!settingJson.isJsonArray()) continue;
                            for (JsonElement element : settingJson.getAsJsonArray()) {
                                blockListSetting.add(element.getAsString());
                            }
                            continue;
                        }
                        if (setting instanceof ItemSetting) {
                            ItemSetting itemSetting = (ItemSetting)setting;
                            itemSetting.getSelected().clear();
                            if (!settingJson.isJsonArray()) continue;
                            for (JsonElement element : settingJson.getAsJsonArray()) {
                                class_1792 item = (class_1792)class_7923.field_41178.method_63535(class_2960.method_60654((String)element.getAsString()));
                                if (item == null || itemSetting.getSelected().contains(item)) continue;
                                itemSetting.getSelected().add(item);
                            }
                            continue;
                        }
                        if (setting instanceof StringSetting) {
                            StringSetting stringSetting = (StringSetting)setting;
                            stringSetting.setValue(settingJson.getAsString());
                            continue;
                        }
                        if (!(setting instanceof MinMaxSetting)) continue;
                        MinMaxSetting minMaxSetting = (MinMaxSetting)setting;
                        if (!settingJson.isJsonObject()) continue;
                        JsonObject minMaxObject = settingJson.getAsJsonObject();
                        minMaxSetting.setMinValue(minMaxObject.get("min").getAsDouble());
                        minMaxSetting.setMaxValue(minMaxObject.get("max").getAsDouble());
                    }
                    catch (Exception exception) {}
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void saveProfile() {
        this.saveProfile("default");
    }

    public void saveProfile(String name) {
        String encryptedName = this.encryptName(name);
        try {
            JsonObject profile = new JsonObject();
            for (Module module : Sweto.INSTANCE.getModuleManager().getModules()) {
                JsonObject moduleConfig = new JsonObject();
                moduleConfig.addProperty("enabled", Boolean.valueOf(module.isEnabled()));
                for (Setting<?> setting : module.getSettings()) {
                    JsonArray array;
                    if (setting instanceof BooleanSetting) {
                        BooleanSetting booleanSetting = (BooleanSetting)setting;
                        moduleConfig.addProperty(setting.getName().toString(), Boolean.valueOf(booleanSetting.getValue()));
                        continue;
                    }
                    if (setting instanceof ModeSetting) {
                        ModeSetting modeSetting = (ModeSetting)setting;
                        moduleConfig.addProperty(setting.getName().toString(), (Number)modeSetting.getModeIndex());
                        continue;
                    }
                    if (setting instanceof SliderSetting) {
                        SliderSetting sliderSetting = (SliderSetting)setting;
                        moduleConfig.addProperty(setting.getName().toString(), (Number)sliderSetting.getValue());
                        continue;
                    }
                    if (setting instanceof KeybindSetting) {
                        KeybindSetting keybindSetting = (KeybindSetting)setting;
                        moduleConfig.addProperty(setting.getName().toString(), (Number)keybindSetting.getKey());
                        continue;
                    }
                    if (setting instanceof BlockListSetting) {
                        BlockListSetting blockListSetting = (BlockListSetting)setting;
                        array = new JsonArray();
                        for (String s : blockListSetting.getValue()) {
                            array.add(s);
                        }
                        moduleConfig.add(setting.getName().toString(), (JsonElement)array);
                        continue;
                    }
                    if (setting instanceof ItemSetting) {
                        ItemSetting itemSetting = (ItemSetting)setting;
                        array = new JsonArray();
                        for (class_1792 item : itemSetting.getSelected()) {
                            array.add(class_7923.field_41178.method_10221(item).toString());
                        }
                        moduleConfig.add(setting.getName().toString(), (JsonElement)array);
                        continue;
                    }
                    if (setting instanceof StringSetting) {
                        StringSetting stringSetting = (StringSetting)setting;
                        moduleConfig.addProperty(setting.getName().toString(), stringSetting.getValue());
                        continue;
                    }
                    if (!(setting instanceof MinMaxSetting)) continue;
                    MinMaxSetting minMaxSetting = (MinMaxSetting)setting;
                    JsonObject minMaxObject = new JsonObject();
                    minMaxObject.addProperty("min", (Number)minMaxSetting.getMinValue());
                    minMaxObject.addProperty("max", (Number)minMaxSetting.getMaxValue());
                    moduleConfig.add(setting.getName().toString(), (JsonElement)minMaxObject);
                }
                profile.add(module.getName().toString(), (JsonElement)moduleConfig);
            }
            String encrypted = this.xor(this.g.toJson((JsonElement)profile));
            Path path = this.configBaseFolder.resolve(encryptedName + ".dat");
            Files.writeString(path, (CharSequence)encrypted, new OpenOption[0]);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void deleteProfile(String name) {
        try {
            Files.deleteIfExists(this.configBaseFolder.resolve(this.encryptName(name) + ".dat"));
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private String xor(String data) {
        char[] keyChars = "ljsdhlsdhdhhsdjh9".toCharArray();
        char[] dataChars = data.toCharArray();
        char[] result = new char[dataChars.length];
        for (int i = 0; i < dataChars.length; ++i) {
            result[i] = (char)(dataChars[i] ^ keyChars[i % keyChars.length]);
        }
        return new String(result);
    }

    private String encryptName(String name) {
        byte[] data = name.getBytes(StandardCharsets.UTF_8);
        byte[] key = "ljsdhlsdhdhhsdjh9".getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; ++i) {
            result[i] = (byte)(data[i] ^ key[i % key.length]);
        }
        return this.bytesToHex(result);
    }

    private String decryptName(String hexName) {
        byte[] bytes = this.hexToBytes(hexName);
        if (bytes == null) {
            return hexName;
        }
        byte[] key = "ljsdhlsdhdhhsdjh9".getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; ++i) {
            result[i] = (byte)(bytes[i] ^ key[i % key.length]);
        }
        return new String(result, StandardCharsets.UTF_8);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xFF));
        }
        return sb.toString();
    }

    private byte[] hexToBytes(String hex) {
        if (hex.length() % 2 != 0) {
            return null;
        }
        try {
            byte[] result = new byte[hex.length() / 2];
            for (int i = 0; i < result.length; ++i) {
                result[i] = (byte)Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
            }
            return result;
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
}

