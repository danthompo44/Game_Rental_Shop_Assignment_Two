package GameApp.java.controllers;

import GameApp.java.general.AlertMessage;
import GameApp.java.models.adaptors.ConsoleViewAdapter;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import GameApp.java.services.ConsoleService;
import GameApp.java.services.interfaces.AssignServiceDependency;
import GameApp.java.services.interfaces.IConsoleService;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLShowConsolesController implements Initializable, AssignServiceDependency {
    private Router router = new Router();
    private IConsoleService cs;

    @FXML
    private CheckBox repaired, loaned;
    @FXML
    private ListView consoles;
    @FXML
    private Label heading;
    @FXML
    private void handleEditAction(ActionEvent event) throws IOException{
        if(consoles.getSelectionModel().getSelectedIndex()!=-1){
            router.changeRouteWithDetails(RouteNames.EDIT_CONSOLE, event, ConsoleViewAdapter.getID(consoles));
        }
        else{
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Please select a Console to edit!");
        }
    }
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException{
        router.changeRoute(RouteNames.COLLEAGUE_HOME, event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private void handleRepairedCheckboxAction(ActionEvent event){
        if(loaned.isSelected()) {
            loaned.setSelected(false);
        }
        showProducts();
    }
    @FXML
    private void handleRentedCheckboxAction(ActionEvent event){
        if(repaired.isSelected()) {
            repaired.setSelected(false);
        }
        showProducts();
    }
    @FXML
    private void handleDeleteAction(ActionEvent event){
        if(consoles.getSelectionModel().getSelectedIndex()!=-1){
            if(!(ConsoleViewAdapter.getConsoleObject(consoles)).isRented()){
                if(AlertMessage.showConfirmationMessage("Are you sure you want to delete " + ConsoleViewAdapter.getConsoleDescription(consoles) +"?")){
                    cs.removeConsole(ConsoleViewAdapter.getConsoleObject(consoles));
                    showProducts();
                }
            }
            else{
                AlertMessage.showMessage(Alert.AlertType.INFORMATION, ConsoleViewAdapter.getConsoleDescription(consoles) + " is currently on loan and cannot be deleted!");
            }

        }
        else{
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Please Select A Console To Delete!");
        }
    }

    private void showProducts() {
        ObservableList fullConsoleList = FXCollections.observableArrayList(cs.allConsoles());
        ObservableList repairConsoleList = FXCollections.observableArrayList(cs.getBrokenConsoles());
        ObservableList loanedConsoleList = FXCollections.observableArrayList(cs.getLoanedConsoles());

        if (!repaired.isSelected() && !loaned.isSelected()) {
            heading.setText("All Consoles");
            consoles.setItems(fullConsoleList);
        }
        else if(repaired.isSelected()){
            heading.setText("Consoles Being Repaired");
            consoles.setItems(repairConsoleList);
        }
        else if(loaned.isSelected()){
            heading.setText("Consoles On Loan");
            consoles.setItems(loanedConsoleList);
        }
    }

    @Override
    public void setDependency(IService service) {
        cs = (IConsoleService) service;
        showProducts();
    }
}
