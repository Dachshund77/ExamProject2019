CREATE PROCEDURE sp_InsertCompany(@NewCompID INT, @CvrNr INT, @CompanyName VARCHAR(50))
AS
BEGIN
    IF EXISTS(SELECT * FROM tbl_Company WHERE fld_CompanyID = @NewCompID)
        BEGIN
            UPDATE tbl_Company
            SET fld_CvrNr       = @CvrNr,
                fld_CompanyName = @CompanyName
            WHERE fld_CompanyID = @NewCompID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Company VALUES (@CompanyName, @CvrNr)
        END
END;