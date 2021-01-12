package com.hyahm.autotip;

import com.hyahm.Constants;
import com.hyahm.HyahmMain;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoTipEvent {
    int tickCounter = 1;

    @SubscribeEvent(priority = EventPriority.LOW)
    void onTick(TickEvent.ClientTickEvent event) {
        if(event.isCanceled() || !HyahmMain.getInstance().config.autoTipConfig.isEnabled)
            return;

        if(tickCounter == 0)
        {
            // verify its both a server and it is hypixel
            if(Constants.isHypixel())
                return;

            Minecraft.getMinecraft().thePlayer.sendChatMessage("/tip all");
        }

        tickCounter++;
        tickCounter = tickCounter % 18000;
    }
}
