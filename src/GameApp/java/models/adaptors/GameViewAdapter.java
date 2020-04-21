package GameApp.java.models.adaptors;

import GameApp.java.controllers.interfaces.IGameCommunication;
import GameApp.java.models.Game;
import javafx.scene.control.ListView;


public class GameViewAdapter {
    public static Game getGameObject(ListView<Game> lv){
        return lv.getSelectionModel().getSelectedItem();
    }

    public static String getGameID(ListView<Game> lv){
        return lv.getSelectionModel().getSelectedItem().getId();
    }

    public static String getGameDescription(ListView<Game> lv){
        return lv.getSelectionModel().getSelectedItem().getDescription();
    }

    public static void getGameDetails(Game g, IGameCommunication gc){
        gc.gameDetailsToEdit(g.getId(), g.getDescription(), g.getCost(), g.getConsole().getId(), g.getConsole().getDescription(), g.isRented(), g.isBeingRepaired());
    }
}
