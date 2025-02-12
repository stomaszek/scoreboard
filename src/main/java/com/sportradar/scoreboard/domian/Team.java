package com.sportradar.scoreboard.domian;

public class Team {
  String name;

  public Team(String name) {
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

    if (name == null) {
        return other.name == null;
    } else return name.equals(other.name);
  }
}
