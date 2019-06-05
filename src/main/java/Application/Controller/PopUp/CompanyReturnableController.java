package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Application.Controller.Controllers;
import Domain.Company;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public abstract class CompanyReturnableController extends AbstractController{

    public abstract Company getReturn();

    public abstract Parent getParent();

}
