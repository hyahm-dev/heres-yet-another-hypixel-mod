package com.hyahm.autogg;

import com.hyahm.Constants;
import com.hyahm.HyahmMain;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Pattern;

public class AutoGGEvents {

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChatEvent(ClientChatReceivedEvent event)
    {
        HyahmMain.getInstance().logger.info("Received event");
        // go match server and server IP

        if(Minecraft.getMinecraft().getCurrentServerData() == null) {
            HyahmMain.getInstance().logger.info("OMG THE f IS THIS WHAT");
            return;
        }

        HyahmMain.getInstance().logger.info("HYAHM connected to: " + Minecraft.getMinecraft().getCurrentServerData().serverName);

        if(Minecraft.getMinecraft().getCurrentServerData().serverIP != "mc.hypixel.net")
            return;

        if(!HyahmMain.getInstance().isServer())
            return;
        if(event.isCanceled())
            return;

        String msg = (Pattern.compile("(?i)" + '\u00A7' + "[0-9A-FK-OR]"))
            .matcher(event.message.getUnformattedText())
            .replaceAll("");

        if(!Constants.AutoGGMatch.stream().anyMatch(msg::contains))
            return;

        for (Pattern expr: Constants.MatchNormal) {
            if(expr.matcher(msg).matches())
                return;
        }

        Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac gg");
    }
}
