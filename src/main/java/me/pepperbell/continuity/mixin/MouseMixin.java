/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import me.pepperbell.continuity.event.EventManager;
import me.pepperbell.continuity.event.events.ButtonListener;
import me.pepperbell.continuity.event.events.MouseMoveListener;
import me.pepperbell.continuity.event.events.MouseUpdateListener;
import net.minecraft.class_310;
import net.minecraft.class_312;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_312.class})
public abstract class MouseMixin {
    @Shadow
    @Final
    private class_310 field_1779;
    @Unique
    private double Sweto$lastMouseX;
    @Unique
    private double Sweto$lastMouseY;
    @Unique
    private boolean Sweto$initialized;
    @Unique
    private final int[] Sweto$buttonStates = new int[8];

    @Shadow
    public abstract double method_1603();

    @Shadow
    public abstract double method_1604();

    @Inject(method={"method_55793"}, at={@At(value="TAIL")})
    private void onMouseUpdate(CallbackInfo ci) {
        EventManager.fire(new MouseUpdateListener.MouseUpdateEvent());
        long window = this.field_1779.method_22683().method_4490();
        double x = this.method_1603();
        double y = this.method_1604();
        if (!this.Sweto$initialized) {
            this.Sweto$initialized = true;
            this.Sweto$lastMouseX = x;
            this.Sweto$lastMouseY = y;
            for (int button = 0; button < this.Sweto$buttonStates.length; ++button) {
                this.Sweto$buttonStates[button] = GLFW.glfwGetMouseButton((long)window, (int)button);
            }
            return;
        }
        if (x != this.Sweto$lastMouseX || y != this.Sweto$lastMouseY) {
            this.Sweto$lastMouseX = x;
            this.Sweto$lastMouseY = y;
            EventManager.fire(new MouseMoveListener.MouseMoveEvent(window, x, y));
        }
        for (int button = 0; button < this.Sweto$buttonStates.length; ++button) {
            int state = GLFW.glfwGetMouseButton((long)window, (int)button);
            if (state == this.Sweto$buttonStates[button]) continue;
            this.Sweto$buttonStates[button] = state;
            EventManager.fire(new ButtonListener.ButtonEvent(button, window, state, 0));
        }
    }
}

