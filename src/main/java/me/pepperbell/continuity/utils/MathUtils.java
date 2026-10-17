/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.utils;

import java.util.Random;

public final class MathUtils {
    private static final Random RANDOM = new Random();

    public static double roundToStep(double value, double step) {
        if (step == 0.0) {
            return value;
        }
        return (double)Math.round(value / step) * step;
    }

    public static int randomInt(int min, int max) {
        if (min >= max) {
            return min;
        }
        return RANDOM.nextInt(max - min + 1) + min;
    }

    public static double roundToDecimal(double value, int places) {
        double scale = Math.pow(10.0, places);
        return (double)Math.round(value * scale) / scale;
    }

    public static double roundToDecimal(double value, double inc) {
        if (inc == 0.0) {
            return value;
        }
        return (double)Math.round(value / inc) * inc;
    }

    public static float goodLerp(float delta, float start, float end) {
        float result = start + delta * (end - start);
        if (start < end) {
            return Math.max(start, Math.min(end, result));
        }
        return Math.max(end, Math.min(start, result));
    }

    public static double goodLerp(double delta, double start, double end) {
        double result = start + delta * (end - start);
        if (start < end) {
            return Math.max(start, Math.min(end, result));
        }
        return Math.max(end, Math.min(start, result));
    }

    public static double smoothStepLerp(double delta, double start, double end) {
        double d = delta * delta * (3.0 - 2.0 * delta);
        return start + d * (end - start);
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}

