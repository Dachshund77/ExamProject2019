CREATE OR ALTER PROCEDURE sp_Place_Employee_From_Consultation(@EmployeeID INT,
                                                 @NewEmployeeFirstName Nvarchar(30),
                                                 @NewEmployeeLastName Nvarchar(30),
                                                 @NewCprNr Nvarchar(10),
                                                 @NewEmail Nvarchar(30),
                                                 @NewPhoneNr Nvarchar(20),
                                                 @ConsultationID INT)
AS
BEGIN
    DECLARE @insertedEmployeeID INT
    IF EXISTS(SELECT * FROM tbl_Employee WHERE fld_EmployeeID = @EmployeeID)
        BEGIN
            UPDATE tbl_Employee
            SET fld_EmployeeFirstName = @NewEmployeeFirstName,
                fld_EmployeeLastName  = @NewEmployeeLastName,
                fld_CprNr             = @NewCprNr,
                fld_Email             = @NewEmail,
                fld_PhoneNr           = @NewPhoneNr
            WHERE fld_CprNr = @NewCprNr
            SET @insertedEmployeeID = SCOPE_IDENTITY()
            EXECUTE sp_Insert_Consultation_Employee_Bridge @ConsultationID, @insertedEmployeeID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Employee
            VALUES (@NewEmployeeFirstName, @NewEmployeeLastName, @NewCprNr, @NewEmail, @NewPhoneNr)
            SET @insertedEmployeeID = SCOPE_IDENTITY()
            EXECUTE sp_Insert_Consultation_Employee_Bridge @ConsultationID, @insertedEmployeeID
        END
END;
