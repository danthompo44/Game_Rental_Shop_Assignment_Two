package tests.models;

import GameApp.java.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RentalTest {
    final static Customer c1 = new Customer("Test", "One", "Address One");
    final static Customer c2 = new Customer("Test", "Four", "Address Four");
    final static Customer c3 = new Customer("Test", "Three", "Address Three");
    final static Console co1 = new Console("Console One, C1, Rentable", 10.99, BitDepth.SIXTY_FOUR, FormFactor.STANDARD,false, false);
    final static Console co2 = new Console("Console Two, C1, Rentable", 99.91, BitDepth.TWO_HUNDRED_FIFTY_SIX, FormFactor.HANDHELD,false, false);
    final static Game g1 = new Game("GameConstructorOne, Rentable", 19.99, co1,false, false);
    final static Game g2 = new Game("Console Three, C1, Rentable", 81.29, co2,false, false);
    final static Game g3 = new Game("PR1712","Console Four, C2, Is Rented", 19.99, co1, true, false);

    final static ArrayList<IProduct> p1 = new ArrayList<IProduct>();
    final static ArrayList<IProduct> p2 = new ArrayList<IProduct>();
    static Rental r1;
    static Rental r2;
    static Rental r3;

    @BeforeAll
    static void createRentals(){
        p1.add(g1);
        p1.add(co1);
        p1.add(g2);
        r1 = new Rental(c1, p1);

        p2.add(g2);
        p2.add(co2);
        p2.add(g3);
        r2 = new Rental(c2, p2);
        r3 = new Rental(c3, p1);
    }
    @Test
    void checkIdIsDifferentForEachRental(){
        Assertions.assertFalse(r1.getId().equals(r2.getId()) && r1.getId().equals(r3.getId()) && r2.getId().equals(r3.getId()));
    }
    @Test
    void returnRentalSetsProductsWithinRentalToReturned(){
        r1.returnRental();
        for(IProduct p: r1.getProducts()){
            Assertions.assertFalse(p.isRented());
        }
    }
    @Test
    void totalCostCorrectlyCalculatesTotalCostOfRental(){
        //products total cost of r1 should be co1 + g1 + g2 = 112.27
        Assertions.assertEquals("112.27", String.format("%.2f", r1.getTotalCost()));
    }
}
