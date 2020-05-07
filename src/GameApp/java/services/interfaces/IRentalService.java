package GameApp.java.services.interfaces;

import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.Rental;

import java.util.ArrayList;

public interface IRentalService extends IService{
    public ArrayList getAll();
    public void addRental(Object... args);
    public void returnRentalById(String id);
    public Rental getRentalObjectFromCustomerId(String id) throws DoesNotExistException;
    public boolean customerHasExistingRental(String id);
}
