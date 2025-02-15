package com.sportradar.scoreboard;

import com.sportradar.scoreboard.domian.Score;
import com.sportradar.scoreboard.domian.Team;
import java.util.Set;
import java.util.TreeSet;

class SimpleScoreboardStorage implements ScoreboardStorage {
  Set<StoredScore> scores = new TreeSet<>();

  @Override
  public boolean isStored(Score score) {
    for (StoredScore s : scores) {
      if (s.getHomeTeam().equals(score.getHomeTeam())
          && s.getAwayTeam().equals(score.getAwayTeam())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean isPlaying(Team team) {
    for (StoredScore s : scores) {
      if (s.getHomeTeam().equals(team) || s.getAwayTeam().equals(team)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void add(Score score) {
    StoredScore ss = new StoredScore(score);

    if (isStored(ss)) {
      removeFromScores(score);
    }

    scores.add(ss);
  }

  @Override
  public void remove(Score score) {
    removeFromScores(score);
  }

  @Override
  public void update(Score score) {
    StoredScore ss = new StoredScore(score);

    if (isStored(ss)) {
      removeFromScores(score);
    }

    scores.add(ss);
  }

  @Override
  public Score[] getAllScoresSorted() {
    if (scores.isEmpty()) {
      return new Score[0];
    } else {
      Score[] r = new Score[scores.size()];
      int i = scores.size();
      for (StoredScore score : scores) {
        r[--i] = score.toScore();
      }
      return r;
    }
  }

  private void removeFromScores(Score score) {
    scores.removeIf(
        s ->
            s.getHomeTeam().equals(score.getHomeTeam())
                && s.getAwayTeam().equals(score.getAwayTeam()));
  }
}
