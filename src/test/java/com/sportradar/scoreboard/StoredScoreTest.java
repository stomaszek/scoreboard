package com.sportradar.scoreboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sportradar.scoreboard.domian.Score;
import com.sportradar.scoreboard.domian.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoredScoreTest {
  final Team poland = new Team("Poland");
  final Team holland = new Team("Holland");
  final StoredScore polandHolland1 = new StoredScore(new Score(poland, holland));
  final StoredScore polandHolland2 = new StoredScore(new Score(poland, holland, 3, 5));

  @BeforeEach
  void setUp() {}

  @Test
  void compareToDifferentNoOfGoals() {
    assertTrue(polandHolland2.compareTo(polandHolland1) > 0);
  }

  @Test
  void compareToSameNoOfGoals() {
    StoredScore polandHolland3 = new StoredScore(new Score(poland, holland));

    assertTrue(polandHolland3.compareTo(polandHolland1) > 0);
  }

  @Test
  void toScore() {
    Score s = polandHolland2.toScore();

    assertEquals(poland, s.getHomeTeam());
    assertEquals(holland, s.getAwayTeam());
    assertEquals(3, s.getHomeTeamGoals());
    assertEquals(5, s.getAwayTeamGoals());
  }
}
