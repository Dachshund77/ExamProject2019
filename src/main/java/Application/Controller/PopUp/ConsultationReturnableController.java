package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Domain.DomainObjects.Consultation;

public abstract class ConsultationReturnableController extends AbstractController {

    public abstract Consultation getReturn();

    public abstract String getURL();
}
