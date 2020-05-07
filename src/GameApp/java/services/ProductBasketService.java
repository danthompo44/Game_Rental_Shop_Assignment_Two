package GameApp.java.services;

import GameApp.java.models.ProductDetails;
import GameApp.java.repositories.ProductBasketRepository;
import GameApp.java.services.interfaces.IProductBasketService;

import java.util.ArrayList;

//Keeps track of what products the user has been added to their basket
public class ProductBasketService extends SuperService implements IProductBasketService {

    @Override
    public ArrayList getAll() {
        return ProductBasketRepository.getProductBasket();
    }

    public void addProduct(ProductDetails product) throws Exception{
        ProductBasketRepository.addProduct(product);
    }

    public void clearBasket(){
        ProductBasketRepository.clearBasket();
    }

    public void removeProduct(ProductDetails product){
        ProductBasketRepository.removeProduct(product);
    }

    public boolean consoleLimitReached(){
        return ProductBasketRepository.consoleLimitReached();
    }
}
