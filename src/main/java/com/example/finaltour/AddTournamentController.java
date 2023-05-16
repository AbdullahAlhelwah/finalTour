package com.example.finaltour;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddTournamentController {
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
        if (type == null || name.isEmpty() || sport == null || start == null || isIndividual == null) {
            //show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please fill all required fields.");
            alert.showAndWait();
            return;
        }
        // check if the start date is before the end date
        if (end != null && start.isAfter(end)) {
            //show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid date");
            alert.setContentText("The start date must be before the end date.");
            alert.showAndWait();
            return;
        }
            //create the tournament
            create(name, type, sport, start, end, isIndividual);
            //go back to the admin page
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
    
    //create tournament instance
    private void create(String name, String type, String sport, LocalDate start, LocalDate end, String isIndividual) {
        Date startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = null;
        if (end != null)
             endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Tournament tournament;
        
        if(type.equals("Elimination")){
            if(isIndividual.equals("Individual"))
                tournament = new Elimination(name, true, sport, startDate, endDate);
            else
                tournament = new Elimination(name, false, sport, startDate, endDate);
        } else {
            if (isIndividual.equals("Individual"))
                tournament = new RoundRobin(name, true, sport, startDate, endDate);
            else
                tournament = new RoundRobin(name, false, sport, startDate, endDate);
        }
        //check that the tournament is not already added (by name)
        for (Tournament t : Main.tournaments) {
            if (t.getName().equals(tournament.getName())) {
                //show an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Tournament already exists");
                alert.setContentText("A tournament with the same name already exists.");
                alert.showAndWait();
                return;
            }
        }
        //add the tournament to the list of tournaments
        Main.tournaments.add(tournament);
        // show a success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Tournament added successfully");
        alert.setContentText("The tournament has been added successfully.");
        alert.showAndWait();
        
        
    }
}
