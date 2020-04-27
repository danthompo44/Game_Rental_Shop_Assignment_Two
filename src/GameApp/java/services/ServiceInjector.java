package GameApp.java.services;

import GameApp.java.routers.RouteNames;
import GameApp.java.services.interfaces.AssignFourServiceDependencies;
import GameApp.java.services.interfaces.AssignServiceDependency;
import GameApp.java.services.interfaces.AssignThreeServiceDependencies;
import GameApp.java.services.interfaces.AssignTwoServiceDependencies;
import javafx.fxml.FXMLLoader;

public class ServiceInjector {
    public static void assignDependency(FXMLLoader fxmlLoader, RouteNames route){
        AssignServiceDependency sd;
        AssignTwoServiceDependencies asd;
        AssignThreeServiceDependencies atd;
        AssignFourServiceDependencies afd;
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
                afd = fxmlLoader.getController();
                afd.setDependency(new CustomerService());
                afd.setSecondaryDependency(new GameService());
                afd.setTertiaryDependency(new ConsoleService());
                afd.setFourthDependency(new RentalService());
        }
    }
    public static void assignCustomerHomePageDependencies(AssignTwoServiceDependencies sd){
        sd.setDependency(new GameService());
        sd.setSecondaryDependency(new ConsoleService());
    }
}
