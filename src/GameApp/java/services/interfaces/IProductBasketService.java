package GameApp.java.services.interfaces;

import GameApp.java.models.IProduct;
import java.util.ArrayList;

public interface IProductBasketService extends IService {
    public ArrayList<IProduct> allBasketItems();
    public void addProduct(IProduct product) throws Exception;
    public void clearBasket();
    public void removeProduct(IProduct product);
    public boolean consoleLimitReached();
}
