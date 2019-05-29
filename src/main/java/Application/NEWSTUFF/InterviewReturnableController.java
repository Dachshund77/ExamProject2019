package Application.NEWSTUFF;

import Application.Controllers;
import Domain.Interview;

public interface InterviewReturnableController extends Controllers {

    Interview getReturn();
}
