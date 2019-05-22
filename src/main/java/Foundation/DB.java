package Foundation;

import Persistance.DbFacade;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
     */

    @SuppressWarnings("Duplicates")
    public ResultSet executeStoredProcedure(String sp, Object... param) throws SQLException {

        // Preparing metaData
        Map<Integer,String> metaDataMap = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataMap.size())); //Build the string
        //Build parameters
        for (int i = 0; i < metaDataMap.size(); i++) {
            setCallParameter(i+1, metaDataMap.get(i+1), param[i]);
        }
        //Getting results
        rs = cstmt.executeQuery();

        return rs;
    }

    @SuppressWarnings("Duplicates")
    public void addStoredProcedureToBatch(String sp, Object... param) throws SQLException{
        conn.setAutoCommit(false);
        // Preparing metaData
        Map<Integer,String> metaDataMap = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataMap.size())); //Build the string
        //Build parameters
        for (int i = 0; i < metaDataMap.size(); i++) {
            setCallParameter(i+1, metaDataMap.get(i+1), param[i]);
        }
        cstmt.addBatch();
    }

    public boolean executeBatch() throws SQLException {
        boolean returnBoolean = cstmt.execute();
        conn.commit();
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
     */

    @SuppressWarnings("Duplicates")
    public Boolean executeStoredProcedureNoRS(String sp, Object... param) throws SQLException {
        // Preparing metaData
        Map<Integer,String> metaDataMap = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataMap.size()));
        //Build parameters
        for (int i = 0; i < metaDataMap.size(); i++) {
            setCallParameter(i+1, metaDataMap.get(i+1), param[i]);
        }
        return cstmt.execute();
    }


    /**
     * Helper method that will construct the String for a callable statement.
     *
     * @param sp         The name of the Stored procedure
     * @param parameters Number of Parameters for the Stored procedure
     * @return String with the Callable statement
     */
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
     * Helper method to set the callable statement values.
     *
     * @param index     Index of callable statement Parameter
     * @param dataType  Type of Data at this Parameter
     * @param dataValue The Object value provided from the application
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    private void setCallParameter(int index, String dataType, Object dataValue) throws SQLException {
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
                    cstmt.setDate(index, (Date) dataValue);
                }
                break;
            case "null":
                cstmt.setNull(index, Types.NULL);
                break;
            default:
                System.out.println("ERROR: Could not define Procedue type");
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
     */
    private Map<Integer, String> getSPMetaData(String sp) throws SQLException {
        Map<Integer,String> returnMap = new HashMap<>();
        //Establishing call
        cstmt = conn.prepareCall("{call sp_GetSPMetaData(?)}");
        cstmt.setString(1, sp);
        rs = cstmt.executeQuery();
        // Getting the Metadata
        while (rs.next()) {
            // Building a ProcedureMetaData object
            int position = (int) rs.getObject("ORDINAL_POSITION");
            String type = (String) rs.getObject("DATA_TYPE");
            returnMap.put(position,type);
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
