package GameApp.java.controllers;

import GameApp.java.controllers.interfaces.AssignServiceDependency;
import GameApp.java.controllers.interfaces.IControllerCommunication;
import GameApp.java.general.AlertMessage;
import GameApp.java.general.CostFormatter;
import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.adaptors.ConsoleViewAdapter;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import GameApp.java.services.interfaces.IConsoleService;
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

public class FXMLEditConsoleController implements Initializable, IControllerCommunication, AssignServiceDependency {
    private Router router = new Router();
    private IConsoleService cs;

    @FXML
    private TextField id, description, cost, bitDepth, formFactor;
    @FXML
    private CheckBox rented, repair;
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException{//method for changing routes to the show consoles page
        router.changeRoute(RouteNames.SHOW_CONSOLES, event);
    }
    @FXML
    private void handleSubmitAction(ActionEvent event){//method for updating a customer and changing routes to the show consoles page
        String id = this.id.getText();
        String description = this.description.getText();
        String cost = this.cost.getText();
        String bitDepth = this.bitDepth.getText();
        String formFactor = this.formFactor.getText();
        boolean isRented = rented.isSelected();
        boolean beingRepaired = repair.isSelected();
        try{
            cs.editConsole(id, description, cost, bitDepth, formFactor, isRented, beingRepaired);
            router.changeRoute(RouteNames.SHOW_CONSOLES, event);
        }
        catch(Exception e){
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, e.getMessage());
        }
    }

    @Override
    public void passID(String id) {//method for passing an objects id from the previous page
        try{
            ConsoleViewAdapter.getConsoleDetails(cs.getConsoleByID(id), this);
        }
        catch(DoesNotExistException dne){//exception should never be seen, here for completeness
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, dne.getMessage());
        }
    }

    @Override
    public void detailsToEdit(Object... args) {
        this.id.setText((String) args[0]);
        this.description.setText((String) args[1]);
        this.cost.setText(CostFormatter.format((Double) args[2]));
        this.bitDepth.setText((String) args[3]);
        this.formFactor.setText((String) args[4]);
        this.rented.setSelected((Boolean) args[5]);
        this.repair.setSelected((Boolean) args[6]);
        setRepairedVisibility();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void setRepairedVisibility(){//set the visibility of the repair checkbox so you can't set a product to being repaired when it's on loan
        if(rented.isSelected()){
            repair.setVisible(false);
        }
    }

    @Override
    public void setDependency(IService service) {
        cs = (IConsoleService) service;
    }
}
