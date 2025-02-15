package com.sportradar.scoreboard.util;

public class TeamNameValidator {
    private TeamNameValidator() {
        throw new IllegalAccessError("do not instantiate");
    }

    public static boolean isValidTeamName(String name) {
        //TODO: potentially to be extended - check format, length, trim, etc.
        return StringUtils.isNotEmptyAlphabetic(name);
    }
}

