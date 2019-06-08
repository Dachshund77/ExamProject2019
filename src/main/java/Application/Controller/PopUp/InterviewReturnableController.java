package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Domain.DomainObjects.Interview;

public abstract class InterviewReturnableController extends AbstractController {

    public abstract Interview getReturn();

    public abstract String getURL();
}
