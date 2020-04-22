package GameApp.java.models;

public interface IProductRental extends IProduct{
    public void addToRental();
    public void removeFromRental();
    public boolean isAvailable();
    public boolean isRented();
    public boolean isBeingRepaired();
    public void setIsRented(boolean isRented);
    public void setIsBeingRepaired(boolean isBeingRepaired);
    public double getRentalCost();
    public void setRentalCost(double cost);
}
