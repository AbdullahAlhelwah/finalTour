package com.example.finaltour;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class EliminationPageController implements Initializable {

    @FXML
    private Label team1;

    @FXML
    private Label team10;

    @FXML
    private Label team11;

    @FXML
    private Label team12;

    @FXML
    private Label team13;

    @FXML
    private Label team14;

    @FXML
    private Label team15;

    @FXML
    private Label team2;

    @FXML
    private Label team3;

    @FXML
    private Label team4;

    @FXML
    private Label team5;

    @FXML
    private Label team6;

    @FXML
    private Label team7;

    @FXML
    private Label team8;

    @FXML
    private Label tourName;

    @FXML
    private Label team9;
    private Stage stage;


    @FXML
    void toTournamentPage(ActionEvent event) {

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();

    }

    Tournament e =   TournamentPageContoller.selectedTournament;
    ArrayList<Match> matches = e.getMatches();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourName.setText(e.getName());

        team1.setText(matches.get(matches.size()-1).getTeam1Name());
        team2.setText(matches.get(matches.size()-1).getTeam2Name());
        team3.setText(matches.get(matches.size()-2).getTeam1Name());
        team4.setText(matches.get(matches.size()-2).getTeam2Name());
        team5.setText(matches.get(matches.size()-3).getTeam1Name());
        team6.setText(matches.get(matches.size()-3).getTeam2Name());
        team7.setText(matches.get(matches.size()-4).getTeam2Name());
        team8.setText(matches.get(matches.size()-4).getTeam1Name());
        team9.setText(matches.get(matches.size()-5).getTeam2Name());
        team10.setText(matches.get(matches.size()-5).getTeam1Name());
        team11.setText(matches.get(matches.size()-6).getTeam2Name());
        team12.setText(matches.get(matches.size()-6).getTeam1Name());
        team13.setText(matches.get(matches.size()-7).getTeam2Name());
        team14.setText(matches.get(matches.size()-7).getTeam1Name());
        team15.setText(e.getWinnerName());




    }
}
