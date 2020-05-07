package GameApp.java.models;

public interface ProductBehaviour {
    public double getCost();
    public void setCost(double cost);
    public boolean is(); //use to determine if a product IS rented or IS sold(further down the line)
    public void setIs(boolean is); //Used to set a porduct as "IS rented" or "IS sold"(further down the line
}
