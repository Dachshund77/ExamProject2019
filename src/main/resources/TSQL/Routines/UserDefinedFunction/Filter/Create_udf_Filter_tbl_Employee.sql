CREATE OR ALTER FUNCTION udf_Filter_tbl_Employee(@fld_EmployeeID INT, @fld_EmployeeFirstName NVARCHAR(30),
                                                 @fld_EmployeeLastName NVARCHAR(30), @fld_CprNr NVARCHAR(10),
                                                 @fld_Email NVARCHAR(30), @fld_PhoneNr NVARCHAR(20))
    RETURNS TABLE AS
        RETURN
        SELECT *
        FROM tbl_Employee
        WHERE (@fld_EmployeeID IS NULL OR fld_EmployeeID = @fld_EmployeeID)
          AND (@fld_EmployeeFirstName IS NULL OR fld_EmployeeFirstName LIKE '%' + @fld_EmployeeFirstName + '%')
          AND (@fld_EmployeeLastName IS NULL OR fld_EmployeeLastName LIKE '%' + @fld_EmployeeLastName + '%')
          AND (@fld_CprNr IS NULL OR fld_CprNr LIKE '%' + @fld_CprNr + '%')
          AND (@fld_Email IS NULL OR fld_Email LIKE '%' + @fld_Email + '%')
          AND (@fld_PhoneNr IS NULL OR fld_PhoneNr LIKE '%' + @fld_PhoneNr + '%');
