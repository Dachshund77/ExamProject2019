CREATE OR ALTER PROCEDURE sp_Place_Company(@NewCompID INT OUTPUT , @CvrNr NVARCHAR(80), @CompanyName NVARCHAR(50))
AS
BEGIN
    SET NOCOUNT ON
    IF EXISTS(SELECT * FROM tbl_Company WHERE fld_CompanyID = @NewCompID)
        BEGIN
            UPDATE tbl_Company
            SET fld_CvrNr       = @CvrNr,
                fld_CompanyName = @CompanyName
            WHERE fld_CompanyID = @NewCompID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Company VALUES (@CvrNr, @CompanyName)
            SET @NewCompID = SCOPE_IDENTITY()
        END
END;