USE db_SmartAcademy;
CREATE PROCEDURE sp_Delete_Interview_By_Employee_ID(@EmployeeID INT)
AS
BEGIN
    DELETE FROM tbl_Interview WHERE fld_Employee_ID = @EmployeeID
END;