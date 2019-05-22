package Application.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewCompController {

    @FXML
    TextField getName;
    @FXML
    TextField getCVR;
    @FXML
    Button addBtn;

    /**
     * Retrive the text from the textfields
     * then insert them into tbl_Company
     * @param actionEvent
     */


    public void getText(ActionEvent actionEvent) {

        String name = getName.getText();
        String cvr = getCVR.getText();

        /*
        DB database = DB.getInstance();
        try{
            database.connect();
            DbFacade.insertProvider(new Provider(id,fullName));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                database.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

         */


    }
}
