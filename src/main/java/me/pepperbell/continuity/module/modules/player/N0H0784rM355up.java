/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module.modules.player;

import me.pepperbell.continuity.event.events.ClickSlotListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.modules.combat._4u101013m;
import me.pepperbell.continuity.module.setting.ItemSetting;
import me.pepperbell.continuity.module.setting.MinMaxSetting;
import me.pepperbell.continuity.utils.EncryptedString;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1819;
import net.minecraft.class_3489;
import net.minecraft.class_9362;

public final class N0H0784rM355up
extends Module
implements ClickSlotListener {
    private final MinMaxSetting protectedHotbarSlots = (MinMaxSetting)new MinMaxSetting(EncryptedString.of("P|_$\u00b7r`~$^o__t_-e$.c.$t-\u00b7e``~*d`-_. *\u00b7$.H\u00b7~o\u00ad`t`-b|_a~*_r~_|\u00ad `\u00b7*\u00b7S^|.^l|\u00ad\u00ado.|~t-`.~s"), 1.0, 9.0, 1.0, 1.0, 9.0).setDescription(EncryptedString.of("H$-o*~`t.~b\u00b7*~*a\u00b7~r||\u00b7 ^\u00ad~s|..l*_*.o^\u00b7.$t*|`` \u00b7.r\u00b7-~-a*$_n*~^g^-^`e\u00ad\u00ad `_`^t\u00ad`h`\u00ad~a$-$_t$^~` |`c.`a^|n~~n`\u00b7^\u00ado~*^t_|\u00b7^ -.^.b*.|e|\u00b7-_ _||$m\u00b7\u00b7_-o|\u00ad.d*`*^i`*~f_\u00b7\u00b7i~|e*$`d$~^ .-.`u~-$^n*-\u00ad$l|~e\u00b7`_s~`.s|$ _*-|t-*h-^\u00b7e~|| \u00ad`\u00adi^^t~~|$e.|m--. `-^i*`~s~.|| _$-a^*l`~l$*|o-\u00ad`\u00adw*|e__d"));
    private final ItemSetting alwaysAllowItems = new ItemSetting(EncryptedString.of("A-`l^|^\u00b7w`^a.|~y-$\u00ads``` \u00ad\u00adA\u00ad*|l.\u00b7\u00adl*\u00ado`*~w__`- `*^`I^~$t`.\u00ad~e$_*\u00b7m^|*s"), EncryptedString.of("I$_`^t\u00b7*-_e|*m~~s*^ \u00ad-$t`*$h|.a*\u00adt\u00b7~\u00b7 $^\u00b7a`\u00ad~r$-$e\u00ad^ \u00ad\u00ada~$_l_^w|_`~a-$`y_.-s-~ -|a^*$l_-$$l\u00b7~o\u00ad~*~w.\u00b7*\u00ade`$d-|*_ \u00b7*_t_-o~* \u00ad\u00b7`^b*~~e`\u00b7* -|*\u00b7s\u00ad_`w_*\u00b7a\u00b7\u00ad^p\u00ad`~p~-e\u00b7.d**- |-`|i--~n|\u00b7t_$o*-. .|._p^|\u00ad`r-^`o\u00b7`\u00b7t\u00ad**e_*c.\u00b7\u00ad.t-^`e$\u00add^` .\u00adh|`\u00ado\u00ad-^t\u00b7_.b\u00ad|a~-_.r*~ *_s*\u00b7\u00adl_\u00b7o_\u00b7t^`s"), class_1802.field_8639, class_1802.field_8833);

    public N0H0784rM355up() {
        super(EncryptedString.of("N\u00b7\u00b7o|^`\u00adH_.\u00b7o\u00ad.t-\u00b7-b~-a*`|r.\u00b7.`M\u00b7`e|$^s*\u00ads_$`$u|^p"), EncryptedString.of("P\u00b7^_|r*$e|`~v^$-e\u00ad`~*n*\u00b7.t-|s*` `\u00ad\u00b7-m|.\u00b7`o\u00b7`-v|$|i|.~~n|--g$_ *$|d$_i$^s\u00ad|-|a~_^l|\u00b7l~$_*o\u00b7~w\u00b7-e\u00ad~\u00b7|d$`* _^\u00b7i\u00ad\u00ad*t.`..e~|m`_~\u00b7s\u00ad~ ~^.i\u00b7_~\u00adn\u00b7^_`t*\u00ad\u00b7|o||\u00ad~ -~-.p`*\u00ad|r\u00ad\u00ad.o-|t-_-e$^`~c|\u00b7t*_e`-$*d_\u00b7$^ $~h``o`*.t|-^b_\u00ad$a\u00b7$`_r**_` |_s\u00b7~*`l_^`o$_.-t$|s"), -1, Category.MISC);
        this.addSettings(this.protectedHotbarSlots, this.alwaysAllowItems);
    }

    @Override
    public void onEnable() {
        this.eventManager.add(ClickSlotListener.class, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.eventManager.remove(ClickSlotListener.class, this);
        super.onDisable();
    }

    @Override
    public void onClickSlot(ClickSlotListener.ClickSlotEvent event) {
        class_1799 sourceStack;
        class_1799 cursorStack;
        if (this.mc.field_1724 == null || this.mc.field_1687 == null) {
            return;
        }
        if (_4u101013m.bypassNoHotbarMessup) {
            return;
        }
        class_1703 handler = this.mc.field_1724.field_7512;
        if (handler == null) {
            return;
        }
        int slotId = event.getSlotId();
        if (slotId < 0 || slotId >= handler.field_7761.size()) {
            return;
        }
        class_1735 clickedSlot = handler.method_7611(slotId);
        int clickedInvIndex = clickedSlot.method_34266();
        int minHotbar = this.protectedHotbarSlots.getMinInt() - 1;
        int maxHotbar = this.protectedHotbarSlots.getMaxInt() - 1;
        boolean isPlayerInv = clickedSlot.field_7871 instanceof class_1661;
        boolean isHotbarSlot = isPlayerInv && clickedInvIndex >= 0 && clickedInvIndex < 9;
        boolean isProtectedHotbarSlot = isHotbarSlot && clickedInvIndex >= minHotbar && clickedInvIndex <= maxHotbar;
        class_1713 actionType = event.getActionType();
        int button = event.getButton();
        if (actionType == class_1713.field_7791) {
            class_1799 clickedStack;
            if (button == 40) {
                return;
            }
            int hotbarInvIndex = button;
            if (hotbarInvIndex >= minHotbar && hotbarInvIndex <= maxHotbar && !this.isAllowedForKeybindSwap(clickedStack = clickedSlot.method_7677())) {
                event.cancel();
            }
            return;
        }
        if (!(actionType != class_1713.field_7790 && actionType != class_1713.field_7793 || !isProtectedHotbarSlot || (cursorStack = handler.method_34255()).method_7960() || this.isAllowedForManualPlacement(cursorStack))) {
            event.cancel();
            return;
        }
        if (actionType == class_1713.field_7794 && !this.isAllowedForManualPlacement(sourceStack = clickedSlot.method_7677()) && minHotbar <= maxHotbar && !isHotbarSlot) {
            event.cancel();
            return;
        }
        if (actionType == class_1713.field_7789 && isProtectedHotbarSlot && !(cursorStack = handler.method_34255()).method_7960() && !this.isAllowedForManualPlacement(cursorStack)) {
            event.cancel();
        }
    }

    private boolean isAllowedForKeybindSwap(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return true;
        }
        class_1792 item = stack.method_7909();
        if (this.alwaysAllowItems.isSelected(item)) {
            return true;
        }
        return stack.method_31573(class_3489.field_42611) || item instanceof class_1743 || item.method_7876().toLowerCase().contains("pickaxe") || item instanceof class_1819 || item instanceof class_9362;
    }

    private boolean isAllowedForManualPlacement(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return true;
        }
        class_1792 item = stack.method_7909();
        if (item == class_1802.field_8833 || item == class_1802.field_23141 || item == class_1802.field_8801 || item == class_1802.field_8281 || item == class_1802.field_8463 || item == class_1802.field_8367 || item == class_1802.field_8634) {
            return true;
        }
        if (this.alwaysAllowItems.isSelected(item)) {
            return true;
        }
        return stack.method_31573(class_3489.field_42611) || item instanceof class_1743 || item.method_7876().toLowerCase().contains("pickaxe") || item instanceof class_1819 || item instanceof class_9362;
    }
}

