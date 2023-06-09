package com.example.finaltour;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MyController implements Initializable {

    
    // to set up the button action
    private Stage logInStage;
    private Scene logInscene;
    private Parent logInroot;

    @FXML
    private Label welcomeMsg;

    @FXML
    private Button profileButton;

    public void switchToStudentProfilePage(ActionEvent event) {
        // teamPlayerController.playerNoob = 
        try {
            logInroot = FXMLLoader.load(getClass().getResource("StudentProfile.fxml"));
            logInStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            logInscene = new Scene(logInroot);
            logInStage.setScene(logInscene);
            logInStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void switchToLogInPage(ActionEvent event) {
        try {
            logInroot = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            logInStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            logInscene = new Scene(logInroot);
            logInStage.setScene(logInscene);
            logInStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button showDetailsBottun;

    public static Tournament selectedObjectTournament; // we save the object for tournament page.
    
    // this method to set the action event for the button show Details
    public void switchToTournamentPage(ActionEvent event) {
        try {
            // Create a new stage for the second window
            Stage secondStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TournamentPage.fxml"));
            Parent root = loader.load();
            secondStage.setResizable(false);

            TournamentPageContoller tournamentPageContoller = loader.getController();
            tournamentPageContoller.populatePage(selectedObjectTournament, secondStage); // to send the object to the tournament page

            // Set the scene for the second window
            Scene secondScene = new Scene(root);

            // Set the stage's scene to the second scene
            secondStage.setScene(secondScene);

            // Show the second window
            secondStage.show(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ListView<String> currentTournaments;

    @FXML
    private ListView<String> prevTournaments;

    @FXML
    private ListView<String> upcommingTournaments;

    @FXML
    private TextFlow textFlow;

    private String selectedTournamentName;

    
    Elimination e = new Elimination("fff",false,"fff",new Date(0000));




    ArrayList<String> currentTourNames = new ArrayList<>();
    ArrayList<String> prevTournamentsNames = new ArrayList<>();
    ArrayList<String> nextTournamentNames = new ArrayList<>();
    ArrayList<Tournament> currentTournamentT = new ArrayList<>();
    ArrayList<Tournament> prevTournamentT =  new ArrayList<>();
    ArrayList<Tournament> nexTournamentT = new ArrayList<>();

    ArrayList<Tournament> allTournaments = Main.tournaments;
    ///
    @FXML
    Button logoutButton = new Button();
    @FXML
    Button myButton = new Button();
    protected BooleanProperty isStu =  new SimpleBooleanProperty(false); 
    private BooleanProperty login = new SimpleBooleanProperty(Main.username.equals("Guest"));
    private BooleanProperty logout = new SimpleBooleanProperty(!Main.username.equals("Guest"));
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set the visibality of login button and logout button
        isStu.set(!Main.isAdmin && !Main.username.equals("Guest"));
        profileButton.visibleProperty().bind(isStu);
        myButton.visibleProperty().bind(login);
        logoutButton.visibleProperty().bind(logout);

        ///////
        welcomeMsg.setText("Welcome " + Main.username);
        for(Tournament tt: Main.tournaments){
            if(tt.getStatues().equals("Archived")){
                prevTournamentT.add(tt);
                prevTournamentsNames.add(tt.getName());
            }else if(tt.getStatues().equals("started")){
                currentTourNames.add(tt.getName());
                currentTournamentT.add(tt);
            }else{
                nexTournamentT.add(tt);
                nextTournamentNames.add(tt.getName());
            }
        }

        // populating the Lists
        currentTournaments.getItems().addAll(currentTourNames);
        prevTournaments.getItems().addAll(prevTournamentsNames);
        upcommingTournaments.getItems().addAll(nextTournamentNames);

        // this code listen for any change in the selected cell and change the lable below
        currentTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedObjectTournament = null;
                selectedTournamentName = currentTournaments.getSelectionModel().getSelectedItem(); // get the name of the selected tournament

                //search for the tournament and display its details
                for(int i = 0; i < currentTournamentT.size(); i++) {
                    if(currentTournamentT.get(i).getName().equals(selectedTournamentName)) {
                        Text text = new Text(currentTournamentT.get(i).getDetails());
                        textFlow.getChildren().clear();
                        textFlow.getChildren().add(text);
                        selectedObjectTournament = currentTournamentT.get(i);
                        showDetailsBottun.setVisible(true);
                        
                    }
                }
            }   
        });

    
        
        prevTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedTournamentName = prevTournaments.getSelectionModel().getSelectedItem();

                for(int i = 0; i < prevTournamentT.size(); i++) {
                    if(prevTournamentT.get(i).getName().equals(selectedTournamentName)) { // get the object givin the name of the tournament
                        Text text = new Text(prevTournamentT.get(i).getDetails());
                        textFlow.getChildren().clear();
                        textFlow.getChildren().add(text);
                        selectedObjectTournament = prevTournamentT.get(i); // this to get the tournament object and send to tournament Page
                        showDetailsBottun.setVisible(true);
                    }
                }
            }   
        });

        upcommingTournaments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                selectedTournamentName = upcommingTournaments.getSelectionModel().getSelectedItem();

                for(int i = 0; i < nexTournamentT.size(); i++) {
                    if(nexTournamentT.get(i).getName().equals(selectedTournamentName)) {
                        Text text = new Text(nexTournamentT.get(i).getDetails());
                        textFlow.getChildren().clear();
                        textFlow.getChildren().add(text);
                        selectedObjectTournament = nexTournamentT.get(i); // this to get the tournament object and send to tournament Page
                        showDetailsBottun.setVisible(true);
                    }
                }
            }   
        });

    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void toMatches(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("matchesByDate.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void toFilter(ActionEvent event) {
        Stage secondStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("filterPage.fxml"));
        // Parent root =  null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Scene secondScene = new Scene(root);

        // Set the stage's scene to the second scene
        secondStage.setScene(secondScene);

        // Show the second window
        secondStage.show();

    }


    public void logout(ActionEvent actionEvent) throws IOException {
        Main.username = "Guest";
        Main.isAdmin = false;
        //set sign in button to visible


        welcomeMsg.setText("Welcome " + Main.username);
        root = FXMLLoader.load(getClass().getResource("firstPage.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
