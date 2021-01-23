package com.hyahm.gui;

import net.minecraft.client.gui.Gui;

public class ScalableRect extends ScalableGUIElement {
    protected int color;
    public ScalableRect(double widthScale, double lengthScale, double scalePosX, double scalePosY, int color) {
        super(widthScale, lengthScale, scalePosX, scalePosY);
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void render() {
        int sizeY = res.getScaledHeight();
        int sizeX = res.getScaledWidth();


        Gui.drawRect((int) (sizeX * scalePosX),
                (int) (sizeY * scalePosY),
                (int) (sizeX * scalePosX + sizeX * widthScale),
                (int) (sizeY * scalePosY + sizeY * lengthScale),
                this.color
        );
    }

    public void render(int color) {
        int sizeY = res.getScaledHeight();
        int sizeX = res.getScaledWidth();


        Gui.drawRect((int) (sizeX * scalePosX),
                (int) (sizeY * scalePosY),
                (int) (sizeX * scalePosX + sizeX * widthScale),
                (int) (sizeY * scalePosY + sizeY * lengthScale),
                color
        );
    }
}
