/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.module;

import java.util.ArrayList;
import java.util.List;
import me.pepperbell.continuity.Sweto;
import me.pepperbell.continuity.event.events.ButtonListener;
import me.pepperbell.continuity.module.Category;
import me.pepperbell.continuity.module.Module;
import me.pepperbell.continuity.module.modules.client.C0nf195;
import me.pepperbell.continuity.module.modules.client.C11ck9U1;
import me.pepperbell.continuity.module.modules.client.Fr13nd5;
import me.pepperbell.continuity.module.modules.client._531fD357ruc7;
import me.pepperbell.continuity.module.modules.combat.Cry57410p71m1z3r;
import me.pepperbell.continuity.module.modules.combat.D0u8134nch0r;
import me.pepperbell.continuity.module.modules.combat.H0V3R70173M;
import me.pepperbell.continuity.module.modules.combat.H1780x;
import me.pepperbell.continuity.module.modules.combat.Kn0ck84ckD15p14c3m3n7;
import me.pepperbell.continuity.module.modules.combat.M4c35w4p;
import me.pepperbell.continuity.module.modules.combat.N0M155D314y;
import me.pepperbell.continuity.module.modules.combat.R34ch;
import me.pepperbell.continuity.module.modules.combat.R4nd14u70Cry5741;
import me.pepperbell.continuity.module.modules.combat.R4nd1V413K4M4cr0;
import me.pepperbell.continuity.module.modules.combat.V310c17y;
import me.pepperbell.continuity.module.modules.combat._41m455157;
import me.pepperbell.continuity.module.modules.combat._4n717073m0ffh4nd;
import me.pepperbell.continuity.module.modules.combat._4nch0rM4cr0;
import me.pepperbell.continuity.module.modules.combat._4u101013m;
import me.pepperbell.continuity.module.modules.combat._4u70Cry5741;
import me.pepperbell.continuity.module.modules.combat._4u70D0u813H4nd;
import me.pepperbell.continuity.module.modules.combat._4u70Dr41n;
import me.pepperbell.continuity.module.modules.combat._4u70H17Cry5741;
import me.pepperbell.continuity.module.modules.combat._4u70M4c3;
import me.pepperbell.continuity.module.modules.combat._4u70P07;
import me.pepperbell.continuity.module.modules.combat._4u70P07R3f111;
import me.pepperbell.continuity.module.modules.combat._4u70P34r1C47ch;
import me.pepperbell.continuity.module.modules.combat._4u70W1ndCh4r93;
import me.pepperbell.continuity.module.modules.combat._54f34nch0r;
import me.pepperbell.continuity.module.modules.combat._54f34nch0rV2;
import me.pepperbell.continuity.module.modules.combat._5h131dD154813r;
import me.pepperbell.continuity.module.modules.combat._5h131dR34c710n;
import me.pepperbell.continuity.module.modules.combat._5p34r5w4p;
import me.pepperbell.continuity.module.modules.combat._5p34rM4c3D0u81374p;
import me.pepperbell.continuity.module.modules.combat._5p34rR34ch;
import me.pepperbell.continuity.module.modules.combat._5pr1n7R3537;
import me.pepperbell.continuity.module.modules.combat._5w0rdH17;
import me.pepperbell.continuity.module.modules.combat._7r1993r807;
import me.pepperbell.continuity.module.modules.combat._FL1CKH17;
import me.pepperbell.continuity.module.modules.misc.K3yP34r1;
import me.pepperbell.continuity.module.modules.misc.N08r34kD314y;
import me.pepperbell.continuity.module.modules.misc.N0JumpD314y;
import me.pepperbell.continuity.module.modules.misc.P1n95p00f;
import me.pepperbell.continuity.module.modules.misc.Pr3v3n7;
import me.pepperbell.continuity.module.modules.misc._4n71F149;
import me.pepperbell.continuity.module.modules.misc._4u70XP;
import me.pepperbell.continuity.module.modules.misc._5pr1n7;
import me.pepperbell.continuity.module.modules.player.F457P14c3;
import me.pepperbell.continuity.module.modules.player.N0H0784rM355up;
import me.pepperbell.continuity.module.modules.render.Fu118r19h7;
import me.pepperbell.continuity.module.modules.render.N00v3r14y;
import me.pepperbell.continuity.module.modules.render.N080unc3;
import me.pepperbell.continuity.module.modules.render.N4m3Pr073c7;
import me.pepperbell.continuity.module.modules.render.P14y3r35P;
import me.pepperbell.continuity.module.modules.render._570r49335p;
import me.pepperbell.continuity.module.modules.render._74r937Hud;
import me.pepperbell.continuity.module.modules.world._8r1d93455157;
import me.pepperbell.continuity.module.setting.KeybindSetting;
import me.pepperbell.continuity.module.setting.Setting;
import me.pepperbell.continuity.utils.EncryptedString;

