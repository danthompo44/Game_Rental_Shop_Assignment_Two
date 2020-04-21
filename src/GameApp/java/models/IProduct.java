package GameApp.java.models;

public interface IProduct {
    public String getId();
    public String getDescription();
    public double getCost();
    public void addToRental();
    public void removeFromRental();
    public boolean isAvailable();
    public boolean isRented();
    public boolean isBeingRepaired();
    public void setCost(double cost);
    public void setDescription(String description);
    public void setIsRented(boolean isRented);
    public void setIsBeingRepaired(boolean isBeingRepaired);


}
