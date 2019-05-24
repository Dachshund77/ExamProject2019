package Persistance;

import Domain.Company;
import Domain.Education;
import Domain.Employee;
import Domain.Provider;
import Foundation.DB;
import Foundation.Sp;
import Foundation.SpGetKey;
import Foundation.SpWithRs;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import org.apache.ibatis.jdbc.Null;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Facade class that helps convert SQL data to objects.
 * Note that this class will not establish a Db connection on its own.
 */
public class DbFacade { //TODO This whole god forsaken shit class need to have an overhaul on documentation.

   /*
    * INSERTION
    */

    /**
     * Method that will write the a provider object to the database.
     * @param provider The Container for the values that will be inserted.
     * @return True if successful added to batch.
     * @throws SQLException         Exception thrown when encountered a fatal error.
     * @throws NullPointerException Thrown if the Domain structure contain missing parts.
     */
    public static int insertProvider(Provider provider) throws SQLException, NullPointerException { // FIXME: 24/05/2019 Javadoc
        DB database = DB.getInstance();

        Integer providerID = provider.getProviderID();
        String providerName = provider.getProviderName();
        // 1 Insert this provider
        providerID = database.executeStoredProcedureGetID(SpGetKey.PLACE_PROVIDER, providerID, providerName);

        // 2 return inserted ID
        return providerID;
    }

