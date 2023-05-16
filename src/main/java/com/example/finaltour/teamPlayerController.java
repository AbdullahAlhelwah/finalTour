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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class teamPlayerController implements Initializable {

    @FXML
    private TableColumn<Student, String> id;

    @FXML
    private TableColumn<Student, String> name;

    @FXML
    private TableView<Student> table;

    @FXML
    private Label teamName;

    ArrayList<Student> players = EliminationTeamController.selectedTeam.getMembers();

    private Stage stage;
    private Scene scene;
    private Parent root;
    public static Student playerNoob;

    @FXML
    void toPlayerProfile(ActionEvent event) {
        playerNoob = table.getSelectionModel().getSelectedItem();
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

    @FXML
    void toTeamList(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("EliminationTeam.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teamName.setText(EliminationTeamController.selectedTeam.getName());
        name.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        id.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));

        table.setItems(FXCollections.observableArrayList(players));

    }
}
