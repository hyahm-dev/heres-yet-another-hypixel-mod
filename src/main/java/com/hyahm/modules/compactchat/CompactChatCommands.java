package com.hyahm.modules.compactchat;

import com.hyahm.HyahmMain;
import com.hyahm.IModuleCommand;
import com.hyahm.modules.ModuleCommandHandler;
import com.hyahm.utils.ArgsOption;
import com.hyahm.utils.Utils;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import static com.hyahm.utils.Utils.*;

@ModuleCommandHandler(name = "compact chat", cmd = "cchat")
public class CompactChatCommands implements IModuleCommand {
    private static final ArgsOption<?> argsOption = new ArgsOption<Boolean>(
        (sender, b) -> {
            HyahmMain.config.autoGGConfig.isEnabled = b;
            sender.addChatMessage(new ChatComponentText("Autogg is " + Utils.getEnabledString(b)));
            return true;
        },
        enableDisable
    );

    @Override
    public String usage(ICommandSender sender) {
        return "Usage: cchat [enable/disable]";
    }

    @Override
    public void exec(ICommandSender sender, String[] args) throws CommandException {
        if(args.length == 0) {
            // display current config
            sender.addChatMessage(new ChatComponentText("[" +
                    getEnabledString(HyahmMain.config.autoGGConfig.isEnabled) +
                    "]"
            ));
            return;
        }

        parseArg(sender, args[0], invalidCmd, argsOption);
    }
}
