package Foundation;

import Domain.*;
import Foundation.DB;
import Foundation.Sp;
import Foundation.SpGetKey;
import Foundation.SpWithRs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Facade class that helps convert SQL data to objects.
 * <br/><br/>
 * <Font Color = red>Important</Font Color> Connection must be managed by callee with
 * {@link DB#connect()} and {@link DB#disconnect()}.
 *
 * @see DB
 */
@SuppressWarnings("Duplicates") //Dont judge me, copy past code is the way to go here. -Sven
public class DbFacade {

    /*
     * INSERTION
     */

    /**
     * Method that will write the a {@link Provider} object to the database.
     * Note that this method expects that the Object structure is referencing each other correctly.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param provider The Container for the values that will be inserted.
     * @return int Primary Key of the inserted or update Provider.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static int insertProvider(Provider provider) throws SQLException {
        DB database = DB.getInstance();

        Integer providerID = provider.getProviderID();
        String providerName = provider.getProviderName();
        // 1 Insert this provider
        providerID = database.executeStoredProcedure(SpGetKey.PLACE_PROVIDER, providerID, providerName);

        // 2 return inserted ID
        return providerID;
    }

    /**
     * Method that will write the a {@link Education} object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * Note that this method expects that the Object structure is referencing each other correctly.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param education The Container for the values that will be inserted.
     * @return int Primary Key of the inserted or update Education.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static int insertEducation(Education education) throws SQLException {
        DB database = DB.getInstance();

        // 1 insert provider via other method that return its ID
        int providerID = insertProvider(education.getProvider());

        // 2 Insert this education and get AmuNr
        Integer amuNr = education.getAmuNr();
        String educationName = education.getEducationName();
        String educationDescription = education.getDescription();
        int noOfDays = education.getNoOfDays();

        amuNr = database.executeStoredProcedure(SpGetKey.PLACE_EDUCATION, amuNr, providerID, educationName, educationDescription, noOfDays);

        // 3 Insert dates with amurNr
        ArrayList<LocalDate> dates = education.getDates();
        for (LocalDate date : dates) {
            database.addStoredProcedureToBatch(Sp.PLACE_DATE, null, amuNr, date);
        }
        database.executeBatch();

        // 4 return amu nr
        return amuNr;
    }

    /**
     * Method that will write a {@link EducationWish} object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * Note that this method expects that the Object structure is referencing each other correctly.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param educationWish The Container for the values that will be inserted.
     * @param interViewID   ID of interview the tbl_EducationWish record will point to.
     * @return int Primary Key of the inserted or update EducationWish.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static int insertEducationWish(EducationWish educationWish, int interViewID) throws SQLException {
        DB database = DB.getInstance();

        // 1 Write education to db and get returnvalue
        Education education = educationWish.getEducation();
        int amuNr = insertEducation(education);

        // 2 We insert this education wish and get inserted id
        Integer educationWishID = educationWish.getEducationWishID();
        Integer priority = educationWish.getPriority();

        educationWishID = database.executeStoredProcedure(SpGetKey.PLACE_EDUCATION_WISH, educationWishID, amuNr, interViewID, priority);

        // 3 return value
        return educationWishID;
    }

    /**
     * Method that will write a {@link FinishedEducation} object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * Note that this method expects that the Object structure is referencing each other correctly.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param finishedEducation The Container for the values that will be inserted.
     * @param interViewID       ID of the interview the tbl_FinishedEduction record wil point to
     * @return int Primary Key of the inserted or update FinishedEducation.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static int insertFinishedEducation(FinishedEducation finishedEducation, int interViewID) throws SQLException {
        DB database = DB.getInstance();

        // 1 Write education to db and get return value
        Education education = finishedEducation.getEducation();
        int amuNr = insertEducation(education);

        // 2 Write this finished education to db
        Integer finishedEducationID = finishedEducation.getFinishedEducationID();
        LocalDate dateFinished = finishedEducation.getDateFinished();

        finishedEducationID = database.executeStoredProcedure(SpGetKey.PLACE_FINISHED_EDUCATION, finishedEducationID, amuNr, interViewID, dateFinished);

        // 3 return value
        return finishedEducationID;

    }

    /**
     * Method that will write a {@link Employee} object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * Note that this method expects that the Object structure is referencing each other correctly.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param employee The Container for the values that will be inserted.
     * @return int Primary Key of the inserted or update Employee.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static int insertEmployee(Employee employee) throws SQLException {
        DB database = DB.getInstance();

        // 1 Insert this employee and get the id
        Integer oldEmployeeID = employee.getEmployeeId();
        String employeeFirstName = employee.getEmployeeFirstName();
        String employeeLastName = employee.getEmployeeLastName();
        String CPRnr = employee.getCprNr();
        String eMail = employee.geteMail();
        String phoneNr = employee.getPhoneNr();

        int newEmployeeID = database.executeStoredProcedure(SpGetKey.PLACE_EMPLOYEE, oldEmployeeID, employeeFirstName, employeeLastName, CPRnr, eMail, phoneNr);

        // 3 Insert interview by calling method and passing
        ArrayList<Interview> employees = employee.getInterviews();
        for (Interview interview : employees) {
            insertInterview(interview, newEmployeeID);
        }

        // 4 return employee id
        return newEmployeeID;
    }

    /**
     * Method that will write a {@link Interview} object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * Note that this method expects that the Object structure is referencing each other correctly.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     * To execute this batch call {@link DB#executeBatch()} before disconnecting.
     *
     * @param interview  The Container for the values that will be inserted.
     * @param employeeID The employee Id tbl_interview Record will point too.
     * @return int Primary Key of the inserted or update Interview.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static int insertInterview(Interview interview, int employeeID) throws SQLException {
        DB database = DB.getInstance();

        // 1 We write this interview to the db and get ID
        Integer oldInterViewID = interview.getInterviewID();
        String interViewName = interview.getInterviewName();
        Integer productUnderstanding = interview.getProductUnderstanding();
        Integer problemUnderstanding = interview.getProblemUnderstanding();
        Integer flexibility = interview.getFlexibility();
        Integer qualityAwareness = interview.getQualityAwareness();
        Integer cooperation = interview.getCooperation();

        int newInterViewID = database.executeStoredProcedure(SpGetKey.PLACE_INTERVIEW,
                oldInterViewID,
                interViewName,
                employeeID,
                productUnderstanding,
                problemUnderstanding,
                flexibility,
                qualityAwareness,
                cooperation);

        // 2 Reinster by calling method
        ArrayList<EducationWish> educationWishes = interview.getEducationWishes();
        for (EducationWish educationWish : educationWishes) {
            insertEducationWish(educationWish, newInterViewID);
        }

        // 4 reinsert by calling method
        ArrayList<FinishedEducation> finishedEducations = interview.getFinishedEducations();
        for (FinishedEducation finishedEducation : finishedEducations) {
            insertFinishedEducation(finishedEducation, newInterViewID);
        }

        return newInterViewID;
    }

    /**
     * Method that will write a {@link Company} object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * Note that this method expects that the Object structure is referencing each other correctly.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param company The Container for the values that will be inserted.
     * @return int Primary Key of the inserted or update Company.
     * @throws SQLException         Exception thrown when encountered a fatal error.
     * @throws NullPointerException Thrown if the Domain structure contain missing parts.
     * @see DB
     */
    public static int insertCompany(Company company) throws SQLException {
        DB database = DB.getInstance();

        // 1 Insert this Company into db and get company id
        Integer oldCompanyID = company.getCompanyID();
        String cvrNr = company.getCvrNr();
        String companyName = company.getCompanyName();

        int newCompanyID = database.executeStoredProcedure(SpGetKey.PLACE_COMPANY, oldCompanyID, cvrNr, companyName);

        // 2 Insert Consultation should be done by other method
        ArrayList<Consultation> consultations = company.getConsultations();
        for (Consultation consultation : consultations) {
            insertConsultation(consultation, newCompanyID);
        }

        // 3 Insert education via method that returns the inserted ID
        ArrayList<Education> educations = company.getEducationList();
        ArrayList<Integer> newEducationIDs = new ArrayList<>();
        for (Education education : educations) {
            newEducationIDs.add(insertEducation(education));
        }

        // 4 With the returnValue we can make the bridge table, purge old one
        for (Integer newEducationID : newEducationIDs) {
            database.addStoredProcedureToBatch(Sp.INSERT_COMPANY_EDUCATION_BRIDGE, newCompanyID, newEducationID);
        }
        database.executeBatch();

        // 5 return value
        return newCompanyID;
    }

    /**
     * Method that will write a {@link Consultation} object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * Note that this method expects that the Object structure is referencing each other correctly.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconect}.
     *
     * @param consultation The Container for the values that will be inserted.
     * @param companyID    The companyID that the tbl_Consultation will point to.
     * @return int Primary Key of the inserted or update Consultation
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static int insertConsultation(Consultation consultation, int companyID) throws SQLException {
        DB database = DB.getInstance();

        // 1 Insert Consultations
        Integer oldConsultationID = consultation.getConsultationID();
        String consultationName = consultation.getConsultationName();
        LocalDate startDate = consultation.getStartDate();
        LocalDate endDate = consultation.getEndDate();

        int newConsultationID = database.executeStoredProcedure(SpGetKey.PLACE_CONSULTATION, oldConsultationID, consultationName, startDate, endDate, companyID);

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
     * SEARCHING BY VALUES(DYNAMIC)
     */

    /**
     * Finds {@link Provider} Objects from the DataBase by specified search criteria.
     * The search will be done by:
     * <ul>
     * <li>
     * Strings will filter out any objects that does not match the Parameter in any place.
     * </li>
     * <li>
     * Integer will filter out any objects that do not have exactly that value.
     * </li>
     * <li>
     * LocalDates will either filter out all objects that have earlier or later Date field, depending on the parameter.
     * </li>
     * <li>
     * Null Values are allowed as wild card, filtering out nothing.
     * </li>
     * </ul>
     * This method will also make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconect}.
     *
     * @param providerID   Unique Primary key of a {@link Provider}.
     * @param providerName {@link Provider} name as String.
     * @return ArrayList of {@link Provider} Object.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We want never just the provider. Use Find Company
     */
    @Deprecated
    public static ArrayList<Provider> findProviders(Integer providerID,
                                                    String providerName) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_PROVIDER,
                providerID,
                providerName);

        while (rs.next()) {
            buildProvider(rs, providers);
        }
        return new ArrayList<>(providers.values());
    }

    /**
     * Finds {@link Education} Objects from the DataBase by specified search criteria.
     * The search will be done by:
     * <ul>
     * <li>
     * Strings will filter out any objects that does not match the Parameter in any place.
     * </li>
     * <li>
     * Integer will filter out any objects that do not have exactly that value.
     * </li>
     * <li>
     * LocalDates will either filter out all objects that have earlier or later Date field, depending on the parameter.
     * </li>
     * <li>
     * Null Values are allowed as wild card, filtering out nothing.
     * </li>
     * </ul>
     * This method will also make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconect}.
     *
     * @param AmuNr             Unique Primary key of a {@link Education}.
     * @param educationName     {@link Education} name as String.
     * @param educationNoOfDays Number of Days this {@link Education} takes.
     * @param educationMinDate  Minimum date a {@link Education} needs to have to be found.
     * @param educationMaxDate  Maximum date a {@link Education} need to have to be found
     * @param providerID        Unique Primary key of a {@link Provider}.
     * @param providerName      {@link Provider} name as String.
     * @return ArrayList of {@link Education} Object.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static ArrayList<Education> findEducations(Integer AmuNr,
                                                      String educationName,
                                                      Integer educationNoOfDays,
                                                      LocalDate educationMinDate,
                                                      LocalDate educationMaxDate,
                                                      Integer providerID,
                                                      String providerName) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EDUCATION,
                AmuNr,
                educationName,
                educationNoOfDays,
                educationMinDate,
                educationMaxDate,
                providerID,
                providerName);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
        }
        return new ArrayList<>(educations.values());
    }

    /**
     * Finds {@link Interview} Objects from the DataBase by specified search criteria.
     * The search will be done by:
     * <ul>
     * <li>
     * Strings will filter out any objects that does not match the Parameter in any place.
     * </li>
     * <li>
     * Integer will filter out any objects that do not have exactly that value.
     * </li>
     * <li>
     * LocalDates will either filter out all objects that have earlier or later Date field, depending on the parameter.
     * </li>
     * <li>
     * Null Values are allowed as wild card, filtering out nothing.
     * </li>
     * </ul>
     * This method will also make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconect}.
     *
     * @param interviewID       Unique Primary key of a {@link Interview}.
     * @param interviewName     {@link Interview} name as String.
     * @param AmuNr             Unique Primary key of a {@link Education}.
     * @param educationName     {@link Education} name as String.
     * @param educationNoOfDays Number of Days this {@link Education} takes.
     * @param educationMinDate  Minimum date a {@link Education} needs to have to be found.
     * @param educationMaxDate  Maximum date a {@link Education} need to have to be found
     * @param providerID        Unique Primary key of a {@link Provider}.
     * @param providerName      {@link Provider} name as String.
     * @return ArrayList of {@link Interview} Object.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just the Interview, use find Companies
     */
    @Deprecated
    public static ArrayList<Interview> findInterviews(Integer interviewID,
                                                      String interviewName,
                                                      Integer AmuNr,
                                                      String educationName,
                                                      Integer educationNoOfDays,
                                                      LocalDate educationMinDate,
                                                      LocalDate educationMaxDate,
                                                      Integer providerID,
                                                      String providerName) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_INTERVIEW,
                interviewID,
                interviewName,
                AmuNr,
                educationName,
                educationNoOfDays,
                educationMinDate,
                educationMaxDate,
                providerID,
                providerName);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
        }
        return new ArrayList<>(interviews.values());
    }

    /**
     * Finds {@link Employee} Objects from the DataBase by specified search criteria.
     * The search will be done by:
     * <ul>
     * <li>
     * Strings will filter out any objects that does not match the Parameter in any place.
     * </li>
     * <li>
     * Integer will filter out any objects that do not have exactly that value.
     * </li>
     * <li>
     * LocalDates will either filter out all objects that have earlier or later Date field, depending on the parameter.
     * </li>
     * <li>
     * Null Values are allowed as wild card, filtering out nothing.
     * </li>
     * </ul>
     * This method will also make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconect}.
     *
     * @param employeeID        Unique Primary key of a {@link Employee}.
     * @param employeeFirstName {@link Employee} First name as String.
     * @param employeeLastName  {@link Employee} Last name as String.
     * @param cprNr             {@link Employee} CprNr as String.
     * @param email             {@link Employee} Email Address as String.
     * @param phoneNr           {@link Employee} PhoneNr as String.
     * @param interviewID       Unique Primary key of a {@link Interview}.
     * @param interviewName     {@link Interview} name as String.
     * @param AmuNr             Unique Primary key of a {@link Education}.
     * @param educationName     {@link Education} name as String.
     * @param educationNoOfDays Number of Days this {@link Education} takes.
     * @param educationMinDate  Minimum date a {@link Education} needs to have to be found.
     * @param educationMaxDate  Maximum date a {@link Education} need to have to be found
     * @param providerID        Unique Primary key of a {@link Provider}.
     * @param providerName      {@link Provider} name as String.
     * @return ArrayList of {@link Employee} Object.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just the Interview, use find Companies
     */
    @Deprecated
    public static ArrayList<Employee> findEmployees(Integer employeeID,
                                                    String employeeFirstName,
                                                    String employeeLastName,
                                                    String cprNr,
                                                    String email,
                                                    String phoneNr,
                                                    Integer interviewID,
                                                    String interviewName,
                                                    Integer AmuNr,
                                                    String educationName,
                                                    Integer educationNoOfDays,
                                                    LocalDate educationMinDate,
                                                    LocalDate educationMaxDate,
                                                    Integer providerID,
                                                    String providerName) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();
        HashMap<Integer, Employee> employees = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EMPLOYEE,
                employeeID,
                employeeFirstName,
                employeeLastName,
                cprNr,
                email,
                phoneNr,
                interviewID,
                interviewName,
                AmuNr,
                educationName,
                educationNoOfDays,
                educationMinDate,
                educationMaxDate,
                providerID,
                providerName);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
            buildEmployee(rs, interviews, employees);
        }
        return new ArrayList<>(employees.values());
    }

    /**
     * Finds {@link Consultation} Objects from the DataBase by specified search criteria.
     * The search will be done by:
     * <ul>
     * <li>
     * Strings will filter out any objects that does not match the Parameter in any place.
     * </li>
     * <li>
     * Integer will filter out any objects that do not have exactly that value.
     * </li>
     * <li>
     * LocalDates will either filter out all objects that have earlier or later Date field, depending on the parameter.
     * </li>
     * <li>
     * Null Values are allowed as wild card, filtering out nothing.
     * </li>
     * </ul>
     * This method will also make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconect}.
     *
     * @param consultationID      Unique Primary key of a {@link Consultation}.
     * @param consultationName    {@link Consultation} name as String.
     * @param consultationMinDate Minimum date a {@link Consultation} need to have to be found.
     * @param consultationMaxDate Maximum date a {@link Consultation} need to have to be found.
     * @param employeeID          Unique Primary key of a {@link Employee}.
     * @param employeeFirstName   {@link Employee} First name as String.
     * @param employeeLastName    {@link Employee} Last name as String.
     * @param cprNr               {@link Employee} CprNr as String.
     * @param email               {@link Employee} Email Address as String.
     * @param phoneNr             {@link Employee} PhoneNr as String.
     * @param interviewID         Unique Primary key of a {@link Interview}.
     * @param interviewName       {@link Interview} name as String.
     * @param AmuNr               Unique Primary key of a {@link Education}.
     * @param educationName       {@link Education} name as String.
     * @param educationNoOfDays   Number of Days this {@link Education} takes.
     * @param educationMinDate    Minimum date a {@link Education} needs to have to be found.
     * @param educationMaxDate    Maximum date a {@link Education} need to have to be found
     * @param providerID          Unique Primary key of a {@link Provider}.
     * @param providerName        {@link Provider} name as String.
     * @return ArrayList of {@link Consultation} Object.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just the Interview, use find Companies
     */
    @Deprecated
    public static ArrayList<Consultation> findConsultations(Integer consultationID,
                                                            String consultationName,
                                                            LocalDate consultationMinDate,
                                                            LocalDate consultationMaxDate,
                                                            Integer employeeID,
                                                            String employeeFirstName,
                                                            String employeeLastName,
                                                            String cprNr,
                                                            String email,
                                                            String phoneNr,
                                                            Integer interviewID,
                                                            String interviewName,
                                                            Integer AmuNr,
                                                            String educationName,
                                                            Integer educationNoOfDays,
                                                            LocalDate educationMinDate,
                                                            LocalDate educationMaxDate,
                                                            Integer providerID,
                                                            String providerName) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();
        HashMap<Integer, Employee> employees = new HashMap<>();
        HashMap<Integer, Consultation> consultations = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_CONSULTATION,
                consultationID,
                consultationName,
                consultationMinDate,
                consultationMaxDate,
                employeeID,
                employeeFirstName,
                employeeLastName,
                cprNr,
                email,
                phoneNr,
                interviewID,
                interviewName,
                AmuNr,
                educationName,
                educationNoOfDays,
                educationMinDate,
                educationMaxDate,
                providerID,
                providerName);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
            buildEmployee(rs, interviews, employees);
            buildConsultation(rs, employees, consultations);
        }
        return new ArrayList<>(consultations.values());
    }

    /**
     * Finds {@link Company} Objects from the DataBase by specified search criteria.
     * The search will be done by:
     * <ul>
     * <li>
     * Strings will filter out any objects that does not match the Parameter in any place.
     * </li>
     * <li>
     * Integer will filter out any objects that do not have exactly that value.
     * </li>
     * <li>
     * LocalDates will either filter out all objects that have earlier or later Date field, depending on the parameter.
     * </li>
     * <li>
     * Null Values are allowed as wild card, filtering out nothing.
     * </li>
     * </ul>
     * This method will also make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconect}.
     *
     * @param companyID           Unique Primary key of a {@link Company}.
     * @param cvrNr               {@link Company} cvrNr as String.
     * @param companyName         {@link Company} name as String.
     * @param consultationID      Unique Primary key of a {@link Consultation}.
     * @param consultationName    {@link Consultation} name as String.
     * @param consultationMinDate Minimum date a {@link Consultation} need to have to be found.
     * @param consultationMaxDate Maximum date a {@link Consultation} need to have to be found.
     * @param employeeID          Unique Primary key of a {@link Employee}.
     * @param employeeFirstName   {@link Employee} First name as String.
     * @param employeeLastName    {@link Employee} Last name as String.
     * @param cprNr               {@link Employee} CprNr as String.
     * @param email               {@link Employee} Email Address as String.
     * @param phoneNr             {@link Employee} PhoneNr as String.
     * @param interviewID         Unique Primary key of a {@link Interview}.
     * @param interviewName       {@link Interview} name as String.
     * @param AmuNr               Unique Primary key of a {@link Education}.
     * @param educationName       {@link Education} name as String.
     * @param educationNoOfDays   Number of Days this {@link Education} takes.
     * @param educationMinDate    Minimum date a {@link Education} needs to have to be found.
     * @param educationMaxDate    Maximum date a {@link Education} need to have to be found
     * @param providerID          Unique Primary key of a {@link Provider}.
     * @param providerName        {@link Provider} name as String.
     * @return ArrayList of {@link Company} Object.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static ArrayList<Company> findCompanies(Integer companyID,
                                                   String cvrNr,
                                                   String companyName,
                                                   Integer consultationID,
                                                   String consultationName,
                                                   LocalDate consultationMinDate,
                                                   LocalDate consultationMaxDate,
                                                   Integer employeeID,
                                                   String employeeFirstName,
                                                   String employeeLastName,
                                                   String cprNr,
                                                   String email,
                                                   String phoneNr,
                                                   Integer interviewID,
                                                   String interviewName,
                                                   Integer AmuNr,
                                                   String educationName,
                                                   Integer educationNoOfDays,
                                                   LocalDate educationMinDate,
                                                   LocalDate educationMaxDate,
                                                   Integer providerID,
                                                   String providerName) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();
        HashMap<Integer, Employee> employees = new HashMap<>();
        HashMap<Integer, Consultation> consultations = new HashMap<>();
        HashMap<Integer, Company> companies = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_COMPANY,
                companyID,
                cvrNr,
                companyName,
                consultationID,
                consultationName,
                consultationMinDate,
                consultationMaxDate,
                employeeID,
                employeeFirstName,
                employeeLastName,
                cprNr,
                email,
                phoneNr,
                interviewID,
                interviewName,
                AmuNr,
                educationName,
                educationNoOfDays,
                educationMinDate,
                educationMaxDate,
                providerID,
                providerName);


        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
            buildEmployee(rs, interviews, employees);
            buildConsultation(rs, employees, consultations);
            buildCompany(rs, consultations, educations, companies);
        }
        return new ArrayList<>(companies.values());
    }

    /*
     * DELETE BY ID
     */

    /**
     * Deletes a {@link Provider} from the DataBase by PrimaryKey.
     * Be aware that this method also deletes all records of {@link Education Educations} that reference the deleted Provider.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param providerID Primary Key of the element that should be deleted.
     * @return True if deleted successfully.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static boolean deleteProvider(int providerID) throws SQLException {
        DB database = DB.getInstance();
        return database.executeStoredProcedure(Sp.DELETE_PROVIDER_BY_PK, providerID);
    }

    /**
     * Deletes a {@link Education} from the DataBase by PrimaryKey.
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param educationID Primary Key of the element that should be deleted.
     * @return True if deleted successfully.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static boolean deleteEducation(int educationID) throws SQLException {
        DB database = DB.getInstance();
        return database.executeStoredProcedure(Sp.DELETE_EDUCATION_BY_PK, educationID);
    }

    /**
     * Deletes a {@link Interview} from the DataBase by PrimaryKey.
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param interviewID Primary Key of the element that should be deleted.
     * @return True if deleted successfully.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static boolean deleteInterview(int interviewID) throws SQLException {
        DB database = DB.getInstance();
        return database.executeStoredProcedure(Sp.DELETE_INTERVIEW_BY_PK);
    }


    /**
     * Deletes a {@link Employee} from the DataBase by PrimaryKey.
     * Be aware that this method also deletes all records of {@link Interview Interviews} that reference the deleted Employee.
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param employeeID Primary Key of the element that should be deleted.
     * @return True if deleted successfully.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static boolean deleteEmployee(int employeeID) throws SQLException {
        DB database = DB.getInstance();
        return database.executeStoredProcedure(Sp.DELETE_EMPLOYEE_BY_PK, employeeID);
    }

    /**
     * Deletes a {@link Consultation} from the DataBase by PrimaryKey.
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param consultationID Primary Key of the element that should be deleted.
     * @return True if deleted successfully.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static boolean deleteConsultation(int consultationID) throws SQLException {
        DB database = DB.getInstance();
        return database.executeStoredProcedure(Sp.DELETE_CONSULTATION_BY_PK, consultationID);
    }

    /**
     * Deletes a {@link Company} from the DataBase by PrimaryKey.
     * Be aware that this method also deletes all records of {@link Consultation Consultations} that reference the deleted Company.
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param companyID Primary Key of the element that should be deleted.
     * @return True if deleted successfully.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static boolean deleteCompany(int companyID) throws SQLException {
        DB database = DB.getInstance();
        return database.executeStoredProcedure(Sp.DELETE_COMPANY_BY_PK, companyID);
    }


    /*
     * GET BY PRIMARY KEY
     */

    /**
     * Finds a {@link Provider} from the DataBase by PrimaryKey.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param providerID Primary Key of the element that should be returned.
     * @return {@link Provider} Object with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just the one object, use find Companies
     */
    @Deprecated
    public static Provider findProvider(int providerID) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_PROVIDER, providerID, null);

        while (rs.next()) {
            buildProvider(rs, providers);
        }
        if (providers.isEmpty()) { //test if nothing was found
            return null;
        }
        return new ArrayList<>(providers.values()).get(0);
    }

    /**
     * Finds a {@link Education} from the DataBase by PrimaryKey.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param educationID Primary Key of the element that should be returned.
     * @return {@link Education} Object with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just the one object, use find Companies
     */
    @Deprecated
    public static Education findEducation(int educationID) throws SQLException {
        Education returnEducation = null;
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();


        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EDUCATION, educationID, null, null, null, null, null, null);

        //get row by row
        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
        }
        if (educations.isEmpty()) { //test if nothing was found
            return null;
        }
        return new ArrayList<>(educations.values()).get(0);
    }

    /**
     * Finds a {@link Interview} from the DataBase by PrimaryKey.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param interviewID Primary Key of the element that should be returned.
     * @return {@link Interview} Object with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just the one object, use find Companies
     */
    @Deprecated
    public static Interview findInterview(int interviewID) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_INTERVIEW, interviewID, null, null, null, null, null, null, null, null);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
        }
        if (interviews.isEmpty()) { //test if nothing was found
            return null;
        }
        return new ArrayList<>(interviews.values()).get(0);
    }

    /**
     * Finds a {@link Employee} from the DataBase by PrimaryKey.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param employeeID Primary Key of the element that should be returned.
     * @return {@link Employee} Object with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just the one object, use find Companies
     */
    @Deprecated
    public static Employee findEmployee(int employeeID) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();
        HashMap<Integer, Employee> employees = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EMPLOYEE, employeeID, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
            buildEmployee(rs, interviews, employees);
        }
        if (employees.isEmpty()) { //test if nothing was found
            return null;
        }
        return new ArrayList<>(employees.values()).get(0);
    }

    /**
     * Finds a {@link Consultation} from the DataBase by PrimaryKey.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param consultationID Primary Key of the element that should be returned.
     * @return {@link Consultation} Object with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just the one object, use find Companies
     */
    @Deprecated
    public static Consultation findConsultation(int consultationID) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();
        HashMap<Integer, Employee> employees = new HashMap<>();
        HashMap<Integer, Consultation> consultations = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_CONSULTATION, consultationID, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
            buildEmployee(rs, interviews, employees);
            buildConsultation(rs, employees, consultations);
        }
        if (consultations.isEmpty()) { //test if nothing was found
            return null;
        }
        return new ArrayList<>(consultations.values()).get(0);
    }

    /**
     * Finds a {@link Company} from the DataBase by PrimaryKey.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param companyID Primary Key of the element that should be returned.
     * @return {@link Company} Object with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just the one object, use find Companies
     */
    @Deprecated
    public static Company findCompany(int companyID) throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();
        HashMap<Integer, Employee> employees = new HashMap<>();
        HashMap<Integer, Consultation> consultations = new HashMap<>();
        HashMap<Integer, Company> companies = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_COMPANY, companyID, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
            buildEmployee(rs, interviews, employees);
            buildConsultation(rs, employees, consultations);
            buildCompany(rs, consultations, educations, companies);
        }
        if (companies.isEmpty()) { //test if nothing was found
            return null;
        }
        return new ArrayList<>(companies.values()).get(0);
    }

    /*
     *GETTING ALL VALUES
     */

    /**
     * Finds all {@link Provider Providers} from the DataBase.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link Provider Provider} Objects with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just this method, use find Companies
     */
    @Deprecated
    public static ArrayList<Provider> findAllProviders() throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_PROVIDER, null, null);

        while (rs.next()) {
            buildProvider(rs, providers);
        }
        return new ArrayList<>(providers.values());
    }

    /**
     * Finds all {@link Education Educations} from the DataBase.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link Education} Objects with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just this method, use find Companies
     */
    @Deprecated
    public static ArrayList<Education> findAllEducations() throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EDUCATION, null, null, null, null, null, null, null);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
        }
        return new ArrayList<>(educations.values());
    }

    /**
     * Finds all {@link Interview Interviews} from the DataBase.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link Interview} Objects with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just this method, use find Companies
     */
    @Deprecated
    public static ArrayList<Interview> findAllInterviews() throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_INTERVIEW, null, null, null, null, null, null, null, null, null);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
        }
        return new ArrayList<>(interviews.values());
    }

    /**
     * Finds all {@link Employee Employees} from the DataBase.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link Employee} Objects with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just this method, use find Companies
     */
    @Deprecated
    public static ArrayList<Employee> findAllEmployees() throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();
        HashMap<Integer, Employee> employees = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EMPLOYEE, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
            buildEmployee(rs, interviews, employees);
        }
        return new ArrayList<>(employees.values());
    }

    /**
     * Finds all {@link Consultation Consultations} from the DataBase.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link Consultation} Objects with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just this method, use find Companies
     */
    @Deprecated
    public static ArrayList<Consultation> findAllConsultations() throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();
        HashMap<Integer, Employee> employees = new HashMap<>();
        HashMap<Integer, Consultation> consultations = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_CONSULTATION, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
            buildEmployee(rs, interviews, employees);
            buildConsultation(rs, employees, consultations);
        }
        return new ArrayList<>(consultations.values());
    }

    /**
     * Finds all {@link Company Companies} from the DataBase.
     * This method will make sure to rebuild the object references correctly. Note that this is a resource intensive operation.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link Company} Objects with correct reference structure.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     * @deprecated We never want just this method, use find Companies
     */
    @Deprecated
    public static ArrayList<Company> findAllCompanies() throws SQLException {
        //init needed values
        HashMap<Integer, Provider> providers = new HashMap<>();
        HashMap<Integer, Education> educations = new HashMap<>();
        HashMap<Integer, Interview> interviews = new HashMap<>();
        HashMap<Integer, FinishedEducation> finishedEducations = new HashMap<>();
        HashMap<Integer, EducationWish> educationWishes = new HashMap<>();
        HashMap<Integer, Employee> employees = new HashMap<>();
        HashMap<Integer, Consultation> consultations = new HashMap<>();
        HashMap<Integer, Company> companies = new HashMap<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_COMPANY, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        while (rs.next()) {
            buildProvider(rs, providers);
            buildEducation(rs, educations, providers);
            buildEducationWish(rs, educationWishes, educations);
            buildFinishedEducation(rs, finishedEducations, educations);
            buildInterview(rs, educationWishes, finishedEducations, interviews);
            buildEmployee(rs, interviews, employees);
            buildConsultation(rs, employees, consultations);
            buildCompany(rs, consultations, educations, companies);
        }
        return new ArrayList<>(companies.values());
    }

    /*
     * HELPER METHODS FOR FINDING
     */

    /**
     * Helper Method that builds a {@link Provider}.
     *
     * @param rs              ResultSet of Data
     * @param providerHashMap HashMap of Primary ID as key, {@link Provider} as value.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    private static void buildProvider(ResultSet rs, HashMap<Integer, Provider> providerHashMap) throws SQLException {
        //Fetch values from rs and test if the rs contain the object
        int id = rs.getInt("tbl_Provider_PK_fld_ProviderID");
        if (!rs.wasNull()) { //Test for existences
            String providerName = rs.getString("tbl_Provider_fld_ProviderName");

            // Make new Provider
            Provider provider = new Provider(id, providerName);

            //Put provider in HashMap
            providerHashMap.putIfAbsent(id, provider);
        }
    }

    /**
     * Helper Method that builds a {@link Education}.
     * Note that this method must be called after {@link #buildProvider}
     * for any given ResultSet row.
     *
     * @param rs               ResultSet of Data
     * @param educationHashMap HashMap of Primary ID as key, {@link Education} as value.
     * @param providerHashMap  HashMap of Primary ID as key, {@link Provider} as value.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    private static void buildEducation(ResultSet rs,
                                       HashMap<Integer, Education> educationHashMap,
                                       HashMap<Integer, Provider> providerHashMap) throws SQLException {
        //Fetch values from rs and test if the rs contain the object
        int id = rs.getInt("tbl_Education_PK_fld_AmuNr");
        if (!rs.wasNull()) { //Test for existences
            String name = rs.getString("tbl_Education_fld_EducationName");
            String description = rs.getString("tbl_Education_fld_Description");
            int noOfDays = rs.getInt("tbl_Education_fld_NoOfDays"); //Can not be null in db
            LocalDate localDate = rs.getDate("tbl_Date_fld_Date").toLocalDate();

            //fetch the provider from the hashMap
            Provider provider = null;
            int providerId = rs.getInt("tbl_Education_FK_fld_ProviderID");
            if (!rs.wasNull()) {
                provider = providerHashMap.get(providerId);
            }

            //Make new Education
            Education education = new Education(id, name, description, noOfDays, new ArrayList<>(), provider);

            // Put education in hashMap and add date to the right reference
            education = educationHashMap.putIfAbsent(id, education);
            if (education != null) {
                education.getDates().add(localDate);
            }
        }
    }

    /**
     * Helper Method that builds a {@link FinishedEducation}.
     * Note that this method must be called after {@link #buildEducation}
     * for any given ResultSet row.
     *
     * @param rs                       ResultSet of Data
     * @param finishedEducationHashMap HashMap of Primary ID as key, {@link FinishedEducation} as value.
     * @param educationHashMap         HashMap of Primary ID as key, {@link Education} as value.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    private static void buildFinishedEducation(ResultSet rs,
                                               HashMap<Integer, FinishedEducation> finishedEducationHashMap,
                                               HashMap<Integer, Education> educationHashMap) throws SQLException {
        // Fetch values from rs
        int id = rs.getInt("tbl_FinishedEducation_PK_fld_FinishedEducationID");
        if (!rs.wasNull()) { //Test for existences
            LocalDate localDate = rs.getDate("tbl_FinishedEducation_fld_FinishedDate").toLocalDate();

            // Fetch values from updated hashMap
            int educationId = rs.getInt("tbl_FinishedEducation_FK_fld_AmuNr");
            Education education = null;
            if (!rs.wasNull()) {
                education = educationHashMap.get(educationId);
            }

            // make new object
            FinishedEducation finishedEducation = new FinishedEducation(id, education, localDate);

            //Put Object in hashMap and add references
            finishedEducationHashMap.putIfAbsent(id, finishedEducation);
        }
    }

    /**
     * Helper Method that builds a {@link EducationWish}.
     * Note that this method must be called after {@link #buildEducation}
     * for any given ResultSet row.
     *
     * @param rs                   ResultSet of Data
     * @param educationWishHashMap HashMap of Primary ID as key, {@link EducationWish} as value.
     * @param educationHashMap     HashMap of Primary ID as key, {@link Education} as value.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    private static void buildEducationWish(ResultSet rs,
                                           HashMap<Integer, EducationWish> educationWishHashMap,
                                           HashMap<Integer, Education> educationHashMap) throws SQLException {
        // Fetch values from rs
        int id = rs.getInt("tbl_EducationWish_PK_fld_EducationWishID");
        if (!rs.wasNull()) { //Test for existences
            int priority = rs.getInt("tbl_EducationWish_fld_WishPriority");

            // Fetch values from updated hashMap
            int educationId = rs.getInt("tbl_EducationWish_FK_fld_AmuNr");
            Education education = null;
            if (!rs.wasNull()) {
                education = educationHashMap.get(educationId);
            }

            // make new object
            EducationWish educationWish = new EducationWish(id, education, priority);

            //Put Object in hashMap and add references
            educationWishHashMap.putIfAbsent(id, educationWish);
        }
    }

    /**
     * Helper Method that builds a {@link Interview}.
     * Note that this method must be called after {@link #buildFinishedEducation} and {@link #buildEducationWish}
     * for any given ResultSet row.
     *
     * @param rs                       ResultSet of Data
     * @param educationWishHashMap     HashMap of Primary ID as key, {@link EducationWish} as value.
     * @param finishedEducationHashMap HashMap of Primary ID as key, {@link FinishedEducation} as value.
     * @param interviewHashMap         HashMap of Primary ID as key, {@link Interview} as value.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    private static void buildInterview(ResultSet rs,
                                       HashMap<Integer, EducationWish> educationWishHashMap,
                                       HashMap<Integer, FinishedEducation> finishedEducationHashMap,
                                       HashMap<Integer, Interview> interviewHashMap) throws SQLException {
        // Fetch values from rs
        int id = rs.getInt("tbl_Interview_PK_fld_InterviewID");
        if (!rs.wasNull()) { //Test for existence
            String name = rs.getString("tbl_Interview_fld_InterviewName");
            Integer productUnderstanding = rs.getInt("tbl_Interview_fld_ProductUnderstanding"); //Can be null, rs set it to 0
            if (rs.wasNull()) {
                productUnderstanding = null;
            }
            Integer problemUnderstanding = rs.getInt("tbl_Interview_fld_ProblemUnderstanding");
            if (rs.wasNull()) {
                problemUnderstanding = null;
            }
            Integer flexibility = rs.getInt("tbl_Interview_fld_Flexibility");
            if (rs.wasNull()) {
                flexibility = null;
            }
            Integer qualityAwareness = rs.getInt("tbl_Interview_fld_QualityAwarness");
            if (rs.wasNull()) {
                qualityAwareness = null;
            }
            Integer cooperation = rs.getInt("tbl_Interview_fld_Cooperation");
            if (rs.wasNull()) {
                cooperation = null;
            }

            // Fetch values from updated hashMap
            int eduWishID = rs.getInt("tbl_EducationWish_PK_fld_EducationWishID");
            EducationWish educationWish = null;
            if (!rs.wasNull()) {
                educationWish = educationWishHashMap.get(eduWishID);
            }

            int finEduID = rs.getInt("tbl_FinishedEducation_PK_fld_FinishedEducationID");
            FinishedEducation finishedEducation = null;
            if (!rs.wasNull()) {
                finishedEducation = finishedEducationHashMap.get(finEduID);
            }

            // make new object
            Interview interview = new Interview(id, name, productUnderstanding, problemUnderstanding, flexibility, qualityAwareness, cooperation, new ArrayList<>(), new ArrayList<>());

            //Put Object in hashMap and add references
            interview = interviewHashMap.putIfAbsent(id, interview);
            if (interview != null) {
                interview.getEducationWishes().add(educationWish);
                interview.getFinishedEducations().add(finishedEducation);
            }
        }
    }

    /**
     * Helper Method that builds a {@link Employee}.
     * Note that this method must be called after  {@link #buildInterview}
     * for any given ResultSet row.
     *
     * @param rs               ResultSet of Data
     * @param interviewHashMap HashMap of Primary ID as key, {@link Interview} as value.
     * @param employeeHashMap  HashMap of Primary ID as key, {@link Employee} as value.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    private static void buildEmployee(ResultSet rs,
                                      HashMap<Integer, Interview> interviewHashMap,
                                      HashMap<Integer, Employee> employeeHashMap) throws SQLException {
        // Fetch values from rs
        int id = rs.getInt("tbl_Employee_PK_fld_EmployeeID");
        if (!rs.wasNull()) {
            String firstName = rs.getString("tbl_Employee_fld_EmployeeFirstName");
            String lastName = rs.getString("tbl_Employee_fld_EmployeeLastName");
            String cpr = rs.getString("tbl_Employee_fld_CprNr");
            String eMail = rs.getString("tbl_Employee_fld_Email");
            String phoneNr = rs.getString("tbl_Employee_fld_PhoneNr");

            // Fetch values from updated hashMap
            int interviewId = rs.getInt("tbl_Interview_PK_fld_InterviewID");
            Interview interview = null;
            if (!rs.wasNull()) {
                interview = interviewHashMap.get(interviewId);
            }
            // make new object
            Employee employee = new Employee(id, firstName, lastName, cpr, eMail, phoneNr, new ArrayList<>());

            //Put Object in hashMap and add references
            employee = employeeHashMap.putIfAbsent(id, employee);
            if (employee != null) {
                employee.getInterviews().add(interview);
            }
        }
    }

    /**
     * Helper Method that builds a {@link Consultation}.
     * Note that this method must be called after  {@link #buildEmployee}
     * for any given ResultSet row.
     *
     * @param rs                  ResultSet of Data
     * @param employeeHashMap     HashMap of Primary ID as key, {@link Employee} as value.
     * @param consultationHashMap HashMap of Primary ID as key, {@link Consultation} as value.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    private static void buildConsultation(ResultSet rs,
                                          HashMap<Integer, Employee> employeeHashMap,
                                          HashMap<Integer, Consultation> consultationHashMap) throws SQLException {
        // Fetch values from rs
        int id = rs.getInt("tbl_Consultation_PK_fld_ConsultationID");
        if (!rs.wasNull()) {
            String name = rs.getString("tbl_Consultation_fld_ConsultationName");
            LocalDate startDate = rs.getDate("tbl_Consultation_fld_StartDate").toLocalDate();
            LocalDate endDate = rs.getDate("tbl_Consultation_fld_EndDate").toLocalDate();

            // Fetch values from updated hashMap
            int employeeId = rs.getInt("tbl_Consultation_Employee_Bridge_FK_fld_EmployeeID");
            Employee employee = null;
            if (!rs.wasNull()) {
                employee = employeeHashMap.get(employeeId);
            }

            // make new object
            Consultation consultation = new Consultation(id, name, startDate, endDate, new ArrayList<>());

            //Put Object in hashMap and add references
            consultation = consultationHashMap.putIfAbsent(id, consultation);
            if (consultation != null) {
                consultation.getEmployees().add(employee);
            }
        }
    }

    /**
     * Helper Method that builds a {@link Company}.
     * Note that this method must be called after  {@link #buildEmployee} and {@link #buildEducation}
     * for any given ResultSet row.
     *
     * @param rs                  ResultSet of Data
     * @param consultationHashMap HashMap of Primary ID as key, {@link Interview} as value.
     * @param companyHashMap      HashMap of Primary ID as key, {@link Company} as value.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    private static void buildCompany(ResultSet rs,
                                     HashMap<Integer, Consultation> consultationHashMap,
                                     HashMap<Integer, Education> educationHashMap,
                                     HashMap<Integer, Company> companyHashMap) throws SQLException {
        // Fetch values from rs
        int id = rs.getInt("tbl_Company_PK_fld_CompanyID");
        if (!rs.wasNull()) {
            String cvr = rs.getString("tbl_Company_fld_CvrNr");
            String name = rs.getString("tbl_Company_fld_CompanyName");

            // Fetch values from updated hashMap
            int consultationId = rs.getInt("tbl_Consultation_PK_fld_ConsultationID");
            Consultation consultation = null;
            if (!rs.wasNull()) {
                consultation = consultationHashMap.get(consultationId);
            }

            int educatiionId = rs.getInt("tbl_Company_Education_Bridge_FK_fld_AmurNr");
            Education education = null;
            if (!rs.wasNull()) {
                education = educationHashMap.get(educatiionId);
            }

            // make new object
            Company company = new Company(id, cvr, name, new ArrayList<>(), new ArrayList<>());

            //Put Object in hashMap and add references
            company = companyHashMap.putIfAbsent(id, company);
            if (company != null) {
                company.getConsultations().add(consultation);
                company.getEducationList().add(education);
            }
        }
    }


    /**
     * Method to establish a connection to the Database.
     * Note that this must be called before running any other methods from this class.
     * After finished Database interaction {@link #disconnect()} must be called.
     *
     * @throws SQLException Exception when SQL encounter a fatal problem
     * @see DB
     */
    public static void connect() throws SQLException{
        DB.getInstance().connect();
    }

    /**
     * Method to close connection to the Database.
     *
     * @throws SQLException Exception when SQL encounter a fatal problem
     * @see DB
     */
    public static void disconnect() throws SQLException{
        DB.getInstance().disconnect();
    }



}
