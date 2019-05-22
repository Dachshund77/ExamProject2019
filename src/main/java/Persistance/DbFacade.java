package Persistance;

import Domain.*;
import Foundation.DB;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Facade class that helps convert SQL data to objects.
 * Note that this class will not establish a Db connection on its own.
 */
public class DbFacade {

    /*
     * INSERTION
     */

    /**
     * Method that will write a provider object to the database.
     * <br>
     * <br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() discoonect}.
     * To execute this batch call {@link DB#executeBatch()} before disconnecting.
     *
     * @param provider The Container for the values that will be inserted.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    public static void insertProviderToBatch(Provider provider) throws SQLException {
        //Get values for insertion
        Integer providerID = provider.getProviderID();
        String providerName = provider.getProviderName();
        //Write to Db
        DB.getInstance().addStoredProcedureToBatch("sp_InsertProvider", providerID, providerName);
    }

    /**
     * Method that will write a Education object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * <br>
     * <br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() discoonect}.
     * To execute this batch call {@link DB#executeBatch()} before disconnecting.
     *
     * @param education The Container for the values that will be inserted.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    public static void insertEducationToBatch(Education education) throws SQLException {
        DB database = DB.getInstance();

        //First we Insert the provider (has 1 to m cardinality)
        Provider provider = education.getProvider();
        insertProviderToBatch(provider);

        //Secondly write the education to the database
        Integer amuNr = education.getAmuNr();
        Integer providerID = education.getProvider().getProviderID();
        String educationName = education.getEducationName();
        String description = education.getDescription();
        Integer noOfDays = education.getNoOfDays();

        database.addStoredProcedureToBatch("sp_InsertEducation", amuNr, providerID, educationName, description, noOfDays);

        //Then we write dates to database
        database.addStoredProcedureToBatch("sp_DeleteDateByAmuNr", amuNr);

        // Then reInsert
        ArrayList<Date> dates = education.getDates();
        for (Date date : dates) {
            database.addStoredProcedureToBatch("sp_InsertDate", null, amuNr, date);
        }
    }

    /**
     * Method that will write a EducationWish object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * <br>
     * <br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() discoonect}.
     * To execute this batch call {@link DB#executeBatch()} before disconnecting.
     *
     * @param educationWish The Container for the values that will be inserted.
     * @param interViewID   ID of interview the tbl_EducationWish record will point to.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    private static void insertEducationWishToBatch(EducationWish educationWish, int interViewID) throws SQLException {
        DB database = DB.getInstance();

        //First we make sure that the Education is written/updated in the database
        Education education = educationWish.getEducation();
        insertEducationToBatch(education);

        //Next we write this EducationWish to the batch
        Integer educationWishID = educationWish.getEducationWishID();
        Integer amuNr = education.getAmuNr();
        Integer priority = educationWish.getPriority();

        database.addStoredProcedureToBatch("sp_InsertEducationWish", educationWishID, amuNr, interViewID, priority);
    }

    /**
     * Method that will write a EducationWish object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * <br>
     * <br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() discoonect}.
     * To execute this batch call {@link DB#executeBatch()} before disconnecting.
     *
     * @param finishedEducation The Container for the values that will be inserted.
     * @param interViewID       ID of the interview the tbl_FinishedEduction record wil point to
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    private static void insertFinishedEducationToBatch(FinishedEducation finishedEducation, int interViewID) throws SQLException {
        DB database = DB.getInstance();

        //First we make sure that the Education is written/updated in the database
        Education education = finishedEducation.getEducation();
        insertEducationToBatch(education);

        //Next we write this FinishedEducation to the database
        Integer finishedEducationID = finishedEducation.getFinishedEducationID();
        Integer amuNr = education.getAmuNr();
        Date finishedDate = finishedEducation.getDateFinished();

        database.addStoredProcedureToBatch("sp_InsertFinishedEducation", finishedEducationID, amuNr, interViewID, finishedDate);
    }

    /**
     * Method that will write a Employee object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * <br>
     * <br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() discoonect}.
     * To execute this batch call {@link DB#executeBatch()} before disconnecting.
     *
     * @param employee The Container for the values that will be inserted.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    public static void insertEmployeeToBatch(Employee employee) throws SQLException {
        DB database = DB.getInstance();

        //Unpacking the object
        Integer employeeID = employee.getEmployeeId();
        String employeeFirstName = employee.getEmployeeFirstName();
        String employeeLastName = employee.getEmployeeLastName();
        String CPRnr = employee.getCprNr();
        String eMail = employee.getMail();
        String phoneNr = employee.getPhoneNr();

        database.addStoredProcedureToBatch("sp_InsertEmployee", employeeID, employeeFirstName, employeeLastName, CPRnr, eMail, phoneNr);
    }

    /**
     * Method that will write a Interview object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * <br>
     * <br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() discoonect}.
     * To execute this batch call {@link DB#executeBatch()} before disconnecting.
     *
     * @param interview The Container for the values that will be inserted.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    public static void insertInterviewToBatch(Interview interview) throws SQLException {
        DB database = DB.getInstance();

        //We write the employee
        insertEmployeeToBatch(interview.getEmployee());

        //We write this interview
        Integer interViewID = interview.getInterviewID();
        String interViewName = interview.getInterviewName();
        Integer employeeID = interview.getEmployee().getEmployeeId();
        Integer productUnderstanding = interview.getProductUnderstanding();
        Integer problemUnderstanding = interview.getProblemUnderstanding();
        Integer flexibility = interview.getFlexibility();
        Integer qualityAwareness = interview.getQualityAwareness();
        Integer cooperation = interview.getCooperation();

        database.addStoredProcedureToBatch("sp_InsertInterview",
                interViewID,
                interViewName,
                employeeID, productUnderstanding,
                problemUnderstanding,
                flexibility,
                qualityAwareness,
                cooperation);

        //We write the finished education, we have to delete all FinishedEducation with this interView beforehand else we create unwanted relics
        database.addStoredProcedureToBatch("sp_DeleteFinishedEducationByInterviewID",interViewID);
        ArrayList<FinishedEducation> finishedEducations = interview.getFinishedEducations();
        for (FinishedEducation finishedEducation : finishedEducations) {
            insertFinishedEducationToBatch(finishedEducation, interViewID);
        }

        //We write the education wishes, we have to delete all EducationWishes with this interviewID beforehand else we create unwanted relics
        database.addStoredProcedureToBatch("sp_DeleteEducationWishByID",interViewID);
        ArrayList<EducationWish> educationWishes = interview.getEducationWishes();
        for (EducationWish educationWish : educationWishes) {
            insertEducationWishToBatch(educationWish, interViewID);
        }

    }

    /**
     * Use {@link #insertEmployeeToBatch(Employee)} instead
     */
    @Deprecated
    public static boolean insertEmployee(Employee employee) throws SQLException {
        Integer employeeID = employee.getEmployeeId();
        String employeeFirstName = employee.getEmployeeFirstName();
        String employeeLastName = employee.getEmployeeLastName();
        String CPRnr = employee.getCprNr();
        String eMail = employee.getMail();
        String phoneNr = employee.getPhoneNr();

        return DB.getInstance().executeStoredProcedureNoRS("sp_InsertEmployee", employeeID, employeeFirstName, employeeLastName, CPRnr, eMail, phoneNr);
    }

