package Application.NEWSTUFF;

import Application.Controllers;
import Domain.Company;

public interface CompanyReturnableController extends Controllers {

    Company getReturn();
}
