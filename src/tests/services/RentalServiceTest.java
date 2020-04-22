package tests.services;

import GameApp.java.models.Customer;
import GameApp.java.models.IProductRental;
import GameApp.java.models.Rental;
import GameApp.java.services.RentalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

import java.util.ArrayList;

public class RentalServiceTest {
    final static TestData data = new TestData();
    final Rental r1 = data.rentalOne;
    final Rental r2 = data.rentalTwo;
    final Customer c1 = data.customerOneC1;
    final Customer c2 = data.customerTwoC2;
    final ArrayList<IProductRental> products = data.productListOneConsoleThreeGames;

    @BeforeAll
    static void createRentals(){
        data.createRentals();
    }
    @BeforeEach
    void setUp(){
        RentalService.allRentals().clear();
        assertGetAllRentalsSize(0);
    }
    void assertGetAllRentalsSize(int i){
        Assertions.assertEquals(i, RentalService.allRentals().size());
    }
    @Test
    void getAllRentalsContainsExpectedNumberOfRentals(){
        RentalService.allRentals().add(r1);
        assertGetAllRentalsSize(1);
        RentalService.allRentals().add(r2);
        assertGetAllRentalsSize(2);
        RentalService.allRentals().remove(r2);
        assertGetAllRentalsSize(1);
    }
    @Test
    void addRentalSuccessfullyCreatesARental(){
        RentalService.addRental(c1, products);
        assertGetAllRentalsSize(1);
    }
    @Test
    void returnRentalByIdPassesIdToRepositoryReturnsExpectedRental(){
        RentalService.allRentals().add(r1);
        assertGetAllRentalsSize(1);
        RentalService.returnRentalById(r1.getId());
        assertGetAllRentalsSize(0);
    }
    @Test
    void returnRentalByIdDoesNotRemoveRentalIfIdDoesNotExist(){
        RentalService.allRentals().add(r1);
        assertGetAllRentalsSize(1);
        RentalService.returnRentalById("Does Not Exist");
        assertGetAllRentalsSize(1);
    }
    @Test
    void getRentalObjectFromCustomerIdReturnsExpectedRental(){
        RentalService.allRentals().add(r1);
        assertGetAllRentalsSize(1);
        try{
            Assertions.assertEquals(r1.hashCode(), RentalService.getRentalObjectFromCustomerId(r1.getCustomer().getId()).hashCode());
        }
        catch(Exception ignore){}
    }
    @Test
    void getRentalObjectFromCustomerIdThrowsExpectedExceptionWhenCustomerIdDoesNotExistInRepository(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            RentalService.getRentalObjectFromCustomerId("Does Not Exist");
        });
        String expected = "Sorry, a rental does not exist!";
        String actual = exception.getMessage();

        Assertions.assertTrue(actual.contains(expected));
    }
    @Test
    void customerHasExistingRentalReturnsTrueIfCustomerARental(){
        RentalService.addRental(c1, products);
        Assertions.assertTrue(RentalService.customerHasExistingRental(c1.getId()));
    }
    @Test
    void customerHasExistingRentalReturnsFalseIfCustomerDoesNothaveARental(){
        RentalService.addRental(c1, products);
        Assertions.assertFalse(RentalService.customerHasExistingRental(c2.getId()));
    }
}
