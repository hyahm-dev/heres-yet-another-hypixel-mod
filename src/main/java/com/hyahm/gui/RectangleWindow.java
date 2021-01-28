package com.hyahm.gui;

import com.hyahm.gui.renderers.IRenderer;
import com.hyahm.gui.renderers.StackedRenderer;

public class RectangleWindow implements IWindow{
    int x, y, width, length;
    private IRenderer renderer;
    public RectangleWindow(int x, int y, int width, int length) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.length = length;
    }

    @Override
    public void render() {
        renderer.draw(x, y, width, length, color);
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
