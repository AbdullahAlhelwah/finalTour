package com.example.finaltour;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private VBox adminVbox;

    @FXML
    private TextField goals1;

    @FXML
    private TextField goals2;
    private BooleanProperty adminCond = new SimpleBooleanProperty(false);   

    @FXML
    void toTournament(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("TournamentPage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    ArrayList<Match> matches = TournamentPageContoller.selectedTournament.getMatches();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminCond.set(Main.isAdmin);
        adminVbox.visibleProperty().bind(adminCond);

        team1Name.setCellValueFactory(new PropertyValueFactory<Match,String>("team1Name"));
        team2Name.setCellValueFactory(new PropertyValueFactory<Match,String>("team2Name"));
        result.setCellValueFactory(new PropertyValueFactory<Match,String>("result"));
        date.setCellValueFactory(new PropertyValueFactory<Match,Date>("date"));
        round.setCellValueFactory(new PropertyValueFactory<Match,Integer>("round"));

        table.setItems(FXCollections.observableArrayList(matches));

    }
    @FXML
    void record(ActionEvent event) {
        Match m = table.getSelectionModel().getSelectedItem();
        try {
            if (m.getTeam1Name().equals("TBA")||m.getTeam2Name().equals("TBA"))
                throw new RuntimeException();
            int g1 = Integer.parseInt(goals1.getText());
            int g2 = Integer.parseInt(goals2.getText());

            m.recordScore(g1,g2);
            table.refresh();
        } catch (NumberFormatException n){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText("You can only enter integer");
            alert.showAndWait();

        } catch (RuntimeException r){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText("You can not record a match with TBA team");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText("You can not edit elimination match");
            alert.showAndWait();
        }


    }
}
