package GameApp.java.models;

public interface IProduct {
    public String getId();
    public String getDescription();
    public void setDescription(String description);
    public boolean isBeingRepaired();
    public void setIsBeingRepaired(boolean isBeingRepaired);
}
