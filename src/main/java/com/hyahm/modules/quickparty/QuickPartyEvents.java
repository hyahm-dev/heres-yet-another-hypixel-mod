package com.hyahm.modules.quickparty;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.collection.parallel.ParIterableLike;

public class QuickPartyEvents {
    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent event) {
        if(Minecraft.getMinecraft().thePlayer.isSneaking()) {
            MovingObjectPosition pos = Minecraft.getMinecraft().objectMouseOver;
            if(pos.entityHit instanceof EntityPlayer) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/p " + pos.entityHit.getName());
            }
        }
    }
}
