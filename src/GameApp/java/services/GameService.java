package GameApp.java.services;

import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.general.exceptions.NotAvailableException;
import GameApp.java.models.Console;
import GameApp.java.models.Game;
import GameApp.java.models.validators.ProductValidator;
import GameApp.java.repositories.GameRepository;
import GameApp.java.services.interfaces.IGameService;

import java.util.ArrayList;

//responsible for creating Game objects from arguments given to it by the front end
//It then uses these arguments to create Game objects and then passes them to the Data Repository
//which is responsible for checking that they exist or are being repaired etc...
public class GameService implements IGameService{
    public ArrayList<Game> allGames(){
        return GameRepository.getAllGames();
    }

    public ArrayList<Game> availableGames(){
        return GameRepository.availableGames();
    }

    public void editGame(Object... args) throws Exception{
        Game game = createGameFromParameters(args);
        GameRepository.editGame(game);
    }

    public void removeGame(Game game){
        GameRepository.removeGame(game);
    }

    public ArrayList<Game> getLoanedGames(){
        return GameRepository.getLoanedGames();
    }

    public ArrayList<Game> getBrokenGames(){
        return GameRepository.getBrokenGames();
    }

    public Game getGameByID(String id) throws DoesNotExistException {
        return GameRepository.getGameByID(id);
    }//if a game exist it will return the game object

    public ArrayList<Game> getAvailableGamesByConsole(Console console){
        return GameRepository.getAvailableGamesByPlatform(console);
    }

    public void noGamesAreAvailable() throws NotAvailableException{
        GameRepository.noGamesAreAvailable();
    }

    private Game createGameFromParameters(Object... args) throws Exception{
        String id = (String) args [0];
        String description = (String) args[1];
        String cost = (String) args[2];
        String consoleId = (String) args[3];
        Console console = ConsoleService.getConsoleByID(consoleId);
        boolean isRented = (boolean) args[4];
        boolean beingRepaired = (boolean) args [5];
        ProductValidator.validate(description, cost);
        return new Game(id, description, Double.parseDouble(cost), console, isRented, beingRepaired);
    }//returns a Game Object from the arguments passed to it
}