    /*
     * SEARCHING FOR VALUES
     */

    /**
     * Method that will search the Database for Provider objects.
     * Values may be null and be used as wildCard.
     *
     * @param providerID   Integer ID of the provider, may be null as wildcard
     * @param ProviderName String name of the provider, may be null as wildcard
     * @return HashMap of all the found provider. Key values equals the unique ProviderID
     * @throws SQLException Exception when encountered a fatal error
     * @see Provider
     */
    public static HashMap<Integer, Provider> findProviders(Integer providerID, String ProviderName) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> returnMap = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure("sp_FindProvider", providerID, ProviderName);

        while (rs.next()) {
            Provider tempProvider = new Provider(rs);
            returnMap.put(tempProvider.getProviderID(), tempProvider);
        }
        return returnMap;
    }

    /*
     * GETTING ALL VALUES
     */

    /**
     * Method that will search the Database for ALL Provider objects.
     * Values may be null and be used as wildCard.
     *
     * @return HashMap of all the found provider. Key values equals the unique ProviderID
     * @throws SQLException Exception when encountered a fatal error
     * @see Provider
     */
    public static HashMap<Integer, Provider> findAllProviders() throws SQLException {
        //init needed values
        HashMap<Integer, Provider> returnMap = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure("sp_FindProvider", null, null);

        while (rs.next()) {
            Provider tempProvider = new Provider(rs);
            returnMap.put(tempProvider.getProviderID(), tempProvider);
        }
        return returnMap;
    }
}
