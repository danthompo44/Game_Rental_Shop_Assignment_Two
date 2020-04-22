package GameApp.java.models;

import GameApp.java.general.DateHelp;

import java.util.ArrayList;

public class Rental {
    private String id;
    private static int idSeed = 1000;
    private ArrayList<IProductRental> rentalItems = new ArrayList<>();
    private Customer customer;
    private String returnDate;
    private double totalCost;

    public Rental(Customer customer, ArrayList<IProductRental> products){
        id = "RE" + idSeed;
        idSeed++;
        this.customer = customer;
        rentalItems.addAll(products);
        returnDate = DateHelp.getOneMonthLater();
        setTotalCost(products);
        for(IProductRental p: products){
            p.addToRental();
        }
    }

    public String getId(){
        return id;
    }

    public Customer getCustomer(){//method for returning a rentals customer object (if they exist).
            return customer;
    }

    public ArrayList<IProductRental> getProducts(){
        return rentalItems;
    }

    public void returnRental(){
        for(IProductRental p: rentalItems){
            p.removeFromRental();
        }
    }

    public String getReturnDate(){
        return returnDate;
    }

    private void setTotalCost(ArrayList<IProductRental> products){
        totalCost = 0.0;
        for(IProductRental p: products){
            totalCost+=p.getRentalCost();
        }
    }

    public double getTotalCost(){
        return totalCost;
    }
}