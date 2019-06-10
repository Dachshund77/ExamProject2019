CREATE OR ALTER PROCEDURE sp_Find_Employee_By_ID(@ID int)
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
    WHERE fld_EmployeeID = @ID
END;