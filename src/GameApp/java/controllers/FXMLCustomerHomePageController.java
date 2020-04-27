package GameApp.java.controllers;

import GameApp.java.general.AlertMessage;
import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.adaptors.ConsoleViewAdapter;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import GameApp.java.services.ConsoleService;
import GameApp.java.services.GameService;
import GameApp.java.services.interfaces.AssignServiceDependency;
import GameApp.java.services.interfaces.ICustomerService;
import GameApp.java.services.interfaces.IService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLCustomerHomePageController implements Initializable, AssignServiceDependency {
    private Router router = new Router();
    private ICustomerService cs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gamesByConsoleCheckbox.setSelected(true);
        populateAvailableConsoles();
        consolesOutput.getSelectionModel().selectFirst();
        showAvailableGames();
    }
    @FXML
    ListView consolesOutput;
    @FXML
    ListView gamesOutput;
    @FXML
    CheckBox gamesByConsoleCheckbox;
    @FXML
    Label consolesLabel, gamesLabel;
    @FXML
    private void handleSelectConsoleMouseClickAction(MouseEvent event){//method for available console depending on what console is selected
        showAvailableGames();
    }
    @FXML
    private void handleColleagueSignInAction(ActionEvent event) throws IOException{//method for changing routes to the colleague sign in page
        router.changeRoute(RouteNames.COLLEAGUE_SIGN_IN, event);
    }
    @FXML
    private void handleGamesByConsoleCheckboxAction(ActionEvent event){
        showAvailableGames();
    }

    private void populateAvailableConsoles() {//method for displaying available consoles in the console listview
        ObservableList consoles;
        consoles = FXCollections.observableArrayList(ConsoleService.availableConsoles());
        consolesOutput.setItems(consoles);
    }

    private void showAvailableGames(){
        if(gamesByConsoleCheckbox.isSelected()){
            ObservableList availableGamesByPlatform;
            try{
                availableGamesByPlatform = FXCollections.observableArrayList(GameService.getAvailableGamesByConsole(ConsoleService.getConsoleByID(ConsoleViewAdapter.getID(consolesOutput))));
                gamesOutput.setItems(availableGamesByPlatform);
                gamesLabel.setText("Games By Console");
            }
            catch(DoesNotExistException dne){//exception should never be seen, here for completeness
                AlertMessage.showMessage(Alert.AlertType.INFORMATION, dne.getMessage());
            }
        }
        else{
            ObservableList games;
            games = FXCollections.observableArrayList(GameService.availableGames());
            gamesOutput.setItems(games);
            gamesLabel.setText("All Available Games");
        }
        if(ConsoleService.availableConsoles().size()==0){
            consolesLabel.setText("Consoles: None Are Available");
        }
        if(GameService.availableGames().size()==0){
            gamesLabel.setText("Games: None Are Available");
        }
    }

    @Override
    public void setDependency(IService service) {
        cs = (ICustomerService) service;
    }
}
