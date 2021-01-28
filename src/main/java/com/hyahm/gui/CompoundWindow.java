package com.hyahm.gui;

import com.hyahm.gui.renderers.IRenderer;
import com.hyahm.gui.renderers.StackedRenderer;

import java.util.HashMap;
import java.util.Map;

public class CompoundWindow implements IWindow {
    StackedRenderer renderer;
    HashMap<String, IWindow> subWindows;


    public IRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(IRenderer renderer) {
        if(!(renderer instanceof StackedRenderer))
            throw new IllegalArgumentException("Ohes Noes You are a dumbass");

        this.renderer = (StackedRenderer) renderer;

        for (Map.Entry<String, IWindow> entry : subWindows.entrySet()) {
            entry.getValue().setRenderer(renderer);
        }
    }

    public CompoundWindow() {
        renderer = new StackedRenderer();
    }

    public void addWindow(String name, IWindow window) {
        window.setRenderer(renderer);
        subWindows.put(name, window);
    }

    public void removeWindow(String name) {
        subWindows.remove(name);
    }

    public IWindow getWindow(String name) {
        return subWindows.get(name);
    }

    @Override
    public void render() {

    }
}
