package GameApp.java.models.adaptors;

import GameApp.java.controllers.interfaces.IControllerCommunication;
import GameApp.java.models.Console;
import javafx.scene.control.ListView;

public class ConsoleViewAdapter {//Used to allow the Controller classes to have no knowledge of the console model, returns Strings/Objects from a listview
    public static String getID(ListView<Console> lv){//return the id of the selected console within a list view
        return lv.getSelectionModel().getSelectedItem().getId();
    }

    public static Console getConsoleObject(ListView<Console> lv){//return the selected console object within a listview
        return lv.getSelectionModel().getSelectedItem();
    }

    public static String getConsoleDescription(ListView<Console> lv){//return the selected console description within a listview
        return lv.getSelectionModel().getSelectedItem().getDescription();
    }

    public static void getConsoleDetails(Console c, IControllerCommunication cc){
        cc.detailsToEdit(c.getId(), c.getDescription(), c.getRentalCost(), c.getBitDepth(), c.getFormFactor(), c.isRented(), c.isBeingRepaired());
    }
}
