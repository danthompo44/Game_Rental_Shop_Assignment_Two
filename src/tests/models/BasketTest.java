package tests.models;

import GameApp.java.models.Basket;
import GameApp.java.models.Console;
import GameApp.java.models.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

public class BasketTest {
    final TestData data = new TestData();
    final Game gameOne = data.gameOneC1ConsoleOneAvailable;
    final Game gameTwo = data.gameTwoC2ConsoleTwoInForRepair;
    final Game gameThree = data.gameThreeC1ConsoleThreeAvailable;
    final Console console = data.consoleOneC1Available;

    @BeforeEach
    void setUp(){
        Basket.getProducts().clear();
        assertBasketSize(0);
    }
    void assertBasketSize(int i){
        Assertions.assertEquals(i, Basket.getProducts().size());
    }
    @Test
    void addProductSuccessfullyAddsAProduct(){
        Basket.addProduct(gameOne);
        assertBasketSize(1);
        Basket.addProduct(console);
        assertBasketSize(2);
    }
    @Test
    void removeProductSuccessfullyRemovesAProduct(){
        Basket.getProducts().add(gameOne);
        Basket.getProducts().add(gameTwo);
        assertBasketSize(2);
        Basket.removeProduct(gameOne);
        assertBasketSize(1);
        Basket.removeProduct(gameTwo);
        assertBasketSize(0);
    }
    @Test
    void removeProductDoesNotRemoveAProductThatIsNotInTheBasket(){
        Basket.getProducts().add(gameOne);
        Basket.getProducts().add(gameTwo);
        assertBasketSize(2);
        Basket.removeProduct(gameThree);
        assertBasketSize(2);
        Basket.removeProduct(console);
        assertBasketSize(2);
    }
    @Test
    void clearBasketSuccessfullyClearsTheBasket(){
        Basket.getProducts().add(gameOne);
        Basket.getProducts().add(gameTwo);
        Basket.getProducts().add(gameThree);
        Basket.getProducts().add(console);
        assertBasketSize(4);
        Basket.clearBasket();
        assertBasketSize(0);
    }
    @Test
    void getProductsSuccessfullyRetrievesProducts(){

    }
}
