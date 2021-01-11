package com.hyahm.autogg;

import com.hyahm.Constants;
import com.hyahm.HyahmMain;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashSet;
import java.util.regex.Pattern;

public class AutoGGEvents {
    private int enqueueTime = -1;
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChatEvent(ClientChatReceivedEvent event)
    {
        if(event.isCanceled() || HyahmMain.getInstance().isEnabled)
            return;

        // verify its both a server and it is hypixel
        if(Minecraft.getMinecraft().getCurrentServerData() == null)
            return;
        if(!Minecraft.getMinecraft().getCurrentServerData().serverIP.endsWith("hypixel.net"))
            return;


        // delete orphans because we all are technoblade
        String msg = (Pattern.compile("(?i)" + '\u00A7' + "[0-9A-FK-OR]"))
            .matcher(event.message.getUnformattedText())
            .replaceAll("");

        // anarchy
        if(!Constants.AutoGGMatch.stream().anyMatch(msg::contains))
            return;

        for (Pattern expr : Constants.MatchNormal) {
            if(expr.matcher(msg).matches()) {
                HyahmMain.getInstance().logger.info("sus ngl: " + expr.toString());
                return;
            }
        }

        // schedule da task
        enqueueTime = HyahmMain.getInstance().delay;
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    void onTick(TickEvent.ClientTickEvent event) {
        if(enqueueTime == 0)
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac gg");
        if(enqueueTime > -1)
            enqueueTime--;
    }
}
