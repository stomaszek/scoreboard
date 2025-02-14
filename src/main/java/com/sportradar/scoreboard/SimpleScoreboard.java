package com.sportradar.scoreboard;

import com.sportradar.scoreboard.domian.Score;
import com.sportradar.scoreboard.domian.Team;

public class SimpleScoreboard implements Scoreboard {

    @Override
    public void startGame(Team homeTeam, Team awayTeam) {
    }

    @Override
    public void finishGame(Team homeTeam, Team awayTeam) {
    }

    @Override
    public void updateScore(Score score) {
    }

    @Override
    public Score[] getCurrentScoreBoard() {
        return new Score[] {};
    }
}
