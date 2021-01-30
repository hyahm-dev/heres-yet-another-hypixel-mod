package com.hyahm.gui.renderers;

public interface IRenderer {
    void draw(int x, int y, int width, int height, int color);
    void drawString(String t, int x, int y, int color, boolean shadow);
}
