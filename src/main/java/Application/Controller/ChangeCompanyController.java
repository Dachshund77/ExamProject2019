package Application.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ChangeCompanyController {

    @FXML
    Label searchValue;
    @FXML
    Label currentSearchValue;
    @FXML
    Label newSearchValue;
    @FXML
    TextField searchField;
    @FXML
    TextField currentSearchField;
    @FXML
    TextField newSearchField;
    @FXML
    RadioButton CVRSearch;
    @FXML
    RadioButton companySearch;


    /**
     * When the CVR radiobutton is selected (on by default)
     * The user must search for a CVR from the company
     * they wish to change
     *
     * @param actionEvent
     */

    public void handleCVR(ActionEvent actionEvent) {
        companySearch.selectedProperty().setValue(false);

        searchValue.setText("CVR no.");
        currentSearchValue.setText("Current company name.");
        newSearchValue.setText("New company name");

    }

    /**
     * Almost identical to the previous handler except
     * that if the "company" button is selected
     * The user have to search for the company name
     * <p>
     * The labels are changed as well
     * for each radio button
     *
     * @param actionEvent
     */

    public void handleCompanySearch(ActionEvent actionEvent) {
        CVRSearch.selectedProperty().setValue(false);

        searchValue.setText("Company name");
        currentSearchValue.setText("Current CVR no.");
        newSearchValue.setText("New CVR no");
    }

    /**
     * get the text from the fields
     * depending on which radiobutton is toggled
     * <p>
     * update the data in the database
     * with the new data
     *
     * @param actionEvent
     */
    public void handleSearch(ActionEvent actionEvent) {

        if (CVRSearch.selectedProperty().getValue()) {
            String oldCompany = currentSearchField.getText();
            String newCompany = newSearchField.getText();

        } else if (companySearch.selectedProperty().getValue()) {
            int oldCVR = Integer.parseInt(currentSearchField.getText());
            int newCVR = Integer.parseInt(newSearchField.getText());


        }

    }
}
