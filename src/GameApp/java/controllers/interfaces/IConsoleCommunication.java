package GameApp.java.controllers.interfaces;

public interface IConsoleCommunication extends IControllerCommunication {
    void consoleDetailsToEdit(String id, String description, double cost, String bitDepth, String formFactor,
    boolean isRented, boolean beingRepaired);
}


