package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Domain.DomainObjects.Provider;

public abstract class ProviderReturnableController extends AbstractController {

   public abstract Provider getReturn();

    public abstract String getURL();
}
