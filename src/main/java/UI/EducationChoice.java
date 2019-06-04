package UI;

import Application.Controller.PopUp.EducationReturnableController;
//import Application.NEWSTUFF.Controller.EducationReturnableController;
import Domain.Education;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EducationChoice extends Stage {

    public Education showAndReturn(EducationReturnableController controller){
        Parent root = controller.getParent();
        super.setTitle("Select education");
        super.setScene(new Scene(root, 600, 400));
        initModality(Modality.APPLICATION_MODAL);
        super.showAndWait();
        return controller.getReturn();
    }

    public Education showAndReturn(EducationReturnableController controller, Education education){
        controller.initValues(education);
        super.showAndWait();
        return controller.getReturn();
    }
}
