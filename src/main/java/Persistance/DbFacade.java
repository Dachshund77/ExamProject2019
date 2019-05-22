package Persistance;

import Domain.Education;
import Domain.Provider;
import Foundation.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
     * Method that will write the a provider object to the database.
     * @param provider The Container for the values that will be inserted.
     * @return True, if inserted correctly
     * @throws SQLException Exception thrown when encountered a fatal error.
     */
    public static boolean insertProvider(Provider provider) throws SQLException {
        //Get values for insertion
        Integer providerID = provider.getProviderID();
        String providerName = provider.getProviderName();
        //Write to Db
        return DB.getInstance().executeStoredProcedureNoRS("sp_InsertProvider",providerID,providerName);
    }

    private static boolean insertEducation(Education education) throws SQLException{
        return true; /// FIXME: 22/05/2019 Need implementation
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
}
