package com.hyahm.gui;

import com.hyahm.gui.renderers.IRenderer;

public interface IWindow {
    void render();
    IRenderer getRenderer();
    void setRenderer(IRenderer renderer);
}
