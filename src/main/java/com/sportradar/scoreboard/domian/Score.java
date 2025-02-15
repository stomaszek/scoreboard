package com.sportradar.scoreboard.domian;

public class Score {
  private final Team homeTeam;
  private final Team awayTeam;

  private final int homeTeamGoals;
  private final int awayTeamGoals;

  public Score(Team homeTeam, Team awayTeam) {
    verifyTeams(homeTeam, awayTeam);
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeTeamGoals = 0;
    this.awayTeamGoals = 0;
  }

  public Score(Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals) {
    verifyTeams(homeTeam, awayTeam);
    verifyGoals(homeTeamGoals, awayTeamGoals);
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeTeamGoals = homeTeamGoals;
    this.awayTeamGoals = awayTeamGoals;
  }

  public Team getHomeTeam() {
    return homeTeam;
  }

  public Team getAwayTeam() {
    return awayTeam;
  }

  public int getHomeTeamGoals() {
    return homeTeamGoals;
  }

  public int getAwayTeamGoals() {
    return awayTeamGoals;
  }

  private void verifyTeams(Team homeTeam, Team awayTeam) {
    if (homeTeam.equals(awayTeam)) {
      throw new IllegalArgumentException(
          String.format("%s can't play with %s", homeTeam.getName(), awayTeam.getName()));
    }
  }

  private void verifyGoals(int homeTeamGoals, int awayTeamGoals) {
    // TODO: check if upper limit of goals makes sense
    if ((homeTeamGoals < 0) || (awayTeamGoals < 0)) {
      throw new IllegalArgumentException(
          String.format("%d:%d is not a valid result", homeTeamGoals, awayTeamGoals));
    }
  }
}
