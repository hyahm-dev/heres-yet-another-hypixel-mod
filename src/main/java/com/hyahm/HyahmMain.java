package com.hyahm;

import com.hyahm.modules.ModuleCommandHandler;
import com.hyahm.modules.ModuleEventHandler;
import com.hyahm.modules.autogg.AutoGGCommands;
import com.hyahm.modules.autogg.AutoGGEvents;
import com.hyahm.modules.autoplay.AutoPlayEvents;
import com.hyahm.modules.autotip.AutoTipCommands;
import com.hyahm.modules.autotip.AutoTipEvent;
import com.hyahm.hooks.HookHandler;
import com.hyahm.modules.stats.NickChecker;
import com.hyahm.modules.utils.Keystrokes;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mod(modid = HyahmMain.MODID, version = HyahmMain.VERSION, clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public class HyahmMain
{
    public static final String MODID = "hyahm";
    public static final String VERSION = "1.0.1-dev";
    public static ConfigManager config = new ConfigManager();
    public static final Logger logger = LogManager.getLogger("HYAHM");
    public static final TickEventScheduler scheduler = new TickEventScheduler();
    public static final HookHandler HOOK_HANDLER = new HookHandler();

    @Mod.Instance
    public static HyahmMain instance;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger.info("----------------HYAHM----------------  ");
        logger.info("Starting preinit, loading configs      ");

        config = new ConfigManager(event.getSuggestedConfigurationFile());

        logger.info("loading handlers...");

        logger.info("running mod core init");
        MinecraftForge.EVENT_BUS.register(scheduler);
        MinecraftForge.EVENT_BUS.register(HOOK_HANDLER);
        logger.info("mod core init done!");

        for (ASMDataTable.ASMData asmData : event.getAsmData().getAll(ModuleEventHandler.class.getCanonicalName())) {
            try {
                Class asmClass = Class.forName(asmData.getClassName());
                String name = asmData.getAnnotationInfo().get("name").toString();

                logger.info("Loading module event handler: " + name);
                Object instance = asmClass.newInstance();
                MinecraftForge.EVENT_BUS.register(instance);
                HOOK_HANDLER.hook(instance);
                logger.info("Loading module event handler: " + name + ": done!");
            } catch (Exception e) {
                logger.error("Error loading event handler: ", e);
            }
        }

        for (ASMDataTable.ASMData asmData : event.getAsmData().getAll(ModuleCommandHandler.class.getCanonicalName())) {
            try {
                Class<? extends ICommand> asmClass = Class.forName(asmData.getClassName()).asSubclass(ICommand.class);
                String name = asmData.getAnnotationInfo().get("name").toString();

                logger.info("Loading module command: " + name);
                ICommand instance = asmClass.newInstance();
                ClientCommandHandler.instance.registerCommand(instance);
                logger.info("Loading module command: " + name + ": done!");
            } catch (Exception e) {
                logger.error("Error loading command: ", e);
            }
        }

        logger.info("----------------HYAHM----------------  ");
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        logger.info("----------------HYAHM----------------  ");

        logger.info("----------------HYAHM----------------  ");
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {


    }
}
