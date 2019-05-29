package Application.NEWSTUFF;

import Application.Controllers;
import Domain.Consultation;

public interface ConsultationReturnableController extends Controllers {

    Consultation getReturn();
}
