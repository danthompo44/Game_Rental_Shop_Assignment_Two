package GameApp.java.models;

import GameApp.java.general.DateHelp;
import GameApp.java.models.idFactory.IId;
import GameApp.java.models.idFactory.IdFactory;

import java.util.ArrayList;

public class Rental implements IId {
    private String id;
    private ArrayList<ProductBehaviour> rentalItems = new ArrayList<>();
    private Customer customer;
    private String returnDate;
    private double totalCost;

    public Rental(Customer customer, ArrayList<ProductBehaviour> products) {
        id = IdFactory.getId(this).getId();
        this.customer = customer;
        rentalItems.addAll(products);
        returnDate = DateHelp.getOneMonthLater();
        setTotalCost((products));
        for(ProductBehaviour p: products){
            p.setIs(true);
        }
    }

    @Override
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

    @Override
    public String toString(){
        return String.format("Rental ID: %s\n" +
                "Customer ID: %s\n" +
                "Customer Name: %s\n" +
                "Return Date: %s", id, customer.getId(), customer.getFullName(), returnDate);

    }
}