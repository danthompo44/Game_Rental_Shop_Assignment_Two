package GameApp.java.models.idFactory;

public class RentalId implements IId {
    private static int idSeed = 1000;
    private String id;

    public RentalId(){
        String prefix = "RE";
        id = prefix + idSeed;
        idSeed++;
    }

    @Override
    public String getId() {
        return id;
    }
}
