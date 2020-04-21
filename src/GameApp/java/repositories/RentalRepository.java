package GameApp.java.repositories;

import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.Rental;
import GameApp.java.services.SessionService;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

//simulates a DB communicating with a public object - session
//Acts like the middle man, retrieves all Rental objects stored in the database, and can process arguments given
// to it's methods and communicating between the back end (Make-shift DB) and the front end
public class RentalRepository {
    public static ArrayList<Rental> getAllRentals(){
        return SessionService.getRentals();
    }

    public static void addRental(Rental rental){
        if(!getAllRentals().contains(rental)){
            getAllRentals().add(rental);
        }
    }

    public static void returnRentalById(String id){
        try{
            for(Rental r : getAllRentals()){
                if(r.getId().equals(id)){
                    getAllRentals().remove(r);
                    r.returnRental();
                }
            }
        }
        catch(ConcurrentModificationException ignored){
        }
    }

    public static Rental getRentalFromCustomerId(String cID) throws DoesNotExistException{
        for(Rental r: getAllRentals()) {
            if (r.getCustomer().getId().equals(cID)) {
                return r;
            }
        }
        throw new DoesNotExistException("Sorry, a rental");
    }

    public static boolean customerHasExistingRental(String id){
        for(Rental r: getAllRentals()){
            if(r.getCustomer().getId().equals(id)){
                return true;
            }
        }
        return false;
    }
}
