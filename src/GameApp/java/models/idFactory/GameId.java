package GameApp.java.models.idFactory;

public class GameId implements IId {
    private static int idSeed = 1000;
    private String id;

    public GameId(){
        String prefix = "GA";
        id = prefix + idSeed;
        idSeed++;
    }

    @Override
    public String getId() {
        return id;
    }
}
