package DevOpsTools;

import Foundation.DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class ScriptRunner {

    public static void main(String[] args) {
        ScriptRunner scriptRunner = new ScriptRunner();
        scriptRunner.runScripts();
    }

    private void runScripts(){
        ArrayList<String> executionOrder = new ArrayList<>();

        // Create DB
        executionOrder.add(getClass().getResource("/TSQL/DbCreation/Create_db_SmartAcademy.sql").getFile());

        //Table creation
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_Provider.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_Education.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_Date.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_EducationList.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_Company.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_Education_EducationList_Bridge.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_EducationWish.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_FinishedEducation.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_Consultation.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_Employee.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_Consultation_Employee_Bridge.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_Interview.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_FinishedEducation_Interview_Bridge.sql").getFile());
        executionOrder.add(getClass().getResource("/TSQL/TableCreation/Create_tbl_EducationWish_Interview_Bridge.sql").getFile());

        //Add Stored procedures
        executionOrder.add(getClass().getResource("/TSQL/StoredProcedures/Meta/Create_sp_GetSPMetaData.sql").getFile());

        // run the scripts
        try {
            DB.getInstance().connect();
            for (String s : executionOrder) {
                DB.getInstance().executeScript(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DB.getInstance().disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
