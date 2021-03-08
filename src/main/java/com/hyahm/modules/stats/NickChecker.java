package com.hyahm.modules.stats;

import com.hyahm.IModuleCommand;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import static com.hyahm.utils.Utils.matchNick;

public class NickChecker implements IModuleCommand {
    @Override
    public String usage(ICommandSender sender) {
        return "usage: /checknick [name]...";
    }

    @Override
    public void exec(ICommandSender sender, String[] args) throws CommandException {
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
}
