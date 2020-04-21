package GameApp.java.models;

public class ProductDetails {
    private String id;
    private static int idSeed = 1000;
    private String description;
    private double cost;

    public ProductDetails(String description, double cost){
        this.id = "PR" + idSeed;
        this.description = description;
        this.cost = cost;
        idSeed++;
    }
    public ProductDetails(String id, String description, double cost){
        this.id = id;
        this.description = description;
        this.cost = cost;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
