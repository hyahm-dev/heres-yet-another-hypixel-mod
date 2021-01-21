package com.hyahm.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class ScalableTextRect extends ScalableRect{
    private String text;
    private FontRenderer fontRenderer;
    public static final int pixelSize = 10;

    public ScalableTextRect(double widthScale, double lengthScale, double scalePosX, double scalePosY, int color) {
        super(widthScale, lengthScale, scalePosX, scalePosY, color);
        this.fontRenderer = Minecraft.getMinecraft().fontRendererObj;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void render() {
        super.render();
        //double factor = (double)pixelSize / (double)Minecraft.getMinecraft().displayHeight;
        //float trueScale = (float) (super.lengthScale / factor);
        //super.render();
        //GL11.glPushMatrix();
        //GL11.glScalef(trueScale, trueScale, trueScale);
        fontRenderer.drawStringWithShadow(text, super.getPixelPosX(), super.getPixelPosY(), 0xFFFFFF); //fr - fontRenderer
        //GL11.glPopMatrix(); //End this matrix
    }
}
