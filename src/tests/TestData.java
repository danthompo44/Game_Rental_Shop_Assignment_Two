package tests;

import GameApp.java.models.*;

import java.util.ArrayList;

public class TestData {//holds all data used for testing
    public final Console consoleOneC1Available = new Console("Console One, C1, Rentable", 10.99, BitDepth.SIXTY_FOUR, FormFactor.STANDARD,false, false);
    public final Console consoleTwoC1Available = new Console("Console Two, C1, Rentable", 99.91, BitDepth.TWO_HUNDRED_FIFTY_SIX, FormFactor.HANDHELD,false, false);
    public final Console consoleThreeC2Rented = new Console("PR1001", "Console Three, C2, Is Rented", 0.32, "8bit", "Handheld", true, false);
    public final Console consoleFourC2InForRepair = new Console("PR1014", "Console Four, C2, Being Repaired", 12.97, "8bit", "Standard", false, true);
    public final Console consoleFourWithEditValues = new Console("PR1014", "EditedDescription", 999.99, "8bit", "Standard", true, false);
    public final Game gameOneC1ConsoleOneAvailable = new Game("GameConstructorOne, Rentable", 19.99, consoleOneC1Available,false, false);
    public final Game gameTwoC2ConsoleTwoInForRepair = new Game("PR1009", "Game Two, C2, Being Repaired", 14.99, consoleTwoC1Available, false, true);
    public final Game gameThreeC1ConsoleThreeAvailable = new Game("Console Three, C1, Rentable", 81.29, consoleThreeC2Rented,false, false);
    public final Game gameFourC2ConsoleFourRented = new Game("PR1712", "Console Four, C2, Is Rented", 19.99, consoleFourC2InForRepair, true, false);
    public final Game gameTwoC2ConsoleTwoInForRepairEdited = new Game("PR1009", "Edited Description", 20.99, consoleTwoC1Available, true, false);

    public final Customer customerOneC1 = new Customer("Test", "One", "Address One");
    public final Customer customerTwoC2 = new Customer("CU1012", "Test", "Two", "Address Two");
    public final Customer customerThreeC1 = new Customer("Test", "Three", "Address Three");
    public final Customer customerFourC1 = new Customer("Test", "Four", "Address Four");
    public final Customer customerTwoC2Edit = new Customer("CU1012", "Test", "Two", "Address Two Edited");

    public final ArrayList<IProduct> productListOneConsoleThreeGames = new ArrayList<IProduct>();
    public final ArrayList<IProduct> productListNoConsoleThreeGames = new ArrayList<IProduct>();
    public final ArrayList<IProduct> productListOneConsoleNoGames = new ArrayList<IProduct>();
    public Rental rentalOne;
    public Rental rentalTwo;
    public Rental rentalThree;

    public TestData() {
        // add products to first product list (One console, Three games)
        productListOneConsoleThreeGames.add(consoleOneC1Available);
        productListOneConsoleThreeGames.add(gameOneC1ConsoleOneAvailable);
        productListOneConsoleThreeGames.add(gameTwoC2ConsoleTwoInForRepair);
        productListOneConsoleThreeGames.add(gameThreeC1ConsoleThreeAvailable);
        // add products to second product list(No Console Three Games)
        productListNoConsoleThreeGames.add(gameOneC1ConsoleOneAvailable);
        productListNoConsoleThreeGames.add(gameTwoC2ConsoleTwoInForRepair);
        productListNoConsoleThreeGames.add(gameFourC2ConsoleFourRented);
        //add product to third product list(One Console, No Games);
        productListOneConsoleNoGames.add(consoleFourC2InForRepair);
    }

    public void createRentals() {
        //productListOne - rental constructor turns
        rentalOne = new Rental(customerOneC1, productListOneConsoleThreeGames);
        rentalTwo = new Rental(customerTwoC2, productListNoConsoleThreeGames);
        rentalThree = new Rental(customerFourC1, productListOneConsoleNoGames);
    }
}
