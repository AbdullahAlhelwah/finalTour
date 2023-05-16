package com.example.finaltour;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class AdminController implements Initializable {


    // to set up the button action
    private Stage logInStage;
    private Scene logInscene;
    private Parent logInroot;


    @FXML
    private Button showDetailsBottun;

    public Tournament selectedObjectTournament; // we save the object for tournament page.

    // this method to set the action event for the button show Details
    public void switchToTournamentPage(ActionEvent event) {
        try {
            // Create a new stage for the second window
            Stage secondStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TournamentPage.fxml"));
            Parent root = loader.load();

            TournamentPageContoller tournamentPageContoller = loader.getController();
            tournamentPageContoller.populatePage(selectedObjectTournament, secondStage); // to send the object to the tournament page

            // Set the scene for the second window
            Scene secondScene = new Scene(root);

            // Set the stage's scene to the second scene
            secondStage.setScene(secondScene);

            // Show the second window
            secondStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ListView<String> currentTournaments;

    @FXML
    private ListView<String> prevTournaments;

    @FXML
    private ListView<String> upcommingTournaments;

    @FXML
    private TextFlow textFlow;

    private String selectedTournamentName;


    ArrayList<String> currentTourNames = new ArrayList<>();
    ArrayList<String> prevTournamentsNames = new ArrayList<>();
    ArrayList<String> nextTournamentNames = new ArrayList<>();
    ArrayList<Tournament> currentTournamentT = new ArrayList<>();
    ArrayList<Tournament> prevTournamentT =  new ArrayList<>();
    ArrayList<Tournament> nexTournamentT = new ArrayList<>();

    ArrayList<Tournament> allTournaments = Main.tournaments;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(Tournament tt: Main.tournaments){
            if(tt.getStatues().equals("Archived")){
                prevTournamentT.add(tt);
                prevTournamentsNames.add(tt.getName());
            }else if(tt.getStatues().equals("started")){
                currentTourNames.add(tt.getName());
                currentTournamentT.add(tt);
            }else{
                nexTournamentT.add(tt);
                nextTournamentNames.add(tt.getName());
            }
        }

        //showDetailsBottun.setVisible(false); // it is not visibale untill a tournament is selected.

        // populating the Lists
        currentTournaments.getItems().addAll(currentTourNames);
        prevTournaments.getItems().addAll(prevTournamentsNames);
        upcommingTournaments.getItems().addAll(nextTournamentNames);

        // this code listen for any change in the selected cell and change the lable below
        currentTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedTournamentName = currentTournaments.getSelectionModel().getSelectedItem(); // get the name of the selected tournament

                //search for the tournament and display its details
                for(int i = 0; i < currentTournamentT.size(); i++) {
                    if(currentTournamentT.get(i).getName().equals(selectedTournamentName)) {
                        Text text = new Text(currentTournamentT.get(i).getDetails());
                        textFlow.getChildren().clear();
                        textFlow.getChildren().add(text);
                        selectedObjectTournament = currentTournamentT.get(i);
                        showDetailsBottun.setVisible(true);

                    }
                }
            }
        });

        // "The name of the tournament is: " + currentTournamentT[i].getName() +
        //                 "\nIs Individual? " + currentTournamentT[i].getIsIndividual() +
        //                 "          The Sport: " + currentTournamentT[i].getSport() +
        //                 "\nHas Finished? " + currentTournamentT[i].getHasFinished()


        prevTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedTournamentName = prevTournaments.getSelectionModel().getSelectedItem();

                for(int i = 0; i < prevTournamentT.size(); i++) {
                    if(prevTournamentT.get(i).getName().equals(selectedTournamentName)) { // get the object givin the name of the tournament
                        Text text = new Text(prevTournamentT.get(i).getDetails());
                        textFlow.getChildren().clear();
                        textFlow.getChildren().add(text);
                        selectedObjectTournament = prevTournamentT.get(i); // this to get the tournament object and send to tournament Page
                        showDetailsBottun.setVisible(true);
                    }
                }
            }
        });

        upcommingTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedTournamentName = upcommingTournaments.getSelectionModel().getSelectedItem();

                for(int i = 0; i < nexTournamentT.size(); i++) {
                    if(nexTournamentT.get(i).getName().equals(selectedTournamentName)) {
                        Text text = new Text(nexTournamentT.get(i).getDetails());
                        textFlow.getChildren().clear();
                        textFlow.getChildren().add(text);
                        selectedObjectTournament = nexTournamentT.get(i); // this to get the tournament object and send to tournament Page
                        showDetailsBottun.setVisible(true);
                    }
                }
            }
        });

    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void toMatches(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("matchesByDate.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void toFilter(ActionEvent event) {
        Stage secondStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("filterPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Scene secondScene = new Scene(root);

        // Set the stage's scene to the second scene
        secondStage.setScene(secondScene);

        // Show the second window
        secondStage.show();

    }

    //add tournament
    @FXML
    public void addTournament(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("addTournament.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
