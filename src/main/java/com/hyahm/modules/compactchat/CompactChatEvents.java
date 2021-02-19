package com.hyahm.modules.compactchat;

import com.hyahm.modules.ModuleEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleEventHandler(name = "compact chat")
public class CompactChatEvents {
    private String msg = "";
    private int line = 0;
    private int amount = 0;

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onClientReceivedChat(ClientChatReceivedEvent event) {
        final GuiNewChat gui = Minecraft.getMinecraft().ingameGUI.getChatGUI();

        if (event.type == 0) {

            if (msg.equals(event.message.getUnformattedText())) {
                gui.deleteChatLine(line);
                amount++;
                event.message.appendText(EnumChatFormatting.GRAY + " [x" + amount + "]");
            }
            else {
                amount = 1;
                msg = event.message.getUnformattedText();
            }

            gui.printChatMessageWithOptionalDeletion(event.message, ++line);

            if (line > 256)
                line = 0;

            if(event.isCancelable())
                event.setCanceled(true);
        }
    }
}
