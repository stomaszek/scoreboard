package com.sportradar.scoreboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sportradar.scoreboard.domian.Score;
import com.sportradar.scoreboard.domian.Team;
import com.sportradar.scoreboard.exception.ScoreboardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleScoreboardTest {
  final Team mexico = new Team("Mexico");
  final Team canada = new Team("Canada");
  final Team spain = new Team("Spain");
  final Team brazil = new Team("Brazil");
  final Team germany = new Team("Germany");
  final Team france = new Team("France");
  final Team uruguay = new Team("Uruguay");
  final Team italy = new Team("Italy");
  final Team argentina = new Team("Argentina");
  final Team australia = new Team("Australia");

  Scoreboard sb;

  @BeforeEach
  void initScoreboard() {
    sb = new SimpleScoreboard();
  }

  @Test
  void checkScoreboardInitialization() {
    Score[] s = sb.getCurrentScoreBoard();

    assertEquals(0, s.length);
  }

  @Test
  void checkStartMatchUsingTheSameTeam() {
    assertThrows(ScoreboardException.class, () -> sb.startGame(mexico, mexico));
  }

  @Test
  void checkUpdateScoreForNotStartedMatch() {
    assertThrows(ScoreboardException.class, () -> sb.updateScore(new Score(mexico, italy, 3, 2)));
  }

  @Test
  void checkStartMatchByTeamAlreadyPlaying() {
    sb.startGame(mexico, italy);

    assertThrows(ScoreboardException.class, () -> sb.startGame(mexico, spain));
    assertThrows(ScoreboardException.class, () -> sb.startGame(spain, italy));
  }

  @Test
  void checkStartMatch() {
    sb.startGame(mexico, italy);

    Score[] s = sb.getCurrentScoreBoard();

    assertEquals(1, s.length);

    assertEquals(mexico, s[0].getHomeTeam());
    assertEquals(italy, s[0].getAwayTeam());
    assertEquals(0, s[0].getHomeTeamGoals());
    assertEquals(0, s[0].getAwayTeamGoals());
  }

  @Test
  void checkStartMatchAlreadyStarted() {
    sb.startGame(mexico, italy);
    assertThrows(ScoreboardException.class, () -> sb.startGame(mexico, italy));
  }

  @Test
  void checkUpdateScore() {
    sb.startGame(mexico, italy);
    sb.updateScore(new Score(mexico, italy, 3, 2));

    Score[] s = sb.getCurrentScoreBoard();

    assertEquals(1, s.length);
    assertEquals(mexico, s[0].getHomeTeam());
    assertEquals(italy, s[0].getAwayTeam());
    assertEquals(3, s[0].getHomeTeamGoals());
    assertEquals(2, s[0].getAwayTeamGoals());
  }

  @Test
  void checkUpdateScoreScoreSeveralTimes() {
    sb.startGame(mexico, italy);
    sb.updateScore(new Score(mexico, italy, 0, 0));
    sb.updateScore(new Score(mexico, italy, 1, 2));
    sb.updateScore(new Score(mexico, italy, 3, 2));

    Score[] s = sb.getCurrentScoreBoard();

    assertEquals(1, s.length);

    assertEquals(mexico, s[0].getHomeTeam());
    assertEquals(italy, s[0].getAwayTeam());
    assertEquals(3, s[0].getHomeTeamGoals());
    assertEquals(2, s[0].getAwayTeamGoals());
  }

  @Test
  void checkFinishGameForNotStartedOne() {
    assertThrows(ScoreboardException.class, () -> sb.finishGame(mexico, spain));
  }

  @Test
  void checkFinishGameUsingTheSameTeam() {
    assertThrows(ScoreboardException.class, () -> sb.finishGame(mexico, mexico));
  }

  @Test
  void checkFinishGameForOnlyOne() {
    sb.startGame(mexico, spain);
    sb.finishGame(mexico, spain);

    Score[] s = sb.getCurrentScoreBoard();

    assertEquals(0, s.length);
  }

  @Test
  void checkFinishGame() throws Exception {
    sb.startGame(france, germany);
    Thread.sleep(2);
    sb.startGame(mexico, spain);
    Thread.sleep(2);
    sb.finishGame(france, germany);

    Score[] s = sb.getCurrentScoreBoard();

    assertEquals(1, s.length);
    assertEquals(mexico, s[0].getHomeTeam());
    assertEquals(spain, s[0].getAwayTeam());
    assertEquals(0, s[0].getHomeTeamGoals());
    assertEquals(0, s[0].getAwayTeamGoals());
  }

  @Test
  // scenario specified by client
  void checkClientScenario() {
    // given
    sb.startGame(mexico, canada);
    sb.startGame(spain, brazil);
    sb.startGame(germany, france);
    sb.startGame(uruguay, italy);
    sb.startGame(argentina, australia);

    sb.updateScore(new Score(mexico, canada, 0, 5));
    sb.updateScore(new Score(spain, brazil, 10, 2));
    sb.updateScore(new Score(germany, france, 2, 2));
    sb.updateScore(new Score(uruguay, italy, 6, 6));
    sb.updateScore(new Score(argentina, australia, 3, 1));

    // when
    Score[] s = sb.getCurrentScoreBoard();

    // then
    assertEquals(5, s.length);

    assertEquals(uruguay, s[0].getHomeTeam());
    assertEquals(italy, s[0].getAwayTeam());
    assertEquals(6, s[0].getHomeTeamGoals());
    assertEquals(6, s[0].getAwayTeamGoals());

    assertEquals(spain, s[1].getHomeTeam());
    assertEquals(brazil, s[1].getAwayTeam());
    assertEquals(10, s[1].getHomeTeamGoals());
    assertEquals(2, s[1].getAwayTeamGoals());

    assertEquals(mexico, s[2].getHomeTeam());
    assertEquals(canada, s[2].getAwayTeam());
    assertEquals(0, s[2].getHomeTeamGoals());
    assertEquals(5, s[2].getAwayTeamGoals());

    assertEquals(argentina, s[3].getHomeTeam());
    assertEquals(australia, s[3].getAwayTeam());
    assertEquals(3, s[3].getHomeTeamGoals());
    assertEquals(1, s[3].getAwayTeamGoals());

    assertEquals(germany, s[4].getHomeTeam());
    assertEquals(france, s[4].getAwayTeam());
    assertEquals(2, s[4].getHomeTeamGoals());
    assertEquals(2, s[4].getAwayTeamGoals());
  }
}
