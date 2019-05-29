package Application.NEWSTUFF;

import Application.Controllers;
import Domain.Provider;

public interface ProviderReturnableController extends Controllers {

    Provider getReturn();
}
