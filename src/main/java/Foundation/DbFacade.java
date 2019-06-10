package Foundation;

import Application.SearchContainer;
import Domain.DisplayObjects.*;
import Domain.DomainObjects.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

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

    private static HashMap<Integer, Consultation> consultationCache = new HashMap<>();
    private static HashMap<Integer, Employee> employeeCache = new HashMap<>();
    private static HashMap<Integer, Interview> interviewCache = new HashMap<>();
    private static HashMap<Integer, Education> educationCache = new HashMap<>();
    private static HashMap<Integer, Provider> providerCache = new HashMap<>();

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
        Integer oldEmployeeID = employee.getEmployeeID();
        String employeeFirstName = employee.getEmployeeFirstName();
        String employeeLastName = employee.getEmployeeLastName();
        String CPRnr = employee.getCprNr();
        String eMail = employee.getEmail();
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
        return database.executeStoredProcedure(Sp.DELETE_INTERVIEW_BY_PK, interviewID);
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

    /**
     * Deletes a {@link FinishedEducation} from the DataBase by FK.
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param interviewID Primary Key of the interview this finished Education will be removed from.
     * @return True if deleted successfully.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static boolean deleteFinishedEducationByInterviewID(int interviewID) throws SQLException {
        DB database = DB.getInstance();
        return database.executeStoredProcedure(Sp.DELETE_FINISHED_EDUCATION_BY_INTERVIEW_ID, interviewID);
    }

    /**
     * Deletes a {@link EducationWish} from the DataBase by FK.
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @param interviewID Primary Key of the interview this Education Wish will be removed from.
     * @return True if deleted successfully.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static boolean deleteEducationWishByInterviewID(int interviewID) throws SQLException {
        DB database = DB.getInstance();
        return database.executeStoredProcedure(Sp.DELETE_EDUCATION_WISH_BY_INTERVIEW_ID, interviewID);
    }

    /*
     * get by ID
     */

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
     */
    public static Company findCompanyByID(int companyID) throws SQLException {
        Company returnCompany = null;
        //fetch information
        ArrayList<Consultation> newConsultations = findConsultationByCompanyID(companyID);

        //getRet of data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_COMPANY_BY_ID,companyID);
        while (rs.next()){
            int newCompanyId = rs.getInt("CompanyID");
            if (!rs.wasNull()){
                String newCompanyCvrNr = rs.getString("CompanyCvrNr");
                String newCompanyName = rs.getString("CompanyName");

                returnCompany = new Company(newCompanyId,newCompanyCvrNr,newCompanyName,newConsultations);
            }
        }

        clearCache();
        return returnCompany;
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
     */
    public static Consultation findConsultationByID(int consultationID) throws SQLException {
        Consultation returnConsultation = null;
        //fetch information
        ArrayList<Employee> newEmployees = findEmployeesByConsultationID(consultationID);

        //get rest of data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_CONSULTATION_BY_ID,consultationID);
        while(rs.next()){
            int newConsultationId = rs.getInt("ConsultationID");
            if (!rs.wasNull()){
                String newConsultationName = rs.getString("ConsultationName");
                LocalDate newConsultationStartDate = rs.getDate("ConsultationStartDate").toLocalDate();
                LocalDate newConsultationEndDate = rs.getDate("ConsultationEndDate").toLocalDate();

                returnConsultation = new Consultation(newConsultationId,newConsultationName,newConsultationStartDate,newConsultationEndDate,newEmployees);
            }
        }
        clearCache();
        return returnConsultation;
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
     */
    public static Employee findEmployeeByID(int employeeID) throws SQLException {
        Employee returnEmployee = null;
        //Fetch information
        ArrayList<Interview> newInterviews = findInterviewsByEmployeeID(employeeID);

        //get rest of Data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EMPLOYEE_BY_ID, employeeID);
        while (rs.next()){
            int newEmployeeId = rs.getInt("EmployeeID");
            if (!rs.wasNull()) {
                String newEmployeeFirstName = rs.getString("EmployeeFirstName");
                String newEmployeeLastName = rs.getString("EmployeeLastName");
                String newEmployeeCpr = rs.getString("EmployeeCprNr");
                String newEmployeeEmail = rs.getString("EmployeeEmail");
                String newEmployeePhoneNr = rs.getString("EmployePhoneNr");

                returnEmployee = new Employee(newEmployeeId, newEmployeeFirstName, newEmployeeLastName, newEmployeeCpr, newEmployeeEmail, newEmployeePhoneNr,newInterviews);
            }
        }
        clearCache();
        return returnEmployee;
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
     */
    public static Interview findInterviewByID(int interviewID) throws SQLException {
        Interview returnInterview = null;
        //Fetch information deeper in the hierarchy
        ArrayList<FinishedEducation> newFinishedEducations = findFinishedEducationsByInterviewID(interviewID);
        ArrayList<EducationWish> newEducationWishes = findEducationWishesByInterviewID(interviewID);

        //get rest of date
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_INTERVIEW_BY_ID, interviewID);

        while (rs.next()) {
            int newInterviewID = rs.getInt("InterviewID");
            if (!rs.wasNull()) {
                String newInterviewName = rs.getString("InterviewName");
                Integer newInterviewProdScore = rs.getInt("InterviewProductUnderstanding");
                Integer newInterviewProbScore = rs.getInt("interviewProblemUnderstanding");
                Integer newInterviewFlexScore = rs.getInt("InterviewFlexiblity");
                Integer newInterviewQualityScore = rs.getInt("InterviewQualityAwarenes");
                Integer newInterviewCooperation = rs.getInt("InterviewCooperation");

                returnInterview = new Interview(newInterviewID, newInterviewName, newInterviewProdScore, newInterviewProbScore, newInterviewFlexScore, newInterviewQualityScore, newInterviewCooperation, newFinishedEducations, newEducationWishes);
            }
        }
        clearCache();
        return returnInterview;
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
     */
    public static Education findEducationByID(int educationID) throws SQLException {
        Education returnEducation = null;
        //Fetch information deeper in the hierarchy
        Provider newProvider = findProviderByEducationID(educationID);
        ArrayList<LocalDate> newDates = findDatesByEducationID(educationID);

        //Get rest of needed information and build
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EDUCATION_BY_ID, educationID);

        while (rs.next()) {
            int newEducationID = rs.getInt("EducationAmuNr");
            if (!rs.wasNull()) {
                String newEducationName = rs.getString("EducationName");
                String newEducationDescription = rs.getString("EducationDescription");
                Integer newEducationNoOfDays = rs.getInt("EducationNoOfDays");

                returnEducation = new Education(newEducationID, newEducationName, newEducationDescription, newEducationNoOfDays, newDates, newProvider);
            }
        }
        clearCache();
        return returnEducation;
    }

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
     */
    public static Provider findProviderByID(int providerID) throws SQLException {
        Provider returnProvider = null;

        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_PROVIDER_BY_ID, providerID);

        while (rs.next()) {
            int newProviderID = rs.getInt("ProviderID");
            if (!rs.wasNull()) {
                String newProviderName = rs.getString("ProviderName");

                returnProvider = new Provider(newProviderID, newProviderName);
            }
        }
        clearCache();
        return returnProvider;
    }

    /*
     * Helper methods for finding by ID
     */

    private static ArrayList<Consultation> findConsultationByCompanyID(int companyID) throws SQLException {
        ArrayList<Consultation> returnArrayList = new ArrayList<>();
        ArrayList<Consultation> newConsultations = new ArrayList<>();

        //get the date
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_CONSULTATIONS_BY_COMPANY_ID, companyID);
        while (rs.next()) {
            int newConsultationId = rs.getInt("ConsultationID");
            if (consultationCache.get(newConsultationId) != null) {
                returnArrayList.add(consultationCache.get(newConsultationId));
            } else if (!rs.wasNull()) {
                String newConsultationName = rs.getString("ConsultationName");
                LocalDate newConsultationStartDate = rs.getDate("ConsultationStartDate").toLocalDate();
                LocalDate newConsultationEndDate = rs.getDate("ConsultationEndDate").toLocalDate();

                Consultation tempConsultation = new Consultation(newConsultationId,newConsultationName,newConsultationStartDate,newConsultationEndDate,null);
                newConsultations.add(tempConsultation);
            }
        }
        //add rest of data
        for (Consultation newConsultation : newConsultations) {
            int id = newConsultation.getConsultationID();
            newConsultation.setEmployees(findEmployeesByConsultationID(id));

            newConsultation = consultationCache.putIfAbsent(id, newConsultation);

            returnArrayList.add(newConsultation);
        }
        return returnArrayList;
    }

    private static ArrayList<Employee> findEmployeesByConsultationID(int consultationID) throws SQLException {
        ArrayList<Employee> returnArrayList = new ArrayList<>();
        ArrayList<Employee> newEmployees = new ArrayList<>();

        //get the data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EMPLOYEES_BY_CONSULTATION_ID, consultationID);
        while (rs.next()) {
            int newEmployeeId = rs.getInt("EmployeeID");
            //try to find in cache
            if (employeeCache.get(newEmployeeId) != null) {
                returnArrayList.add(employeeCache.get(newEmployeeId));
            } else if (!rs.wasNull()) {
                String newEmployeeFirstName = rs.getString("EmployeeFirstName");
                String newEmployeeLastName = rs.getString("EmployeeLastName");
                String newEmployeeCpr = rs.getString("EmployeeCprNr");
                String newEmployeeEmail = rs.getString("EmployeeEmail");
                String newEmployeePhoneNr = rs.getString("EmployePhoneNr");

                Employee tempEmployee = new Employee(newEmployeeId, newEmployeeFirstName, newEmployeeLastName, newEmployeeCpr, newEmployeeEmail, newEmployeePhoneNr, null);
                newEmployees.add(tempEmployee);
            }
        }
        //Add rest of data
        for (Employee newEmployee : newEmployees) {
            int id = newEmployee.getEmployeeID();
            newEmployee.setInterviews(findInterviewsByEmployeeID(id));

            newEmployee = employeeCache.putIfAbsent(id, newEmployee);

            returnArrayList.add(newEmployee);
        }
        return returnArrayList;
    }

    private static ArrayList<Interview> findInterviewsByEmployeeID(int employeeID) throws SQLException {
        ArrayList<Interview> returnArrayList = new ArrayList<>();
        ArrayList<Interview> newInterviews = new ArrayList<>();

        //get the data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_INTERVIEWS_BY_EMPLOYEE_ID, employeeID);
        while (rs.next()) {
            int newInterviewId = rs.getInt("InterviewID");
            //Try to find in cache
            if (interviewCache.get(newInterviewId) != null) {
                returnArrayList.add(interviewCache.get(newInterviewId));
            } else if (!rs.wasNull()) { //else we build new
                String newInterviewName = rs.getString("InterviewName");
                Integer newInterviewProdScore = rs.getInt("InterviewProductUnderstanding");
                Integer newInterviewProbScore = rs.getInt("interviewProblemUnderstanding");
                Integer newInterviewFlexScore = rs.getInt("InterviewFlexiblity");
                Integer newInterviewQualityScore = rs.getInt("InterviewQualityAwarenes");
                Integer newInterviewCooperation = rs.getInt("InterviewCooperation");

                Interview tempInterview = new Interview(newInterviewId, newInterviewName, newInterviewProdScore, newInterviewProbScore, newInterviewFlexScore, newInterviewQualityScore, newInterviewCooperation, null, null);
                newInterviews.add(tempInterview);
            }
        }
        //add rest of data to new interviews
        for (Interview newInterview : newInterviews) {
            int id = newInterview.getInterviewID();
            newInterview.setEducationWishes(findEducationWishesByInterviewID(id));
            newInterview.setFinishedEducations(findFinishedEducationsByInterviewID(id));

            newInterview = interviewCache.putIfAbsent(id, newInterview);

            returnArrayList.add(newInterview);
        }
        return returnArrayList;
    }

    private static ArrayList<EducationWish> findEducationWishesByInterviewID(int interviewID) throws SQLException {
        ArrayList<EducationWish> returnArrayList = new ArrayList<>();

        //get the data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EDUCATION_WISHES_BY_INTERVIEW_ID, interviewID);
        while (rs.next()) {
            int newEducationWishId = rs.getInt("EducationWishID");
            if (!rs.wasNull()) {
                Integer newEducationWishPriority = rs.getInt("EducationWishPriority");

                EducationWish tempWish = new EducationWish(newEducationWishId, null, newEducationWishPriority);
                returnArrayList.add(tempWish);
            }
        }
        //Attach the education to found wishes
        for (EducationWish educationWish : returnArrayList) {
            int id = educationWish.getEducationWishID();
            educationWish.setEducation(findEducationByEducationWishID(id));
        }
        return returnArrayList;
    }

    private static ArrayList<FinishedEducation> findFinishedEducationsByInterviewID(int interviewID) throws SQLException {
        ArrayList<FinishedEducation> returnArrayList = new ArrayList<>();

        //Get the data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_FINISHED_EDUCATIONS_BY_INTERVIEW_ID, interviewID);
        while (rs.next()) {
            int newFinishedEducationId = rs.getInt("FinishedEducationID");
            if (!rs.wasNull()) {
                LocalDate finishedDate = rs.getDate("FinishedEducationDate").toLocalDate();

                FinishedEducation tempFinishedEducation = new FinishedEducation(newFinishedEducationId, null, finishedDate);
                returnArrayList.add(tempFinishedEducation);
            }
        }
        //Attach teh education to found FinishedEducations
        for (FinishedEducation finishedEducation : returnArrayList) {
            int id = finishedEducation.getFinishedEducationID();
            finishedEducation.setEducation(findEducationByFinishedEducationID(id));
        }
        return returnArrayList;
    }

    private static Education findEducationByFinishedEducationID(int finishedEducationID) throws SQLException {
        Education returnEducation = null;

        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EDUCATIONS_BY_FINISHED_EDUCATION_ID, finishedEducationID);
        while (rs.next()) {
            int newEducationId = rs.getInt("EducationAmuNr");
            //Return if cached already
            if (educationCache.get(newEducationId) != null) {
                return educationCache.get(newEducationId);
            }
            if (!rs.wasNull()) {
                String newEducationName = rs.getString("EducationName");
                String newEducationDescription = rs.getString("EducationDescription");
                Integer newEducationNoOfDays = rs.getInt("EducationNoOfDays");

                returnEducation = new Education(newEducationId, newEducationName, newEducationDescription, newEducationNoOfDays, null, null);
            }
        }
        if (returnEducation != null) {
            returnEducation.setProvider(findProviderByEducationID(returnEducation.getAmuNr()));
            returnEducation.setDates(findDatesByEducationID(returnEducation.getAmuNr()));
            returnEducation = educationCache.putIfAbsent(returnEducation.getAmuNr(), returnEducation);
        }

        return returnEducation;
    }

    private static Education findEducationByEducationWishID(int eudcationwishid) throws SQLException {
        Education returnEducation = null;

        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_EDUCATIONS_BY_EDUCATION_WISH_ID, eudcationwishid);
        while (rs.next()) {
            int newEducationId = rs.getInt("EducationAmuNr");
            //Return if cached already
            if (educationCache.get(newEducationId) != null) {
                return educationCache.get(newEducationId);
            }
            if (!rs.wasNull()) {
                String newEducationName = rs.getString("EducationName");
                String newEducationDescription = rs.getString("EducationDescription");
                Integer newEducationNoOfDays = rs.getInt("EducationNoOfDays");

                returnEducation = new Education(newEducationId, newEducationName, newEducationDescription, newEducationNoOfDays, null, null);
            }
        }
        if (returnEducation != null) {
            returnEducation.setProvider(findProviderByEducationID(returnEducation.getAmuNr()));
            returnEducation.setDates(findDatesByEducationID(returnEducation.getAmuNr()));
            returnEducation = educationCache.putIfAbsent(returnEducation.getAmuNr(), returnEducation);
        }

        return returnEducation;
    }

    private static Provider findProviderByEducationID(int educationID) throws SQLException {
        Provider returnProvider = null;

        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_PROVIDER_BY_EDUCATION_ID, educationID);
        while (rs.next()) {
            int newProviderID = rs.getInt("ProviderID");
            //Immediately return if provider is cached already
            if (providerCache.get(newProviderID) != null) {
                return providerCache.get(newProviderID);
            }
            if (!rs.wasNull()) {
                String newProviderName = rs.getString("ProviderName");
                Provider newProvider = new Provider(newProviderID, newProviderName);

                // if already in Cache
                returnProvider = providerCache.putIfAbsent(newProvider.getProviderID(), newProvider);
            }
        }
        return returnProvider;
    }

    private static ArrayList<LocalDate> findDatesByEducationID(int educationID) throws SQLException {
        ArrayList<LocalDate> returnArrayList = new ArrayList<>();

        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_DATES_BY_EDUCATION_ID, educationID);
        while (rs.next()) {
            LocalDate date = rs.getDate("Date").toLocalDate();
            returnArrayList.add(date);
        }
        return returnArrayList;
    }


    private static void clearCache() {
        consultationCache.clear();
        employeeCache.clear();
        interviewCache.clear();
        educationCache.clear();
        providerCache.clear();
    }

    /*
     * Find Display objects
     */

    /**
     * Finds all {@link DisplayCompany DisplayCompanies} from the DataBase.
     * DisplayCompanies can be used to display data for the user. In addition display objects are more lightweight and should be
     * the preferred database interaction if lots of objects are need to be displayed.
     * Note that Display Objects are are only keeping the minimum required information of the corresponding Domain object.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link DisplayCompany} Objects.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static ArrayList<DisplayCompany> findDisplayCompanies(SearchContainer container) throws SQLException {
        if (container.isOnlyFilteringCompanies()) {
            return findAllDisplayCompanies(container);
        } else {
            return findFilteredDisplayCompanies(container);
        }
    }

    /**
     * Finds all {@link DisplayConsultation DisplayConsultations} from the DataBase.
     * DisplayCompanies can be used to display data for the user. In addition display objects are more lightweight and should be
     * the preferred database interaction if lots of objects are need to be displayed.
     * Note that Display Objects are are only keeping the minimum required information of the corresponding Domain object.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link DisplayConsultation} Objects.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static ArrayList<DisplayConsultation> findDisplayConsultations(SearchContainer container) throws SQLException {
        if (container.isOnlyFilteringConsultations()) {
            return findAllDisplayConsultations(container);
        } else {
            return findFilteredDisplayConsultations(container);
        }
    }

    /**
     * Finds all {@link DisplayEmployee DisplayEmployees} from the DataBase.
     * DisplayCompanies can be used to display data for the user. In addition display objects are more lightweight and should be
     * the preferred database interaction if lots of objects are need to be displayed.
     * Note that Display Objects are are only keeping the minimum required information of the corresponding Domain object.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link DisplayEmployee} Objects.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static ArrayList<DisplayEmployee> findDisplayEmployees(SearchContainer container) throws SQLException {
        if (container.isOnlyFilteringEmployees()) {
            return findAllDisplayEmployees(container);
        } else {
            return findFilteredDisplayEmployees(container);
        }
    }

    /**
     * Finds all {@link DisplayInterview DisplayInterviews} from the DataBase.
     * DisplayCompanies can be used to display data for the user. In addition display objects are more lightweight and should be
     * the preferred database interaction if lots of objects are need to be displayed.
     * Note that Display Objects are are only keeping the minimum required information of the corresponding Domain object.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link DisplayInterview} Objects.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static ArrayList<DisplayInterview> findDisplayInterviews(SearchContainer container) throws SQLException {
        if (container.isOnlyFilteringInterviews()) {
            return findAllDisplayInterviews(container);
        } else {
            return findFilteredDisplayInterviews(container);
        }
    }

    /**
     * Finds all {@link DisplayEducation DisplayEducations} from the DataBase.
     * DisplayCompanies can be used to display data for the user. In addition display objects are more lightweight and should be
     * the preferred database interaction if lots of objects are need to be displayed.
     * Note that Display Objects are are only keeping the minimum required information of the corresponding Domain object.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link DisplayEducation} Objects.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static ArrayList<DisplayEducation> findDisplayEducations(SearchContainer container) throws SQLException {
        if (container.isOnlyFilteringEducations()) {
            return findAllDisplayEducations(container);
        } else {
            return findFilteredDisplayEducations(container);
        }
    }

    /**
     * Finds all {@link DisplayProvider DisplayProviders} from the DataBase.
     * DisplayCompanies can be used to display data for the user. In addition display objects are more lightweight and should be
     * the preferred database interaction if lots of objects are need to be displayed.
     * Note that Display Objects are are only keeping the minimum required information of the corresponding Domain object.
     *
     * <br><br>
     * <font color=red>Note</font> that the caller has to manage {@link DB#connect() connection} and {@link DB#disconnect() disconnect}.
     *
     * @return ArrayList of {@link DisplayProvider} Objects.
     * @throws SQLException Exception thrown when encountered a fatal error.
     * @see DB
     */
    public static ArrayList<DisplayProvider> findDisplayProvider(SearchContainer container) throws SQLException {
        if (container.isOnlyFilteringProviders()) {
            return findAllDisplayProviders(container);
        } else {
            return findFilteredDisplayProviders(container);
        }
    }

    /*
     * Find display when only filtering that table. Those method will not throw out unrelated items by inner joining.
     */

    private static ArrayList<DisplayCompany> findAllDisplayCompanies(SearchContainer container) throws SQLException {
        //Init return value
        HashSet<DisplayCompany> returnSet = new HashSet<>();

        //Unpack needed values
        Integer companyID = container.getCompanyID();
        String companyCvr = container.getCvrNr();
        String companyName = container.getCompanyName();

        //Get ResultSet
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_ALL_DISPLAY_COMPANIES, companyID, companyCvr, companyName);

        //Build objects
        while (rs.next()) {
            Integer displayCompanyId = rs.getInt("CompanyID");
            if (!rs.wasNull()) {
                String displayCvrNr = rs.getString("CompanyCvrNr");
                String displayCompanyName = rs.getString("CompanyName");

                //build object
                DisplayCompany tempDisplayCompany = new DisplayCompany(displayCompanyId, displayCvrNr, displayCompanyName);
                returnSet.add(tempDisplayCompany);
            }
        }

        //Return list
        return new ArrayList<>(returnSet);
    }

    private static ArrayList<DisplayConsultation> findAllDisplayConsultations(SearchContainer container) throws SQLException {
        //Init return value
        HashSet<DisplayConsultation> returnSet = new HashSet<>();

        //Unpack needed values
        Integer consultationID = container.getConsultationID();
        String consultationName = container.getConsultationName();
        LocalDate consultationMinDate = container.getConsultationMinDate();
        LocalDate consultationMaxDate = container.getConsultationMaxDate();

        //Get ResultSet
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_ALL_DISPLAY_CONSULTATIONS, consultationID, consultationName, consultationMinDate, consultationMaxDate);

        //Build objects
        while (rs.next()) {
            Integer displayConsultationId = rs.getInt("ConsultationID");
            if (!rs.wasNull()) {
                String displayConsultationName = rs.getString("ConsultationName");
                LocalDate displayConsultationStartDate = rs.getDate("ConsultationStartDate").toLocalDate();
                LocalDate displayConsultationEndDate = rs.getDate("ConsultationEndDate").toLocalDate();

                //build object
                DisplayConsultation tempDisplayConsultation = new DisplayConsultation(displayConsultationId, displayConsultationName, displayConsultationStartDate, displayConsultationEndDate);
                returnSet.add(tempDisplayConsultation);
            }
        }

        //Return list
        return new ArrayList<>(returnSet);
    }

    private static ArrayList<DisplayEmployee> findAllDisplayEmployees(SearchContainer container) throws SQLException {
        //Init return value
        HashSet<DisplayEmployee> returnSet = new HashSet<>();

        //Unpack needed values
        Integer employeeID = container.getEmployeeID();
        String employeeFirstName = container.getEmployeeFirstName();
        String employeeLastName = container.getEmployeeLastName();
        String employeeCprNr = container.getCprNr();
        String employeeEMail = container.getEmail();
        String employeePhone = container.getPhoneNr();

        //Get ResultSet

        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_ALL_DISPLAY_EMPLOYEES, employeeID, employeeFirstName, employeeLastName, employeeCprNr, employeeEMail, employeePhone);

        //Build objects
        while (rs.next()) {
            Integer displayEmployeeId = rs.getInt("EmployeeID");
            if (!rs.wasNull()) {
                String displayEmployeeFirstName = rs.getString("EmployeeFirstName");
                String displayEmployeeLastName = rs.getString("EmployeeLastName");
                String displayEmployeeCprNr = rs.getString("EmployeeCprNr");
                String displayEmployeeEmail = rs.getString("EmployeeEmail");
                String displayEmployeePhoneNr = rs.getString("EmployeePhoneNr");

                //Build Object
                DisplayEmployee tempDisplayEmployee = new DisplayEmployee(displayEmployeeId, displayEmployeeFirstName, displayEmployeeLastName, displayEmployeeCprNr, displayEmployeeEmail, displayEmployeePhoneNr);
                returnSet.add(tempDisplayEmployee);
            }
        }
        //Return list
        return new ArrayList<>(returnSet);
    }

    private static ArrayList<DisplayInterview> findAllDisplayInterviews(SearchContainer container) throws SQLException {
        //Init return value
        HashSet<DisplayInterview> returnSet = new HashSet<>();

        //Unpack needed values
        Integer interviewId = container.getInterviewID();
        String interviewName = container.getInterviewName();

        //Get ResultSet
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_ALL_DISPLAY_INTERVIEWS, interviewId, interviewName);

        //Build object
        while (rs.next()) {
            int displayInterviewId = rs.getInt("InterviewID");
            if (!rs.wasNull()) {
                String displayInterviewName = rs.getString("InterviewName");
                Integer displayInterviewProdScore = rs.getInt("InterviewProductUnderstanding"); //Can not be null anyway
                Integer displayInterviewProbScore = rs.getInt("interviewProblemUnderstanding");
                Integer displayInterviewFlexScore = rs.getInt("InterviewFlexiblity");
                Integer displayInterviewQualityScore = rs.getInt("InterviewQualityAwarenes");
                Integer displayInterviewCooperationScore = rs.getInt("InterviewCooperation");

                //Build object
                DisplayInterview tempDisplayInterview = new DisplayInterview(displayInterviewId, displayInterviewName, displayInterviewProdScore, displayInterviewProbScore, displayInterviewFlexScore, displayInterviewQualityScore, displayInterviewCooperationScore);
                returnSet.add(tempDisplayInterview);
            }
        }
        //Return list
        return new ArrayList<>(returnSet);
    }

    private static ArrayList<DisplayEducation> findAllDisplayEducations(SearchContainer container) throws SQLException {
        //Init return value
        HashSet<DisplayEducation> returnSet = new HashSet<>();

        //Unpack needed values
        Integer educationAmuNr = container.getAmuNr();
        String educationName = container.getEducationName();
        Integer educationNoOfDays = container.getEducationNoOfDays();

        //Get ResultSet
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_ALL_DISPLAY_EDUCATIONS, educationAmuNr, educationName, educationNoOfDays);

        //Build objects
        while (rs.next()) {
            int displayEducationAmuNr = rs.getInt("EducationAmuNr");
            if (!rs.wasNull()) {
                String displayEducationName = rs.getString("EducationName");
                String displayEducationDescription = rs.getString("EducationDescription");
                Integer displayEducationNoOfDays = rs.getInt("EducationNoOfDays");
                String displayEducationProviderName = rs.getString("ProviderName");

                DisplayEducation tempDisplayEducation = new DisplayEducation(displayEducationAmuNr, displayEducationName, displayEducationDescription, displayEducationNoOfDays, displayEducationProviderName);
                returnSet.add(tempDisplayEducation);
            }
        }
        //Return list
        return new ArrayList<>(returnSet);
    }

    private static ArrayList<DisplayProvider> findAllDisplayProviders(SearchContainer container) throws SQLException {
        //Init return value
        HashSet<DisplayProvider> returnSet = new HashSet<>();

        //Unpack needed values
        Integer providerId = container.getProviderID();
        String providerName = container.getProviderName();

        //Get ResultSet
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_ALL_DISPLAY_PROVIDERS, providerId, providerName);

        //Build objects
        while (rs.next()) {
            int displayProviderId = rs.getInt("ProviderID");
            if (!rs.wasNull()) {
                String displayProviderName = rs.getString("ProviderName");

                DisplayProvider tempDisplayProvider = new DisplayProvider(displayProviderId, displayProviderName);
                returnSet.add(tempDisplayProvider);
            }
        }
        //Return list
        return new ArrayList<>(returnSet);
    }

    /*
     * Find Display item by filtering all table together
     */

    private static ArrayList<DisplayCompany> findFilteredDisplayCompanies(SearchContainer container) throws SQLException {
        //Init return collection
        HashSet<DisplayCompany> returnSet = new HashSet<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_DISPLAY_COMPANIES,
                container.getCompanyID(),
                container.getCvrNr(),
                container.getCompanyName(),
                container.getConsultationID(),
                container.getConsultationName(),
                container.getConsultationMinDate(),
                container.getConsultationMaxDate(),
                container.getEmployeeID(),
                container.getEmployeeFirstName(),
                container.getEmployeeLastName(),
                container.getCprNr(),
                container.getEmail(),
                container.getPhoneNr(),
                container.getInterviewID(),
                container.getInterviewName(),
                container.getAmuNr(),
                container.getEducationName(),
                container.getEducationNoOfDays(),
                container.getEducationMinDate(),
                container.getEducationMaxDate(),
                container.getProviderID(),
                container.getProviderName());

        //build object
        while (rs.next()) {
            int displayCompanyId = rs.getInt("CompanyID");
            if (!rs.wasNull()) {
                String displayCompanyCvr = rs.getString("CompanyCvrNr");
                String displayCompanyName = rs.getString("CompanyName");

                DisplayCompany tempDisplayCompany = new DisplayCompany(displayCompanyId, displayCompanyCvr, displayCompanyName);
                returnSet.add(tempDisplayCompany);
            }
        }
        //Return resultset
        return new ArrayList<>(returnSet);
    }

    private static ArrayList<DisplayConsultation> findFilteredDisplayConsultations(SearchContainer container) throws SQLException {
        //Init return collection
        HashSet<DisplayConsultation> returnSet = new HashSet<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_DISPLAY_CONSULTATIONS,
                container.getCompanyID(),
                container.getCvrNr(),
                container.getCompanyName(),
                container.getConsultationID(),
                container.getConsultationName(),
                container.getConsultationMinDate(),
                container.getConsultationMaxDate(),
                container.getEmployeeID(),
                container.getEmployeeFirstName(),
                container.getEmployeeLastName(),
                container.getCprNr(),
                container.getEmail(),
                container.getPhoneNr(),
                container.getInterviewID(),
                container.getInterviewName(),
                container.getAmuNr(),
                container.getEducationName(),
                container.getEducationNoOfDays(),
                container.getEducationMinDate(),
                container.getEducationMaxDate(),
                container.getProviderID(),
                container.getProviderName());

        //build object
        while (rs.next()) {
            int displayConsultationId = rs.getInt("ConsultationID");
            if (!rs.wasNull()) {
                String displayConsultationName = rs.getString("ConsultationName");
                LocalDate displayConsultationStartDate = rs.getDate("ConsultationStartDate").toLocalDate();
                LocalDate displayConsultationEndDate = rs.getDate("ConsultationEndDate").toLocalDate();

                DisplayConsultation tempDisplayConsultation = new DisplayConsultation(displayConsultationId, displayConsultationName, displayConsultationStartDate, displayConsultationEndDate);
                returnSet.add(tempDisplayConsultation);
            }
        }
        //Return resultset
        return new ArrayList<>(returnSet);
    }

    private static ArrayList<DisplayEmployee> findFilteredDisplayEmployees(SearchContainer container) throws SQLException {
        //Init return collection
        HashSet<DisplayEmployee> returnSet = new HashSet<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_DISPLAY_EMPLOYEES,
                container.getCompanyID(),
                container.getCvrNr(),
                container.getCompanyName(),
                container.getConsultationID(),
                container.getConsultationName(),
                container.getConsultationMinDate(),
                container.getConsultationMaxDate(),
                container.getEmployeeID(),
                container.getEmployeeFirstName(),
                container.getEmployeeLastName(),
                container.getCprNr(),
                container.getEmail(),
                container.getPhoneNr(),
                container.getInterviewID(),
                container.getInterviewName(),
                container.getAmuNr(),
                container.getEducationName(),
                container.getEducationNoOfDays(),
                container.getEducationMinDate(),
                container.getEducationMaxDate(),
                container.getProviderID(),
                container.getProviderName());

        //build object
        while (rs.next()) {
            int displayEmployeeId = rs.getInt("EmployeeID");
            if (!rs.wasNull()) {
                String displayEmployeeFirstName = rs.getString("EmployeeFirstName");
                String displayEmployeeLastName = rs.getString("EmployeeLastName");
                String displayEmployeeCprNr = rs.getString("EmployeeCprNr");
                String displayEmployeeEmail = rs.getString("EmployeeEmail");
                String displayEmployeePhoneNr = rs.getString("EmployeePhoneNr");

                DisplayEmployee tempDisplayEmployee = new DisplayEmployee(displayEmployeeId, displayEmployeeFirstName, displayEmployeeLastName, displayEmployeeCprNr, displayEmployeeEmail, displayEmployeePhoneNr);
                returnSet.add(tempDisplayEmployee);
            }
        }
        //Return resultset
        return new ArrayList<>(returnSet);
    }

    private static ArrayList<DisplayInterview> findFilteredDisplayInterviews(SearchContainer container) throws SQLException {
        //Init return collection
        HashSet<DisplayInterview> returnSet = new HashSet<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_DISPLAY_INTERVIEWS,
                container.getCompanyID(),
                container.getCvrNr(),
                container.getCompanyName(),
                container.getConsultationID(),
                container.getConsultationName(),
                container.getConsultationMinDate(),
                container.getConsultationMaxDate(),
                container.getEmployeeID(),
                container.getEmployeeFirstName(),
                container.getEmployeeLastName(),
                container.getCprNr(),
                container.getEmail(),
                container.getPhoneNr(),
                container.getInterviewID(),
                container.getInterviewName(),
                container.getAmuNr(),
                container.getEducationName(),
                container.getEducationNoOfDays(),
                container.getEducationMinDate(),
                container.getEducationMaxDate(),
                container.getProviderID(),
                container.getProviderName());

        //build object
        while (rs.next()) {
            int displayInterviewId = rs.getInt("InterviewID");
            if (!rs.wasNull()) {
                String displayinterviewName = rs.getString("InterviewName");
                Integer displayInterviewProdScore = rs.getInt("InterviewProductUnderstanding");
                Integer displayInterviewProbScore = rs.getInt("interviewProblemUnderstanding");
                Integer displayInterviewFlexScore = rs.getInt("InterviewFlexiblity");
                Integer displayInterviewQualityScore = rs.getInt("InterviewQualityAwarenes");
                Integer displayInterviewCooperationScore = rs.getInt("InterviewCooperation");

                DisplayInterview tempDisplayInterview = new DisplayInterview(displayInterviewId, displayinterviewName, displayInterviewProdScore, displayInterviewProbScore, displayInterviewFlexScore, displayInterviewQualityScore, displayInterviewCooperationScore);
                returnSet.add(tempDisplayInterview);
            }
        }
        //Return resultset
        return new ArrayList<>(returnSet);
    }

    private static ArrayList<DisplayEducation> findFilteredDisplayEducations(SearchContainer container) throws SQLException {
        //Init return collection
        HashSet<DisplayEducation> returnSet = new HashSet<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_DISPLAY_EDUCATIONS,
                container.getCompanyID(),
                container.getCvrNr(),
                container.getCompanyName(),
                container.getConsultationID(),
                container.getConsultationName(),
                container.getConsultationMinDate(),
                container.getConsultationMaxDate(),
                container.getEmployeeID(),
                container.getEmployeeFirstName(),
                container.getEmployeeLastName(),
                container.getCprNr(),
                container.getEmail(),
                container.getPhoneNr(),
                container.getInterviewID(),
                container.getInterviewName(),
                container.getAmuNr(),
                container.getEducationName(),
                container.getEducationNoOfDays(),
                container.getEducationMinDate(),
                container.getEducationMaxDate(),
                container.getProviderID(),
                container.getProviderName());

        //build object
        while (rs.next()) {
            //First set
            int displayEducationId1 = rs.getInt("EducationAmuNr1");
            if (!rs.wasNull()) {
                String displayEducationName1 = rs.getString("EducationName1");
                String displayEducationDescription1 = rs.getString("EducationDescription1");
                Integer displayEducationNoOfDays1 = rs.getInt("EducationNoOfDays1");
                String displayEducationProviderName1 = rs.getString("ProviderName1");

                DisplayEducation tempDisplayEducation = new DisplayEducation(displayEducationId1, displayEducationName1, displayEducationDescription1, displayEducationNoOfDays1, displayEducationProviderName1);
                returnSet.add(tempDisplayEducation);
            }

            //second set
            int displayEducationId2 = rs.getInt("EducationAmuNr2");
            if (!rs.wasNull()) {
                String displayEducationName2 = rs.getString("EducationName2");
                String displayEducationDescription2 = rs.getString("EducationDescription2");
                Integer displayEducationNoOfDays2 = rs.getInt("EducationNoOfDays2");
                String displayEducationProviderName2 = rs.getString("ProviderName2");

                DisplayEducation tempDisplayEducation = new DisplayEducation(displayEducationId2, displayEducationName2, displayEducationDescription2, displayEducationNoOfDays2, displayEducationProviderName2);
                returnSet.add(tempDisplayEducation);
            }
        }

        //Return resultset
        return new ArrayList<>(returnSet);
    }

    private static ArrayList<DisplayProvider> findFilteredDisplayProviders(SearchContainer container) throws SQLException {
        //Init return collection
        HashSet<DisplayProvider> returnSet = new HashSet<>();

        //Getting data
        ResultSet rs = DB.getInstance().executeStoredProcedure(SpWithRs.FIND_DISPLAY_PROVIDERS,
                container.getCompanyID(),
                container.getCvrNr(),
                container.getCompanyName(),
                container.getConsultationID(),
                container.getConsultationName(),
                container.getConsultationMinDate(),
                container.getConsultationMaxDate(),
                container.getEmployeeID(),
                container.getEmployeeFirstName(),
                container.getEmployeeLastName(),
                container.getCprNr(),
                container.getEmail(),
                container.getPhoneNr(),
                container.getInterviewID(),
                container.getInterviewName(),
                container.getAmuNr(),
                container.getEducationName(),
                container.getEducationNoOfDays(),
                container.getEducationMinDate(),
                container.getEducationMaxDate(),
                container.getProviderID(),
                container.getProviderName());

        //build object
        while (rs.next()) {
            //First set
            int displayProviderId1 = rs.getInt("ProviderID1");
            if (!rs.wasNull()) {
                String displayProviderName1 = rs.getString("ProviderName1");

                DisplayProvider tempDisplayProvider = new DisplayProvider(displayProviderId1, displayProviderName1);
                returnSet.add(tempDisplayProvider);
            }

            //second set
            int displayProviderId2 = rs.getInt("ProviderID2");
            if (!rs.wasNull()) {
                String displayProviderName2 = rs.getString("ProviderName2");

                DisplayProvider tempDisplayProvider = new DisplayProvider(displayProviderId2, displayProviderName2);
                returnSet.add(tempDisplayProvider);
            }
        }

        //Return resultset
        return new ArrayList<>(returnSet);
    }

    /**
     * Method to establish a connection to the Database.
     * Note that this must be called before running any other methods from this class.
     * After finished Database interaction {@link #disconnect()} must be called.
     *
     * @throws SQLException Exception when SQL encounter a fatal problem
     * @see DB
     */
    public static void connect() throws SQLException {
        DB.getInstance().connect();
    }

    /**
     * Method to close connection to the Database.
     *
     * @throws SQLException Exception when SQL encounter a fatal problem
     * @see DB
     */
    public static void disconnect() throws SQLException {
        DB.getInstance().disconnect();
    }


}
