package com.hyahm.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public abstract class ScalableGUIElement {
    protected double widthScale;
    protected double lengthScale;
    protected double scalePosX;
    protected double scalePosY;
    static final ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());

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
        return (int) (scalePosX * res.getScaledWidth());
    }

    public int getPixelPosY() {
        return (int) (scalePosY * res.getScaledHeight());
    }

    public void setPixelPosX(int pixelPosX) { this.scalePosY = (double)pixelPosX / (double)res.getScaledWidth(); }

    public void setPixelPosY(int pixelPosY) {
        this.scalePosY = (double)pixelPosY / (double)res.getScaledHeight();
    }

    public abstract void render();
}
