package com.hyahm.modules.autogg;

import com.hyahm.HyahmMain;
import com.hyahm.hooks.GameEndEvent;
import com.hyahm.hooks.Hook;
import com.hyahm.modules.ModuleEventHandler;
import net.minecraft.client.Minecraft;

@ModuleEventHandler(name = "autogg")
public class AutoGGEvents {

    @Hook(level = 1000)
     public void autoGGHook(GameEndEvent e) {
        if(HyahmMain.config.autoGGConfig.isEnabled)
            HyahmMain.scheduler.enqueueEvent(HyahmMain.config.autoGGConfig.delay, () -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac gg"));
    }
}