package com.sportradar.scoreboard;

import com.sportradar.scoreboard.domian.Score;
import com.sportradar.scoreboard.domian.Team;
import com.sportradar.scoreboard.exception.ScoreboardException;

public class SimpleScoreboard implements Scoreboard {

  ScoreboardStorage store = new SimpleScoreboardStorage();

  @Override
  public void startGame(Team homeTeam, Team awayTeam) {
    if (homeTeam.equals(awayTeam)) {
      throw new ScoreboardException(
          String.format("%s can't play against %s", homeTeam.getName(), awayTeam.getName()));
    }

    if (store.isStored(new Score(homeTeam, awayTeam))) {
      throw new ScoreboardException(
          String.format("%s vs %s already started", homeTeam.getName(), awayTeam.getName()));
    }

    if (store.isPlaying(homeTeam)) {
      throw new ScoreboardException(String.format("%s is already playing", homeTeam.getName()));
    }
    if (store.isPlaying(awayTeam)) {
      throw new ScoreboardException(String.format("%s is already playing", awayTeam.getName()));
    }

    store.add(new StoredScore(new Score(homeTeam, awayTeam)));
  }

  @Override
  public void finishGame(Team homeTeam, Team awayTeam) {
    if (homeTeam.equals(awayTeam)) {
      throw new ScoreboardException(
          String.format("%s can't play against %s", homeTeam.getName(), awayTeam.getName()));
    }

    Score score = new Score(homeTeam, awayTeam);

    if (store.isStored(score)) {
      store.remove(score);
    } else {
      throw new ScoreboardException(
          String.format("%s vs %s is not listed", homeTeam.getName(), awayTeam.getName()));
    }
  }

  @Override
  public void updateScore(Score score) {
    if (store.isStored(score)) {
      store.update(score);
    } else {
      throw new ScoreboardException(
          String.format(
              "%s vs %s is not started yet",
              score.getHomeTeam().getName(), score.getAwayTeam().getName()));
    }
  }

  @Override
  public Score[] getCurrentScoreBoard() {
    return store.getAllScoresSorted();
  }
}
