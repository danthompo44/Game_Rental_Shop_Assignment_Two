package GameApp.java.models;

public class RentableBehaviour implements ProductBehaviour {
    private double rentalCost;
    private boolean isRented;

    public RentableBehaviour(double rentalCost, boolean isRented){
        this.rentalCost = rentalCost;
        this.isRented = isRented;
    }

    @Override
    public double getCost() {
        return rentalCost;
    }

    @Override
    public void setCost(double cost) {
        this.rentalCost = cost;
    }

    @Override
    public boolean is() { // product is Rented as opposed to isSold
        return isRented;
    }

    @Override
    public void setIs(boolean is) {
        isRented = is;
    }
}
