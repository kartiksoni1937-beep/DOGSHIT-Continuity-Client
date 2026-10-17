/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.mixin;

import net.minecraft.class_1282;
import net.minecraft.class_1309;
import net.minecraft.class_2338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_1309.class})
public interface LivingEntityAccessor {
    @Accessor(value="field_6282")
    public boolean getJumping();

    @Accessor(value="field_6276")
    public class_1282 getLastDamageSource();

    @Accessor(value="field_6276")
    public void setLastDamageSource(class_1282 var1);

    @Accessor(value="field_6226")
    public long getLastDamageTime();

    @Accessor(value="field_6226")
    public void setLastDamageTime(long var1);

    @Accessor(value="field_6268")
    public class_2338 getLastBlockPos();

    @Accessor(value="field_6268")
    public void setLastBlockPos(class_2338 var1);

    @Accessor(value="field_6236")
    public void setAttacking(class_1309 var1);
}

