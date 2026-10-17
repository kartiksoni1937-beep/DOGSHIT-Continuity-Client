/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.renderer;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;
import net.minecraft.class_310;
import net.minecraft.class_332;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class LwjglShader {
    private static int programId = -1;
    private static int vaoId = -1;
    private static int vboId = -1;
    private static int projMatLoc;
    private static int modelViewMatLoc;
    private static int colorLoc;
    private static int color2Loc;
    private static int sizeLoc;
    private static int radiusLoc;
    private static int thicknessLoc;
    private static int softnessLoc;
    private static FloatBuffer projBuffer;
    private static FloatBuffer mvBuffer;
    public static boolean FORCE_FALLBACK;

    private static String loadShaderSource(String path) {
        try (InputStream is = LwjglShader.class.getResourceAsStream(path)) {
            if (is == null) {
                return null;
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }

    private static void init() {
        if (programId != -1) {
            return;
        }
        try {
            String vertexSource = LwjglShader.loadShaderSource("/assets/immediatelyfast/shaders/rounded_quad.vert");
            String fragmentSource = LwjglShader.loadShaderSource("/assets/immediatelyfast/shaders/rounded_quad.frag");
            if (vertexSource == null || fragmentSource == null) {
                return;
            }
            int vShader = LwjglShader.compileShader(35633, vertexSource);
            int fShader = LwjglShader.compileShader(35632, fragmentSource);
            programId = GL20.glCreateProgram();
            GL20.glAttachShader((int)programId, (int)vShader);
            GL20.glAttachShader((int)programId, (int)fShader);
            GL20.glBindAttribLocation((int)programId, (int)0, (CharSequence)"Position");
            GL20.glLinkProgram((int)programId);
            if (GL20.glGetProgrami((int)programId, (int)35714) == 0) {
                programId = -2;
                return;
            }
            GL20.glDeleteShader((int)vShader);
            GL20.glDeleteShader((int)fShader);
            projMatLoc = GL20.glGetUniformLocation((int)programId, (CharSequence)"ProjMat");
            modelViewMatLoc = GL20.glGetUniformLocation((int)programId, (CharSequence)"ModelViewMat");
            colorLoc = GL20.glGetUniformLocation((int)programId, (CharSequence)"Color");
            color2Loc = GL20.glGetUniformLocation((int)programId, (CharSequence)"Color2");
            sizeLoc = GL20.glGetUniformLocation((int)programId, (CharSequence)"Size");
            radiusLoc = GL20.glGetUniformLocation((int)programId, (CharSequence)"Radius");
            thicknessLoc = GL20.glGetUniformLocation((int)programId, (CharSequence)"Thickness");
            softnessLoc = GL20.glGetUniformLocation((int)programId, (CharSequence)"Softness");
            vaoId = GL30.glGenVertexArrays();
            vboId = GL15.glGenBuffers();
            GL30.glBindVertexArray((int)vaoId);
            GL15.glBindBuffer((int)34962, (int)vboId);
            float[] vertices = new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f};
            FloatBuffer buffer = BufferUtils.createFloatBuffer((int)vertices.length);
            buffer.put(vertices).flip();
            GL15.glBufferData((int)34962, (FloatBuffer)buffer, (int)35044);
            GL20.glVertexAttribPointer((int)0, (int)2, (int)5126, (boolean)false, (int)0, (long)0L);
            GL20.glEnableVertexAttribArray((int)0);
            GL15.glBindBuffer((int)34962, (int)0);
            GL30.glBindVertexArray((int)0);
        }
        catch (Exception e) {
            programId = -2;
        }
    }

    private static int compileShader(int type, String source) {
        int shader = GL20.glCreateShader((int)type);
        GL20.glShaderSource((int)shader, (CharSequence)source);
        GL20.glCompileShader((int)shader);
        if (GL20.glGetShaderi((int)shader, (int)35713) == 0) {
            throw new RuntimeException("Shader failed to compile");
        }
        return shader;
    }

    public static boolean drawRoundedQuad(class_332 context, Color color, double x, double y, double x2, double y2, double r1, double r2, double r3, double r4) {
        return LwjglShader.drawRoundedGradientQuad(context, color, color, x, y, x2, y2, r1, r2, r3, r4);
    }

    public static boolean drawRoundedOutline(class_332 context, Color color, double x, double y, double x2, double y2, double r1, double r2, double r3, double r4, double thickness) {
        return LwjglShader.drawShader(context, color, color, x, y, x2, y2, r1, r2, r3, r4, thickness, 0.0);
    }

    public static boolean drawGradientRoundedOutline(class_332 context, Color color, Color color2, double x, double y, double x2, double y2, double r1, double r2, double r3, double r4, double thickness) {
        return LwjglShader.drawShader(context, color, color2, x, y, x2, y2, r1, r2, r3, r4, thickness, 0.0);
    }

    public static boolean drawCircle(class_332 context, Color color, double x, double y, double radius) {
        return LwjglShader.drawRoundedQuad(context, color, x - radius, y - radius, x + radius, y + radius, radius, radius, radius, radius);
    }

    public static boolean drawRoundedGradientQuad(class_332 context, Color color, Color color2, double x, double y, double x2, double y2, double r1, double r2, double r3, double r4) {
        return LwjglShader.drawShader(context, color, color2, x, y, x2, y2, r1, r2, r3, r4, 0.0, 0.0);
    }

    private static boolean drawShader(class_332 context, Color color, Color color2, double x, double y, double x2, double y2, double r1, double r2, double r3, double r4, double thickness, double softness) {
        if (FORCE_FALLBACK) {
            return false;
        }
        LwjglShader.init();
        if (programId <= 0) {
            return false;
        }
        float width = (float)(x2 - x);
        float height = (float)(y2 - y);
        try {
            int lastProgram = GL11.glGetInteger((int)35725);
            int lastVao = GL11.glGetInteger((int)34229);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL20.glUseProgram((int)programId);
            class_310 mc = class_310.method_1551();
            Matrix4f projMat = new Matrix4f().setOrtho(0.0f, (float)mc.method_22683().method_4486(), (float)mc.method_22683().method_4502(), 0.0f, -1000.0f, 1000.0f);
            float[] projMatArr = new float[16];
            projMat.get(projMatArr);
            GL20.glUniformMatrix4fv((int)projMatLoc, (boolean)false, (float[])projMatArr);
            Matrix3x2fStack sm = context.method_51448();
            Matrix4f modelViewMat = new Matrix4f(sm.m00(), sm.m01(), 0.0f, 0.0f, sm.m10(), sm.m11(), 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, sm.m20(), sm.m21(), 0.0f, 1.0f).translate((float)x, (float)y, 0.0f).scale(width, height, 1.0f);
            float[] mvMatArr = new float[16];
            modelViewMat.get(mvMatArr);
            GL20.glUniformMatrix4fv((int)modelViewMatLoc, (boolean)false, (float[])mvMatArr);
            GL20.glUniform4f((int)colorLoc, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
            GL20.glUniform4f((int)color2Loc, (float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
            GL20.glUniform2f((int)sizeLoc, (float)width, (float)height);
            GL20.glUniform4f((int)radiusLoc, (float)((float)r3), (float)((float)r2), (float)((float)r4), (float)((float)r1));
            GL20.glUniform1f((int)thicknessLoc, (float)((float)thickness));
            GL20.glUniform1f((int)softnessLoc, (float)((float)softness));
            boolean depthTest = GL11.glIsEnabled((int)2929);
            if (depthTest) {
                GL11.glDisable((int)2929);
            }
            GL11.glDisable((int)2884);
            GL30.glBindVertexArray((int)vaoId);
            GL11.glDrawArrays((int)6, (int)0, (int)4);
            int err = GL11.glGetError();
            if (err != 0) {
                // empty if block
            }
            GL30.glBindVertexArray((int)lastVao);
            if (depthTest) {
                GL11.glEnable((int)2929);
            }
            GL11.glEnable((int)2884);
            GL20.glUseProgram((int)lastProgram);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    static {
        projBuffer = BufferUtils.createFloatBuffer((int)16);
        mvBuffer = BufferUtils.createFloatBuffer((int)16);
        FORCE_FALLBACK = false;
    }
}

