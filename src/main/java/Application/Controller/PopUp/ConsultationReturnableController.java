package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Application.Controller.Controllers;
import Domain.Consultation;
import javafx.scene.Parent;

public abstract class ConsultationReturnableController extends AbstractController {

    public abstract Consultation getReturn();

    public abstract Parent getParent();
}
