package GameApp.java.controllers;

import GameApp.java.controllers.interfaces.AssignServiceDependency;
import GameApp.java.general.AlertMessage;
import GameApp.java.models.adaptors.RentalViewAdapter;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import GameApp.java.services.interfaces.IRentalService;
import GameApp.java.services.interfaces.IService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLShowAllRentalsController implements Initializable, AssignServiceDependency {
    private Router router = new Router();
    private IRentalService rs;

    @FXML
    ListView allRentals;

    public void handleBackAction(ActionEvent event) throws IOException{
        router.changeRoute(RouteNames.COLLEAGUE_HOME, event);
    }

    public void handleReturnRental(ActionEvent event){
        if(allRentals.getSelectionModel().getSelectedIndex()!=-1){
            if(AlertMessage.showConfirmationMessage("Are you sure you want to return this rental?")){
                rs.returnRentalById(RentalViewAdapter.getRentalId(allRentals));
                displayRentals();
            }
        }
        else{
            AlertMessage.showMessage(Alert.AlertType.INFORMATION,"Please select a Rental that you would like to return!");
        }
    }

    @Override
    public void setDependency(IService service) {
        rs = (IRentalService) service;
        displayRentals();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void displayRentals(){
        ObservableList rentals = FXCollections.observableArrayList(rs.getAll());
        allRentals.setItems(rentals);
    }
}
