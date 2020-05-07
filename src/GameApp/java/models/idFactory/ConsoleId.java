package GameApp.java.models.idFactory;

public class ConsoleId implements IId {
    private static int idSeed = 1000;
    private String id;

    public ConsoleId(){
        String prefix = "CO";
        id = prefix + idSeed;
        idSeed++;
    }

    @Override
    public String getId() {
        return id;
    }
}
