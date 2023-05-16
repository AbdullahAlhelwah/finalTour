package com.example.finaltour;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.Node;


public class TournamentPageContoller implements Initializable {

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


    @FXML
    private Button generateButton;
    @FXML private Button closeRegButton;
    private BooleanProperty generate = new SimpleBooleanProperty(false);
    private BooleanProperty close = new SimpleBooleanProperty(false);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set the visibility of login button and logout button
        generateButton.visibleProperty().bind(generate);
        closeRegButton.visibleProperty().bind(close);
    }

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
        //set generate button
        generate.set(Main.isAdmin && !selectedTournament.getOpen() && selectedTournament.getTeams().size() == 0);
        close.set(Main.isAdmin && selectedTournament.getOpen());
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

        // if(!t.getOpen()) {
        //     statusLabel.setText("Closed");
        //     statusLabel.setTextFill(Color.RED);
        // } else {
        //     statusLabel.setText("Open");
        //     statusLabel.setTextFill(Color.GREEN);
        // }
        statusLabel.setText(t.getStatues());

        // get the teams participated in the tournament 
        String particpants = "";
        for(Team team : t.getTeams())
            if(team!=null)
            particpants += team.getName() + ", ";

        if (particpants.equals(""))
            particpants += "No participants";

        Text text = new Text(particpants);
        participantTextFlow.getChildren().add(text);  
    }
    @FXML
    void toStanding(ActionEvent event) {
        String destination;
        if (selectedTournament.getMatches().size()==0 && selectedTournament instanceof Elimination){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fail");
            alert.setHeaderText("Standing is not shown");
            alert.setContentText("Matches have not been generated yet.");
            alert.showAndWait();

        }
        else {
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
    @FXML
    void toTeams(ActionEvent event) {
        try {
                root = FXMLLoader.load(getClass().getResource("EliminationTeam.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }catch (IOException e) {
                e.printStackTrace();
            }
    }


    @FXML
    void toMatches(ActionEvent event) {

    }
    
}
