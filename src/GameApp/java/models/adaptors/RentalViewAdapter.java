package GameApp.java.models.adaptors;

import GameApp.java.controllers.interfaces.IRentalCommunication;
import GameApp.java.general.CostFormatter;
import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.Rental;
import GameApp.java.services.RentalService;

public class RentalViewAdapter {
    private static String confirmationMessage;
    private static RentalService rs = new RentalService();

    public static String confirmationString(String id){
        confirmationMessage ="";
        try{
            Rental r = rs.getRentalObjectFromCustomerId(id);
            confirmationMessage += "Rental to be Returned on: " + r.getReturnDate();
            confirmationMessage += "\nTotal Cost: £" + CostFormatter.format(r.getTotalCost());
            return confirmationMessage;
        }
        catch(DoesNotExistException dne){
            return dne.getMessage();
        }
    }

    public static void getCustomerDetails(Rental r, IRentalCommunication rc){
        rc.rentalDetailsToEdit(r.getCustomer().getFullName(), r.getId(), r.getReturnDate(), r.getTotalCost(), r.getProducts());
    }
}
