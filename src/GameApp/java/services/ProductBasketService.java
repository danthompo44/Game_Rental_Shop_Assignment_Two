package GameApp.java.services;

import GameApp.java.models.IProduct;
import GameApp.java.repositories.ProductBasketRepository;

import java.util.ArrayList;

//Keeps track of what products the user has been added to their basket
public class ProductBasketService {
    public static ArrayList<IProduct> allBasketItems() {
        return ProductBasketRepository.getProductBasket();
    }

    public static void addProduct(IProduct product) throws Exception{
        ProductBasketRepository.addProduct(product);
    }

    public static void clearBasket(){
        ProductBasketRepository.clearBasket();
    }

    public static void removeProduct(IProduct product){
        ProductBasketRepository.removeProduct(product);
    }

    public static boolean consoleLimitReached(){
        return ProductBasketRepository.consoleLimitReached();
    }



}
