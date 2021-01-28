package com.hyahm.utils;

/*import com.hyahm.ConfigManager;
import com.hyahm.gui.ScalableTextRect;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.hyahm.gui.windows.MCWindow;


public class FPSCounter {
    private ScalableTextRect<MCWindow> fps;
    private int tick = 0;

    public FPSCounter() {
        fps = new ScalableTextRect<MCWindow>(.1, .1, .1, .1, 0xFFFFFFFF, new MCWindow());
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onTick(TickEvent.ClientTickEvent event) {
        if(event.isCanceled())
            return;

        if(tick == 0)
            fps.setText(Integer.toString(Minecraft.getDebugFPS()));
        tick++;
        tick = tick % 30;

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPostRender(RenderGameOverlayEvent.Text event) {
        if(ConfigManager.fpsConfig.isEnabled)
            fps.render();
    }
}
*/