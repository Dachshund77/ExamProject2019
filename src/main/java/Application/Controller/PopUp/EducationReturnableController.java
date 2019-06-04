package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Application.Controller.Controllers;
import Domain.Education;
import javafx.scene.Parent;

public abstract class EducationReturnableController extends AbstractController {

    public abstract Education getReturn();

    public abstract Parent getParent();
}
