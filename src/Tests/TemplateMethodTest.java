package Tests;

import GameApp.java.models.BitDepth;
import GameApp.java.models.Console;
import GameApp.java.models.FormFactor;
import GameApp.java.models.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TemplateMethodTest {
    Console console = new Console("Console One", 99.99, BitDepth.EIGHT, FormFactor.HANDHELD,
            true, false);
    Console consoleTwo = new Console("Console One", 99.99, BitDepth.EIGHT, FormFactor.HANDHELD,
            false, false);
    Game game = new Game("Game one", 20.99, console, true, true);

    @Test
    void thrownExceptionsDifferBetweenGameAndConsole(){
        Exception exception = Assertions.assertThrows(Exception.class,() -> {
            console.executeBehaviour();
        });
        String expected = "Sorry, this console is unavailable";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));

        exception = Assertions.assertThrows(Exception.class,() -> {
           game.executeBehaviour();
        });
        expected = "Sorry, this Game is unavailable!";
        actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }
    void assertProductIsSetTorRentedAndNoExceptionIsThrownWhenProductIsAvailable(){
        try{
            consoleTwo.executeBehaviour();
            Assertions.assertTrue(consoleTwo.is());
        }
        catch(Exception ignore){}
    }
}


