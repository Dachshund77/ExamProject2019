package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Application.Controller.Controllers;
import Domain.Employee;
import javafx.scene.Parent;

public abstract class EmployeeReturnableController extends AbstractController {

    public abstract Employee getReturn();

    public abstract String getURL();
}
