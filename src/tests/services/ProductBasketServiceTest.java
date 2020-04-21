package tests.services;

import GameApp.java.models.Console;
import GameApp.java.models.Game;
import GameApp.java.services.ProductBasketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

public class ProductBasketServiceTest {
    final TestData data = new TestData();
    final Game gameOne = data.gameOneC1ConsoleOneAvailable;
    final Game gameTwo = data.gameTwoC2ConsoleTwoInForRepair;
    final Game gameThree = data.gameThreeC1ConsoleThreeAvailable;
    final Console console = data.consoleOneC1Available;

    @BeforeEach
    void setUp(){
        ProductBasketService.allBasketItems().clear();
        assertProductBasketSize(0);
    }
    void assertProductBasketSize(int i){
        Assertions.assertEquals(i, ProductBasketService.allBasketItems().size());
    }
    @Test
    void allBasketItemsShowsCorrectNumberOfItems(){
        ProductBasketService.allBasketItems().add(gameOne);
        assertProductBasketSize(1);
        ProductBasketService.allBasketItems().add(gameTwo);
        assertProductBasketSize(2);
        ProductBasketService.allBasketItems().add(gameThree);
        assertProductBasketSize(3);
        ProductBasketService.allBasketItems().add(console);
        assertProductBasketSize(4);
    }
    @Test
    void addProductMethodPassesProductToProductRepositoryToAddToArrayList(){
        try{
            ProductBasketService.addProduct(gameOne);
            assertProductBasketSize(1);
            ProductBasketService.addProduct(console);
            assertProductBasketSize(2);
            ProductBasketService.addProduct(gameTwo);
        }
        catch(Exception ignore){}
    }
    @Test
    void clearBasketSuccessfullyClearsProductBasket(){
        ProductBasketService.allBasketItems().add(gameOne);
        ProductBasketService.allBasketItems().add(gameTwo);
        ProductBasketService.allBasketItems().add(gameThree);
        ProductBasketService.allBasketItems().add(console);
        assertProductBasketSize(4);
        ProductBasketService.clearBasket();
        assertProductBasketSize(0);
    }
    @Test
    void removeProductSuccessfullyPassesProductToRepositoryForRemoval(){
        ProductBasketService.allBasketItems().add(gameOne);
        ProductBasketService.allBasketItems().add(gameTwo);
        ProductBasketService.allBasketItems().add(gameThree);
        ProductBasketService.allBasketItems().add(console);
        assertProductBasketSize(4);
        ProductBasketService.removeProduct(gameTwo);
        assertProductBasketSize(3);
        ProductBasketService.removeProduct(console);
        assertProductBasketSize(2);
    }
    @Test
    void consoleLimitReachedTalksToRepositoryAndReturnsAsExpected(){
        Assertions.assertFalse(ProductBasketService.consoleLimitReached());
        try{
            ProductBasketService.addProduct(console);
        }
        catch(Exception ignore){}
        Assertions.assertTrue(ProductBasketService.consoleLimitReached());
    }
}
