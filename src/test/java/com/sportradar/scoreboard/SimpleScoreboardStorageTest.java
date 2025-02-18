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
    storage.addOrUpdate(polandHolland1);

    assertTrue(storage.isStored(polandHolland1));
  }

  @Test
  void checkIfAddedTwiceScoreIsStored() {
    storage.addOrUpdate(polandHolland1);

    assertTrue(storage.isStored(polandHolland2));
  }

  @Test
  void checkIfAddedAndRemovedIsNotStored() {
    storage.addOrUpdate(polandHolland1);
    storage.remove(polandHolland2);

    assertFalse(storage.isStored(polandHolland1));
    assertEquals(0, storage.getAllScoresSorted().length);
  }

  @Test
  void checkAddSingleScore() {
    storage.addOrUpdate(polandHolland1);

    Score[] s = storage.getAllScoresSorted();

    assertEquals(1, s.length);
    assertEquals(poland, s[0].getHomeTeam());
    assertEquals(holland, s[0].getAwayTeam());
    assertEquals(0, s[0].getHomeTeamGoals());
    assertEquals(0, s[0].getAwayTeamGoals());
  }

  @Test
  void checkAddScoresForTheSameMatch() {
    storage.addOrUpdate(polandHolland1);
    storage.addOrUpdate(polandHolland2);
    storage.addOrUpdate(polandHolland1);
    storage.addOrUpdate(polandHolland1);
    storage.addOrUpdate(polandHolland2);

    Score[] s = storage.getAllScoresSorted();

    assertEquals(1, s.length);
    assertEquals(poland, s[0].getHomeTeam());
    assertEquals(holland, s[0].getAwayTeam());
    assertEquals(3, s[0].getHomeTeamGoals());
    assertEquals(5, s[0].getAwayTeamGoals());
  }

  @Test
  void checkAddMultipleScores() {
    storage.addOrUpdate(polandHolland1);
    storage.addOrUpdate(polandHolland2);
    storage.addOrUpdate(franceItaly);

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

  @Test
  void isPlayingEmpty() {
    assertFalse(storage.isPlaying(poland));
  }

  @Test
  void isPlayingNonEmpty() {
    storage.addOrUpdate(polandHolland1);

    assertTrue(storage.isPlaying(poland));
  }

  @Test
  void isPlayingNonEmptyFalse() {
    storage.addOrUpdate(polandHolland1);

    assertFalse(storage.isPlaying(france));
  }
}
