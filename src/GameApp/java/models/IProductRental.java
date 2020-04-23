package GameApp.java.models;

public interface IProductRental extends IProduct{
    public void addToRental();
    public void removeFromRental();
    public boolean isAvailable();
    public boolean isRented();
    public void setIsRented(boolean isRented);
    public double getRentalCost();
    public void setRentalCost(double cost);
}
