package com.hyahm.modules.autotip;

import com.hyahm.HyahmMain;
import com.hyahm.Constants;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

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
                    Constants.getEnabledString(HyahmMain.config.autoGGConfig.isEnabled) +
                    "]: seconds delay (" +
                    HyahmMain.config.autoTipConfig.delay / 20 +
                    ")"
            ));
        }

        if(args.length != 1)
            return;
        try {
            int i = Integer.parseInt(args[0]);
            HyahmMain.config.autoTipConfig.delay = i * 20;
            HyahmMain.config.sync();
            sender.addChatMessage(new ChatComponentText("Delay set to: " + HyahmMain.config.autoTipConfig.delay / 20 + " seconds"));
        }
        catch (NumberFormatException e) {
            if (args[0].equals("enable")) {
                sender.addChatMessage(new ChatComponentText("Autotip is " + Constants.getEnabledString(true)));
                HyahmMain.config.autoTipConfig.isEnabled = true;
            } else if (args[0].equals("disable")) {
                sender.addChatMessage(new ChatComponentText("Autotip is " + Constants.getEnabledString(false)));
                HyahmMain.config.autoTipConfig.isEnabled = false;
            } else
                sender.addChatMessage(new ChatComponentText("Invalid Command!"));

            HyahmMain.config.sync();
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