package com.hyahm.autotip;

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
        return Arrays.asList("atip");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(args.length == 0)
            sender.addChatMessage(new ChatComponentText("[" +
                    (HyahmMain.getInstance().config.autoTipConfig.isEnabled ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) +
                    HyahmMain.getInstance().config.autoTipConfig.isEnabled + EnumChatFormatting.RESET +
                    "]: delay (" +
                    HyahmMain.getInstance().config.autoTipConfig.delay / 20 +
                    ")"
            ));

        if(args.length != 1)
            return;

        Integer i = Integer.getInteger(args[0]);
        if(i == null) {
            if(args[0] == "enable")
                HyahmMain.getInstance().config.autoTipConfig.isEnabled = true;
            else if(args[0] == "disable")
                HyahmMain.getInstance().config.autoTipConfig.isEnabled = false;
            else
                return;
        }

        HyahmMain.getInstance().config.autoTipConfig.delay = i * 20;
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