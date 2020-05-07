package GameApp.java.services;

import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.Customer;
import GameApp.java.models.ProductBehaviour;
import GameApp.java.models.Rental;
import GameApp.java.repositories.RentalRepository;
import GameApp.java.services.interfaces.IRentalService;

import java.util.ArrayList;

//responsible for creating Rental objects from arguments given to it by the front end
//It then uses these arguments to create Rental objects and then passes them to the Data Repository
//which is responsible for checking that they exist or are being repaired etc...
public class RentalService extends SuperService implements IRentalService {

    @Override
    public ArrayList getAll(){
        return RentalRepository.getAllRentals();
    }

    public void addRental(Object... args){
        Rental rental = createRentalFromParameters(args);
        RentalRepository.addRental(rental);
    }

    public void returnRentalById(String id){
        RentalRepository.returnRentalById(id);
    }

    public Rental getRentalObjectFromCustomerId(String id) throws DoesNotExistException{
        return RentalRepository.getRentalFromCustomerId(id);
    }

    private Rental createRentalFromParameters(Object... args){
        Customer customer = (Customer) args[0];
        ArrayList<ProductBehaviour> products = (ArrayList<ProductBehaviour>) args[1];

        return new Rental(customer, products);
    }

    public boolean customerHasExistingRental(String id){
        return RentalRepository.customerHasExistingRental(id);
    }
}
