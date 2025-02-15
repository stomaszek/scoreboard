package com.sportradar.scoreboard.domian;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreTest {
  Team poland = new Team("Poland");
  Team holland = new Team("Holland");

  @Test
  void checkDefaultScore() {
    Score s = new Score(poland, holland);

    assertEquals(poland, s.getHomeTeam());
    assertEquals(holland, s.getAwayTeam());
    assertEquals(0, s.getHomeTeamGoals());
    assertEquals(0, s.getAwayTeamGoals());
  }

  @Test
  void checkCreatedScoreZeroZero() {
    Score s = new Score(poland, holland, 0, 0);

    assertEquals(poland, s.getHomeTeam());
    assertEquals(holland, s.getAwayTeam());
    assertEquals(0, s.getHomeTeamGoals());
    assertEquals(0, s.getAwayTeamGoals());
  }

  @Test
  void checkCreatedValidScore() {
    Score s = new Score(poland, holland, 3, 2);

    assertEquals(poland, s.getHomeTeam());
    assertEquals(holland, s.getAwayTeam());
    assertEquals(3, s.getHomeTeamGoals());
    assertEquals(2, s.getAwayTeamGoals());
  }

  @Test
  void testCreatedInvalidScore() {
    assertThrows(IllegalArgumentException.class, () -> new Score(poland, holland, 3, -2));
    assertThrows(IllegalArgumentException.class, () -> new Score(poland, holland, -3, -2));
    assertThrows(IllegalArgumentException.class, () -> new Score(poland, holland, -3, 2));
  }

  @Test
  void testSameTeamInvalidScore() {
    assertThrows(IllegalArgumentException.class, () -> new Score(poland, poland, 3, 2));
  }
}
