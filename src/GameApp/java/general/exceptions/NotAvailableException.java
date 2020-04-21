package GameApp.java.general.exceptions;

public class NotAvailableException extends Exception{
    public NotAvailableException(String productType){
        super("I'm sorry, no " + productType + " are available for you to rent!");
    }
}
