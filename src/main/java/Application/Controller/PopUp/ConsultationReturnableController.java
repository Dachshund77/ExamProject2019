package Application.Controller.PopUp;

import Application.Controller.Controllers;
import Domain.Consultation;

public interface ConsultationReturnableController extends Controllers {

    Consultation getReturn();
}
