package Tests;

import GameApp.java.services.SessionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SingletonTest {
    SessionService serviceOne = SessionService.getInstance();
    SessionService serviceTwo = SessionService.getInstance();
    SessionService serviceThree = SessionService.getInstance();
    @Test
    void assertSingletonIsTheSameInstance(){
        Assertions.assertEquals(serviceOne.hashCode(), serviceTwo.hashCode());
        Assertions.assertEquals(serviceOne.hashCode(), serviceThree.hashCode());
        Assertions.assertEquals(serviceTwo.hashCode(), serviceThree.hashCode());
    }
}


