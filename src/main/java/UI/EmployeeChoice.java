package UI;

import Application.Controller.PopUp.EmployeeReturnableController;
//import Application.NEWSTUFF.Controller.EmployeeReturnableController;
import Domain.Employee;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeChoice extends Stage {

    public Employee showAndReturn(EmployeeReturnableController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EmployeeReturnableController loadedController = loader.getController();
        setTitle("Select company");
        Scene scene = new Scene(root, 600, 500);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
        return loadedController.getReturn();
    }

    public Employee showAndReturn(EmployeeReturnableController controller, Employee employee){
        controller.initValues(employee);
        super.showAndWait();
        return controller.getReturn();
    }
}
