package com.example.finaltour;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class StudentProfileContoller implements Initializable {
    
    @FXML
    private Label stuNameLabel;

    @FXML
    private Label stuIdLabel1;

    @FXML
    private ListView<String> teamsList;

    @FXML
    private ListView<String> tournamentsList;

    @FXML
    private Button goBackButton;

    Student student = new Student("mishary", "20203"); // sample
    ArrayList<Team> allTeams = student.getAllTeams();

    String[] teams;
    String[] tournaments;

    public void initialize(URL location, ResourceBundle resources) {
        stuNameLabel.setText(student.getName());
        stuIdLabel1.setText(student.getId());

        try {
            student.addTeam(new Team(new RoundRobin("tournament", false, null, null), "team1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        teams = new String[allTeams.size()];
        tournaments  = new String[allTeams.size()];

        for (int i = 0; i < allTeams.size(); i++) {
            teams[i] = allTeams.get(i).getName();
            tournaments[i] = allTeams.get(i).getTournament().getName();
        }
        teamsList.getItems().addAll(teams);
        tournamentsList.getItems().addAll(tournaments);
    }

}
