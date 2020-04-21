package GameApp.java.controllers.interfaces;

public interface ICustomerCommunication extends IControllerCommunication {
    void customerDetailsToEdit(String id, String firstName, String surname, String address);
}
