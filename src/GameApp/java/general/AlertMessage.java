package GameApp.java.general;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertMessage {
    public static void showMessage(Alert.AlertType alertType, String content){
        Alert alert = new Alert(alertType, content);
        alert.showAndWait();
    }
    public static boolean showConfirmationMessage(String content){
        boolean result = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, content, ButtonType.YES, ButtonType.CANCEL);
        alert.setTitle("Confirmation Required");
        alert.setHeaderText("Please Confirm");
        alert.showAndWait();

        if(alert.getResult()==ButtonType.YES){
            result=true;
        }
        return result;
    }
}
