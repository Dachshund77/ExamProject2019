package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Application.Controller.Controllers;
import Domain.Company;
import javafx.scene.Parent;

public abstract class CompanyReturnableController extends AbstractController{

    public abstract Company getReturn();

    public abstract Parent getParent();
}
