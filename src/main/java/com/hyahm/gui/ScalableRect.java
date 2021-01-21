package com.hyahm.gui;

import com.hyahm.HyahmMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class ScalableRect extends ScalableGUIElement{
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
    void render() {
        ScaledResolution r = new ScaledResolution(Minecraft.getMinecraft());
        int sizeY = r.getScaledHeight();
        int sizeX = r.getScaledWidth();

        HyahmMain.logger.info(sizeX);
        HyahmMain.logger.info(sizeY);

        Gui.drawRect((int)(sizeX * scalePosX),
                (int)(sizeY * scalePosY),
                (int)(sizeX * scalePosX + sizeX * widthScale),
                (int)(sizeY * scalePosY + sizeY * lengthScale),
                this.color
        );
    }
}
