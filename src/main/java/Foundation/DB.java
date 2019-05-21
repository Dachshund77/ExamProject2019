package Foundation;

import Domain.Company;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
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

    private Map typeMape;

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
        } catch (IOException |ClassNotFoundException e) {
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
    public RsData executeStoredProcedure(String sp, Object... param) throws SQLException {

        // Preparing metaData
        ArrayList<ProcedureMetaData> metaDataArray = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataArray.size())); //Build the string
        //Build parameters
        for (int i = 0; i < metaDataArray.size(); i++) {
            ProcedureMetaData tempMetaData = metaDataArray.get(i);
            setCallParameter(tempMetaData.position, tempMetaData.dataType, param[i]);
        }
        //Getting results
        rs = cstmt.executeQuery();

        return new RsData(rs);
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
        ArrayList<Object[]> returnArrayList = new ArrayList<>();
        // Preparing metaData
        ArrayList<ProcedureMetaData> metaDataArray = getSPMetaData(sp);
        //Setting callable statement
        cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataArray.size()));
        //Build parameters
        for (int i = 0; i < metaDataArray.size(); i++) {
            ProcedureMetaData tempMetaData = metaDataArray.get(i);
            setCallParameter(tempMetaData.position, tempMetaData.dataType, param[i]);
        }
        return true;
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
        System.err.println(dataType);
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

                //TODO maybe make a default type for Java object?
        }

    }

    /**
     * Method to establish a connection to the Database.
     * Note that this must be called before running any other methods from this class.
     *
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    public void connect() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:" + port + ";databaseName=" + databaseName, userName, password);
        setTypeMap();
    }

    /**
     * Helper method that maps the sql typfes for the SqlData interface
     * @see SQLData
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    private void setTypeMap() throws SQLException {
        //Setting the Type map fpr SQLData interface
        Map<String, Class<?>> typeMap= conn.getTypeMap();
        typeMap.put("type_Company", Company.class);
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
     * @return ArrayList of ProcedureMetaData
     * @throws SQLException Exception when SQL encounter a fatal problem
     * @see ProcedureMetaData
     */
    private ArrayList<ProcedureMetaData> getSPMetaData(String sp) throws SQLException {
        ArrayList<ProcedureMetaData> returnArrayList = new ArrayList<>();
        //Establishing call
        cstmt = conn.prepareCall("{call sp_GetSPMetaData(?)}");
        cstmt.setString(1, sp);
        rs = cstmt.executeQuery();
        // Getting the Metadata
        while (rs.next()) {
            // Building a ProcedureMetaData object
            int position = (int) rs.getObject("ORDINAL_POSITION");
            String type = (String) rs.getObject("DATA_TYPE");
            ProcedureMetaData tempMetaData = new ProcedureMetaData(position, type);
            returnArrayList.add(tempMetaData);
        }
        // Cleaning up
        cstmt.close();
        rs.close();
        //Returning values
        return returnArrayList;
    }

    public void executeScript(String path){
        try {
            ScriptRunner runner = new ScriptRunner(conn);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(path));
            //runner.setStopOnError(false);
            //runner.setSendFullScript(true);
            runner.runScript(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private note to group information, Mainly used to build the stored procedure string and set its values.
     */
    private class ProcedureMetaData { //TODO This can and should be replaced with a map
        private int position;
        private String dataType;

        ProcedureMetaData(int position, String dataType) {
            this.position = position;
            this.dataType = dataType;
        }
    }
}
