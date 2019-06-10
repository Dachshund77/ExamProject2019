package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Domain.DomainObjects.Employee;

public abstract class EmployeeReturnableController extends AbstractController {

    public abstract Employee getReturn();

    public abstract String getURL();
}
