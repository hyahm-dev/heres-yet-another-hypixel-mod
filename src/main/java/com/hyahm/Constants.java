package com.hyahm;

import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Constants {
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

    public static Pattern removeColor = Pattern.compile("(?i)" + '\u00A7' + "[0-9A-FK-OR]");

    public static boolean isHypixel() {
        if(Minecraft.getMinecraft().getCurrentServerData() == null)
            return false;
        if(!Minecraft.getMinecraft().getCurrentServerData().serverIP.endsWith("hypixel.net"))
            return false;
        return true;
    }
}
