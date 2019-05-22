USE db_SmartAcademy;
CREATE PROCEDURE sp_InsertEmployee( @NewEmployeeFirstName varchar(30),
                                    @NewEmployeeLastName varchar(30),
                                    @NewCprNr varchar(10),
                                    @NewEmail varchar(30),
                                    @NewPhoneNr varchar(20))
AS
BEGIN
    IF EXISTS(SELECT * FROM tbl_Employee WHERE fld_CprNr = @NewCprNr)
        BEGIN
            UPDATE tbl_Employee
            SET fld_EmployeeFirstName = @NewEmployeeFirstName,
                fld_EmployeeLastName = @NewEmployeeLastName,
                fld_Email = @NewEmail,
                fld_PhoneNr = @NewPhoneNr
            WHERE fld_CprNr = @NewCprNr
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Employee VALUES (@NewEmployeeFirstName,@NewEmployeeLastName,@NewCprNr,@NewEmail,@NewPhoneNr)
        END
END;
