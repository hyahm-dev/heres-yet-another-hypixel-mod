package com.hyahm.utils;

import com.hyahm.utils.function.ArgumentStringConsumer;
import com.hyahm.utils.function.Fallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.lwjgl.opengl.GL11;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Utils {
    public static ArgumentStringConsumer<Boolean> enableDisable = (str) -> {
        if(str.equals("enable"))
            return true;
        else if(str.equals("disable"))
            return false;

        throw new IllegalArgumentException();
    };

    public static ArgumentStringConsumer<Integer> integerParser = Integer::parseInt;

    public static Fallback invalidCmd = (sender) -> sender.addChatMessage(new ChatComponentText("Invalid command!"));

    public static List<String> AutoGGMatch = Arrays.asList(
        "1st Killer - ",
        "1st Place - ",
        "Winner: ",
        " - Damage Dealt - ",
        "Winning Team - ",
        "1st - ",
        "Winners: ",
        "Winner: ",
        "Winning Team: ",
        " won the game!",
        "Top Seeker: ",
        "1st Place: ",
        "Last team standing!",
        "Winner #1 (",
        "Top Survivors",
        "Winners - "
    );

    public static Pattern[] MatchNormal = {
        Pattern.compile("(?<rk>\\.get(.+) )?(?<pl>\\S{1,16}): (?<msg>.*)"),
        Pattern.compile("\\.get(TEAM) (?<rk>\\.get(.+) )?(?<pl>\\S{1,16}): (?<msg>.*)"),
        Pattern.compile("Guild > (?<rk>\\.get(.+) )?(?<pl>\\S{1,16}): (?<msg>.*)"),
        Pattern.compile("Party > (?<rk>\\.get(.+) )?(?<pl>\\S{1,16}): (?<msg>.*)"),
        Pattern.compile("\\.get(SHOUT) (?<rk>\\.get(.+) )?(?<pl>\\S{1,16}): (?<msg>.*)"),
        Pattern.compile("\\.get(SPECTATOR) (?<rk>\\.get(.+) )?(?<pl>\\S{1,16}): (?<msg>.*)"),
    };

    public static Pattern matchNick = Pattern.compile("(^[^_]+_[^_]+_[^_]+$)");

    public static Pattern removeColor = Pattern.compile("(?i)" + '\u00A7' + "[0-9A-FK-OR]");

    public static boolean isHypixel() {
        if(Minecraft.getMinecraft().getCurrentServerData() == null)
            return false;
        if(!Minecraft.getMinecraft().getCurrentServerData().serverIP.endsWith("hypixel.net"))
            return false;
        return true;
    }

    public static String getEnabledString(boolean isEnabled) {
        return "" + (isEnabled ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) +
                EnumChatFormatting.BOLD +
                (isEnabled ? "Enabled" : "Disabled") +
                EnumChatFormatting.RESET;
    }

    public static String readAll(InputStream stream) throws IOException {
        try (InputStreamReader istream = new InputStreamReader(stream)) {
            BufferedReader bufferedReader = new BufferedReader(istream);
            StringBuilder builder = new StringBuilder();

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null)
                builder.append(currentLine);

            return builder.toString();
        }
    }

    public static int parseArg(ICommandSender sender, String str,  Fallback fallback, ArgsOption<?>... argsOptions) {
        for(ArgsOption<?> opt: argsOptions) {
            try {
                return opt.tryExec(sender, str) ? 1 : -1;
            }
            catch (Exception ignored) {

            }
        }
        fallback.fallback(sender);
        return 0;
    }

    public static int parseArg(ICommandSender sender, String str, ArgsOption<?>... argsOptions) {
        for(ArgsOption<?> opt: argsOptions) {
            try {
                return opt.tryExec(sender, str) ? 1 : -1;
            }
            catch (Exception ignored) {

            }
        }
        return 0;
    }
}
