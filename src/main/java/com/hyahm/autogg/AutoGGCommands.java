package com.hyahm.autogg;

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
        if(args.length == 0)
            sender.addChatMessage(new ChatComponentText("[" +
                (HyahmMain.getInstance().isEnabled ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) +
                HyahmMain.getInstance().isEnabled + EnumChatFormatting.RESET +
                "]: tick delay (" +
                HyahmMain.getInstance().delay +
                ")"
            ));

        if(args.length != 1)
            return;

        Integer i = Integer.getInteger(args[0]);
        if(i == null) {
            if(args[0] == "enable")
                HyahmMain.getInstance().isEnabled = true;
            else if(args[0] == "disable")
                HyahmMain.getInstance().isEnabled = false;
            else
                return;
        }

        HyahmMain.getInstance().delay = i;
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