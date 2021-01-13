package com.hyahm;

import com.google.gson.*;

import java.io.*;

public class ConfigManager {
    private File config;

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

    public AutoGGConfig autoGGConfig;
    public AutoTipConfig autoTipConfig;
    public BedwarsConfig bedwarsConfig;

    public ConfigManager() {
        this.autoGGConfig = new AutoGGConfig();
        this.autoTipConfig = new AutoTipConfig();
        this.bedwarsConfig = new BedwarsConfig();
    }

    public ConfigManager(File config) {
        this();
        Gson gson = new Gson();

        this.config = config;
        if(config.exists()) {
            try {
                FileReader reader = new FileReader(this.config);
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder builder = new StringBuilder();

                String currentLine;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    builder.append(currentLine);
                }
                String complete = builder.toString();

                JsonObject cfg = new JsonParser().parse(complete).getAsJsonObject();

                cfg = cfg.getAsJsonObject("modules");

                this.autoGGConfig = gson.fromJson(cfg.getAsJsonObject("autogg"), AutoGGConfig.class);
                this.autoTipConfig = gson.fromJson(cfg.getAsJsonObject("autotip"), AutoTipConfig.class);

            }
            catch (Exception e){
                this.sync();
            }
        }
    }

    public void sync() {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new Gson();
        try {
            if(config == null) {
                HyahmMain.logger.info("Unexpected null config");
                return;
            }

            config.createNewFile();

            JsonObject root = new JsonObject();

            JsonObject modules = new JsonObject();
            JsonElement autoGGElem = gson.toJsonTree(autoGGConfig);
            JsonElement autoTipElem = gson.toJsonTree(autoTipConfig);

            modules.add("autogg", autoGGElem);
            modules.add("autotip", autoTipElem);

            root.add("modules", modules);

            FileWriter f = new FileWriter(config);
            f.write(gsonBuilder.toJson(root));
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
