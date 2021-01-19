package com.hyahm.gui;

import net.minecraft.client.Minecraft;

public abstract class ScalableGUIElement {
    protected double widthScale;
    protected double lengthScale;
    protected double scalePosX;
    protected double scalePosY;

    public ScalableGUIElement(double widthScale, double lengthScale, double scalePosX, double scalePosY) {
        this.lengthScale = lengthScale;
        this.widthScale = widthScale;
        this.scalePosX = scalePosX;
        this.scalePosY = scalePosY;
    }

    public double getLengthScale() {
        return lengthScale;
    }

    public double getWidthScale() {
        return widthScale;
    }

    public double getScalePosX() {
        return scalePosX;
    }

    public double getScalePosY() {
        return scalePosY;
    }

    public void setScalePosX(double scalePosX) {
        this.scalePosX = scalePosX;
    }

    public void setScalePosY(double scalePosY) {
        this.scalePosY = scalePosY;
    }

    public void setLengthScale(double lengthScale) {
        this.lengthScale = lengthScale;
    }

    public void setWidthScale(double widthScale) {
        this.widthScale = widthScale;
    }

    public int getPixelPosX() {
        return (int) (scalePosX * Minecraft.getMinecraft().displayWidth);
    }

    public int getPixelPosY() {
        return (int) (scalePosY * Minecraft.getMinecraft().displayHeight);
    }

    abstract void render();
}
