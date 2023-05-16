package com.example.finaltour;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class matchesListController implements Initializable {

    @FXML
    private TableColumn<Match, Date> date;

    @FXML
    private TableColumn<Match, String> result;

    @FXML
    private TableColumn<Match, Integer> round;

    @FXML
    private TableView<Match> table;

    @FXML
    private TableColumn<Match, String> team1Name;

    @FXML
    private TableColumn<Match, String> team2Name;

    @FXML
    void toTournament(ActionEvent event) {

    }
    ArrayList<Match> matches = TournamentPageContoller.selectedTournament.getMatches();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        team1Name.setCellValueFactory(new PropertyValueFactory<Match,String>("team1Name"));
        team2Name.setCellValueFactory(new PropertyValueFactory<Match,String>("team2Name"));
        result.setCellValueFactory(new PropertyValueFactory<Match,String>("result"));
        date.setCellValueFactory(new PropertyValueFactory<Match,Date>("date"));
        round.setCellValueFactory(new PropertyValueFactory<Match,Integer>("round"));

        table.setItems(FXCollections.observableArrayList(matches));

    }
}
