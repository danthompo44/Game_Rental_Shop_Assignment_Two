package GameApp.java.models;

public class Customer {
    private final String id;
    private String forename;
    private String surname;
    private String address;
    private static int nextId = 1000;

    public Customer(String forename, String surname, String address){
        this.forename = forename;
        this.surname = surname;
        this.address = address;
        id = "CU" + nextId;
        nextId++;
    }
    public Customer(String id, String forename, String surname, String address){
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.address = address;
    }

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