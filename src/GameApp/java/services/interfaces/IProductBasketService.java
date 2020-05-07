package GameApp.java.services.interfaces;

import GameApp.java.models.ProductDetails;
import java.util.ArrayList;

public interface IProductBasketService extends IService {
    public ArrayList<ProductDetails> allBasketItems();
    public void addProduct(ProductDetails product) throws Exception;
    public void clearBasket();
    public void removeProduct(ProductDetails product);
    public boolean consoleLimitReached();
}
