package GameApp.java.controllers.interfaces;

public interface IGameCommunication extends IControllerCommunication {
    void gameDetailsToEdit(String id, String description, double cost, String consoleID, String platform, boolean isLoaned, boolean beingRepaired);
}
