package GameApp;

import GameApp.java.services.SessionService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    //create a public object that anything in the app can access
    //this is done by using App.session from any class!!
    public static SessionService session = new SessionService();

    @Override
        public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/views/FXMLCustomerHomePage.fxml"));
        root.getStylesheets().add(getClass().getResource("resources/css/style.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setTitle("Retro Gaming App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
