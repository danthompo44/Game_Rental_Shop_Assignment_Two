package GameApp.java.services;

import GameApp.java.routers.RouteNames;
import GameApp.java.services.interfaces.AssignServiceDependency;
import GameApp.java.services.interfaces.AssignTwoServiceDependencies;
import javafx.fxml.FXMLLoader;

public class ServiceInjector {
    public static void assignDependency(FXMLLoader fxmlLoader, RouteNames route){
        AssignServiceDependency sd;
        AssignTwoServiceDependencies asd;
        switch(route){
            case ADD_CUSTOMER:
            case EDIT_CUSTOMER:
                sd = fxmlLoader.getController();
                sd.setDependency(new CustomerService());
                break;
            case EDIT_GAME:
            case SHOW_GAMES:
                sd = fxmlLoader.getController();
                sd.setDependency(new GameService());
                break;
            case CREATE_RENTAL:
            case COLLEAGUE_HOME:
                asd = fxmlLoader.getController();
                asd.setDependency(new CustomerService());
                asd.setSecondaryDependency(new GameService());

        }
    }
    public static void assignCustomerHomePageDependencies(AssignTwoServiceDependencies sd){
        sd.setDependency(new GameService());
    }
}
