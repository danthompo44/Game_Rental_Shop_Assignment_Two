package Tests;

import GameApp.java.models.BitDepth;
import GameApp.java.models.Console;
import GameApp.java.models.FormFactor;
import GameApp.java.models.Game;
import GameApp.java.models.idFactory.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IdFactoryTest {
    IId consoleId = new ConsoleId();
    IId customerId = new CustomerID();
    IId customerId2 = new CustomerID();
    IId gameId = new GameId();
    IId rentalId = new RentalId();
    IId rentalId2 = new RentalId();
    Console console = new Console("Console One", 99.99, BitDepth.EIGHT, FormFactor.HANDHELD,
            false, false);
    Game game = new Game("Game one", 20.99, console, true, true);

    @Test
    void idObjectsReturnAsExpectedTypesFromTheFactory(){
        Assertions.assertTrue(IdFactory.createCustomerId() instanceof CustomerID);
        Assertions.assertTrue(IdFactory.createRentalId() instanceof RentalId);
    }
    @Test
    void assertProductIsAssignedAnIdWhenCreated(){
        Assertions.assertNotNull(console.getId());
        Assertions.assertNotNull(game.getId());
    }
    @Test
    void idsContainCorrectPrefixes(){
        Assertions.assertTrue(consoleId.getId().contains("CO"));
        Assertions.assertTrue(customerId.getId().contains("CU"));
        Assertions.assertTrue(gameId.getId().contains("GA"));
        Assertions.assertTrue(rentalId.getId().contains("RE"));
    }
    @Test
    void allIdsAreDifferent(){
        Assertions.assertNotEquals(consoleId.getId(), console.getId());
        Assertions.assertNotEquals(customerId.getId(), customerId2.getId());
        Assertions.assertNotEquals(gameId.getId(), game.getId());
        Assertions.assertNotEquals(rentalId.getId(), rentalId2.getId());
    }
}


