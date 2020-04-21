package GameApp.java.models;

import GameApp.java.general.DateHelp;

import java.util.ArrayList;

public class Rental {
    private String id;
    private static int idSeed = 1000;
    private ArrayList<IProduct> rentalItems = new ArrayList<>();
    private Customer customer;
    private String returnDate;
    private double totalCost;

    public Rental(Customer customer, ArrayList<IProduct> products){
        id = "RE" + idSeed;
        idSeed++;
        this.customer = customer;
        rentalItems.addAll(products);
        returnDate = DateHelp.getOneMonthLater();
        setTotalCost(products);
        for(IProduct p: products){
            p.addToRental();
        }
    }

    public String getId(){
        return id;
    }

    public Customer getCustomer(){//method for returning a rentals customer object (if they exist).
            return customer;
    }

    public ArrayList<IProduct> getProducts(){
        return rentalItems;
    }

    public void returnRental(){
        for(IProduct p: rentalItems){
            p.removeFromRental();
        }
    }

    public String getReturnDate(){
        return returnDate;
    }

    private void setTotalCost(ArrayList<IProduct> products){
        totalCost = 0.0;
        for(IProduct p: products){
            totalCost+=p.getCost();
        }
    }

    public double getTotalCost(){
        return totalCost;
    }
}