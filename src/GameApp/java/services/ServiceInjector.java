package GameApp.java.services;

import GameApp.java.controllers.interfaces.AssignServiceDependencies;
import GameApp.java.routers.RouteNames;
import javafx.fxml.FXMLLoader;

public class ServiceInjector {
    public static void assignDependency(FXMLLoader fxmlLoader, RouteNames route){
        AssignServiceDependencies sd;
        switch(route){
            case ADD_CUSTOMER:
            case EDIT_CUSTOMER:
                sd = fxmlLoader.getController();
                sd.setDependencies(new CustomerService());
                break;
            case SHOW_RENTAL:
                sd = fxmlLoader.getController();
                sd.setDependencies(new RentalService());
                break;
            case EDIT_GAME:
            case SHOW_GAMES:
                sd = fxmlLoader.getController();
                sd.setDependencies(new GameService());
                break;
            case EDIT_CONSOLE:
            case SHOW_CONSOLES:
                sd = fxmlLoader.getController();
                sd.setDependencies(new ConsoleService());
                break;
            case COLLEAGUE_HOME:
                sd = fxmlLoader.getController();
                sd.setDependencies(new CustomerService(), new GameService(), new RentalService());
                break;
            case CREATE_RENTAL:
                sd = fxmlLoader.getController();
                sd.setDependencies(new CustomerService(), new GameService(), new ConsoleService(), new RentalService(), new ProductBasketService());
                break;
        }
    }
    public static void assignCustomerHomePageDependencies(AssignServiceDependencies sd){
        sd.setDependencies(new GameService(), new ConsoleService());
    }
}
