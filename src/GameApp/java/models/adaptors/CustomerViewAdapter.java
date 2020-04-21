package GameApp.java.models.adaptors;

import GameApp.java.controllers.interfaces.ICustomerCommunication;
import GameApp.java.models.Customer;
import javafx.scene.control.ListView;


public class CustomerViewAdapter {
    public static String getID(ListView<Customer> lv){
        return lv.getSelectionModel().getSelectedItem().getId();
    }

    public static void getCustomerDetails (Customer c, ICustomerCommunication cc){
        cc.customerDetailsToEdit(c.getId(), c.getForename(), c.getSurname(), c.getAddress());
    }

    public static Customer getCustomer(ListView<Customer> lv){
        return lv.getSelectionModel().getSelectedItem();
    }

    public static String getCustomerFullName (ListView<Customer> lv) {
        return lv.getSelectionModel().getSelectedItem().getFullName();
    }
}
