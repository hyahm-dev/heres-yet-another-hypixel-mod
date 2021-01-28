package com.hyahm.gui.renderers;

import javafx.util.Pair;
import net.minecraft.client.gui.Gui;

import javax.swing.*;
import java.util.Stack;

public class StackedRenderer implements IRenderer{
    Stack<Pair<Integer, Integer>> pos;
    int x, y;

    public StackedRenderer() {
        x = y = 0;
        pos = new Stack<>();
    }

    public void draw(int x, int y, int width, int height, int color) {
        Gui.drawRect(this.x + x,
                this.y + y,
                this.x + x + width,
                this.y + y + height,
                color
        );
    }

    public void push(int x, int y) {
        this.x += x;
        this.y += y;

        pos.push(new Pair<>(x, y));
    }

    public void pop() {
        Pair<Integer, Integer> i = pos.pop();
        this.x -= i.getKey();
        this.y -= i.getValue();
    }

    public void drawAndPush(int x, int y, int width, int height, int color) {
        push(x, y);
        draw(x, y, width, height, color);
    }



}
