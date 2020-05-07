package GameApp.java.models;

import GameApp.java.models.idFactory.IId;
import GameApp.java.models.idFactory.IdFactory;

public abstract class Product implements IId, ProductBehaviour, ProductDetails{
    private String id;
    private String description;
    private ProductBehaviour behaviour;


    public Product(String description, ProductBehaviour behaviour){
        id = IdFactory.getId(this).getId();
        this.description = description;
        this.behaviour = behaviour;
    }

    public Product(String id, String description, ProductBehaviour behaviour){
        this.id=id;
        this.description = description;
        this.behaviour = behaviour;
    }

    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return behaviour.getCost(); //returns rental cost, can be used to return sale cost if a new class is created that implements ProductBehaviour Later
    }

    public void setCost(double cost) {
        this.behaviour.setCost(cost);
    }

    @Override
    public boolean is() { //returns true if product IS rented, can be used for if product IS sold later on
        return behaviour.is();
    }

    @Override
    public void setIs(boolean is) {
        behaviour.setIs(is);
    }


    @Override
    public String toString(){
        return String.format("Product ID: %s\n" +
                "Description: %s\n" +
                "Cost: £%.2f\n", id, description, getCost());
    }


}
