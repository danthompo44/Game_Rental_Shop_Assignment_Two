package GameApp.java.models.adaptors;

import GameApp.java.models.IProduct;
import javafx.scene.control.ListView;

public class ProductViewAdapter {
    public static String getProductDescription(ListView<IProduct> lv){
        return lv.getSelectionModel().getSelectedItem().getDescription();
    }

    public static IProduct getProductObject(ListView<IProduct> lv){
        return lv.getSelectionModel().getSelectedItem();
    }
}
