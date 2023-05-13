package com.example.finaltour;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    Team team = new Team(r,"blgndu");
    Team team2 = new Team(r,"6666");




    ArrayList<Team> list = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tour_name.setText(r.getName());
        team_draws.setCellValueFactory(new PropertyValueFactory<Team,Integer>("draws"));
        team_losses.setCellValueFactory(new PropertyValueFactory<Team,Integer>("losses"));
        team_wins.setCellValueFactory(new PropertyValueFactory<Team,Integer>("wins"));
        team_points.setCellValueFactory(new PropertyValueFactory<Team,Integer>("points"));
        team_gd.setCellValueFactory(new PropertyValueFactory<Team,Integer>("goalDifference"));
        team_name.setCellValueFactory(new PropertyValueFactory<Team,String>("name"));
        team.addPoints(7);
        team.addGoalsReceived(3);
        team.addGoalsScored(15);
        team2.addPoints(9);
        team2.addGoalsReceived(3);
        team2.addGoalsScored(18);
        r.addTeam(team);
        r.addTeam(team2);
        list.add(team);
        list.add(team2);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        System.out.println(r.getTeams());

        FXCollections.sort(FXCollections.observableArrayList(r.getTeams()));
        System.out.println(r.getTeams());

        table.setItems(FXCollections.observableArrayList(r.getTeams()));


    }
}
