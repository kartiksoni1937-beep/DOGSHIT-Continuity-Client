/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import net.minecraft.class_310;
import net.minecraft.class_312;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_310.class})
public interface MinecraftClientAccessor {
    @Accessor(value="field_1729")
    public class_312 getMouse();

    @Invoker(value="method_1583")
    public void invokeDoItemUse();

    @Invoker(value="method_1536")
    public boolean invokeDoAttack();

    @Accessor(value="field_1752")
    public void setItemUseCooldown(int var1);
}

