package GameApp.java.services;

import GameApp.java.general.exceptions.CustomerException;
import GameApp.java.models.Customer;
import GameApp.java.models.validators.CustomerValidator;
import GameApp.java.repositories.CustomerRepository;

import java.util.ArrayList;

//responsible for creating Customer objects from arguments given to it by the front end
//It then uses these arguments to create Customer objects and then passes them to the Data Repository
//which is responsible for checking that they exist or are being repaired etc...
public class CustomerService {
    public static ArrayList<Customer> allCustomers(){
        return CustomerRepository.getAllCustomers();
    }

    public static void addCustomer(Object... args) throws CustomerException{
        Customer customer = createCustomerFromParameters(args);
        CustomerRepository.addCustomer(customer);
    }

    public static void removeCustomer(Customer customer){
        CustomerRepository.removeCustomer(customer);
    }

    public static void editCustomer(Object... args) throws CustomerException{
        Customer customer = createCustomerFromIdParameters(args);
        CustomerRepository.editCustomer(customer);
    }

    public static Customer getCustomerById(String id){
        return CustomerRepository.getCustomerByID(id);
    }//if a customer exists within the repository it will return the customer object

    private static Customer createCustomerFromParameters(Object... args) throws CustomerException {
        String forename = (String) args[0];
        String surname = (String) args[1];
        String address = (String) args[2];
        CustomerValidator.validate(forename, surname, address);
        return new Customer(forename, surname, address);
    }

    private static Customer createCustomerFromIdParameters(Object... args) throws CustomerException{
        String id = (String) args[0];
        String forename = (String) args[1];
        String surname = (String) args[2];
        String address = (String) args[3];
        CustomerValidator.validate(forename, surname, address);
        return new Customer(id, forename, surname, address);
    }
}
