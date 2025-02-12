package com.sportradar.scoreboard;

import com.sportradar.scoreboard.domian.Score;
import com.sportradar.scoreboard.domian.Team;

import java.util.Iterator;

public interface Scoreboard {
  void startGame(Team homeTeam, Team awayTeam);
  void finishGame(Team homeTeam, Team awayTeam);
  void updateScore(Score score);
  Iterator<Score> getCurrentScoreBoard();
}
