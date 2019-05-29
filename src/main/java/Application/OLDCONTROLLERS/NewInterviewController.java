package Application.OLDCONTROLLERS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class NewInterviewController {



    @FXML
    TextField getName;
    @FXML
    TextField getID;
    @FXML
    TextField problemRating;
    @FXML
    TextField productRating;
    @FXML
    TextField qualityRating;
    @FXML
    TextField flexibilityRating;
    @FXML
    TextField cooperationRating;
    @FXML
    ComboBox companyDrop;
    @FXML
    ComboBox consultationDrop;


    /**
     * This function will get the data from the textfields
     * it will then insert the data in the "Interview" table in our DB
     * @param actionEvent
     */
    public void getText(ActionEvent actionEvent) {

        String fullName = getName.getText();
        int id = Integer.parseInt(getID.getText());

        int problem = Integer.parseInt(problemRating.getText());
        int product = Integer.parseInt(productRating.getText());
        int flexibility = Integer.parseInt(flexibilityRating.getText());
        int quality = Integer.parseInt(qualityRating.getText());
        int cooperation = Integer.parseInt(cooperationRating.getText());
        System.out.println(problem);
        System.out.println(product);
        System.out.println(flexibility);
        System.out.println(quality);
        System.out.println(cooperation);


        /**
         * Here we call the DbFacade class
         * it will connect to the DB, the insert the stored procedure
         */

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