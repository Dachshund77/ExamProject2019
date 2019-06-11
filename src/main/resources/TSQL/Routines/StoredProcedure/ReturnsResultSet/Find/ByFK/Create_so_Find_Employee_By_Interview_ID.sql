CREATE OR ALTER PROCEDURE sp_Find_Employee_By_Interview_ID(@InterviewID int)
AS
BEGIN
    SELECT [tbl_Employee].fld_EmployeeID        AS EmployeeID,
           [tbl_Employee].fld_EmployeeFirstName AS EmployeeFirstName,
           [tbl_Employee].fld_EmployeeLastName  AS EmployeeLastName,
           [tbl_Employee].fld_CprNr             AS EmployeeCprNr,
           [tbl_Employee].fld_Email             AS EmployeeEmail,
           [tbl_Employee].fld_PhoneNr           AS EmployePhoneNr
    FROM tbl_Employee
             INNER JOIN tbl_Interview ON tbl_Employee.fld_EmployeeID = tbl_Interview.fld_Employee_ID
    WHERE [tbl_Interview].fld_InterviewID = @InterviewID
END;