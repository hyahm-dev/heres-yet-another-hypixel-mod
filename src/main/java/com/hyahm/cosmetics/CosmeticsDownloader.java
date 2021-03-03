package com.hyahm.cosmetics;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hyahm.HyahmMain;
import com.hyahm.utils.Utils;
import net.minecraft.client.Minecraft;
import org.apache.commons.collections4.map.LRUMap;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;

public class CosmeticsDownloader {
    public static final String CAPE = "cape";

    private LRUMap<String, ICosmetic> cache = new LRUMap<>(1000);

    public void load() {
        File f = Paths.get(Minecraft.getMinecraft().mcDataDir.getAbsolutePath(), "hyahm", "cosmetics", "index.json").toFile();
        f.mkdirs();
        try {
            boolean res = f.createNewFile();
            if(res) {
                URL url = new URL("https://url");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("accept", "application/json");
                InputStream stream = connection.getInputStream();
                String meta = Utils.readAll(stream);
                JsonObject data = HyahmMain.gson.fromJson(meta, JsonObject.class);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ICosmetic[] get(String uuid) {
        // check if cached

        return null;
    }
}
