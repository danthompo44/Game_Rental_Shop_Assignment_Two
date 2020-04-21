package GameApp.java.controllers;

import GameApp.java.general.AlertMessage;
import GameApp.java.models.adaptors.GameViewAdapter;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import GameApp.java.services.GameService;
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

public class FXMLShowGamesController implements Initializable {
    private Router router = new Router();
    @FXML
    private CheckBox repaired, loaned;
    @FXML
    private ListView games;
    @FXML
    private Label heading;
    @FXML
    private void handleEditAction(ActionEvent event) throws IOException {
        if(games.getSelectionModel().getSelectedIndex()!=-1){
            router.changeRouteWithGameDetails(RouteNames.EDIT_GAME, event, GameViewAdapter.getGameID(games));
        }
        else{
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Please select a Game to edit!");
        }
    }
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException{
        router.changeRoute(RouteNames.COLLEAGUE_HOME, event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showProducts();
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
        if(repaired.isSelected()){
        repaired.setSelected(false);
        }
        showProducts();
    }
    @FXML
    private void handleDeleteAction(ActionEvent event){
        if(games.getSelectionModel().getSelectedIndex()!=-1){
            if(!(GameViewAdapter.getGameObject(games)).isRented()){
                GameService.removeGame(GameViewAdapter.getGameObject(games));
                AlertMessage.showMessage(Alert.AlertType.INFORMATION, GameViewAdapter.getGameDescription(games) + " Has Been Deleted!");
                showProducts();
            }
            else{
                AlertMessage.showMessage(Alert.AlertType.INFORMATION, GameViewAdapter.getGameDescription(games) + " is currently on loan and cannot be deleted!");
            }
        }
        else{
            AlertMessage.showMessage(Alert.AlertType.INFORMATION,"Please Select A Game To Delete!");
        }
    }


    private void showProducts() {
        ObservableList fullGameList = FXCollections.observableArrayList(GameService.allGames());
        ObservableList repairGameList = FXCollections.observableArrayList(GameService.getBrokenGames());
        ObservableList loanedGameList = FXCollections.observableArrayList(GameService.getLoanedGames());

        if (!repaired.isSelected() && !loaned.isSelected()) {
            heading.setText("All Games");
            games.setItems(fullGameList);
        }
        else if(repaired.isSelected()){
            heading.setText("Games Being Repaired");
            games.setItems(repairGameList);
        }
        else if(loaned.isSelected()){
            heading.setText("Games On Loan");
            games.setItems(loanedGameList);
        }
    }
}







