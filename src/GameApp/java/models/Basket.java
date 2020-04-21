package GameApp.java.models;

import java.util.ArrayList;

public class Basket {
    private static ArrayList<IProduct> products = new ArrayList<>();

    public Basket(){
    }

    public static void addProduct(IProduct product){
        products.add(product);
    }
    public static void removeProduct(IProduct product){
        products.remove(product);
    }
    public static void clearBasket(){
        products.clear();
    }
    public static ArrayList<IProduct> getProducts(){
        return products;
    }
}
