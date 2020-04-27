package GameApp.java.services;

import GameApp.java.routers.RouteNames;
import GameApp.java.services.interfaces.*;
import javafx.fxml.FXMLLoader;

public class ServiceInjector {
    public static void assignDependency(FXMLLoader fxmlLoader, RouteNames route){
        AssignServiceDependency sd;
        AssignThreeServiceDependencies atd;
        AssignFiveServiceDependencies fsd;
        switch(route){
            case ADD_CUSTOMER:
            case EDIT_CUSTOMER:
                sd = fxmlLoader.getController();
                sd.setDependency(new CustomerService());
                break;
            case SHOW_RENTAL:
                sd = fxmlLoader.getController();
                sd.setDependency(new RentalService());
                break;
            case EDIT_GAME:
            case SHOW_GAMES:
                sd = fxmlLoader.getController();
                sd.setDependency(new GameService());
                break;
            case EDIT_CONSOLE:
            case SHOW_CONSOLES:
                sd = fxmlLoader.getController();
                sd.setDependency(new ConsoleService());
                break;
            case COLLEAGUE_HOME:
                atd = fxmlLoader.getController();
                atd.setDependency(new CustomerService());
                atd.setSecondaryDependency(new GameService());
                atd.setTertiaryDependency(new RentalService());
                break;
            case CREATE_RENTAL:
                fsd = fxmlLoader.getController();
                fsd.setDependency(new CustomerService());
                fsd.setSecondaryDependency(new GameService());
                fsd.setTertiaryDependency(new ConsoleService());
                fsd.setFourthDependency(new RentalService());
                fsd.setFifthDependency(new ProductBasketService());
                break;
        }
    }
    public static void assignCustomerHomePageDependencies(AssignTwoServiceDependencies sd){
        sd.setDependency(new GameService());
        sd.setSecondaryDependency(new ConsoleService());
    }
}
