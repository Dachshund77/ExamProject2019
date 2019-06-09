package DevOpsTools;

import Foundation.DB;
import Foundation.DbFacade;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ScriptRunner {

    public static void main(String[] args) {
        ScriptRunner scriptRunner = new ScriptRunner();
        scriptRunner.runScripts();
    }

    private void runScripts() {
        ArrayList<File> executionOrder = new ArrayList<>();

        // Create DB
        executionOrder.add(new File(getClass().getResource("/TSQL/Script/DbCreation/Create_db_SmartAcademy.sql").getFile()));

        //Table creation
        executionOrder.add(new File(getClass().getResource("/TSQL/Script/TableCreation/Create_tbl_Provider.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Script/TableCreation/Create_tbl_Education.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Script/TableCreation/Create_tbl_Date.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Script/TableCreation/Create_tbl_Company.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Script/TableCreation/Create_tbl_Consultation.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Script/TableCreation/Create_tbl_Employee.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Script/TableCreation/Create_tbl_Consultation_Employee_Bridge.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Script/TableCreation/Create_tbl_Interview.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Script/TableCreation/Create_tbl_EducationWish.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Script/TableCreation/Create_tbl_FinishedEducation.sql").getFile()));

        // Add table types
        executionOrder.addAll(getFilesFromResourceDir(new File(getClass().getResource("/TSQL/Script/TableTypes").getFile())));

        //Add the delete all procedure the order matters here
        executionOrder.add(new File(getClass().getResource("/TSQL/Routines/StoredProcedure/ReturnsNothing/Delete/All/Create_sp_Delete_All_Providers.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Routines/StoredProcedure/ReturnsNothing/Delete/All/Create_sp_Delete_All_Interviews.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Routines/StoredProcedure/ReturnsNothing/Delete/All/Create_sp_Delete_All_Finished_Educations.Sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Routines/StoredProcedure/ReturnsNothing/Delete/All/Create_sp_Delete_All_Employees.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Routines/StoredProcedure/ReturnsNothing/Delete/All/Create_sp_Delete_All_Educations.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Routines/StoredProcedure/ReturnsNothing/Delete/All/Create_sp_Delete_All_Education_Wishes.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Routines/StoredProcedure/ReturnsNothing/Delete/All/Create_sp_Delete_All_Consultations.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Routines/StoredProcedure/ReturnsNothing/Delete/All/Create_sp_Delete_All_Companies.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/Routines/StoredProcedure/ReturnsNothing/Delete/All/Create_sp_Delete_All_DB_Entries.sql").getFile()));
        //It should not matter what order routines get added to the db

        executionOrder.addAll(getFilesFromResourceDir(new File(getClass().getResource("/TSQL/Routines").getFile())));

        // run the scripts
        try {
            DbFacade.connect();
            for (File file : executionOrder) {
                DB.getInstance().executeScript(file);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbFacade.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Find all files given a start node.
     * @param folder Start folder
     * @return ArrayList of all files.
     */
    private ArrayList<File> getFilesFromResourceDir(File folder) {
        ArrayList<File> returnArrayList = new ArrayList<>();

        File[] listOfFiles = folder.listFiles();
        for (File file : Objects.requireNonNull(listOfFiles)) {
            if (file.isDirectory()) {
                returnArrayList.addAll(getFilesFromResourceDir(file));
            } else if (file.isFile()) {
                returnArrayList.add(file);
            } else {
                System.out.println("Unexpected element at");
                System.out.println(file.getAbsoluteFile());
            }
        }
        return returnArrayList;
    }
}
