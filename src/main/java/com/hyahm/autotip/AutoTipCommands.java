package com.hyahm.autotip;

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

public class AutoTipCommands implements ICommand {

    @Override
    public String getCommandName() {
        return "autotip";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return  "Usage: /autotip [delay] or /autotip [enable/disable]. /autotip gets current info";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("atip", "at");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(args.length == 0) {
            sender.addChatMessage(new ChatComponentText("[" +
                    Constants.getEnabledString(ConfigManager.autoGGConfig.isEnabled) +
                    "]: seconds delay (" +
                    ConfigManager.autoTipConfig.delay / 20 +
                    ")"
            ));
        }

        if(args.length != 1)
            return;
        try {
            int i = Integer.parseInt(args[0]);
            ConfigManager.autoTipConfig.delay = i * 20;
            ConfigManager.sync();
            sender.addChatMessage(new ChatComponentText("Delay set to: " + ConfigManager.autoTipConfig.delay / 20 + " seconds"));
        }
        catch (NumberFormatException e) {
            if (args[0].equals("enable")) {
                sender.addChatMessage(new ChatComponentText("Autotip is " + Constants.getEnabledString(true)));
                ConfigManager.autoTipConfig.isEnabled = true;
            } else if (args[0].equals("disable")) {
                sender.addChatMessage(new ChatComponentText("Autotip is " + Constants.getEnabledString(false)));
                ConfigManager.autoTipConfig.isEnabled = false;
            } else
                sender.addChatMessage(new ChatComponentText("Invalid Command!"));

            ConfigManager.sync();
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