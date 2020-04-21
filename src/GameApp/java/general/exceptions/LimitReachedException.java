package GameApp.java.general.exceptions;

public class LimitReachedException extends Exception {
    public LimitReachedException(String productType){
        super("Sorry, you've reached your " + productType + " limit for this rental!\n");
    }
}
