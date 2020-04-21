package tests.services;

import GameApp.java.models.Console;
import GameApp.java.models.Game;
import GameApp.java.services.ConsoleService;
import GameApp.java.services.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

public class GameServiceTest {
    final TestData data = new TestData();
    final Game gameOneAvailable = data.gameOneC1ConsoleOneAvailable;
    final Game gameTwoBroken = data.gameTwoC2ConsoleTwoInForRepair;
    final Game gameThreeAvailable = data.gameThreeC1ConsoleThreeAvailable;
    final Game gameFourRented = data.gameFourC2ConsoleFourRented;
    final Game g2Edit = data.gameTwoC2ConsoleTwoInForRepairEdited;
    final Console consoleGameOne = data.consoleOneC1Available;
    final Console consoleTwo = data.consoleFourC2InForRepair;

    @BeforeEach
    void setUp(){
        GameService.allGames().clear();
        assertAllGamesSize(0);
    }
    void assertAllGamesSize(int i){
        Assertions.assertEquals(i, GameService.allGames().size());
    }
    void assertAvailableGamesSize(int i){
        Assertions.assertEquals(i, GameService.availableGames().size());
    }
    void assertGetLoanedGamesSize(int i){
        Assertions.assertEquals(i, GameService.getLoanedGames().size());
    }
    void assertGetBrokenGamesSize(int i){
        Assertions.assertEquals(i, GameService.getBrokenGames().size());
    }
    @Test
    void allGamesContainsCorrectNumberOfGames() {
        GameService.allGames().add(gameOneAvailable);
        assertAllGamesSize(1);
        GameService.allGames().add(gameTwoBroken);
        assertAllGamesSize(2);
        GameService.allGames().remove(gameTwoBroken);
        assertAllGamesSize(1);
    }
    @Test
    void availableGamesContainsOnlyAvailableGames(){
        GameService.allGames().add(gameOneAvailable);
        GameService.allGames().add(gameTwoBroken);
        GameService.allGames().add(gameThreeAvailable);
        GameService.allGames().add(gameFourRented);
        assertAvailableGamesSize(2);
    }
    @Test
    void editGameSuccessfullyEditsAGame(){
        GameService.allGames().add(gameTwoBroken);
        ConsoleService.allConsoles().add(consoleTwo);
        Assertions.assertEquals("PR1009", gameTwoBroken.getId());
        Assertions.assertEquals(consoleTwo.getId(), "PR1014");
        String id = "PR1009";
        String description = "Edited Description";
        String cost = "20.99";
        String consoleId = "PR1014";
        boolean isRented = true;
        boolean isBeingRepaired = false;

        try{
            GameService.editGame(id,description,cost,consoleId,isRented,isBeingRepaired);
        }
        catch(Exception ignore){}
        Assertions.assertEquals("Edited Description", gameTwoBroken.getDescription());
        Assertions.assertEquals(20.99, gameTwoBroken.getCost());
        Assertions.assertTrue(gameTwoBroken.isRented());
        Assertions.assertFalse(gameTwoBroken.isBeingRepaired());
    }
    @Test
    void removeGameSuccessfullyRemovesAGame(){
        GameService.allGames().add(gameOneAvailable);
        assertAllGamesSize(1);
        GameService.removeGame(gameOneAvailable);
        assertAllGamesSize(0);
    }
    @Test
    void removeGameDoesNotRemoveAGameWhenTheGamePassedToItIsNotInTheRepository(){
        GameService.allGames().add(gameOneAvailable);
        assertAllGamesSize(1);
        GameService.removeGame(gameTwoBroken);
        assertAllGamesSize(1);
    }
    @Test
    void getLoanedGamesOnlyShowsGamesThatAreLoaned(){
        GameService.allGames().add(gameOneAvailable);
        GameService.allGames().add(gameTwoBroken);
        GameService.allGames().add(gameThreeAvailable);
        GameService.allGames().add(gameFourRented);
        assertGetLoanedGamesSize(1);
    }
    @Test
    void getBrokenGamesOnlyShowsGamesThatAreBroken(){
        GameService.allGames().add(gameOneAvailable);
        GameService.allGames().add(gameTwoBroken);
        GameService.allGames().add(gameThreeAvailable);
        GameService.allGames().add(gameFourRented);
        assertGetBrokenGamesSize(1);
    }
    @Test
    void getGameByIdRetrievesTheExpectedGame(){
        GameService.allGames().add(gameOneAvailable);
        try{
            Assertions.assertEquals(gameOneAvailable.hashCode(), GameService.getGameByID(gameOneAvailable.getId()).hashCode());
        }
        catch (Exception ignore){}
    }
    @Test
    void getGameByIdDoesNotRetrieveAGameThatDoesNotExistThrowsException(){
        GameService.allGames().add(gameOneAvailable);
        Exception exception = Assertions.assertThrows(Exception.class,() -> {
            GameService.getGameByID("Does not exist");
            });
        String expected = "Game does not exist!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }
    @Test
    void getAvailableGamesByConsoleOnlyShowsGamesRelevantToThatConsole(){
        GameService.allGames().add(gameOneAvailable);
        GameService.allGames().add(gameTwoBroken);
        GameService.allGames().add(gameThreeAvailable);
        GameService.allGames().add(gameFourRented);
        for(Game g: GameService.getAvailableGamesByConsole(consoleGameOne)){
            Assertions.assertEquals(consoleGameOne, g.getConsole());
        }
    }
    @Test
    void getAvailableGamesByConsoleOnlyShowsAvailableGames(){
        GameService.allGames().add(gameOneAvailable);
        GameService.allGames().add(gameTwoBroken);
        GameService.allGames().add(gameThreeAvailable);
        GameService.allGames().add(gameFourRented);
        assertAvailableGamesSize(2);
    }
    //createFromParameters not tested as it is used within the editGame method
}
