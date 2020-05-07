package GameApp.java.models.validators;

import GameApp.java.general.exceptions.LimitReachedException;
import GameApp.java.models.Console;
import GameApp.java.models.Game;
import GameApp.java.models.ProductDetails;

public class BasketValidator {
    private static final int CONSOLE_LIMIT = 1;
    private static final int GAME_LIMIT = 3;
    private static int gameCount = 0;
    private static int consoleCount = 0;

    public static void addProduct(ProductDetails product) throws LimitReachedException{
        if(productIsAConsole(product)){
            if(consoleLimitIsNotReached()){
                consoleCount++;
            }
            else{
                throw new LimitReachedException("console");
            }
        }
        else if(productIsAGame(product)){
            if(gameLimitIsNotReached()){
                gameCount++;
            }
            else{
                throw new LimitReachedException("game");
            }
        }
    }
    public static void removeProduct(ProductDetails product){
        if(productIsAConsole(product)){
            consoleCount--;
        }
        else if(productIsAGame(product)){
            gameCount--;
        }
    }

    public static void reset(){
        gameCount=0;
        consoleCount=0;
    }

    private static boolean productIsAConsole(ProductDetails product){
        return product instanceof Console;
    }

    private static boolean productIsAGame(ProductDetails product){
        return product instanceof Game;
    }

    private static boolean consoleLimitIsNotReached(){
        return consoleCount<CONSOLE_LIMIT;
    }
    private static boolean gameLimitIsNotReached(){
        return gameCount<GAME_LIMIT;
    }
    public static boolean consoleLimitReached(){
        return consoleCount == CONSOLE_LIMIT;
    }
    public static boolean gameLimitReached(){
        return gameCount == GAME_LIMIT;
    }
    public static int getGameCount(){
        return gameCount;
    }
    public static int getConsoleCount(){
        return consoleCount;
    }
}
