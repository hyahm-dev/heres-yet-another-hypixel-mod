package com.hyahm.modules.autotip;

import com.hyahm.HyahmMain;
import com.hyahm.IModuleCommand;
import com.hyahm.modules.ModuleCommandHandler;
import com.hyahm.utils.Utils;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

@ModuleCommandHandler(name = "autotip", cmd = "autotip")
public class AutoTipCommands implements IModuleCommand {
    @Override
    public String usage(ICommandSender sender) {
        return  "Usage: autotip [delay] or /autotip [enable/disable]. /autotip gets current info";
    }

    @Override
    public void exec(ICommandSender sender, String[] args) throws CommandException {
        if(args.length == 0) {
            sender.addChatMessage(new ChatComponentText("[" +
                    Utils.getEnabledString(HyahmMain.config.autoGGConfig.isEnabled) +
                    "]: seconds delay (" +
                    HyahmMain.config.autoTipConfig.delay / 20 +
                    ")"
            ));
        }

        if(args.length != 1)
            return;
        try {
            int i = Integer.parseInt(args[0]);
            HyahmMain.config.autoTipConfig.delay = i * 20;
            HyahmMain.config.sync();
            sender.addChatMessage(new ChatComponentText("Delay set to: " + HyahmMain.config.autoTipConfig.delay / 20 + " seconds"));
        }
        catch (NumberFormatException e) {
            if (args[0].equals("enable")) {
                sender.addChatMessage(new ChatComponentText("Autotip is " + Utils.getEnabledString(true)));
                HyahmMain.config.autoTipConfig.isEnabled = true;
            } else if (args[0].equals("disable")) {
                sender.addChatMessage(new ChatComponentText("Autotip is " + Utils.getEnabledString(false)));
                HyahmMain.config.autoTipConfig.isEnabled = false;
            } else
                sender.addChatMessage(new ChatComponentText("Invalid Command!"));

            HyahmMain.config.sync();
        }
    }
}