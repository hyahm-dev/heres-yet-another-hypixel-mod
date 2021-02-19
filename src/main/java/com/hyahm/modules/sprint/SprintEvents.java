package com.hyahm.modules.sprint;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class SprintEvents
{
    KeyBinding binding = new KeyBinding("key.sprinttoggle", Keyboard.KEY_C, "key.hyahm.name");
    boolean sprintStatus = false;
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(binding.isPressed()) {
            sprintStatus = !sprintStatus;
        }

        Minecraft.getMinecraft().thePlayer.setSprinting(sprintStatus);
    }
}