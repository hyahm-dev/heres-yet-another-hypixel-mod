package com.hyahm;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class BasicCommands {
    public static class SetToken implements ICommand {
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

    public static class HyahmCommand implements ICommand {
        private Map<String, IModuleCommand> moduleCommands = new HashMap<>();

        public HyahmCommand() {

        }

        @Override
        public String getCommandName() {
            return "hyahm";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return  "Usage: /hyahm [module] [module usage]\n"+
                    "Use /hyahm help [module] to get help messages";
        }

        @Override
        public List<String> getCommandAliases() {
            return Collections.emptyList();
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) throws CommandException {
            if(args.length < 1)
                sender.addChatMessage(new ChatComponentText("Invalid Usage! Use /hyahm help!"));

            String name = args[0];
            String[] modArgs = Arrays.copyOfRange(args, 1, args.length);

            moduleCommands.get(name).exec(sender, modArgs);
        }

        @Override
        public boolean canCommandSenderUseCommand(ICommandSender sender) {
            return true;
        }

        @Override
        public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
            if(args.length == 0) {
                return new ArrayList<>(moduleCommands.keySet());
            }
            else
                return null;
        }

        @Override
        public boolean isUsernameIndex(String[] args, int index) {
            if(args.length == 0)
                return false;
            String name = args[0];
            String[] modArgs = Arrays.copyOfRange(args, 1, args.length);

            return moduleCommands.get(name).isUsernameIndex(modArgs, index - 1);
        }

        public void register(String str, IModuleCommand cmd) { moduleCommands.put(str, cmd); }

        @Override
        public int compareTo(@NotNull ICommand o) {
            return 0;
        }
    }
}
