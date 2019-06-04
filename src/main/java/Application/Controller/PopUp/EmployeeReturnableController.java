package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Application.Controller.Controllers;
import Domain.Employee;

public abstract class EmployeeReturnableController extends AbstractController {

    public abstract Employee getReturn();
}
