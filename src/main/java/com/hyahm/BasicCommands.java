package com.hyahm;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.List;

public class BasicCommands {
    public class SetToken implements ICommand {
        @Override
        public String getCommandName() { return "settok"; }

        @Override
        public String getCommandUsage(ICommandSender sender) { return "Usage: /settoken [hypixel api token]"; }

        @Override
        public List<String> getCommandAliases() {
            return null;
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) throws CommandException {
            if(args.length != 1)
                return;



        }

        @Override
        public boolean canCommandSenderUseCommand(ICommandSender sender) {
            return true;
        }

        @Override
        public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
            return null;
        }

        @Override
        public boolean isUsernameIndex(String[] args, int index) {
            return false;
        }

        @Override
        public int compareTo(ICommand o) {
            return 0;
        }
    }
}
