package tests.models;

import GameApp.java.models.Console;
import GameApp.java.models.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    final TestData testData = new TestData();
    Game gameConsoleOne;
    Game gamePR1009;
    Game gameAvailable;
    Game gameRented;
    Game gameBeingRepaired;
    Console consoleOne;

    @BeforeEach
    void setUp(){
        gameConsoleOne = testData.gameOneC1ConsoleOneAvailable;
        gamePR1009 = testData.gameTwoC2ConsoleTwoInForRepair;
        gameAvailable = testData.gameOneC1ConsoleOneAvailable;
        gameRented = testData.gameFourC2ConsoleFourRented;
        gameBeingRepaired = testData.gameTwoC2ConsoleTwoInForRepair;
        consoleOne = testData.consoleOneC1Available;
    }
    @Test
    void getConsoleIdReturnsCorrectId(){
        Assertions.assertEquals("PR1009", gamePR1009.getId());
    }
    @Test
    void getDescriptionReturnsExpectedDescription(){
        Assertions.assertEquals("Game Two, C2, Being Repaired", gamePR1009.getDescription());
    }
    @Test
    void getCostReturnsExpectedCost(){
        Assertions.assertEquals(14.99, gamePR1009.getRentalCost());
    }
    @Test
    void isRentedReturnsExpectedBoolean(){
        Assertions.assertTrue(gameRented.isRented());
        Assertions.assertFalse(gameAvailable.isRented());
    }
    @Test
    void isBeingRepairedReturnsExpectedBoolean(){
        Assertions.assertTrue(gameBeingRepaired.isBeingRepaired());
        Assertions.assertFalse(gameAvailable.isBeingRepaired());
    }
    @Test
    void setCostAllowsForTheSettingOfCost(){
        Assertions.assertEquals(14.99, gamePR1009.getRentalCost());
        gamePR1009.setRentalCost(9.99);
        Assertions.assertEquals(9.99, gamePR1009.getRentalCost());
    }
    @Test
    void setDescriptionAllowsForTheSettingOfTheDescription(){
        Assertions.assertEquals("Game Two, C2, Being Repaired", gamePR1009.getDescription());
        gamePR1009.setDescription("Edited");
        Assertions.assertEquals("Edited", gamePR1009.getDescription());
    }
    @Test
    void setIsRentedAllowsFotTheSettingOfIsRented(){
        Assertions.assertFalse(gameAvailable.isRented());
        gameAvailable.setIsRented(true);
        Assertions.assertTrue(gameAvailable.isRented());
    }
    @Test
    void setIsBeingRepairedAllowsForTheSettingOfIsBeingRepaired(){
        Assertions.assertFalse(gameAvailable.isBeingRepaired());
        gameAvailable.setIsBeingRepaired(true);
        Assertions.assertTrue(gameAvailable.isBeingRepaired());
    }
    @Test
    void addToRentalSetsIsRentedToTrue(){
        Assertions.assertFalse(gameAvailable.isRented());
        gameAvailable.addToRental();
        Assertions.assertTrue(gameAvailable.isRented());
    }
    @Test
    void removeFromRentalSetsIsRentedToFalse(){
        Assertions.assertTrue(gameRented.isRented());
        gameRented.removeFromRental();
        Assertions.assertFalse(gameRented.isRented());
    }
    @Test
    void isAvailableReturnsTrueWhenConsoleIsAvailable(){
        Assertions.assertTrue(gameAvailable.isAvailable());
    }
    @Test
    void isAvailableReturnsFalseWhenConsoleIsRented(){
        Assertions.assertFalse(gameRented.isAvailable());
    }
    @Test
    void isAvailableReturnsFalseWhenConsoleIsBeingRepaired(){
        Assertions.assertFalse(gameBeingRepaired.isAvailable());
    }
    @Test
    void gameContainsKnowledgeOfConsoleItPlaysOn(){
        assertEquals(gameConsoleOne.getConsole(), consoleOne);
    }
}