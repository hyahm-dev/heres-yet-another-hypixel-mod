package com.hyahm.modules.gameselector;


import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.hyahm.HyahmMain;
import com.hyahm.modules.ModuleEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@ModuleEventHandler(name = "game selector")
public class GameSelectorEvents {
    public GameSelectorEvents() {
        ResourceLocation location = new ResourceLocation(HyahmMain.MODID, "meta/gameselector/gamemappings.json");
        try(InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(location).getInputStream()) {
            JsonReader reader = new JsonReader(new InputStreamReader(stream));
            reader.beginObject();
            JsonObject obj = HyahmMain.gson.fromJson(reader, JsonObject.class);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
