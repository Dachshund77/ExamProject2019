package Application.Controller;

//import Application.ViewController;
import Application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class FindCompController {

    @FXML
    TableView compTable;
    @FXML
    Button okBtn;

    public void initialize(){

    }

    /*
    TableColumn<Integer, Company> companyID = new TableColumn<>("companyID");
    companyID.setCellValueFactory(new propertyValueFactory<>("ID"));
    companyID.getItems().add();

    TableColumn<String, Company> companyCVR = new TableColumn<>("companyCVR");
    companyCVR.setCellValueFactory(New propertyValueFactory<>("CVR No."));
    companyCVR.getItems().add();

    TableColumn<String, Company> companyName = new TableColumn<>("companyName");
    company.setCellValueFactory(New propertyValueFactory<>("Company name"));
    companyName.getItems().add();
    */

    



    public void handleOk(ActionEvent actionEvent) {
   //     ViewController.START_PAGE_CONTROLLER.loadParent();
    }
}
