package com.hyahm;

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
        Pattern.compile("(?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)"),
        Pattern.compile("\\.get(TEAM) (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)"),
        Pattern.compile("Guild > (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)"),
        Pattern.compile("Party > (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)"),
        Pattern.compile("\\.get(SHOUT) (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)"),
        Pattern.compile("\\.get(SPECTATOR) (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)"),
    };
}
