package tests.models.validators;

import GameApp.java.models.validators.CustomerValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerValidatorTest {
    @Test
    void forenameIsNullAndSurnameAndAddressAreCorrect(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            CustomerValidator.validate(null, "Test Pass", "Test Pass");
        });
        String expected = "First name can't be null!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
        //run methods to check all other conditions are true
        surnameIsValid(exception);
        addressIsValid(exception);
        forenameIsNotEmpty(exception);
    }
    @Test
    void forenameIsEmptyAndSurnameAndAddressAreCorrect(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            CustomerValidator.validate("", "Test Pass", "Test Pass");
        });
        String expected = "First name can't be empty!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
        //run methods to check all other conditions are true
        surnameIsValid(exception);
        addressIsValid(exception);
        forenameIsNotNull(exception);
    }
    @Test
    void surnameIsNullAndForenameAndAddressAreCorrect(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            CustomerValidator.validate("Test Pass", null, "Test Pass");
        });
        String expected = "Surname can't be null!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
        //run methods to check all other conditions are true
        forenameIsValid(exception);
        addressIsValid(exception);
        surnameIsNotEmpty(exception);
    }
    @Test
    void surnameIsEmptyAndForenameAndAddressAreCorrect(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            CustomerValidator.validate("Test Pass", "", "Test Pass");
        });
        String expected = "Surname can't be empty!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
        forenameIsValid(exception);
        addressIsValid(exception);
        surnameIsNotNull(exception);
    }
    @Test
    void addressIsNullAndForenameAndSurnameAreCorrect(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            CustomerValidator.validate("Test Pass", "Test Pass", null);
        });
        String expected = "Address can't be null!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
        forenameIsValid(exception);
        surnameIsValid(exception);
        addressIsNotEmpty(exception);
    }
    @Test
    void addressIsEmptyAndForenameAndSurnameAreCorrect(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            CustomerValidator.validate("Test Pass", "Test Pass", "");
        });
        String expected = "Address can't be empty!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
        forenameIsValid(exception);
        surnameIsValid(exception);
        addressIsNotNull(exception);
    }
    void forenameIsValid(Exception exception){
        forenameIsNotNull(exception);
        forenameIsNotEmpty(exception);
    }
    void surnameIsValid(Exception exception){
        surnameIsNotNull(exception);
        surnameIsNotEmpty(exception);
    }
    void addressIsValid(Exception exception){
        addressIsNotNull(exception);
        addressIsNotEmpty(exception);
    }
    void forenameIsNotNull(Exception exception){
        String unexpected = "First name can't be null";
        Assertions.assertFalse(exception.toString().contains(unexpected));
    }
    void forenameIsNotEmpty(Exception exception){
        String unexpected = "First name can't be empty!";
        Assertions.assertFalse(exception.toString().contains(unexpected));
    }
    void surnameIsNotNull(Exception exception){
        String unexpected = "Surname can't be null!";
        Assertions.assertFalse(exception.toString().contains(unexpected));
    }
    void surnameIsNotEmpty(Exception exception){
        String unexpected = "Surname can't be empty!";
        Assertions.assertFalse(exception.toString().contains(unexpected));
    }
    void addressIsNotNull(Exception exception){
        String unexpected = "Address can't be null!";
        Assertions.assertFalse(exception.toString().contains(unexpected));
    }
    void addressIsNotEmpty(Exception exception){
        String unexpected = "Address can't be empty!";
        Assertions.assertFalse(exception.toString().contains(unexpected));
    }
}
