package com.example.finaltour;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable{
    private final String name;
    private final String id;
    private final ArrayList<Team> allTeams = new ArrayList<>();
    public Student(String name,String id){
        this.name = name;
        this.id = id;
    }

    // getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    // returns true if the student is enrolled in t
    public boolean participateIn(Tournament tournament){
        for(Team t : allTeams)
            if(t.getTournament().equals(tournament)) return true;
        return false;
    }

    public ArrayList<Team>  getAllTeams() {
        return allTeams;
    }

    public void addTeam(Team t){
        this.allTeams.add(t);
    }
    public void removeTeam(Team t){
        this.allTeams.remove(t);
    }

    @Override
    public String toString() {
        return name + " ," + id;
    }
}