package GameApp.java.models;

import GameApp.java.models.idFactory.IId;
import GameApp.java.models.idFactory.IdFactory;

public abstract class Product implements IId, ProductBehaviour, ProductDetails, Repairable{
    private String id;
    private String description;
    private boolean isBeingRepaired;
    private ProductBehaviour behaviour;


    public Product(String description, ProductBehaviour behaviour, boolean isBeingRepaired){
        id = IdFactory.getId(this).getId();
        this.description = description;
        this.behaviour = behaviour;
        this.isBeingRepaired = isBeingRepaired;
    }

    public Product(String id, String description, ProductBehaviour behaviour, boolean isBeingRepaired){
        this.id=id;
        this.description = description;
        this.behaviour = behaviour;
        this.isBeingRepaired = isBeingRepaired;
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

    public void executeBehaviour() throws Exception {
        getMessage();
        setIs(true);
    }

    abstract void getMessage() throws Exception;

    @Override
    public boolean is() { //returns true if product IS rented, can be used for if product IS sold later on
        return behaviour.is();
    }

    @Override
    public void setIs(boolean is) {
        behaviour.setIs(is);
    }

    @Override
    public boolean isBeingRepaired() {
        return isBeingRepaired;
    }


    @Override
    public void setIsBeingRepaired(boolean isBeingRepaired) {
        this.isBeingRepaired = isBeingRepaired;
    }

    public boolean isAvailable() {
        return !is()&&!isBeingRepaired;
    }

    @Override
    public String toString(){
        return String.format("Product ID: %s\n" +
                "Description: %s\n" +
                "Cost: £%.2f\n", id, description, getCost());
    }


}
