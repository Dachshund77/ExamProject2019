package UI.NEWSTUFF;

import Application.NEWSTUFF.EducationReturnableController;
import Application.NEWSTUFF.EmployeeReturnableController;
import Domain.Education;
import Domain.Employee;
import javafx.stage.Stage;

public class EmployeeChoice extends Stage {

    public Employee showAndReturn(EmployeeReturnableController controller){
        super.showAndWait();
        return controller.getReturn();
    }

    public Employee showAndReturn(EmployeeReturnableController controller, Employee employee){
        controller.initValues(employee);
        super.showAndWait();
        return controller.getReturn();
    }
}
