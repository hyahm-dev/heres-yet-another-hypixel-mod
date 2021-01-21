package com.hyahm.utils;

import com.hyahm.HyahmMain;
import com.hyahm.gui.ScalableTextRect;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class FPSCounter {
    ScalableTextRect fps;

    public FPSCounter() {
        fps = new ScalableTextRect(.5, .5, .1, .2, 0xFFFFFFFF);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onTick(TickEvent.ClientTickEvent event) {
        if(event.isCanceled())
            return;

        fps.setText(Integer.toString(Minecraft.getDebugFPS()));

    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onPostRender(RenderGameOverlayEvent.Post event) {
        HyahmMain.logger.info("event called!");
        fps.render();
    }
}
