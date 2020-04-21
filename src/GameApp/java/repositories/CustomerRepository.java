package GameApp.java.repositories;

import GameApp.java.models.Customer;
import GameApp.java.services.SessionService;

import java.util.ArrayList;

//simulates a DB communicating with a public object - session
//Acts like the middle man, retrives all Customer Objects stored in the database, and can process arguments given
// to it's methods and communicating between the back end (Make-shift DB) and the front end
public class CustomerRepository {

    public static ArrayList<Customer> getAllCustomers() {
        return SessionService.getCustomers();
    }//returns all consoles within the SessionService class getCustomers()

    public static Customer getCustomerByID(String id) {
        for (Customer c : getAllCustomers()) {
            if (c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }//Allows for the search of a Customer by their ID, uses the getAllCustomers method to retrieve
    //an arraylist of Customers, loops through them and if the ID matches the ID passed to the method it will
    //return the Customer object, else it will return null.

    public static void addCustomer(Customer customer) {
        if (!getAllCustomers().contains(customer)) {
            getAllCustomers().add(customer);
        }
    }//Allows the addition of a Customer object to the getAllCustomers() methods ArrayList

    public static void removeCustomer(Customer customer){
        getAllCustomers().remove(customer);
    }//method for removing a Customer object from the getAllCustomers() resultant array list.
    //Matching the id of the passed Customer to one within the ArrayList
    public static void editCustomer(Customer customer){
        for(Customer c : getAllCustomers()){
            if(c.getId().equals(customer.getId())){
                c.setAddress(customer.getAddress());
            }
        }
    }
}
