package GameApp.java.models;

import GameApp.java.models.hashmaps.HashMaps;

public class Console extends Product implements Repairable{
    private String bitDepth;
    private String formFactor;
    private boolean isBeingRepaired;

    public Console(String description, double cost, BitDepth bitDepth, FormFactor formFactor, boolean isRented, boolean isBeingRepaired) {
        super(description, new RentableBehaviour(cost, isRented));
        this.bitDepth = HashMaps.bitDepthStringMap.get(bitDepth);
        this.formFactor = HashMaps.formFactorStringMap.get(formFactor);
        this.isBeingRepaired = isBeingRepaired;
    }

    public Console(String id, String description, double cost, String bitDepth, String formFactor, boolean isRented, boolean isBeingRepaired){
        super(id, description, new RentableBehaviour(cost, isRented));
        this.bitDepth=HashMaps.bitDepthStringMap.get(bitDepth);
        this.formFactor=HashMaps.formFactorStringMap.get(formFactor);
        this.isBeingRepaired = isBeingRepaired;
    }

    @Override
    public boolean isBeingRepaired(){
        return isBeingRepaired;
    }

    public String getBitDepth(){
        return bitDepth;
    }

    public String getFormFactor(){
        return formFactor;
    }

    @Override
    public void setIsBeingRepaired(boolean isBeingRepaired) {
        this.isBeingRepaired = isBeingRepaired;
    }

    @Override
    public boolean isAvailable() {
        return !super.is()&&!isBeingRepaired;
    }

}
