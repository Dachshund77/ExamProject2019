CREATE OR ALTER PROCEDURE sp_Place_Employee(@EmployeeID INT OUTPUT ,
                                   @NewEmployeeFirstName varchar(30),
                                   @NewEmployeeLastName varchar(30),
                                   @NewCprNr varchar(10),
                                   @NewEmail varchar(30),
                                   @NewPhoneNr varchar(20))
AS
BEGIN
    SET NOCOUNT ON
    IF EXISTS(SELECT * FROM tbl_Employee WHERE fld_EmployeeID = @EmployeeID)
        BEGIN
            UPDATE tbl_Employee
            SET fld_EmployeeFirstName = @NewEmployeeFirstName,
                fld_EmployeeLastName  = @NewEmployeeLastName,
                fld_CprNr             = @NewCprNr,
                fld_Email             = @NewEmail,
                fld_PhoneNr           = @NewPhoneNr
            WHERE fld_CprNr = @NewCprNr
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Employee
            VALUES (@NewEmployeeFirstName, @NewEmployeeLastName, @NewCprNr, @NewEmail, @NewPhoneNr)
            SET @EmployeeID = SCOPE_IDENTITY()
        END
END;
