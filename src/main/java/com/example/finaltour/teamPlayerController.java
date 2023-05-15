package com.example.finaltour;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    void toPlayerProfile(ActionEvent event) {

    }

    @FXML
    void toTeamList(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        id.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));

    }
}
