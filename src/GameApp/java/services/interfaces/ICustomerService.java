package GameApp.java.services.interfaces;

import GameApp.java.general.exceptions.CustomerException;
import GameApp.java.models.Customer;

import java.util.ArrayList;

public interface ICustomerService extends IService{
    public ArrayList getAll();
    public void addCustomer(Object... args) throws CustomerException;
    public void removeCustomer(Customer customer);
    public void editCustomer(Object... args) throws CustomerException;
    public Customer getCustomerById(String id);
}
