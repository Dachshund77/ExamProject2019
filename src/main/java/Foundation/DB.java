package Foundation;

import Persistance.DbFacade;
import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.sql.Date;

/**
 * Access point for Database interaction. This class wil be facaded by {@link DbFacade} to get usable objects from the resultSet.
 */
public class DB {
    private static DB ourInstance = new DB();

    public static DB getInstance() {
        return ourInstance;
    }

    private String port;
    private String databaseName;
    private String userName;
    private String password;

    private Connection conn;
    private CallableStatement cstmt;

    private ResultSet rs;


    private DB() {
        Properties props = new Properties();

        URL resourcesURL = getClass().getResource("/Database/db.Properties");
        try {
            props.load(new FileReader(new File(resourcesURL.getFile())));
            port = props.getProperty("port", "1433");
            databaseName = props.getProperty("databaseName");
            userName = props.getProperty("userName", "sa");
            password = props.getProperty("password");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Step 2
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Method that will execute a stored procedure and return the resultSet reference.
     * Note that it is the callers responsibility to provide the correct amount of parameters.
     *
     * @param sp    String with the name of the Stored Procedure
     * @param param Variable parameter for the Procedure
     * @return ResultSet of SP.
     * @throws SQLException Exception when SQL encounter a fatal problem
     * @deprecated Since 23/05/19. Replace with {@link #executeStoredProcedure(SpWithRs, Object...)}
     */
    @Deprecated
    @SuppressWarnings("Duplicates")
    public ResultSet executeStoredProcedure(String sp, Object... param) throws SQLException {

        // Preparing metaData
        Map<Integer, String> metaDataMap = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataMap.size())); //Build the string
        //Build parameters
        for (int i = 0; i < metaDataMap.size(); i++) {
            setCallParameter(i + 1, metaDataMap.get(i + 1), param[i]);
        }
        //Getting results
        rs = cstmt.executeQuery();

        return rs;
    }

    /**
     * Method that will execute a stored procedure and return the resultSet reference.
     * Note that it is the callers responsibility to provide the correct amount of parameters.
     *
     * @param sp    Enum Type {@link SpWithRs}.
     * @param param Variable parameter for the Procedure
     * @return ResultSet of SP.
     * @throws SQLException Exception when SQL encounter a fatal problem
     */

    @SuppressWarnings("Duplicates")
    public ResultSet executeStoredProcedure(SpWithRs sp, Object... param) throws SQLException {
        System.out.println("DB.executeStoredProcedure");
        System.out.println("sp = [" + sp + "], param = [" + Arrays.toString(param) + "] ");
        System.out.println("param length was "+param.length);

        // Preparing metaData
        Map<Integer, String> metaDataMap = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataMap.size())); //Build the string
        //Build parameters
        for (int i = 0; i < metaDataMap.size(); i++) {
            setCallParameter(i + 1, metaDataMap.get(i + 1), param[i]);
        }
        //Getting results
        rs = cstmt.executeQuery();

