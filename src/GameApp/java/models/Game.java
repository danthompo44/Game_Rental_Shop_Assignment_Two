package GameApp.java.models;

import GameApp.java.general.exceptions.GameUnavailableException;

public class Game extends Product {
    private Console console;


    public Game(String description, double rentalCost, Console console, boolean isRented, boolean isBeingRepaired) {
        super(description, new RentableBehaviour(rentalCost, isRented),  isBeingRepaired);
        this.console = console;
    }

    public Game(String id, String description, double rentalCost, Console console, boolean isRented, boolean isBeingRepaired){
        super(id, description, new RentableBehaviour(rentalCost, isRented),  isBeingRepaired);
        this.console = console;
    }

    public Console getConsole(){
        return console;
    }


    @Override
    void getMessage() throws Exception {
        if(!isAvailable()){
            throw new GameUnavailableException("Sorry, this Game is unavailable!");
        }
    }

    @Override
    public String toString(){
        return super.toString() + "Console: " + console.getDescription() + "\n";
    }

}

