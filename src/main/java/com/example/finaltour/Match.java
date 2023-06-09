package com.example.finaltour;


import java.io.Serializable;
import java.util.Date;

public class Match implements Serializable{
    private final Tournament tournament;
    private Team team1;
    private Team team2;
    private int goals1;
    private int goals2;
    private Date date;
    private boolean finshed = false;
    private String team1Name;
    private String team2Name;
    private String tourName;
    private String result;
    private int round;


    //Match constructor
    public Match(Tournament t, Team a, Team b, Date d,int r){
        tournament  = t;
        team1 = a;
        team2 = b;
        team1Name = a.getName();
        team2Name = b.getName();
        tourName = t.getName();
        date = d;
        result = "TBA";
        round =r;
    }
    //another constructors with 1/without Teams
    public Match(Tournament t, Date d, Team a,int r){
        tournament  = t;
        date = d;
        team1 = a;
        team1Name = team1.getName();
        team2Name = "TBA";
        tourName = t.getName();
        result = "TBA";
        round =r;
    }
    public Match(Tournament t, Date d,int r){
        tournament  = t;
        date = d;
        tourName = t.getName();
        team1Name = "TBA";
        team2Name = "TBA";
        result = "TBA";
        round =r;
    }


    //Getters
    public Date getDate() {
        return date;
    }
    public int getGoals1() {
        return goals1;
    }
    public int getGoals2() {
        return goals2;
    }
    public Team getTeam1() {
        return team1;
    }
    public Team getTeam2() {
        return team2;
    }public Tournament getTournament() {
        return tournament;
    }
    public boolean gethasFinshed() {
        return finshed;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public String getTourName() {
        return tourName;
    }
    public int getRound() {
        return round;
    }

    public Team getWinner() {
        //if the match is not finished, return null
        if (!finshed) {
            return null;
        }
        //if the match is a draw, return null
        if (goals1 == goals2) {
            return null;
        }
        //if team1 wins, return team1
        if (goals1 > goals2) {
            return team1;
        }
        //if team2 wins, return team2
        return team2;
    }

    public String getResult() {
        return result;
    }

    //Setters
    public void setDate(Date date) {
        this.date = date;
    }
    public void setTeam1(Team team1) {
        this.team1 = team1;
        team1Name = team1.getName();
    }
    public void setTeam2(Team team2) {
        this.team2 = team2;
        team2Name = team2.getName();
    }
    public void setResult(){
        result = ""+goals1+"-"+goals2;
    }

    // a method to record the score of the match (We can use this method to edit the score as well as record it)
    public void recordScore(int a, int b) throws Exception{
        //if there is no team1 or team2, throw an exception
        if (team1 == null || team2 == null) {
            throw new Exception("There must be two teams");
        }
        //if tournament has finished, throw an exception
        if (tournament.getHasFinished()) {
            throw new Exception("Tournament has finished");
        }
        //check if the score is valid
        if (a < 0 || b < 0) {
            throw new Exception("Goals cannot be negative");
        }


        //if the match has already been played, Go to editScore method
        if (finshed) {
            editScore(a, b);
        }
        //if the match has not been played, record the score, and update the teams' points if it is a round robin tournament or update table if it is an elimination tournament
        else {
            //record the score
            goals1 = a;
            goals2 = b;
            finshed = true;
            setResult();
            //check if the match is a round robin match
            if (tournament instanceof RoundRobin) {
                team1.addGoalsReceived(b);
                team1.addGoalsScored(a);
                team2.addGoalsReceived(a);
                team2.addGoalsScored(b);
                ((RoundRobin) tournament).updateTable();
                //check if the match is a draw
                if (a == b) {
                    team1.addPoints(1);
                    team2.addPoints(1);
                    team1.modifyDraws(1);
                    team2.modifyDraws(1);
                }
                //check if team1 wins
                else if (a > b) {
                    team1.addPoints(3);
                    team1.modifyWins(1);
                    team2.modifyLoses(1);
                }
                //check if team2 wins
                else {
                    team2.addPoints(3);
                    team1.modifyLoses(1);
                    team2.modifyWins(1);
                }
                
            }
            //if the match is an elimination match, update the table
            else {
                //check if the match is a draw, return exception
                if (a == b) {
                    throw new Exception("There must be a winner");
                }
                //check if team1 wins
                else if (a > b) {
                    team1.addGoalsReceived(b);
                    team1.addGoalsScored(a);
                    team2.addGoalsReceived(a);
                    team2.addGoalsScored(b);
                    team1.addPoints(1);
                }
                //check if team2 wins
                else {
                    team1.addGoalsReceived(b);
                    team1.addGoalsScored(a);
                    team2.addGoalsReceived(a);
                    team2.addGoalsScored(b);
                    team2.addPoints(1);
                }

                //update the table
                //cast tournament to an elimination tournament
                ((Elimination) tournament).updateBracket(this);
            }
        }

    }

    // a method to edit the score of the match
    private void editScore(int a, int b) throws Exception {
        //if there is no team1 or team2, throw an exception
        if (team1 == null || team2 == null) {
            throw new Exception("There must be two teams");
        }
        //if tournament has finished, throw an exception
        if (tournament.getHasFinished()) {
            throw new Exception("Tournament has finished");
        }
        //check if the score is valid
        if (a < 0 || b < 0) {
            throw new Exception("Goals cannot be negative");
        }
        // if it is a round robin tournament, update the points of the teams
        //remove the old points
        if (tournament instanceof RoundRobin) {
            //check if the match is a draw
            if (goals1 == goals2) {
                team1.addPoints(-1);
                team2.addPoints(-1);
                team1.modifyDraws(-1);
                team2.modifyDraws(-1);
            }
            //check if team1 wins
            else if (goals1 > goals2) {
                team1.addPoints(-3);
                team1.modifyWins(-1);
                team2.modifyLoses(-1);
            }
            //check if team2 wins
            else {
                team2.addPoints(-3);
                team1.modifyLoses(-1);
                team2.modifyWins(-1);
            }
            //record the new result
            finshed = false;
            recordScore(a, b);
        }
        //if it is an elimination tournament, update the table
        else {
            throw new Exception("Can't modify scores for elimination tournament.");
        }
    }



    
    public String toString() {
        return team1.getName()+" vs "+ team2.getName()+"  "+date.toString();
    }

}