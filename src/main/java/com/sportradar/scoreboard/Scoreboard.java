package com.sportradar.scoreboard;

import com.sportradar.scoreboard.domian.Score;
import com.sportradar.scoreboard.domian.Team;

import java.util.Iterator;

public interface Scoreboard {
    public void startGame(Team homeTeam, Team awayTeam);
    public void finishGame(Team homeTeam, Team awayTeam);
    public void updateScore(Score score);
    public Iterator<Score> getCurrentScoreBoard();
}
