package com.sportradar.scoreboard.domian;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TeamTest {
  Team poland = new Team("Poland");
  Team holland = new Team("Holland");

  @Test
  void checkIfTheSameNameIdentifiesTheSameTeam() {
    Team polandAgain = new Team("Poland");

    assertEquals(poland, polandAgain);
  }

  @Test
  void checkIfDifferentNameIdentifiesTheDifferentTeams() {
    assertNotEquals(poland, holland);
  }

  @Test
  void checkIfTeamIsNotEqualToNull() {
    assertNotEquals(poland, null);
  }

  @Test
  void checkIfSameTeamIsEqual() {
    assertEquals(poland, poland);
  }

  @Test
  void checkIfTeamIsNotEqualToStringWithTheSameName() {
    assertNotEquals(poland, "Poland");
  }

  @Test
  void checkIfNullIsNotValidTeamName() {
    assertThrows(IllegalArgumentException.class, () -> new Team(null));
  }

  @Test
  void checkIfEmptyIsNotValidTeamName() {
    assertThrows(IllegalArgumentException.class, () -> new Team(""));
  }

  @Test
  void checkIfAlphaNumericIsNotValidTeamName() {
    assertThrows(IllegalArgumentException.class, () -> new Team("testName3"));
  }

  @Test
  void checkIfSpecialIsNotValidTeamName() {
    assertThrows(IllegalArgumentException.class, () -> new Team("nameWith$@"));
  }
}
