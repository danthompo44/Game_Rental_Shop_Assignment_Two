package tests.repositories;

import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.Console;
import GameApp.java.models.Game;
import GameApp.java.repositories.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

public class GameRepositoryTest {
    final TestData data = new TestData();
    final Game gameAvailable = data.gameOneC1ConsoleOneAvailable;
    final Game gameRented = data.gameFourC2ConsoleFourRented;
    final Game gameRepair = data.gameTwoC2ConsoleTwoInForRepair;
    final Game gamePR1712 = data.gameFourC2ConsoleFourRented;
    final Game gameConsoleOne = data.gameOneC1ConsoleOneAvailable;
    final Game gameNotConsoleOne = data.gameFourC2ConsoleFourRented;
    final Console consoleOne = data.consoleOneC1Available;
    final Game gamePR1712Edited = new Game("PR1712", "Edited", 999.99, data.consoleFourC2InForRepair, false, true);

    @BeforeEach
    void setUp(){
        GameRepository.getAllGames().clear();
        assertGetAllGameSize(0);
    }
    private void assertGetAllGameSize(int i){
        Assertions.assertEquals(i, GameRepository.getAllGames().size());
    }
    private void assertAvailableGamesSize(int i){
        Assertions.assertEquals(i, GameRepository.availableGames().size());
    }
    private void assertGetLoanedGamesSize(int i){
        Assertions.assertEquals(i, GameRepository.getLoanedGames().size());
    }
    private void assertGetBrokenGamesSize(int i){
        Assertions.assertEquals(i, GameRepository.getBrokenGames().size());
    }
    @Test
    void gameRepositoryContainsTheCorrectNumberOfConsoles(){
        GameRepository.getAllGames().add(gameAvailable);
        assertGetAllGameSize(1);
        GameRepository.getAllGames().add(gameRented);
        assertGetAllGameSize(2);
    }
    @Test
    void gameThatIsNotRentedOrBeingRepairedIsAddedToGamesAvailableList(){
        GameRepository.getAllGames().add(gameAvailable);
        assertAvailableGamesSize(1);
    }
    @Test
    void gameThatIsRentedIsNotAddedToGamesAvailable(){
        //check games available is 0
        assertAvailableGamesSize(0);
        GameRepository.getAllGames().add(gameRented);
        assertAvailableGamesSize(0);
    }
    @Test
    void gameThatIsBeingRepairedIsNotAddedToGamesAvailable(){
        //check games available is 0
        assertAvailableGamesSize(0);

        GameRepository.getAllGames().add(gameRepair);
        assertAvailableGamesSize(0);
    }
    @Test
    void correctGameIsReturnedIfMatchingIdIsPassedToGetGameById(){
        GameRepository.getAllGames().add(gamePR1712);
        try{
            Assertions.assertEquals(gamePR1712.hashCode(), GameRepository.getGameByID("PR1712").hashCode());
        }
        catch(Exception ignore){}
    }
    @Test
    void throwsExpectedExceptionIfNoMatchingIdIsFoundInGameRepository(){
        Exception exception = Assertions.assertThrows(DoesNotExistException.class, () -> {
           GameRepository.getGameByID("Does Not Exist");
        });
        String expected = "Game does not exist!";
        String actual = exception.getMessage();

        Assertions.assertTrue(actual.contains(expected));
    }
    @Test
    void gameIsAddedToGetGamesByConsoleIfItIsAssociatedWithThatConsole(){
        //add game which has a reference to consoleOne within
        GameRepository.getAllGames().add(gameConsoleOne);
        Assertions.assertEquals(1, GameRepository.getAvailableGamesByPlatform(consoleOne).size());
    }
    @Test
    void gameIsNotAddedToGetGamesByConsoleIfItIsNotAssociatedWithThatGame(){
        //check all games is empty
        Assertions.assertEquals(0, GameRepository.getAllGames().size());
        //add unassociated game
        GameRepository.getAllGames().add(gameNotConsoleOne);
        Assertions.assertEquals(0, GameRepository.getAvailableGamesByPlatform(consoleOne).size());
    }
    @Test
    void gameInformationIsEditedCorrectlyWhenIdMatches(){
        //add game to repository
        GameRepository.getAllGames().add(gamePR1712);
        //edit game with same id
        Assertions.assertEquals("PR1712", gamePR1712.getId());
        Assertions.assertEquals("PR1712", gamePR1712Edited.getId());
        GameRepository.editGame(gamePR1712Edited);
        Assertions.assertEquals("Edited", gamePR1712.getDescription());
        Assertions.assertEquals(999.99, gamePR1712.getCost());
        Assertions.assertFalse(gamePR1712.isRented());
        Assertions.assertTrue(gamePR1712.isBeingRepaired());
    }
    @Test
    void gameIsRemovedFromGetAllGamesWhenGamePassedIsCorrect(){
        //add game to repository
        GameRepository.getAllGames().add(gamePR1712);
        assertGetAllGameSize(1);
        //remove game
        GameRepository.removeGame(gamePR1712);
        assertGetAllGameSize(0);
    }
    @Test
    void gameIsNotRemovedFromGetAllGamesWhenGamePassedIsIncorrect(){
        //add game to repository
        GameRepository.getAllGames().add(gamePR1712);
        assertGetAllGameSize(1);
        //remove game
        GameRepository.removeGame(gamePR1712Edited);
        assertGetAllGameSize(1);
    }
    @Test
    void gameIsAddedToGetLoanedGamesWhenItIsOnLoan(){
        //check get loaned games is empty
        assertGetLoanedGamesSize(0);
        GameRepository.getAllGames().add(gameRented);
        assertGetLoanedGamesSize(1);
    }
    @Test
    void gameIsNotAddedToGetLoanedGamesWhenItIsNotOnLoanButIsOutForRepair(){
        //check get loaned games is empty
        assertGetLoanedGamesSize(0);
        GameRepository.getAllGames().add(gameRepair);
        assertGetLoanedGamesSize(0);
    }
    @Test
    void gameIsNotAddedToGetLoanedGamesWhenItIsNotOnLoanOrBeingRepaired(){
        //check get loaned games is empty
        assertGetLoanedGamesSize(0);
        GameRepository.getAllGames().add(gameAvailable);
        assertGetLoanedGamesSize(0);
    }
    @Test
    void gameIsAddedToBrokenGamesWhenItsBroken(){
        //check Broken games is Empty
        assertGetBrokenGamesSize(0);
        GameRepository.getAllGames().add(gameRepair);
        assertGetBrokenGamesSize(1);
    }
    @Test
    void gameIsNotAddedToBrokenGamesWhenItIsNotBrokenButIsOutOnLoan(){
        //check Broken games is Empty
        assertGetBrokenGamesSize(0);
        GameRepository.getAllGames().add(gameRented);
        assertGetBrokenGamesSize(0);
    }
    @Test
    void gameIsNotAddedToBrokenGamesWhenItIsNotBrokenOrOnLoan(){
        //check Broken games is Empty
        assertGetBrokenGamesSize(0);
        GameRepository.getAllGames().add(gameAvailable);
        assertGetBrokenGamesSize(0);
    }
}
