package UI;

import Application.Controller.PopUp.EmployeeReturnableController;
//import Application.NEWSTUFF.Controller.EmployeeReturnableController;
import Domain.Employee;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmployeeChoice extends Stage {

    public Employee showAndReturn(EmployeeReturnableController controller){
        Parent root = controller.getParent();
        super.setTitle("Select employee");
        super.setScene(new Scene(root, 600, 500));
        initModality(Modality.APPLICATION_MODAL);
        super.showAndWait();
        return controller.getReturn();
    }

    public Employee showAndReturn(EmployeeReturnableController controller, Employee employee){
        controller.initValues(employee);
        super.showAndWait();
        return controller.getReturn();
    }
}
