package com.hyahm.modules.gameselector;

import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import com.hyahm.HyahmMain;
import com.hyahm.modules.ModuleEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@ModuleEventHandler(name = "game selector")
public class GameSelectorEvents {
    private boolean a = false;

    public GameSelectorEvents() {
        ResourceLocation location = new ResourceLocation(HyahmMain.MODID, "meta/gameselector/gamemappings.json");
        try(InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(location).getInputStream()) {
            JsonReader reader = new JsonReader(new InputStreamReader(stream));
            reader.beginArray();
            JsonElement obj = HyahmMain.gson.fromJson(reader, JsonElement.class);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onOverlay(RenderGameOverlayEvent.Text event) {

    }
}
