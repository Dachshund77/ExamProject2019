package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Domain.DomainObjects.Company;

public abstract class CompanyReturnableController extends AbstractController{

    public abstract Company getReturn();

    public abstract String getURL();

}
