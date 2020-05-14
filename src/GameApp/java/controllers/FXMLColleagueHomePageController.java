package GameApp.java.controllers;

import GameApp.java.controllers.interfaces.AssignMultipleDependencies;
import GameApp.java.general.AlertMessage;
import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.general.exceptions.NotAvailableException;
import GameApp.java.models.adaptors.CustomerViewAdapter;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import GameApp.java.services.interfaces.ICustomerService;
import GameApp.java.services.interfaces.IGameService;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLColleagueHomePageController implements Initializable, AssignMultipleDependencies {
    private Router router = new Router();
    private ICustomerService cs;
    private IGameService gs;
    private IRentalService rs;
    @Override
    public void setDependencies(ArrayList<IService> services) {
        cs = (ICustomerService) services.get(0);
        gs = (IGameService) services.get(1);
        rs = (IRentalService) services.get(2);
        setup();
    }
    @FXML
    private void handleCreateRentalAction(ActionEvent event) throws IOException{//Method for creating a new rental
        try{
            gs.noGamesAreAvailable();
            if(customers.getSelectionModel().getSelectedIndex()!=-1){//checks if a customer is selected in the listview displayed
                if(!rs.customerHasExistingRental(CustomerViewAdapter.getID(customers))){//checks if a customer already has a rental
                    this.router.changeRouteWithDetails(RouteNames.CREATE_RENTAL, event,
                            CustomerViewAdapter.getID(customers));
                }
                else{//displays message if customer already has a rental
                    AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Customer already has a rental!");
                }
            }
            else{//displays message if a customer hasn't been selected
                AlertMessage.showMessage(Alert.AlertType.INFORMATION,
                        "Please select a customer to begin creating a rental!");
            }
        }
        catch (NotAvailableException nae){
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, nae.getMessage());
        }
    }
    @FXML
    private ListView customers;
    @FXML
    private void handleAddCustomersAction(ActionEvent event) throws IOException{//method for switching views to the add customer page
        router.changeRoute(RouteNames.ADD_CUSTOMER, event);
    }
    @FXML
    private void handleShowRentalAction(ActionEvent event) throws IOException{//method for showing rental details
        if(customers.getSelectionModel().getSelectedIndex()!=-1){//checks if a customer has been selected from the listview displayed
            try {//throws an exception if customer doesn't have a rental, changes route to show rental view
                rs.getRentalObjectFromCustomerId(CustomerViewAdapter.getID(customers));
                this.router.changeRouteWithDetails(RouteNames.SHOW_RENTAL, event, CustomerViewAdapter.getID(customers));
            }
            catch(DoesNotExistException dne){//displays message saying that customer doesn't have a rental
                AlertMessage.showMessage(Alert.AlertType.INFORMATION, dne.getMessage());
            }
        }
        else{//displays an error message if a customer hasn't been selected from the listview on screen
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Please Select A Customer To Be Able To View Their Rental!");
        }
    }
    @FXML
    private void handleShowGamesAction(ActionEvent event) throws IOException{//changes route to show games view
        router.changeRoute(RouteNames.SHOW_GAMES, event);
    }
    @FXML
    private void handleShowConsolesAction(ActionEvent event) throws IOException{//changes route to show consoles page
        router.changeRoute(RouteNames.SHOW_CONSOLES, event);
    }
    @FXML
    private void handleShowAllRentalsAction(ActionEvent event) throws IOException{
        router.changeRoute(RouteNames.ALL_RENTALS, event);
    }
    @FXML
    private void handleSignOutAction(ActionEvent event) throws IOException{//changes route to customer home page
        router.changeRoute(RouteNames.CUSTOMER_HOME_PAGE, event);
    }
    @FXML
    private void handleEditCustomerAction(ActionEvent event) throws IOException{//method for changing route to edit customer page
        if(customers.getSelectionModel().getSelectedIndex()!=-1){//checks if a customer has been selected from the displayed list view
            this.router.changeRouteWithDetails(RouteNames.EDIT_CUSTOMER, event, CustomerViewAdapter.getID(customers));
        }
        else{//displays error message if customer hasn't been selected
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Please Select A Customer To Edit!");
        }
    }
    @FXML
    private void handleDeleteAction(ActionEvent event){//method for deleting a customer
        if(customers.getSelectionModel().getSelectedIndex()!=-1) {//checks if a customer has been selected from the displayed listview
            if(AlertMessage.showConfirmationMessage("Are you sure you want to delete " + CustomerViewAdapter.getCustomerFullName(customers) + "? Any associated rental will be returned!")){
                if(rs.customerHasExistingRental(CustomerViewAdapter.getID(customers))){
                    try{
                        rs.returnRentalById(rs.getRentalObjectFromCustomerId(CustomerViewAdapter.getID(customers)).getId());
                    }
                    catch(DoesNotExistException dne){
                        AlertMessage.showMessage(Alert.AlertType.INFORMATION, dne.getMessage());//exception if customer does not have rental
                    }
                }
                cs.removeCustomer(CustomerViewAdapter.getCustomer(customers));//uses customer view adaptor to retrieve customer object to be deleted
            }
            getCustomers();//calls method to display customers in listview
        }
        else{//displays error message if user hasn't selected a customer
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Please Select A Customer To Be Deleted!");
        }
    }
    private void getCustomers(){//displays all customers into the listview
        ObservableList customer = FXCollections.observableArrayList(cs.getAll());
        customers.setItems(customer);
    }

    private void setup(){
        getCustomers();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
