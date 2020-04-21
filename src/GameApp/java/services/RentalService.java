package GameApp.java.services;

import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.Customer;
import GameApp.java.models.IProduct;
import GameApp.java.models.Rental;
import GameApp.java.repositories.RentalRepository;

import java.util.ArrayList;

//responsible for creating Rental objects from arguments given to it by the front end
//It then uses these arguments to create Rental objects and then passes them to the Data Repository
//which is responsible for checking that they exist or are being repaired etc...
public class RentalService {
    public static ArrayList<Rental> allRentals(){
        return RentalRepository.getAllRentals();
    }

    public static void addRental(Object... args){
        Rental rental = createRentalFromParameters(args);
        RentalRepository.addRental(rental);
    }

    public static void returnRentalById(String id){
        RentalRepository.returnRentalById(id);
    }

    public static Rental getRentalObjectFromCustomerId(String id) throws DoesNotExistException{
        return RentalRepository.getRentalFromCustomerId(id);
    }

    private static Rental createRentalFromParameters(Object... args){
        Customer customer = (Customer) args[0];
        ArrayList<IProduct> products = (ArrayList<IProduct>) args[1];

        return new Rental(customer, products);
    }

    public static boolean customerHasExistingRental(String id){
        return RentalRepository.customerHasExistingRental(id);
    }
}
