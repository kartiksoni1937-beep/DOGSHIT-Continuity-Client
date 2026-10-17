/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.client;

import java.awt.Color;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.AttackListener;
import me.pepperbell.continuity.event.events.ButtonListener;
import me.pepperbell.continuity.event.events.HudListener;
import me.pepperbell.continuity.managers.FriendManager;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.setting.BooleanSetting;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import me.pepperbell.continuity.utils.RenderUtils;
import me.pepperbell.continuity.utils.TextRenderer;
import me.pepperbell.continuity.utils.WorldUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_332;
import net.minecraft.class_3966;

public final class Fr13nd5
extends Module
implements ButtonListener,
AttackListener,
HudListener {
    private final KeybindSetting addFriendKey = (KeybindSetting)new KeybindSetting(EncryptedString.of("F~`r\u00b7*i$^`e\u00b7.`^n.^|d\u00b7` $`K|\u00ad\u00ad*e^~|y"), 2, false).setDescription(EncryptedString.of("K^|^e``\u00b7`y\u00b7|^\u00b7 _`\u00b7$t*~o_\u00b7 `^a*^^_d-\u00add*~*/_`.r\u00ad^$e|_m$~*$o~-\u00b7v\u00b7~\u00ad$e.~.$ .\u00b7f-$`r__i`~^\u00ade.\u00b7_\u00adn\u00b7^d\u00b7^\u00b7.s"));
    public final BooleanSetting antiAttack = (BooleanSetting)new BooleanSetting(EncryptedString.of("A-^n\u00b7._t^^i*\u00b7.A.\u00adt_$-t^`a.\u00ad__c-_k"), false).setDescription(EncryptedString.of("D$`\u00b7o``e|\u00b7^s^\u00b7\u00adn$\u00b7|'-~t.^*` ^$\u00b7$l$-e~_~_t\u00b7\u00b7` `\u00b7y._~`o._u~*~ ^~h`^i~|t\u00b7| \u00ad|*f\u00ad\u00adr`-i.\u00b7e^`\u00ad$n_\u00ad~\u00add\u00b7^_s"));
    public final BooleanSetting disableAimAssist = (BooleanSetting)new BooleanSetting(EncryptedString.of("A~.n`.t~|$i\u00ad$A\u00ad-i___m"), false).setDescription(EncryptedString.of("D~^_\u00b7i~\u00b7$s\u00b7-^-a._b\u00b7^.l$*\u00b7-e^|s*^`* \u00b7-$~a*-$`i.-^\u00b7m`^ _*.\u00b7a\u00ad\u00b7._s~||s_.i_-.s$*t$\u00ad ^.f|.-$o$.r`*$ __|f$`-`r-\u00adi**`e|_`n`$d\u00b7-\u00b7^s"));
    public final BooleanSetting friendStatus = (BooleanSetting)new BooleanSetting(EncryptedString.of("F.~~\u00adr_*`i*$e.*|^n~\u00ad||d.` .*.S\u00ad*`|t\u00ad\u00ad~a\u00b7\u00b7\u00ad_t^*_-u$\u00ad^s"), false).setDescription(EncryptedString.of("T`~`e~\u00b7`~l^|-l.\u00b7~*s*|$ *$~y\u00b7$`$o$.u|$|. .^-i\u00b7*f_* `.y^\u00b7|o\u00b7*.u||'._r*$.e*| --a$`i||m_\u00b7|.i^.n.`$g$` \u00ad|a*`t|$~ -`a\u00b7$*| |_`~f_\u00b7``r*$i\u00b7-$~e.~n_*d$- |~*~o|_r\u00ad~ .~*~n\u00b7*|o-~~~t"));
    private FriendManager manager;

    public Fr13nd5() {
        super(EncryptedString.of("F$~r.$i|~e\u00ad._-n~~-~d|\u00b7-s"), EncryptedString.of("T\u00ad^`\u00b7h^$\u00b7$i\u00ad~.s*_** ._m^|o.*|d\u00ad~~u~\u00ad^_l$\u00ade-$|. $$\u00b7m_~a_~.\u00b7k_|.|e|\u00ad.s$~$^ ||^_i_-t\u00ad^* \u00ad$`-s^`-*o`~.- _^y$^\u00ado__u$\u00ad `.^\u00adc~$*a.$$n$-\u00ad'~^~t--^- \u00ad*`\u00b7d._$.o*- -*c*_e|^-r_\u00ad~*t.*|a|\u00b7i-*`n^-~* |\u00b7\u00ads*~~t\u00b7`u$\u00b7_f_^-_f$|~ _*i.^\u00b7f^\u00b7~* \u00ad~y*-|o*.u*~** _`_.h^|a-`\u00adv\u00b7-\u00ad\u00b7e-|\u00ad\u00ad \u00b7\u00ada\u00b7_~ \u00b7*p\u00ad\u00ad$l\u00b7.~\u00ada`\u00ady-*e-_r^. .-`f`~~-r`--i\u00b7~e-\u00b7$-n*^d`-\u00b7*e.-d~$\u00b7_!"), -1, Category.CLIENT);
        this.addSettings(this.addFriendKey, this.antiAttack, this.disableAimAssist, this.friendStatus);
        this.setKey(-1);
    }

    @Override
    public void onEnable() {
        this.manager = Sweto.INSTANCE.getFriendManager();
        this.eventManager.add(ButtonListener.class, this);
        this.eventManager.add(AttackListener.class, this);
        this.eventManager.add(HudListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(ButtonListener.class, this);
        this.eventManager.remove(AttackListener.class, this);
        this.eventManager.remove(HudListener.class, this);
        super.onDisable();
    }

    @Override
    public void onButtonPress(ButtonListener.ButtonEvent event) {
        class_3966 hitResult;
        class_1297 entity;
        if (this.mc.field_1724 == null) {
            return;
        }
        if (this.mc.field_1755 != null) {
            return;
        }
        class_239 class_2392 = this.mc.field_1765;
        if (class_2392 instanceof class_3966 && (entity = (hitResult = (class_3966)class_2392).method_17782()) instanceof class_1657) {
            class_1657 player = (class_1657)entity;
            if (event.button == this.addFriendKey.getKey() && event.action == 1) {
                if (!this.manager.isFriend(player)) {
                    this.manager.addFriend(player);
                } else {
                    this.manager.removeFriend(player);
                }
            }
        }
    }

    @Override
    public void onAttack(AttackListener.AttackEvent event) {
        if (!this.antiAttack.getValue()) {
            return;
        }
        if (this.manager.isAimingOverFriend()) {
            event.cancel();
        }
    }

    @Override
    public void onRenderHud(HudListener.HudEvent event) {
        class_1657 player;
        class_3966 hitResult;
        class_1297 entity;
        if (!this.friendStatus.getValue()) {
            return;
        }
        class_332 context = event.context;
        RenderUtils.unscaledProjection(context, 1.0f);
        class_239 class_2392 = WorldUtils.getHitResult(100.0);
        if (class_2392 instanceof class_3966 && (entity = (hitResult = (class_3966)class_2392).method_17782()) instanceof class_1657 && this.manager.isFriend(player = (class_1657)entity)) {
            TextRenderer.drawCenteredString(EncryptedString.of("P`*~l^$*^a-`y$`^*e|\u00b7$^r_. *_`i`\u00ad^-s*|- \u00b7~f~._.r|~\u00adi`.e$-`n*-^.d"), context, this.mc.method_22683().method_4480() / 2, this.mc.method_22683().method_4507() / 2 + 25, Color.GREEN.getRGB());
        }
        RenderUtils.scaledProjection(context);
    }
}

