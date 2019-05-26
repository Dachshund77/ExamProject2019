CREATE OR ALTER PROCEDURE sp_Place_Provider(@ProviderID INT OUTPUT ,
                                   @newProviderName VARCHAR(30))
AS
BEGIN
    SET NOCOUNT ON
    IF EXISTS(SELECT * FROM tbl_Provider WHERE fld_ProviderID = @ProviderID)
        BEGIN
            UPDATE tbl_Provider
            SET fld_ProviderName = @newProviderName
            WHERE fld_ProviderID = @ProviderID
        END
    ELSE
        BEGIN
            INSERT INTO tbl_Provider VALUES (@newProviderName)
            SET @ProviderID = SCOPE_IDENTITY()
        END
END;

