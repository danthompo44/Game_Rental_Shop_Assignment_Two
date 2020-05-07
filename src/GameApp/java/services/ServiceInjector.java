package GameApp.java.services;

import GameApp.java.controllers.interfaces.AssignMultipleDependencies;
import GameApp.java.routers.RouteNames;
import GameApp.java.services.interfaces.IService;

import java.util.ArrayList;

public class ServiceInjector {
    public static IService createDependency(RouteNames route){
        switch(route){
            case ADD_CUSTOMER:
            case EDIT_CUSTOMER:
                return new CustomerService();
            case SHOW_RENTAL:
            case ALL_RENTALS:
                return new RentalService();
            case EDIT_GAME:
            case SHOW_GAMES:
                return new GameService();
            case EDIT_CONSOLE:
            case SHOW_CONSOLES:
                return new ConsoleService();
        }
        return null;
    }
    public static ArrayList<IService> createMultipleServices(RouteNames route){
        ArrayList<IService> services = new ArrayList<>();
        switch(route){
            case COLLEAGUE_HOME:
                services.add(new CustomerService());
                services.add(new GameService());
                services.add(new RentalService());
                break;
            case CREATE_RENTAL:
                services.add(new CustomerService());
                services.add(new GameService());
                services.add(new ConsoleService());
                services.add(new RentalService());
                services.add(new ProductBasketService());
        }
        return services;
    }
    public static void assignCustomerHomePageDependencies(AssignMultipleDependencies control){
        ArrayList<IService> services = new ArrayList<>();
        services.add(new GameService());
        services.add(new ConsoleService());

        control.setDependencies(services);
    }
    public static boolean requiresMultipleServices(RouteNames route){
        return route == RouteNames.COLLEAGUE_HOME || route == RouteNames.CREATE_RENTAL;
    }
}
