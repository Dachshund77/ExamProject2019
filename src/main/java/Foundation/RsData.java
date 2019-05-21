package Foundation;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Wrapper class to keep a result set and the map over columns together.
 */
public class RsData {

    private ResultSet rs;
    private HashMap columnMap;

    public RsData(ResultSet rs) throws SQLException{ //TODO we proably dont evnen eed ti BS here anymore
        this.rs = rs;
        this.columnMap = getResultSetMap();
    }

    /**
     * Build a map over the column names of the current result set. Note That the starting index is 1.
     *
     * @return Map of Columns with where the Key is index number
     * @throws SQLException Exception when SQL encounter a fatal problem
     */
    public HashMap<Integer, String> getResultSetMap() throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        HashMap<Integer, String> returnMap = new HashMap<>();
        Integer columnCount = rsmd.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rsmd.getColumnName(i);
            returnMap.put(i, columnName);
        }

        return returnMap;
    }

    public ResultSet getRs() {
        return rs;
    }

    public HashMap getColumnMap() {
        return columnMap;
    }
}
