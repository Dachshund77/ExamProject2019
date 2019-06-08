CREATE OR ALTER PROCEDURE sp_Find_Employees_By_Consultation_ID(@ConsultationID int)
AS
    BEGIN
        SELECT * FROM tbl_Employee
        INNER JOIN tbl_Consultation_Employee_Bridge ON tbl_Employee.fld_EmployeeID = tbl_Consultation_Employee_Bridge.fld_EmployeeID
        WHERE tbl_Consultation_Employee_Bridge.fld_ConsultationID = @ConsultationID
    END;