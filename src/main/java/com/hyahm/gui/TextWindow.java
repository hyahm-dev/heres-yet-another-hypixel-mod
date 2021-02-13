package com.hyahm.gui;

import com.hyahm.gui.renderers.IRenderer;

public class TextWindow implements IWindow {
    protected IRenderer renderer;
    public int color;
    public int x, y;
    public double scale;
    public String text;
    public boolean shadow;

    public TextWindow() {
        color = 0;
        renderer = null;
    }

    public TextWindow(String text, int x, int y, int color, boolean shadow) {
        this(text, x, y, color, shadow, 1);
    }

    public TextWindow(String text, int x, int y, int color, boolean shadow, double scale) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        this.shadow = shadow;
        this.scale = scale;
    }

    @Override
    public void render() {
        renderer.drawString(text, x, y, color, shadow, scale);
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
