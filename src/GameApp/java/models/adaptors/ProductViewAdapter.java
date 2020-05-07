package GameApp.java.models.adaptors;

import GameApp.java.models.ProductDetails;
import javafx.scene.control.ListView;

public class ProductViewAdapter {
    public static String getProductDescription(ListView<ProductDetails> lv){
        return lv.getSelectionModel().getSelectedItem().getDescription();
    }

    public static ProductDetails getProductObject(ListView<ProductDetails> lv){
        return lv.getSelectionModel().getSelectedItem();
    }
}
