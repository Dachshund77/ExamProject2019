USE db_SmartAcademy;
CREATE PROCEDURE sp_InsertEmployeeAsConsultation(@EmployeeID INT,
                                                 @NewEmployeeFirstName varchar(30),
                                                 @NewEmployeeLastName varchar(30),
                                                 @NewCprNr varchar(10),
                                                 @NewEmail varchar(30),
                                                 @NewPhoneNr varchar(20),
                                                 @ConsultationID INT)
AS
BEGIN
    IF EXISTS(SELECT * FROM tbl_Employee WHERE fld_EmployeeID = @EmployeeID)
        BEGIN
            UPDATE tbl_Employee
            SET fld_EmployeeFirstName = @NewEmployeeFirstName,
                fld_EmployeeLastName  = @NewEmployeeLastName,
                fld_CprNr             = @NewCprNr,
                fld_Email             = @NewEmail,
                fld_PhoneNr           = @NewPhoneNr
            WHERE fld_CprNr = @NewCprNr
            EXECUTE sp_InsertConsultation_Employee_Bridge @ConsultationID, @EmployeeID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Employee
            VALUES (@NewEmployeeFirstName, @NewEmployeeLastName, @NewCprNr, @NewEmail, @NewPhoneNr)
            SET @EmployeeID = SCOPE_IDENTITY();
            EXECUTE sp_InsertConsultation_Employee_Bridge @ConsultationID, @EmployeeID
        END
END;
