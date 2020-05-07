package GameApp.java.models;

import java.util.ArrayList;

public class Basket {
    private static ArrayList<ProductDetails> products = new ArrayList<>();

    public Basket(){
    }

    public static void addProduct(ProductDetails product){
        products.add(product);
    }
    public static void removeProduct(ProductDetails product){
        products.remove(product);
    }
    public static void clearBasket(){
        products.clear();
    }
    public static ArrayList<ProductDetails> getProducts(){
        return products;
    }
}
