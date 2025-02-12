package com.sportradar.scoreboard.domian;

public class Score {
    Team homeTeam;
    Team awayTeam;

    int homeTeamGoals;
    int awayTeamGoals;

    public Score(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = 0;
        this.awayTeamGoals = 0;
    }

    public Score(Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

}
