package Persistance;

import Domain.*;
import Foundation.DB;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Facade class that helps convert SQL data to objects.
 * Note that this class will not establish a Db connection on its own.
 */
public class DbFacade {

   /*
    * INSERTION
    */

    /**
     * Method that will write the a provider object to the database.
     * @param provider The Container for the values that will be inserted.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    public static void insertProviderToBatch(Provider provider) throws SQLException {
        //Get values for insertion
        Integer providerID = provider.getProviderID();
        String providerName = provider.getProviderName();
        //Write to Db
        DB.getInstance().addStoredProcedureToBatch("sp_InsertProvider",providerID,providerName);
    }

    /**
     * Method that will write the a provider object to the database.
     * Will also make sure that its children objects are written correctly to the database.
     * @param education The Container for the values that will be inserted.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    private static void insertEducation(Education education) throws SQLException{ //TODO we should reduce round trips to the Database
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

        database.addStoredProcedureToBatch("sp_InsertEducation",amuNr,providerID, educationName,description,noOfDays);

        //Then we write dates to database
        // Delete by educationID first
        database.addStoredProcedureToBatch("sp_DeleteDateByAmuNr",amuNr);

        // Then reInsert
        ArrayList<Date> dates = education.getDates();
        for (Date date : dates) {
            database.addStoredProcedureToBatch("sp_InsertDate",null,amuNr,date);
        }
    }

    /**
     * Method that will write the employee object to the database.
     * @param employee The container of values, that will be inserted.
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    public static boolean insertEmployee(Employee employee) throws SQLException //TODO need javaDOC and maybe fix
    {
        Integer employeeID = employee.getEmployeeId();
        String employeeFirstName = employee.getEmployeeFirstName();
        String employeeLastName = employee.getEmployeeLastName();
        String CPRnr = employee.getCprNr();
        String eMail = employee.getMail();
        String phoneNr = employee.getPhoneNr();

        return DB.getInstance().executeStoredProcedureNoRS("sp_InsertEmployee",employeeFirstName,employeeLastName,CPRnr,eMail,phoneNr);
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
        ResultSet rs = DB.getInstance().executeStoredProcedure("sp_FindProvider",providerID, ProviderName);

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
        ResultSet rs = DB.getInstance().executeStoredProcedure("sp_FindProvider",null, null);

        while (rs.next()){
            Provider tempProvider = new Provider(rs);
            returnMap.put(tempProvider.getProviderID(),tempProvider);
        }
        return returnMap;
    }

    public static HashSet<Company> findAllCompanies() throws SQLException{

        HashSet<Company> returnCompNames = new HashSet<>();

        ResultSet rs = DB.getInstance().executeStoredProcedure("sp_FindCompanyNames");

        while (rs.next()){
            Company tempCompNames = new Company(rs);
            returnCompNames.add(tempCompNames);
        }
        return returnCompNames;


    }

}
