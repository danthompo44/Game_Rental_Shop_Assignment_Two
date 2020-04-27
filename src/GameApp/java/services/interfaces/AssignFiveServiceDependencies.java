package GameApp.java.services.interfaces;

public interface AssignFiveServiceDependencies extends AssignThreeServiceDependencies {
    public void setFourthDependency(IService service);
    public void setFifthDependency(IService service);
}
