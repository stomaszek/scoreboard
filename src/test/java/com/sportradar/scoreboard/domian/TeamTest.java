package com.sportradar.scoreboard.domian;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import  static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class TeamTest {

  @Test
  void checkIfTheSameNameIdentifiesTheSameTeam() {
    Team poland1 = new Team("Poland");
    Team poland2 = new Team("Poland");

    assertEquals(poland1, poland2);
  }

  @Test
  void checkIfEmptyNameIdentifiesTheSameTeam() {
    Team empty1 = new Team("");
    Team empty2 = new Team("");

    assertEquals(empty1, empty2);
  }

  @Test
  void checkIfNullNameIdentifiesTheSameTeam() {
    Team null1 = new Team(null);
    Team null2 = new Team(null);

    assertEquals(null1, null2);
  }

  @Test
  void checkIfDifferentNameIdentifiesTheDifferentTeams() {
    Team poland = new Team("Poland");
    Team holland = new Team("Holland");

    assertNotEquals(poland, holland);
  }

  @Test
  void checkIfTeamIsNotEqualToNull() {
    Team poland = new Team("Poland");

    assertNotEquals(null, poland);
    assertFalse(poland.equals(null));
  }

  @Test
  void checkIfSameTeamIsEqual() {
    Team poland = new Team("Poland");

    assertEquals(poland, poland);
  }

  @Test
  void checkIfDifferentNameIdentifiesTheDifferentTeamcc() {
    Team poland = new Team("Poland");
    Team empty = new Team(null);

    assertNotEquals(poland, empty);
  }
    @Test
    void checkIfTeamIsNotEqualToStringWithTheSameName() {
        Team poland = new Team("Poland");

        assertNotEquals(poland, "Poland");
    }

}
