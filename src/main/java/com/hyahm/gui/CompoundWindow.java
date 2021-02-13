package com.hyahm.gui;

import com.hyahm.gui.renderers.IRenderer;
import com.hyahm.gui.renderers.StackedRenderer;

import javax.xml.soap.Text;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class CompoundWindow extends RectangleWindow implements IWindow {
    StackedRenderer renderer;
    HashMap<String, IWindow> subWindows;

    public CompoundWindow() {
        super();
        renderer = new StackedRenderer();
        subWindows = new HashMap<>();
    }

    public CompoundWindow(int x, int y, int width, int length, int color) {
        super(x, y, width, length, color);
        renderer = new StackedRenderer();
        subWindows = new HashMap<>();
    }

    @Override
    public IRenderer getRenderer() {
        return renderer;
    }

    @Override
    public void setRenderer(IRenderer renderer) {
        if(!(renderer instanceof StackedRenderer))
            throw new IllegalArgumentException("CompoundWindow must have StackedRenderer");

        this.renderer = (StackedRenderer) renderer;

        for (Map.Entry<String, IWindow> entry : subWindows.entrySet())
            entry.getValue().setRenderer(renderer);
    }

    public CompoundWindow addWindow(String name, IWindow window) {
        window.setRenderer(renderer);
        subWindows.put(name, window);
        return this;
    }

    public CompoundWindow removeWindow(String name) {
        subWindows.remove(name);
        return this;
    }

    public IWindow getWindow(String name) {
        return subWindows.get(name);
    }

    public CompoundWindow getCompoundWindow(String name) {
        if(subWindows.get(name) instanceof CompoundWindow)
            return (CompoundWindow) subWindows.get(name);
        throw new ClassCastException("Cannot get compound window");
    }

    public RectangleWindow getRectangleWindow(String name) {
        if(subWindows.get(name) instanceof RectangleWindow)
            return (RectangleWindow) subWindows.get(name);
        throw new ClassCastException("Cannot get rectangle window");
    }

    public TextWindow getTextWindow(String name) {
        if(subWindows.get(name) instanceof TextWindow)
            return (TextWindow) subWindows.get(name);
        throw new ClassCastException("Cannot get text window");
    }

    @Override
    public void render() {
        renderer.drawAndPush(super.x, super.y, super.width, super.height, super.color, super.scale);
        for (Map.Entry<String, IWindow> entry : subWindows.entrySet()) {
            entry.getValue().render();
        }
        renderer.pop();
    }
}
