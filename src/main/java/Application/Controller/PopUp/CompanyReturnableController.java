package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Application.Controller.Controllers;
import Domain.Company;

public abstract class CompanyReturnableController extends AbstractController{

    public abstract Company getReturn();
}
