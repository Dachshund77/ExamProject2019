package Application.Controller.PopUp;

import Application.Controller.AbstractController;
import Application.Controller.Controllers;
import Domain.Provider;
import javafx.scene.Parent;

public abstract class ProviderReturnableController extends AbstractController {

   public abstract Provider getReturn();

    public abstract String getURL();
}
