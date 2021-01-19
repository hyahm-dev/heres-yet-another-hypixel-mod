package com.hyahm.utils;

import com.hyahm.gui.ScalableTextRect;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class GenericUI {
    ScalableTextRect rect;
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onTick(TickEvent.ClientTickEvent event) {
        if(event.isCanceled())
            return;

    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onPostRender(RenderGameOverlayEvent.Post event) {
        // render gui??? pogg??
    }
}