public final class ModuleManager
implements ButtonListener {
    private final List<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        this.addModules();
        this.addKeybinds();
    }

    public void addModules() {
        this.add(new _41m455157().setSubCategory("Sword"));
        this.add(new _4nch0rM4cr0().setSubCategory("Crystal"));
        this.add(new R4nd1V413K4M4cr0().setSubCategory("Crystal"));
        this.add(new _54f34nch0r().setSubCategory("Crystal"));
        this.add(new _54f34nch0rV2().setSubCategory("Crystal"));
        this.add(new _4u70Cry5741().setSubCategory("Crystal"));
        this.add(new _4u70D0u813H4nd().setSubCategory("Crystal"));
        this.add(new _4u70H17Cry5741().setSubCategory("Crystal"));
        this.add(new _7r1993r807().setSubCategory("Sword"));
        this.add(new _4u70P07().setSubCategory("UHC"));
        this.add(new _4u70M4c3().setSubCategory("Mace"));
        this.add(new _4u70P07R3f111().setSubCategory("UHC"));
        this.add(new Cry57410p71m1z3r().setSubCategory("Crystal"));
        this.add(new D0u8134nch0r().setSubCategory("Crystal"));
        this.add(new N0M155D314y().setSubCategory("Sword"));
        this.add(new _5h131dD154813r().setSubCategory("Crystal"));
        this.add(new V310c17y().setSubCategory("Movement"));
        this.add(new M4c35w4p().setSubCategory("Mace"));
        this.add(new _4u70P34r1C47ch().setSubCategory("Mace"));
        this.add(new _4u70W1ndCh4r93().setSubCategory("Mace"));
        this.add(new _4u70Dr41n().setSubCategory("UHC"));
        this.add(new _5pr1n7R3537().setSubCategory("Movement"));
        this.add(new _5p34r5w4p().setSubCategory("Mace"));
        this.add(new _5p34rM4c3D0u81374p().setSubCategory("Mace"));
        this.add(new _5p34rR34ch().setSubCategory("Mace"));
        this.add(new H1780x().setSubCategory("Sword"));
        this.add(new R34ch().setSubCategory("Sword"));
        this.add(new Kn0ck84ckD15p14c3m3n7().setSubCategory("Sword"));
        this.add(new _5h131dR34c710n().setSubCategory("Sword"));
        this.add(new _5w0rdH17().setSubCategory("Sword"));
        this.add(new _FL1CKH17().setSubCategory("Sword"));
        this.add(new _4u101013m().setSubCategory("Crystal"));
        this.add(new H0V3R70173M().setSubCategory("Crystal"));
        this.add(new _4n717073m0ffh4nd().setSubCategory("Crystal"));
        this.add(new R4nd14u70Cry5741().setSubCategory("Crystal"));
        this.add(new Pr3v3n7());
        this.add(new _4u70XP());
        this.add(new N0JumpD314y().setSubCategory("Movement"));
        this.add(new P1n95p00f());
        this.add(new K3yP34r1());
        this.add(new N08r34kD314y());
        this.add(new _5pr1n7().setSubCategory("Movement"));
        this.add(new _4n71F149());
        this.add(new _8r1d93455157().setSubCategory("Movement"));
        this.add(new F457P14c3());
        this.add(new N0H0784rM355up());
        this.add(new N080unc3());
        this.add(new P14y3r35P());
        this.add(new _570r49335p());
        this.add(new _74r937Hud());
        this.add(new Fu118r19h7());
        this.add(new N4m3Pr073c7());
        this.add(new N00v3r14y());
        this.add(new C11ck9U1());
        this.add(new C0nf195());
        this.add(new Fr13nd5());
        this.add(new _531fD357ruc7());
    }

    public List<Module> getEnabledModules() {
        return this.modules.stream().filter(Module::isEnabled).toList();
    }

    public List<Module> getModules() {
        return this.modules;
    }

    public void addKeybinds() {
        Sweto.INSTANCE.getEventManager().add(ButtonListener.class, this);
        for (Module module : this.modules) {
            if (module instanceof _531fD357ruc7) continue;
            module.addSetting((Setting<?>)new KeybindSetting(EncryptedString.of("K$*.e_^y.$~.b|^\u00b7$i`^n|`\u00add"), module.getKey(), true).setDescription(EncryptedString.of("K*-*~e-$-y-^ ~^.t.`*o\u00b7* .~e~\u00ad\u00b7^n\u00b7||_a$^`^b\u00b7_$.l--|e^`_^d_\u00b7~^ *\u00adt``h.\u00ad\u00b7$e*~ $^m*|o`\u00ad`~d^.\u00b7u\u00b7*-l~\u00ad-.e")));
        }
    }

    public List<Module> getModulesInCategory(Category category) {
        return this.modules.stream().filter(module -> module.getCategory() == category).toList();
    }

    public <T extends Module> T getModule(Class<T> moduleClass) {
        return (T)((Module)this.modules.stream().filter(moduleClass::isInstance).findFirst().orElse(null));
    }

    public void add(Module module) {
        this.modules.add(module);
    }

    @Override
    public void onButtonPress(ButtonListener.ButtonEvent event) {
        if (event.action == 1 && event.button == 39) {
            this.getModule(C11ck9U1.class).toggle();
            return;
        }
        if (event.button == 261 && event.action == 1) {
            this.getModule(_531fD357ruc7.class).setEnabled(true);
            return;
        }
        this.modules.forEach(module -> {
            if (module.getKey() > 0 && module.getKey() == event.button && event.action == 1) {
                module.toggle();
            }
        });
    }
}

