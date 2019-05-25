package Persistance;

import Domain.*;
import Foundation.DB;
import Foundation.Sp;
import Foundation.SpGetKey;
import Foundation.SpWithRs;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.security.DomainCombiner;
import java.time.LocalDate;
import java.util.ArrayList;
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
     *
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
     *
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
    public static int insertEducationWish(EducationWish educationWish, int interViewID) throws SQLException, NullPointerException {
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
    public static int insertFinishedEducation(FinishedEducation finishedEducation, int interViewID) throws SQLException, NullPointerException {
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

        int newEmployeeID = database.executeStoredProcedureGetID(SpGetKey.PLACE_EMPLOYEE, oldEmployeeID, employeeFirstName, employeeLastName, CPRnr, eMail, phoneNr);

        // 2 Purge interview table
        database.addStoredProcedureToBatch(Sp.DELETE_INTERVIEW_BY_EMPLOYEE_ID, oldEmployeeID);
        database.executeBatch();

        // 3 Insert interview by calling method and passing
        ArrayList<Interview> employees = employee.getInterviews();
        for (Interview interview : employees) {
            insertInterview(interview, newEmployeeID);
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
    public static int insertInterview(Interview interview, int employeeID) throws SQLException, NullPointerException {
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

        int newCompanyID = database.executeStoredProcedureGetID(SpGetKey.PLACE_COMPANY, oldCompanyID, cvrNr, companyName);

        // 2 cleanse Consultation table by company id
        database.addStoredProcedureToBatch(Sp.DELETE_CONSULTATION_BY_COMPANY_ID, oldCompanyID);
        database.executeBatch();

        // 3 Insert Consultation should be done by other method
        ArrayList<Consultation> consultations = company.getConsultations();
        for (Consultation consultation : consultations) {
            insertConsultation(consultation, newCompanyID);
        }

        // 4 Insert education via method that returns the inserted ID
        ArrayList<Education> educations = company.getEducationList();
        ArrayList<Integer> newEducationIDs = new ArrayList<>();
        for (Education education : educations) {
            newEducationIDs.add(insertEducation(education));
        }

        // 5 With the returnValue we can make the bridge table, purge old one
        for (Integer newEducationID : newEducationIDs) {
            database.addStoredProcedureToBatch(Sp.INSERT_COMPANY_EDUCATION_BRIDGE, newCompanyID, newEducationID);
        }
        database.executeBatch();

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
    public static int insertConsultation(Consultation consultation, int companyID) throws SQLException, NullPointerException {
        DB database = DB.getInstance();

        // 1 Insert Consultations
        Integer oldConsultationID = consultation.getConsultationID();
        String consultationName = consultation.getConsultationName();
        LocalDate startDate = consultation.getStartDate();
        LocalDate endDate = consultation.getEndDate();

        int newConsultationID = database.executeStoredProcedureGetID(SpGetKey.PLACE_CONSULTATION, oldConsultationID, consultationName, startDate, endDate, companyID);

        if (oldConsultationID != null) {
            // 2 purge bridge table
            database.addStoredProcedureToBatch(Sp.DELETE_CONSULTATION_EMPLOYEE_BRIDGE_BY_CONSULTATION_ID, oldConsultationID); //TODO make asearch
        }
        database.executeBatch();

        // 3 Insert Employees via method
        ArrayList<Employee> employees = consultation.getEmployees();
        ArrayList<Integer> newEmployeeIDs = new ArrayList<>();
        for (Employee employee : employees) {
            newEmployeeIDs.add(insertEmployee(employee));
        }

        // 4 with the return value of insert employee we can reinsert bridge table
        for (Integer newEmployeeID : newEmployeeIDs) {
            database.addStoredProcedureToBatch(Sp.INSERT_CONSULTATION_EMPLOYEE_BRIDGE, newConsultationID, newEmployeeID);
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
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_PROVIDER, providerID, ProviderName);

        while (rs.next()) {
            Provider tempProvider = new Provider(rs);
            returnMap.put(tempProvider.getProviderID(), tempProvider);
        }
        return returnMap;
    }

    /*
     * DELETE BY ID
     */



    /*
     * GET BY PRIMARY KEY
     */

    public static Provider findProvider(int ID) throws SQLException { //TODO MAKE JAVA DOC
        //init needed values
        Provider returnProvider = null;

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_PROVIDER, ID, null);

        while (rs.next()) {
            returnProvider = new Provider(rs);
        }
        return returnProvider;
    }

    public static Education findEducation(int ID) throws SQLException {
        Education returnEducation = null;
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>(); //Integer is provider id
        HashMap<Integer, Education> educations = new HashMap<>(); //Integer is education id


        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EDUCATION, ID, null, null, null, null, null, null, null);

        //get row by row
        while (rs.next()) {
            buildProvider(rs,providers);
            buildEducation(rs,educations,providers);
        }

        for (Education education : educations.values()) {
            returnEducation = education;
        }
        return returnEducation;
    }

    /*
     *GETTING ALL VALUES
     */

    /**
     * Method that will search the Database for ALL Provider objects.
     * Values may be null and be used as wildCard.
     *
     * @return HashMap of all the found provider. Key values equals the unique ProviderID
     * @throws SQLException Exception when encountered a fatal error
     * @see Provider
     */
    public static ArrayList<Provider> findAllProviders() throws SQLException {
        //init needed values
        HashMap<Integer, Provider> returnMap = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_PROVIDER, null, null);

        while (rs.next()) {
            Provider tempProvider = new Provider(rs);
            returnMap.put(tempProvider.getProviderID(), tempProvider);
        }
        return new ArrayList<>(returnMap.values());
    }


    /*
     * HELPER METHODS FOR FINDING
     */

    private static void buildProvider(ResultSet rs, HashMap<Integer, Provider> providerHashMap) throws SQLException {
        //Fetch values from rs
        int id = rs.getInt("fld_ProviderID");
        String providerName = rs.getString("fld_ProviderName");

        // Make new Provider
        Provider provider = new Provider(id, providerName);

        //Put provider in HashMap
        providerHashMap.putIfAbsent(id, provider);
    }

    //Should be done AFTER call to buildProvider
    private static void buildEducation(ResultSet rs,
                                       HashMap<Integer, Education> educationHashMap,
                                       HashMap<Integer, Provider> providerHashMap) throws SQLException {
        //Fetch values from rs
        int id = rs.getInt("fld_AmuNR");
        String name = rs.getString("fld_EducationName");
        String description = rs.getString("fld_Description");
        int noOfDays = rs.getInt("fld_NoOfDays");
        LocalDate localDate = rs.getDate("fld_Date").toLocalDate();

        //fetch the provider from the hashMap
        int providerId = rs.getInt("fld_ProviderID");
        Provider provider = providerHashMap.get(providerId);

        //Make new Education
        Education education = new Education(id, name, description, noOfDays, new ArrayList<>(), provider);

        // Put education in hashMap and add date to the right reference
        education = educationHashMap.putIfAbsent(id, education);
        if (education != null) {
            education.getDates().add(localDate);
        }
    }

    //should be called after education since we need the updated HashMap
    private static void buildFinishedEducation(ResultSet rs,
                                               HashMap<Integer, FinishedEducation> finishedEducationHashMap,
                                               HashMap<Integer, Education> educationHashMap) throws SQLException {
        // Fetch values from rs
        int id = rs.getInt("fld_FinishedEducationID");
        LocalDate localDate = rs.getDate("fld_FinishedDate").toLocalDate();

        // Fetch values from updated hashMap
        int educationId = rs.getInt("fld_AmuNR");
        Education education = educationHashMap.get(educationId);

        // make new object
        FinishedEducation finishedEducation = new FinishedEducation(id, education, localDate);

        //Put Object in hashMap and add references
        finishedEducationHashMap.putIfAbsent(id, finishedEducation);
    }

    //should be called after  education we need the updated HashMap
    private static void buildEducationWish(ResultSet rs,
                                           HashMap<Integer, EducationWish> educationWishHashMap,
                                           HashMap<Integer, Education> educationHashMap) throws SQLException {
        // Fetch values from rs
        int id = rs.getInt("fld_EducationWishID");
        int priority = rs.getInt("fld_WishPriority");

        // Fetch values from updated hashMap
        int educationId = rs.getInt("fld_AmuNR");
        Education education = educationHashMap.get(educationId);

        // make new object
        EducationWish educationWish = new EducationWish(id, education, priority);

        //Put Object in hashMap and add references
        educationWishHashMap.putIfAbsent(id, educationWish);
    }

    //should be called after both finished education and education wish
    private static void buildInterview(ResultSet rs,
                                       HashMap<Integer, EducationWish> educationWishHashMap,
                                       HashMap<Integer, FinishedEducation> finishedEducationHashMap,
                                       HashMap<Integer, Interview> interviewHashMap) throws SQLException {
        // Fetch values from rs
        int id = rs.getInt("fld_InterviewID");
        String name = rs.getString("fld_InterviewName");
        int productUnderstanding = rs.getInt("fld_ProductUnderstanding");
        int problemUnderstanding = rs.getInt("fld_ProblemUnderstanding");
        int flexibility = rs.getInt("fld_Flexibility");
        int qualityAwareness = rs.getInt("fld_QualityAwareness");
        int cooperation = rs.getInt("fld_Cooperation");

        // Fetch values from updated hashMap
        int eduWishID = rs.getInt("fld_EducationWishID");
        EducationWish educationWish = educationWishHashMap.get(eduWishID);

        int finEduID = rs.getInt("fld_FinishedEducationID");
        FinishedEducation finishedEducation = finishedEducationHashMap.get(finEduID);

        // make new object
        Interview interview = new Interview(id,name,productUnderstanding,problemUnderstanding,flexibility,qualityAwareness,cooperation,new ArrayList<>(),new ArrayList<>());

        //Put Object in hashMap and add references
        interview = interviewHashMap.putIfAbsent(id,interview);
        if (interview != null){
            interview.getEducationWishes().add(educationWish);
            interview.getFinishedEducations().add(finishedEducation);
        }
    }

    //should be called after interview
    private static void buildEmployee(ResultSet rs,
                                      HashMap<Integer,Interview> interviewHashMap,
                                      HashMap<Integer, Employee> employeeHashMap) throws SQLException{
        // Fetch values from rs
        int id = rs.getInt("fld_EmployeeID");
        String firstName = rs.getString("fld_EmployeeFirstName");
        String lastName = rs.getString("fld_EmployeeLastName");
        String cpr =rs.getString("fld_CprNr");
        String eMail = rs.getString("fld_EMail");
        String phoneNr = rs.getString("fld_PhoneNr");

        // Fetch values from updated hashMap
        int interviewId = rs.getInt("fld_InterviewID");
        Interview interview = interviewHashMap.get(interviewId);

        // make new object
        Employee employee = new Employee(id,firstName,lastName,cpr,eMail,phoneNr,new ArrayList<>());

        //Put Object in hashMap and add references
        employee = employeeHashMap.putIfAbsent(id,employee);
        if (employee != null){
            employee.getInterviews().add(interview);
        }
    }

    //should be called after consultation
    private static void buildConsultation(ResultSet rs,
                                          HashMap<Integer, Employee> employeeHashMap,
                                          HashMap<Integer, Consultation> consultationHashMap) throws SQLException {
        // Fetch values from rs
        int id = rs.getInt("fld_ConsultationID");
        String name = rs.getString("fld_ConsultationName");
        LocalDate startDate = rs.getDate("fld_StartDate").toLocalDate();
        LocalDate endDate = rs.getDate("fld_EndDate").toLocalDate();

        // Fetch values from updated hashMap
        int employeeId = rs.getInt("fld_EmployeeID");
        Employee employee = employeeHashMap.get(employeeId);

        // make new object
        Consultation consultation = new Consultation(id,name,startDate,endDate,new ArrayList<>());

        //Put Object in hashMap and add references
        consultation = consultationHashMap.putIfAbsent(id,consultation);
        if (consultation != null){
            consultation.getEmployees().add(employee);
        }
    }

    //should be called after education and consultation
    private static void buildCompany(ResultSet rs,
                                     HashMap<Integer, Consultation> consultationHashMap,
                                     HashMap<Integer,Education> educationHashMap,
                                     HashMap<Integer,Company> companyHashMap) throws SQLException  {
        // Fetch values from rs
        int id = rs.getInt("fld_CompanyID");
        String cvr = rs.getString("fld_CvrNr");
        String name = rs.getString("fld_CompanyName");

        // Fetch values from updated hashMap
        int consultationId = rs.getInt("fld_ConsultationID");
        Consultation consultation = consultationHashMap.get(consultationId);

        int educatiionId = rs.getInt("fld_EducationID");
        Education education = educationHashMap.get(educatiionId);

        // make new object
        Company company = new Company(id,cvr,name,new ArrayList<>(),new ArrayList<>());

        //Put Object in hashMap and add references
        company = companyHashMap.putIfAbsent(id,company);
        if (company != null){
            company.getConsultations().add(consultation);
            company.getEducationList().add(education);
        }
    }


    /***
     * Is bugged
     * @return bug
     * @throws SQLException 100 percent of thetime
     * @deprecated is bugged, see DbFace.
     */
    @Deprecated
    public static HashMap<Integer, Company> FindAllCompanies() throws SQLException {

        //init needed values
        HashMap<Integer, Company> returnCompNames = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure("sp_FindCompanyNames");

        while (rs.next()) {
            Company tempCompanyNames = new Company(rs);
            returnCompNames.put(tempCompanyNames.getCompanyID(), tempCompanyNames);
        }
        return returnCompNames;

    }

}
