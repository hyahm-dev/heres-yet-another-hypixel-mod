package com.hyahm.autotip;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoTipEventHandler {
    @SubscribeEvent(priority = EventPriority.LOW)
    void onTick()
}
