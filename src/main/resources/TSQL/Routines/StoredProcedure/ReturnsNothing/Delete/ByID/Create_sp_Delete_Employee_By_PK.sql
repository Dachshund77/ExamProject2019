CREATE OR ALTER PROCEDURE sp_Delete_Employee_By_PK(@EmployeeID int) AS
BEGIN
    DELETE FROM tbl_Employee WHERE fld_EmployeeID = @EmployeeID
END;