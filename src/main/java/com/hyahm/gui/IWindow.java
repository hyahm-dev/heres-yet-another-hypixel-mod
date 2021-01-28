package com.hyahm.gui;

import com.hyahm.gui.renderers.IRenderer;

public interface IWindow {
    int color = 0;

    void render();
    IRenderer getRenderer();
    void setRenderer(IRenderer renderer);
}
