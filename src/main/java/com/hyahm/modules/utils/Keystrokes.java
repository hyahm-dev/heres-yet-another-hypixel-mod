package com.hyahm.modules.utils;

import com.hyahm.HyahmMain;
import com.hyahm.gui.CompoundWindow;
import com.hyahm.gui.TextWindow;
import net.minecraft.client.Minecraft;
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
    CompoundWindow ui;

    public Keystrokes() {
        final int y = (16 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT) / 2 + 1;

        // creates the W key
        ui = new CompoundWindow(0, 0, 0 ,0, 0);
        ui.addWindow("field_0001_a", new CompoundWindow(22, 4, 16, 16, 0x40FFFFFF)
            .addWindow("field_0001_b", new TextWindow(
                    "W",
                    (16 - Minecraft.getMinecraft().fontRendererObj.getCharWidth('W')) / 2,
                    y,
                    0x60FFFFFF,
                    false
                )
            )
        );


        // creates the A key
        ui.addWindow("field_0002_a", new CompoundWindow(4, 22, 16, 16, 0x40FFFFFF)
                .addWindow("field_0002_b", new TextWindow(
                        "A",
                        (16 - Minecraft.getMinecraft().fontRendererObj.getCharWidth('A')) / 2,
                        y,
                        0x60FFFFFF,
                        false
                )
        ));

        // creates the S key
        ui.addWindow("field_0003_a", new CompoundWindow(22, 22, 16, 16, 0x40FFFFFF)
                .addWindow("field_0003_b", new TextWindow(
                        "S",
                        (16 - Minecraft.getMinecraft().fontRendererObj.getCharWidth('S')) / 2,
                        y,
                        0x60FFFFFF,
                        false
                )
        ));

        // creates the D key
        ui.addWindow("field_0004_a", new CompoundWindow(40, 22, 16, 16, 0x40FFFFFF)
                .addWindow("field_0004_b", new TextWindow(
                        "D",
                        (16 - Minecraft.getMinecraft().fontRendererObj.getCharWidth('D')) / 2,
                        y,
                        0x60FFFFFF,
                        false
                )
        ));

        // creates the left and right click keys
        ui.addWindow("LC", new CompoundWindow(4, 40 , 25, 16, 0x40FFFFFF).addWindow("text", new TextWindow(
                "0",
                0,
                y,
                0x60FFFFFF,
                false,
                0.5
        )).addWindow("name", new TextWindow(
                "LMB",
                (24 - Minecraft.getMinecraft().fontRendererObj.getStringWidth("LMB")) / 2 + 1,
                y,
                0x60FFFFFF,
                false
        )));

        ui.addWindow("RC", new CompoundWindow(31, 40 , 25, 16, 0x40FFFFFF).addWindow("text", new TextWindow(
                "0",
                0,
                y,
                0x60FFFFFF,
                false,
                0.5
        )).addWindow("name", new TextWindow(
                "RMB",
                (24 - Minecraft.getMinecraft().fontRendererObj.getStringWidth("RMB")) / 2 + 1,
                y,
                0x60FFFFFF,
                false
        )));



        // sets some values
        ui.getCompoundWindow("LC").getTextWindow("text").y = 8;
        ui.getCompoundWindow("RC").getTextWindow("text").y = 8;
        ui.getCompoundWindow("LC").getTextWindow("text").color = 0x60FFFFFF;
        ui.getCompoundWindow("RC").getTextWindow("text").color = 0x60FFFFFF;
    }

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

        // calculate cps and then set
        ui.getCompoundWindow("LC").getTextWindow("text").text =
                Integer.toString((int)((double)leftClicks.size() / ((double)HyahmMain.config.cpsConfig.setRemoveTickDelay / 20.d)));
        ui.getCompoundWindow("RC").getTextWindow("text").text =
                Integer.toString((int)((double)rightClicks.size() / ((double)HyahmMain.config.cpsConfig.setRemoveTickDelay / 20.d)));

        TextWindow tlc = ui.getCompoundWindow("LC").getTextWindow("text");
        TextWindow trc = ui.getCompoundWindow("RC").getTextWindow("text");
        tlc.x = (24 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(tlc.text) / 2) / 2;
        trc.x = (24 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(trc.text) / 2) / 2;

        ui.render();

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onClickMouseEvent(MouseEvent event) {
        if(!event.buttonstate)
            return;
        switch  (event.button) {
            case 0:
                leftClicks.add(ticks);
                break;
            case 1:
                rightClicks.add(ticks);
        }
    }
}
