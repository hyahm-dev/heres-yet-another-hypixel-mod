package com.hyahm.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.EnumChatFormatting;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.lwjgl.opengl.GL11;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Utils {
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
}
