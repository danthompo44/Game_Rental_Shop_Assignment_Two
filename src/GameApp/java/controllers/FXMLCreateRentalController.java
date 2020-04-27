package GameApp.java.controllers;

import GameApp.java.controllers.interfaces.ICustomerCommunication;
import GameApp.java.general.AlertMessage;
import GameApp.java.models.adaptors.*;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import GameApp.java.services.*;
import GameApp.java.services.interfaces.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLCreateRentalController implements Initializable, ICustomerCommunication, AssignTwoServiceDependencies {
    private Router router = new Router();
    private ICustomerService cs;
    private IGameService gs;

    @FXML
    TextField customerID, customerName, addressField;
    @FXML
    ListView consoles;
    @FXML
    ListView games;
    @FXML
    ListView basketView;
    @FXML
    CheckBox consoleCheckbox, showAllGamesCheckbox;
    @FXML
    Button submit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateAvailableConsoles();
        ProductBasketService.clearBasket();
    }

    @FXML
    private void handleSubmitAction(ActionEvent event) throws IOException{//method for creating a rental after checks are complete and changing routes to colleague home page
        if(!basketView.getItems().isEmpty()){//only runs if the basket has products within it
            RentalService.addRental(cs.getCustomerById(customerID.getText()), ProductBasketService.allBasketItems());
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, RentalViewAdapter.confirmationString(customerID.getText()));
            router.changeRoute(RouteNames.COLLEAGUE_HOME, event);
        }
        else{//displays message if basket is empty
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Basket is Empty!");
        }
    }
    @FXML
    private void handleShowAllGamesCheckboxAction(ActionEvent event){
        if(consoles.getSelectionModel().getSelectedIndex()!=-1){
            populateAvailableGamesByConsole(ConsoleViewAdapter.getID(consoles));
        }
        else if(!showAllGamesCheckbox.isSelected()){
            games.getItems().clear();
        }
        else{
            populateAllAvailableGames();
        }
    }

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException{//method for changing routes back to the colleague home page
        router.changeRoute(RouteNames.COLLEAGUE_HOME, event);
    }

    @FXML
    private void handleConsoleListViewAction(MouseEvent event) {//calls populate available games method when a new console is selected in the console listview
        populateAvailableGamesByConsole(ConsoleViewAdapter.getID(consoles));
    }

    @FXML
    private void handleAddToBasketAction(ActionEvent event){//method for adding a product to the basket listview
        if(consoles.getSelectionModel().getSelectedIndex()!=-1){//check if a console is selected
            if(consoleCheckbox.isSelected()) {
                try {//catch exception if product already exists in the basket or it's limit has been reached
                    ProductBasketService.addProduct(ConsoleViewAdapter.getConsoleObject(consoles));//add console to basket
                }
                catch(Exception e){
                    AlertMessage.showMessage(Alert.AlertType.INFORMATION, e.getMessage());
                }
            }
            if(games.getSelectionModel().getSelectedIndex()!=-1){//check if a game has been selected
                try {//catch exception if product already exists in the basket or it's limit has been reached
                    ProductBasketService.addProduct(GameViewAdapter.getGameObject(games));//add game to basket
                }
                catch(Exception e){
                    AlertMessage.showMessage(Alert.AlertType.INFORMATION, e.getMessage());
                }
            }
            else{
                AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Please select a Game to add to the basket!");
            }
        }
        else{//Alert customer to select a console before trying to add to the basket
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Please select a Console to view its games!");
        }
        consoleCheckbox.setSelected(false);//reset console checkbox to false
        showBasketItems();//refresh basket listview to show updated items
        consoleCheckbox.setVisible(!ProductBasketService.consoleLimitReached());//sets console checbox to visible or not depending if console limit is reached
//        populateAvailableGames(ConsoleViewAdapter.getID(consoles));
    }
    @FXML
    private void handleRemoveFromBasketAction(ActionEvent event){//method for removing product from the basket
        if(basketView.getSelectionModel().getSelectedIndex()!=-1){//checks if product is selected in the basket
            ProductBasketService.removeProduct(ProductViewAdapter.getProductObject(basketView));//remove product from basket using view adaptor to retrieve product from the listview
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, ProductViewAdapter.getProductDescription(basketView) + " has been removed from the basket!");//display message confirming removal
            showBasketItems();//refresh basket listview with updated items
            consoleCheckbox.setVisible(!ProductBasketService.consoleLimitReached());
        }
        else{//display message if no product has been selected
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "No items are in your Basket");
        }
    }

    private void populateAvailableConsoles() {//populates available console listview
        ObservableList consolesList;
        consolesList = FXCollections.observableArrayList(ConsoleService.availableConsoles());
        consoles.setItems(consolesList);
    }

    private void populateAvailableGamesByConsole(String id) {//populates games that are available on the selected console
        if(showAllGamesCheckbox.isSelected()){
            ObservableList gamesList;
            gamesList = FXCollections.observableArrayList(gs.availableGames());
            games.setItems(gamesList);
        }
        else{
            ObservableList gamesList;
            try{
                gamesList = FXCollections.observableArrayList(gs.getAvailableGamesByConsole(ConsoleService.getConsoleByID(id)));
                games.setItems(gamesList);
            }
            catch(Exception e){//exception should never be seen, here for completeness
                AlertMessage.showMessage(Alert.AlertType.INFORMATION, e.getMessage());
            }
        }
    }

    private void populateAllAvailableGames(){
        ObservableList gamesList;
        gamesList = FXCollections.observableArrayList(gs.availableGames());
        games.setItems(gamesList);
    }

    private void showBasketItems(){//displays basket items in the listview
        ObservableList basket;
        basket = FXCollections.observableArrayList(ProductBasketService.allBasketItems());
        basketView.setItems(basket);
    }

    @Override
    public void passID(String id) {//controller communication that allow the retrieval of the customer id from the colleague home page, passes this id to the customer details method
        CustomerViewAdapter.getCustomerDetails(cs.getCustomerById(id), this);
    }

    @Override
    public void customerDetailsToEdit(String id, String firstName, String surname, String address) {
        customerID.setText(id);
        customerName.setText(String.format("%s %s",firstName, surname));
        addressField.setText(address);
    }

    @Override
    public void setDependency(IService service) {
        cs = (ICustomerService) service;
    }

    @Override
    public void setSecondaryDependency(IService service) {
        gs = (IGameService) service;
    }
}
