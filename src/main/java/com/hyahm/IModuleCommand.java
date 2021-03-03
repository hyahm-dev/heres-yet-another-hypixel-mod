package com.hyahm;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

// simplified version of ICommand

public interface IModuleCommand {
    String usage(ICommandSender sender);
    void exec(ICommandSender sender, String[] args) throws CommandException;
    default boolean isUsernameIndex(String[] args, int index) {
        return false;
    }
}
