package GameApp.java.models.idFactory;

public class CustomerID implements IId {
    private static int idSeed = 1000;
    private String id;

    public CustomerID(){
        String prefix = "CU";
        id = prefix + idSeed;
        idSeed++;
    }
    @Override
    public String getId() {
        return id;
    }
}
