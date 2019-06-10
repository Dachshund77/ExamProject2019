CREATE OR ALTER PROCEDURE sp_Place_Employee(@EmployeeID INT OUTPUT,
                                            @NewEmployeeFirstName Nvarchar(30),
                                            @NewEmployeeLastName Nvarchar(30),
                                            @NewCprNr Nvarchar(10),
                                            @NewEmail Nvarchar(30),
                                            @NewPhoneNr Nvarchar(20))
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
