package com.example.finaltour;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.Node;


public class TournamentPageContoller {

    @FXML
    private Label typeLabel;

    @FXML
    private Label sportLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label isIndividualLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label winnerLabel;

    @FXML
    private TextFlow participantTextFlow;

    static Tournament selectedTournament;
    Stage stage;
    private Scene scene;
    private Parent root;


    public void setSelectedTournament(Tournament t) {
        selectedTournament = t;
    }

    public void setStage(Stage s) {
        stage = s;
    }

    public void cancelButton(ActionEvent event) {
        stage.close();
    }

    public void switchToRegisterPage(ActionEvent event) {
        try {
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("RigesterInTournamentPage.fxml"));
            Parent root = loader.load();

            RigesterInTournamentController rigesterInTournamentController = loader.getController();
            rigesterInTournamentController.cotroller(selectedTournament, stage);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populatePage(Tournament t, Stage s) {
        setStage(s);
        setSelectedTournament(t);
        isIndividualLabel.setText(t.getIsIndividual() + "");
        nameLabel.setText(t.getName());
        sportLabel.setText(t.getSport());
        startDateLabel.setText(t.getStartDate() + "");

        if (t.getWinner() != null)
            winnerLabel.setText(t.getWinner().getName());
        else
            winnerLabel.setText("-");


        if(t instanceof RoundRobin)
            typeLabel.setText("Round Robin");
        else
            typeLabel.setText("Elimination");

        if(t.getHasFinished() == true) {
            statusLabel.setText("Closed");
            statusLabel.setTextFill(Color.RED);
        } else {
            statusLabel.setText("Open");
            statusLabel.setTextFill(Color.GREEN);
        }

        // get the teams participated in the tournament 
        String particpants = "";
        for(Team team : t.getTeams())
            particpants += team.getName() + ", ";

        if (particpants.equals(""))
            particpants += "No particpants";

        Text text = new Text(particpants);
        participantTextFlow.getChildren().add(text);  
    }
    @FXML
    void toStanding(ActionEvent event) {
        String destination;
        if (selectedTournament instanceof Elimination)
            destination = "EliminationPage.fxml";
        else
            destination = "RoundRobinTable.fxml";
        try {
            root = FXMLLoader.load(getClass().getResource(destination));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    
}
