package GameApp.java.models;

import GameApp.java.general.exceptions.ConsoleUnavailableException;
import GameApp.java.models.hashmaps.HashMaps;

public class Console extends Product{
    private String bitDepth;
    private String formFactor;

    public Console(String description, double cost, BitDepth bitDepth, FormFactor formFactor, boolean isRented, boolean isBeingRepaired) {
        super(description, new RentableBehaviour(cost, isRented),  isBeingRepaired);
        this.bitDepth = HashMaps.bitDepthStringMap.get(bitDepth);
        this.formFactor = HashMaps.formFactorStringMap.get(formFactor);
    }

    public Console(String id, String description, double cost, String bitDepth, String formFactor, boolean isRented, boolean isBeingRepaired){
        super(id, description, new RentableBehaviour(cost, isRented),  isBeingRepaired);
        this.bitDepth=HashMaps.bitDepthStringMap.get(bitDepth);
        this.formFactor=HashMaps.formFactorStringMap.get(formFactor);
    }

    public String getBitDepth(){
        return bitDepth;
    }

    public String getFormFactor(){
        return formFactor;
    }

    @Override
    void getMessage() throws Exception {
        if(!isAvailable()){
            throw new ConsoleUnavailableException("Sorry, this console is unavailable");
        }
    }

    @Override
    public String toString(){
        return super.toString() + "Form Factor: " + formFactor;
    }

}
