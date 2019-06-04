package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Application.Controller.Controllers;
import Domain.Interview;
import javafx.scene.Parent;

public abstract class InterviewReturnableController extends AbstractController {

    public abstract Interview getReturn();

    public abstract Parent getParent();
}
