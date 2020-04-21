package GameApp.java.routers;

import GameApp.java.controllers.interfaces.IConsoleCommunication;
import GameApp.java.controllers.interfaces.ICustomerCommunication;
import GameApp.java.controllers.interfaces.IGameCommunication;
import GameApp.java.controllers.interfaces.IRentalCommunication;
import GameApp.java.general.ScreenHelp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Router {
    final String VIEW_PATH = "../../resources/views/";
    final String STYLE_PATH = "../../resources/css/style.css";

    //maps keys to a string which the changeRoute() method uses to change scenes. It does this by accessing the RouteNames enum
    //to force a particular selections and adds the resultant string from this key to a predefined string "VIEW_PATH" or "STYLE_PATH".

    private final Map<RouteNames, String> routesMap = new HashMap<>(){{
        put(RouteNames.ADD_CUSTOMER, VIEW_PATH+"FXMLAddCustomer.fxml");
        put(RouteNames.CUSTOMER_HOME_PAGE, VIEW_PATH+"FXMLCustomerHomePage.fxml");
        put(RouteNames.COLLEAGUE_SIGN_IN, VIEW_PATH+"FXMLColleagueSignIn.fxml");
        put(RouteNames.CREATE_RENTAL, VIEW_PATH+"FXMLCreateRental.fxml");
        put(RouteNames.COLLEAGUE_HOME, VIEW_PATH+"FXMLColleagueHomePage.fxml");
        put(RouteNames.SHOW_GAMES, VIEW_PATH+"FXMLShowGames.fxml");
        put(RouteNames.SHOW_CONSOLES, VIEW_PATH+ "FXMLShowConsoles.fxml");
        put(RouteNames.EDIT_CUSTOMER, VIEW_PATH+"FXMLEditCustomer.fxml");
        put(RouteNames.SHOW_RENTAL, VIEW_PATH+"FXMLShowRental.fxml");
        put(RouteNames.EDIT_GAME, VIEW_PATH+"FXMLEditGame.fxml");
        put(RouteNames.EDIT_CONSOLE, VIEW_PATH+"FXMLEditConsole.fxml");
    }};

    //Takes an action event and a RouteNames(enum) object, retrieves the path from the routesMap method above.
    //Then reloads this into the Scene and stage whilst applying the css styling.

    public final void changeRoute(RouteNames route, ActionEvent actionEvent) throws IOException {
        String sceneRoute = routesMap.get(route);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneRoute));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        root.getStylesheets().add(getClass().getResource(STYLE_PATH).toExternalForm());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

        ScreenHelp.centreScreen(stage);
    }

    public final void changeRouteWithCustomerDetails(RouteNames route, ActionEvent actionEvent, String id) throws IOException {
        String sceneRoute = routesMap.get(route);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneRoute));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        root.getStylesheets().add(getClass().getResource(STYLE_PATH).toExternalForm());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

        ICustomerCommunication controller = fxmlLoader.getController();
        controller.passID(id);

        ScreenHelp.centreScreen(stage);
    }

    public final void changeRouteWithConsoleDetails(RouteNames route, ActionEvent actionEvent, String id) throws IOException {
        String sceneRoute = routesMap.get(route);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneRoute));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        root.getStylesheets().add(getClass().getResource(STYLE_PATH).toExternalForm());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

        IConsoleCommunication controller = fxmlLoader.getController();
        controller.passID(id);

        ScreenHelp.centreScreen(stage);
    }

    public final void changeRouteWithGameDetails(RouteNames route, ActionEvent actionEvent, String id) throws IOException {
        String sceneRoute = routesMap.get(route);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneRoute));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        root.getStylesheets().add(getClass().getResource(STYLE_PATH).toExternalForm());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

        IGameCommunication controller = fxmlLoader.getController();
        controller.passID(id);

        ScreenHelp.centreScreen(stage);
    }

    public final void changeRouteWithRentalDetails(RouteNames route, ActionEvent actionEvent, String id) throws IOException {
        String sceneRoute = routesMap.get(route);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneRoute));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        root.getStylesheets().add(getClass().getResource(STYLE_PATH).toExternalForm());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

        IRentalCommunication controller = fxmlLoader.getController();
        controller.passID(id);

        ScreenHelp.centreScreen(stage);
    }
}
