package GameApp.java.models.idFactory;

import GameApp.java.models.Game;

public abstract class IdFactory {
    public static IId createCustomerId(){
        return new CustomerID();
    }
    public static IId createProductId(IId product){
        if(product instanceof Game){
            return new GameId();
        }
        else{
            return new ConsoleId();
        }
    }
    public static IId createRentalId(){
        return new RentalId();
    }
}
