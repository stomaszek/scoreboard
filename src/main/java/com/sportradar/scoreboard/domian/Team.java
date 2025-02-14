package com.sportradar.scoreboard.domian;

import static com.sportradar.scoreboard.util.StringUtils.isNotEmptyAlphabetic;

public class Team {
  String name;

  public Team(String name) {
    validateName(name);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    Team other = (Team) obj;

    return name.equals(other.name);
  }

  private void validateName(String name) {
    if(!isNotEmptyAlphabetic(name)) {
      throw new IllegalArgumentException("nor valid team name (use non-empty alphabetic)");
    }
  }

}
