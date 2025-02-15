package com.sportradar.scoreboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sportradar.scoreboard.domian.Score;
import com.sportradar.scoreboard.domian.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleScoreboardStorageTest {

  ScoreboardStorage storage;
  final Team poland = new Team("Poland");
  final Team holland = new Team("Holland");
  final Team france = new Team("France");
  final Team italy = new Team("Italy");
  final Score polandHolland1 = new Score(poland, holland);
  final Score polandHolland2 = new Score(poland, holland, 3, 5);
  final Score franceItaly = new Score(france, italy, 8, 5);

  @BeforeEach
  void init() {
    storage = new SimpleScoreboardStorage();
  }

  @Test
  void checkScoreBoardStorageInitialization() {
    Score[] s = storage.getAllScoresSorted();

    assertEquals(0, s.length);
  }

  @Test
  void checkIfAddedScoreIsStored() {
    storage.add(polandHolland1);

    assertTrue(storage.isStored(polandHolland1));
  }

  @Test
  void checkIfAddedTwiceScoreIsStored() {
    storage.add(polandHolland1);

    assertTrue(storage.isStored(polandHolland2));
  }

  @Test
  void checkIfAddedAndRemovedIsNotStored() {
    storage.add(polandHolland1);
    storage.remove(polandHolland2);

    assertFalse(storage.isStored(polandHolland1));
    assertEquals(0, storage.getAllScoresSorted().length);
  }

  @Test
  void checkAddSingleScore() {
    storage.add(polandHolland1);

    Score[] s = storage.getAllScoresSorted();

    assertEquals(1, s.length);
    assertEquals(poland, s[0].getHomeTeam());
    assertEquals(holland, s[0].getAwayTeam());
    assertEquals(0, s[0].getHomeTeamGoals());
    assertEquals(0, s[0].getAwayTeamGoals());
  }

  @Test
  void checkAddScoresForTheSameMatch() {
    storage.add(polandHolland1);
    storage.add(polandHolland2);
    storage.add(polandHolland1);
    storage.add(polandHolland1);
    storage.add(polandHolland2);

    Score[] s = storage.getAllScoresSorted();

    assertEquals(1, s.length);
    assertEquals(poland, s[0].getHomeTeam());
    assertEquals(holland, s[0].getAwayTeam());
    assertEquals(3, s[0].getHomeTeamGoals());
    assertEquals(5, s[0].getAwayTeamGoals());
  }

  @Test
  void checkAddMultipleScores() {
    storage.add(polandHolland1);
    storage.add(polandHolland2);
    storage.add(franceItaly);

    Score[] s = storage.getAllScoresSorted();

    assertEquals(2, s.length);
    assertEquals(france, s[0].getHomeTeam());
    assertEquals(italy, s[0].getAwayTeam());
    assertEquals(8, s[0].getHomeTeamGoals());
    assertEquals(5, s[0].getAwayTeamGoals());

    assertEquals(poland, s[1].getHomeTeam());
    assertEquals(holland, s[1].getAwayTeam());
    assertEquals(3, s[1].getHomeTeamGoals());
    assertEquals(5, s[1].getAwayTeamGoals());
  }

  public Team getFrance() {
    return france;
  }

  @Test
  void isPlayingEmpty() {
    assertFalse(storage.isPlaying(poland));
  }

  @Test
  void isPlayingNonEmpty() {
    storage.add(polandHolland1);

    assertTrue(storage.isPlaying(poland));
  }

  @Test
  void isPlayingNonEmptyFalse() {
    storage.add(polandHolland1);

    assertFalse(storage.isPlaying(france));
  }
}
