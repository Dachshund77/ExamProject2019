package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Domain.DomainObjects.EducationWish;
import Domain.DomainObjects.FinishedEducation;
import Domain.DomainObjects.Interview;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class InterviewSub extends AbstractController {

    public Text interviewID;
    public TextField interViewNameTextField;
    public Tooltip interViewNameTooltip;
    public ComboBox productUnderstandingComboBox;
    public ComboBox problemUnderstandingComboBox;
    public ComboBox qualityAwarenessComboBox;
    public ComboBox cooperationComboBox;
    public ComboBox flexibilityComboBox;
    public TableView<FinishedEducation> finishedEducationTableView;
    public TableColumn<FinishedEducation, String> finishedEducationNameColumn;
    public TableColumn<FinishedEducation, LocalDate> finishedEducationDateColumn;
    public TableView<EducationWish> educationWishTableView;
    public TableColumn<EducationWish, String> educationWishNameColumn;
    public TableColumn<EducationWish, Integer> educationWishPriorityColumn;
    public Button addEducationWishbutton;
    public Button removeEducationWishButton;
    public Button pickEduForWishButton;
    public Button addFinishedEducationButton;
    public Button removeFinishedEducationButton;
    public Button pickEduForFinishedEducationButton;
    public DatePicker dateForFinishedEducation;

    public ArrayList<EducationWish> educationWishArrayList;
    public ArrayList<FinishedEducation> finishedEducationArrayList;

    public Interview selectedInterview;

    private String defaultComboBoxText = "1 - " + Interview.getQualityMaxValue();

    public BooleanBinding isValid;
    private SimpleBooleanProperty interViewNameIsValid = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty productUnderstandingIsValid = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty problemUnderstandingIsValid = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty qualityAwarenessIsValid = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty cooperationIsValid = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty flexibilityIsValid = new SimpleBooleanProperty(false);

    ObservableList<Integer> numberList;

    public void initialize(){

        setQualityNumberList();

        interViewNameTextField.textProperty().addListener((observable -> handleInterviewNameInput()));

        //setup listview
        //Setup isValid
        //setup bindings
        isValid = new BooleanBinding() {
            {
                bind(interViewNameIsValid);
                bind(productUnderstandingIsValid);
                bind(problemUnderstandingIsValid);
                bind(qualityAwarenessIsValid);
                bind(cooperationIsValid);
                bind(flexibilityIsValid);
            }
            @Override
            protected boolean computeValue() {
                if (interViewNameIsValid.get() && productUnderstandingIsValid.get() && problemUnderstandingIsValid.get()
                        && qualityAwarenessIsValid.get() && cooperationIsValid.get() && flexibilityIsValid.get()){
                    System.out.println("true");
                    return true;
                } else {
                    System.out.println("false");
                    return false;
                }
            }
        };

        resetForm();

        productUnderstandingComboBox.getItems().setAll(numberList);
        problemUnderstandingComboBox.getItems().setAll(numberList);
        qualityAwarenessComboBox.getItems().setAll(numberList);
        cooperationComboBox.getItems().setAll(numberList);
        flexibilityComboBox.getItems().setAll(numberList);
    }

    @Override
    public void initValues(Interview interview) {
        // hook up interview
        selectedInterview = interview;
                resetForm();
    }

    /**
     * On every keypress in the TextField interViewNameTextField,
     * this method updates due to a listener.
     * Checks if the input is valid or not.
     * If the input is invalid, it displays a tooltip with the error and makes the field red
     */
    public void handleInterviewNameInput(){
        if(Interview.isValidInterviewName(interViewNameTextField.getText()))
        {
            interViewNameTextField.setTooltip(null);
            interViewNameIsValid.set(true);
            interViewNameTextField.getStyleClass().remove("TextField-Error");
        } else {
            String invalidCause = Interview.interviewNameInvalidCause(interViewNameTextField.getText());
            interViewNameTextField.setTooltip(new Tooltip(invalidCause));
            interViewNameIsValid.set(false);
            if(!interViewNameTextField.getStyleClass().contains("TextField-Error"))
                interViewNameTextField.getStyleClass().add("TextField-Error");
        }
    }

    /**
     * Checks if the input is valid or not.
     * If the input is wrong or has not changed, a tooltip is displayed.
     * @param event When changing the value in the ComboBox
     */
    public void handleProductUnderstandingBox(ActionEvent event)
    {
        if(!productUnderstandingComboBox.getValue().toString().equals("1 - " + Interview.getQualityMaxValue())) {
            int chosenNum = Integer.parseInt(productUnderstandingComboBox.getValue().toString());
            if (Interview.isValidProductUnderstanding(chosenNum)) {
                productUnderstandingComboBox.setTooltip(null);
                productUnderstandingIsValid.set(true);
                productUnderstandingComboBox.getStyleClass().removeAll("TextField-Error");
            } else {
                String invalidCause = Interview.productUnderstandingInvalidCause(chosenNum);
                productUnderstandingComboBox.setTooltip(new Tooltip(invalidCause));
                productUnderstandingIsValid.set(false);
                if (!productUnderstandingComboBox.getStyleClass().contains("TextField-Error"))
                    productUnderstandingComboBox.getStyleClass().add("TextField-Error");
            }
        }
    }

    /**
     * Checks if the input is valid or not.
     * If the input is wrong or has not changed, a tooltip is displayed.
     * @param event When changing the value in the ComboBox
     */
    public void handleProblemUnderstandingBox(ActionEvent event)
    {
        if(!productUnderstandingComboBox.getValue().toString().equals("1 - " + Interview.getQualityMaxValue())) {
            int chosenNum = Integer.parseInt(problemUnderstandingComboBox.getValue().toString());
            if (Interview.isValidProblemUnderstanding(chosenNum)) {
                problemUnderstandingComboBox.setTooltip(null);
                problemUnderstandingIsValid.set(true);
                problemUnderstandingComboBox.getStyleClass().removeAll("TextField-Error");
            } else {
                String invalidCause = Interview.problemUnderstandingInvalidCause(chosenNum);
                problemUnderstandingComboBox.setTooltip(new Tooltip(invalidCause));
                problemUnderstandingIsValid.set(false);
                if (!problemUnderstandingComboBox.getStyleClass().contains("TextField-Error"))
                    problemUnderstandingComboBox.getStyleClass().add("TextField-Error");
            }
        }

    }

    /**
     * Checks if the input is valid or not.
     * If the input is wrong or has not changed, a tooltip is displayed.
     * @param event When changing the value in the ComboBox
     */
    public void handleQualityAwarenessBox(ActionEvent event)
    {
        if(!productUnderstandingComboBox.getValue().toString().equals("1 - " + Interview.getQualityMaxValue())) {
            int chosenNum = Integer.parseInt(qualityAwarenessComboBox.getValue().toString());
            if (Interview.isValidQualityAwareness(chosenNum)) {
                qualityAwarenessIsValid.set(true);
                qualityAwarenessComboBox.setTooltip(null);
                qualityAwarenessComboBox.getStyleClass().removeAll("TextField-Error");
            } else {
                String invalidCause = Interview.qualityAwarenessInvalidCause(chosenNum);
                qualityAwarenessComboBox.setTooltip(new Tooltip(invalidCause));
                qualityAwarenessIsValid.set(false);
                if (!qualityAwarenessComboBox.getStyleClass().contains("TextField-Error"))
                    qualityAwarenessComboBox.getStyleClass().add("TextField-Error");
            }
        }
    }

    /**
     * Checks if the input is valid or not.
     * If the input is wrong or has not changed, a tooltip is displayed.
     * @param event When changing the value in the ComboBox
     */
    public void handleCooperationBox(ActionEvent event)
    {
        if(!productUnderstandingComboBox.getValue().toString().equals("1 - " + Interview.getQualityMaxValue())) {
            int chosenNum = Integer.parseInt(cooperationComboBox.getValue().toString());
            if (Interview.isValidCooperation(chosenNum)) {
                cooperationComboBox.setTooltip(null);
                cooperationIsValid.set(true);
                cooperationComboBox.getStyleClass().removeAll("TextField-Error");
            } else {
                String invalidCause = Interview.cooperationInvalidCause(chosenNum);
                cooperationComboBox.setTooltip(new Tooltip(invalidCause));
                cooperationIsValid.set(false);
                if (!cooperationComboBox.getStyleClass().contains("TextField-Error"))
                    cooperationComboBox.getStyleClass().add("TextField-Error");
            }
        }
    }

    /**
     * Checks if the input is valid or not.
     * If the input is wrong or has not changed, a tooltip is displayed.
     * @param event When changing the value in the ComboBox
     */
    public void handleFlexibilityBox(ActionEvent event)
    {
        if(!productUnderstandingComboBox.getValue().toString().equals("1 - " + Interview.getQualityMaxValue())) {
            int chosenNum = Integer.parseInt(flexibilityComboBox.getValue().toString());
            if (Interview.isValidFlexibility(chosenNum)) {
                flexibilityComboBox.setTooltip(null);
                flexibilityIsValid.set(true);
                flexibilityComboBox.getStyleClass().removeAll("TextField-Error");
            } else {
                String invalidCause = Interview.productUnderstandingInvalidCause(chosenNum);
                flexibilityComboBox.setTooltip(new Tooltip(invalidCause));
                flexibilityIsValid.set(false);
                if (!flexibilityComboBox.getStyleClass().contains("TextField-Error"))
                    flexibilityComboBox.getStyleClass().add("TextField-Error");
            }
        }
    }
    public void handleAddEducationWish(ActionEvent event){

    }

    public void handleAddFinishedEducation(ActionEvent event){

    }

    public void handleRemoveEducationWish(ActionEvent event){

    }

    public void handleRemoveFinishedEducation(ActionEvent event){

    }

    public void handlePickFinishedEducation(ActionEvent actionEvent) {

    }

    /**
     * A check for the if the fields are valid
     * @param bool
     */
    public void setDisabled(boolean bool){
        interViewNameTextField.setDisable(bool);
        productUnderstandingComboBox.setDisable(bool);
        problemUnderstandingComboBox.setDisable(bool);
        qualityAwarenessComboBox.setDisable(bool);
        cooperationComboBox.setDisable(bool);
        flexibilityComboBox.setDisable(bool);
        addEducationWishbutton.setDisable(bool);
        addEducationWishbutton.setVisible(false);
        removeEducationWishButton.setDisable(bool);
        removeEducationWishButton.setVisible(false);
        pickEduForWishButton.setDisable(bool);
        pickEduForWishButton.setVisible(false);
        addFinishedEducationButton.setDisable(bool);
        addFinishedEducationButton.setVisible(false);
        removeFinishedEducationButton.setDisable(bool);
        removeFinishedEducationButton.setVisible(false);
        pickEduForFinishedEducationButton.setDisable(bool);
        pickEduForFinishedEducationButton.setVisible(false);
        educationWishTableView.setVisible(false);
        educationWishTableView.setDisable(bool);
        finishedEducationTableView.setVisible(false);
        finishedEducationTableView.setDisable(bool);
        dateForFinishedEducation.setVisible(false);
        dateForFinishedEducation.setDisable(bool);
    }

    /**
     * Initalizes a ArrayList to provide the ComboBoxes with numbers.
     * Numbers are depending on QUALITY_MAX_VALUE in Interview.java
     */
    public void setQualityNumberList()
    {
        ArrayList<Integer> setNumberList = new ArrayList<>();
        for (int i = 1; i < Interview.getQualityMaxValue() + 1; i++) {
            setNumberList.add(i);
        }
        numberList = FXCollections.observableArrayList(setNumberList);
    }

    /**
     * Method to reset fields and comboboxes, either manually or through startup.
     * Checks if the user got there by selecting an interview or if they want to create a new.
     */
    public void resetForm(){
        if(selectedInterview != null)
        {
            interViewNameTextField.setText(selectedInterview.getInterviewName());
            productUnderstandingComboBox.setValue(selectedInterview.getProductUnderstanding());
            problemUnderstandingComboBox.setValue(selectedInterview.getProductUnderstanding());
            qualityAwarenessComboBox.setValue(selectedInterview.getProductUnderstanding());
            cooperationComboBox.setValue(selectedInterview.getProductUnderstanding());
            flexibilityComboBox.setValue(selectedInterview.getProductUnderstanding());

        }
        else
        {
            interViewNameTextField.setText("");
            productUnderstandingComboBox.setValue(defaultComboBoxText); //FIXME : Does not reset to default text in ComboBoxes, if a value has been picked.
            problemUnderstandingComboBox.setValue(defaultComboBoxText);
            qualityAwarenessComboBox.setValue(defaultComboBoxText);
            cooperationComboBox.setValue(defaultComboBoxText);
            flexibilityComboBox.setValue(defaultComboBoxText);

            String startToolTipComboBox = "Change to a number between 1 and " + Interview.getQualityMaxValue();
            productUnderstandingComboBox.setTooltip(new Tooltip(startToolTipComboBox));
            problemUnderstandingComboBox.setTooltip(new Tooltip(startToolTipComboBox));
            qualityAwarenessComboBox.setTooltip(new Tooltip(startToolTipComboBox));
            cooperationComboBox.setTooltip(new Tooltip(startToolTipComboBox));
            flexibilityComboBox.setTooltip(new Tooltip(startToolTipComboBox));

            productUnderstandingIsValid.set(false);
            problemUnderstandingIsValid.set(false);
            qualityAwarenessIsValid.set(false);
            cooperationIsValid.set(false);
            flexibilityIsValid.set(false);

        }
    }
}
