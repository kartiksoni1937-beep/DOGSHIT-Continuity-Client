/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import me.pepperbell.continuity.utils.MathUtils;

public final class AnimationUtils {
    private double value;
    private final double originalValue;
    private double endValue;

    public AnimationUtils(double value) {
        this.value = value;
        this.originalValue = value;
    }

    public double animate(double delta, double end, double speed) {
        this.endValue = end;
        this.value = MathUtils.goodLerp((float)(delta * speed), (float)this.value, (float)end);
        return this.value;
    }

    public double animate(double delta, double end) {
        return this.animate(delta, end, 1.0);
    }

    public double getValue() {
        return this.value;
    }

    public double getOriginalValue() {
        return this.originalValue;
    }

    public double getEndValue() {
        return this.endValue;
    }

    public double getAnimationProgress() {
        return this.value / this.endValue;
    }

    public void reset(double delta) {
        this.value = MathUtils.smoothStepLerp(delta, this.value, this.originalValue);
    }
}

