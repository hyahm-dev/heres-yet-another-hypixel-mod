package com.hyahm.modules.autogg;

import static com.hyahm.utils.Utils.*;
import com.hyahm.HyahmMain;
import com.hyahm.IModuleCommand;
import com.hyahm.modules.ModuleCommandHandler;
import com.hyahm.utils.ArgsOption;
import com.hyahm.utils.Utils;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;


@ModuleCommandHandler(name = "autogg", cmd = "autogg")
public class AutoGGCommands implements IModuleCommand {
    static private final ArgsOption<?>[] options = {
        new ArgsOption<Boolean>(
            (sender, b) -> {
                HyahmMain.config.autoGGConfig.isEnabled = b;
                sender.addChatMessage(new ChatComponentText("Autogg is " + Utils.getEnabledString(b)));
                return true;
            },
            enableDisable
        ),
        new ArgsOption<Integer>(
            (sender, i) -> {
                HyahmMain.config.autoGGConfig.delay = i;
                sender.addChatMessage(new ChatComponentText("Delay set to: " + i + " ticks"));
                return true;
            },
            integerParser
        ),
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

        parseArg(sender, args[0], invalidCmd, options);
        HyahmMain.config.sync();
    }
}