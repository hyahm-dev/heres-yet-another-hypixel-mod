package com.hyahm;

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

    public AutoGGConfig autoGGConfig;
    public AutoTipConfig autoTipConfig;

    public ConfigManager() {
        this.autoGGConfig = new AutoGGConfig();
        this.autoTipConfig = new AutoTipConfig();
    }

    public ConfigManager(Configuration config) {

    }
}
