package com.example.finaltour;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class RoundRobinTableController implements Initializable {

    @FXML
    private TableView<Team> table;

    @FXML
    private TableColumn<Team, Integer> team_draws;

    @FXML
    private TableColumn<Team, Integer> team_losses;

    @FXML
    private TableColumn<Team, String> team_name;

    @FXML
    private TableColumn<Team, Integer> team_points;

    @FXML
    private TableColumn<Team, Integer> team_wins;

    @FXML
    private TableColumn<Team, Integer> team_gd;

    @FXML
    private Label tour_name;

    RoundRobin r = new RoundRobin("dd",false,"football",new Date(1111111));




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tour_name.setText(r.getName());
        team_draws.setCellValueFactory(new PropertyValueFactory<Team,Integer>("draws"));
        team_losses.setCellValueFactory(new PropertyValueFactory<Team,Integer>("losses"));
        team_wins.setCellValueFactory(new PropertyValueFactory<Team,Integer>("wins"));
        team_points.setCellValueFactory(new PropertyValueFactory<Team,Integer>("points"));
        team_gd.setCellValueFactory(new PropertyValueFactory<Team,Integer>("goalDifference"));
        team_name.setCellValueFactory(new PropertyValueFactory<Team,String>("name"));


        table.setItems(FXCollections.observableArrayList(r.getTeams()));


    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void ToHome(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("firstPage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
