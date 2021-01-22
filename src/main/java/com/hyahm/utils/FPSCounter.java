package com.hyahm.utils;

import com.hyahm.HyahmMain;
import com.hyahm.gui.ScalableTextRect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class FPSCounter {
    private ScalableTextRect fps;
    private int tick = 0;

    public FPSCounter() {
        fps = new ScalableTextRect(.5, .5, .1, .1, 0xFFFFFFFF);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onTick(TickEvent.ClientTickEvent event) {
        if(event.isCanceled())
            return;

        if(tick == 0)
            fps.setText(Integer.toString(Minecraft.getDebugFPS()));
        tick++;
        tick = tick % 30;

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPostRender(RenderGameOverlayEvent.Text event) {
        fps.render();
    }
}