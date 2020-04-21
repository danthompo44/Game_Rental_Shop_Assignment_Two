package GameApp.java.general.exceptions;

public class ExistsInBasketException extends Exception {
    public ExistsInBasketException(String productDescription){
        super(productDescription + " is already in your basket!");
    }
}
