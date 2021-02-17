package com.hyahm.modules.autotip;

import com.hyahm.modules.ModuleEventHandler;
import com.hyahm.utils.Utils;
import com.hyahm.HyahmMain;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@ModuleEventHandler(name = "autotip")
public class AutoTipEvent {
    int tickCounter = 1;

    @SubscribeEvent(priority = EventPriority.LOW)
    void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (event.isCanceled() || !HyahmMain.config.autoTipConfig.isEnabled)
                return;
            if(!Utils.isHypixel())
                return;

            tickCounter++;
            tickCounter = tickCounter % HyahmMain.config.autoTipConfig.delay;
            
            if (tickCounter == 0)
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/tip all");
        }
    }
}
