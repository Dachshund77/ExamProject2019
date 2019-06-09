package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Domain.DomainObjects.Education;

public abstract class EducationReturnableController extends AbstractController {

    public abstract Education getReturn();

    public abstract String getURL();
}
