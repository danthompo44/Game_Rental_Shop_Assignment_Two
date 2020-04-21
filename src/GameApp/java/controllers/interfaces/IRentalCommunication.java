package GameApp.java.controllers.interfaces;

import java.util.ArrayList;

public interface IRentalCommunication extends IControllerCommunication {
    void rentalDetailsToEdit(String fullname, String rentalId, String returnDate, double cost, ArrayList products);
}
