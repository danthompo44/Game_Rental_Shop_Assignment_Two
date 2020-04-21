package GameApp.java.general.exceptions;

public class DoesNotExistException extends Exception {
    public DoesNotExistException(String product){
        super(product + " does not exist!");
    }
}
