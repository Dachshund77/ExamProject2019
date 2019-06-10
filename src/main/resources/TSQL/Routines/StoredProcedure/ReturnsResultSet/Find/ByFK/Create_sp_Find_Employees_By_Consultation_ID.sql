CREATE OR ALTER PROCEDURE sp_Find_Employees_By_Consultation_ID(@ConsultationID int)
AS
    BEGIN
        SET NOCOUNT ON
        SELECT [tbl_Employee].fld_EmployeeID        AS EmployeeID,
               [tbl_Employee].fld_EmployeeFirstName AS EmployeeFirstName,
               [tbl_Employee].fld_EmployeeLastName  AS EmployeeLastName,
               [tbl_Employee].fld_CprNr             AS EmployeeCprNr,
               [tbl_Employee].fld_Email             AS EmployeeEmail,
               [tbl_Employee].fld_PhoneNr           AS EmployePhoneNr
        FROM tbl_Employee
        INNER JOIN tbl_Consultation_Employee_Bridge ON tbl_Employee.fld_EmployeeID = tbl_Consultation_Employee_Bridge.fld_EmployeeID
        WHERE tbl_Consultation_Employee_Bridge.fld_ConsultationID = @ConsultationID
    END;