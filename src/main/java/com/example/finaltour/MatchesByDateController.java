package com.example.finaltour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class MatchesByDateController implements Initializable {

    @FXML
    private TableColumn<Match, String> TorName;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<Match, Date> matchDate;

    @FXML
    private TableView<Match> table;

    @FXML
    private TableColumn<Match, String> team1;

    @FXML
    private TableColumn<Match, String> team2;

    RoundRobin r = new RoundRobin("ddd",false,"fff",new Date());
    Team t1 = new Team(r,"ggg");
    Team t2 = new Team(r,"hhh");
    ArrayList<Match> aLLmatches = new ArrayList<>();
    ArrayList<Match> matches = new ArrayList<>();
    





    @FXML
    void findMatches(ActionEvent event) {
        
        
        matches.clear();

        // String dateString = "2023-05-15"; // The given date string  // selected date
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // try {
        //     Date date = dateFormat.parse(dateString);
        //     matches.add(new Match(r,t1,t2,date));
        //     System.out.println(date);
        // } catch (ParseException e) {
        //     System.out.println("Invalid date format");
        // }

        LocalDate date0 = datePicker.getValue();
        for (int i=0;i<aLLmatches.size();i++){
            LocalDate localDate = aLLmatches.get(i).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (localDate.equals(date0))
                matches.add(aLLmatches.get(i));
            }


        matchDate.setCellValueFactory(new PropertyValueFactory<Match,Date>("date"));
        team1.setCellValueFactory(new PropertyValueFactory<Match,String>("team1Name"));
        team2.setCellValueFactory(new PropertyValueFactory<Match,String>("team2Name"));
        TorName.setCellValueFactory(new PropertyValueFactory<Match,String>("tourName"));

        table.setItems(FXCollections.observableArrayList(matches));

    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void toHome(ActionEvent event) {
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // adding matches from all tournaments to arrayList
        for(Tournament t : Main.tournaments){
            for(Match m: t.matches){
                aLLmatches.add(m);
            }
        }
        matches.clear();


    }
}
