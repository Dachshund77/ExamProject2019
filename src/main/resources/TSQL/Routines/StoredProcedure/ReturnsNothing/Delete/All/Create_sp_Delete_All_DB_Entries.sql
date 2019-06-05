CREATE OR ALTER PROCEDURE sp_Delete_All_DB_Entries
AS
BEGIN
    EXECUTE sp_Delete_All_Providers;
    EXECUTE sp_Delete_All_Educations;
    EXECUTE sp_Delete_All_Education_Wishes;
    EXECUTE sp_Delete_All_Finished_Educations;
    EXECUTE sp_Delete_All_Interviews;
    EXECUTE sp_Delete_All_Employees;
    EXECUTE sp_Delete_All_Consultations;
    EXECUTE sp_Delete_All_Companies;
END;