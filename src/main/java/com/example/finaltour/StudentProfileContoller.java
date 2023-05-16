package com.example.finaltour;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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

    Student student;

    ArrayList<Team> stuTeams;

    String[] teams;
    String[] tournaments;

    public void initialize(URL location, ResourceBundle resources) {
        if (teamPlayerController.playerNoob != null)
            student = teamPlayerController.playerNoob;
        stuTeams = student.getAllTeams();
        stuNameLabel.setText(student.getName());
        stuIdLabel1.setText(student.getId());

        
        teams = new String[stuTeams.size()];
        tournaments  = new String[stuTeams.size()];

        for (int i = 0; i < stuTeams.size(); i++) {
            teams[i] = stuTeams.get(i).getName();
            tournaments[i] = stuTeams.get(i).getTournament().getName();
        }
        teamsList.getItems().addAll(teams);
        tournamentsList.getItems().addAll(tournaments);
    }
    private Stage stage;
    @FXML
    void goBack(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();

    }

}
