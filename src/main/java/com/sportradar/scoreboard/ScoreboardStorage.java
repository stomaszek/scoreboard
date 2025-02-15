package com.sportradar.scoreboard;

import com.sportradar.scoreboard.domian.Score;
import com.sportradar.scoreboard.domian.Team;

interface ScoreboardStorage {
  boolean isStored(Score score);

  boolean isPlaying(Team team);

  void add(Score score);

  void remove(Score score);

  void update(Score score);

  Score[] getAllScoresSorted();
}
