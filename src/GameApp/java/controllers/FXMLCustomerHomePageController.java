package GameApp.java.controllers;

import GameApp.java.controllers.interfaces.AssignMultipleDependencies;
import GameApp.java.general.AlertMessage;
import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.adaptors.ConsoleViewAdapter;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import GameApp.java.services.ServiceInjector;
import GameApp.java.services.interfaces.IConsoleService;
import GameApp.java.services.interfaces.IGameService;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLCustomerHomePageController implements Initializable, AssignMultipleDependencies {
    private Router router = new Router();
    private IGameService gs;
    private IConsoleService cs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceInjector.assignCustomerHomePageDependencies(this);
        populateAvailableConsoles();
        consolesOutput.getSelectionModel().selectFirst();
        showAvailableGames();
    }
    @FXML
    ListView consolesOutput;
    @FXML
    ListView gamesOutput;
    @FXML
    Label consolesLabel, gamesLabel;
    @FXML
    CheckBox checkbox;

    @FXML
    private void handleSelectConsoleMouseClickAction(MouseEvent event){//method for available console depending on what console is selected
        showAvailableGames();
    }
    @FXML
    private void handleColleagueSignInAction(ActionEvent event) throws IOException{//method for changing routes to the colleague sign in page
        router.changeRoute(RouteNames.COLLEAGUE_SIGN_IN, event);
    }
    @FXML
    private void handleCheckBoxAction(ActionEvent event){
        showAvailableGames();
    }

    private void populateAvailableConsoles() {//method for displaying available consoles in the console listview
        ObservableList consoles;
        consoles = FXCollections.observableArrayList(cs.availableConsoles());
        consolesOutput.setItems(consoles);
    }

    private void showAvailableGames(){
        ObservableList games;
        if(checkbox.isSelected()){
            try{
                games = FXCollections.observableArrayList(gs.getAvailableGamesByConsole(cs.getConsoleByID(ConsoleViewAdapter.getID(consolesOutput))));
                gamesOutput.setItems(games);
                gamesLabel.setText("Available Games For Selected Console");
            }
            catch(DoesNotExistException dne){//exception should never be seen, here for completeness
                AlertMessage.showMessage(Alert.AlertType.INFORMATION, dne.getMessage());
            }
        }
        else{
            games = FXCollections.observableArrayList(gs.availableGames());
            gamesOutput.setItems(games);
            gamesLabel.setText("All Available Games");
        }

    }

    @Override
    public void setDependencies(ArrayList<IService> services) {
        gs = (IGameService) services.get(0);
        cs = (IConsoleService) services.get(1);
    }
}
