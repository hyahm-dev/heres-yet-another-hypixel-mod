package com.hyahm;

import com.hyahm.autogg.AutoGGEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
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
    public static Configuration config;


    public static final Logger logger = LogManager.getLogger();

    @Mod.Instance
    private static HyahmMain instance;

    private static boolean isServer;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.info("----------------HYAHM----------------  ");
        logger.info("Starting preinit, loading configs      ");
        config = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
        logger.info("----------------HYAHM----------------  ");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new AutoGGEvents());
    }

    @Mod.EventHandler
    public void  postInit(FMLPostInitializationEvent event) {

    }

    public static void syncConfig() { // Gets called from preInit
        try {
            // Load config
            config.load();

            // Read props from config
            Property p = config.get(Configuration.CATEGORY_GENERAL,
                    "autogg", // Property name
                    "true");
        } catch (Exception e) {
            // Failed reading/writing, just continue
        } finally {
            // Save props to config IF config changed
            if (config.hasChanged())
                config.save();
        }
    }

    public boolean isServer() {
        return this.isServer;
    }

    public static HyahmMain getInstance() {
        return instance;
    }
}
