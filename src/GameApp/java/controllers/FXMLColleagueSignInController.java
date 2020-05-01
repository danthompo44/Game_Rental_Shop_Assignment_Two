package GameApp.java.controllers;

import GameApp.java.general.CredentialsHelp;
import GameApp.java.routers.RouteNames;
import GameApp.java.routers.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLColleagueSignInController implements Initializable {
    private Router router = new Router();
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label incorrectDetailsLabel;
    @FXML
    private void handleSubmitAction(ActionEvent event) throws IOException{//method for checking credentials and changing route to the colleague home page
        if(CredentialsHelp.checkCredentials(username.getText(), password.getText())){//Check credentials
            router.changeRoute(RouteNames.COLLEAGUE_HOME, event);
        }
        else{
            incorrectDetailsLabel.setText("*Incorrect Details, Try Again!");
        }
    }
    @FXML
    private void handleBackAction(ActionEvent event) throws IOException{//method for changing route back to the customer home page
        router.changeRoute(RouteNames.CUSTOMER_HOME_PAGE, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
