package com.example.finaltour;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EliminationTeamController implements Initializable {

    @FXML
    private TableColumn<Team, String> tName;

    @FXML
    private TableView<Team> table;
    private Stage stage;
    private Scene scene;
    private Parent root;

    static Team selectedTeam;



    @FXML
    void toTeamDetails(ActionEvent event) {
        selectedTeam = table.getSelectionModel().getSelectedItem();
        if(!selectedTeam.getTournament().getIsIndividual()){
            try {
                root = FXMLLoader.load(getClass().getResource("teamPlayers.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            teamPlayerController.playerNoob = selectedTeam.getMembers().get(0);
            try {
                root = FXMLLoader.load(getClass().getResource("StudentProfile.fxml"));
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
    void toTournament(ActionEvent event) {
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

    ArrayList<Team> teams = TournamentPageContoller.selectedTournament.getTeams();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(teams);
        tName.setCellValueFactory(new PropertyValueFactory<Team,String>("name"));
        table.setItems(FXCollections.observableArrayList(teams));

    }
}
