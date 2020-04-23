package GameApp.java.models;

import GameApp.java.general.CostFormatter;
import GameApp.java.models.hashmaps.HashMaps;

public class Console implements IProductRental{
    private String bitDepth;
    private String formFactor;
    private boolean isRented;
    private boolean isBeingRepaired;
    private ProductDetails details;

    public Console(String description, double cost, BitDepth bitDepth, FormFactor formFactor, boolean isRented, boolean isBeingRepaired) {
        details = new ProductDetails(description, cost);
        this.bitDepth = HashMaps.bitDepthStringMap.get(bitDepth);
        this.formFactor = HashMaps.formFactorStringMap.get(formFactor);
        this.isRented = isRented;
        this.isBeingRepaired = isBeingRepaired;
    }

    public Console(String id, String description, double cost, String bitDepth, String formFactor, boolean isRented, boolean isBeingRepaired){
        details = new ProductDetails(id, description, cost);
        this.bitDepth=HashMaps.bitDepthStringMap.get(bitDepth);
        this.formFactor=HashMaps.formFactorStringMap.get(formFactor);
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
    public double getRentalCost(){
        return details.getRentalCost();
    }

    @Override
    public boolean isRented(){
        return isRented;
    }

    @Override
    public boolean isBeingRepaired(){
        return isBeingRepaired;
    }

    public String getBitDepth(){
        return bitDepth;
    }

    public String getFormFactor(){
        return formFactor;
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
                "Cost:  £%s", details.getId(), details.getDescription(), CostFormatter.format(details.getRentalCost()));
    }
}
