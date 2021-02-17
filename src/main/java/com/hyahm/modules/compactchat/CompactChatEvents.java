package com.hyahm.modules.compactchat;

import com.hyahm.modules.ModuleEventHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleEventHandler(name = "compact chat")
public class CompactChatEvents {
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChatEvent(ClientChatReceivedEvent event) {
    }
}
