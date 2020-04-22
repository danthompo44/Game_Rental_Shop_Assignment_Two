package GameApp.java.repositories;

import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.general.exceptions.NotAvailableException;
import GameApp.java.models.Console;
import GameApp.java.models.Game;
import GameApp.java.services.SessionService;

import java.util.ArrayList;

//simulates a DB communicating with a public object - session
//Acts like the middle man, retrieves all Game Objects stored in the database, and can process arguments given
// to it's methods and communicating between the back end (Make-shift DB) and the front end
public class GameRepository {
    public static ArrayList<Game> gamesByConsole = new ArrayList<>(); //ArrayList for adding games related to a console to
    public static ArrayList<Game> getAllGames() {
        return SessionService.getGames();
    }//returns all consoles within the SessionService class getGames()

    public static ArrayList<Game> availableGames() {
        ArrayList<Game> availableGames = new ArrayList<>();
        for (Game g : getAllGames()) {
            if (g.isAvailable()) {
                availableGames.add(g);
            }
        }
        return availableGames;
    }//uses the getAllGames() method to retrieve all games then loops through them
    // checks if a game is either being rented or is being repaired,
    // if the statement returns false the game is added to the arraylist
    // and then returned from the method

    public static Game getGameByID(String id) throws DoesNotExistException{
        for (Game g : getAllGames()) {
            if (g.getId().equals(id)) {
                return g;
            }
        }
        throw new DoesNotExistException("Game");
    }//Allows for the search of a game by it's ID, uses the getAllGames method to retrieve
    //an arraylist of games, loops through them and if the ID matches the ID passed to the method it will
    //return the Game object, else it will return null.

    public static ArrayList<Game> getAvailableGamesByPlatform(Console console){
        gamesByConsole.clear();
        for(Game g : availableGames()){
            if(console.equals(g.getConsole())){
                gamesByConsole.add(g);
            }
        }
        return gamesByConsole;
    }

    public static void editGame(Game game) {
        for (Game g : getAllGames()) {
            if (g.getId().equals(game.getId())) {
                g.setDescription(game.getDescription());
                g.setIsRented(game.isRented());
                g.setRentalCost(game.getRentalCost());
                g.setIsBeingRepaired(game.isBeingRepaired());
            }
        }
    }//method for editing a Game within the method getAllGames() ArrayList, if the Games ID matched that of the
    // game Object passed to the method it will set the attributes of that object with the gets of the passed object

    public static void removeGame(Game game){
        getAllGames().remove(game);
    }//method for removing a Game object from the getAllGames() resultant array list.
    //Matching the id of the passed Game to one within the ArrayList
    public static ArrayList <Game> getLoanedGames(){
        ArrayList<Game> loanedGames = new ArrayList<>();
        for(Game g: getAllGames()){
            if(g.isRented()){
                loanedGames.add(g);
            }
        }
        return loanedGames;
    }

    public static ArrayList <Game> getBrokenGames(){
        ArrayList<Game> brokenGames = new ArrayList<>();
        for(Game g: getAllGames()){
            if(g.isBeingRepaired()){
                brokenGames.add(g);
            }
        }
        return brokenGames;
    }

    public static void noGamesAreAvailable() throws NotAvailableException{
        if(availableGames().size()==0){
            throw new NotAvailableException("games");
        }
    }
}
