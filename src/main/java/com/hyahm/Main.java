package com.hyahm;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.model.ModelDynBucket;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MODID, version = Main.VERSION, clientSideOnly = true)
public class Main
{
    public static final String MODID = "hyahm";
    public static final String VERSION = "1.0";
    public static Configuration config;
    private static final Logger logger = LogManager.getLogger();
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
}
