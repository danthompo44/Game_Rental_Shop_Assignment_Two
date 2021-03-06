package GameApp.java.controllers;

import GameApp.java.controllers.interfaces.AssignServiceDependency;
import GameApp.java.controllers.interfaces.IControllerCommunication;
import GameApp.java.general.AlertMessage;
import GameApp.java.general.CostFormatter;
import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.adaptors.GameViewAdapter;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import GameApp.java.services.interfaces.IGameService;
import GameApp.java.services.interfaces.IService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLEditGameController implements Initializable, IControllerCommunication, AssignServiceDependency {
    private Router router = new Router();
    private IGameService gs;

    @FXML
    private TextField gameID, gameDescription, cost, platform, consoleID;
    @FXML
    private CheckBox loanedCheckbox, repairedCheckbox;
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException{//method for changing the route to the show games view
        router.changeRoute(RouteNames.SHOW_GAMES, event);
    }
    @FXML
    private void handleSubmitAction(ActionEvent event){//method for editing a game, takes input fields and passes them to the GameService.editGame method
        String id = gameID.getText();
        String description = gameDescription.getText();
        String gameCost = cost.getText();
        String consoleID = this.consoleID.getText();
        boolean loaned = loanedCheckbox.isSelected();
        boolean beingRepaired = repairedCheckbox.isSelected();
        try{
            gs.editGame(id, description, gameCost, consoleID, loaned, beingRepaired);
            router.changeRoute(RouteNames.SHOW_GAMES, event);
        }
        catch (Exception e){
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @Override
    public void passID(String id) {
        try{
            GameViewAdapter.getGameDetails(gs.getGameByID(id), this);
        }
        catch(DoesNotExistException dne){//Exception should never be seen, here for completeness
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, dne.getMessage());
        }

    }

    @Override
    public void detailsToEdit(Object... args) {
        gameID.setText((String) args[0]);
        gameDescription.setText((String) args[1]);
        this.cost.setText(CostFormatter.format((Double) args[2]));
        this.consoleID.setText((String) args[3]);
        this.platform.setText((String) args[4]);
        loanedCheckbox.setSelected((Boolean) args[5]);
        repairedCheckbox.setSelected((Boolean) args[6]);
        setRepairedCheckboxVisibility();
    }

    private void setRepairedCheckboxVisibility(){
        if(loanedCheckbox.isSelected()){
            repairedCheckbox.setVisible(false);
        }
    }

    @Override
    public void setDependency(IService service) {
        gs = (IGameService) service;
    }
}
