package com.hyahm.gui.renderers;

import com.hyahm.utils.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import java.util.Stack;

public class StackedRenderer implements IRenderer{
    Stack<Pair<Integer, Integer>> pos;
    FontRenderer f;
    int x, y;

    public StackedRenderer() {
        x = y = 0;
        pos = new Stack<>();
        f = Minecraft.getMinecraft().fontRendererObj;
    }

    public void draw(int x, int y, int width, int height, int color) {
        Gui.drawRect(this.x + x,
                this.y + y,
                this.x + x + width,
                this.y + y + height,
                color
        );
    }

    @Override
    public void drawString(String t, int x, int y, int color, boolean shadow) {
        f.drawString(t, this.x + x, this.y + y, color, shadow);
    }

    public void push(int x, int y) {
        this.x += x;
        this.y += y;

        pos.push(new Pair<>(x, y));
    }

    public void pop() {
        Pair<Integer, Integer> i = pos.pop();
        this.x -= i.getKey();
        this.y -= i.getVal();
    }

    public void drawAndPush(int x, int y, int width, int height, int color) {
        draw(x, y, width, height, color);
        push(x, y);
    }



}
