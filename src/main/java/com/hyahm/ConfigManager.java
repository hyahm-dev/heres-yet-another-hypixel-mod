package com.hyahm;

import com.google.gson.*;
import java.io.*;
import java.time.Duration;
import java.time.Instant;

public class ConfigManager {
    private static File configFile;

    public class AutoGGConfig {
        public int delay = 10;
        public boolean isEnabled = true;
    }

    public class AutoTipConfig {
        public int delay = 6000;
        public boolean isEnabled = true;
    }

    public class BedwarsConfig {
        public boolean isLevelsEnabled = true;
    }

    public class FPSConfig {
        public boolean isEnabled = true;
        public double scalePosX = .1;
        public double scalePosY = .1;
        public double scaleWidth = .1;
        public double scaleHeight = .1;
    }

    public static AutoGGConfig autoGGConfig;
    public static AutoTipConfig autoTipConfig;
    public static BedwarsConfig bedwarsConfig;
    public static FPSConfig fpsConfig;
    public static String token;

    public ConfigManager() {
        this.autoGGConfig = new AutoGGConfig();
        this.autoTipConfig = new AutoTipConfig();
        this.bedwarsConfig = new BedwarsConfig();
        this.fpsConfig = new FPSConfig();
        this.token = "";
    }

    public ConfigManager(File config) {
        this();
        Gson gson = new Gson();

        this.configFile = config;

        if(config.exists()) {
            try {
                HyahmMain.logger.info("Loading config with json: ");
                HyahmMain.logger.info("Reading and parsing config file at: " + config.getAbsolutePath());
                Instant start = Instant.now();
                FileReader reader = new FileReader(this.configFile);
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder builder = new StringBuilder();

                String currentLine;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    builder.append(currentLine);
                }
                String complete = builder.toString();

                JsonObject cfg = new JsonParser().parse(complete).getAsJsonObject();

                JsonObject modules = cfg.getAsJsonObject("modules");
                Instant end = Instant.now();
                HyahmMain.logger.debug("Time to load config file: "+ Duration.between(start, end));
                HyahmMain.logger.info("Loading data");
                this.autoGGConfig = gson.fromJson(modules.getAsJsonObject("autogg"), AutoGGConfig.class);
                this.autoTipConfig = gson.fromJson(modules.getAsJsonObject("autotip"), AutoTipConfig.class);
                this.fpsConfig = gson.fromJson(modules.getAsJsonObject("fps"), FPSConfig.class);
                this.token = cfg.getAsJsonPrimitive("token").getAsString();
            }
            catch (Exception e){
                HyahmMain.logger.error("Error occurred while loading config! Calling sync function to write defaults");
                this.sync();
            }
        }
    }

    public static void sync() {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new Gson();
        try {
            configFile.createNewFile();

            JsonObject root = new JsonObject();

            JsonObject modules = new JsonObject();
            JsonElement autoGGElem = gson.toJsonTree(autoGGConfig);
            JsonElement autoTipElem = gson.toJsonTree(autoTipConfig);
            JsonElement fpsConfigElem = gson.toJsonTree(fpsConfig);

            modules.add("autogg", autoGGElem);
            modules.add("autotip", autoTipElem);
            modules.add("fps", fpsConfigElem);

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
