package tests.models;

import GameApp.java.models.Console;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

class ConsoleTest {
    final TestData testData = new TestData();
    Console console1_64Bit;
    Console console4_Handheld;
    Console consoleStandard;
    Console console256bit;
    Console consolePR1014;
    Console consoleRented;
    Console consoleBeingRepaired;
    Console consoleAvailable;

    @BeforeEach
    void setUp(){
        console1_64Bit = testData.consoleOneC1Available;
        console4_Handheld = testData.consoleTwoC1Available;
        consoleStandard = testData.consoleOneC1Available;
        console256bit = testData.consoleTwoC1Available;
        consolePR1014 = testData.consoleFourC2InForRepair;
        consoleRented = testData.consoleThreeC2Rented;
        consoleBeingRepaired = testData.consoleFourC2InForRepair;
        consoleAvailable = testData.consoleOneC1Available;
    }
    @Test
    void getConsoleIdReturnsCorrectId(){
        Assertions.assertEquals("PR1014", consolePR1014.getId());
    }
    @Test
    void getDescriptionReturnsExpectedDescription(){
        Assertions.assertEquals("Console Four, C2, Being Repaired", consolePR1014.getDescription());
    }
    @Test
    void getCostReturnsExpectedCost(){
        Assertions.assertEquals(12.97, consolePR1014.getCost());
    }
    @Test
    void isRentedReturnsExpectedBoolean(){
        Assertions.assertTrue(consoleRented.isRented());
        Assertions.assertFalse(consoleAvailable.isRented());
    }
    @Test
    void isBeingRepairedReturnsExpectedBoolean(){
        Assertions.assertTrue(consoleBeingRepaired.isBeingRepaired());
        Assertions.assertFalse(consoleAvailable.isBeingRepaired());
    }
    @Test
    void setCostAllowsForTheSettingOfCost(){
        Assertions.assertEquals(12.97, consolePR1014.getCost());
        consolePR1014.setCost(9.99);
        Assertions.assertEquals(9.99, consolePR1014.getCost());
    }
    @Test
    void setDescriptionAllowsForTheSettingOfTheDescription(){
        Assertions.assertEquals("Console Four, C2, Being Repaired", consolePR1014.getDescription());
        consolePR1014.setDescription("Edited");
        Assertions.assertEquals("Edited", consolePR1014.getDescription());
    }
    @Test
    void setIsRentedAllowsFotTheSettingOfIsRented(){
        Assertions.assertFalse(consoleAvailable.isRented());
        consoleAvailable.setIsRented(true);
        Assertions.assertTrue(consoleAvailable.isRented());
    }
    @Test
    void setIsBeingRepairedAllowsForTheSettingOfIsBeingRepaired(){
        Assertions.assertFalse(consoleAvailable.isBeingRepaired());
        consoleAvailable.setIsBeingRepaired(true);
        Assertions.assertTrue(consoleAvailable.isBeingRepaired());
    }
    @Test
    void addToRentalSetsIsRentedToTrue(){
        Assertions.assertFalse(consoleAvailable.isRented());
        consoleAvailable.addToRental();
        Assertions.assertTrue(consoleAvailable.isRented());
    }
    @Test
    void removeFromRentalSetsIsRentedToFalse(){
        Assertions.assertTrue(consoleRented.isRented());
        consoleRented.removeFromRental();
        Assertions.assertFalse(consoleRented.isRented());
    }
    @Test
    void isAvailableReturnsTrueWhenConsoleIsAvailable(){
        Assertions.assertTrue(consoleAvailable.isAvailable());
    }
    @Test
    void isAvailableReturnsFalseWhenConsoleIsRented(){
        Assertions.assertFalse(consoleRented.isAvailable());
    }
    @Test
    void isAvailableReturnsFalseWhenConsoleIsBeingRepaired(){
        Assertions.assertFalse(consoleBeingRepaired.isAvailable());
    }
    @Test
    void bitDepthReturns64bitStringValue(){
        Assertions.assertEquals("64bit", console1_64Bit.getBitDepth());
    }
    @Test
    void bitDepthReturns256bitStringValue(){
        Assertions.assertEquals("256bit", console256bit.getBitDepth());
    }
    @Test
    void formFactorReturnsHandheldStringValue(){
        Assertions.assertEquals("Handheld", console4_Handheld.getFormFactor());
    }
    @Test
    void formFactorReturnsStandardStringValue(){
        Assertions.assertEquals("Standard", consoleStandard.getFormFactor());
    }
}