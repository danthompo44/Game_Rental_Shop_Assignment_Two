package GameApp.java.services;

import GameApp.java.models.IProduct;
import GameApp.java.repositories.ProductBasketRepository;
import GameApp.java.services.interfaces.IProductBasketService;

import java.util.ArrayList;

//Keeps track of what products the user has been added to their basket
public class ProductBasketService implements IProductBasketService {
    public ArrayList<IProduct> allBasketItems() {
        return ProductBasketRepository.getProductBasket();
    }

    public void addProduct(IProduct product) throws Exception{
        ProductBasketRepository.addProduct(product);
    }

    public void clearBasket(){
        ProductBasketRepository.clearBasket();
    }

    public void removeProduct(IProduct product){
        ProductBasketRepository.removeProduct(product);
    }

    public boolean consoleLimitReached(){
        return ProductBasketRepository.consoleLimitReached();
    }
}
