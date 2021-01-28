package com.hyahm.utils;

import com.hyahm.HyahmMain;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.ArrayList;
import java.util.List;

public class Keystrokes {
    long ticks = 0;
    List<Long> rightClicks = new ArrayList<>();
    List<Long> leftClicks = new ArrayList<>();

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onTick(TickEvent.ClientTickEvent event) {
        if(event.phase == TickEvent.Phase.END) {
            ticks++;
            if(leftClicks.size() != 0)
                leftClicks.removeIf(l -> (l < ticks - HyahmMain.config.cpsConfig.setRemoveTickDelay));
            if(rightClicks.size() != 0)
                rightClicks.removeIf(l -> (l < ticks - HyahmMain.config.cpsConfig.setRemoveTickDelay));
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPostRender(RenderGameOverlayEvent.Text event) {
        Gui.drawRect(22, 4, 38, 20, 0x40FFFFFF);
        Gui.drawRect(4, 22, 20, 38, 0x40FFFFFF);
        Gui.drawRect(22, 22, 38, 38, 0x40FFFFFF);
        Gui.drawRect(40, 22, 56, 38, 0x40FFFFFF);
        Gui.drawRect(4, 40,28, 56, 0x40FFFFFF);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onClickMouseEvent(MouseEvent event) {
        if(event.isCanceled())
            return;
        switch  (event.button) {
            case 0:
                leftClicks.add(ticks);
            case 2:
                rightClicks.add(ticks);
        }
    }
}
