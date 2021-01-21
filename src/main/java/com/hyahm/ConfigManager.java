package com.hyahm;

import com.google.gson.*;
import java.io.*;

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

    public static AutoGGConfig autoGGConfig;
    public static AutoTipConfig autoTipConfig;
    public static BedwarsConfig bedwarsConfig;

    public ConfigManager() {
        this.autoGGConfig = new AutoGGConfig();
        this.autoTipConfig = new AutoTipConfig();
        this.bedwarsConfig = new BedwarsConfig();
    }

    public ConfigManager(File config) {
        this();
        Gson gson = new Gson();

        this.configFile = config;

        if(config.exists()) {
            try {
                HyahmMain.logger.debug("Loading config with json");
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

                HyahmMain.logger.info("Loading data");
                this.autoGGConfig = gson.fromJson(modules.getAsJsonObject("autogg"), AutoGGConfig.class);
                this.autoTipConfig = gson.fromJson(modules.getAsJsonObject("autotip"), AutoTipConfig.class);
            }
            catch (Exception e){
                HyahmMain.logger.debug("Error! Calling sync function to write defaults");
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

            modules.add("autogg", autoGGElem);
            modules.add("autotip", autoTipElem);

            root.add("modules", modules);

            FileWriter f = new FileWriter(configFile);
            f.write(gsonBuilder.toJson(root));
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
