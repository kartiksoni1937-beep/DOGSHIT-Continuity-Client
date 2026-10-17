/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.combat;

import java.util.Random;
import me.pepperbell.continuity.event.events.HudListener;
import me.pepperbell.continuity.event.events.PacketSendListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.module.setting.ModeSetting;
import me.pepperbell.continuity.module.setting.SliderSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.TimerUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_2848;
import org.lwjgl.glfw.GLFW;

public final class _5pr1n7R3537
extends Module
implements PacketSendListener,
HudListener {
    private final ModeSetting<Mode> mode = new ModeSetting<Mode>(EncryptedString.of("M-$$\u00b7o-\u00b7$~d|-$e"), Mode.WTap, Mode.class);
    private final SliderSetting chance = new SliderSetting(EncryptedString.of("C\u00ad$\u00b7-h\u00b7-`a-~\u00b7n\u00ad__c$_e"), 1.0, 100.0, 100.0, 1.0);
    private final MinMaxSetting delay = new MinMaxSetting(EncryptedString.of("D|~e\u00ad~~\u00adl\u00ad~a_$*-y"), 0.0, 1000.0, 1.0, 50.0, 100.0);
    private final MinMaxSetting timing = new MinMaxSetting(EncryptedString.of("T\u00ad`i-|m\u00ad\u00ad\u00ad|i~*n.*`g"), 0.0, 500.0, 1.0, 30.0, 60.0);
    private final BooleanSetting onlyWhileSprinting = (BooleanSetting)new BooleanSetting(EncryptedString.of("O^.n\u00ad^-l$**\u00b7y_-\u00ad .*-S~~\u00b7\u00adp^`r-.i-$n_^.t\u00b7.i._n.|\u00adg"), true).setDescription(EncryptedString.of("O^_`|n*|\u00b7\u00adl\u00ad_y~\u00ad` .^$\u00adr.\u00ad\u00ade$~__s\u00b7\u00b7.^e~$t$*s$\u00ad~ ~$~|s\u00b7^\u00ad\u00adp_`_r|`i*`.~n`|t*\u00b7*| ~\u00adw$.^^h`$*e^$-^n\u00b7* ._|y~|o$~$|u-.* .*a`~^_r_$-e~` \u00b7\u00ada$\u00b7-\u00adl*$r.^e||~a\u00ad^\u00b7d|$-y-. ~$s|^p^\u00ad.r$-`i_$.n*\u00b7|t*$_i..~n|_.g"));
    private final BooleanSetting onlyOnGround = (BooleanSetting)new BooleanSetting(EncryptedString.of("O.\u00ad..n\u00ad`~l--y|_- \u00ad~G\u00ad-|r\u00b7|\u00b7-o.^\u00b7u..n-$\u00add"), true).setDescription(EncryptedString.of("O`_-n$_l``*~y\u00b7. ||r-_\u00b7^e|`\u00b7|s^_.e.\u00ad_|t$\u00b7s*^ |_s*^\u00adp*\u00ad\u00ad^r-*.$i\u00b7~$n\u00b7`t.- `$w^*h__e|.*n\u00ad`_ -.\u00b7\u00ado$`-n|$^ ~~*\u00b7g\u00ad\u00ad\u00ad\u00adr\u00ad$\u00b7o*\u00ad|_u$``n$\u00b7\u00add"));
    private final TimerUtils resetTimer = new TimerUtils();
    private final TimerUtils releaseTimer = new TimerUtils();
    private final Random random = new Random();
    private boolean needsReset;
    private boolean resetting;
    private int currentDelay;
    private int currentTiming;

    public _5pr1n7R3537() {
        super(EncryptedString.of("S^`|$p*-r|~~i-`^_n\u00b7`t\u00b7`| `~R$$e^-^s-|*e$~\u00ad\u00adt"), EncryptedString.of("R**\u00b7e~\u00ad-s$`\u00b7.e`$^t*`s\u00ad\u00b7*^ `~$$y`^\u00b7-o`|u.$-r$|~ `-|s$`\u00b7p|\u00b7r.|.|i^$`n`\u00b7t*$$ ~~|o$\u00ad`\u00b7n\u00b7` $--*a_\u00ad.t^$`-t$$**a*.c`\u00ad|_k_|-_ ~`*f*_|_o`^r|`$ *_*m*_-$o`\u00ad|r~`__e`| \u00ad|^~k_.\u00ad`n\u00ad*$^o_.$-c.\u00ad.k\u00b7\u00b7b-|^a.$|\u00b7c-$\u00b7_k"), -1, Category.COMBAT);
        this.addSettings(this.mode, this.chance, this.delay, this.timing, this.onlyWhileSprinting, this.onlyOnGround);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(PacketSendListener.class, this);
        this.eventManager.add(HudListener.class, this);
        this.needsReset = false;
        this.resetting = false;
        this.currentDelay = this.delay.getRandomValueInt();
        this.currentTiming = this.timing.getRandomValueInt();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(PacketSendListener.class, this);
        this.eventManager.remove(HudListener.class, this);
        if (this.resetting) {
            this.restoreState();
        }
        super.onDisable();
    }

    @Override
    public void onRenderHud(HudListener.HudEvent event) {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (this.needsReset && this.resetTimer.delay(this.currentDelay)) {
            this.needsReset = false;
            this.resetting = true;
            this.releaseTimer.reset();
            this.performReset();
        }
        if (this.resetting && this.releaseTimer.delay(this.currentTiming)) {
            this.restoreState();
            this.resetting = false;
            this.currentDelay = this.delay.getRandomValueInt();
            this.currentTiming = this.timing.getRandomValueInt();
        }
    }

    @Override
    public void onPacketSend(PacketSendListener.PacketSendEvent event) {
        class_2596 class_25962 = event.packet;
        if (!(class_25962 instanceof class_2824)) {
            return;
        }
        class_2824 packet = (class_2824)class_25962;
        packet.method_34209(new class_2824.class_5908(){

            public void method_34219(class_1268 hand) {
            }

            public void method_34220(class_1268 hand, class_243 pos) {
            }

            public void method_34218() {
                if (_5pr1n7R3537.this.resetting || _5pr1n7R3537.this.needsReset) {
                    return;
                }
                if (_5pr1n7R3537.this.onlyOnGround.getValue() && !((_5pr1n7R3537)_5pr1n7R3537.this).mc.field_1724.method_24828()) {
                    return;
                }
                if (_5pr1n7R3537.this.onlyWhileSprinting.getValue() && !((_5pr1n7R3537)_5pr1n7R3537.this).mc.field_1724.method_5624()) {
                    return;
                }
                if (_5pr1n7R3537.this.random.nextInt(100) + 1 > _5pr1n7R3537.this.chance.getValueInt()) {
                    return;
                }
                _5pr1n7R3537.this.needsReset = true;
                _5pr1n7R3537.this.resetTimer.reset();
            }
        });
    }

    private void performReset() {
        switch (this.mode.getMode().ordinal()) {
            case 0: {
                this.mc.field_1690.field_1894.method_23481(false);
                break;
            }
            case 1: {
                this.mc.field_1690.field_1894.method_23481(false);
                this.mc.field_1690.field_1881.method_23481(true);
                break;
            }
            case 2: {
                this.mc.field_1690.field_1832.method_23481(true);
                break;
            }
            case 3: {
                this.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)this.mc.field_1724, class_2848.class_2849.field_12985));
            }
        }
    }

    private void restoreState() {
        switch (this.mode.getMode().ordinal()) {
            case 0: {
                if (GLFW.glfwGetKey((long)this.mc.method_22683().method_4490(), (int)87) != 1) break;
                this.mc.field_1690.field_1894.method_23481(true);
                break;
            }
            case 1: {
                this.mc.field_1690.field_1881.method_23481(GLFW.glfwGetKey((long)this.mc.method_22683().method_4490(), (int)83) == 1);
                if (GLFW.glfwGetKey((long)this.mc.method_22683().method_4490(), (int)87) != 1) break;
                this.mc.field_1690.field_1894.method_23481(true);
                break;
            }
            case 2: {
                this.mc.field_1690.field_1832.method_23481(GLFW.glfwGetKey((long)this.mc.method_22683().method_4490(), (int)340) == 1);
                break;
            }
            case 3: {
                this.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)this.mc.field_1724, class_2848.class_2849.field_12981));
            }
        }
    }

    public static enum Mode {
        WTap,
        STap,
        Sneak,
        Packet;

    }
}

