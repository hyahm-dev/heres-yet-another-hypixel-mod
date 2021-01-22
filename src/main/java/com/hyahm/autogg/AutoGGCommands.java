package com.hyahm.autogg;

import com.hyahm.ConfigManager;
import com.hyahm.Constants;
import com.hyahm.HyahmMain;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Arrays;
import java.util.List;

public class AutoGGCommands implements ICommand {
    @Override
    public String getCommandName() {
        return "autogg";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return  "Usage: /autogg [delay] or /autogg [enable/disable]. /autogg gets current info";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("gg", "agg");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(args.length == 0) {
            sender.addChatMessage(new ChatComponentText("[" +
                Constants.getEnabledString(ConfigManager.autoGGConfig.isEnabled) +
                "]: tick delay (" +
                ConfigManager.autoGGConfig.delay +
                ")"
            ));
            return;
        }

        if(args.length != 1)
            return;
        try {
            Integer i = Integer.parseInt(args[0]);
            ConfigManager.autoGGConfig.delay = i;
            ConfigManager.sync();
            sender.addChatMessage(new ChatComponentText("Delay set to: " + ConfigManager.autoGGConfig.delay + " ticks"));
        }
        catch(NumberFormatException e) {
            if (args[0].equals("enable")) {
                sender.addChatMessage(new ChatComponentText("Autogg is " + Constants.getEnabledString(true)));
                ConfigManager.autoGGConfig.isEnabled = true;
            } else if (args[0].equals("disable")) {
                sender.addChatMessage(new ChatComponentText("Autogg is " + Constants.getEnabledString(false)));
                ConfigManager.autoGGConfig.isEnabled = false;
            } else
                sender.addChatMessage(new ChatComponentText("Invalid Command!"));
            ConfigManager.sync();
            return;
        }
    }

    // basic commands stuff
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