package Foundation;

import Persistance.DbFacade;
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

/**
 * Access point for Database interaction. This class will be used by {@link DbFacade} to get usable objects from the resultSet.
 * <br/><br/>
 * <Font Color = red>Important</Font Color> Connection must be managed by caller with
 * {@link DB#connect()} and {@link DB#disconnect()}.
 */
public class DB {
    private static DB ourInstance = new DB();

    public static DB getInstance() { //TODO metadata should be loaded at creation of this Class, also own illeaglarugmentexception malformed procedure call
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
     * Method that will execute a {@link SpWithRs Stored procedure with ResultSet} and returns the resultSet reference.
     * Note that it is the callers responsibility to provide the correct amount of parameters.
     * <br/><br/>
     * <Font Color = red>Important</Font Color> Connection must be managed by caller with
     * {@link #connect()} and {@link #disconnect()}.
     *
     * @param sp    Enum Type {@link SpWithRs}.
     * @param param Object array with correct amount of Parameters
     * @return ResultSet of SP.
     * @throws SQLException Exception when SQL encounter a fatal problem
     */

    @SuppressWarnings("Duplicates")
    public ResultSet executeStoredProcedure(SpWithRs sp, Object... param) throws SQLException {
        System.out.println("DB.executeStoredProcedure");
        System.out.println("sp = [" + sp + "], param = [" + Arrays.toString(param) + "] ");
        System.out.println("param length was " + param.length);

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
     * Method that will execute a {@link SpGetKey Stored procedure returning a Single int} that represent the just inserted or updated Primary Key.
     * Note that it is the callers responsibility to provide the correct amount of parameters.
     * <br/><br/>
     * <Font Color = red>Important</Font Color> Connection must be managed by caller with
     * {@link #connect()} and {@link #disconnect()}.
     *
     * @param sp    Enum Type {@link SpGetKey}.
     * @param param Object array with correct amount of Parameters
     * @return int with the just inserted or updated Primary Key.
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    @SuppressWarnings("Duplicates")
    public int executeStoredProcedure(SpGetKey sp, Object... param) throws SQLException { //TODO the method should be done better
        System.out.println("DB.executeStoredProcedure"); //TODO Remove uneeded system.out before deployment
        System.out.println("sp = [" + sp + "], param = [" + Arrays.toString(param) + "]");
        System.out.println("param length was " + param.length);
        // Preparing metaData
        Map<Integer, String> metaDataMap = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataMap.size())); //Build the string
        //Build parameters
        for (int i = 0; i < metaDataMap.size(); i++) {
            setCallParameter(i + 1, metaDataMap.get(i + 1), param[i]);
        }
        //Getting results
        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.execute();
        return cstmt.getInt(1);

    }

    /**
     * Method that will add a {@link Sp Stored procedure} to a batch.
     * Note that it is the callers responsibility to provide the correct amount of parameters
     * and also to execute the batch with {@link #executeBatch()}.
     * If only as single Stored procedure must be executed {@link #executeStoredProcedure(Sp, Object...)}
     * can be used instead.
     * <br/><br/>
     * <Font Color = red>Important</Font Color> Connection must be managed by caller with
     * {@link #connect()} and {@link #disconnect()}.
     *
     * @param sp    Enum Type {@link Sp}.
     * @param param Object array with correct amount of Parameters
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    @SuppressWarnings("Duplicates")
    public void addStoredProcedureToBatch(Sp sp, Object... param) throws SQLException {
        System.out.println("DB.addStoredProcedureToBatch"); //TODO Remove uneeded system out before deployment
        System.out.println("sp = [" + sp + "], param = [" + Arrays.toString(param) + "]");
        System.out.println("param length was " + param.length);

        conn.setAutoCommit(false);
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
     * Note that the batch files will be execute as first in first out order.
     *
     * @return True if successful added to database
     * @throws SQLException Exception when SQL encounter a fatal problem. Will Rollback this transaction.
     */
    public boolean executeBatch() throws SQLException {
        boolean returnBoolean = cstmt.execute();
        try {
            conn.commit();
        } catch (SQLException e) {
            conn.rollback(); //Rollback
            throw e; //Propagate exception upwards.
        }
        return returnBoolean;
    }

    /**
     * Method that will add execute {@link Sp Stored procedure}.
     * Note that it is the callers responsibility to provide the correct amount of parameters
     * and also to execute the batch with {@link #executeBatch()}.
     * If multiple stored procedure must be executed after each other use {@link #addStoredProcedureToBatch(Sp, Object...)} instead.
     * <br/><br/>
     * <Font Color = red>Important</Font Color> Connection must be managed by caller with
     * {@link #connect()} and {@link #disconnect()}.
     *
     * @param sp    Enum Type {@link Sp}.
     * @param param Object array with correct amount of Parameters
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    @SuppressWarnings("Duplicates")
    public boolean executeStoredProcedure(Sp sp, Object... param) throws SQLException {
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
                    cstmt.setDate(index, java.sql.Date.valueOf((LocalDate) dataValue));
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
     * After finished Database interaction {@link #disconnect()} must be called.
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
     * This method is used to determine what the parameter should be cast to with {@link #setCallParameter(int, String, Object)}.
     *
     * @param procedure Enum type that implements {@link Procedure}.
     * @return Map of ProcedureMetaData
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    @SuppressWarnings("Duplicates")
    private Map<Integer, String> getSPMetaData(Procedure procedure) throws SQLException {
        Map<Integer, String> returnMap = new HashMap<>();
        //Establishing call
        cstmt = conn.prepareCall("{call " + SpWithRs.GET_PROCEDURE_META_DATA.get() + "(?)}");
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

    /**
     * Method that takes a sql file an executes it.
     * Note that this should only used when necessarily and the desired functionality
     * can not be achieved with stored procedures.
     *
     * @param file Sql file that will be run.
     */
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
