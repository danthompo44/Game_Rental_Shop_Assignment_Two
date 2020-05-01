package GameApp.java.controllers.interfaces;

import GameApp.java.services.interfaces.IService;
import java.util.ArrayList;

public interface AssignMultipleDependencies {
    public void setDependencies(ArrayList<IService> services);
}
