package com.hyahm;

import com.hyahm.autogg.AutoGGCommands;
import com.hyahm.autogg.AutoGGEvents;
import com.hyahm.autotip.AutoTipCommands;
import com.hyahm.autotip.AutoTipEvent;
import com.hyahm.utils.FPSCounter;
import com.hyahm.utils.FPSCounter;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
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
    public static final String VERSION = "1.0-dev";
    public static ConfigManager config = new ConfigManager();
    public static final Logger logger = LogManager.getLogger("HYAHM");
    public static TickEventScheduler scheduler = new TickEventScheduler();

    @Mod.Instance
    private static HyahmMain instance;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger.info("----------------HYAHM----------------  ");
        logger.info("Starting preinit, loading configs      ");
        config = new ConfigManager(event.getSuggestedConfigurationFile());
        logger.info("----------------HYAHM----------------  ");
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        logger.info("----------------HYAHM----------------  ");
        logger.info("Starting init, loading commands        ");

        logger.info("Loading module command: autogg");
        ClientCommandHandler.instance.registerCommand(new AutoGGCommands());
        logger.info("Loading module command: autogg: done!");

        logger.info("Loading module command: autotip");
        ClientCommandHandler.instance.registerCommand(new AutoTipCommands());
        logger.info("Loading module command: autotip: done!");

        logger.info("Starting init, loading handlers        ");
        logger.info("Loading module event: autogg");
        MinecraftForge.EVENT_BUS.register(new AutoGGEvents());
        logger.info("Loading module command: autogg: done!");

        logger.info("Loading module event: autotip");
        MinecraftForge.EVENT_BUS.register(new AutoTipEvent());
        logger.info("Loading module event: autotip: done!");

        logger.info("Loading module event: basic game overlay");
        MinecraftForge.EVENT_BUS.register(new FPSCounter());
        logger.info("Loading module event: basic game overlay: done!");

        logger.info("----------------HYAHM----------------  ");
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {

    }

    public static HyahmMain getInstance() {
        return instance;
    }
}
