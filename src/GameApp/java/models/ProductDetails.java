package GameApp.java.models;

public class ProductDetails {
    private String id;
    private static int idSeed = 1000;
    private String description;
    private double rentalCost;

    public ProductDetails(String description, double rentalCost){
        this.id = "PR" + idSeed;
        this.description = description;
        this.rentalCost = rentalCost;
        idSeed++;
    }
    public ProductDetails(String id, String description, double rentalCost){
        this.id = id;
        this.description = description;
        this.rentalCost = rentalCost;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(double rentalCost) {
        this.rentalCost = rentalCost;
    }
}
