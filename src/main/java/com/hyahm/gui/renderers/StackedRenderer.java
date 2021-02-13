package com.hyahm.gui.renderers;

import com.hyahm.utils.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.util.Stack;

public class StackedRenderer implements IRenderer{
    private static class PackData {
        int x, y;
        double scale;

        PackData(int x, int y, double scale) {
            this.x=x;
            this.y = y;
            this.scale = scale;
        }
    }
    Stack<PackData> pos;
    FontRenderer f;
    int x, y;
    double scale;

    public StackedRenderer() {
        x = y = 0;
        pos = new Stack<>();
        f = Minecraft.getMinecraft().fontRendererObj;
        scale = 1;
    }

    public void draw(int x, int y, int width, int height, int color, double scale) {
        GL11.glPushMatrix();
        GlStateManager.scale(scale * this.scale, scale * this.scale, scale * this.scale);
        Gui.drawRect((int) ((this.x + x) * (1 / (this.scale * scale))),
                (int) ((this.y + y) * (1 / (this.scale * scale))),
                this.x + x + width,
                this.y + y + height,
                color
        );
        GL11.glPopMatrix();
    }

    @Override
    public void drawString(String t, int x, int y, int color, boolean shadow, double scale) {
        GL11.glPushMatrix();
        GlStateManager.scale(scale * this.scale, scale * this.scale, scale * this.scale);
        f.drawString(t, (float) ((this.x + x)* (1 / (this.scale * scale))), (float) ((this.y + y)* (1 / (this.scale * scale))), color, shadow);
        GL11.glPopMatrix();

    }

    public void push(int x, int y, double scale) {
        this.x += x;
        this.y += y;
        this.scale *= scale;

        pos.push(new PackData(this.x, this.y, this.scale));
    }

    public void pop() {
        PackData i = pos.pop();
        this.x -= i.x;
        this.y -= i.y;
        this.scale /= i.scale;
    }

    public void drawAndPush(int x, int y, int width, int height, int color, double scale) {
        draw(x, y, width, height, color, scale);
        push(x, y, scale);
    }
}
