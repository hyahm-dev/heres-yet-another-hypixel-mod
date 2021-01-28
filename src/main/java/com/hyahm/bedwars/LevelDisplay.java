package com.hyahm.bedwars;

import com.hyahm.HyahmMain;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LevelDisplay {
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPlayerNameFormat(PlayerEvent.NameFormat event) {
        if(event.isCanceled() || HyahmMain.config.bedwarsConfig.isLevelsEnabled) {

        }
    }
}
