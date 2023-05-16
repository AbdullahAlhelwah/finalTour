package com.example.finaltour;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RigesterInTournamentController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label participantNameLabel;

    @FXML
    private VBox textFieldsVbox;

    @FXML
    private TextField participantNameTextField;

    @FXML
    private HBox memberHbox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private VBox LabelsVbox;

    @FXML
    private Label membersLabel;

    @FXML
    private Button confirmButton;

    @FXML
    private Button addMembersButton;

    ArrayList<HBox> members = new ArrayList<>();

    Tournament selectedTournament;

    Team registeredTeam;
    Stage stage;

    public void cancelButton(ActionEvent event) {
        stage.close();
    }

    public void setSelectedTournament(Tournament t) {
        selectedTournament = t;
    }

    public void setStage(Stage s) {
        stage = s;
    }

    public void cotroller(Tournament t, Stage stage) {
        setSelectedTournament(t);
        setStage(stage);
        if (t.getIsIndividual()) {
            addMembersButton.setVisible(false);
            membersLabel.setText("Member");
            participantNameTextField.setVisible(false);
            participantNameLabel.setVisible(false);
        }
    }

    public void addNewTextField(ActionEvent event) {
        TextField name = new TextField();
        name.setPromptText("Name");

        TextField id = new TextField();
        id.setPromptText("ID");
        HBox member = new HBox();

        member.getChildren().addAll(name, id);
        member.setSpacing(5);
        members.add(member);
        textFieldsVbox.getChildren().add(member);
    }


    public void confirm(ActionEvent event) {
        if (selectedTournament.getIsIndividual()) {
            Student student = new Student(nameTextField.getText(), idTextField.getText());
            try {
                registeredTeam = new Team(selectedTournament, nameTextField.getText());
                registeredTeam.addStudent(student);
            } catch (Exception e) {
                e.printStackTrace();
            }

            selectedTournament.addTeam(registeredTeam);
            stage.close();

        } else { // if the tournament is not individule
            try {
                registeredTeam = new Team(selectedTournament, participantNameTextField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (HBox h : members) { // add each student to the team
                TextField nameTextField = (TextField) h.getChildren().get(0);
                String name = nameTextField.getText();

                TextField idTextField = (TextField) h.getChildren().get(0);
                String id = idTextField.getText();
                registeredTeam.getMembers().add(new Student(name, id));
            }
            // add the team to tournament
            selectedTournament.addTeam(registeredTeam);
            stage.close();

        }
    }

}
