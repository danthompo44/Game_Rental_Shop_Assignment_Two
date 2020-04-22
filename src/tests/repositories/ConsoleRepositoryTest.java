package tests.repositories;

import GameApp.java.general.exceptions.DoesNotExistException;
import GameApp.java.models.Console;
import GameApp.java.repositories.ConsoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

public class ConsoleRepositoryTest {
    final TestData data = new TestData();
    final Console consoleOne = data.consoleOneC1Available;
    final Console consoleTwo = data.consoleTwoC1Available;
    final Console consoleThree = data.consoleThreeC2Rented;
    final Console consoleFour = data.consoleFourC2InForRepair;
    final Console consoleFourWithEditValues = data.consoleFourWithEditValues;

    @BeforeEach
    void setUp(){
        ConsoleRepository.getAllConsoles().clear();
        assertGetAllConsolesSize(0);
    }
    private void assertGetAllConsolesSize(int i){
        Assertions.assertEquals(i, ConsoleRepository.getAllConsoles().size());
    }
    private void assertGetLoanedConsolesSize(int i){
        Assertions.assertEquals(i, ConsoleRepository.getLoanedConsoles().size());
    }
    private void assertGetBrokenConsolesSize(int i){
        Assertions.assertEquals(i, ConsoleRepository.getBrokenConsoles().size());
    }
    @Test
    void consoleRepositoryContainsTheCorrectNumberOfConsoles(){
        ConsoleRepository.getAllConsoles().add(consoleOne);
        assertGetAllConsolesSize(1);
        ConsoleRepository.getAllConsoles().add(consoleTwo);
        assertGetAllConsolesSize(2);
        ConsoleRepository.getAllConsoles().add(consoleThree);
        assertGetAllConsolesSize(3);
    }
    @Test
    void consoleRepositoryAvailableConsolesMethodOnlyReturnsAvailableConsoles(){
        //add available console
        ConsoleRepository.getAllConsoles().add(consoleOne);
        //add rented Console
        ConsoleRepository.getAllConsoles().add(consoleThree);
        //add being repaired console
        ConsoleRepository.getAllConsoles().add(consoleFour);
        Assertions.assertEquals(1, ConsoleRepository.availableConsoles().size());
        assertGetAllConsolesSize(3);
    }
    @Test
    void correctConsoleIsReturnedWhenGivenThatConsolesId(){
        //add console
        ConsoleRepository.addConsole(consoleOne);
        try{
            Assertions.assertEquals(consoleOne.hashCode(), ConsoleRepository.getConsoleByID(consoleOne.getId()).hashCode());
        }
        catch(Exception ignore){}
    }
    @Test
    void correctExceptionIsThrownIfConsoleIdDoesNotExist(){
        Exception exception = Assertions.assertThrows(DoesNotExistException.class, () -> {
           ConsoleRepository.getConsoleByID("Does Not Exist");
        });
        String expected = "Console does not exist!";
        String actual = exception.getMessage();

        Assertions.assertTrue(actual.contains(expected));
    }
    @Test
    void consoleIsAddedToGetAllConsoleCorrectly(){
        //add console
        ConsoleRepository.addConsole(consoleOne);
        assertGetAllConsolesSize(1);
    }
    @Test
    void consoleIsCorrectlyEditedWhenIdsMatchAndNewDataIsGiven(){
        ConsoleRepository.addConsole(consoleFour);
        ConsoleRepository.editConsole(consoleFourWithEditValues);

        Assertions.assertEquals("EditedDescription", consoleFour.getDescription());
        Assertions.assertEquals(999.99, consoleFour.getRentalCost());
        Assertions.assertTrue(consoleFour.isRented());
        Assertions.assertFalse(consoleFour.isBeingRepaired());
    }
    @Test
    void consoleIsRemovedCorrectlyWhenRemoveConsoleMethodIsCalled(){
        //add console
        ConsoleRepository.getAllConsoles().add(consoleOne);
        assertGetAllConsolesSize(1);
        //remove console
        ConsoleRepository.removeConsole(consoleOne);
        assertGetAllConsolesSize(0);
    }
    @Test
    void consoleIsCorrectlyAddedToOnLoanListWhenOnLoan(){
        //add a console that is on loan
        ConsoleRepository.getAllConsoles().add(consoleThree);
        assertGetLoanedConsolesSize(1);
    }
    @Test
    void consoleIsCorrectlyRemovedFromOnLoanArrayList(){
        //confirm arrayList is empty
        assertGetLoanedConsolesSize(0);
        //add a console that is on loan
        ConsoleRepository.addConsole(consoleThree);
        assertGetLoanedConsolesSize(1);
        ConsoleRepository.removeConsole(consoleThree);
        assertGetLoanedConsolesSize(0);
    }
    @Test
    void consoleIsNotAddedToOnLoanListWhenIsNotOnLoan(){
        //confirm arrayList is empty
        assertGetLoanedConsolesSize(0);
        //add a console that is not on loan
        ConsoleRepository.getAllConsoles().add(consoleTwo);
        assertGetLoanedConsolesSize(0);
        //add a console that is on loan but is in for repair
        ConsoleRepository.getAllConsoles().add(consoleFour);
        assertGetLoanedConsolesSize(0);
    }
    @Test
    void consoleIsCorrectlyAddedToBeingRepairedArrayListIfBeingRepaired(){
        //confirm arrayList is empty
        assertGetBrokenConsolesSize(0);
        //add a console that is in for repair
        ConsoleRepository.addConsole(consoleFour);
        assertGetBrokenConsolesSize(1);
    }
    @Test
    void consoleIsCorrectlyRemovedFromBeingRepairedArrayList(){
        //confirm arrayList is empty
        assertGetBrokenConsolesSize(0);
        //add a console that is in for repair
        ConsoleRepository.addConsole(consoleFour);
        assertGetBrokenConsolesSize(1);
        ConsoleRepository.removeConsole(consoleFour);
        assertGetBrokenConsolesSize(0);
    }
    @Test
    void consoleIsNotAddedToBeingRepairedListWhenIsNotBeingRepaired(){
        //confirm arrayList is empty
        assertGetBrokenConsolesSize(0);
        //add a console that is not in for repair
        ConsoleRepository.getAllConsoles().add(consoleTwo);
        assertGetBrokenConsolesSize(0);
        //add a console that is not in for repair but is rented
        ConsoleRepository.getAllConsoles().add(consoleThree);
        assertGetBrokenConsolesSize(0);
    }
}
