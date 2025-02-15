package com.sportradar.scoreboard;

import com.sportradar.scoreboard.domian.Score;

class StoredScore extends Score implements Comparable<StoredScore> {
  long timestamp;

  public StoredScore(Score score) {
    super(
        score.getHomeTeam(),
        score.getAwayTeam(),
        score.getHomeTeamGoals(),
        score.getAwayTeamGoals());
    timestamp = System.currentTimeMillis();
    try {
      //to ensure timestamp uniques
      Thread.sleep(2);
    } catch (InterruptedException e) {
      //
    }
  }

  @Override
  public int compareTo(StoredScore other) {
    if (getSumOfGoals() == other.getSumOfGoals()) {
      return Long.compare(timestamp, other.timestamp);
    } else {
      return Integer.compare(getSumOfGoals(), other.getSumOfGoals());
    }
  }

  public Score toScore() {
    return new Score(getHomeTeam(), getAwayTeam(), getHomeTeamGoals(), getAwayTeamGoals());
  }

  private int getSumOfGoals() {
    return getHomeTeamGoals() + getAwayTeamGoals();
  }
}
