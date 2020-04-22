package tests.repositories;

import GameApp.java.models.IProductRental;
import GameApp.java.models.Rental;
import GameApp.java.repositories.RentalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

public class RentalRepositoryTest {
    final static TestData data = new TestData();
    final Rental rentalOne = data.rentalOne;
    final Rental rentalTwo = data.rentalTwo;
    final Rental rentalThree = data.rentalThree;

    @BeforeAll
    static void createRentals(){
        data.createRentals();
    }
    @BeforeEach
    void setUp(){
        RentalRepository.getAllRentals().clear();
        assertGetAllRentalsSize(0);
        data.createRentals();
    }
    private void assertGetAllRentalsSize(int i){
        Assertions.assertEquals(i, RentalRepository.getAllRentals().size());
    }
    @Test
    void getAllRentalsContainsCorrectAmountOfRentals(){
        RentalRepository.getAllRentals().add(rentalOne);
        assertGetAllRentalsSize(1);
        RentalRepository.getAllRentals().add(rentalTwo);
        assertGetAllRentalsSize(2);
    }
    @Test
    void addingARentalThatDoesNotExistInArrayListCreatesANewRental(){
        RentalRepository.addRental(rentalOne);
        assertGetAllRentalsSize(1);
    }
    @Test
    void rentalThatAlreadyExistsWillNotBeAddedToRepository(){
        RentalRepository.getAllRentals().add(rentalOne);
        assertGetAllRentalsSize(1);
        RentalRepository.addRental(rentalOne);
        assertGetAllRentalsSize(1);
    }
    @Test
    void addRentalSuccessfullySetsProductsWithinRentalToRented(){
        RentalRepository.addRental(rentalOne);
        for(IProductRental p: rentalOne.getProducts()){
            Assertions.assertTrue(p.isRented());
        }
    }
    @Test
    void rentalWithSameIdAsThatPassedToReturnRentalByIdMethodIsReturned(){
        RentalRepository.getAllRentals().add(rentalOne);
        assertGetAllRentalsSize(1);
        //retrieve rental using string identical to it's Id, return that rental
        RentalRepository.returnRentalById(rentalOne.getId());
        assertGetAllRentalsSize(0);
    }
    @Test
    void noRentalsInTheListAreReturnedIfGivenIdDoesNotMatchAnIdInTheRepository(){
        RentalRepository.getAllRentals().add(rentalOne);
        assertGetAllRentalsSize(1);
        //retrieve rental using string identical to it's Id, return that rental
        RentalRepository.returnRentalById(rentalTwo.getId());
        assertGetAllRentalsSize(1);
    }
    @Test
    void correctRentalObjectIsRetrievedIfGivenACustomerIdThatExistsWithinTheRepository(){
        RentalRepository.getAllRentals().add(rentalThree);
        assertGetAllRentalsSize(1);
        int expectedHashCode = 0;
        try{
            expectedHashCode = RentalRepository.getRentalFromCustomerId(rentalThree.getCustomer().getId()).hashCode();
        }
        catch(Exception ignore){}
            Assertions.assertEquals(rentalThree.hashCode(), expectedHashCode);
    }
    @Test
    void correctExceptionIsThrownIfGivenACustomerIdWhoDoesNotHaveARentalInTheRepository(){
        RentalRepository.getAllRentals().add(rentalThree);
        assertGetAllRentalsSize(1);

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            RentalRepository.getRentalFromCustomerId(rentalOne.getCustomer().getId());
        });
        String expected = "Sorry, a rental does not exist!";
        String actual = exception.getMessage();
        //assert actual exception contains expected
        Assertions.assertTrue(actual.contains(expected));
    }
    @Test
    void booleanReturnsTrueIfACustomerHasACurrentRental(){
        RentalRepository.getAllRentals().add(rentalThree);
        assertGetAllRentalsSize(1);
        Assertions.assertTrue(RentalRepository.customerHasExistingRental(rentalThree.getCustomer().getId()));
    }
    @Test
    void booleanReturnsFalseIfACustomerDoesNotHaveACurrentRental(){
        RentalRepository.getAllRentals().add(rentalThree);
        assertGetAllRentalsSize(1);
        Assertions.assertFalse(RentalRepository.customerHasExistingRental(rentalTwo.getCustomer().getId()));
    }
}
