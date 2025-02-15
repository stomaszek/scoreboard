package com.sportradar.scoreboard.domian;

import com.sportradar.scoreboard.util.TeamNameValidator;

public class Team {
  private final String name;

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
    if (!TeamNameValidator.isValidTeamName(name)) {
      throw new IllegalArgumentException(String.format("%s is not valid team name", name));
    }
  }
}
