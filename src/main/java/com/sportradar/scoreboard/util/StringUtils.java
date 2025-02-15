package com.sportradar.scoreboard.util;

class StringUtils {

  public static boolean isNotEmptyAlphabetic(String s) {
    return !isNullOrEmpty(s) && isAlphabetic(s);
  }

  private static boolean isNullOrEmpty(String s) {
    return s == null || s.isEmpty();
  }

  private static boolean isAlphabetic(String s) {
    return s.matches("[a-zA-Z]*");
  }
}
