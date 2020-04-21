package tests.models.validators;

import GameApp.java.models.validators.ProductValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductValidatorTest {

    @Test
    void costHasNoErrorsButDescriptionIsNull(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            ProductValidator.validate(null, "9.99");
        });
        String expected = "Description can't be null!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
        //run methods to check all other conditions are true
        costHasNoErrors(exception);
        descriptionIsNotEmpty(exception);
    }
    @Test
    void costHasNoErrorsButDescriptionIsEmpty(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            ProductValidator.validate("", "9.99");
        });
        String expected = "Description can't be empty!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
        //run methods to check all other conditions are true
        costHasNoErrors(exception);
        descriptionIsNotNull(exception);
    }
    @Test
    void descriptionHasNoErrorsButCostIsNull(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            ProductValidator.validate("Description Test", null);
        });
        String expected = "Cost can't be null!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
        //run methods to check all other conditions are true
        descriptionHasNoErrors(exception);
        costIsNotEmpty(exception);
        costIsANumber(exception);
    }
    @Test
    void descriptionHasNoErrorsButCostIsEmpty(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
           ProductValidator.validate("Description Test", "");
        });
        String expected = "Cost can't be empty!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
        //run methods to check all other conditions are true
        descriptionHasNoErrors(exception);
        costIsNotNull(exception);
        costIsANumber(exception);
    }
    @Test
    void descriptionHasNoErrorsButCostIsNotANumber(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            ProductValidator.validate("Description Test", "Not A Number");
        });
        String expected = "Cost is not a number!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
        //run methods to check all other conditions are true
        descriptionHasNoErrors(exception);
        costIsNotNull(exception);
        costIsNotEmpty(exception);
    }
    void costHasNoErrors(Exception exception){
        costIsNotNull(exception);
        costIsNotEmpty(exception);
        costIsANumber(exception);
    }
    void descriptionHasNoErrors(Exception exception){
        descriptionIsNotNull(exception);
        descriptionIsNotEmpty(exception);
    }
    void descriptionIsNotNull(Exception exception){
        String value = exception.getMessage();
        Assertions.assertFalse(value.contains("Description can't be null!"));
    }
    void descriptionIsNotEmpty(Exception exception){
        String value = exception.getMessage();
        Assertions.assertFalse(value.contains("Description can't be empty!"));
    }
    void costIsNotNull(Exception exception){
        String value = exception.getMessage();
        Assertions.assertFalse(value.contains("Cost can't be null!"));
    }
    void costIsNotEmpty(Exception exception){
        String value = exception.getMessage();
        Assertions.assertFalse(value.contains("Cost can't be empty!"));
    }
    void costIsANumber(Exception exception){
        String value = exception.getMessage();
        Assertions.assertFalse(value.contains("Cost is not a number!"));
    }
}
