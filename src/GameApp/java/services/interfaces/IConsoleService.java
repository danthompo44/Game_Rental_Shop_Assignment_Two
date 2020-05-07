package GameApp.java.services.interfaces;

import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.Console;

import java.util.ArrayList;

public interface IConsoleService extends IService{
    public ArrayList getAll();
    public ArrayList<Console> availableConsoles();
    public void editConsole(Object... args) throws Exception;
    public void removeConsole(Console console);
    public ArrayList<Console> getBrokenConsoles();
    public ArrayList<Console> getLoanedConsoles();
    public Console getConsoleByID(String id) throws DoesNotExistException;
}
