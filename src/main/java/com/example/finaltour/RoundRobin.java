package com.example.finaltour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class RoundRobin extends Tournament implements java.io.Serializable{


    // constructor
    public RoundRobin(String name, boolean isIndividual, String sport, Date startDate) {
        super(name, isIndividual, sport, startDate);
    }
    // another constructor with endDate
    public RoundRobin(String name, boolean isIndividual, String sport, Date startDate, Date endDate) {
        super(name, isIndividual, sport, startDate, endDate);
    }

    public void generateMatches(int restDays) throws Exception {
        if (restDays < 0) {
            throw new Exception("Rest days cannot be negative");
        }
        if (matches.size() > 0) {
            throw new Exception("Matches have already been generated");
        }
        if (getTeams().size() < 2) {
            throw new Exception("Not enough teams to generate matches");
        }
        if (getHasFinished()) {
            throw new Exception("Tournament has finished");
        }
        

        ArrayList<Team> teamsCopy = new ArrayList<Team>(getTeams());
        if (teamsCopy.size()%2 == 1) teamsCopy.add(null);
        Collections.shuffle(teamsCopy);
        ArrayList<Team> opponent = new ArrayList<>(teamsCopy);
        opponent.remove(0);
        int nRounds = teamsCopy.size() -1;
        for(int round = 0; round < nRounds; round++){
            matches.add(new Match(this,teamsCopy.get(0),opponent.get(0),
                    new Date(getStartDate().getTime()+ (long) restDays *round*24 * 60 * 60 * 1000),round+1));
            for(int i= 1; i< teamsCopy.size()/2; i++){
                matches.add(new Match(this,opponent.get(opponent.size()-i),opponent.get(i),
                        new Date(getStartDate().getTime()+ (long) restDays *round*24 * 60 * 60 * 1000),round+1));
            }
            Collections.rotate(opponent,1);
        }
        if (getEndDate() == null) {
            setEndDate(new Date(getStartDate().getTime() + (long) restDays * (nRounds + 1) * 24 * 60 * 60 * 1000));
        }
        //check if the final date is before or on the same date as the end date or raise an error
        if (matches.get(matches.size()-1).getDate().compareTo(getEndDate()) >= 0) {
            matches.clear();
            throw new Exception("The tournament cannot be finished in the given time, please reduce the number of rest days");
        }

    }

    public void updateTable(){
        Collections.sort(getTeams());
        Collections.reverse(getTeams());

        for (Match mm: matches){
            if(!mm.gethasFinshed()) break;
        }    
        setHasFinished(true);
        setWinner(getTeams().get(0));
    }
    public void viewStanding() {
    }


}