    /**
     * Method that will write the a provider object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * @param education The Container for the values that will be inserted.
     * @return True if successful added to batch.
     * @throws SQLException         Exception thrown when encountered a fatal error.
     * @throws NullPointerException Thrown if the Domain structure contain missing parts.
     */
    @SuppressWarnings("Duplicates")
    public static int insertEducation(Education education) throws SQLException, NullPointerException { //todo old amu stuff
        DB database = DB.getInstance();

        // 1 insert provider via other method that return its ID
        int providerID = insertProvider(education.getProvider());

        // 2 Insert this education and get AmuNr
        Integer amuNr = education.getAmuNr();
        String educationName = education.getEducationName();
        String educationDescription = education.getDescription();
        int noOfDays = education.getNoOfDays();

        amuNr = database.executeStoredProcedureGetID(SpGetKey.PLACE_EDUCATION, amuNr, providerID, educationName, educationDescription, noOfDays);

        // 3 Purge dates table
        database.addStoredProcedureToBatch(Sp.DELETE_DATE_BY_AMU_NR, amuNr);

        // 4 Insert dates with amurNr
        ArrayList<LocalDate> dates = education.getDates();
        for (LocalDate date : dates) {
            database.addStoredProcedureToBatch(Sp.PLACE_DATE, null, amuNr, date);
        }
        database.executeBatch();

        // 5 return amu nr
        return amuNr;
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
     * @return True if successful added to batch.
     * @throws SQLException         Exception thrown when encountered a fatal error.
     * @throws NullPointerException Thrown if the Domain structure contain missing parts.
     */
    private static int insertEducationWish(EducationWish educationWish, int interViewID) throws SQLException, NullPointerException {
        DB database = DB.getInstance();

        // 1 Write education to db and get returnvalue
        Education education = educationWish.getEducation();
        int amuNr = insertEducation(education);

        // 2 We insert this education wish and get inserted id
        Integer educationWishID = educationWish.getEducationWishID();
        Integer priority = educationWish.getPriority();

        educationWishID = database.executeStoredProcedureGetID(SpGetKey.PLACE_EDUCATION_WISH, educationWishID, amuNr, interViewID, priority);

        // 3 return value
        return educationWishID;
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
     * @return True if successful added to batch.
     * @throws SQLException         Exception thrown when encountered a fatal error.
     * @throws NullPointerException Thrown if the Domain structure contain missing parts.
     */
    private static int insertFinishedEducation(FinishedEducation finishedEducation, int interViewID) throws SQLException, NullPointerException {
        DB database = DB.getInstance();

        // 1 Write education to db and get return value
        Education education = finishedEducation.getEducation();
        int amuNr = insertEducation(education);

        // 2 Write this finished education to db
        Integer finishedEducationID = finishedEducation.getFinishedEducationID();
        LocalDate dateFinished = finishedEducation.getDateFinished();

        finishedEducationID = database.executeStoredProcedureGetID(SpGetKey.PLACE_FINISHED_EDUCATION, finishedEducationID, amuNr, interViewID, dateFinished);

        // 3 return value
        return finishedEducationID;

    }

    /**
     * Method that will write a Employee object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * Note that his method will not create a relationship between an employee and consultation.
     * Use {@link #insertConsultation(Consultation, int)} for that.
     * <br>
     * <br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() discoonect}.
     * To execute this batch call {@link DB#executeBatch()} before disconnecting.
     *
     * @param employee The Container for the values that will be inserted.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    @SuppressWarnings("Duplicates")

    public static int insertEmployee(Employee employee) throws SQLException {
        DB database = DB.getInstance();

        // 1 Insert this employee and get the id
        Integer oldEmployeeID = employee.getEmployeeId();
        String employeeFirstName = employee.getEmployeeFirstName();
        String employeeLastName = employee.getEmployeeLastName();
        String CPRnr = employee.getCprNr();
        String eMail = employee.geteMail();
        String phoneNr = employee.getPhoneNr();

        int newEmployeeID = database.executeStoredProcedureGetID(SpGetKey.PLACE_EMPLOYEE,oldEmployeeID,employeeFirstName,employeeLastName,CPRnr,eMail,phoneNr);

        // 2 Purge interview table
        database.addStoredProcedureToBatch(Sp.DELETE_INTERVIEW_BY_EMPLOYEE_ID, oldEmployeeID);
        database.executeBatch();

        // 3 Insert interview by calling method and passing
        ArrayList<Interview> employees = employee.getInterviews();
        for (Interview interview : employees) {
            insertInterview(interview,newEmployeeID);
        }

        // 4 return employee id
        return newEmployeeID;
    }

    /**
     * Method that will write a Interview object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * <br>
     * <br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() discoonect}.
     * To execute this batch call {@link DB#executeBatch()} before disconnecting.
     *
     * @param interview  The Container for the values that will be inserted.
     * @param employeeID The employee Id tbl_interview Record will point too.
     * @return True if successful added to batch.
     * @throws SQLException         Exception thrown when encountered a fatal error.
     * @throws NullPointerException Thrown if the Domain structure contain missing parts.
     */
    private static int insertInterview(Interview interview, int employeeID) throws SQLException, NullPointerException {
        DB database = DB.getInstance();

        // 1 We write this interview to the db and get ID
        Integer oldInterViewID = interview.getInterviewID();
        String interViewName = interview.getInterviewName();
        Integer productUnderstanding = interview.getProductUnderstanding();
        Integer problemUnderstanding = interview.getProblemUnderstanding();
        Integer flexibility = interview.getFlexibility();
        Integer qualityAwareness = interview.getQualityAwareness();
        Integer cooperation = interview.getCooperation();

        int newInterViewID = database.executeStoredProcedureGetID(SpGetKey.PLACE_INTERVIEW,
                oldInterViewID,
                interViewName,
                employeeID,
                productUnderstanding,
                problemUnderstanding,
                flexibility,
                qualityAwareness,
                cooperation);

        // 2 Purge EducationWish table
        database.addStoredProcedureToBatch(Sp.DELETE_EDUCATION_WISH_BY_INTERVIEW_ID, oldInterViewID);
        database.executeBatch();

        // 3 Reinster by calling method
        ArrayList<EducationWish> educationWishes = interview.getEducationWishes();
        for (EducationWish educationWish : educationWishes) {
            insertEducationWish(educationWish, newInterViewID);
        }

        // 4 Purge Finished Education
        database.addStoredProcedureToBatch(Sp.DELETE_FINISHED_EDUCATION_BY_INTERVIEW_ID, oldInterViewID);
        database.executeBatch();

        // 5 reinsert by calling method
        ArrayList<FinishedEducation> finishedEducations = interview.getFinishedEducations();
        for (FinishedEducation finishedEducation : finishedEducations) {
            insertFinishedEducation(finishedEducation, newInterViewID);
        }

        return newInterViewID;
    }

    /**
     * Method that will write a Company object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * <br>
     * <br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() discoonect}.
     * To execute this batch call {@link DB#executeBatch()} before disconnecting.
     *
     * @param company The Container for the values that will be inserted.
     * @return True if successful added to batch.
     * @throws SQLException         Exception thrown when encountered a fatal error.
     * @throws NullPointerException Thrown if the Domain structure contain missing parts.
     */
    public static int insertCompany(Company company) throws SQLException, NullPointerException {
        DB database = DB.getInstance();

        // 1 Insert this Company into db and get company id
        Integer oldCompanyID = company.getCompanyID();
        String cvrNr = company.getCvrNr();
        String companyName = company.getCompanyName();

        int newCompanyID = database.executeStoredProcedureGetID(SpGetKey.PLACE_COMPANY,oldCompanyID,cvrNr,companyName);

        // 2 cleanse Consultation table by company id
        database.addStoredProcedureToBatch(Sp.DELETE_CONSULTATION_BY_COMPANY_ID,oldCompanyID);
        database.executeBatch();

        // 3 Insert Consultation should be done by other method
        ArrayList<Consultation> consultations = company.getConsultations();
        for (Consultation consultation : consultations) {
            insertConsultation(consultation,newCompanyID);
        }

        // 4 Insert education via method that returns the inserted ID
        ArrayList<Education> educations = company.getEducationList();
        ArrayList<Integer> newEducationIDs = new ArrayList<>();
        for (Education education : educations) {
            newEducationIDs.add(insertEducation(education));
        }

        // 5 With the returnValue we can make the bridge table, purge old one
        for (Integer newEducationID : newEducationIDs) {
            database.addStoredProcedureToBatch(Sp.INSERT_COMPANY_EDUCATION_BRIDGE,newCompanyID,newEducationID);
        }

        // 6 return value
        return newCompanyID;
    }

    /**
     * Method that will write a Consultation object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * <br>
     * <br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() discoonect}.
     * To execute this batch call {@link DB#executeBatch()} before disconnecting.
     *
     * @param consultation The Container for the values that will be inserted.
     * @param companyID    The companyID that the tbl_Consultation will point to.
     * @return True if successful added to batch.
     * @throws SQLException         Exception thrown when encountered a fatal error.
     * @throws NullPointerException Thrown if the Domain structure contain missing parts.
     */
    private static int insertConsultation(Consultation consultation, int companyID) throws SQLException, NullPointerException {
        DB database = DB.getInstance();

        // 1 Insert Consultations
        Integer oldConsultationID = consultation.getConsultationID();
        String consultationName = consultation.getConsultationName();
        LocalDate startDate = consultation.getStartDate();
        LocalDate endDate = consultation.getEndDate();

        int newConsultationID = database.executeStoredProcedureGetID(SpGetKey.PLACE_CONSULTATION,oldConsultationID,consultationName,startDate,endDate,companyID);

        // 2 purge bridge table
        database.addStoredProcedureToBatch(Sp.DELETE_CONSULTATION_EMPLOYEE_BRIDGE_BY_CONSULTATION_ID,oldConsultationID); //TODO make asearch
        database.executeBatch();

        // 3 Insert Employees via method
        ArrayList<Employee> employees = consultation.getEmployees();
        ArrayList<Integer> newEmployeeIDs = new ArrayList<>();
        for (Employee employee : employees) {
            newEmployeeIDs.add(insertEmployee(employee));
        }

        // 4 with the return value of insert employee we can reinsert bridge table
        for (Integer newEmployeeID : newEmployeeIDs) {
            database.addStoredProcedureToBatch(Sp.INSERT_CONSULTATION_EMPLOYEE_BRIDGE,newConsultationID,newEmployeeIDs);
        }
        database.executeBatch();

        return newConsultationID;
    }

    /*
     * SEARCHING FOR VALUES
     */

    /**
     * Method that will search the Database for Provider objects.
     * Values may be null and be used as wildCard.
     * @param providerID Integer ID of the provider, may be null as wildcard
     * @param ProviderName String name of the provider, may be null as wildcard
     * @return HashMap of all the found provider. Key values equals the unique ProviderID
     * @throws SQLException Exception when encountered a fatal error
     * @see Provider
     */
    public static HashMap<Integer, Provider> findProviders(Integer providerID, String ProviderName) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> returnMap = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_PROVIDER, providerID, ProviderName);

        while (rs.next()){
            Provider tempProvider = new Provider(rs);
            returnMap.put(tempProvider.getProviderID(),tempProvider);
        }
        return returnMap;
    }

    /*
     * GETTING ALL VALUES
     */

    /**
     * Method that will search the Database for ALL Provider objects.
     * Values may be null and be used as wildCard.
     * @return HashMap of all the found provider. Key values equals the unique ProviderID
     * @throws SQLException Exception when encountered a fatal error
     * @see Provider
     */
    public static HashMap<Integer, Provider> findAllProviders() throws SQLException {
        //init needed values
        HashMap<Integer, Provider> returnMap = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_PROVIDER, null, null);

        while (rs.next()){
            Provider tempProvider = new Provider(rs);
            returnMap.put(tempProvider.getProviderID(),tempProvider);
        }
        return returnMap;
    }

    public static HashMap<Integer, Company> FindAllCompanies() throws SQLException{

        //init needed values
        HashMap<Integer, Company> returnCompNames = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure("sp_FindCompanyNames");

        while (rs.next()){
            Company tempCompanyNames = new Company(rs);
            returnCompNames.put(tempCompanyNames.getCompanyID(),tempCompanyNames);
        }
        return returnCompNames;

    }
}
