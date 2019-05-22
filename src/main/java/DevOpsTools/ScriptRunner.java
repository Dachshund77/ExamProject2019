package DevOpsTools;

import Foundation.DB;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ScriptRunner {

    public static void main(String[] args) {
        ScriptRunner scriptRunner = new ScriptRunner();
        scriptRunner.runScripts();
    }

    private void runScripts(){
        ArrayList<File> executionOrder = new ArrayList<>();

        // Create DB
        executionOrder.add(new File(getClass().getResource("/TSQL/DbCreation/Create_db_SmartAcademy.sql").getFile()));

        //Table creation
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_Provider.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_Education.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_Date.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_Company.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_Company_Education_Bridge.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_EducationWish.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_FinishedEducation.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_Consultation.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_Employee.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_Consultation_Employee_Bridge.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_Interview.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_FinishedEducation_Interview_Bridge.sql").getFile()));
        executionOrder.add(new File(getClass().getResource("/TSQL/TableCreation/Create_tbl_EducationWish_Interview_Bridge.sql").getFile()));

        //It should not matter what order stored procedure get added to the db

        executionOrder.addAll(getFilesFromResourceDir(new File(getClass().getResource("/TSQL/StoredProcedure").getFile())));

        // run the scripts
        try {
            DB.getInstance().connect();
            for (File file : executionOrder) {
                DB.getInstance().executeScript(file);
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

    private ArrayList<File> getFilesFromResourceDir(File folder){
        ArrayList<File> returnArrayList = new ArrayList<>();

        File[] listOfFiles = folder.listFiles();
        for (File file: Objects.requireNonNull(listOfFiles)){
            if (file.isDirectory()){
                returnArrayList.addAll(getFilesFromResourceDir(file));
            } else if (file.isFile()){
                returnArrayList.add(file);
            } else {
                System.out.println("Unexpected element at");
                System.out.println(file.getAbsoluteFile());
            }
        }
        return returnArrayList;
    }
}
