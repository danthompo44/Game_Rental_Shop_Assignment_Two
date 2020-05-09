package GameApp.java.services;

import GameApp.java.general.AlertMessage;
import GameApp.java.models.*;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class SessionService {//Used to create Dummy Data when the app starts up
    private final static ArrayList<Game> games = new ArrayList<>();
    private final static ArrayList<Console> consoles = new ArrayList<>();
    private final static ArrayList<Customer> customers = new ArrayList<>();
    private final static ArrayList<Rental> rentals = new ArrayList<>();

    private SessionService(){
        populateConsoles();
        populateGames();
        populateCustomers();
        populateRentals();
    }

    private static class Singleton{
        private static final SessionService INSTANCE = new SessionService();
    }

    public static SessionService getInstance(){
        return Singleton.INSTANCE;
    }

    private void populateConsoles(){
        String [] description = {"Atari 2600", "TurboGrafx-16", "Nintendo SNES", "Atari Lynx", "Sony PS2"};
        double [] cost = {10, 15, 20, 10, 15};
        BitDepth [] bitDepth = {BitDepth.EIGHT, BitDepth.EIGHT, BitDepth.SIXTEEN, BitDepth.SIXTEEN, BitDepth.HUNDRED_TWENTY_EIGHT};
        FormFactor [] formFactor = {FormFactor.STANDARD, FormFactor.HANDHELD, FormFactor.STANDARD, FormFactor.HANDHELD, FormFactor.STANDARD};
        boolean [] isRented = {false, false, false, false, false};
        boolean [] isBeingRepaired = {false, false, false, true, false};
        for(int i = 0; i<cost.length; i++){
            Console c = new Console(description[i], cost[i], bitDepth[i], formFactor[i], isRented[i], isBeingRepaired[i]);
            consoles.add(c);
        }
    }//adds dummy console data to an array and then pass it into an array list


    private void populateGames(){
        String [] description = {"Space Invaders", "R Type", "Sunset Riders",
                                 "Xybots", "Gran Turismo 3"};
        double [] cost = {5,5,5,5,5};
        boolean [] isRented = {false, false, false, false, false};
        boolean [] isBeingRepaired = {false, false, false, true, false};
        for(int i = 0; i<cost.length; i++){
            Game g = new Game(description[i], cost[i], consoles.get(i), isRented[i], isBeingRepaired[i]);
            games.add(g);
        }

        Game gameMissile = new Game("Missile Command", 5, consoles.get(0), false, false);
        Game gameMarioKart = new Game("Super Mario Kart", 10, consoles.get(2), false, false);
        games.add(gameMissile);
        games.add(gameMarioKart);

    }//adds dummy game data to an array and then pass this into an array list


    private void populateCustomers(){
        String [] forename = {"Dan", "Matt", "Lucy", "Isaac", "Dom"};
        String [] surname = {"Thompson", "Morgan", "Reed", "Sharkey", "Needham"};
        String [] address = {"44 Garsdale Road", "15 Fulmar Road", "27 Sandpiper Drive", "18 Gannet Road", "15 Payne Road"};

        for(int i = 0; i<forename.length; i++){
            Customer c = new Customer(forename[i], surname[i], address[i]);
            customers.add(c);
        }
    }//adds dummy customer data to an array then pass that array into an array list

    private void populateRentals(){
        ArrayList<Product> productsRentalOne = new ArrayList<>();
        productsRentalOne.add(consoles.get(1));//add TurboGrafx
        productsRentalOne.add(games.get(1));//add R Type
        ArrayList<Product> productsRentalTwo = new ArrayList<>();
        productsRentalTwo.add(games.get(4));//add Gran Turismo 3
        productsRentalTwo.add(games.get(6));//add Mario Kart
        try{
            Rental r1 = new Rental(customers.get(1), productsRentalOne);
            Rental r2 = new Rental(customers.get(3), productsRentalTwo);
            rentals.add(r1);
            rentals.add(r2);
        }
        catch(Exception e){
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, e.getMessage());
        }
    }

    public ArrayList<Game> getGames() {
        return games;
    }
    public ArrayList<Console> getConsoles(){
        return consoles;
    }
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    public ArrayList<Rental> getRentals(){
        return rentals;
    }
    }


