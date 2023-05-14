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
    private Scene scene;
    private Parent root;

    @FXML
    void toTournamentPage(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("TournamentPage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Elimination e = (Elimination) TournamentPageContoller.selectedTournament;
    ArrayList<Match> matches = e.getMatches();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourName.setText(e.getName());

        team1.setText(matches.get(0).getTeam1().getName());
        team2.setText(matches.get(0).getTeam2().getName());
        team3.setText(matches.get(1).getTeam1().getName());
        team4.setText(matches.get(1).getTeam2().getName());
        team5.setText(matches.get(2).getTeam1().getName());
        team6.setText(matches.get(2).getTeam2().getName());
        team7.setText(matches.get(3).getTeam1().getName());
        team8.setText(matches.get(3).getTeam2().getName());


    }
}
