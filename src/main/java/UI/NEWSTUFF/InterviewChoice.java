package UI.NEWSTUFF;

import Application.NEWSTUFF.EmployeeReturnableController;
import Application.NEWSTUFF.InterviewReturnableController;
import Domain.Employee;
import Domain.Interview;
import javafx.stage.Stage;

public class InterviewChoice extends Stage {

    public Interview showAndReturn(InterviewReturnableController controller){
        super.showAndWait();
        return controller.getReturn();
    }

    public Interview showAndReturn(InterviewReturnableController controller, Interview interview){
        controller.initValues(interview);
        super.showAndWait();
        return controller.getReturn();
    }
}
