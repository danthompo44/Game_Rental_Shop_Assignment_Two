package GameApp.java.models;

import GameApp.java.models.idFactory.IId;
import GameApp.java.models.idFactory.IdFactory;

public class Customer implements IId {
    private final String id;
    private String forename;
    private String surname;
    private String address;

    public Customer(String forename, String surname, String address){
        this.forename = forename;
        this.surname = surname;
        this.address = address;
        id = IdFactory.createCustomerId().getId();
    }
    public Customer(String id, String forename, String surname, String address){
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.address = address;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName(){
        return  forename + " " + surname;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    @Override
    public String toString(){
        return String.format("(%s) %s %s, %s ", id, forename, surname, address);
    }
}