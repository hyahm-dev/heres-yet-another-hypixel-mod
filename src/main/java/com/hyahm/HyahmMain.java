package com.hyahm;

import com.hyahm.autogg.AutoGGCommands;
import com.hyahm.autogg.AutoGGEvents;
import com.hyahm.autotip.AutoTipCommands;
import com.hyahm.autotip.AutoTipEvent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = HyahmMain.MODID, version = HyahmMain.VERSION, clientSideOnly = true)
public class HyahmMain
{
    public static final String MODID = "hyahm";
    public static final String VERSION = "1.0";
    public ConfigManager config;
    public static final Logger logger = LogManager.getLogger();

    @Mod.Instance
    private static HyahmMain instance;

    public HyahmMain() {
        config = new ConfigManager();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.info("----------------HYAHM----------------  ");
        logger.info("Starting preinit, loading configs      ");
        config = new ConfigManager(new Configuration(event.getSuggestedConfigurationFile()));

        logger.info("----------------HYAHM----------------  ");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("----------------HYAHM----------------  ");
        logger.info("Starting init, loading commands        ");
        ClientCommandHandler.instance.registerCommand(new AutoGGCommands());
        ClientCommandHandler.instance.registerCommand(new AutoTipCommands());

        logger.info("Starting init, loading handlers        ");
        MinecraftForge.EVENT_BUS.register(new AutoGGEvents());
        MinecraftForge.EVENT_BUS.register(new AutoTipEvent());
        config = new ConfigManager();
        logger.info("----------------HYAHM----------------  ");
    }

    @Mod.EventHandler
    public void  postInit(FMLPostInitializationEvent event) {

    }

    public static HyahmMain getInstance() {
        return instance;
    }
}
