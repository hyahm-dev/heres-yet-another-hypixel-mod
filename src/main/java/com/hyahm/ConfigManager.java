package com.hyahm;

import net.minecraftforge.client.model.IModelCustomData;
import net.minecraftforge.common.config.Configuration;

public class ConfigManager {
    public class AutoGGConfig {
        public int delay = 10;
        public boolean isEnabled = true;
    }

    public class AutoTipConfig {
        public int delay = 18000;
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

    public ConfigManager(Configuration config) {

    }
}
