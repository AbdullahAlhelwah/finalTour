package com.example.finaltour;

import java.util.Date;

public class AddToTestTournaments {
    public static void main(String[] args) throws Exception {
        Tournament testTournament1 = new RoundRobin("myRoundRobin", false, "BasketBall", new Date());
        Tournament testTournament2 = new Elimination("myElimination",true, "tennis", new Date());
        // ana 7mar mswi 2 loop 
        for(int i =1;i<9;i++)
            testTournament2.addTeam(new Team(testTournament2, new Student("Hamza"+i, "00"+i))); 
        for (int j=1;j<9;j++){
            Team te = new Team(testTournament1, "teamNo"+j);
            te.addStudent(new Student("Mishari"+j, "10"+j));
            te.addStudent(new Student("BoSad"+j,"20"+j));
            testTournament1.addTeam(te);
        }
        testTournament1.generateMatches(1);
        testTournament2.generateMatches(3);
        Main.tournaments.add(testTournament1);
        Main.tournaments.add(testTournament2);
        Main.saveTournaments();
        


        // Main.loadTournaments();
        // System.out.println(Main.tournaments.get(0).getName());

    }
}
