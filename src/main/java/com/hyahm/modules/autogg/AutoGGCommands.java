package com.hyahm.modules.autogg;
import com.hyahm.IModuleCommand;
import com.hyahm.modules.ModuleCommandHandler;
import com.hyahm.modules.ModuleEventHandler;
import com.hyahm.utils.ArgsOption;
import com.hyahm.utils.Utils;
import com.hyahm.HyahmMain;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.List;


@ModuleCommandHandler(name = "autogg", cmd = "autogg")
public class AutoGGCommands implements IModuleCommand {
    static ArgsOption<?>[] options = {

    };

    @Override
    public String usage(ICommandSender sender) {
        return "Usage: autogg [enable/disable/<ticks>]";
    }

    @Override
    public void exec(ICommandSender sender, String[] args) throws CommandException {
        // check if it has 0 args
        if(args.length == 0) {
            // display current config
            sender.addChatMessage(new ChatComponentText("[" +
                Utils.getEnabledString(HyahmMain.config.autoGGConfig.isEnabled) +
                "]: tick delay (" +
                HyahmMain.config.autoGGConfig.delay +
                ")"
            ));
            return;
        }

        // if it doesnt have 1 arg, then it is invalid
        if(args.length != 1)
            return;

        // attempts to convert the arg to a int, if not, check if it is "enable" or "disable"
        try {
            HyahmMain.config.autoGGConfig.delay = Integer.parseInt(args[0]);
            HyahmMain.config.sync();
            sender.addChatMessage(new ChatComponentText("Delay set to: " + HyahmMain.config.autoGGConfig.delay + " ticks"));
        }
        catch(NumberFormatException e) {
            if (args[0].equals("enable")) {
                sender.addChatMessage(new ChatComponentText("Autogg is " + Utils.getEnabledString(true)));
                HyahmMain.config.autoGGConfig.isEnabled = true;
            } else if (args[0].equals("disable")) {
                sender.addChatMessage(new ChatComponentText("Autogg is " + Utils.getEnabledString(false)));
                HyahmMain.config.autoGGConfig.isEnabled = false;
            } else
                sender.addChatMessage(new ChatComponentText("Invalid Command!"));
            HyahmMain.config.sync();
        }
    }
}