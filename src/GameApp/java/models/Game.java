package GameApp.java.models;

public class Game extends Product implements Repairable {
    private Console console;
    private boolean isBeingRepaired;

    public Game(String description, double rentalCost, Console console, boolean isRented, boolean isBeingRepaired) {
        super(description, new RentableBehaviour(rentalCost, isRented));
        this.console = console;
        this.isBeingRepaired = isBeingRepaired;
    }

    public Game(String id, String description, double rentalCost, Console console, boolean isRented, boolean isBeingRepaired){
        super(id, description, new RentableBehaviour(rentalCost, isRented));
        this.console = console;
        this.isBeingRepaired = isBeingRepaired;
    }

    public Console getConsole(){
        return console;
    }


    @Override
    public boolean isBeingRepaired() {
        return isBeingRepaired;
    }


    @Override
    public void setIsBeingRepaired(boolean isBeingRepaired) {
        this.isBeingRepaired = isBeingRepaired;
    }

    @Override
    public boolean isAvailable() {
        return !super.is()&&!isBeingRepaired;
    }



    @Override
    public String toString(){
        return super.toString() + console.getDescription() + "\n";
    }

}


