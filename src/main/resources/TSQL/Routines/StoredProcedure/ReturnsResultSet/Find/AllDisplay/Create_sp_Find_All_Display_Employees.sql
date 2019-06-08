CREATE OR ALTER PROCEDURE sp_Find_All_Display_Employees(@fld_EmployeeID INT, @fld_EmployeeFirstName NVARCHAR(30),
                                                        @fld_EmployeeLastName NVARCHAR(30), @fld_CprNr NVARCHAR(10),
                                                        @fld_Email NVARCHAR(30), @fld_PhoneNr NVARCHAR(20))
AS
BEGIN
    SET NOCOUNT ON

    SELECT fld_EmployeeID AS EmployeeID,
           fld_EmployeeFirstName AS EmployeeFirstName,
           fld_EmployeeLastName AS EmployeeLastName,
           fld_CprNr AS EmployeeCprNr,
           fld_Email AS EmployeeEmail,
           fld_PhoneNr AS EmployePhoneNr
    FROM udf_Filter_tbl_Employee(@fld_EmployeeID, @fld_EmployeeFirstName, @fld_EmployeeLastName, @fld_CprNr, @fld_Email,
                                 @fld_PhoneNr)
END;