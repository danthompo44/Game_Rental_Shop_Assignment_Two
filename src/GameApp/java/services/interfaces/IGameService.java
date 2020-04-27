package GameApp.java.services.interfaces;

import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.general.exceptions.NotAvailableException;
import GameApp.java.models.Console;
import GameApp.java.models.Game;
import java.util.ArrayList;

public interface IGameService {
    public ArrayList<Game> allGames();
    public ArrayList<Game> availableGames();
    public void editGame(Object... args) throws Exception;
    public void removeGame(Game game);
    public ArrayList<Game> getLoanedGames();
    public ArrayList<Game> getBrokenGames();
    public Game getGameByID(String id) throws DoesNotExistException;
    public ArrayList<Game> getAvailableGamesByConsole(Console console);
    public  void noGamesAreAvailable() throws NotAvailableException;
}
