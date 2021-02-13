package com.hyahm.gui;

import com.hyahm.gui.renderers.IRenderer;

public class RectangleWindow implements IWindow{
    public int x, y, width, height, scale;
    protected IRenderer renderer;
    public int color;

    public RectangleWindow() {
        x = y = width = height = 0;
        scale = 1;
        renderer = null;
    }

    public RectangleWindow(int x, int y, int width, int length, int color) {
        this(x, y, width, length, color, 1);
    }


    public RectangleWindow(int x, int y, int width, int length, int color, int scale) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = length;
        this.color = color;
        this.scale = scale;
    }

    @Override
    public void render() {
        renderer.draw(x, y, width, height, color, scale);
    }

    @Override
    public IRenderer getRenderer() {
        return renderer;
    }

    @Override
    public void setRenderer(IRenderer renderer) {
        this.renderer = renderer;
    }
}
