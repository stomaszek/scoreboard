package com.sportradar.scoreboard;

import com.sportradar.scoreboard.domian.Score;
import com.sportradar.scoreboard.domian.Team;
import com.sportradar.scoreboard.exception.ScoreboardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleScoreboardTest {
    Team mexico = new Team("Mexico");
    Team canada = new Team("Canada");
    Team spain = new Team("Spain");
    Team brazil = new Team("Brazil");
    Team germany = new Team("Germany");
    Team france = new Team("France");
    Team uruguay = new Team("Uruguay");
    Team italy = new Team("Italy");
    Team argentina = new Team("Argentina");
    Team australia = new Team("Australia");

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
    @Disabled
    void checkStartMatchUsingTheSameTeam() {
        assertThrows(ScoreboardException.class, () -> sb.startGame(mexico, mexico));
    }

    @Test
    @Disabled
    void checkUpdateScoreForNotStartedMatch() {
        assertThrows(ScoreboardException.class, () -> sb.updateScore(new Score(mexico, italy, 3, 2)));
    }

    @Test
    @Disabled
    void checkStartMatchByTeamAlreadyPlaying() {
        sb.startGame(mexico, italy);

        assertThrows(ScoreboardException.class, () -> sb.startGame(mexico, spain));
    }

    @Test
    @Disabled
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
    @Disabled
    void checkStartMatchAlreadyStarted() {
        sb.startGame(mexico, italy);
        assertThrows(ScoreboardException.class, () -> sb.startGame(mexico, italy));
    }

    @Test
    @Disabled
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
    @Disabled
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
    @Disabled
    void checkFinishGameForNotStartedOne() {
        assertThrows(ScoreboardException.class, () -> sb.finishGame(mexico, spain));
    }

    @Test
    void checkFinishGameForOnlyOne() {
        sb.startGame(mexico, spain);
        sb.finishGame(mexico, spain);

        Score[] s = sb.getCurrentScoreBoard();

        assertEquals(0, s.length);
    }

    @Test
    @Disabled
    void checkFinishGame() {
        sb.startGame(mexico, spain);
        sb.startGame(france, germany);
        sb.finishGame(france, germany);

        Score[] s = sb.getCurrentScoreBoard();

        assertEquals(1, s.length);
        assertEquals(mexico, s[0].getHomeTeam());
        assertEquals(spain, s[0].getAwayTeam());
        assertEquals(0, s[0].getHomeTeamGoals());
        assertEquals(0, s[0].getAwayTeamGoals());
    }

    @Test
    @Disabled
    //scenario specified by client
    void checkClientScenario() {
        //given
        sb.startGame(mexico, canada);
        sb.startGame(spain, brazil);
        sb.startGame(germany, france);
        sb.startGame(uruguay, italy);
        sb.startGame(argentina, australia);

        sb.updateScore(new Score(mexico, canada,0,5));
        sb.updateScore(new Score(spain, brazil, 10, 2));
        sb.updateScore(new Score(germany, france, 2, 2));
        sb.updateScore(new Score(uruguay, italy, 6, 6));
        sb.updateScore(new Score(argentina, australia, 3,2));

        //when
        Score[] s = sb.getCurrentScoreBoard();

        //then
        assertEquals(5, s.length);

        assertEquals(uruguay, s[0].getHomeTeam());
        assertEquals(italy, s[0].getAwayTeam());
        assertEquals(6, s[0].getHomeTeamGoals());
        assertEquals(6, s[0].getAwayTeamGoals());

        assertEquals(spain, s[0].getHomeTeam());
        assertEquals(brazil, s[0].getAwayTeam());
        assertEquals(10, s[0].getHomeTeamGoals());
        assertEquals(2, s[0].getAwayTeamGoals());

        assertEquals(mexico, s[0].getHomeTeam());
        assertEquals(canada, s[0].getAwayTeam());
        assertEquals(0, s[0].getHomeTeamGoals());
        assertEquals(5, s[0].getAwayTeamGoals());

        assertEquals(argentina, s[0].getHomeTeam());
        assertEquals(australia, s[0].getAwayTeam());
        assertEquals(3, s[0].getHomeTeamGoals());
        assertEquals(1, s[0].getAwayTeamGoals());

        assertEquals(germany, s[0].getHomeTeam());
        assertEquals(france, s[0].getAwayTeam());
        assertEquals(2, s[0].getHomeTeamGoals());
        assertEquals(2, s[0].getAwayTeamGoals());
    }

}
