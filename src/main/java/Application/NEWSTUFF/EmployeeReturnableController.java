package Application.NEWSTUFF;

import Application.Controllers;
import Domain.Employee;

public interface EmployeeReturnableController extends Controllers {

    Employee getReturn();
}
