package com.hyahm.autotip;

import com.hyahm.ConfigManager;
import com.hyahm.Constants;
import com.hyahm.HyahmMain;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.config.ConfigGuiType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoTipEvent {
    int tickCounter = 1;

    @SubscribeEvent(priority = EventPriority.LOW)
    void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            HyahmMain.logger.info(tickCounter);
            if (event.isCanceled() || !ConfigManager.autoTipConfig.isEnabled)
                return;
            if(!Constants.isHypixel())
                return;

            tickCounter++;
            tickCounter = tickCounter % ConfigManager.autoTipConfig.delay;
            
            if (tickCounter == 0) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/tip all");
                HyahmMain.logger.info("triggered");
            }
        }
    }
}
