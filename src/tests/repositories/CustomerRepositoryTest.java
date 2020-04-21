package tests.repositories;

import GameApp.java.models.Customer;
import GameApp.java.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

public class CustomerRepositoryTest {
    final TestData data = new TestData();
    final Customer customerOne = data.customerOneC1;
    final Customer customerTwo = data.customerTwoC2;
    final Customer customerThree = data.customerThreeC1;
    final Customer customerCU1012 = data.customerTwoC2;
    final Customer customerTwoEdited = data.customerTwoC2Edit;

    @BeforeEach
    void setUp(){
        CustomerRepository.getAllCustomers().clear();
        assertGetAllCustomersSize(0);
    }
    private void assertGetAllCustomersSize(int i){
        Assertions.assertEquals(i, CustomerRepository.getAllCustomers().size());
    }
    @Test
    void customerRepositoryContainsCorrectNumberOfCustomers(){
        CustomerRepository.getAllCustomers().add(customerOne);
        assertGetAllCustomersSize(1);
        CustomerRepository.getAllCustomers().add(customerTwo);
        assertGetAllCustomersSize(2);
        CustomerRepository.getAllCustomers().add(customerThree);
        assertGetAllCustomersSize(3);
    }
    @Test
    void correctCustomerIsReturnedWhenAnIdIsGivenThatExistsInTheRepository(){
        CustomerRepository.getAllCustomers().add(customerCU1012);
        assertGetAllCustomersSize(1);
        Assertions.assertEquals(customerCU1012.hashCode(), CustomerRepository.getCustomerByID("CU1012").hashCode());
    }
    @Test
    void nullIsReturnedWhenAnIdIsGivenThatDoesNotExistInTheRepository(){
        CustomerRepository.getAllCustomers().add(customerOne);
        assertGetAllCustomersSize(1);
        Assertions.assertNull(CustomerRepository.getCustomerByID("Test"));
    }
    @Test
    void customerIsCorrectlyAddedToTheRepository(){
        CustomerRepository.addCustomer(customerOne);
        assertGetAllCustomersSize(1);
        CustomerRepository.addCustomer(customerTwo);
        assertGetAllCustomersSize(2);
    }
    @Test
    void customerIsRemovedFromRepositoryWhenTheyExistInTheRepository(){
        CustomerRepository.getAllCustomers().add(customerOne);
        assertGetAllCustomersSize(1);
        //remove Customer
        CustomerRepository.removeCustomer(customerOne);
        assertGetAllCustomersSize(0);
    }
    @Test
    void customerIsNotRemovedFromRepositoryWhenTheyDoNotExistInRepository(){
        CustomerRepository.getAllCustomers().add(customerOne);
        assertGetAllCustomersSize(1);
        //remove non-existent customer
        CustomerRepository.removeCustomer(customerTwo);
        assertGetAllCustomersSize(1);
    }
    @Test
    void customersAddressIsSuccessfullyEditedWhenTheCorrectIdIsGiven(){
        CustomerRepository.getAllCustomers().add(customerTwo);
        assertGetAllCustomersSize(1);
        Assertions.assertEquals("Address Two", customerTwo.getAddress());
        CustomerRepository.editCustomer(customerTwoEdited);
        Assertions.assertEquals("Address Two Edited", customerTwo.getAddress());
    }
    @Test
    void customersAddressIsNotEditedWhenIncorrectIdIsGiven(){
        CustomerRepository.getAllCustomers().add(customerTwo);
        assertGetAllCustomersSize(1);
        Assertions.assertEquals("Address Two", customerTwo.getAddress());
        CustomerRepository.editCustomer(customerThree);
        Assertions.assertEquals("Address Two", customerTwo.getAddress());
    }
}
