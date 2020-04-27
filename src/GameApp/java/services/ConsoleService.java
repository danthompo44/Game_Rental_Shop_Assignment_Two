package GameApp.java.services;

import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.Console;
import GameApp.java.models.validators.ProductValidator;
import GameApp.java.repositories.ConsoleRepository;
import GameApp.java.services.interfaces.IConsoleService;

import java.util.ArrayList;

//responsible for creating Console objects from arguments given to it by the front end
//It then uses these arguments to create Console objects and then passes them to the Data Repository
//which is responsible for checking that they exist or are being repaired etc...
public class ConsoleService implements IConsoleService {
    public ArrayList<Console> allConsoles() {
        return ConsoleRepository.getAllConsoles();
    }

    public ArrayList<Console> availableConsoles(){
        return ConsoleRepository.availableConsoles();
    }

    public void editConsole(Object... args) throws Exception{
        Console console = createConsoleFromParameters(args);
        ConsoleRepository.editConsole(console);
    }

    public void removeConsole(Console console){
        ConsoleRepository.removeConsole(console);
    }

    public ArrayList<Console> getBrokenConsoles(){
        return ConsoleRepository.getBrokenConsoles();
    }

    public ArrayList<Console> getLoanedConsoles(){
        return ConsoleRepository.getLoanedConsoles();
    }

    public Console getConsoleByID(String id) throws DoesNotExistException{
        return ConsoleRepository.getConsoleByID(id);
    }//if a console exist in the data repository it will return the console object

    private Console createConsoleFromParameters(Object... args) throws Exception{
        String id = (String) args[0];
        String description = (String) args[1];
        String cost = (String) args[2];
        String bitDepth = (String) args[3];
        String formFactor = (String) args[4];
        boolean isRented = (boolean) args[5];
        boolean beingRepaired = (boolean) args [6];
        ProductValidator.validate(description, cost);

        return new Console(id, description, Double.parseDouble(cost), bitDepth, formFactor, isRented, beingRepaired);
    }//returns a Console Object from the arguments passed to it
}
