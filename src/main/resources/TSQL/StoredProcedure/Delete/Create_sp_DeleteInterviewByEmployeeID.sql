USE db_SmartAcademy;
CREATE PROCEDURE sp_DeleteInterviewByEmployeeID(@EmployeeID INT)
AS
BEGIN
    DELETE FROM tbl_Interview WHERE fld_Employee_ID = @EmployeeID
END;