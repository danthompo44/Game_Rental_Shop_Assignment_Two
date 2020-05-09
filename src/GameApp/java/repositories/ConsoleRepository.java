package GameApp.java.repositories;

import GameApp.App;
import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.Console;

import java.util.ArrayList;

//simulates a DB communicating with a public object - session
//Acts like the middle man, retrieves all Consoles Objects stored in the database, and can process arguments given
// to it's methods and communicating between the back end (Make-shift DB) and the front end
public class ConsoleRepository{
    public static ArrayList<Console> getAllConsoles() {
        return App.session.getConsoles();
    }//returns all consoles within the SessionService class getConsoles()

    public static ArrayList<Console> availableConsoles() {
        ArrayList<Console> availableConsoles = new ArrayList<>();
        for (Console c : getAllConsoles()) {
            if (c.isAvailable()) {
                availableConsoles.add(c);
            }
        }
        return availableConsoles;
    }//uses the getAllConsoles() method to retrieve consoles then loops through them
    // checks if a console is either being rented or is being repaired,
    // if the statement returns false the game is added to the arraylist
    // and then returned from the method

    public static Console getConsoleByID(String id) throws DoesNotExistException{
        for (Console c : getAllConsoles()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        throw new DoesNotExistException("Console");
    }//Allows for the search of a console by it's ID, uses the getAllConsoles method to retrieve
    //an arraylist of consoles, loops through them and if the ID matches the ID passed to the method it will
    //return the Console object, else it will return null.

    public static void addConsole(Console console) {
        if (!getAllConsoles().contains(console)) {
            getAllConsoles().add(console);
        }
    }//Allows the addition of a Console object to the getAllConsoles() methods ArrayList

    public static void editConsole(Console console) {
        for (Console c : getAllConsoles()) {
            if (c.getId().equals(console.getId())) {
                c.setDescription(console.getDescription());
                c.setCost(console.getCost());
                c.setIsBeingRepaired(console.isBeingRepaired());
                c.setIs(console.is());
            }
        }
    }//method for editing a Console within the method getAllConsoles() ArrayList, if the Consoles ID matched that of the
    // Console Object passed to the method it will set the attributes of that object with the gets of the passed object

    public static void removeConsole(Console console){
        getAllConsoles().remove(console);
    }//method for removing a Console object from the getAllConsole() resultant array list.
    //Matching the id of the passed Console to one within the ArrayList

    public static ArrayList <Console> getLoanedConsoles(){
        ArrayList<Console> loanedConsoles = new ArrayList<>();
        for(Console c: getAllConsoles()){
            if(c.is()){
                loanedConsoles.add(c);
            }
        }
        return loanedConsoles;
    }

    public static ArrayList <Console> getBrokenConsoles(){
        ArrayList<Console> brokenConsoles = new ArrayList<>();
        for(Console c: getAllConsoles()){
            if(c.isBeingRepaired()){
                brokenConsoles.add(c);
            }
        }
        return brokenConsoles;
    }
}
