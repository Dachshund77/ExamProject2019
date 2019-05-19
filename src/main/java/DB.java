import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
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
    private ResultSetMetaData rsmd;

    private DB() {
        Properties props = new Properties();

        URL resourcesURL = getClass().getResource("resources/Database/db.Properties");
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
     * @param sp String with the name of the Stored Procedure
     * @param param Variable parameter for the Procedure
     * @return ResultSet of SP.
     */

    @SuppressWarnings("Duplicates")
    public ResultSet executeStoredProcedure(String sp, Object... param) {
        rsmd = null;
        try {
            // Preparing metaData
            ArrayList<ProcedureMetaData> metaDataArray = getSPMetaData(sp);
            //Setting callable statement
            cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataArray.size()));
            //Build parameters
            for (int i = 0; i < metaDataArray.size(); i++) {
                ProcedureMetaData tempMetaData = metaDataArray.get(i);
                setCallParameter(tempMetaData.position, tempMetaData.dataType, param[i]);
            }
            //Getting results
            rs = cstmt.executeQuery();
            rsmd = rs.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }



    /**
     * Method that will execute a stored procedure and return the no resultSet
     * Note that it is the callers responsibility to provide the correct amount of parameters.
     * @param sp String with the name of the Stored Procedure
     * @param param Variable parameter for the Procedure
     * @return True of successful executed.
     */

    @SuppressWarnings("Duplicates")
    public Boolean executeStoredProcedureNoRS(String sp, Object... param) {
        rsmd = null;
        ArrayList<Object[]> returnArrayList = new ArrayList<>();
        try {
            // Preparing metaData
            ArrayList<ProcedureMetaData> metaDataArray = getSPMetaData(sp);
            //Setting callable statement
            cstmt = conn.prepareCall(buildProcedureCall(sp, metaDataArray.size()));
            //Build parameters
            for (int i = 0; i < metaDataArray.size(); i++) {
                ProcedureMetaData tempMetaData = metaDataArray.get(i);
                setCallParameter(tempMetaData.position, tempMetaData.dataType, param[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * Helper method that will construct the String for a callable statement.
     * @param sp The name of the Stored procedure
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
     * @param index Index of callable statement Parameter
     * @param dataType Type of Data at this Parameter
     * @param dataValue The Object value provided from the application
     */
    private void setCallParameter(int index, String dataType, Object dataValue) {
        try {

            switch (dataType) {
                case "int":
                    if (dataValue == null){
                        cstmt.setNull(index, Types.INTEGER);
                    }else {
                        cstmt.setInt(index, (Integer) dataValue);
                    }
                    break;
                case "varchar":
                    if (dataValue == null) {
                        cstmt.setNull(index,Types.VARCHAR);
                    }else{
                        cstmt.setString(index, (String) dataValue);
                    }
                    break;
                case "nvarchar":
                    if (dataValue == null) {
                        cstmt.setNull(index,Types.NVARCHAR);
                    }else{
                        cstmt.setString(index, (String) dataValue);
                    }
                    break;
                case "numeric":
                    if(dataValue == null) {
                        cstmt.setNull(index, Types.NUMERIC);
                    }else {
                        cstmt.setBigDecimal(index, (BigDecimal) dataValue);
                    }
                    break;
                case "date":
                    if (dataValue == null) {
                        cstmt.setNull(index,Types.DATE);
                    }else {
                        cstmt.setDate(index, (Date) dataValue);
                    }
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to establish a connection to the Database.
     * Note that this must be called before running any other methods from this class.
     */
    public void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:" + port + ";databaseName=" + databaseName, userName, password);
        } catch (SQLException e) { //TODO error handling could be improved in this class in genneral
            e.printStackTrace();
        }
    }

    /**
     * Method to close connection to the Database.
     */
    public void disconnect() {
        try {
            if (cstmt != null) {
                cstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSetMetaData getRsmd() {
        return rsmd;
    }

    /**
     * Hardcoded execution of the sp_GetSPMetaData.
     * This method is used to determine what the parameter should be cast to.
     * @param sp The Stored procedure we want to get Meta Data for
     * @return ArrayList of ProcedureMetaData
     * @see ProcedureMetaData
     */
    private ArrayList<ProcedureMetaData> getSPMetaData(String sp) {
        ArrayList<ProcedureMetaData> returnArrayList = new ArrayList<>();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnArrayList;
    }


    /**
     * Build a map over the column names of the current result set. Note That the starting index is 1.
     * @return Map of Columns with where the Key is index number
     */
    public Map getResultSetMap(){
        HashMap<Integer,String> returnMap = new HashMap();
        try {
            Integer columnCount = rsmd.getColumnCount();
            for (int i = 1; i > columnCount; i++) {
                 String columnName = rsmd.getColumnName(i);
                 returnMap.put(i,columnName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    /**
     * Private note to group information
     */
    private class ProcedureMetaData {
        private int position;
        private String dataType;

        public ProcedureMetaData(int position, String dataType) {
            this.position = position;
            this.dataType = dataType;
        }
    }
}
