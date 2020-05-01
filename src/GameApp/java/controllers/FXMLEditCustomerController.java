package GameApp.java.controllers;

import GameApp.java.controllers.interfaces.AssignServiceDependency;
import GameApp.java.controllers.interfaces.IControllerCommunication;
import GameApp.java.general.AlertMessage;
import GameApp.java.general.exceptions.CustomerException;
import GameApp.java.models.adaptors.CustomerViewAdapter;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import GameApp.java.services.interfaces.ICustomerService;
import GameApp.java.services.interfaces.IService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLEditCustomerController implements Initializable, IControllerCommunication, AssignServiceDependency {
    private Router router = new Router();
    private ICustomerService cs;
    @FXML
    private TextField id, firstName, surname, address;
    @FXML
    private void handleSubmitAction(ActionEvent event) throws IOException{//method for editing the customers details, using the customer vaildator to check fields aren't empty
        try{
            String [] details = getInputDetails();
            cs.editCustomer(details[0], details[1], details[2], details[3]);
            router.changeRoute(RouteNames.COLLEAGUE_HOME, event);
        }
        catch(CustomerException ce){//display an alert box if the input fields are empty
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, ce.getMessage());
        }
    }
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException{
        router.changeRoute(RouteNames.COLLEAGUE_HOME, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void passID(String id) {//method for allowing the passing of an objects id from the previous page
        CustomerViewAdapter.getCustomerDetails(cs.getCustomerById(id), this);
    }

    @Override
    public void detailsToEdit(Object... args) {
        this.id.setText((String) args[0]);
        this.firstName.setText((String) args[1]);
        this.surname.setText((String) args[2]);
        this.address.setText((String) args[3]);
    }

    private String [] getInputDetails(){//retrieve input details from the views input fields
        String customerId = id.getText();
        String customerFirstname = firstName.getText();
        String customerSurname = surname.getText();
        String customerAddress = address.getText();

        return new String[]{customerId, customerFirstname, customerSurname, customerAddress};
    }

    @Override
    public void setDependency(IService service) {
        cs = (ICustomerService) service;
    }
}
