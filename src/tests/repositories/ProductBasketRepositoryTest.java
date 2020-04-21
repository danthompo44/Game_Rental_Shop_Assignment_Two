package tests.repositories;

import GameApp.java.models.Console;
import GameApp.java.models.Game;
import GameApp.java.models.validators.BasketValidator;
import GameApp.java.repositories.ProductBasketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductBasketRepositoryTest {
    final TestData data = new TestData();
    final Console consoleOne = data.consoleOneC1Available;
    final Console consoleTwo = data.consoleTwoC1Available;
    final Game gameOne = data.gameOneC1ConsoleOneAvailable;
    final Game gameTwo = data.gameTwoC2ConsoleTwoInForRepair;
    final Game gameThree = data.gameThreeC1ConsoleThreeAvailable;
    final Game gameFour = data.gameFourC2ConsoleFourRented;

    @BeforeEach
    void setUp(){
        ProductBasketRepository.getProductBasket().clear();
        assertGetProductBasketSize(0);
    }
    private void assertGetProductBasketSize(int i){
        Assertions.assertEquals(i, ProductBasketRepository.getProductBasket().size());
    }
    private void resetCountersAndConfirm(){
        BasketValidator.reset();
    }
    @Test
    void productBasketShowsCorrectAmountOfProducts(){
        ProductBasketRepository.getProductBasket().add(consoleOne);
        assertGetProductBasketSize(1);
    }
    @Test
    void productBasketAddsGamesAndConsoles(){
        //reset basket counters
        resetCountersAndConfirm();
        try{ProductBasketRepository.addProduct(consoleOne);}
        catch(Exception ignore){ }
        //check product is added
        assertGetProductBasketSize(1);

        try{ProductBasketRepository.addProduct(gameOne);}
        catch(Exception ignore){ }
        //check product is added
        assertGetProductBasketSize(2);
    }
    @Test
    void productBasketDoesNotContainThisConsoleAndConsoleLimitIsNotMetConsoleIsAdded(){
        resetCountersAndConfirm();
        try{ProductBasketRepository.addProduct(consoleOne);}
        catch(Exception ignore){}
        assertGetProductBasketSize(1);
    }
    @Test
    void productBasketDoesNotContainConsoleAndConsoleLimitIsNotMetCountersIncrementCorrectly(){
        //reset and confirm counters are at 0
        resetCountersAndConfirm();
        //add console
        try{ProductBasketRepository.addProduct(consoleOne); }
        catch (Exception ignore){}
        //check console counter has incremented and game counter hasn't
        Assertions.assertEquals(1, ProductBasketRepository.getTotalConsolesAdded());
        Assertions.assertEquals(0, ProductBasketRepository.getTotalGamesAdded());
        //add game
        try{ProductBasketRepository.addProduct(gameOne);}
        catch(Exception ignore){}
        //check game counter has incremented and console counter has not
        Assertions.assertEquals(1, ProductBasketRepository.getTotalConsolesAdded());
        Assertions.assertEquals(1, ProductBasketRepository.getTotalGamesAdded());
    }
    @Test
    void productBasketDoesNotContainConsoleAndConsoleLimitIsMetConsoleIsNotAdded(){
        resetCountersAndConfirm();
        addConsoleCheckItIsAddedAndConsoleLimitIsReached();
        //console limit is met, try to add another console
        try{ProductBasketRepository.addProduct(consoleTwo);}
        catch(Exception ignore){}
        //check that another console is not added
        assertGetProductBasketSize(1);
    }
    @Test
    void productBasketDoesNotContainConsoleAndConsoleLimitIsMetCountersDoNotIncrement(){
        resetCountersAndConfirm();
        addConsoleCheckItIsAddedAndConsoleLimitIsReached();
        //console limit is met, try to add another console
        try{ProductBasketRepository.addProduct(consoleTwo);}
        catch(Exception ignore){}
        //check that another console counter is not incremented
        Assertions.assertEquals(1, ProductBasketRepository.getTotalConsolesAdded());
    }
    @Test
    void productBasketDoesNotContainConsoleAndConsoleLimitIsMetExceptionIsThrown(){
        resetCountersAndConfirm();
        addConsoleCheckItIsAddedAndConsoleLimitIsReached();
        Exception exception = assertThrows(Exception.class, () -> {
            ProductBasketRepository.addProduct(consoleTwo);
        });

        String expectedMessage = "Sorry, you've reached your console limit for this rental!";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
        //wait for phils response to be able to complete this test, might need to write in a basket class into code that hold a seperate arrayList for Consoles and Games
    }
    void addConsoleCheckItIsAddedAndConsoleLimitIsReached(){
        try{ProductBasketRepository.addProduct(consoleOne);}
        catch(Exception ignore){}
        assertGetProductBasketSize(1);
        Assertions.assertTrue(ProductBasketRepository.consoleLimitReached());
    }
    @Test
    void productBasketDoesNotContainGameAndGameLimitIsNotMetGameIsAdded(){
        resetCountersAndConfirm();
        //add game
        try{ProductBasketRepository.addProduct(gameOne);}
        catch(Exception ignore){}
        //check game is added
        assertGetProductBasketSize(1);
    }
    @Test
    void productBasketDoesNotContainGameAndGameLimitIsNotMetCounterIncrements(){
        resetCountersAndConfirm();
        //add game
        try{ProductBasketRepository.addProduct(gameOne);}
        catch(Exception ignore){}
        //check counters have incremented
        Assertions.assertEquals(1, ProductBasketRepository.getTotalGamesAdded());
    }
    @Test
    void productBasketDoesNotContainGameAndGameLimitIsMetGameIsNotAdded(){
        resetCountersAndConfirm();
        addGamesCheckTheyAreAddedAndGameLimitIsReached();
        //try to add a game
        try{ProductBasketRepository.addProduct(gameOne);}
        catch(Exception ignore){}
        assertGetProductBasketSize(3);
    }
    @Test
    void productBasketDoesNotContainGameAndGameLimitIsMetCounterDoesNotIncrement(){
        resetCountersAndConfirm();
        addGamesCheckTheyAreAddedAndGameLimitIsReached();
        //try to add a game
        try{ProductBasketRepository.addProduct(gameOne);}
        catch(Exception ignore){}
        Assertions.assertEquals(3, ProductBasketRepository.getTotalGamesAdded());
    }
    @Test
    void productBasketDoesNotContainGameAndGameLimitIsMetExceptionIsThrown(){
        resetCountersAndConfirm();
        addGamesCheckTheyAreAddedAndGameLimitIsReached();
        // Check For Exception using Lambda function, add game that isn't in repository to avoid seeing the wrong exception
        Exception exception = assertThrows(Exception.class, () -> {
            ProductBasketRepository.addProduct(gameFour);
        });
        //declare expected exception and actual exception
        String expected = "Sorry, you've reached your game limit for this rental!";
        String actual = exception.getMessage();
        //assert whether the actual exception contains the expected
        Assertions.assertTrue(actual.contains(expected));
    }
    @Test
    void exceptionIsThrownIfGetProductBasketContainsProductThatIsBeingAdded(){
        //add product
        try{ProductBasketRepository.addProduct(gameOne);}
        catch(Exception ignore){}
        //add same product for second time, assert exception
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
           ProductBasketRepository.addProduct(gameOne);
        });
        //declare actual and expected strings
        String expected = " is already in your basket!";
        String actual = exception.getMessage();
        //assert true if actual contains expected message

        Assertions.assertTrue(actual.contains(expected));
    }
    void addGamesCheckTheyAreAddedAndGameLimitIsReached(){
        try{
            ProductBasketRepository.addProduct(gameOne);
            assertGetProductBasketSize(1);
            ProductBasketRepository.addProduct(gameTwo);
            assertGetProductBasketSize(2);
            ProductBasketRepository.addProduct(gameThree);
        }
        catch(Exception ignore){}
        //check 3 games have been added
        assertGetProductBasketSize(3);
        //check game limit of three is reached
        Assertions.assertTrue(ProductBasketRepository.gameLimitReached());
    }
    @Test
    void whenProductBasketContainsConsoleItIsRemovedFromGetProductBasketUsingRemoveMethod(){
        ProductBasketRepository.getProductBasket().add(consoleOne);
        assertGetProductBasketSize(1);
        ProductBasketRepository.removeProduct(consoleOne);
        assertGetProductBasketSize(0);
    }
    @Test
    void whenProductBasketContainsConsoleAndIsRemovedFromGetProductBasketCounterDecreases(){
        resetCountersAndConfirm();
        try{ProductBasketRepository.addProduct(consoleOne);}
        catch(Exception ignore){}
        //confirm counters increments
        Assertions.assertEquals(1, ProductBasketRepository.getTotalConsolesAdded());
        ProductBasketRepository.removeProduct(consoleOne);
        //confirm counters decrease after removing product
        Assertions.assertEquals(0, ProductBasketRepository.getTotalConsolesAdded());
    }
    @Test
    void whenProductBasketContainsGameItIsRemovedFromGetProductBasketUsingRemoveMethod(){
        ProductBasketRepository.getProductBasket().add(gameOne);
        assertGetProductBasketSize(1);
        ProductBasketRepository.removeProduct(gameOne);
        assertGetProductBasketSize(0);
    }
    @Test
    void whenProductBasketContainsGameAndIsRemovedFromGetProductBasketCounterDecreases(){
        resetCountersAndConfirm();
        try{ProductBasketRepository.addProduct(gameOne);}
        catch(Exception ignore){}
        //confirm counters increments
        Assertions.assertEquals(1, ProductBasketRepository.getTotalGamesAdded());
        ProductBasketRepository.removeProduct(gameOne);
        //confirm counters decrease after removing product
        Assertions.assertEquals(0, ProductBasketRepository.getTotalGamesAdded());
    }
    @Test
    void getAllProductBasketStillContainsProductWhenRemoveMethodIsCalledOnAProductThatDoesNotExist(){
        ProductBasketRepository.getProductBasket().add(gameOne);
        assertGetProductBasketSize(1);
        ProductBasketRepository.removeProduct(gameTwo);
        assertGetProductBasketSize(1);
    }
    @Test
    void countersStayTheSameWhenRemoveMethodIsCalledOnAProductThatDoesNotExist(){
        ProductBasketRepository.getProductBasket().add(gameOne);
        //assert counters have increased
        Assertions.assertEquals(1, ProductBasketRepository.getTotalGamesAdded());
        ProductBasketRepository.removeProduct(gameTwo);
        Assertions.assertEquals(1, ProductBasketRepository.getTotalGamesAdded());
    }
    @Test
    void booleanReturnsTrueWhenConsoleLimitIsReached(){
        resetCountersAndConfirm();
        addConsoleCheckItIsAddedAndConsoleLimitIsReached();
        Assertions.assertTrue(ProductBasketRepository.consoleLimitReached());
    }
    @Test
    void booleanReturnsFalseIfConsoleLimitIsNotReached(){
        resetCountersAndConfirm();
        Assertions.assertFalse(ProductBasketRepository.consoleLimitReached());
    }
    @Test
    void basketClearsIfClearBasketMethodIsCalled(){
        resetCountersAndConfirm();
        addConsoleCheckItIsAddedAndConsoleLimitIsReached();
        addThreeGamesWithNoAssertions();
        ProductBasketRepository.clearBasket();
        assertGetProductBasketSize(0);
    }
    @Test
    void CountersResetIfClearBasketMethodIsCalled(){
        resetCountersAndConfirm();
        addConsoleCheckItIsAddedAndConsoleLimitIsReached();
        addThreeGamesWithNoAssertions();
        ProductBasketRepository.clearBasket();
        Assertions.assertEquals(0, ProductBasketRepository.getTotalGamesAdded());
        Assertions.assertEquals(0, ProductBasketRepository.getTotalConsolesAdded());
    }
    void addThreeGamesWithNoAssertions(){
        try{
            ProductBasketRepository.addProduct(gameOne);
            ProductBasketRepository.addProduct(gameTwo);
            ProductBasketRepository.addProduct(gameThree);
        }
        catch(Exception ignore){}
    }
}



