package tests.services;

import GameApp.java.models.Customer;
import GameApp.java.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

public class CustomerServiceTest {
    final TestData data = new TestData();
    final Customer customerOne = data.customerOneC1;
    final Customer customerTwo = data.customerTwoC2;
    final Customer customerThree = data.customerThreeC1;
    final Customer c2Edit = data.customerTwoC2Edit;

    @BeforeEach
    void setUp(){
        CustomerService.allCustomers().clear();
        assertAllCustomersSize(0);
    }
    private Customer getCustomerIndexZero(){
        return CustomerService.allCustomers().get(0);
    }
    private void assertAllCustomersSize(int i){
        Assertions.assertEquals(i, CustomerService.allCustomers().size());;
    }
    @Test
    void checkAllCustomersContainsCorrectNumberOfCustomers(){
        CustomerService.allCustomers().add(customerOne);
        assertAllCustomersSize(1);
        CustomerService.allCustomers().add(customerTwo);
        assertAllCustomersSize(2);
        CustomerService.allCustomers().remove(customerTwo);
        assertAllCustomersSize(1);
    }
    @Test
    void checkAddCustomerCorrectlyCreatesACustomerForTheRepositoryToAdd(){
        String forename = "Test Forename";
        String surname = "Test Surname";
        String address = "Test Address";
        //create customer
        try{CustomerService.addCustomer(forename, surname, address);}
        catch(Exception ignore){}
        assertAllCustomersSize(1);
        Assertions.assertEquals(forename, getCustomerIndexZero().getForename());
        Assertions.assertEquals(surname, getCustomerIndexZero().getSurname());
        Assertions.assertEquals(address, getCustomerIndexZero().getAddress());
    }

    @Test
    void checkRemoveCustomerRemovesAValidCustomer(){
        CustomerService.allCustomers().add(customerThree);
        assertAllCustomersSize(1);
        CustomerService.removeCustomer(customerThree);
        assertAllCustomersSize(0);
    }
    @Test
    void checkRemoveCustomerDoesNotRemoveAnInvalidCustomer(){
        CustomerService.allCustomers().add(customerThree);
        assertAllCustomersSize(1);
        CustomerService.removeCustomer(customerTwo);
        assertAllCustomersSize(1);
    }
    @Test
    void editCustomerCorrectlyEditsACustomerInTheRepository(){
        CustomerService.allCustomers().add(customerTwo);
        assertAllCustomersSize(1);
        try{
            CustomerService.editCustomer(c2Edit.getId(), c2Edit.getForename(), c2Edit.getSurname(), c2Edit.getAddress());
        }
        catch(Exception ignore){}
        assertAllCustomersSize(1);
        Assertions.assertEquals(c2Edit.getAddress(), getCustomerIndexZero().getAddress());
    }
    @Test
    void correctCustomerIsReturnedByGetCustomerByIdMethod(){
        CustomerService.allCustomers().add(customerThree);
        Assertions.assertEquals(customerThree.hashCode(), CustomerService.getCustomerById(customerThree.getId()).hashCode());
    }
}
