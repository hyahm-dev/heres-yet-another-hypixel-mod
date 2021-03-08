package com.hyahm;

import com.google.gson.*;
import com.hyahm.utils.Utils;

import java.io.*;
import java.time.Duration;
import java.time.Instant;

public class ConfigManager {
    private File configFile;

    public static class AutoGGConfig {
        public int delay = 10;
        public boolean isEnabled = true;
    }

    public static class AutoTipConfig {
        public int delay = 6000;
        public boolean isEnabled = true;
    }

    public static class BedwarsConfig {
        public boolean isLevelsEnabled = true;
    }

    public static class FPSConfig {
        public boolean isEnabled = true;
        public double scalePosX = .1;
        public double scalePosY = .1;
        public double scaleWidth = .1;
        public double scaleHeight = .1;
    }

    public static class CPSConfig {
        public boolean isEnabledRC = true;
        public double scalePosXRC = .1;
        public double scalePosYRC = .1;
        public double scaleWidthRC = .1;
        public double scaleHeightRC = .1;

        public boolean isEnabledLC = true;
        public double scalePosXLC = .1;
        public double scalePosYLC = .1;
        public double scaleWidthLC = .1;
        public double scaleHeightLC = .1;

        public long setRemoveTickDelay = 20;
    }

    public AutoGGConfig autoGGConfig;
    public AutoTipConfig autoTipConfig;
    public BedwarsConfig bedwarsConfig;
    public FPSConfig fpsConfig;
    public CPSConfig cpsConfig;
    public String token;

    public ConfigManager() {
        autoGGConfig = new AutoGGConfig();
        autoTipConfig = new AutoTipConfig();
        bedwarsConfig = new BedwarsConfig();
        fpsConfig = new FPSConfig();
        cpsConfig = new CPSConfig();
        token = "";
    }

    public ConfigManager(File config) {
        this();

        this.configFile = config;

        if(config.exists()) {
            try {
                HyahmMain.logger.info("Loading config with json: ");
                HyahmMain.logger.info("Reading and parsing config file at: " + config.getAbsolutePath());
                Instant start = Instant.now();
                FileInputStream reader = new FileInputStream(configFile);
                String complete = Utils.readAll(reader);

                JsonObject cfg = new JsonParser().parse(complete).getAsJsonObject();

                JsonObject modules = cfg.getAsJsonObject("modules");
                Instant end = Instant.now();
                HyahmMain.logger.debug("Time to load config file: "+ Duration.between(start, end));
                HyahmMain.logger.info("Loading data");
                if(modules.has("autogg"))
                    autoGGConfig = HyahmMain.gson.fromJson(modules.getAsJsonObject("autogg"), AutoGGConfig.class);
                if(modules.has("autotip"))
                    autoTipConfig = HyahmMain.gson.fromJson(modules.getAsJsonObject("autotip"), AutoTipConfig.class);
                if(modules.has("fps"))
                    fpsConfig = HyahmMain.gson.fromJson(modules.getAsJsonObject("fps"), FPSConfig.class);
                if(modules.has("keystrokes"))
                    cpsConfig = HyahmMain.gson.fromJson(modules.getAsJsonObject("keystrokes"), CPSConfig.class);

                token = cfg.getAsJsonPrimitive("token").getAsString();
            }
            catch (Exception e){
                HyahmMain.logger.error("Error occurred while loading config! Calling sync function to write defaults");
            }
            sync();
        }
    }

    public void sync() {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new Gson();
        try {
            HyahmMain.logger.info("Syncing configs...");
            configFile.createNewFile();

            JsonObject root = new JsonObject();

            JsonObject modules = new JsonObject();
            JsonElement autoGGElem = gson.toJsonTree(autoGGConfig);
            JsonElement autoTipElem = gson.toJsonTree(autoTipConfig);
            JsonElement fpsConfigElem = gson.toJsonTree(fpsConfig);
            JsonElement keystrokesConfigElem = gson.toJsonTree(cpsConfig);

            modules.add("autogg", autoGGElem);
            modules.add("autotip", autoTipElem);
            modules.add("fps", fpsConfigElem);
            modules.add("keystrokes", keystrokesConfigElem);

            root.add("modules", modules);
            root.add("token", new JsonPrimitive(token));

            FileWriter f = new FileWriter(configFile);
            f.write(gsonBuilder.toJson(root));
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
