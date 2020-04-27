package GameApp.java.controllers;

import GameApp.java.controllers.interfaces.IRentalCommunication;
import GameApp.java.general.AlertMessage;
import GameApp.java.general.CostFormatter;
import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.adaptors.RentalViewAdapter;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import GameApp.java.services.interfaces.AssignServiceDependency;
import GameApp.java.services.interfaces.IRentalService;
import GameApp.java.services.interfaces.IService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLShowRentalController implements Initializable, IRentalCommunication, AssignServiceDependency {
    private Router router = new Router();
    private IRentalService rs;

    @FXML
    private TextField customerName, rentalID, totalCost, returnDate;
    @FXML
    private CheckBox returnRentalCheckbox;
    @FXML
    private ListView rentalItems;
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException{
        router.changeRoute(RouteNames.COLLEAGUE_HOME, event);
    }
    @FXML
    private void handleConfirmAction(ActionEvent event) throws IOException{
        if(returnRentalCheckbox.isSelected()){
            rs.returnRentalById(rentalID.getText());
        }
        router.changeRoute(RouteNames.COLLEAGUE_HOME, event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void passID(String id) {
        try{
            RentalViewAdapter.getCustomerDetails(rs.getRentalObjectFromCustomerId(id), this);
        }
        catch(DoesNotExistException dne){
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, dne.getMessage());
        }

    }

    @Override
    public void rentalDetailsToEdit(String fullname, String rentalId, String returnDate, double cost, ArrayList products) {
        customerName.setText(fullname);
        this.rentalID.setText(rentalId);
        this.returnDate.setText(returnDate);
        this.totalCost.setText(CostFormatter.format(cost));
        ObservableList productList = FXCollections.observableArrayList(products);
        rentalItems.setItems(productList);
    }

    @Override
    public void setDependency(IService service) {
        rs = (IRentalService) service;
    }
}
