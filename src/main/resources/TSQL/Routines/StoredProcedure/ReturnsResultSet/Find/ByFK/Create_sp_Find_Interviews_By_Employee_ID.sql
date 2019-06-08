CREATE OR ALTER PROCEDURE sp_Find_Interviews_By_Employee_ID(@EmployeeID int)
AS
    BEGIN
        SELECT * FROM tbl_Interview
        INNER JOIN tbl_Employee ON tbl_Interview.fld_Employee_ID = tbl_Employee.fld_EmployeeID
        WHERE fld_EmployeeID = @EmployeeID
    END;