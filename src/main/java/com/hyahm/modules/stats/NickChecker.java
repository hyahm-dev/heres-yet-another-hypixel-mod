package com.hyahm.modules.stats;

import static com.hyahm.utils.Utils.*;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Arrays;
import java.util.List;

public class NickChecker implements ICommand {
    @Override
    public String getCommandName() {
        return "checknick";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "usage: /checknick [name]";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("cnick", "isnick");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        for (String s : args) {
            // check if it matches regex
            int nickConfidence = 0;
            if(matchNick.matcher(s).matches())
                nickConfidence++;
            if(MojangAPI.isPlayerReal(s))
                nickConfidence++;

            switch (nickConfidence) {
                case 0:
                    sender.addChatMessage(new ChatComponentText(s + ": " + EnumChatFormatting.RED + "no"));
                case 1:
                    sender.addChatMessage(new ChatComponentText(s + ": " + EnumChatFormatting.YELLOW + "maybe"));
                case 2:
                    sender.addChatMessage(new ChatComponentText(s + ": " + EnumChatFormatting.GREEN + "yes"));
                default:
                    throw new IllegalStateException("wtf");
            }
        }
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
        return true;
    }

    @Override
    public int compareTo(ICommand iCommand) {
        return 0;
    }
}
