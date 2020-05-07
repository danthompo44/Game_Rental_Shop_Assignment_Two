package GameApp.java.models;

import GameApp.java.general.DateHelp;

import java.util.ArrayList;

public class Rental {
    private String id;
    private static int idSeed = 1000;
    private ArrayList<ProductBehaviour> rentalItems = new ArrayList<>();
    private Customer customer;
    private String returnDate;
    private double totalCost;

    public Rental(Customer customer, ArrayList<ProductBehaviour> products) {
        id = "RE" + idSeed;
        idSeed++;
        this.customer = customer;
        rentalItems.addAll(products);
        returnDate = DateHelp.getOneMonthLater();
        setTotalCost((products));
        for(ProductBehaviour p: products){
            p.setIs(true);
        }
    }

    public String getId(){
        return id;
    }

    public Customer getCustomer(){//method for returning a rentals customer object (if they exist).
            return customer;
    }

    public ArrayList<ProductBehaviour> getProducts(){
        return rentalItems;
    }

    public void returnRental(){
        for(ProductBehaviour p: rentalItems){
            p.setIs(false);
        }
    }

    public String getReturnDate(){
        return returnDate;
    }

    private void setTotalCost(ArrayList<ProductBehaviour> products){
        totalCost = 0.0;
        for(ProductBehaviour p: products){
            totalCost+=p.getCost();
        }
    }

    public double getTotalCost(){
        return totalCost;
    }
}