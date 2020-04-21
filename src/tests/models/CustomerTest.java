package tests.models;

import GameApp.java.models.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestData;

class CustomerTest {
    final TestData data = new TestData();
    final Customer c1 = data.customerOneC1;
    final Customer c2 = data.customerThreeC1;
    final Customer c3 = data.customerFourC1;

    @Test
    void idIsDifferentForEachCustomer(){
        Assertions.assertFalse(c1.getId().equals(c2.getId()) && c1.getId().equals(c3.getId()) && c2.getId().equals(c3.getId()));
    }
}