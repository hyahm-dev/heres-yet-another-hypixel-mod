package com.hyahm.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ScalableTextRect {
    private double widthScale;
    private double lengthScale;
    private double scalePosX;
    private double scalePosY;

    public double getLengthScale() {
        return lengthScale;
    }

    public double getWidthScale() {
        return widthScale;
    }

    public void setLengthScale(double lengthScale) {
        this.lengthScale = lengthScale;
    }

    public void setWidthScale(double widthScale) {
        this.widthScale = widthScale;
    }

    public void render(String buffer) {
        int sizeY = Minecraft.getMinecraft().displayHeight;
        int sizeX = Minecraft.getMinecraft().displayWidth;
        Gui.drawRect((int)(sizeX * scalePosX),
                (int)(sizeY * scalePosY),
                (int)(sizeX * scalePosX + sizeX * widthScale),
                (int)(sizeY * scalePosY + sizeY * scalePosY),
                0
        );
    }
}
