package com.hyahm.autogg;

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
                Constants.getEnabledString(HyahmMain.config.autoGGConfig.isEnabled) +
                "]: tick delay (" +
                HyahmMain.config.autoGGConfig.delay +
                ")"
            ));
            return;
        }

        if(args.length != 1)
            return;

        Integer i = Integer.parseInt(args[0]);
        if(i == null) {
            if(args[0].equals("enable")) {
                sender.addChatMessage(new ChatComponentText("Autogg is " + Constants.getEnabledString(true)));
                HyahmMain.config.autoGGConfig.isEnabled = true;
            }
            else if(args[0].equals("disable")) {
                sender.addChatMessage(new ChatComponentText("Autogg is " + Constants.getEnabledString(false)));
                HyahmMain.config.autoGGConfig.isEnabled = false;
            }
            else
                sender.addChatMessage(new ChatComponentText("Invalid Command!"));
            HyahmMain.config.sync();
            return;
        }

        HyahmMain.config.autoGGConfig.delay = i;
        HyahmMain.config.sync();
        return;
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