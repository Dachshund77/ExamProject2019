USE db_SmartAcademy;
CREATE PROCEDURE sp_Place_Employee_From_Consultation(@EmployeeID INT,
                                                 @NewEmployeeFirstName varchar(30),
                                                 @NewEmployeeLastName varchar(30),
                                                 @NewCprNr varchar(10),
                                                 @NewEmail varchar(30),
                                                 @NewPhoneNr varchar(20),
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
