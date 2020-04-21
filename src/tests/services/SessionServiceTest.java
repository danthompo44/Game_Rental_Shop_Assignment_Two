package tests.services;

import GameApp.java.services.SessionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestData;

import java.util.ArrayList;

public class SessionServiceTest {
    final TestData data = new TestData();
    static SessionService service;
    @BeforeEach
    void setUp(){
        SessionService.getConsoles().clear();
        assertArrayListSize(0, SessionService.getConsoles());
        SessionService.getCustomers().clear();
        assertArrayListSize(0, SessionService.getCustomers());
        SessionService.getGames().clear();
        assertArrayListSize(0, SessionService.getGames());
        SessionService.getRentals().clear();
        assertArrayListSize(0, SessionService.getRentals());
        service = new SessionService();
    }
    void assertArrayListSize(int i, ArrayList list){
        Assertions.assertEquals(i, list.size());
    }
    @Test
    void sessionServiceCreatesExpectedAmountOfConsoles(){
        Assertions.assertEquals(5, SessionService.getConsoles().size());
    }
    @Test
    void sessionServiceCreatesExpectedAmountOfGames(){
        Assertions.assertEquals(7, SessionService.getGames().size());
    }
    @Test
    void sessionServiceCreatesExpectedAmountOfCustomers(){
        Assertions.assertEquals(5, SessionService.getCustomers().size());
    }
    @Test
    void sessionServiceCreatesNoRentals(){
        Assertions.assertEquals(2, SessionService.getRentals().size());
    }
}