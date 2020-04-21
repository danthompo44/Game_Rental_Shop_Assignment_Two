package tests.services;

import GameApp.java.models.Console;
import GameApp.java.services.ConsoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

public class ConsoleServiceTest {
    final TestData data = new TestData();
    final Console consoleOneAvailable = data.consoleOneC1Available;
    final Console consoleTwoBeingRepaired = data.consoleFourC2InForRepair;
    final Console consoleThreeRented = data.consoleThreeC2Rented;
    final Console consoleFourAvailable = data.consoleTwoC1Available;

    @BeforeEach
    void setUp(){
        ConsoleService.allConsoles().clear();
        assertAllConsolesSize(0);
    }
    private void assertAllConsolesSize(int i){
        Assertions.assertEquals(i, ConsoleService.allConsoles().size());
    }
    private void assertAvailableConsolesSize(int i){
        Assertions.assertEquals(i, ConsoleService.availableConsoles().size());
    }
    private void assertLoanedConsolesSize(int i){
        Assertions.assertEquals(i, ConsoleService.getLoanedConsoles().size());
    }
    private void assertBrokenConsoleSize(int i){
        Assertions.assertEquals(i, ConsoleService.getBrokenConsoles().size());
    }
    @Test
    void allConsolesContainsCorrectNumberOfConsoles(){
        ConsoleService.allConsoles().add(consoleOneAvailable);
        assertAllConsolesSize(1);
        ConsoleService.allConsoles().add(consoleTwoBeingRepaired);
        assertAllConsolesSize(2);
    }
    @Test
    void availableConsolesReturnsExpectedNumberOfAvailableConsoles(){
        ConsoleService.allConsoles().add(consoleOneAvailable);
        assertAvailableConsolesSize(1);
        ConsoleService.allConsoles().add(consoleFourAvailable);
        assertAvailableConsolesSize(2);
        ConsoleService.allConsoles().add(consoleTwoBeingRepaired);
        assertAvailableConsolesSize(2);
        ConsoleService.allConsoles().add(consoleThreeRented);
        assertAvailableConsolesSize(2);
        ConsoleService.allConsoles().remove(consoleOneAvailable);
        assertAvailableConsolesSize(1);
        ConsoleService.allConsoles().remove(consoleFourAvailable);
        assertAvailableConsolesSize(0);
    }
    @Test
    void getConsoleByIdReturnsExpectedConsole(){
        ConsoleService.allConsoles().add(consoleOneAvailable);
        assertAvailableConsolesSize(1);
        try{
            Assertions.assertEquals(consoleOneAvailable.hashCode(), ConsoleService.getConsoleByID(consoleOneAvailable.getId()).hashCode());
        }
        catch(Exception ignore){}
    }
    @Test
    void getConsoleByIdDoesNotRetrieveAConsoleThatDoesNotExistThrowsException(){
        ConsoleService.allConsoles().add(consoleThreeRented);
        Exception exception = Assertions.assertThrows(Exception.class,() -> {
            ConsoleService.getConsoleByID("Does not exist");
        });
        String expected = "Console does not exist!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }
    @Test
    void editConsoleSuccessfullyEditsAConsole(){
        ConsoleService.allConsoles().add(consoleThreeRented);
        //create edit parameters
        String id = "PR1001";
        String description = "Edited Description";
        String cost = "9.99";
        String bitDepth = "8bit";
        String formFactor = "Handheld";
        boolean isRented = false;
        boolean isBeingRepaired = true;
        try{ConsoleService.editConsole(id,description,cost,bitDepth,formFactor,isRented, isBeingRepaired); }
        catch(Exception ignore){}

        //check edit has occurred
        Assertions.assertEquals("Edited Description", consoleThreeRented.getDescription());
        Assertions.assertEquals(9.99, consoleThreeRented.getCost());
        Assertions.assertFalse(consoleThreeRented.isRented());
        Assertions.assertTrue(consoleThreeRented.isBeingRepaired());
    }
    @Test
    void removeConsoleSuccessfullyRemovesAConsole(){
        ConsoleService.allConsoles().add(consoleOneAvailable);
        assertAvailableConsolesSize(1);
        ConsoleService.removeConsole(consoleOneAvailable);
        assertAvailableConsolesSize(0);
    }
    @Test
    void getLoanedConsolesReturnsExpectedLoanedConsoles(){
        ConsoleService.allConsoles().add(consoleOneAvailable);
        assertLoanedConsolesSize(0);
        ConsoleService.allConsoles().add(consoleFourAvailable);
        assertLoanedConsolesSize(0);
        ConsoleService.allConsoles().add(consoleTwoBeingRepaired);
        assertLoanedConsolesSize(0);
        ConsoleService.allConsoles().add(consoleThreeRented);
        assertLoanedConsolesSize(1);
        ConsoleService.allConsoles().remove(consoleOneAvailable);
        assertLoanedConsolesSize(1);
        ConsoleService.allConsoles().remove(consoleThreeRented);
        assertLoanedConsolesSize(0);
    }
    @Test
    void getBrokenConsolesReturnsExpectedBrokenConsoles(){
        ConsoleService.allConsoles().add(consoleOneAvailable);
        assertBrokenConsoleSize(0);
        ConsoleService.allConsoles().add(consoleFourAvailable);
        assertBrokenConsoleSize(0);
        ConsoleService.allConsoles().add(consoleTwoBeingRepaired);
        assertBrokenConsoleSize(1);
        ConsoleService.allConsoles().add(consoleThreeRented);
        assertBrokenConsoleSize(1);
        ConsoleService.allConsoles().remove(consoleOneAvailable);
        assertBrokenConsoleSize(1);
        ConsoleService.allConsoles().remove(consoleTwoBeingRepaired);
        assertBrokenConsoleSize(0);
    }
    //Did not test createConsoleFromParameters due to it being private and being accessed through an earlier test
}
