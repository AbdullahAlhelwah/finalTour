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

    @FXML
    ListView<String> currentTournaments = new ListView<>();

    @FXML
    ListView<String> prevTournaments = new ListView<>();

    @FXML
    ListView<String> upcomingTournaments = new ListView<>();

    @FXML
    TextFlow textFlow;

    @FXML

    // this array will be used to populate the ListView.

    String[] currentTournament = new String[Main.tournaments.size()];

    {
        for(int i = 0; i < Main.tournaments.size(); i++) {
            currentTournament[i] = Main.tournaments.get(i).getName();
        }
    }

    // this array is to display the details of a tournament.
    Tournament[] currentTournamentT = new Tournament[Main.tournaments.size()];
    {
        for(int i = 0; i < Main.tournaments.size(); i++) {
            currentTournamentT[i] = Main.tournaments.get(i);
        }
    }

    String selectedTournament;

    // to set up the button action
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // populating the Lists
        currentTournaments.getItems().addAll(currentTournament);
        prevTournaments.getItems().addAll(currentTournament);
        upcomingTournaments.getItems().addAll(currentTournament);

        // this code listen for any change in the selected cell and change the label below
        displayInfo(currentTournaments);


        displayInfo(prevTournaments);

        displayInfo(upcomingTournaments);

    }

    private void displayInfo(ListView<String> currentTournaments) {
        currentTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedTournament = currentTournaments.getSelectionModel().getSelectedItem(); // get the name of the selected tournament

                //search for the tournament and display its details
                for(int i = 0; i < currentTournamentT.length; i++) {
                    if(currentTournamentT[i].getName().equals(selectedTournament)) {
                        String details = currentTournamentT[i].getDetails();
                        Text text = new Text(details);
                        textFlow.getChildren().clear();
                        textFlow.getChildren().add(text);
                    }
                }
            }
        });
    }



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

    //a method to add tournament
    @FXML
    private javafx.scene.control.TextField nameField;
    @FXML
    private javafx.scene.control.ComboBox typeBox;
    @FXML
    private javafx.scene.control.ComboBox isIndividualBox;
    @FXML
    private javafx.scene.control.ComboBox sportBox;
    @FXML
    private javafx.scene.control.DatePicker startDatePicker;
    @FXML
    private javafx.scene.control.DatePicker endDatePicker;

    public void add(ActionEvent actionEvent) {
        //get the type of the tournament
        String type = (String) typeBox.getValue();
        //get the name of the tournament
        String name = nameField.getText();
        //get the sport of the tournament
        String sport = (String) sportBox.getValue();
        //get the start date of the tournament
        LocalDate start = startDatePicker.getValue();
        //get the end date of the tournament
        LocalDate end = endDatePicker.getValue();
        //get the type of the tournament
        String isIndividual = (String) isIndividualBox.getValue();
        //check if there is any empty field (except for the end date)
        if (type == null || name.isEmpty() || sport == null || start == null || isIndividual == null || end == null) {
            //show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please fill all required fields.");
            alert.showAndWait();
            return;
        }
        //add the tournament
        if(type.equals("Elimination")){
            //create the tournament
            Date startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Tournament tournament;
            if(isIndividual.equals("Individual"))
                tournament = new Elimination(name, true, sport, startDate, endDate);
            else
                tournament = new Elimination(name, false, sport, startDate, endDate);
            //add the tournament to the list of tournaments
            Main.tournaments.add(tournament);
            //show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Tournament added successfully");
            alert.setContentText("The tournament has been added successfully.");
            alert.showAndWait();
            //go back to the first page
            try {
                Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("Round Robin")) {
            // create the tournament
            Date startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
            RoundRobin tournament;
            if (isIndividual.equals("Individual"))
                tournament = new RoundRobin(name, true, sport, startDate);
            else
                tournament = new RoundRobin(name,false, sport, startDate);

            // add the tournament to the list of tournaments, make it round robin type
            Main.tournaments.add(tournament);

            // show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Tournament added successfully");
            alert.setContentText("The tournament has been added successfully.");
            alert.showAndWait();

            // go back to the admin page
            try {
                Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





}