package Application.Controller.SubControllers.Domain;

import Application.Controller.AbstractController;
import Application.Controller.PopUp.Find.FindEducationPopUp;
import Application.Controller.PopUp.Find.FindEmployeePopUp;
import Domain.DomainObjects.*;
import UI.EducationChoice;
import UI.EmployeeChoice;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class InterviewSub extends AbstractController {

    public Text interviewID;
    public TextField interViewNameTextField;
    public Tooltip interViewNameTooltip;
    public TableView<FinishedEducation> finishedEducationTableView;
    public TableColumn<FinishedEducation, String> finishedEducationNameColumn;
    public TableColumn<FinishedEducation, LocalDate> finishedEducationDateColumn;
    public TableView<EducationWish> educationWishTableView;
    public TableColumn<EducationWish, String> educationWishNameColumn;
    public TableColumn<EducationWish, Integer> educationWishPriorityColumn;
    public Button addEducationWishButton;
    public Button removeEducationWishButton;
    public Button pickEduForWishButton;
    public Button addFinishedEducationButton;
    public Button removeFinishedEducationButton;
    public Button pickEduForFinishedEducationButton;
    public DatePicker dateForFinishedEducation;
    public TextField priorityTextField;
    public TextField flexScoreTextField;
    public TextField coopScoreTextfield;
    public TextField qualScoreTextField;
    public TextField probScoreTextField;
    public TextField prodScoreTextField;
    public Button pickEmployeeButton;

    public Interview selectedInterview;
    public Employee selectedParentEmployee;

    public BooleanBinding isValid;

    private SimpleBooleanProperty interViewNameIsValid = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty productUnderstandingIsValid = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty problemUnderstandingIsValid = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty qualityAwarenessIsValid = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty cooperationIsValid = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty flexibilityIsValid = new SimpleBooleanProperty(false);

    private ObservableList<EducationWish> educationWishList = FXCollections.observableArrayList();
    private ObservableList<FinishedEducation> finishedEducationList = FXCollections.observableArrayList();

    private SimpleObjectProperty<Education> selectedEduForFinished;
    private SimpleObjectProperty<Education> selectedEduForWish;
    private SimpleObjectProperty<Employee> selectedEmployee;

    private BooleanBinding validWish;
    private BooleanBinding validFinished;

    private StringBinding pickEmployeeButtonText;

    public void initialize() {
        //init values
        selectedEduForFinished = new SimpleObjectProperty<>(null);
        selectedEduForWish = new SimpleObjectProperty<>(null);
        selectedEmployee = new SimpleObjectProperty<>(null);

        //Register listeners for validation
        interViewNameTextField.textProperty().addListener((observable -> handleInterviewNameInput()));
        prodScoreTextField.textProperty().addListener(observable -> handleProdScoreInput());
        probScoreTextField.textProperty().addListener(observable -> handleProbScoreInput());
        flexScoreTextField.textProperty().addListener(observable -> handleFlexScoreInput());
        qualScoreTextField.textProperty().addListener(observable -> handleQualScoreInput());
        coopScoreTextfield.textProperty().addListener(observable -> handleCoopScoreInput());
        priorityTextField.textProperty().addListener(observable -> handlePriorityInput());
        selectedEmployee.addListener(observable -> handleEmployeeSelectionInput());


        //Register listeners for remove
        removeEducationWishButton.disableProperty().bind(educationWishTableView.getSelectionModel().selectedItemProperty().isNull());
        removeFinishedEducationButton.disableProperty().bind(finishedEducationTableView.getSelectionModel().selectedItemProperty().isNull());

        //Setup boolean binding for add buttons
        validWish = new BooleanBinding() {
            {
                bind(selectedEduForWish);
                bind(priorityTextField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return selectedEduForWish.isNotNull().get() && EducationWish.isValidPriority(priorityTextField.getText());
            }
        };

        addEducationWishButton.disableProperty().bind(validWish.not());

        validFinished = new BooleanBinding() {
            {
                bind(selectedEduForFinished);
                bind(dateForFinishedEducation.valueProperty());
            }

            @Override
            protected boolean computeValue() {
                return selectedEduForFinished.isNotNull().get() && dateForFinishedEducation.valueProperty().isNotNull().get();
            }
        };

        addFinishedEducationButton.disableProperty().bind(validFinished.not());

        //Setup TableViews
        finishedEducationNameColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        finishedEducationDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateFinished"));

        educationWishNameColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        educationWishPriorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));

        finishedEducationTableView.getColumns().setAll(finishedEducationNameColumn, finishedEducationDateColumn);
        educationWishTableView.getColumns().setAll(educationWishNameColumn, educationWishPriorityColumn);

        finishedEducationTableView.setItems(finishedEducationList);
        educationWishTableView.setItems(educationWishList);

        //Setup button validations
        isValid = new BooleanBinding() {
            {
                bind(interViewNameIsValid);
                bind(productUnderstandingIsValid);
                bind(problemUnderstandingIsValid);
                bind(qualityAwarenessIsValid);
                bind(cooperationIsValid);
                bind(flexibilityIsValid);
                bind(selectedEmployee);
            }

            @Override
            protected boolean computeValue() {
                return interViewNameIsValid.get() && productUnderstandingIsValid.get() && problemUnderstandingIsValid.get()
                        && qualityAwarenessIsValid.get() && cooperationIsValid.get() && flexibilityIsValid.get() && selectedEmployee.isNotNull().get();
            }
        };

        //Setup binding for Pick Employee button
        pickEmployeeButtonText = new StringBinding() {
            {
                bind(selectedEmployee);
            }

            @Override
            protected String computeValue() {
                if (selectedEmployee.get() == null) {
                    return "Pick Employee";
                } else {
                    int id = selectedEmployee.get().getEmployeeID();
                    return ("ID: " + id);
                }
            }
        };
        pickEmployeeButton.textProperty().bind(pickEmployeeButtonText);

        resetForm();
    }

    @Override
    public void initValues(Interview interview, Employee employee) {
        // hook up interview
        selectedInterview = interview;
        selectedParentEmployee = employee;

        selectedEmployee.set(employee);
        interviewID.setText("Interview " + selectedInterview.getInterviewID());
        resetForm();
    }

    /**
     * On every keypress in the TextField interViewNameTextField,
     * this method updates due to a listener.
     * Checks if the input is valid or not.
     * If the input is invalid, it displays a tooltip with the error and makes the field red
     */
    private void handleInterviewNameInput() {
        if (Interview.isValidInterviewName(interViewNameTextField.getText())) {
            interViewNameTextField.setTooltip(null);
            interViewNameIsValid.set(true);
            interViewNameTextField.getStyleClass().remove("TextField-Error");
        } else {
            String invalidCause = Interview.interviewNameInvalidCause(interViewNameTextField.getText());
            interViewNameTextField.setTooltip(new Tooltip(invalidCause));
            interViewNameIsValid.set(false);
            if (!interViewNameTextField.getStyleClass().contains("TextField-Error"))
                interViewNameTextField.getStyleClass().add("TextField-Error");
        }
    }

    private void handleProdScoreInput() {
        if (Interview.isValidProductUnderstanding(prodScoreTextField.getText())) {
            prodScoreTextField.setTooltip(null);
            productUnderstandingIsValid.set(true);
            prodScoreTextField.getStyleClass().remove("TextField-Error");
        } else {
            String invalidCause = Interview.productUnderstandingInvalidCause(prodScoreTextField.getText());
            prodScoreTextField.setTooltip(new Tooltip(invalidCause));
            productUnderstandingIsValid.set(false);
            if (!prodScoreTextField.getStyleClass().contains("TextField-Error"))
                prodScoreTextField.getStyleClass().add("TextField-Error");
        }
    }

    private void handleProbScoreInput() {
        if (Interview.isValidProblemUnderstanding(probScoreTextField.getText())) {
            probScoreTextField.setTooltip(null);
            problemUnderstandingIsValid.set(true);
            probScoreTextField.getStyleClass().remove("TextField-Error");
        } else {
            String invalidCause = Interview.problemUnderstandingInvalidCause(probScoreTextField.getText());
            probScoreTextField.setTooltip(new Tooltip(invalidCause));
            problemUnderstandingIsValid.set(false);
            if (!probScoreTextField.getStyleClass().contains("TextField-Error"))
                probScoreTextField.getStyleClass().add("TextField-Error");
        }
    }

    private void handleQualScoreInput() {
        if (Interview.isValidQualityAwareness(qualScoreTextField.getText())) {
            qualScoreTextField.setTooltip(null);
            qualityAwarenessIsValid.set(true);
            qualScoreTextField.getStyleClass().remove("TextField-Error");
        } else {
            String invalidCause = Interview.qualityAwarenessInvalidCause(qualScoreTextField.getText());
            qualScoreTextField.setTooltip(new Tooltip(invalidCause));
            qualityAwarenessIsValid.set(false);
            if (!qualScoreTextField.getStyleClass().contains("TextField-Error"))
                qualScoreTextField.getStyleClass().add("TextField-Error");
        }
    }

    private void handleFlexScoreInput() {
        if (Interview.isValidFlexibility(flexScoreTextField.getText())) {
            flexScoreTextField.setTooltip(null);
            flexibilityIsValid.set(true);
            flexScoreTextField.getStyleClass().remove("TextField-Error");
        } else {
            String invalidCause = Interview.flexibilityInvalidCause(interViewNameTextField.getText());
            flexScoreTextField.setTooltip(new Tooltip(invalidCause));
            flexibilityIsValid.set(false);
            if (!flexScoreTextField.getStyleClass().contains("TextField-Error"))
                flexScoreTextField.getStyleClass().add("TextField-Error");
        }
    }

    private void handleCoopScoreInput() {
        if (Interview.isValidCooperation(coopScoreTextfield.getText())) {
            coopScoreTextfield.setTooltip(null);
            cooperationIsValid.set(true);
            coopScoreTextfield.getStyleClass().remove("TextField-Error");
        } else {
            String invalidCause = Interview.cooperationInvalidCause(coopScoreTextfield.getText());
            coopScoreTextfield.setTooltip(new Tooltip(invalidCause));
            cooperationIsValid.set(false);
            if (!coopScoreTextfield.getStyleClass().contains("TextField-Error"))
                coopScoreTextfield.getStyleClass().add("TextField-Error");
        }
    }

    private void handleEmployeeSelectionInput() {
        if (selectedEmployee.get() == null) {
            if (!pickEmployeeButton.getStyleClass().contains("Button-Error")) {
                pickEmployeeButton.getStyleClass().add("Button-Error");
            }
        } else {
            pickEmployeeButton.getStyleClass().removeAll("Button-Error");
        }
    }

    private void handlePriorityInput() {
        if (EducationWish.isValidPriority(priorityTextField.getText())) {
            priorityTextField.setTooltip(null);
        } else {
            String invalidCause = EducationWish.priorityInvalidCause(priorityTextField.getText());
            priorityTextField.setTooltip(new Tooltip(invalidCause));
        }
    }

    public void handleAddEducationWish(ActionEvent event) {
        EducationWish newData = new EducationWish(null, selectedEduForWish.get(), Integer.parseInt(priorityTextField.getText()));
        educationWishList.add(newData);
        resetValuesForWish();
    }

    public void handleAddFinishedEducation(ActionEvent event) {
        if (dateForFinishedEducation.getValue() != null) {
            LocalDate dateToInsert = dateForFinishedEducation.getValue();
            FinishedEducation newData = new FinishedEducation(null, selectedEduForFinished.get(), dateToInsert);
            finishedEducationList.add(newData);
            resetValuesForFinished();
        }
    }

    public void handlePickEduForWish(ActionEvent event) {
        EducationChoice popUp = new EducationChoice();
        Education foundEducation = popUp.showAndReturn(new FindEducationPopUp());
        if (foundEducation != null) {
            selectedEduForWish.set(foundEducation);
            pickEduForWishButton.setText(foundEducation.getEducationName());
        }
    }

    private void resetValuesForWish() {
        priorityTextField.setText(null);
        selectedEduForWish.set(null);
        pickEduForWishButton.setText("Pick Education");
    }

    public void handlePickEduForFinished(ActionEvent event) {
        EducationChoice popUp = new EducationChoice();
        Education foundEducation = popUp.showAndReturn(new FindEducationPopUp());
        if (foundEducation != null) {
            selectedEduForFinished.set(foundEducation);
            pickEduForFinishedEducationButton.setText(foundEducation.getEducationName());
        }
    }

    private void resetValuesForFinished() {
        dateForFinishedEducation.setValue(null);
        selectedEduForFinished.set(null);
        pickEduForFinishedEducationButton.setText("Pick Education");
    }

    /**
     * Removes an education wish from the table
     *
     * @param event Upon selecting a wish and clicking the "Remove education" button
     */
    public void handleRemoveEducationWish(ActionEvent event) {
        EducationWish selectedData = educationWishTableView.getSelectionModel().getSelectedItem();
        educationWishList.remove(selectedData);
    }

    /**
     * Removes a finished education from the table
     *
     * @param event Upon selecting a finished education and clicking the "Remove finished education" button
     */
    public void handleRemoveFinishedEducation(ActionEvent event) {
        FinishedEducation selectedData = finishedEducationTableView.getSelectionModel().getSelectedItem();
        finishedEducationList.remove(selectedData);
    }

    /**
     * A method to disable Fields, in cases that needs it
     *
     * @param bool true or false, depending on if fields should be disabled
     */
    @SuppressWarnings("Duplicates")
    public void setDisabled(boolean bool) { //TODO Need implemetation
        interViewNameTextField.setDisable(bool);
        prodScoreTextField.setDisable(bool);
        probScoreTextField.setDisable(bool);
        qualScoreTextField.setDisable(bool);
        coopScoreTextfield.setDisable(bool);
        flexScoreTextField.setDisable(bool);

        educationWishTableView.setDisable(bool);
        finishedEducationTableView.setDisable(bool);

        pickEduForWishButton.setDisable(bool);
        pickEduForWishButton.setVisible(!bool);

        priorityTextField.setDisable(bool);
        priorityTextField.setVisible(!bool);

        //addEducationWishButton.setDisable(bool);
        addEducationWishButton.setVisible(!bool);

        //removeEducationWishButton.setDisable(bool);
        removeEducationWishButton.setVisible(!bool);

        pickEduForFinishedEducationButton.setDisable(bool);
        pickEduForFinishedEducationButton.setVisible(!bool);

        dateForFinishedEducation.setDisable(bool);
        dateForFinishedEducation.setVisible(!bool);

        //addFinishedEducationButton.setDisable(bool);
        addFinishedEducationButton.setVisible(!bool);

        //removeFinishedEducationButton.setDisable(bool);
        removeFinishedEducationButton.setVisible(!bool);

        pickEmployeeButton.setDisable(bool);
        pickEmployeeButton.setVisible(!bool);
    }

    /**
     * Method to reset fields and comboboxes, either manually or through startup.
     * Checks if the user got there by selecting an interview or if they want to create a new.
     */
    public void resetForm() {
        handleEmployeeSelectionInput();
        if (selectedInterview != null) { //TODO employee button
            resetValuesForFinished();
            resetValuesForWish();

            selectedEmployee.set(null);

            educationWishList.clear();
            educationWishList.addAll(selectedInterview.getEducationWishes());

            finishedEducationList.clear();
            finishedEducationList.addAll(selectedInterview.getFinishedEducations());

            interViewNameTextField.setText(selectedInterview.getInterviewName());
            if (selectedInterview.getProductUnderstanding() != null) {
                prodScoreTextField.setText(selectedInterview.getProductUnderstanding().toString());
            } else {
                prodScoreTextField.setText(null);
            }
            if (selectedInterview.getProblemUnderstanding() != null) {
                probScoreTextField.setText(selectedInterview.getProblemUnderstanding().toString());
            } else {
                probScoreTextField.setText(null);
            }
            if (selectedInterview.getFlexibility() != null) {
                flexScoreTextField.setText(selectedInterview.getFlexibility().toString());
            } else {
                flexScoreTextField.setText(null);
            }
            if (selectedInterview.getQualityAwareness() != null) {
                qualScoreTextField.setText(selectedInterview.getQualityAwareness().toString());
            } else {
                qualScoreTextField.setText(null);
            }
            if (selectedInterview.getCooperation() != null) {
                coopScoreTextfield.setText(selectedInterview.getCooperation().toString());
            } else {
                coopScoreTextfield.setText(null);
            }

        } else {
            resetValuesForFinished();
            resetValuesForWish();

            selectedEmployee.set(selectedParentEmployee);

            educationWishList.clear();
            finishedEducationList.clear();

            interViewNameTextField.setText(null);
            prodScoreTextField.setText(null);
            probScoreTextField.setText(null);
            flexScoreTextField.setText(null);
            qualScoreTextField.setText(null);
            coopScoreTextfield.setText(null);
        }
    }

    @SuppressWarnings("Duplicates")
    public Interview getInterview() { //TODO needs implementation
        Integer id = null;
        if (selectedInterview != null){
            id = selectedInterview.getInterviewID();
        }
        String name = interViewNameTextField.getText();

        Integer prod = null;
        if (prodScoreTextField.getText() != null && !prodScoreTextField.getText().trim().isEmpty()) {
             prod = Integer.parseInt(prodScoreTextField.getText());
        }

        Integer prob = null;
        if (prodScoreTextField.getText() != null && !prodScoreTextField.getText().trim().isEmpty()) {
             prob = Integer.parseInt(probScoreTextField.getText());
        }

        Integer flex = null;
        if (flexScoreTextField.getText() !=null && !flexScoreTextField.getText().trim().isEmpty()) {
             flex = Integer.parseInt(flexScoreTextField.getText());
        }

        Integer qual = null;
        if(qualScoreTextField.getText() != null && !qualScoreTextField.getText().trim().isEmpty()) {
            qual = Integer.parseInt(qualScoreTextField.getText());
        }

        Integer coop = null;
        if (coopScoreTextfield.getText() != null && !coopScoreTextfield.getText().trim().isEmpty()){
            coop = Integer.parseInt(coopScoreTextfield.getText());
        }
        ArrayList<FinishedEducation> finishedEducations = new ArrayList<>(finishedEducationList);
        ArrayList<EducationWish> educationWishes = new ArrayList<>(educationWishList);
        return new Interview(id,name,prod,prob,flex,qual,coop,finishedEducations,educationWishes);
    }

    public int getEmployeeID() {
        return selectedEmployee.get().getEmployeeID();
    }


    public void handlePickEmployee(ActionEvent event) {
        EmployeeChoice popUp = new EmployeeChoice();
        Employee foundEducation = popUp.showAndReturn(new FindEmployeePopUp());
        if (foundEducation != null) {
            selectedEmployee.set(foundEducation);
        }
    }
}
