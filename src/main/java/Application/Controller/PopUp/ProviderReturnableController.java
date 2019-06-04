package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Application.Controller.Controllers;
import Domain.Provider;

public abstract class ProviderReturnableController extends AbstractController {

   public abstract Provider getReturn();
}
