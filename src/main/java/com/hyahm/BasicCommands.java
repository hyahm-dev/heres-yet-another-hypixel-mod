package com.hyahm;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.BlockPos;

import java.util.Arrays;
import java.util.Collections;
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

    public static class Crash implements ICommand {
        @Override
        public String getCommandName() {
            return "crash";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/crash";
        }

        @Override
        public List<String> getCommandAliases() {
            return Collections.emptyList();
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) throws CommandException {
            Minecraft.getMinecraft().crashed(new CrashReport("You asked for it!", new Throwable()));
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
