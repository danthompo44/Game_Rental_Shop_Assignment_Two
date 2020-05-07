package GameApp.java.repositories;

import GameApp.java.general.exceptions.ExistsInBasketException;
import GameApp.java.models.Basket;
import GameApp.java.models.ProductDetails;
import GameApp.java.models.validators.BasketValidator;

import java.util.ArrayList;

//responsible for keep track of items in the basket and adding them to an array list
public class ProductBasketRepository {
    public static ArrayList<ProductDetails> getProductBasket() {
        return Basket.getProducts();
    }

    public static void addProduct(ProductDetails product) throws Exception {
        if(!getProductBasket().contains(product)){
            BasketValidator.addProduct(product);
            Basket.addProduct(product);
        }
        else{
            throw new ExistsInBasketException(product.getDescription());
        }
    }//method for adding a Product to the Product Basket, runs checks to make sure not too many games/consoles
    // are already rented and if the product already exists in the basket

    public static void removeProduct(ProductDetails product){
        if(getProductBasket().contains(product)){
            BasketValidator.removeProduct(product);
            Basket.removeProduct(product);
        }
    }//checks if product exists and checks what type of product it is,
    //removes the product and lowers the console/game count accordingly.

    //returns true if console limit is reached

    public static void clearBasket(){
        BasketValidator.reset();
        Basket.clearBasket();
    }

    public static boolean consoleLimitReached(){
        return BasketValidator.consoleLimitReached();
    }
}
