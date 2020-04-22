package GameApp.java.models;

import GameApp.java.general.CostFormatter;

public class Game implements IProductRental {
    private Console console;
    private ProductDetails details;
    private boolean isRented;
    private boolean isBeingRepaired;

    public Game(String description, double rentalCost, Console console, boolean isRented, boolean isBeingRepaired) {
        details = new ProductDetails(description, rentalCost);
        this.console = console;
        this.isRented = isRented;
        this.isBeingRepaired = isBeingRepaired;
    }

    public Game(String id, String description, double rentalCost, Console console, boolean isRented, boolean isBeingRepaired){
        details = new ProductDetails(id, description, rentalCost);
        this.console = console;
        this.isRented = isRented;
        this.isBeingRepaired = isBeingRepaired;
    }
    @Override
    public String getId() {
        return details.getId();
    }

    @Override
    public String getDescription() {
        return details.getDescription();
    }

    @Override
    public double getRentalCost() {
        return details.getRentalCost();
    }

    public Console getConsole(){
        return console;
    }

    @Override
    public boolean isRented() {
        return isRented;
    }

    @Override
    public boolean isBeingRepaired() {
        return isBeingRepaired;
    }

    @Override
    public void setRentalCost(double cost) {
        details.setRentalCost(cost);
    }

    @Override
    public void setDescription(String description) {
        details.setDescription(description);
    }

    @Override
    public void setIsRented(boolean isRented) {
        this.isRented = isRented;
    }

    @Override
    public void setIsBeingRepaired(boolean isBeingRepaired) {
        this.isBeingRepaired = isBeingRepaired;
    }

    @Override
    public void addToRental() {
        this.isRented = true;
    }

    @Override
    public void removeFromRental() {
        this.isRented = false;
    }

    @Override
    public boolean isAvailable() {
        return !isRented&&!isBeingRepaired;
    }

    @Override
    public String toString(){
        return String.format("Product ID:  %s\n" +
                "Description:  %s\n" +
                "Cost:  £%s\n" +
                "Game's Platform:  %s", details.getId(), details.getDescription(), CostFormatter.format(details.getRentalCost()), console.getDescription());
    }
}
