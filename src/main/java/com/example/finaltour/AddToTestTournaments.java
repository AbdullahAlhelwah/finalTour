package com.example.finaltour;

import java.util.Date;

public class AddToTestTournaments {
    public static void main(String[] args) throws Exception {
        Main.tournaments.clear();
        Tournament testTournament1 = new RoundRobin("myRoundRobin", false, "BasketBall", new Date());
        Tournament testTournament2 = new Elimination("myElimination",true, "tennis", new Date());
        Tournament testTournament3 = new RoundRobin("دوري الأجازة", true, "tennis", new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
        Tournament testTournament4 = new Elimination("دوري يبدأ بكرة", false, "football", new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
        // ana 7mar mswi 2 loop 
        for(int i =1;i<9;i++)
            testTournament2.addTeam(new Team(testTournament2, new Student("Hamza"+i, "0000"+i))); 
        for (int j=1;j<9;j++){
            Team te = new Team(testTournament1, "teamNo"+j);
            te.addStudent(new Student("Mishari"+j, "1100"+j));
            te.addStudent(new Student("BoSad"+j,"2100"+j));
            testTournament1.addTeam(te);
            if(j<7){
                te.addStudent(new Student("Majeed"+j, "3000"+j));
                testTournament4.addTeam(te);
            }
        }
        for(int k =1; k < 8; k++){
            testTournament3.addTeam(new Team(testTournament3, new Student("Abdullah"+k, "1000"+k)));

        }
        
        

        testTournament1.setOpen(false);
        testTournament2.setOpen(false);
        testTournament1.generateMatches(1);
        testTournament2.generateMatches(3);


        Main.tournaments.add(testTournament1);
        Main.tournaments.add(testTournament2);
        Main.tournaments.add(testTournament3);
        Main.tournaments.add(testTournament4);
        Main.saveTournaments();

        // System.out.println(Main.tournaments.get(1).matches.get(Main.tournaments.get(1).matches.size()-1));
        


        // Main.loadTournaments();
        // System.out.println(Main.tournaments.get(0).getName());

    }
}
