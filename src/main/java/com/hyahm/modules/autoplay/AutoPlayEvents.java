package com.hyahm.modules.autoplay;

import com.hyahm.HyahmMain;
import com.hyahm.hooks.GameEndEvent;
import com.hyahm.hooks.Hook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;

public class AutoPlayEvents {
    @Hook(level = 900)
    public void onGameEnd(GameEndEvent gameEndEvent) {
        HyahmMain.scheduler.enqueueEvent(10, () -> {
            for(int i = 27; i < Minecraft.getMinecraft().thePlayer.inventory.getSizeInventory(); i++) {
                if(Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(i).getItem() == Items.paper) {
                    Minecraft.getMinecraft().thePlayer.inventory.currentItem = i - 27;
                    KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode());
                }
            }
        });
    }
}
