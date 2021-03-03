package com.hyahm.modules.compactchat;

import com.hyahm.IModuleCommand;
import com.hyahm.modules.ModuleCommandHandler;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

@ModuleCommandHandler(name = "compact chat", cmd = "cchat")
public class CompactChatCommands implements IModuleCommand {
    @Override
    public String usage(ICommandSender sender) {
        return "Usage: cchat [enable/disable]";
    }

    @Override
    public void exec(ICommandSender sender, String[] args) throws CommandException {

    }
}
