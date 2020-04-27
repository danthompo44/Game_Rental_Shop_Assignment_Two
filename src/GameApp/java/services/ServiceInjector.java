package GameApp.java.services;

import GameApp.java.routers.RouteNames;
import GameApp.java.services.interfaces.AssignServiceDependency;
import javafx.fxml.FXMLLoader;

public class ServiceInjector {
    public static void assignDependency(FXMLLoader fxmlLoader, RouteNames route){
        AssignServiceDependency sd = fxmlLoader.getController();
        switch(route){
            case ADD_CUSTOMER:
            case EDIT_CUSTOMER:
            case CUSTOMER_HOME_PAGE:
            case COLLEAGUE_HOME:
            case CREATE_RENTAL:
                sd.setDependency(new CustomerService());
        }
    }
}