        return rs;
    }

    @SuppressWarnings("Duplicates")
    public int executeStoredProcedureGetID(SpGetKey sp, Object... param) throws SQLException {
        System.out.println("DB.executeStoredProcedureGetID");
        System.out.println("sp = [" + sp + "], param = [" + Arrays.toString(param) + "]");
        System.out.println("param length was "+param.length);
        // Preparing metaData
        Map<Integer, String> metaDataMap = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataMap.size())); //Build the string
        //Build parameters
        for (int i = 0; i < metaDataMap.size(); i++) {
            setCallParameter(i + 1, metaDataMap.get(i + 1), param[i]);
        }
        //Getting results
        cstmt.registerOutParameter(1,Types.INTEGER);
        cstmt.execute();
        return cstmt.getInt(1);

    }

    /**
     * Add a stored procedure to a batch. To execute the batch call {@link #executeBatch()}.
     *
     * @param sp    String with the name of the Stored Procedure
     * @param param Variable parameter for the Procedure
     * @throws SQLException Exception when SQL encounter a fatal problem
     * @deprecated Since 23/05/19. Replaced with {@link #addStoredProcedureToBatch(Sp, Object...)}.
     */
    @Deprecated
    @SuppressWarnings("Duplicates")
    public void addStoredProcedureToBatch(String sp, Object... param) throws SQLException {
        conn.setAutoCommit(false); //TODO can we rollback if exception
        // Preparing metaData
        Map<Integer, String> metaDataMap = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataMap.size())); //Build the string
        //Build parameters
        for (int i = 0; i < metaDataMap.size(); i++) {
            setCallParameter(i + 1, metaDataMap.get(i + 1), param[i]);
        }
        cstmt.addBatch();
    }

    /**
     * Add a stored procedure to a batch. To execute the batch call {@link #executeBatch()}.
     *
     * @param sp    Enum Type {@link Sp}.
     * @param param Variable parameter for the Procedure
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    @SuppressWarnings("Duplicates")
    public void addStoredProcedureToBatch(Sp sp, Object... param) throws SQLException {
        System.out.println("DB.addStoredProcedureToBatch");
        System.out.println("sp = [" + sp + "], param = [" + Arrays.toString(param) + "]");
        System.out.println("param length was "+param.length);

        conn.setAutoCommit(false); //TODO can we rollback if exception
        // Preparing metaData
        Map<Integer, String> metaDataMap = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataMap.size())); //Build the string

        //Build parameters
        for (int i = 0; i < metaDataMap.size(); i++) {
            setCallParameter(i + 1, metaDataMap.get(i + 1), param[i]);
        }
        cstmt.addBatch();
    }

    /**
     * Execute the batch. To add to the Batch call {@link #addStoredProcedureToBatch(Sp, Object...)}.
     * Note that the batch files will be execute as first in fir out order.
     *
     * @return True if successful added to database
     * @throws SQLException Exception when SQL encounter a fatal problem. Will Rollback this transaction.
     */
    public boolean executeBatch() throws SQLException {
        boolean returnBoolean = cstmt.execute();
        try {
            conn.commit();
        }catch (SQLException e){
            conn.rollback(); //Rollback
            throw e; //Propagate exception upwards.
        }
        return returnBoolean;
    }

    /**
     * Method that will execute a stored procedure and return the no resultSet
     * Note that it is the callers responsibility to provide the correct amount of parameters.
     *
     * @param sp    String with the name of the Stored Procedure
     * @param param Variable parameter for the Procedure
     * @return True of successful executed.
     * @throws SQLException Exception when SQL encounter a fatal problem
     * @deprecated Since 23/05/19. Replaced with {@link #executeStoredProcedureNoRS(Sp, Object...)}
     */

    @Deprecated
    @SuppressWarnings("Duplicates")
    public Boolean executeStoredProcedureNoRS(String sp, Object... param) throws SQLException {
        // Preparing metaData
        Map<Integer, String> metaDataMap = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataMap.size()));
        //Build parameters
        for (int i = 0; i < metaDataMap.size(); i++) {
            setCallParameter(i + 1, metaDataMap.get(i + 1), param[i]);
        }
        return cstmt.execute();
    }

    /**
     * Method that will execute a stored procedure and return no resultSet
     * Note that it is the callers responsibility to provide the correct amount of parameters.
     *
     * @param sp    Enum type {@link Sp}.
     * @param param Variable parameter for the Procedure
     * @return True of successful executed.
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    @SuppressWarnings("Duplicates")
    public Boolean executeStoredProcedureNoRS(Sp sp, Object... param) throws SQLException {
        // Preparing metaData
        Map<Integer, String> metaDataMap = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataMap.size()));
        //Build parameters
        for (int i = 0; i < metaDataMap.size(); i++) {
            setCallParameter(i + 1, metaDataMap.get(i + 1), param[i]);
        }
        return cstmt.execute();
    }

    /**
     * Helper method that will construct the String for a callable statement.
     *
     * @param sp         The name of the Stored procedure
     * @param parameters Number of Parameters for the Stored procedure
     * @return String with the Callable statement
     * @deprecated Since 23/05/19. Replaced with {@link #buildProcedureCall(Procedure, int)}.
     */

    @Deprecated
    @SuppressWarnings("Duplicates")
    private String buildProcedureCall(String sp, int parameters) {
        StringBuilder builder = new StringBuilder();
        builder.append("{call ");
        builder.append(sp);
        if (parameters > 0) {
            builder.append(" (");
            for (int questionMarks = parameters; questionMarks != 0; questionMarks--) {
                builder.append("?");
                if (questionMarks > 1) {
                    builder.append(",");
                }
            }
            builder.append(")");
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * Helper method that will construct the String for a callable statement.
     *
     * @param procedure  Enum type that implements {@link Procedure}.
     * @param parameters Number of Parameters for the Stored procedure
     * @return String with the Callable statement
     */

    @SuppressWarnings("Duplicates")
    private String buildProcedureCall(Procedure procedure, int parameters) {
        System.out.println("procedure = " + procedure.get());
        StringBuilder builder = new StringBuilder();
        builder.append("{call ");
        builder.append(procedure.get());
        if (parameters > 0) {
            builder.append(" (");
            for (int questionMarks = parameters; questionMarks != 0; questionMarks--) {
                builder.append("?");
                if (questionMarks > 1) {
                    builder.append(",");
                }
            }
            builder.append(")");
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * Helper method to set the callable statement values.
     *
     * @param index     Index of callable statement Parameter
     * @param dataType  Type of Data at this Parameter
     * @param dataValue The Object value provided from the application
     * @throws SQLException Exception when SQL encounter a fatal problem
     */

    private void setCallParameter(int index, String dataType, Object dataValue) throws SQLException {
        System.out.println("DB.setCallParameter");
        System.out.println("index = [" + index + "], dataType = [" + dataType + "], dataValue = [" + dataValue + "]");
        switch (dataType) {
            case "int":
                if (dataValue == null) {
                    cstmt.setNull(index, Types.INTEGER);
                } else {
                    cstmt.setInt(index, (Integer) dataValue);
                }
                break;
            case "varchar":
                if (dataValue == null) {
                    cstmt.setNull(index, Types.VARCHAR);
                } else {
                    cstmt.setString(index, (String) dataValue);
                }
                break;
            case "nvarchar":
                if (dataValue == null) {
                    cstmt.setNull(index, Types.NVARCHAR);
                } else {
                    cstmt.setString(index, (String) dataValue);
                }
                break;
            case "numeric":
                if (dataValue == null) {
                    cstmt.setNull(index, Types.NUMERIC);
                } else {
                    cstmt.setBigDecimal(index, (BigDecimal) dataValue);
                }
                break;
            case "date":
                if (dataValue == null) {
                    cstmt.setNull(index, Types.DATE);
                } else {
                    cstmt.setDate(index, java.sql.Date.valueOf((LocalDate)dataValue));
                }
                break;
            case "null":
                cstmt.setNull(index, Types.NULL);

                break;
            default:
                System.out.println("ERROR: Could not define Procedure type");
        }
    }

    /**
     * Method to establish a connection to the Database.
     * Note that this must be called before running any other methods from this class.
     *
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    public void connect() throws SQLException { //TODO make thread save
        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:" + port + ";databaseName=" + databaseName, userName, password);
    }


    /**
     * Method to close connection to the Database.
     *
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    public void disconnect() throws SQLException {
        if (cstmt != null) {
            cstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    /**
     * Hardcoded execution of the sp_GetSPMetaData.
     * This method is used to determine what the parameter should be cast to.
     *
     * @param sp The Stored procedure we want to get Meta Data for
     * @return Map of ProcedureMetaData
     * @throws SQLException Exception when SQL encounter a fatal problem
     * @deprecated Since 23/05/19. Replaced with {@link #getSPMetaData(Procedure)}
     */
    @Deprecated
    @SuppressWarnings("Duplicates")
    private Map<Integer, String> getSPMetaData(String sp) throws SQLException {
        Map<Integer, String> returnMap = new HashMap<>();
        //Establishing call
        cstmt = conn.prepareCall("{call "+SpWithRs.GET_PROCEDURE_META_DATA.get()+"(?)}");
        cstmt.setString(1, sp);
        rs = cstmt.executeQuery();
        // Getting the Metadata
        while (rs.next()) {
            // Building a ProcedureMetaData object
            int position = (int) rs.getObject("ORDINAL_POSITION");
            String type = (String) rs.getObject("DATA_TYPE");
            returnMap.put(position, type);
        }
        // Cleaning up
        cstmt.close();
        rs.close();
        //Returning values
        return returnMap;
    }

    /**
     * Hardcoded execution of the sp_GetSPMetaData.
     * This method is used to determine what the parameter should be cast to.
     *
     * @param procedure Enum type that implements {@link Procedure}.
     * @return Map of ProcedureMetaData
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    @SuppressWarnings("Duplicates")
    private Map<Integer, String> getSPMetaData(Procedure procedure) throws SQLException {
        Map<Integer, String> returnMap = new HashMap<>();
        //Establishing call
        cstmt = conn.prepareCall("{call "+SpWithRs.GET_PROCEDURE_META_DATA.get()+"(?)}");
        cstmt.setString(1, procedure.get());
        rs = cstmt.executeQuery();
        // Getting the Metadata
        while (rs.next()) {
            // Building a ProcedureMetaData object
            int position = (int) rs.getObject("ORDINAL_POSITION");
            String type = (String) rs.getObject("DATA_TYPE");
            returnMap.put(position, type);
        }
        // Cleaning up
        cstmt.close();
        rs.close();
        //Returning values
        return returnMap;
    }

    public void executeScript(File file) {
        try {
            ScriptRunner runner = new ScriptRunner(conn);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            //runner.setStopOnError(false);
            //runner.setSendFullScript(true);
            runner.runScript(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
