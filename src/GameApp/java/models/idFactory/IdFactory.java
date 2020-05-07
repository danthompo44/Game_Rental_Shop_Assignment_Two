package GameApp.java.models.idFactory;

import GameApp.java.models.Console;
import GameApp.java.models.Game;
import GameApp.java.models.Rental;

public class IdFactory {
    public static IId getId(IId object) {
        if (object instanceof Console) {
            return new ConsoleId();
        } else if (object instanceof Game) {
            return new GameId();
        } else if (object instanceof Rental) {
            return new RentalId();
        } else {
            return new CustomerID();
        }
    }
}
