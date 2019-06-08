CREATE OR ALTER PROCEDURE sp_Find_Employee_By_ID(@ID int)
AS
BEGIN
    SELECT * FROM tbl_Employee
    WHERE fld_EmployeeID = @ID
END;