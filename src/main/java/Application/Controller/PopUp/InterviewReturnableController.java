package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Application.Controller.Controllers;
import Domain.Interview;

public abstract class InterviewReturnableController extends AbstractController {

    public abstract Interview getReturn();
}
