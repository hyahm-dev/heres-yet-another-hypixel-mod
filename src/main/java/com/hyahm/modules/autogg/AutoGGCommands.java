package com.hyahm.modules.autogg;
import com.hyahm.Constants;
import com.hyahm.HyahmMain;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.List;

public class AutoGGCommands implements ICommand {
    @Override
    public String getCommandName() { // returns the name of the command to use
        return "autogg";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return  "Usage: /autogg [delay] or /autogg [enable/disable]. /autogg gets current info";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("gg", "agg"); // returns alternative commands
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        HyahmMain.scheduler.enqueueEvent(200, () -> {
            HyahmMain.logger.info("test");
        });
        // check if it has 0 args
        if(args.length == 0) {
            // display current config
            sender.addChatMessage(new ChatComponentText("[" +
                Constants.getEnabledString(HyahmMain.config.autoGGConfig.isEnabled) +
                "]: tick delay (" +
                HyahmMain.config.autoGGConfig.delay +
                ")"
            ));
            return;
        }

        // if it doesnt have 1 arg, then it is invalid
        if(args.length != 1)
            return;

        // attempts to convert the arg to a int, if not, check if it is "enable" or "disable"
        try {
            HyahmMain.config.autoGGConfig.delay = Integer.parseInt(args[0]);
            HyahmMain.config.sync();
            sender.addChatMessage(new ChatComponentText("Delay set to: " + HyahmMain.config.autoGGConfig.delay + " ticks"));
        }
        catch(NumberFormatException e) {
            if (args[0].equals("enable")) {
                sender.addChatMessage(new ChatComponentText("Autogg is " + Constants.getEnabledString(true)));
                HyahmMain.config.autoGGConfig.isEnabled = true;
            } else if (args[0].equals("disable")) {
                sender.addChatMessage(new ChatComponentText("Autogg is " + Constants.getEnabledString(false)));
                HyahmMain.config.autoGGConfig.isEnabled = false;
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