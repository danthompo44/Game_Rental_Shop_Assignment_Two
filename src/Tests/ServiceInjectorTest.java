package Tests;

import GameApp.java.routers.RouteNames;
import GameApp.java.services.*;
import GameApp.java.services.interfaces.IService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class ServiceInjectorTest {
    @Test
    void correctServiceIsCreatedByTheCreateDependencyMethod(){
        Assertions.assertTrue(ServiceInjector.createDependency(RouteNames.ADD_CUSTOMER) instanceof CustomerService);
        Assertions.assertTrue(ServiceInjector.createDependency(RouteNames.EDIT_CUSTOMER) instanceof CustomerService);
        Assertions.assertTrue(ServiceInjector.createDependency(RouteNames.SHOW_RENTAL) instanceof RentalService);
        Assertions.assertTrue(ServiceInjector.createDependency(RouteNames.ALL_RENTALS) instanceof RentalService);
        Assertions.assertTrue(ServiceInjector.createDependency(RouteNames.EDIT_GAME) instanceof GameService);
        Assertions.assertTrue(ServiceInjector.createDependency(RouteNames.SHOW_GAMES) instanceof GameService);
        Assertions.assertTrue(ServiceInjector.createDependency(RouteNames.EDIT_CONSOLE) instanceof ConsoleService);
        Assertions.assertTrue(ServiceInjector.createDependency(RouteNames.SHOW_CONSOLES) instanceof ConsoleService);
    }
    @Test
    void correctServiceAreCreatedByTheCreateMultipleServicesMethod(){
        ArrayList<IService> services = ServiceInjector.createMultipleServices(RouteNames.COLLEAGUE_HOME);
        Assertions.assertTrue(services.get(0) instanceof CustomerService);
        Assertions.assertTrue(services.get(1) instanceof GameService);
        Assertions.assertTrue(services.get(2) instanceof RentalService);
        services = ServiceInjector.createMultipleServices(RouteNames.CREATE_RENTAL);
        Assertions.assertTrue(services.get(0) instanceof CustomerService);
        Assertions.assertTrue(services.get(1) instanceof GameService);
        Assertions.assertTrue(services.get(2) instanceof ConsoleService);
        Assertions.assertTrue(services.get(3) instanceof RentalService);
        Assertions.assertTrue(services.get(4) instanceof ProductBasketService);
    }
    @Test
    void requiresMultipleServicesReturnsCorrectBooleans(){
        Assertions.assertTrue(ServiceInjector.requiresMultipleServices(RouteNames.COLLEAGUE_HOME));
        Assertions.assertTrue(ServiceInjector.requiresMultipleServices(RouteNames.CREATE_RENTAL));
        Assertions.assertFalse(ServiceInjector.requiresMultipleServices(RouteNames.ADD_CUSTOMER));
        Assertions.assertFalse(ServiceInjector.requiresMultipleServices(RouteNames.EDIT_CUSTOMER));
        Assertions.assertFalse(ServiceInjector.requiresMultipleServices(RouteNames.SHOW_RENTAL));
        Assertions.assertFalse(ServiceInjector.requiresMultipleServices(RouteNames.ALL_RENTALS));
        Assertions.assertFalse(ServiceInjector.requiresMultipleServices(RouteNames.EDIT_GAME));
        Assertions.assertFalse(ServiceInjector.requiresMultipleServices(RouteNames.SHOW_GAMES));
        Assertions.assertFalse(ServiceInjector.requiresMultipleServices(RouteNames.EDIT_CONSOLE));
        Assertions.assertFalse(ServiceInjector.requiresMultipleServices(RouteNames.SHOW_CONSOLES));
        Assertions.assertFalse(ServiceInjector.requiresMultipleServices(RouteNames.CUSTOMER_HOME_PAGE));
    }
}
